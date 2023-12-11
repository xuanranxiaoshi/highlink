package info.nemoworks.highlink;

import java.util.Iterator;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.json.JsonReadFeature;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.OutputTag;

import info.nemoworks.highlink.metric.LinkCounter;
import info.nemoworks.highlink.model.GantryTransaction;
import info.nemoworks.highlink.sink.ObjectSink;
import info.nemoworks.highlink.source.TransactionSource;

public class TransactionJob {

        public static void main(String[] args) throws Exception {
                // StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


                // ObjectMapper mapper = new ObjectMapper();
                // mapper.configure(
                //                 JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),
                //                 true);

                // JsonNode enWasteRec = mapper.readTree(TransactionJob.class.getResourceAsStream("/TBL_ENWASTEREC.json"));
                // JsonNode exWasteRec = mapper.readTree(TransactionJob.class.getResourceAsStream("/TBL_EXWASTEREC.json"));
                // JsonNode gantryWasteRec = mapper
                //                 .readTree(TransactionJob.class.getResourceAsStream("/TBL_GANTRYWASTEREC.json"));

                // Iterator<JsonNode> iterator = gantryWasteRec.iterator();
                // if (gantryWasteRec.isArray()) {
                //         while (iterator.hasNext()) {
                //                 ObjectNode node = (ObjectNode) iterator.next();
                //                 node.set("ID", node.get("TRADEID"));
                //                 node.remove("TRADEID");
                //         }
                // }

                // JsonNode parkWasteRec = mapper
                //                 .readTree(TransactionJob.class.getResourceAsStream("/TBL_PARKTRANSWASTEREC.json"));

                // DataStream<ObjectNode> enWaste = env
                //                 .addSource(new TransactionSource(enWasteRec, "entry"))
                //                 .name("ENTRY_WASTE");

                // DataStream<ObjectNode> exWaste = env
                //                 .addSource(new TransactionSource(exWasteRec, "exit"))
                //                 .name("EXIT_WASTE");

                // DataStream<ObjectNode> gantryWaste = env
                //                 .addSource(new TransactionSource(gantryWasteRec, "gantry"))
                //                 .name("GANTRY_WASTE");

                // DataStream<ObjectNode> parkWaste = env
                //                 .addSource(new TransactionSource(parkWasteRec, "park"))
                //                 .name("PARK_WASTE");

                // parkWaste.map(new LinkCounter("park")).addSink(new ObjectSink("parksink"));

                // DataStream<ObjectNode> unionStream = enWaste.union(exWaste).union(gantryWaste).union(parkWaste);

                // final OutputTag<ObjectNode> exitTrans = new OutputTag<ObjectNode>("exitTrans") {
                // };
                // final OutputTag<ObjectNode> parkTrans = new OutputTag<ObjectNode>("parkTrans") {
                // };
                // final OutputTag<ObjectNode> gantryTrans = new OutputTag<ObjectNode>("gantryTrans") {
                // };

                // SingleOutputStreamOperator<ObjectNode> mainDataStream = unionStream
                //                 .process(new ProcessFunction<ObjectNode, ObjectNode>() {

                //                         @Override
                //                         public void processElement(ObjectNode value,
                //                                         ProcessFunction<ObjectNode, ObjectNode>.Context ctx,
                //                                         org.apache.flink.util.Collector<ObjectNode> out)
                //                                         throws Exception {

                //                                 // emit data to regular output
                //                                 out.collect(value);

                //                                 if (value.get("EXTOLLSTATION") != null) {
                //                                         ctx.output(exitTrans, value);
                //                                 } else {
                //                                         if (value.get("GANTRYID") != null) {
                //                                                 ctx.output(gantryTrans, value);
                //                                         } else {
                //                                                 if (value.get("PARKOPERATORID") != null) {
                //                                                         ctx.output(parkTrans, value);
                //                                                 }
                //                                         }
                //                                 }
                //                         }
                //                 });

                // DataStream<GantryTransaction> gantryStream = mainDataStream.getSideOutput(gantryTrans);
                // DataStream<ObjectNode> exitStream = mainDataStream.getSideOutput(exitTrans);
                // DataStream<ObjectNode> parkStream = mainDataStream.getSideOutput(parkTrans);

                // // mainDataStream.addSink(new ObjectSink(ObjectSink.ANSI_YELLOW));

                // gantryStream.map(GantryTransaction::fromJson).addSink(new ObjectSink(ObjectSink.ANSI_BLUE));
                // // exitStream.map(new LinkCounter("exit")).addSink(new ObjectSink(ObjectSink.ANSI_RED));
                // // parkStream.map(new LinkCounter("park")).addSink(new ObjectSink(ObjectSink.ANSI_GREEN));

                // env.execute("transaction processing");
        }

        
}
