package info.nemoworks.highlink.connector;

import info.nemoworks.highlink.kafka.HighwayTransDeSerializer;
import info.nemoworks.highlink.kafka.JsonDeSerializer;
import info.nemoworks.highlink.model.HighwayTransaction;
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
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.80.188:9092");
//        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop105:9092");
        return properties;
    }

    public static <targetClazz> KafkaSource getKafkaGeneralSource(String topic, Class clazz){
        return KafkaSource.<targetClazz>builder()
                .setProperties(KafkaConnectorHelper.getKafkaProperties())
                .setTopics(topic)
                .setValueOnlyDeserializer(new JsonDeSerializer<>(clazz))
                .setStartingOffsets(OffsetsInitializer.latest())
                .build();
    }

    public static KafkaSource getKafkaHighWayTransSource(String topic){
        return KafkaSource.<HighwayTransaction>builder()
                .setProperties(KafkaConnectorHelper.getKafkaProperties())
                .setTopics(topic)
                .setValueOnlyDeserializer(new HighwayTransDeSerializer())
                .setStartingOffsets(OffsetsInitializer.latest())
                .build();
    }
}
