package info.nemoworks.highlink;

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

import info.nemoworks.highlink.metric.LinkCounter;
import info.nemoworks.highlink.model.EntryTransaction;
import info.nemoworks.highlink.model.ExitTransaction;
import info.nemoworks.highlink.model.GantryTransaction;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.ParkTransaction;
import info.nemoworks.highlink.sink.TransactionSink;
import info.nemoworks.highlink.source.TransactionSource;

public class Main {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(
                JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),
                true);

        //读取json文件，模拟数据接收系统收到上传数据
        JsonNode enWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_ENWASTEREC.json"));
        JsonNode exWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_EXWASTEREC.json"));
        JsonNode gantryWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_GANTRYWASTEREC.json"));
        JsonNode parkWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_PARKTRANSWASTEREC.json"));

        //用json中的对象生成数据流（用while true循环模拟无限数据）
        DataStream<HighwayTransaction> enWaste = env
                .addSource(new TransactionSource(enWasteRec, "entry"))
                .name("ENTRY_WASTE");
        DataStream<HighwayTransaction> exWaste = env
                .addSource(new TransactionSource(exWasteRec, "exit"))
                .name("EXIT_WASTE");
        DataStream<HighwayTransaction> gantryWaste = env
                .addSource(new TransactionSource(gantryWasteRec, "gantry"))
                .name("GANTRY_WASTE");
        DataStream<HighwayTransaction> parkWaste = env
                .addSource(new TransactionSource(parkWasteRec, "park"))
                .name("PARK_WASTE");

        //将四个独立的流合并为一个，模拟接收系统向预处理系统传递数据
        DataStream<HighwayTransaction> unionStream = enWaste.union(exWaste).union(gantryWaste).union(parkWaste);

        //将数据流按规则进行拆分
        final OutputTag<ExitTransaction> exitTrans = new OutputTag<ExitTransaction>("exitTrans") {
        };
        final OutputTag<ParkTransaction> parkTrans = new OutputTag<ParkTransaction>("parkTrans") {
        };
        final OutputTag<GantryTransaction> gantryTrans = new OutputTag<GantryTransaction>("gantryTrans") {
        };

        SingleOutputStreamOperator<EntryTransaction> mainDataStream = unionStream
                .process(new ProcessFunction<HighwayTransaction, EntryTransaction>() {

                    @Override
                    public void processElement(HighwayTransaction value,
                            ProcessFunction<HighwayTransaction, EntryTransaction>.Context ctx,
                            Collector<EntryTransaction> out) throws Exception {

                        if (value instanceof ExitTransaction) {
                            ctx.output(exitTrans, (ExitTransaction) value);
                        } else {
                            if (value instanceof GantryTransaction) {
                                ctx.output(gantryTrans, (GantryTransaction) value);
                            } else {
                                if (value instanceof ParkTransaction) {
                                    ctx.output(parkTrans, (ParkTransaction) value);
                                } else {
                                    out.collect((EntryTransaction) value);
                                }
                            }
                        }
                    }

                });

        DataStream<GantryTransaction> gantryStream = mainDataStream.getSideOutput(gantryTrans);
        DataStream<ExitTransaction> exitStream = mainDataStream.getSideOutput(exitTrans);
        DataStream<ParkTransaction> parkStream = mainDataStream.getSideOutput(parkTrans);
        DataStream<EntryTransaction> entryStream = mainDataStream;

        //得到四个不同类型的数据流
        entryStream.map(new LinkCounter<EntryTransaction>("entry"))
                .addSink(new TransactionSink<EntryTransaction>(TransactionSink.ANSI_YELLOW));
        gantryStream.map(new LinkCounter<GantryTransaction>("gantry"))
                .addSink(new TransactionSink<GantryTransaction>(TransactionSink.ANSI_BLUE));
        exitStream.map(new LinkCounter<ExitTransaction>("exit"))
                .addSink(new TransactionSink<ExitTransaction>(TransactionSink.ANSI_RED));
        parkStream.map(new LinkCounter<ParkTransaction>("park"))
                .addSink(new TransactionSink<ParkTransaction>(TransactionSink.ANSI_GREEN));


        //配置flink集群，启动任务
        MiniClusterConfiguration clusterConfiguration = new MiniClusterConfiguration.Builder()
                .setNumTaskManagers(2)
                .setNumSlotsPerTaskManager(4).build();

        try (var cluster = new MiniCluster(clusterConfiguration)) {
            cluster.start();
            cluster.executeJobBlocking(env.getStreamGraph().getJobGraph());
            cluster.close();

        }
    }

}