package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.*;
import info.nemoworks.highlink.model.ExitTransaction.*;
import info.nemoworks.highlink.model.extendTransaction.*;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryEtcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.mapper.ExitMapper;
import info.nemoworks.highlink.model.mapper.ExtensionMapper;
import info.nemoworks.highlink.model.mapper.GantryMapper;
import info.nemoworks.highlink.sink.TransactionSinks;
import info.nemoworks.highlink.source.RawTransactionSource;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.runtime.minicluster.MiniClusterConfiguration;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.json.JsonReadFeature;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * @description:
 * @author：jimi
 * @date: 2023/12/20
 * @Copyright：
 */
public class PrepareData {
    public static void main(String[] args) throws Exception {

        JdbcConnectorHelper.getCreateTableString(GantryEtcTransaction.class);
        JdbcConnectorHelper.getCreateTableString(GantryCpcTransaction.class);

        //StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

        // 读取json文件，模拟数据接收系统收到上传数据
        JsonNode enWasteRec = mapper.readTree(PrepareData.class.getResourceAsStream("/TBL_ENWASTEREC.json"));
        JsonNode exWasteRec = mapper.readTree(PrepareData.class.getResourceAsStream("/TBL_EXWASTEREC.json"));
        JsonNode gantryWasteRec = mapper.readTree(PrepareData.class.getResourceAsStream("/TBL_GANTRYWASTEREC.json"));
        JsonNode parkWasteRec = mapper.readTree(PrepareData.class.getResourceAsStream("/TBL_PARKTRANSWASTEREC.json"));

        // 用json中的对象生成数据流（用while true循环模拟无限数据）
        DataStream<HighwayTransaction> enWaste = env
                .addSource(new RawTransactionSource(enWasteRec, "entry"))
                .name("ENTRY_WASTE");
        DataStream<HighwayTransaction> exWaste = env
                .addSource(new RawTransactionSource(exWasteRec, "exit"))
                .name("EXIT_WASTE");
        DataStream<HighwayTransaction> gantryWaste = env
                .addSource(new RawTransactionSource(gantryWasteRec, "gantry"))
                .name("GANTRY_WASTE");
        DataStream<HighwayTransaction> parkWaste = env
                .addSource(new RawTransactionSource(parkWasteRec, "park"))
                .name("PARK_WASTE");

        // 用json中的对象生成数据流（用while true循环模拟无限数据）
        DataStream<HighwayTransaction> unionStream = enWaste.union(exWaste).union(gantryWaste).union(parkWaste);

        // 将数据流按规则进行拆分
        final OutputTag<ExitRawTransaction> exitTrans = new OutputTag<ExitRawTransaction>("exitTrans") {
        };
        final OutputTag<ExtendRawTransaction> parkTrans = new OutputTag<ExtendRawTransaction>("parkTrans") {
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
                                if (value instanceof ExtendRawTransaction) {
                                    ctx.output(parkTrans,
                                            (ExtendRawTransaction) value);
                                } else {
                                    out.collect((EntryRawTransaction) value);
                                }
                            }
                        }
                    }
                });

        DataStream<GantryRawTransaction> gantryStream = mainDataStream.getSideOutput(gantryTrans);
        DataStream<ExitRawTransaction> exitStream = mainDataStream.getSideOutput(exitTrans);
        DataStream<ExtendRawTransaction> parkStream = mainDataStream.getSideOutput(parkTrans);
        DataStream<EntryRawTransaction> entryStream = mainDataStream;

        // 1. 门架数据预处理
        processGantryTrans(gantryStream);

        // 2. 拓展数据预处理
        processExtTrans(parkStream);

        // 3. 出口数据预备处理
        processExitTrans(exitStream);


        // 得到四个不同类型的数据流
        entryStream.addSink(new TransactionSinks.LogSink<>());
//        exitStream.addSink(new TransactionSinks.LogSink<>());
//        parkStream.addSink(new TransactionSinks.LogSink<>());


        // 配置flink集群，启动任务
        MiniClusterConfiguration clusterConfiguration = new MiniClusterConfiguration.Builder()
                .setNumTaskManagers(1)
                .setNumSlotsPerTaskManager(2).build();


