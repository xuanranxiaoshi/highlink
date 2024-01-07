package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.connector.KafkaConnectorHelper;
import info.nemoworks.highlink.metric.LinkCounter;
import info.nemoworks.highlink.model.EntryRawTransaction;
import info.nemoworks.highlink.model.ExitTransaction.*;
import info.nemoworks.highlink.model.TollChangeTransactions;
import info.nemoworks.highlink.model.extendTransaction.*;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryEtcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.mapper.ExitMapper;
import info.nemoworks.highlink.model.mapper.ExtensionMapper;
import info.nemoworks.highlink.model.mapper.GantryMapper;
import info.nemoworks.highlink.sink.TransactionSinks;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/7
 * @Copyright：
 */
public class PrepareDataFromKafka {

    public static void start(StreamExecutionEnvironment env) throws Exception {

        // 1. 从不同 Kafka topic 中读取数据
        DataStreamSource<EntryRawTransaction> entryStream = env.fromSource(KafkaConnectorHelper.<EntryRawTransaction>getKafkaSource("ENTRY_WASTE", EntryRawTransaction.class),
                WatermarkStrategy.noWatermarks(),
                "ENTRY_WASTE",
                TypeInformation.of(EntryRawTransaction.class));
        DataStreamSource<ExitRawTransaction> exitStream = env.fromSource(KafkaConnectorHelper.<ExitRawTransaction>getKafkaSource("EXIT_WASTE", ExitRawTransaction.class),
                WatermarkStrategy.noWatermarks(),
                "EXIT_WASTE",
                TypeInformation.of(ExitRawTransaction.class));
        DataStreamSource<GantryRawTransaction> gantryStream = env.fromSource(KafkaConnectorHelper.<GantryRawTransaction>getKafkaSource("GANTRY_WASTE", GantryRawTransaction.class),
                WatermarkStrategy.noWatermarks(),
                "GANTRY_WASTE",
                TypeInformation.of(GantryRawTransaction.class));
        DataStreamSource<ExtendRawTransaction> extendStream = env.fromSource(KafkaConnectorHelper.<ExtendRawTransaction>getKafkaSource("EXTEND_WASTE", ExtendRawTransaction.class),
                WatermarkStrategy.noWatermarks(),
                "EXTEND_WASTE",
                TypeInformation.of(ExtendRawTransaction.class));

        // 3.1 门架数据预处理
        processGantryTrans(gantryStream);

        // 3.2 拓展数据预处理
        processExdTrans(extendStream);

        // 3.3 出口数据预处理
        processExitTrans(exitStream);

        entryStream.addSink(new TransactionSinks.LogSink<>());

    }

