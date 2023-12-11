package info.nemoworks.highlink.sink;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.nemoworks.highlink.model.HighwayTransaction;

public class TransactionSink<T extends HighwayTransaction> implements SinkFunction<T> {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(TransactionSink.class);

    private String color;

    public TransactionSink(String color) {
        this.color = color;
    }

    @Override
    public void invoke(T value, Context context) {

        LOG.info(this.color + value.getID() + ANSI_RESET);
    }
}
