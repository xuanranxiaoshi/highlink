package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.dao.CacheDao;
import info.nemoworks.highlink.dao.CachePool;
import info.nemoworks.highlink.dataflow.encoder.ExitLocalETCEncoder;
import info.nemoworks.highlink.dataflow.encoder.ExitLocalOthersEncoder;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.splitTransaction.*;
import info.nemoworks.highlink.model.pathTransaction.MultiProvincePathTrans;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.model.pathTransaction.SingleProvincePathTrans;
import info.nemoworks.highlink.sink.MultiProvincePathCacheSink;
import info.nemoworks.highlink.utils.SimpleContainer;
import info.nemoworks.highlink.utils.SinkUtils;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author：jimi 拆分业务处理
 * @date: 2024/3/5
 * @Copyright：
 * @Description:
 *   交易拆分类别:
 *    1. B1：计费方式只有 1， 根据 A 进行拆分
 *    2. B2/details: 计费方式有3、4、5、6，分别根据 F2、E 进行拆分
 *    3. B3: 计费方式只有 1, 根据 A 进行拆分
 *    4. B4/details: 计费方式有3、4、5、6，分别根据 F2、E 进行拆分
 *
 */
public class SplitDataFlow {


    public static final String A_PREFIX = "A:";
    public static final String B1_PREFIX = "B1:";
    public static final String B2_PREFIX = "B2:";
    public static final String B3_PREFIX = "B3:";
    public static final String B4_PREFIX = "B4:";
    public static final String G_PREFIX = "G:";
    public static final String F2_PREFIX = "F2:";
    public static final String E_PREFIX = "E:";


    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final ObjectMapper mapper = SimpleContainer.getObjectMapper();
    private static final CachePool cachePool = SimpleContainer.getCachePool();



    public static DataStream<SplitResult> flow(DataStream<List<PathTransaction>> aggregatePathStream, DataStream<ProvinceTransaction> provinceStream){

        final OutputTag<List<PathTransaction>> multiProvinceOutputTag = new OutputTag<>("multiProvince") {};

        // 1. 不同拆分业务分流
        SingleOutputStreamOperator<SingleProvincePathTrans> singleProvinceSplitStream = aggregatePathStream.process(
                new ProcessFunction<List<PathTransaction>, SingleProvincePathTrans>() {
                    @Override
                    public void processElement(List<PathTransaction> pathList,
                                               ProcessFunction<List<PathTransaction>, SingleProvincePathTrans>.Context ctx,
                                               Collector<SingleProvincePathTrans> out) throws Exception {
                        PathTransaction exitTrans = pathList.getLast();
                        PathTransaction entryTrans = pathList.get(0);
                        // todo: 根据属性判断
                        if (exitTrans instanceof ExitRawTransaction exitRawTransaction && entryTrans instanceof EntryRawTransaction) {
                            // 将路径数据转化为单省数据
                            out.collect(new SingleProvincePathTrans(pathList)); // 单省数据
                        }
                        else {
                            ctx.output(multiProvinceOutputTag, pathList);   // 跨省数据
                        }
                    }
                }
        ).setParallelism(1).name("单/多省通行记录划分");

        SideOutputDataStream<List<PathTransaction>> multiProvinceStream = singleProvinceSplitStream.getSideOutput(multiProvinceOutputTag);

        // 跨省拆分由部中心的消息触发，先将跨省聚合路径写入缓存
        multiProvinceStream.addSink(new MultiProvincePathCacheSink()).name("多省通行记录暂存").setParallelism(1);

        // 2. 单省拆分: 直接利用聚得到的路径数据进行拆分
        DataStream<SplitResult> singleProvinceSplitRes = processSingleProvince(singleProvinceSplitStream);

        // 3. 多省拆分
        DataStream<SplitResult> multiProvinceSplitRes = processMultiProvince(provinceStream);

        DataStream<SplitResult> union = singleProvinceSplitRes.union(multiProvinceSplitRes);

        return union;
    }


