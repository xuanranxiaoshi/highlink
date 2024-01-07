package info.nemoworks.highlink.connector;

import info.nemoworks.highlink.kafka.JsonDeSerializer;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/7
 * @Copyright：
 */
public class KafkaConnectorHelper {
    public static Properties getKafkaProperties(){
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop105:9092");
        return properties;
    }

    public static <targetClazz> KafkaSource getKafkaSource(String topic, Class clazz){
        return KafkaSource.<targetClazz>builder()
                .setProperties(KafkaConnectorHelper.getKafkaProperties())
                .setTopics(topic)
                .setValueOnlyDeserializer(new JsonDeSerializer<>(clazz))
                .setStartingOffsets(OffsetsInitializer.latest())
                .build();
    }
}
