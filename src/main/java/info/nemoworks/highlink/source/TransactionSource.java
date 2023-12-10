package info.nemoworks.highlink.source;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.slf4j.LoggerFactory;


import info.nemoworks.highlink.sink.ObjectSink;

public class TransactionSource implements SourceFunction<ObjectNode> {

    private static final long serialVersionUID = 1L;

    private final ArrayNode transactions;

    private volatile boolean isRunning = true;
    Random random = new Random();

    private int count;

    private String name;

    public TransactionSource(JsonNode transactions, String name) throws Exception {
        if (!transactions.isArray())
            throw new Exception();
        this.transactions = (ArrayNode) transactions;
        this.name = name;
        this.count = random.nextInt(100);
        // LoggerFactory.getLogger(TransactionSource.class)
                // .info(ObjectSink.ANSI_CYAN + "Source " + this.name + " has " + this.count + ObjectSink.ANSI_RESET);
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    @Override
    public void run(SourceContext<ObjectNode> ctx) throws Exception {
        while (isRunning && this.count > 0) {
            TimeUnit.SECONDS.sleep(random.nextInt(2));
            ctx.collect((ObjectNode) transactions.get(random.nextInt(transactions.size() - 1)));
            this.count--;
        }
    }

}
