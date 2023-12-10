package info.nemoworks.highlink.metric;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;

import info.nemoworks.highlink.model.Transaction;


public class LinkCounter extends RichMapFunction<Transaction, Transaction> {
    private transient Counter counter;

    private String name;

    public LinkCounter(String name) {
        this.name = name;
    }

    @Override
    public void open(Configuration config) {
        this.counter = getRuntimeContext()
                .getMetricGroup()
                .counter(name);
    }

    @Override
    public Transaction map(Transaction value) throws Exception {
        this.counter.inc();
        // LoggerFactory.getLogger(LinkCounter.class)
        // .info(ObjectSink.ANSI_GREEN + this.name + ": " + this.counter.getCount() +
        // ObjectSink.ANSI_RESET);
        return value;
    }

    @Override
    public void close() throws Exception {
        super.close();
    }

}