package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.connector.JedisConnectorHelper;
import info.nemoworks.highlink.dataflow.encoder.PathEncoder;
import info.nemoworks.highlink.dataflow.utils.utils;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.mapper.LocalObjectMapper;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.sink.PathListRedisSink;
import info.nemoworks.highlink.sink.WriteOnlyAnnotationIntrospector;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import redis.clients.jedis.Jedis;

import java.util.LinkedList;

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

    private static Jedis jedis;
    private static ObjectMapper objectMapper;

    static {
        jedis = JedisConnectorHelper.getRedis();
        objectMapper = LocalObjectMapper.getObjectMapper();
        objectMapper.setAnnotationIntrospector(new WriteOnlyAnnotationIntrospector());
    }
    public static DataStream<LinkedList<PathTransaction>> flow(DataStream<LinkedList<PathTransaction>> aggregatePathStream){

        final OutputTag<LinkedList<PathTransaction>> unOrderedPath = new OutputTag<>("unOrderedPath"){};
        final OutputTag<LinkedList<PathTransaction>> overTimePath = new OutputTag<LinkedList<PathTransaction>>("outTimePath"){};
        final OutputTag<LinkedList<PathTransaction>> latePath = new OutputTag<>("latePath"){};
        SingleOutputStreamOperator<LinkedList<PathTransaction>> cleanPathFlow = aggregatePathStream.process(new ProcessFunction<LinkedList<PathTransaction>, LinkedList<PathTransaction>>() {
            @Override
            public void processElement(LinkedList<PathTransaction> pathTransactionLinkedList,
                                       ProcessFunction<LinkedList<PathTransaction>, LinkedList<PathTransaction>>.Context ctx,
                                       Collector<LinkedList<PathTransaction>> out) throws Exception {
                if (pathTransactionLinkedList.size() == 0) {
                    System.out.println("[Error] ExceptionFlow：聚合路径长度为 0 ");
                    return;
                }
                if (pathTransactionLinkedList.size() > 1 &&
                        isEntryData(pathTransactionLinkedList.get(0)) &&
                        isExitData(pathTransactionLinkedList.get(pathTransactionLinkedList.size() - 1))) { // 正常数据
                    System.out.println("[Info] 正常数据： " + pathTransactionLinkedList.get(0).getPASSID());
                    out.collect(pathTransactionLinkedList);
                } else if(!isExitData(pathTransactionLinkedList.get(pathTransactionLinkedList.size()-1))){    //  1. path 不以出口(eixt、省界出口门架)结尾：超时数据
                    LinkedList<PathTransaction> mergeList = mergeFromRedis(pathTransactionLinkedList);
                    System.out.println("[Error] 超时数据：" + pathTransactionLinkedList.get(0).getPASSID());
                    ctx.output(overTimePath, mergeList);
                } else if(isExitData(pathTransactionLinkedList.get(pathTransactionLinkedList.size()-1))) {    //  2. path 以出口结尾：迟到数据, 直接触发计算
                    LinkedList<PathTransaction> mergeList = mergeFromRedis(pathTransactionLinkedList);
                    if(removePath(mergeList.get(0).getPASSID())) {
                        System.out.println("[Error] 迟到数据(deleted): " + pathTransactionLinkedList.get(pathTransactionLinkedList.size()-1).getPASSID());
                        ctx.output(latePath, mergeList);
                    }else{
                        System.out.println("[Error] 迟到数据: " + pathTransactionLinkedList.get(pathTransactionLinkedList.size()-1).getPASSID());
                    }
                }else{
                    System.out.println("[Info] 乱序数据：" + pathTransactionLinkedList.get(0).getPASSID());
                    ctx.output(unOrderedPath, pathTransactionLinkedList);
                }
            }
        });

        // 1. 异常分流
        SideOutputDataStream<LinkedList<PathTransaction>> unOrderedPathFlow = cleanPathFlow.getSideOutput(unOrderedPath);
        SideOutputDataStream<LinkedList<PathTransaction>> overTimePathStream = cleanPathFlow.getSideOutput(overTimePath);
        SideOutputDataStream<LinkedList<PathTransaction>> latePathFlow = cleanPathFlow.getSideOutput(latePath);

        // 1. 超时数据接 redis 暂存
        overTimePathStream.addSink(new PathListRedisSink()).name("overTimePath");
        // 2. latePathFlow 读取 redis 数据重新计算
        DataStream<LinkedList<PathTransaction>> completeStream = cleanPathFlow.union(latePathFlow);
        // 3. 记录计算错误数据
        utils.addFileSinkToStream(unOrderedPathFlow, "unOrderedPath", new PathEncoder());

        return completeStream;
    }

    /**
     * 判断是否为出口或者省界出口数据
     * @param pathTransaction
     * @return
     */
    private static boolean isExitData(PathTransaction pathTransaction){
        if(pathTransaction instanceof ExitRawTransaction) {
            return true;
        }
        return (pathTransaction instanceof GantryRawTransaction gantryRawTransaction) && gantryRawTransaction.getGANTRYTYPE() == 3;
    }

    private static  boolean isEntryData(PathTransaction pathTransaction){
        if(pathTransaction instanceof EntryRawTransaction){
            return true;
        }
        return pathTransaction instanceof GantryRawTransaction gantryRawTransaction && gantryRawTransaction.getGANTRYTYPE() == 2;
    }

    private static boolean removePath(String passID){
        long del = jedis.del(passID);
        return del != 0;
    }

    private static LinkedList<PathTransaction> mergeFromRedis(LinkedList<PathTransaction> pathTransactionLinkedList) throws JsonProcessingException {
        PathTransaction pathTransaction = pathTransactionLinkedList.get(0);
        String passID = pathTransaction.getPASSID();
        String s = jedis.get(passID);
        // 1. redis 不存在记录直接返回
        if(s == null){
            System.out.println("[Info] First write: " + passID);
            return pathTransactionLinkedList;
        }
        // 1. redis 中已有数据，则取出进行合并
        else{
            JsonNode jsonNode = objectMapper.readTree(s);
            LinkedList<PathTransaction> preList = new LinkedList<>();
            JsonNode curNode;
            PathTransaction curPathTrans;
            for (int i = 0; i < jsonNode.size(); i++) {
                curNode = jsonNode.get(i);
                if (curNode.get("EXTOLLSTATION".toLowerCase()) != null) {
                    curPathTrans = objectMapper.treeToValue(curNode, ExitRawTransaction.class);
                }else if (curNode.get("GANTRYID".toLowerCase()) != null) {
                    curPathTrans = objectMapper.treeToValue(curNode, GantryRawTransaction.class);
                }else{
                    curPathTrans = objectMapper.treeToValue(curNode, EntryRawTransaction.class);
                }
                preList.add(curPathTrans);
            }
            preList.addAll(pathTransactionLinkedList);
            System.out.println("[Info] Merge : " + passID);
            return preList;
        }
    }
}
