package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.utils.SinkUtils;
import info.nemoworks.highlink.functions.PathAggregateFunction;
import info.nemoworks.highlink.functions.PathProcessWindowFunction;
import info.nemoworks.highlink.functions.PathTrigger;
import info.nemoworks.highlink.metric.LinkCounter;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.model.tollChangeTransaction.TollChangeTransactions;
import info.nemoworks.highlink.model.exitTransaction.*;
import info.nemoworks.highlink.model.extendTransaction.*;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryEtcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.mapper.ExitMapper;
import info.nemoworks.highlink.model.mapper.ExtensionMapper;
import info.nemoworks.highlink.model.mapper.GantryMapper;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedList;

/**
 * @description: 预处理子系统业务逻辑实现
 * @author：jimi
 * @date: 2024/1/7
 * @Copyright：
 */
public class PrepareFlow {

    public static SingleOutputStreamOperator<LinkedList<PathTransaction>> flow(DataStreamSource unionStream) throws Exception {

        // 1. 切分为不同的数据流
        final OutputTag<ExitRawTransaction> exitTrans = new OutputTag<ExitRawTransaction>("exitTrans") {
        };
        final OutputTag<ParkTransWasteRec> extendTrans = new OutputTag<ParkTransWasteRec>("extendTrans") {
        };
        final OutputTag<GantryRawTransaction> gantryTrans = new OutputTag<GantryRawTransaction>("gantryTrans") {
        };

        SingleOutputStreamOperator<EntryRawTransaction> mainDataStream = unionStream
                .process(new ProcessFunction<HighwayTransaction, EntryRawTransaction>() {
                    @Override
                    public void processElement(HighwayTransaction value,
                                               ProcessFunction<HighwayTransaction, EntryRawTransaction>.Context ctx,
                                               Collector<EntryRawTransaction> out) throws Exception {
                        if (value instanceof ExitRawTransaction) {
                            ctx.output(exitTrans, (ExitRawTransaction) value);
                        } else {
                            if (value instanceof GantryRawTransaction) {
                                ctx.output(gantryTrans, (GantryRawTransaction) value);
                            } else {
                                if (value instanceof ParkTransWasteRec) {
                                    ctx.output(extendTrans,
                                            (ParkTransWasteRec) value);
                                } else {
                                    out.collect((EntryRawTransaction) value);
                                }
                            }
                        }
                    }
                })
                .name("UnionStreamSplit")
                .setParallelism(2);

        // 2. 将数据流按规则进行拆分
        DataStream<GantryRawTransaction> gantryStream = mainDataStream.getSideOutput(gantryTrans);
        DataStream<ExitRawTransaction> exitStream = mainDataStream.getSideOutput(exitTrans);
        DataStream<ParkTransWasteRec> extendStream = mainDataStream.getSideOutput(extendTrans);
        DataStream<EntryRawTransaction> entryStream = mainDataStream.map(new LinkCounter<>("RawEntryTransCounter")).name("RawEntryTransCounter");


        SingleOutputStreamOperator<GantryRawTransaction> rawGantryTrans = gantryStream.map(new LinkCounter<>("RawGantryTransCounter")).name("RawGantryTransCounter");

        SingleOutputStreamOperator<ExitRawTransaction> rawExitTrans = exitStream.map(new LinkCounter<>("RawExitTransCounter")).name("RawExitTransCounter");

        SingleOutputStreamOperator<ParkTransWasteRec> rawExdTrans = extendStream.map(new LinkCounter<>("RawExdTransCounter")).name("RawExdTransCounter");


        // 3.1 拓展数据预处理
        processExdTrans(rawExdTrans);

        // 3.2 出口数据预处理
        processExitTrans(rawExitTrans);


        // 3.3 入口流水
        DataStream<EntryRawTransaction> entryCopyStream = entryStream.broadcast();
        SinkUtils.addInsertSinkToStream(entryStream, EntryRawTransaction.class, "entryRawStream");

        // 3.4 门架数据预处理:
        // (1) 原始数据预处理
        processGantryTrans(rawGantryTrans);

        // (2) 门架路径聚合
        DataStream<GantryRawTransaction> gantryCopyStream = gantryStream.broadcast();
        DataStream<ExitRawTransaction> exitCopyStream = exitStream.broadcast();

        SingleOutputStreamOperator<LinkedList<PathTransaction>> aggregatePathStream =
                processPath(gantryCopyStream, entryCopyStream, exitCopyStream);

        return aggregatePathStream;
    }


    private static ExitRawTransaction reCompute(ExitRawTransaction value) {
        return value;
    }