    /**
     * 单省拆分业务逻辑实现
     *
     * @param singleProvinceSplitStream
     * @return
     */
    private static DataStream<SplitResult> processSingleProvince(DataStream<SingleProvincePathTrans> singleProvinceSplitStream){
        final OutputTag<ExitLocalOtherTrans> exitLocalOtherOutputTag = new OutputTag<ExitLocalOtherTrans>("exitLocalOtherOutputTag") {};
        final OutputTag<ExitLocalETCTrans> exitLocalETCOutputTag = new OutputTag<ExitLocalETCTrans>("exitLocalETCOutputTag") {};

        SingleOutputStreamOperator<SplitResult> splitResultStream = singleProvinceSplitStream.process(new ProcessFunction<SingleProvincePathTrans, SplitResult>() {
            @Override
            public void processElement(SingleProvincePathTrans value,
                                       ProcessFunction<SingleProvincePathTrans, SplitResult>.Context ctx,
                                       Collector<SplitResult> out) throws Exception {
                // 进行单省拆分
                value.splitCharge();
                HighwayTransaction exitTrans = value.getUpdateRes();
                if (exitTrans instanceof ExitLocalETCTrans exitLocalETCTrans) {
                    // 单省 ETC
                    exitLocalETCTrans.setVersion(2);
                    ctx.output(exitLocalETCOutputTag, exitLocalETCTrans);
                    out.collect(exitLocalETCTrans);
                }
                else if (exitTrans instanceof ExitLocalOtherTrans exitLocalOtherTrans) {
                    // 单省 CPC
                    exitLocalOtherTrans.setVersion(2);
                    ctx.output(exitLocalOtherOutputTag, exitLocalOtherTrans);
                    out.collect(exitLocalOtherTrans);
                }
            }
        }).name("单省拆分");

        SideOutputDataStream<ExitLocalETCTrans> exitLocalETCTransStream = splitResultStream.getSideOutput(exitLocalETCOutputTag);
        SideOutputDataStream<ExitLocalOtherTrans> exitLocalOtherTransStream = splitResultStream.getSideOutput(exitLocalOtherOutputTag);


//        SinkUtils.addFileSinkToStream(exitLocalETCTransStream, "exit_local_etc", new ExitLocalETCEncoder());
//        SinkUtils.addFileSinkToStream(exitLocalOtherTransStream, "exit_local_other", new ExitLocalOthersEncoder());
        // todo: 产生背压
        SinkUtils.addInsertSinkToStream(exitLocalETCTransStream,ExitLocalETCTrans.class,"eixtLocalETC_update");
        SinkUtils.addInsertSinkToStream(exitLocalOtherTransStream, ExitLocalOtherTrans.class, "exitLocalOther_update");

        return splitResultStream;
    }

