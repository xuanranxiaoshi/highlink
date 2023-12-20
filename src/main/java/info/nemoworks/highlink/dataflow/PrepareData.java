package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.*;
import info.nemoworks.highlink.model.extendTransaction.*;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryEtcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.mapper.ExtensionMapper;
import info.nemoworks.highlink.model.mapper.GantryMapper;
import info.nemoworks.highlink.sink.TransactionSinks;
import info.nemoworks.highlink.source.RawTransactionSource;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.runtime.minicluster.MiniCluster;
import org.apache.flink.runtime.minicluster.MiniClusterConfiguration;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.json.JsonReadFeature;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
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
//        processGantryTrans(gantryStream);

        // 2. 拓展数据预处理
        processExtTrans(parkStream);

        // 3. 出口数据预备处理

        // 得到四个不同类型的数据流
        entryStream.addSink(new TransactionSinks.LogSink<>());
        exitStream.addSink(new TransactionSinks.LogSink<>());
        parkStream.addSink(new TransactionSinks.LogSink<>());


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
        final OutputTag<ExtForeignGasTransaction> extForeignGasTag = new OutputTag<>("extForeignGasTrans") {
        };
        final OutputTag<ExtForeignMunicipalTransaction> extForeignMunicipalTag = new OutputTag<>("extForeignMunicipalTrans") {
        };
        final OutputTag<ExtForeignParkTransaction> extForeignParkTag = new OutputTag<>("extForeignParkTrans") {
        };
        SingleOutputStreamOperator<ExtLocalTransaction> allTransStream = parkStream.process(new ProcessFunction<ExtendRawTransaction, ExtLocalTransaction>() {
            @Override
            public void processElement(ExtendRawTransaction rawTrans, ProcessFunction<ExtendRawTransaction, ExtLocalTransaction>.Context ctx, Collector<ExtLocalTransaction> collector) throws Exception {
                if (!rawTrans.isPrimaryTrans()) {
                    ctx.output(exdChangeTag, ExtensionMapper.INSTANCE.extRawToTollChangeTrans(rawTrans));
                } else {
                    if (rawTrans.isLocal()) {
                        collector.collect(ExtensionMapper.INSTANCE.extRawToExtLocalTrans(rawTrans));
                    } else if (rawTrans.isGasTrans()) {
                        ctx.output(extForeignGasTag, ExtensionMapper.INSTANCE.extRawToExtForeignGasTrans(rawTrans));
                    } else if (rawTrans.isParkTrans()) {
                        ctx.output(extForeignParkTag, ExtensionMapper.INSTANCE.extRawToExtForeignParkTrans(rawTrans));
                    } else if (rawTrans.isMunicipalTrans()) {
                        ctx.output(extForeignMunicipalTag, ExtensionMapper.INSTANCE.extRawToExtForeignMunicipalTrans(rawTrans));
                    } else {
                        collector.collect(ExtensionMapper.INSTANCE.extRawToExtLocalTrans(rawTrans));
                    }
                }
            }
        });

        DataStream<TollChangeTransactions> exchangeStream = allTransStream.getSideOutput(exdChangeTag);
        DataStream<ExtForeignGasTransaction> extForeignGasStream = allTransStream.getSideOutput(extForeignGasTag);
        DataStream<ExtForeignParkTransaction> extForeignParkStream = allTransStream.getSideOutput(extForeignParkTag);
        DataStream<ExtForeignMunicipalTransaction> extForeignMunicipalStream = allTransStream.getSideOutput(extForeignMunicipalTag);
        DataStream<ExtLocalTransaction> extLocalTransStream = allTransStream;

        exchangeStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(TollChangeTransactions.class),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));
        extForeignGasStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(ExtForeignGasTransaction.class),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));
        extForeignParkStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(ExtForeignParkTransaction.class),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));
        extForeignMunicipalStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(ExtForeignMunicipalTransaction.class),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));
        extLocalTransStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(ExtLocalTransaction.class),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));
    }
}