    private static SingleOutputStreamOperator<LinkedList<PathTransaction>> processPath(DataStream<GantryRawTransaction> gantryStream,
                                                                                       DataStream<EntryRawTransaction> entryCopyStream,
                                                                                       DataStream<ExitRawTransaction> exitCopyStream) {

        // 0. 参数设置
        // 乱序等待 gap
        Duration OutOfOrderGap = Duration.ofMinutes(1);
        // 会话超时时间
        Time sessionGap = Time.minutes(10);

        // 1. 合并 entry, gantry, exit 数据流
        DataStream<GantryRawTransaction> gantryCopyStream = gantryStream.broadcast();
        SingleOutputStreamOperator<PathTransaction> connetStream = gantryCopyStream.connect(entryCopyStream).map(new CoMapFunction<GantryRawTransaction, EntryRawTransaction, PathTransaction>() {
            @Override
            public PathTransaction map2(EntryRawTransaction entryRawTransaction) throws Exception {
                return (PathTransaction) entryRawTransaction;
            }

            @Override
            public PathTransaction map1(GantryRawTransaction rawTransaction) throws Exception {
                return (PathTransaction) rawTransaction;
            }
        });
        SingleOutputStreamOperator<PathTransaction> pathTransStream = connetStream.connect(exitCopyStream).map(new CoMapFunction<PathTransaction, ExitRawTransaction, PathTransaction>() {
            @Override
            public PathTransaction map1(PathTransaction pathTransaction) throws Exception {
                return pathTransaction;
            }

            @Override
            public PathTransaction map2(ExitRawTransaction exitRawTransaction) throws Exception {
                return (PathTransaction) exitRawTransaction;
            }
        });


        // 2. 定义 Watermark 策略: 采用事件语义，提取 enTime 作为逻辑时间
        WatermarkStrategy<PathTransaction> watermarkStrategy = WatermarkStrategy
                // 数据的乱序程度
//                .<PathTransaction>forBoundedOutOfOrderness(OutOfOrderGap)
                .<PathTransaction>forMonotonousTimestamps()
                .withTimestampAssigner(new SerializableTimestampAssigner<PathTransaction>() {
                    @Override
                    public long extractTimestamp(PathTransaction rawTransaction, long l) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = null;
                        try {
                            date = dateFormat.parse(rawTransaction.peekTime());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        long timestamp = date.getTime();
                        // 返回的时间戳，毫秒
                        // System.out.println("数据= { id: " + rawTransaction.getPASSID() + ", enTime: " + rawTransaction.getENTIME() + " }");
                        return timestamp;
                    }
                })
                // 解决多并行度某并行分支没有更新时的推进问题
                .withIdleness(Duration.ofSeconds(5));

        // 指定 watermark 策略，添加水位线
        SingleOutputStreamOperator<PathTransaction> pathTransWithWatermark = pathTransStream.assignTimestampsAndWatermarks(watermarkStrategy);


        // 3. 根据 passId 对数据流开窗
        SingleOutputStreamOperator<LinkedList<PathTransaction>> aggregatePathStream = pathTransWithWatermark
                .keyBy(PathTransaction::getPASSID)
                .window(EventTimeSessionWindows.withGap(sessionGap))
                .trigger(new PathTrigger())
                .aggregate(new PathAggregateFunction(), new PathProcessWindowFunction())
                .setParallelism(2);

        // 4. 返回聚合路径
        return aggregatePathStream;
    }

    private static void processGantryTrans(DataStream<GantryRawTransaction> gantryStream) {
        final OutputTag<GantryCpcTransaction> ganCpcTag = new OutputTag<GantryCpcTransaction>("gantryCpcTrans") {
        };

        // 1. 对拆分得到的门架数据流进行处理
        SingleOutputStreamOperator<GantryEtcTransaction> gantryAllStream = gantryStream
                .process(new ProcessFunction<GantryRawTransaction, GantryEtcTransaction>() {

                    @Override
                    public void processElement(GantryRawTransaction value,
                                               ProcessFunction<GantryRawTransaction, GantryEtcTransaction>.Context ctx,
                                               Collector<GantryEtcTransaction> out) throws Exception {
                        // 处理逻辑 1：判断通行介质是否为OBU
                        if (value.peekETC()) {    // 是：转化为门架ETC计费流水数据
                            ctx.output(ganCpcTag, GantryMapper.INSTANCE.gantryRawToCpcTransaction(value));
                        } else {                // 否：转化为门架CPC计费流水
                            out.collect(GantryMapper.INSTANCE.gantryRawToEtcTransaction(value));
                        }
                    }
                })
                .name("GantryTransProcess")
                .setParallelism(2);

        // 2. 通过判断逻辑拆分数据流
        DataStream<GantryCpcTransaction> gantryCpcStream = gantryAllStream.getSideOutput(ganCpcTag).map(new LinkCounter<>("gantryCpcCounter")).name("gantryCpcCounter");
        SingleOutputStreamOperator<GantryEtcTransaction> gantryEtcStream = gantryAllStream.map(new LinkCounter<>("gantryEtcCounter")).name("gantryEtcCounter");


        // 3. 分别对两类数据进行记录
        SinkUtils.addInsertSinkToStream(gantryCpcStream, GantryCpcTransaction.class, "gantryCpcStream");
        SinkUtils.addInsertSinkToStream(gantryEtcStream, GantryEtcTransaction.class, "gantryEtcStream");
    }

