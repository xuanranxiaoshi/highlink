package info.nemoworks.highlink.metric;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;

import info.nemoworks.highlink.model.HighwayTransaction;

public class LinkCounter<T extends HighwayTransaction> extends RichMapFunction<T, T> {
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
    public T map(T value) throws Exception {
        this.counter.inc();
        return value;
    }

    @Override
    public void close() throws Exception {
        super.close();
    }

}