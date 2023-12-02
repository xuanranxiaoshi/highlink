package info.nemoworks.highlink.source;

import java.util.Random;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class TransactionSource implements SourceFunction<JsonNode> {

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
    public void run(SourceContext<JsonNode> ctx) throws Exception {

        while (isRunning) {
            ctx.collect(transactions.get(random.nextInt(transactions.size() - 1)));
        }
    }

}
