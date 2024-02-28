package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.connector.KafkaConnectorHelper;
import info.nemoworks.highlink.functions.*;
import info.nemoworks.highlink.metric.LinkCounter;
import info.nemoworks.highlink.model.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.*;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.PathTransaction;
import info.nemoworks.highlink.model.TollChangeTransactions;
import info.nemoworks.highlink.model.extendTransaction.*;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryEtcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.mapper.ExitMapper;
import info.nemoworks.highlink.model.mapper.ExtensionMapper;
import info.nemoworks.highlink.model.mapper.GantryMapper;
import info.nemoworks.highlink.sink.TransactionSinks;
import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
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
 * @description:
 * @author：jimi
 * @date: 2024/1/7
 * @Copyright：
 */
public class PrepareGantryFromKafka {

    public static void start(StreamExecutionEnvironment env) throws Exception {

        // 1. 从 Kafka 中读取数据
        DataStreamSource unionStream = env.fromSource(KafkaConnectorHelper.getKafkaHighWayTransSource("HighLink"),
                WatermarkStrategy.noWatermarks(),
                "HighLinkSource",
                TypeInformation.of(HighwayTransaction.class));

        // 2. 切分为不同的数据流
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


        // 3.1 门架数据预处理:
        // (1) 基本的处理逻辑
        processGantryTrans(rawGantryTrans);

        // (2) 门架数据归并
        DataStream<EntryRawTransaction> entryCopyStream = entryStream.broadcast();
        DataStream<GantryRawTransaction> gantryCopyStream = gantryStream.broadcast();
        DataStream<ExitRawTransaction> exitCopyStream = exitStream.broadcast();

        processGantryTrans(gantryCopyStream, entryCopyStream, exitCopyStream);


        // 3.2 拓展数据预处理
        processExdTrans(rawExdTrans);

        // 3.3 出口数据预处理
        processExitTrans(rawExitTrans);


        entryStream.addSink(new TransactionSinks.LogSink<>());

    }


    private static ExitRawTransaction reCompute(ExitRawTransaction value) {
        return value;
    }

    private static void processGantryTrans(DataStream<GantryRawTransaction> gantryStream, DataStream<EntryRawTransaction> entryCopyStream, DataStream<ExitRawTransaction> exitCopyStream) {

        // 0. 参数设置
        // 乱序等待 gap
        Duration OutOfOrderGap = Duration.ofHours(2);
        // 会话超时时间
        Time sessionGap = Time.hours(24);

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
                .<PathTransaction>forBoundedOutOfOrderness(OutOfOrderGap)
                .withTimestampAssigner(new SerializableTimestampAssigner<PathTransaction>() {
                    @Override
                    public long extractTimestamp(PathTransaction rawTransaction, long l) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = null;
                        try {
                            date = dateFormat.parse(rawTransaction.getTime());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        long timestamp = date.getTime();
                        // 返回的时间戳，毫秒
                        // System.out.println("数据= { id: " + rawTransaction.getPASSID() + ", enTime: " + rawTransaction.getENTIME() + " }");
                        return timestamp;
                    }
                })
                .withIdleness(Duration.ofSeconds(5));

        // 指定 watermark 策略，添加水位线
        SingleOutputStreamOperator<PathTransaction> pathTransWithWatermark = pathTransStream.assignTimestampsAndWatermarks(watermarkStrategy);


        // 3. 根据 passId 对数据流开窗
        SingleOutputStreamOperator<LinkedList<PathTransaction>> aggregateCpaStream = pathTransWithWatermark
                .keyBy(PathTransaction::getPASSID)
                .window(EventTimeSessionWindows.withGap(sessionGap))
                .trigger(new PathTrigger())
                .aggregate(new PathAggregateFunction(), new PathProcessWindowFunction())
                .setParallelism(12);

        aggregateCpaStream.addSink(new TransactionSinks.PathLogSink());
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
                        if (value.isEtc()) {    // 是：转化为门架ETC计费流水数据
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
        addSinkToStream(gantryCpcStream, GantryCpcTransaction.class, "gantryCpcStream");
        addSinkToStream(gantryEtcStream, GantryEtcTransaction.class, "gantryEtcStream");
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

        addSinkToStream(exchangeStream, TollChangeTransactions.class, "exchangeStream");
        addSinkToStream(extForeignGasStream, ExdForeignGasTransaction.class, "extForeignGasStream");
        addSinkToStream(extForeignParkStream, ExdForeignParkTransaction.class, "extForeignParkStream");
        addSinkToStream(extForeignMunicipalStream, ExdForeignMunicipalTransaction.class, "extForeignMunicipalStream");
        addSinkToStream(extLocalTransStream, ExdLocalTransaction.class, "extLocalTransStream");

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
                        if (!value.isPrimaryTrans()) {    // 非原始类交易
                            if (value.isPayWithEtc()) {
                                ctx.output(etcTollChange, ExitMapper.INSTANCE.exitRawToTollChangeTrans(value));
                            } else {
                                ctx.output(otherTollChange, ExitMapper.INSTANCE.exitRawToTollChangeTrans(value));
                            }
                        } else {    // 原始类交易
                            if (!value.isPayWithEtc()) {    // 非 ETC 支付
                                if (value.isLocal()) {
                                    ctx.output(localOther, ExitMapper.INSTANCE.exitRawToExitLocalOther(value));
                                } else {
                                    ctx.output(foreignOther, ExitMapper.INSTANCE.exitRawToExitForeignOther(value));
                                }
                            } else {    // ETC 支付
                                if (!value.isTruck() || !value.isEtc() || !value.isGreenCar()) { // 触发二次计算
                                    value = reCompute(value);
                                }
                                if (!value.isLocal()) {
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

        addSinkToStream(etcTollChangeTrans, TollChangeTransactions.class, "etcTollChangeTrans");
        addSinkToStream(otherTollChangeTrans, TollChangeTransactions.class, "otherTollChangeTrans");
        addSinkToStream(localOtherTrans, ExitLocalOtherTrans.class, "localOtherTrans");
        addSinkToStream(foreignOtherTrans, ExitForeignOtherTrans.class, "foreignOtherTrans");
        addSinkToStream(foreignETCTrans, ExitForeignETCTrans.class, "foreignETCTrans");
        addSinkToStream(localETCTrans, ExitLocalETCTrans.class, "localETCTrans");
    }



    private static void addLogSinkToStream(DataStream dataStream, Class clazz, String name){
        dataStream.addSink(new TransactionSinks.LogSink<>());
    }
    public static void addSinkToStream(DataStream dataStream, Class clazz) {
//        dataStream.addSink(new TransactionSinks.LogSink<>());
        dataStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(clazz),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));
    }

    public static void addSinkToStream(DataStream dataStream, Class clazz, String name) {
        dataStream.addSink(new TransactionSinks.LogSink<>()).name(name);
//        dataStream.addSink(JdbcSink.sink(
//                JdbcConnectorHelper.getInsertTemplateString(clazz),
//                JdbcConnectorHelper.getStatementBuilder(),
//                JdbcConnectorHelper.getJdbcExecutionOptions(),
//                JdbcConnectorHelper.getJdbcConnectionOptions())).name(name);
    }
}
