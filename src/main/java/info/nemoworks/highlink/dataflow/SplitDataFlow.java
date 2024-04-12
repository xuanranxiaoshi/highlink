package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.dao.CacheDao;
import info.nemoworks.highlink.dao.CachePool;
import info.nemoworks.highlink.dataflow.encoder.ExitLocalETCEncoder;
import info.nemoworks.highlink.dataflow.encoder.ExitLocalOthersEncoder;
import info.nemoworks.highlink.sink.MultiProvincePathCacheSink;
import info.nemoworks.highlink.utils.SinkUtils;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.utils.SimpleContainer;
import info.nemoworks.highlink.model.multiProvince.*;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.pathTransaction.SingleProvincePathTrans;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author：jimi 拆分子系统
 * @date: 2024/3/5
 * @Copyright：
 */
public class SplitDataFlow {


    private static final String A_PREFIX = "A:";
    private static final String B1_PREFIX = "B1:";
    private static final String B2_PREFIX = "B2:";
    private static final String B3_PREFIX = "B3:";
    private static final String B4_PREFIX = "B4:";
    private static final String G_PREFIX = "G:";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final ObjectMapper mapper = SimpleContainer.getObjectMapper();

    private static final CachePool cachePool = SimpleContainer.getCachePool();



    public static void flow(DataStream<LinkedList<PathTransaction>> aggregatePathStream, DataStreamSource provinceStream){

        final OutputTag<LinkedList<PathTransaction>> multiProvinceOutputTag = new OutputTag<>("multiProvince") {};

        // 1. 不同拆分业务分流
        SingleOutputStreamOperator<SingleProvincePathTrans> singleProvinceSplitStream = aggregatePathStream.process(
                new ProcessFunction<LinkedList<PathTransaction>, SingleProvincePathTrans>() {
                    @Override
                    public void processElement(LinkedList<PathTransaction> pathList,
                                               ProcessFunction<LinkedList<PathTransaction>, SingleProvincePathTrans>.Context ctx,
                                               Collector<SingleProvincePathTrans> out) throws Exception {
                        PathTransaction exitTrans = pathList.getLast();
                        PathTransaction entryTrans = pathList.get(0);
                        // todo: 根据属性判断
                        if (exitTrans instanceof ExitRawTransaction exitRawTransaction && entryTrans instanceof EntryRawTransaction) {
                            out.collect(new SingleProvincePathTrans(pathList)); // 单省数据
                        } else {
                            System.out.println("[Info] 跨省数据（跨省入口|跨省出口|）, passID = "+ pathList.get(0).getPASSID());
                            ctx.output(multiProvinceOutputTag, pathList);
                        }
                    }
                }
        ).setParallelism(1).name("单/多省通行记录划分");

        SideOutputDataStream<LinkedList<PathTransaction>> multiProvinceStream = singleProvinceSplitStream.getSideOutput(multiProvinceOutputTag);

        // 跨省聚合路径写入 redis 缓存
        multiProvinceStream.addSink(new MultiProvincePathCacheSink()).name("多省通行记录暂存").setParallelism(1);

        // 2. 单省拆分逻辑
        processSingleProvince(singleProvinceSplitStream);

        // 3. 多省拆分
        processMultiProvince(provinceStream);

    }


    /**
     * 单省拆分业务逻辑实现
     * @param singleProvinceSplitStream
     */
    private static void processSingleProvince(DataStream<SingleProvincePathTrans> singleProvinceSplitStream){
        final OutputTag<ExitLocalOtherTrans> exitLocalOtherOutputTag = new OutputTag<ExitLocalOtherTrans>("exitLocalOtherOutputTag") {};

        SingleOutputStreamOperator<ExitLocalETCTrans> exitLocalETCTransStream = singleProvinceSplitStream.process(new ProcessFunction<SingleProvincePathTrans, ExitLocalETCTrans>() {
            @Override
            public void processElement(SingleProvincePathTrans value,
                                       ProcessFunction<SingleProvincePathTrans, ExitLocalETCTrans>.Context ctx,
                                       Collector<ExitLocalETCTrans> out) throws Exception {
                // 进行单省拆分
                value.splitCharge();
                HighwayTransaction exitTrans = value.getUpdateRes();
                if (exitTrans instanceof ExitLocalETCTrans exitLocalETCTrans) {
                    // 单省 ETC
                    out.collect(exitLocalETCTrans);
                } else if (exitTrans instanceof ExitLocalOtherTrans exitLocalOtherTrans) {
                    // 单省 CPC
                    ctx.output(exitLocalOtherOutputTag, exitLocalOtherTrans);
                }
            }
        }).name("单省拆分");

        SideOutputDataStream<ExitLocalOtherTrans> exitLocalOtherTransStream = exitLocalETCTransStream.getSideOutput(exitLocalOtherOutputTag);

        // todo: 重写更新数据库文件
        SinkUtils.addFileSinkToStream(exitLocalETCTransStream, "exit_local_etc", new ExitLocalETCEncoder());
        SinkUtils.addFileSinkToStream(exitLocalOtherTransStream, "exit_local_other", new ExitLocalOthersEncoder());
    }

