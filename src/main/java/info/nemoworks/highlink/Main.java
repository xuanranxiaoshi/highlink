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
import info.nemoworks.highlink.sink.ObjectSink;
import info.nemoworks.highlink.source.TransactionSource;

public class Main {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(
                JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),
                true);

        JsonNode enWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_ENWASTEREC.json"));
        JsonNode exWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_EXWASTEREC.json"));
        JsonNode gantryWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_GANTRYWASTEREC.json"));
        JsonNode parkWasteRec = mapper.readTree(Main.class.getResourceAsStream("/TBL_PARKTRANSWASTEREC.json"));
        // Iterator<JsonNode> iterator = gantryWasteRec.iterator();
        // if (gantryWasteRec.isArray()) {
        // while (iterator.hasNext()) {
        // ObjectNode node = (ObjectNode) iterator.next();
        // node.set("ID", node.get("TRADEID"));
        // node.remove("TRADEID");
        // }
        // }

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

        parkWaste.map(new LinkCounter("park")).addSink(new ObjectSink("parksink"));

        DataStream<HighwayTransaction> unionStream = enWaste.union(exWaste).union(gantryWaste).union(parkWaste);

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

        mainDataStream.map(new LinkCounter("main")).addSink(new ObjectSink(ObjectSink.ANSI_YELLOW));

        gantryStream.map(new LinkCounter("gantry")).addSink(new ObjectSink(ObjectSink.ANSI_BLUE));
        exitStream.map(new LinkCounter("exit")).addSink(new ObjectSink(ObjectSink.ANSI_RED));
        parkStream.map(new LinkCounter("park")).addSink(new ObjectSink(ObjectSink.ANSI_GREEN));

        // env.execute("transaction processing");

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
