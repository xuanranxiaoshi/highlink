package info.nemoworks.highlink.kafka;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.json.JsonReadFeature;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/7
 * @Copyright：
 */


public class JsonDeSerializer<T> implements DeserializationSchema<T> {
    private Class<T> tClass;
    private final String encoding = "UTF8";
    private final ObjectMapper objectMapper;

    public JsonDeSerializer(Class<T> tClass) {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        this.tClass=tClass;
    }

    @Override
    public T deserialize(byte[] bytes) throws IOException {
        if(bytes!=null){
            return objectMapper.readValue(new String(bytes), this.tClass);
        }
        return null;
    }

    /**
     * Method to decide whether the element signals the end of the stream. If true is returned the
     * element won't be emitted.
     *
     * @param Element The element to test for the end-of-stream signal.
     * @return True, if the element signals end of stream, false otherwise.
     */
    @Override
    public boolean isEndOfStream(T Element) {
        return false;
    }

    /**
     * Gets the data type (as a {@link TypeInformation}) produced by this function or input format.
     *
     * @return The data type produced by this function or input format.
     */
    @Override
    public TypeInformation<T> getProducedType() {
        return TypeInformation.of(this.tClass);
    }
}
