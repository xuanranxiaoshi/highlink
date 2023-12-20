package info.nemoworks.highlink;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.runtime.minicluster.MiniCluster;
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

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.EntryRawTransaction;
import info.nemoworks.highlink.model.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryEtcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExtendRawTransaction;
import info.nemoworks.highlink.model.mapper.GantryMapper;
import info.nemoworks.highlink.sink.TransactionSinks;
import info.nemoworks.highlink.source.RawTransactionSource;

public class Main {

    public static void main(String[] args) throws Exception {

        JdbcConnectorHelper.getCreateTableString(GantryEtcTransaction.class);
        JdbcConnectorHelper.getCreateTableString(GantryCpcTransaction.class);

//      StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(
                JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),
                true);

        // 读取json文件，模拟数据接收系统收到上传数据
        JsonNode enWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_ENWASTEREC.json"));
        JsonNode exWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_EXWASTEREC.json"));
        JsonNode gantryWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_GANTRYWASTEREC.json"));
        JsonNode parkWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_PARKTRANSWASTEREC.json"));

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

        // 将四个独立的流合并为一个，模拟接收系统向预处理系统传递数据
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

        // 将门架流水再进行拆分：etc/cpc
        final OutputTag<GantryCpcTransaction> ganCpcTag = new OutputTag<GantryCpcTransaction>(
                "gantryCpcTrans") {
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

        // 得到四个不同类型的数据流
        entryStream.addSink(new TransactionSinks.LogSink<>());
        exitStream.addSink(new TransactionSinks.LogSink<>());
        parkStream.addSink(new TransactionSinks.LogSink<>());

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
}
