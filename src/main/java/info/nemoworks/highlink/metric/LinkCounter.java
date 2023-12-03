package info.nemoworks.highlink.metric;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;

import com.fasterxml.jackson.databind.node.ObjectNode;

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
        System.out.println("Counter " + name + "=" + this.counter.getCount());
        return value;
    }
}