    /**
     * 多省交易数据拆分业务处理
     *
     * @param provinceStream
     * @return
     */
    private static DataStream<SplitResult> processMultiProvince(DataStream<ProvinceTransaction> provinceStream){
        final OutputTag<ETCSplitResultGantry> ETCSplitResultGantryOutputTag = new OutputTag<ETCSplitResultGantry>("ETCSplitResultGantryOutputTag") {};
        final OutputTag<ETCSplitResultExit> ETCSplitResultExitOutputTag = new OutputTag<ETCSplitResultExit>("ETCSplitResultExitOutputTag") {};
        final OutputTag<OtherSplitResultGantry> OtherSplitResultGantryOutputTag = new OutputTag<OtherSplitResultGantry>("OtherSplitResultGantryOutputTag") {};
        final OutputTag<OtherSplitResultExit> OtherSplitResultExitOutputTag = new OutputTag<OtherSplitResultExit>("OtherSplitResultExitOutputTag") {};
        final OutputTag<SplitDetailExit> SplitDetailExitOutputTag = new OutputTag<SplitDetailExit>("SplitDetailExitOutputTag") {};
        final OutputTag<SerTollSum> SerTollSumOutputTag = new OutputTag<SerTollSum>("SerTollSumOutputTag") {};

        SingleOutputStreamOperator<SplitResult> splitStream = provinceStream.process(new ProcessFunction<ProvinceTransaction, SplitResult>() {
            @Override
            public void processElement(ProvinceTransaction value,
                                       ProcessFunction<ProvinceTransaction, SplitResult>.Context ctx,
                                       Collector<SplitResult> out) throws Exception {
                // 1. B1 数据拆分
                if (value instanceof ETCSplitResultGantry b1){
                    String id = b1.getID();
                    // 查询 A
                    ProvinceTransaction connectedA = query(A_PREFIX + id, SerTollSum.class);
                    if(connectedA != null){
                        ETCSplitResultGantry etcSplitResultGantry = calculateB1(b1, (SerTollSum) connectedA);
                        ctx.output(ETCSplitResultGantryOutputTag, etcSplitResultGantry);
                        out.collect(etcSplitResultGantry);
                    }else{
                        writeToCache(B1_PREFIX + b1.getID(), b1);
                    }
                }
                // 2. B2 数据拆分
                else if(value instanceof ETCSplitResultExit b2){
                    String id = b2.getID();
                    String passId = b2.getPASSID();
                    Integer feeType = Integer.valueOf(b2.getEXITFEETYPE());
                    // 2.1 计费方式 3， 根据 F2:门架数据拆分
                    if(feeType == 3){
                        String f2_str = query(F2_PREFIX + passId);
                        if(f2_str != null){
                            ETCSplitResultExit etcSplitResultExit = calculateB2_F(b2, f2_str);
                            ctx.output(ETCSplitResultExitOutputTag, etcSplitResultExit);
                            out.collect(etcSplitResultExit);
                        }else{
                            writeToCache(B2_PREFIX + F2_PREFIX + id, b2);
                            System.out.println("[Cache Info] B2 Can't find [" + F2_PREFIX + passId + "]");
                        }
                    }
                    // 2.2 计费方式 4/5，根据 E:出口附属交易数据拆分
                    else if(feeType == 4 || feeType == 5){
                        // E 是预处理子系统的输出、现在直接模拟产生
                        ProvinceTransaction connectedE = query(E_PREFIX + id, ExitWaste.class);
                        if(connectedE != null){
                            ETCSplitResultExit etcSplitResultExit = calculateB2_E(b2, (ExitWaste) connectedE);
                            ctx.output(ETCSplitResultExitOutputTag, etcSplitResultExit);
                            out.collect(etcSplitResultExit);
                        }else{
                            writeToCache(B2_PREFIX + id, b2);
                        }
                    }
                }
                // 3. B3 数据拆分
                else if (value instanceof OtherSplitResultGantry b3){
                    String id = b3.getID();
                    // 查询 A
                    ProvinceTransaction connectedA = query(A_PREFIX + id, SerTollSum.class);
                    if(connectedA != null){
                        OtherSplitResultGantry etcSplitResultGantry = calculateB3(b3, (SerTollSum) connectedA);
                        ctx.output(OtherSplitResultGantryOutputTag, etcSplitResultGantry);
                        out.collect(etcSplitResultGantry);
                    }else{
                        writeToCache(B3_PREFIX + id, b3);
                    }
                }
                // 4. B4 数据拆分
                else if(value instanceof OtherSplitResultExit b4){
                    String passId = b4.getPASSID();
                    String id = b4.getID();
                    Integer feeType = Integer.valueOf(b4.getEXITFEETYPE());
                    // 2.1 计费方式 3， 根据 F2:门架数据拆分
                    if(feeType == 3){
                        String f2_str = query(F2_PREFIX + passId);
                        if(f2_str != null){
                            OtherSplitResultExit otherSplitResultExit = calculateB4_F(b4, f2_str);
                            ctx.output(OtherSplitResultExitOutputTag, otherSplitResultExit);
                            out.collect(otherSplitResultExit);
                        }
                        else{
                            writeToCache(B4_PREFIX + F2_PREFIX + id, b4);
                            System.out.println("[Cache Info] B4 Can't find [" + F2_PREFIX + passId + "]");
                        }
                    }
                    // 2.2 计费方式 4/5，根据 E:出口附属交易数据拆分
                    else if(feeType == 4 || feeType == 5){
                        // E 是预处理子系统的输出、现在直接模拟产生
                        ProvinceTransaction connectedE = query(E_PREFIX + id, ExitWaste.class);
                        if(connectedE != null){
                            OtherSplitResultExit otherSplitResultExit = calculateB4_E(b4, (ExitWaste) connectedE);
                            ctx.output(OtherSplitResultExitOutputTag, otherSplitResultExit);
                            out.collect(otherSplitResultExit);
                        }else{
                            writeToCache(B4_PREFIX + id, b4);
                        }
                    }
                }
                //  A 数据拆分
                else if(value instanceof SerTollSum a){
                    String id = a.getID();
                    // 查询 B1 | B3
                    ProvinceTransaction connectedB = query(B1_PREFIX + id, ETCSplitResultGantry.class);
                    ProvinceTransaction connectedB3 = query(B3_PREFIX + id, OtherSplitResultGantry.class);
                    if(connectedB !=null){
                        ETCSplitResultGantry etcSplitResultGantry = calculateB1((ETCSplitResultGantry) connectedB, a);
                        out.collect(etcSplitResultGantry);
                        out.collect(etcSplitResultGantry);
                    }else if(connectedB3 != null){
                        OtherSplitResultGantry otherSplitResultGantry = calculateB3((OtherSplitResultGantry) connectedB3, a);
                        ctx.output(OtherSplitResultGantryOutputTag, otherSplitResultGantry);
                        out.collect(otherSplitResultGantry);
                    }else{
                        writeToCache(A_PREFIX + a.getID(), a);
                    }
                }
                //  E 数据拆分
                else if(value instanceof ExitWaste e){
                    String id = e.getID();
                    // 查询 B2 | B4
                    ProvinceTransaction connectedB = query(B2_PREFIX + id, ETCSplitResultExit.class);
                    ProvinceTransaction connectedB4 = query(B4_PREFIX + id, OtherSplitResultExit.class);
                    if(connectedB !=null){
                        ETCSplitResultExit etcSplitResultExit = calculateB2_E((ETCSplitResultExit) connectedB, e);
                        ctx.output(ETCSplitResultExitOutputTag, etcSplitResultExit);
                        out.collect(etcSplitResultExit);
                    }else if(connectedB4 != null){
                        OtherSplitResultExit otherSplitResultExit = calculateB4_E((OtherSplitResultExit) connectedB4, e);
                        ctx.output(OtherSplitResultExitOutputTag, otherSplitResultExit);
                        out.collect(otherSplitResultExit);
                    }
                    else{
                        writeToCache(E_PREFIX + id, e);
                    }
                }
            }
        }).setParallelism(1).name("多省拆分");

        SideOutputDataStream<ETCSplitResultGantry> eTCSplitResultGantryStream = splitStream.getSideOutput(ETCSplitResultGantryOutputTag);
        SideOutputDataStream<ETCSplitResultExit> eTCSplitResultExitStream = splitStream.getSideOutput(ETCSplitResultExitOutputTag);
        SideOutputDataStream<OtherSplitResultGantry> otherSplitResultGantryStream = splitStream.getSideOutput(OtherSplitResultGantryOutputTag);
        SideOutputDataStream<OtherSplitResultExit> otherSplitResultExitStream = splitStream.getSideOutput(OtherSplitResultExitOutputTag);


        SinkUtils.addInsertSinkToStream(eTCSplitResultGantryStream, ETCSplitResultGantry.class, "ETCSplitResultGantry");
        SinkUtils.addInsertSinkToStream(eTCSplitResultExitStream, ETCSplitResultExit.class, "ETCSplitResultExit");
        SinkUtils.addInsertSinkToStream(otherSplitResultGantryStream, OtherSplitResultGantry.class, "OtherSplitResultGantry");
        SinkUtils.addInsertSinkToStream(otherSplitResultExitStream, OtherSplitResultExit.class, "OtherSplitResultExit");

        return splitStream;
    }