    /**
     * 多省拆分业务处理
     * @param provinceStream
     */
    private static void processMultiProvince(DataStreamSource provinceStream){
        final OutputTag<ETCSplitResultExit> ETCSplitResultExitOutputTag = new OutputTag<ETCSplitResultExit>("ETCSplitResultExitOutputTag") {};
        final OutputTag<OtherSplitResultGantry> OtherSplitResultGantryOutputTag = new OutputTag<OtherSplitResultGantry>("OtherSplitResultGantryOutputTag") {};
        final OutputTag<OtherSplitResultExit> OtherSplitResultExitOutputTag = new OutputTag<OtherSplitResultExit>("OtherSplitResultExitOutputTag") {};
        final OutputTag<SplitDetailExit> SplitDetailExitOutputTag = new OutputTag<SplitDetailExit>("SplitDetailExitOutputTag") {};
        final OutputTag<SerTollSum> SerTollSumOutputTag = new OutputTag<SerTollSum>("SerTollSumOutputTag") {};

        SingleOutputStreamOperator ETCSplitResultGantryStream = provinceStream.process(new ProcessFunction<ProvinceTransaction, ETCSplitResultGantry>() {
            @Override
            public void processElement(ProvinceTransaction value,
                                       ProcessFunction<ProvinceTransaction, ETCSplitResultGantry>.Context ctx,
                                       Collector<ETCSplitResultGantry> out) throws Exception {
                // 1. 查询
                ProvinceTransaction queryRes = query(value);
                if(queryRes == null){
                    writeToCache(value);
                }
                else{
                    // B1
                    if(value instanceof ETCSplitResultGantry etcSplitResultGantry){
                        ProvinceTransaction updateRes = calculate(etcSplitResultGantry, queryRes);
                        out.collect((ETCSplitResultGantry) updateRes);
                    }
                    // A
                    else if(value instanceof SerTollSum serTollSum){
                        ProvinceTransaction updateRes = calculate(queryRes, serTollSum);
                        out.collect((ETCSplitResultGantry) updateRes);
                    }
                    // B2
                    else if(value instanceof ETCSplitResultExit etcSplitResultExit){
                        ctx.output(ETCSplitResultExitOutputTag, etcSplitResultExit);
                    }
                    // B3
                    else if(value instanceof OtherSplitResultGantry otherSplitResultGantry){
                    }
                    // B4
                    else if(value instanceof OtherSplitResultExit otherSplitResultExit){
                    }
                    // 明细表
                    else if(value instanceof SplitDetailExit splitDetailExit){
                    }
                }
            }
        }).name("多省拆分");

        SideOutputDataStream eTCSplitResultExitStream = ETCSplitResultGantryStream.getSideOutput(ETCSplitResultExitOutputTag);

        SinkUtils.addInsertSinkToStream(ETCSplitResultGantryStream, ETCSplitResultGantry.class, "ETCSplitResultGantry");
        SinkUtils.addInsertSinkToStream(eTCSplitResultExitStream, ETCSplitResultExit.class, "ETCSplitResultExit");
    }