    private static ExitRawTransaction reCompute(ExitRawTransaction value) {
        return value;
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
                }).returns(GantryEtcTransaction.class);
        // 2. 通过判断逻辑拆分数据流
        SingleOutputStreamOperator<GantryCpcTransaction> gantryCpcStream = gantryAllStream.getSideOutput(ganCpcTag).map(new LinkCounter<>("gantryCpcCounter"));
        SingleOutputStreamOperator<GantryEtcTransaction> gantryEtcStream = gantryAllStream.map(new LinkCounter<>("gantryEtcCounter"));

        // 3. 分别对两类数据进行记录
        addSinkToStream(gantryCpcStream, GantryCpcTransaction.class);
        addSinkToStream(gantryEtcStream, GantryEtcTransaction.class);
    }

    private static void processExdTrans(DataStream<ExtendRawTransaction> parkStream) {
        final OutputTag<TollChangeTransactions> exdChangeTag = new OutputTag<>("extChangeTrans") {
        };
        final OutputTag<ExdForeignGasTransaction> extForeignGasTag = new OutputTag<>("extForeignGasTrans") {
        };
        final OutputTag<ExdForeignMunicipalTransaction> extForeignMunicipalTag = new OutputTag<>("extForeignMunicipalTrans") {
        };
        final OutputTag<ExdForeignParkTransaction> extForeignParkTag = new OutputTag<>("extForeignParkTrans") {
        };
        SingleOutputStreamOperator<ExdLocalTransaction> allTransStream = parkStream.process(new ProcessFunction<ExtendRawTransaction, ExdLocalTransaction>() {
            @Override
            public void processElement(ExtendRawTransaction rawTrans, ProcessFunction<ExtendRawTransaction, ExdLocalTransaction>.Context ctx, Collector<ExdLocalTransaction> collector) throws Exception {
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
        }).returns(ExdLocalTransaction.class);

        DataStream<TollChangeTransactions> exchangeStream = allTransStream.getSideOutput(exdChangeTag).map(new LinkCounter<>("extChangeCounter"));
        DataStream<ExdForeignGasTransaction> extForeignGasStream = allTransStream.getSideOutput(extForeignGasTag).map(new LinkCounter<>("extForeignGasCounter"));
        DataStream<ExdForeignParkTransaction> extForeignParkStream = allTransStream.getSideOutput(extForeignParkTag).map(new LinkCounter<>("extForeignParkCounter"));
        DataStream<ExdForeignMunicipalTransaction> extForeignMunicipalStream = allTransStream.getSideOutput(extForeignMunicipalTag).map(new LinkCounter<>("extForeignMunicipalCounter"));
        DataStream<ExdLocalTransaction> extLocalTransStream = allTransStream.map(new LinkCounter<>("extLocalTransCounter"));

        addSinkToStream(exchangeStream, TollChangeTransactions.class);
        addSinkToStream(extForeignGasStream, ExdForeignGasTransaction.class);
        addSinkToStream(extForeignParkStream, ExdForeignParkTransaction.class);
        addSinkToStream(extForeignMunicipalStream, ExdForeignMunicipalTransaction.class);
        addSinkToStream(extLocalTransStream, ExdLocalTransaction.class);

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
        }).returns(ExitLocalETCTrans.class);


        DataStream<TollChangeTransactions> etcTollChangeTrans = exitAllSream.getSideOutput(etcTollChange).map(new LinkCounter<>("etcTollChangeTrans"));
        DataStream<TollChangeTransactions> otherTollChangeTrans = exitAllSream.getSideOutput(otherTollChange).map(new LinkCounter<>("otherTollChangeTransCounter"));
        DataStream<ExitLocalOtherTrans> localOtherTrans = exitAllSream.getSideOutput(localOther).map(new LinkCounter<>("localOtherTransCounter"));
        DataStream<ExitForeignOtherTrans> foreignOtherTrans = exitAllSream.getSideOutput(foreignOther).map(new LinkCounter<>("foreignOtherTransCounter"));
        DataStream<ExitForeignETCTrans> foreignETCTrans = exitAllSream.getSideOutput(foreignETC).map(new LinkCounter<>("foreignETCTransCounter"));
        DataStream<ExitLocalETCTrans> localETCTrans = exitAllSream.map(new LinkCounter<>("localETCTransCounter"));

        addSinkToStream(etcTollChangeTrans, TollChangeTransactions.class);
        addSinkToStream(otherTollChangeTrans, TollChangeTransactions.class);
        addSinkToStream(localOtherTrans, ExitLocalOtherTrans.class);
        addSinkToStream(foreignOtherTrans, ExitForeignOtherTrans.class);
        addSinkToStream(foreignETCTrans, ExitForeignETCTrans.class);
        addSinkToStream(localETCTrans, ExitLocalETCTrans.class);
    }

    public static void addSinkToStream(DataStream dataStream, Class clazz) {
        dataStream.addSink(new TransactionSinks.LogSink<>());
//        dataStream.addSink(JdbcSink.sink(
//                JdbcConnectorHelper.getInsertTemplateString(clazz),
//                JdbcConnectorHelper.getStatementBuilder(),
//                JdbcConnectorHelper.getJdbcExecutionOptions(),
//                JdbcConnectorHelper.getJdbcConnectionOptions()));
    }
}