    private static ETCSplitResultGantry calculateB1(ETCSplitResultGantry b1, SerTollSum a) throws JsonProcessingException {
        String tollIntervalFee = a.getTOLLINTERVALFEE();
        String tollIntervalDiscountFee = a.getTOLLINTERVALDISCOUNTFEE();
        String tollIntervalPayFee = a.getTOLLINTERVALPAYFEE();
        // 拆分逻辑：简单 copy
        b1.setSPLITFLAG("1");
        b1.setSPLITTIME(sdf.format(new Date()));
        b1.setSPLITRULE("1");
        b1.setSPLITOWNERGROUP(a.getTOLLINTERVALID());
        b1.setSPLITOWNERFEEGROUP(tollIntervalFee);
        b1.setSPLITOWNERPAYFEEGROUP(tollIntervalPayFee);
        b1.setSPLITOWNERDISFEEGROUP(tollIntervalDiscountFee);

        return b1;
    }

    private static ETCSplitResultExit calculateB2_E(ETCSplitResultExit b2, ExitWaste e) {
        if(e == null){
            System.out.println("[Split Error] Can't find E data: " + b2.getPASSID());
            return null;
        }
        // 拆分逻辑：简单 copy
        b2.setSPLITFLAG("1");
        b2.setSPLITTIME(sdf.format(new Date()));
        b2.setNATIONSPLITTYPE("2");
        b2.setSPLITOWNERGROUP(e.getSPLITOWNERGROUP());
        b2.setSPLITOWNERFEEGROUP(e.getSPLITOWNERFEEGROUP());
        b2.setSPLITOWNERPAYFEEGROUP(e.getSPLITOWNERPAYFEEGROUP());
        b2.setSPLITOWNERDISFEEGROUP(e.getSPLITOWNERDISFEEGROUP());

        return b2;
    }