    private static void processExdTrans(DataStream<ParkTransWasteRec> parkStream) {
        final OutputTag<TollChangeTransactions> exdChangeTag = new OutputTag<>("extChangeTrans") {
        };
        final OutputTag<ExdForeignGasTransaction> extForeignGasTag = new OutputTag<>("extForeignGasTrans") {
        };
        final OutputTag<ExdForeignMunicipalTransaction> extForeignMunicipalTag = new OutputTag<>("extForeignMunicipalTrans") {
        };
        final OutputTag<ExdForeignParkTransaction> extForeignParkTag = new OutputTag<>("extForeignParkTrans") {
        };
        SingleOutputStreamOperator<ExdLocalTransaction> allTransStream = parkStream.process(new ProcessFunction<ParkTransWasteRec, ExdLocalTransaction>() {
                    @Override
                    public void processElement(ParkTransWasteRec rawTrans, ProcessFunction<ParkTransWasteRec, ExdLocalTransaction>.Context ctx, Collector<ExdLocalTransaction> collector) throws Exception {
                        if (!rawTrans.isPrimaryTrans()) {
                            ctx.output(exdChangeTag, ExtensionMapper.INSTANCE.exdRawToTollChangeTrans(rawTrans));
                        } else {
                            if (rawTrans.isLocal()) {
                                collector.collect(ExtensionMapper.INSTANCE.exdRawToExtLocalTrans(rawTrans));
                            } else if (rawTrans.isGasTrans()) {
                                ctx.output(extForeignGasTag, ExtensionMapper.INSTANCE.exdRawToExtForeignGasTrans(rawTrans));
                            } else if (rawTrans.isParkTrans()) {
                                ctx.output(extForeignParkTag, ExtensionMapper.INSTANCE.exdRawToExtForeignParkTrans(rawTrans));
                            } else if (rawTrans.isMunicipalTrans()) {
                                ctx.output(extForeignMunicipalTag, ExtensionMapper.INSTANCE.exdRawToExtForeignMunicipalTrans(rawTrans));
                            } else {
                                collector.collect(ExtensionMapper.INSTANCE.exdRawToExtLocalTrans(rawTrans));
                            }
                        }
                    }
                })
                .name("ExdTransProcess")
                .setParallelism(2);


        DataStream<TollChangeTransactions> exchangeStream = allTransStream.getSideOutput(exdChangeTag).map(new LinkCounter<>("extChangeCounter")).name("extChangeCounter");
        DataStream<ExdForeignGasTransaction> extForeignGasStream = allTransStream.getSideOutput(extForeignGasTag).map(new LinkCounter<>("extForeignGasCounter")).name("extForeignGasCounter");
        DataStream<ExdForeignParkTransaction> extForeignParkStream = allTransStream.getSideOutput(extForeignParkTag).map(new LinkCounter<>("extForeignParkCounter")).name("extForeignParkCounter");
        DataStream<ExdForeignMunicipalTransaction> extForeignMunicipalStream = allTransStream.getSideOutput(extForeignMunicipalTag).map(new LinkCounter<>("extForeignMunicipalCounter")).name("extForeignMunicipalCounter");
        DataStream<ExdLocalTransaction> extLocalTransStream = allTransStream.map(new LinkCounter<>("extLocalTransCounter")).name("extLocalTransCounter");

        SinkUtils.addInsertSinkToStream(exchangeStream, TollChangeTransactions.class, "exchangeStream");
        SinkUtils.addInsertSinkToStream(extForeignGasStream, ExdForeignGasTransaction.class, "extForeignGasStream");
        SinkUtils.addInsertSinkToStream(extForeignParkStream, ExdForeignParkTransaction.class, "extForeignParkStream");
        SinkUtils.addInsertSinkToStream(extForeignMunicipalStream, ExdForeignMunicipalTransaction.class, "extForeignMunicipalStream");
        SinkUtils.addInsertSinkToStream(extLocalTransStream, ExdLocalTransaction.class, "extLocalTransStream");

    }

