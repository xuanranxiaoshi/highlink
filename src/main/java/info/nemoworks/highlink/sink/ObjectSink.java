package info.nemoworks.highlink.sink;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectSink implements SinkFunction<ObjectNode> {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ObjectSink.class);

    @Override
    public void invoke(ObjectNode value, Context context) {
        LOG.info(value.toString());
    }
}