    private static ETCSplitResultExit calculateB2_F(ETCSplitResultExit b2, String f2Str) throws JsonProcessingException {
        if(f2Str == null){
            System.out.println("[Split Error] Can't find F2 data: " + b2.getPASSID());
            return null;
        }

        MultiProvincePathTrans f2 = new MultiProvincePathTrans(f2Str);
        f2.splitCharge();

        // 拆分逻辑：简单 copy
        b2.setSPLITFLAG("1");
        b2.setSPLITTIME(sdf.format(new Date()));
        b2.setNATIONSPLITTYPE("2");
        b2.setSPLITOWNERGROUP(f2.getChargeUnitesStr());
        b2.setSPLITOWNERFEEGROUP(f2.getFeeStr());
        b2.setSPLITOWNERPAYFEEGROUP(f2.getPayFeeStr());
        b2.setSPLITOWNERDISFEEGROUP(f2.getDiscountStr());

        return b2;
    }

    private static OtherSplitResultGantry calculateB3(OtherSplitResultGantry b3, SerTollSum a) {
        String tollIntervalFee = a.getTOLLINTERVALFEE();
        String tollIntervalDiscountFee = a.getTOLLINTERVALDISCOUNTFEE();
        String tollIntervalPayFee = a.getTOLLINTERVALPAYFEE();
        // 拆分逻辑：简单 copy
        b3.setSPLITFLAG("1");
        b3.setSPLITTIME(sdf.format(new Date()));
        b3.setSPLITRULE("1");
        b3.setSPLITOWNERGROUP(a.getTOLLINTERVALID());
        b3.setSPLITOWNERFEEGROUP(tollIntervalFee);
        b3.setSPLITOWNERPAYFEEGROUP(tollIntervalPayFee);
        b3.setSPLITOWNERDISFEEGROUP(tollIntervalDiscountFee);
        return b3;
    }