    private static void processExitTrans(DataStream<ExitRawTransaction> exitStream) {
        final OutputTag<TollChangeTransactions> etcTollChange = new OutputTag<TollChangeTransactions>("etcTollChangeTrans") {
        };
        final OutputTag<TollChangeTransactions> otherTollChange = new OutputTag<TollChangeTransactions>("otherTollChangeTrans") {
        };
        final OutputTag<ExitForeignOtherTrans> foreignOther = new OutputTag<ExitForeignOtherTrans>("foreignOtherTrans") {
        };
        final OutputTag<ExitLocalOtherTrans> localOther = new OutputTag<ExitLocalOtherTrans>("localOtherTrans") {
        };
        final OutputTag<ExitForeignETCTrans> foreignETC = new OutputTag<ExitForeignETCTrans>("foreignETCTrans") {
        };

        SingleOutputStreamOperator<ExitLocalETCTrans> exitAllSream = exitStream.process(new ProcessFunction<ExitRawTransaction, ExitLocalETCTrans>() {
                    @Override
                    public void processElement(ExitRawTransaction value, ProcessFunction<ExitRawTransaction, ExitLocalETCTrans>.Context ctx, Collector<ExitLocalETCTrans> collector) throws Exception {
                        if (!value.peekPrimaryTrans()) {    // 非原始类交易
                            if (value.peekPayWithEtc()) {
                                ctx.output(etcTollChange, ExitMapper.INSTANCE.exitRawToTollChangeTrans(value));
                            } else {
                                ctx.output(otherTollChange, ExitMapper.INSTANCE.exitRawToTollChangeTrans(value));
                            }
                        } else {    // 原始类交易
                            if (!value.peekPayWithEtc()) {    // 非 ETC 支付
                                if (value.peekLocal()) {
                                    ctx.output(localOther, ExitMapper.INSTANCE.exitRawToExitLocalOther(value));
                                } else {
                                    ctx.output(foreignOther, ExitMapper.INSTANCE.exitRawToExitForeignOther(value));
                                }
                            } else {    // ETC 支付
                                if (!value.peekTruck() || !value.peekOBU() || !value.peekGreenCar()) { // 触发二次计算
                                    value = reCompute(value);
                                }
                                if (!value.peekLocal()) {
                                    ctx.output(foreignETC, ExitMapper.INSTANCE.exitRawToExitForeignETC(value));
                                } else {
                                    collector.collect(ExitMapper.INSTANCE.exitRawToExitLocalETC(value));
                                }
                            }
                        }
                    }
                })
                .name("ExitTransProcess")
                .setParallelism(2);
//                .map(new LinkCounter<>("processExitTrans"));


        DataStream<TollChangeTransactions> etcTollChangeTrans = exitAllSream.getSideOutput(etcTollChange).map(new LinkCounter<>("etcTollChangeTrans")).name("etcTollChangeTrans");
        DataStream<TollChangeTransactions> otherTollChangeTrans = exitAllSream.getSideOutput(otherTollChange).map(new LinkCounter<>("otherTollChangeTransCounter")).name("otherTollChangeTransCounter");
        DataStream<ExitLocalOtherTrans> localOtherTrans = exitAllSream.getSideOutput(localOther).map(new LinkCounter<>("localOtherTransCounter")).name("localOtherTransCounter");
        DataStream<ExitForeignOtherTrans> foreignOtherTrans = exitAllSream.getSideOutput(foreignOther).map(new LinkCounter<>("foreignOtherTransCounter")).name("foreignOtherTransCounter");
        DataStream<ExitForeignETCTrans> foreignETCTrans = exitAllSream.getSideOutput(foreignETC).map(new LinkCounter<>("foreignETCTransCounter")).name("foreignETCTransCounter");
        DataStream<ExitLocalETCTrans> localETCTrans = exitAllSream.map(new LinkCounter<>("localETCTransCounter")).name("localETCTransCounter");

        SinkUtils.addInsertSinkToStream(etcTollChangeTrans, TollChangeTransactions.class, "etcTollChangeTrans");
        SinkUtils.addInsertSinkToStream(otherTollChangeTrans, TollChangeTransactions.class, "otherTollChangeTrans");
        SinkUtils.addInsertSinkToStream(localOtherTrans, ExitLocalOtherTrans.class, "localOtherTrans");
        SinkUtils.addInsertSinkToStream(foreignOtherTrans, ExitForeignOtherTrans.class, "foreignOtherTrans");
        SinkUtils.addInsertSinkToStream(foreignETCTrans, ExitForeignETCTrans.class, "foreignETCTrans");
        SinkUtils.addInsertSinkToStream(localETCTrans, ExitLocalETCTrans.class, "localETCTrans");
    }


}
