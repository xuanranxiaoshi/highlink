package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.dao.CacheDao;
import info.nemoworks.highlink.dao.CachePool;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.utils.SimpleContainer;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 对聚合路径的异常处理流
 *  正常数据：
 *      entry/省界入口
 *      门架数据
 *      exit/省界出口
 *  异常类型：
 *      case 1:
 *
 * @author：jimi
 * @date: 2024/3/12
 * @Copyright：
 */
public class ExceptionFlow {

    private static final ObjectMapper objectMapper;
    private static final CachePool cachePool;

    static  {
        objectMapper = SimpleContainer.getObjectMapper();
        cachePool = SimpleContainer.getCachePool();
    }

    public static DataStream<List<PathTransaction>> flow(DataStream<List<PathTransaction>> aggregatePathStream){

        // 按照 passId 进行 keyBy, 保证同意路径的数据在同一个线程中进行处理
        KeyedStream<List<PathTransaction>, String> keyedStream = aggregatePathStream.keyBy(new KeySelector<List<PathTransaction>, String>() {
            @Override
            public String getKey(List<PathTransaction> value) throws Exception {
                return value.get(0).getPASSID();
            }
        });

        SingleOutputStreamOperator<List<PathTransaction>> cleanPathFlow = keyedStream.process(new KeyedProcessFunction<String, List<PathTransaction>, List<PathTransaction>>() {
            @Override
            public void processElement(List<PathTransaction> pathTransactionList,
                                       KeyedProcessFunction<String, List<PathTransaction>, List<PathTransaction>>.Context ctx,
                                       Collector<List<PathTransaction>> out) throws Exception {
                if (pathTransactionList.size() == 0) {
                    System.out.println("[Error] ExceptionFlow：聚合路径长度为 0 ");
                    return;
                }
                if (isNormalData(pathTransactionList)) { // 正常数据
                    System.out.println(Thread.currentThread().getName() + " [Info] 正常数据： " + pathTransactionList.get(0).getPASSID());
                    out.collect(pathTransactionList);
                }
                else{   // 非正常数据
                    // 1. 查询缓存数据并合并
                    List<PathTransaction> mergedPath = mergeFromRedis(pathTransactionList);
                    // 2. 正常数据返回
                    if(isNormalData(mergedPath)){
                        removePath(mergedPath.get(0).getPASSID());
                        out.collect(mergedPath);
                    }
                    // 3. 异常数据缓存
                    else{
                        write2Cache(mergedPath);
                    }
                }
            }
        }).name("异常路径清理");

        return cleanPathFlow;
    }


    /**
     * 判断路径数据是否正常
     */
    private static boolean isNormalData(List<PathTransaction> pathTransactionList){
        return pathTransactionList.size() > 1 &&
                isEntryData(pathTransactionList.get(0)) &&
                isExitData(pathTransactionList.get(pathTransactionList.size() - 1));
    }

    /**
     * 判断是否为出口或者省界出口数据
     */
    private static boolean isExitData(PathTransaction pathTransaction){
        if(pathTransaction instanceof ExitRawTransaction) {
            return true;
        }
        return (pathTransaction instanceof GantryRawTransaction gantryRawTransaction) && gantryRawTransaction.getGANTRYTYPE() == 3;
    }

    /**
     * 判断是否为入口或者省界入口门架数据
     */
    private static boolean isEntryData(PathTransaction pathTransaction){
        if(pathTransaction instanceof EntryRawTransaction){
            return true;
        }
        return pathTransaction instanceof GantryRawTransaction gantryRawTransaction && gantryRawTransaction.getGANTRYTYPE() == 2;
    }

    /**
     * 删除缓存数据
     */
    private static boolean removePath(String passID){
        try (CacheDao cacheDao = cachePool.getDaoImp()) {
            long del = cacheDao.del(passID);
            System.out.println("[Cache] del: " + passID + ", result: " + del);
            return del != 0;
        }
    }

    /**
     * 从缓存中查询同一路径的数据并合并
     */
    private static List<PathTransaction> mergeFromRedis(List<PathTransaction> pathTransactionLinkedList) throws JsonProcessingException {

        String passID = pathTransactionLinkedList.get(0).getPASSID();

        try (CacheDao cacheDao = cachePool.getDaoImp()) {

            // 查询缓存的路径数据
            String cachePathListStr = cacheDao.get(passID);

            // 合并缓存数据
            if (cachePathListStr != null) {
                List<PathTransaction> cachePathList = string2PathList(cachePathListStr);
                System.out.println("[Cache] merge from cache: " + passID +",size: " + cachePathList.size());
                pathTransactionLinkedList.addAll(cachePathList);
                // 按照时间顺序对数据进行排序
                pathTransactionLinkedList.sort(new Comparator<PathTransaction>() {
                    @Override
                    public int compare(PathTransaction o1, PathTransaction o2) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date date1 = sdf.parse(o1.peekTime());
                            Date date2 = sdf.parse(o2.peekTime());
                            return (int) (date1.getTime() - date2.getTime());
                        } catch (ParseException e) {
                            return -1;
                        }
                    }
                });
                System.out.println("[Cache] merged from redis: " + passID +",size: " + pathTransactionLinkedList.size());
            }else{
                System.out.println("[Cache] first write redis: " + passID +",size: " + pathTransactionLinkedList.size());
            }
            return pathTransactionLinkedList;
        }
    }

    /**
     * 将路径数据写入缓存
     */
    private static void write2Cache(List<PathTransaction> mergedPath) {
        try (CacheDao  cacheDao = cachePool.getDaoImp()){
            cacheDao.set(mergedPath.get(0).getPASSID(), objectMapper.writeValueAsString(mergedPath));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json 字符串反序列化
     */
    private static List<PathTransaction> string2PathList(String pathStr) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(pathStr);
        LinkedList<PathTransaction> preList = new LinkedList<>();
        JsonNode curNode;
        PathTransaction curPathTrans;
        for (int i = 0; i < jsonNode.size(); i++) {
            curNode = jsonNode.get(i);
            if (curNode.get("EXTOLLSTATION") != null) {
                curPathTrans = objectMapper.treeToValue(curNode, ExitRawTransaction.class);
            }else if (curNode.get("GANTRYID") != null) {
                curPathTrans = objectMapper.treeToValue(curNode, GantryRawTransaction.class);
            }else{
                curPathTrans = objectMapper.treeToValue(curNode, EntryRawTransaction.class);
            }
            preList.add(curPathTrans);
        }
        return preList;
    }

}
