package info.nemoworks.highlink.source;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TransactionSource implements SourceFunction<ObjectNode> {

    private static final long serialVersionUID = 1L;

    private final ArrayNode transactions;

    private volatile boolean isRunning = true;
    Random random = new Random();

    public TransactionSource(JsonNode transactions) throws Exception {
        if (!transactions.isArray())
            throw new Exception();
        this.transactions = (ArrayNode) transactions;
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    @Override
    public void run(SourceContext<ObjectNode> ctx) throws Exception {

        while (isRunning) {
            TimeUnit.SECONDS.sleep(random.nextInt(2));
            ctx.collect((ObjectNode) transactions.get(random.nextInt(transactions.size() - 1)));
        }
    }

}