    /**
     * 多省拆分核心计算
     * @param valueB
     * @param associatedData
     */
    private static ProvinceTransaction calculate(ProvinceTransaction valueB, ProvinceTransaction associatedData) {
        // case 1
        if(valueB instanceof ETCSplitResultGantry etcSplitResultGantry && associatedData instanceof  SerTollSum serTollSum){
            String splitFee = etcSplitResultGantry.getSPLITFEE();
            String tollIntervalFee = serTollSum.getTOLLINTERVALFEE();
            String tollIntervalDiscountFee = serTollSum.getTOLLINTERVALDISCOUNTFEE();
            String tollIntervalPayFee = serTollSum.getTOLLINTERVALPAYFEE();
            // 拆分逻辑：简单 copy
            etcSplitResultGantry.setSPLITFLAG("1");
            etcSplitResultGantry.setSPLITTIME(sdf.format(new Date()));
            etcSplitResultGantry.setSPLITRULE("1");
            etcSplitResultGantry.setSPLITOWNERGROUP(serTollSum.getSECTIONID());
            etcSplitResultGantry.setSPLITOWNERFEEGROUP(tollIntervalFee);
            etcSplitResultGantry.setSPLITOWNERPAYFEEGROUP(tollIntervalPayFee);
            etcSplitResultGantry.setSPLITOWNERDISFEEGROUP(tollIntervalDiscountFee);

            return etcSplitResultGantry;
        }
        System.out.println("[Error] calculate type error");
        return null;
    }

    /**
     * 将关联数据未到达的数据写入redis 缓存
     *
     * @param value
     * @throws JsonProcessingException
     */
    private static void writeToCache(ProvinceTransaction value) throws JsonProcessingException {
        CacheDao cacheDao = null;
        try {
            cacheDao = cachePool.getDaoImp();
            // B1
            if (value instanceof ETCSplitResultGantry etcSplitResultGantry) {
                String id = etcSplitResultGantry.getID();
                String set = cacheDao.set(B1_PREFIX + id, mapper.writeValueAsString(etcSplitResultGantry));
                System.out.println("write B1 to Cache: " + set);
            }
            // B2
            else if (value instanceof ETCSplitResultExit etcSplitResultExit) {

            }
            // B3
            else if (value instanceof OtherSplitResultGantry otherSplitResultGantry) {

            }
            // B4
            else if (value instanceof OtherSplitResultExit otherSplitResultExit) {

            }
            // 明细表
            else if (value instanceof SplitDetailExit splitDetailExit) {

            }
            // A
            else if (value instanceof SerTollSum serTollSum) {
                String id = serTollSum.getID();
                String set = cacheDao.set(A_PREFIX + id, mapper.writeValueAsString(serTollSum));
                System.out.println("write A to Cache: " + set);
            }
        }finally {
            if(cacheDao != null) {
                cacheDao.close();
            }
        }
    }

    /**
     * 查询并返回关联数据项
     * @param value
     * @return
     */
    private static ProvinceTransaction query(ProvinceTransaction value) throws JsonProcessingException {
        CacheDao cacheDao = null;
        try{
            cacheDao = cachePool.getDaoImp();
            // B1
            if(value instanceof ETCSplitResultGantry etcSplitResultGantry){
                String id = etcSplitResultGantry.getID();
                String connectA = cacheDao.get(A_PREFIX + id);
                if(connectA == null) {
                    System.out.println("[INFO] can't find [" + A_PREFIX + id + "]");
                    return null;
                }else{
                    JsonNode jsonNode = mapper.readTree(connectA);
                    System.out.println("[INFO] find [" + A_PREFIX + id + "]");
                    return mapper.treeToValue(jsonNode, SerTollSum.class);
                }
            }
            // B2
            else if(value instanceof ETCSplitResultExit etcSplitResultExit){

            }
            // B3
            else if(value instanceof OtherSplitResultGantry otherSplitResultGantry){

            }
            // B4
            else if(value instanceof OtherSplitResultExit otherSplitResultExit){

            }
            // 明细表
            else if(value instanceof SplitDetailExit splitDetailExit){

            }
            // A
            else if(value instanceof SerTollSum serTollSum){
                String id = serTollSum.getID();
                String connectA = cacheDao.get(B1_PREFIX + id);
                if(connectA == null) {
                    System.out.println("[INFO] can't find [" + B1_PREFIX + id + "]");
                    return null;
                }else{
                    JsonNode jsonNode = mapper.readTree(connectA);
                    System.out.println("[INFO] find [" + B1_PREFIX + id + "]");
                    return mapper.treeToValue(jsonNode, ETCSplitResultGantry.class);
                }
            }
        }finally {
            if(cacheDao != null) {
                cacheDao.close();
            }
        }
        return null;
    }


}
