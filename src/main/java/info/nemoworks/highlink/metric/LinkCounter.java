package info.nemoworks.highlink.metric;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.ObjectNode;

import info.nemoworks.highlink.sink.ObjectSink;
import info.nemoworks.highlink.source.TransactionSource;

public class LinkCounter extends RichMapFunction<ObjectNode, ObjectNode> {
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
    public ObjectNode map(ObjectNode value) throws Exception {
        this.counter.inc();
        LoggerFactory.getLogger(LinkCounter.class)
                .info(ObjectSink.ANSI_GREEN + this.name + ": " + this.counter.getCount() + ObjectSink.ANSI_RESET);
        return value;
    }

    @Override
    public void close() throws Exception {
        super.close();
    }

}