    private static OtherSplitResultExit calculateB4_E(OtherSplitResultExit b4, ExitWaste e) {
        if(e == null){
            System.out.println("[Split Error] Can't find E data: " + b4.getPASSID());
            return null;
        }
        // 拆分逻辑：简单 copy
        b4.setSPLITFLAG("1");
        b4.setSPLITTIME(sdf.format(new Date()));
        b4.setNATIONSPLITTYPE("2");
        b4.setSPLITOWNERGROUP(e.getSPLITOWNERGROUP());
        b4.setSPLITOWNERFEEGROUP(e.getSPLITOWNERFEEGROUP());
        b4.setSPLITOWNERPAYFEEGROUP(e.getSPLITOWNERPAYFEEGROUP());
        b4.setSPLITOWNERDISFEEGROUP(e.getSPLITOWNERDISFEEGROUP());

        return b4;
    }

    private static OtherSplitResultExit calculateB4_F(OtherSplitResultExit b4, String f2Str) throws JsonProcessingException {

        // fixme:
        if(f2Str == null){
            System.out.println("[Split Error] Can't find F2 data: " + b4.getPASSID());
            return null;
        }

        MultiProvincePathTrans f2 = new MultiProvincePathTrans(f2Str);
        f2.splitCharge();

        // 拆分逻辑：简单 copy
        b4.setSPLITFLAG("1");
        b4.setSPLITTIME(sdf.format(new Date()));
        b4.setNATIONSPLITTYPE("2");
        b4.setSPLITOWNERGROUP(f2.getChargeUnitesStr());
        b4.setSPLITOWNERFEEGROUP(f2.getFeeStr());
        b4.setSPLITOWNERPAYFEEGROUP(f2.getPayFeeStr());
        b4.setSPLITOWNERDISFEEGROUP(f2.getDiscountStr());

        return b4;
    }

    /**
     * 将关联数据还未到达的数据写入缓存
     * @param value
     * @throws JsonProcessingException
     */
    private static void writeToCache(String key, ProvinceTransaction value) throws JsonProcessingException {
        CacheDao cacheDao = null;
        try {
            cacheDao = cachePool.getDaoImp();
            cacheDao.set(key, mapper.writeValueAsString(value));
        }finally {
            if(cacheDao != null) {
                cacheDao.close();
            }
        }
    }

    private static ProvinceTransaction query(String key, Class<?> clazz) throws JsonProcessingException {
        CacheDao cacheDao = null;
        try {
            cacheDao = cachePool.getDaoImp();
            String res = cacheDao.get(key);
            if (res == null) {
                System.out.println("[Cache Info] Can't find [" + key + "]");
                return null;
            } else {
                JsonNode jsonNode = mapper.readTree(res);
                System.out.println("[Cache Info] Find & Del [" + key + "]");
                // 删除读取的数据
                cacheDao.del(key);
                return (ProvinceTransaction) mapper.treeToValue(jsonNode, clazz);
            }
        } finally {
            if (cacheDao != null) {
                cacheDao.close();
            }
        }
    }

    private static String query(String key){
        CacheDao cacheDao = null;
        try{
            cacheDao = cachePool.getDaoImp();
            String res = cacheDao.get(key);
            if(res == null){
                System.out.println("[Cache Info] Can't find [" + key + "]");
            }else{
                cacheDao.del(key);
                System.out.println("[Cache Info] Find & Del [" + key + "]");
            }
            return res;
        }finally {
            if(cacheDao != null) {
                cacheDao.close();
            }
        }
    }


}