//        try (var cluster = new MiniCluster(clusterConfiguration)) {
//            cluster.start();
//            cluster.executeJobBlocking(env.getStreamGraph().getJobGraph());
//            cluster.close();
//        }
        env.execute();
    }

    private static ExitRawTransaction reCompute(ExitRawTransaction value) {
        return value;
    }


    private static void processGantryTrans(DataStream<GantryRawTransaction> gantryStream) {
        final OutputTag<GantryCpcTransaction> ganCpcTag = new OutputTag<GantryCpcTransaction>("gantryCpcTrans") {
        };

        SingleOutputStreamOperator<GantryEtcTransaction> gantryAllStream = gantryStream
                .process(new ProcessFunction<GantryRawTransaction, GantryEtcTransaction>() {

                    @Override
                    public void processElement(GantryRawTransaction value,
                                               ProcessFunction<GantryRawTransaction, GantryEtcTransaction>.Context ctx,
                                               Collector<GantryEtcTransaction> out) throws Exception {
                        if (value.isEtc()) {
                            ctx.output(ganCpcTag, GantryMapper.INSTANCE
                                    .gantryRawToCpcTransaction(value));
                        } else {
                            out.collect(GantryMapper.INSTANCE
                                    .gantryRawToEtcTransaction(value));
                        }
                    }
                });

        DataStream<GantryCpcTransaction> gantryCpcStream = gantryAllStream.getSideOutput(ganCpcTag);
        DataStream<GantryEtcTransaction> gantryEtcStream = gantryAllStream;

        gantryCpcStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(GantryCpcTransaction.class),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));

        gantryEtcStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(GantryEtcTransaction.class),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));
    }

    private static void processExtTrans(DataStream<ExtendRawTransaction> parkStream) {
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
        });

        DataStream<TollChangeTransactions> exchangeStream = allTransStream.getSideOutput(exdChangeTag);
        DataStream<ExdForeignGasTransaction> extForeignGasStream = allTransStream.getSideOutput(extForeignGasTag);
        DataStream<ExdForeignParkTransaction> extForeignParkStream = allTransStream.getSideOutput(extForeignParkTag);
        DataStream<ExdForeignMunicipalTransaction> extForeignMunicipalStream = allTransStream.getSideOutput(extForeignMunicipalTag);
        DataStream<ExdLocalTransaction> extLocalTransStream = allTransStream;

        addSinkToStream(exchangeStream, TollChangeTransactions.class);
        addSinkToStream(extForeignGasStream, ExdForeignGasTransaction.class);
        addSinkToStream(extForeignParkStream, ExdForeignParkTransaction.class);
        addSinkToStream(extForeignMunicipalStream, ExdForeignMunicipalTransaction.class);
        addSinkToStream(extLocalTransStream, ExdLocalTransaction.class);

//        exchangeStream.addSink(JdbcSink.sink(
//                JdbcConnectorHelper.getInsertTemplateString(TollChangeTransactions.class),
//                JdbcConnectorHelper.getStatementBuilder(),
//                JdbcConnectorHelper.getJdbcExecutionOptions(),
//                JdbcConnectorHelper.getJdbcConnectionOptions()));
//        extForeignGasStream.addSink(JdbcSink.sink(
//                JdbcConnectorHelper.getInsertTemplateString(ExtForeignGasTransaction.class),
//                JdbcConnectorHelper.getStatementBuilder(),
//                JdbcConnectorHelper.getJdbcExecutionOptions(),
//                JdbcConnectorHelper.getJdbcConnectionOptions()));
//        extForeignParkStream.addSink(JdbcSink.sink(
//                JdbcConnectorHelper.getInsertTemplateString(ExtForeignParkTransaction.class),
//                JdbcConnectorHelper.getStatementBuilder(),
//                JdbcConnectorHelper.getJdbcExecutionOptions(),
//                JdbcConnectorHelper.getJdbcConnectionOptions()));
//        extForeignMunicipalStream.addSink(JdbcSink.sink(
//                JdbcConnectorHelper.getInsertTemplateString(ExtForeignMunicipalTransaction.class),
//                JdbcConnectorHelper.getStatementBuilder(),
//                JdbcConnectorHelper.getJdbcExecutionOptions(),
//                JdbcConnectorHelper.getJdbcConnectionOptions()));
//        extLocalTransStream.addSink(JdbcSink.sink(
//                JdbcConnectorHelper.getInsertTemplateString(ExtLocalTransaction.class),
//                JdbcConnectorHelper.getStatementBuilder(),
//                JdbcConnectorHelper.getJdbcExecutionOptions(),
//                JdbcConnectorHelper.getJdbcConnectionOptions()));
    }

    private static void processExitTrans(DataStream<ExitRawTransaction> exitStream){
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
                        if (!value.isTruck() || !value.isEtc() || !value.isGreenCar()){ // 触发二次计算
                            value = reCompute(value);
                        }
                        if(!value.isLocal()){
                            ctx.output(foreignETC, ExitMapper.INSTANCE.exitRawToExitForeignETC(value));
                        }else{
                            collector.collect(ExitMapper.INSTANCE.exitRawToExitLocalETC(value));
                        }
                    }
                }
            }
        });


        DataStream<TollChangeTransactions> etcTollChangeTrans = exitAllSream.getSideOutput(etcTollChange);
        DataStream<TollChangeTransactions> otherTollChangeTrans = exitAllSream.getSideOutput(otherTollChange);
        DataStream<ExitLocalOtherTrans> localOtherTrans = exitAllSream.getSideOutput(localOther);
        DataStream<ExitForeignOtherTrans> foreignOtherTrans = exitAllSream.getSideOutput(foreignOther);
        DataStream<ExitForeignETCTrans> foreignETCTrans = exitAllSream.getSideOutput(foreignETC);
        DataStream<ExitLocalETCTrans> localETCTrans = exitAllSream;

        addSinkToStream(etcTollChangeTrans, TollChangeTransactions.class);
        addSinkToStream(otherTollChangeTrans, TollChangeTransactions.class);
        addSinkToStream(localOtherTrans, ExitLocalOtherTrans.class);
        addSinkToStream(foreignOtherTrans, ExitForeignOtherTrans.class);
        addSinkToStream(foreignETCTrans, ExitForeignETCTrans.class);
        addSinkToStream(localETCTrans, ExitLocalETCTrans.class);
    }

    public static void addSinkToStream(DataStream dataStream, Class clazz) {
        dataStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(clazz),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));
    }
}
