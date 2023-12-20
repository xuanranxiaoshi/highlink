package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.Main;
import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.*;
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
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.io.IOException;

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

        // read json file
        JsonNode enWasteRec = mapper.readTree(PrepareData.class.getResourceAsStream("/TBL_ENWASTEREC.json"));
        JsonNode exWasteRec = mapper.readTree(PrepareData.class.getResourceAsStream("/TBL_EXWASTEREC.json"));
        JsonNode gantryWasteRec = mapper.readTree(PrepareData.class.getResourceAsStream("/TBL_GANTRYWASTEREC.json"));
        JsonNode parkWasteRec = mapper.readTree(PrepareData.class.getResourceAsStream("/TBL_PARKTRANSWASTEREC.json"));


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


        DataStream<HighwayTransaction> unionStream = enWaste.union(exWaste).union(gantryWaste).union(parkWaste);


        final OutputTag<ExitRawTransaction> exitTrans = new OutputTag<ExitRawTransaction>("exitTrans") {
        };
        final OutputTag<ParkRawTransaction> parkTrans = new OutputTag<ParkRawTransaction>("parkTrans") {
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
                                if (value instanceof ParkRawTransaction) {
                                    ctx.output(parkTrans,
                                            (ParkRawTransaction) value);
                                } else {
                                    out.collect((EntryRawTransaction) value);
                                }
                            }
                        }
                    }
                });

        DataStream<GantryRawTransaction> gantryStream = mainDataStream.getSideOutput(gantryTrans);
        DataStream<ExitRawTransaction> exitStream = mainDataStream.getSideOutput(exitTrans);
        DataStream<ParkRawTransaction> parkStream = mainDataStream.getSideOutput(parkTrans);
        DataStream<EntryRawTransaction> entryStream = mainDataStream;

        // Gantry data preprocessing
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

        // Park data preprocessing




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


        try (var cluster = new MiniCluster(clusterConfiguration)) {
            cluster.start();
            cluster.executeJobBlocking(env.getStreamGraph().getJobGraph());
            cluster.close();
        }
    }
}
