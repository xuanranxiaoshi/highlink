package info.nemoworks.highlink.connector;

import info.nemoworks.highlink.kafka.HighwayTransDeSerializer;
import info.nemoworks.highlink.kafka.JsonDeSerializer;
import info.nemoworks.highlink.kafka.ProvinceTransDeSerializer;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.splitTransaction.ProvinceTransaction;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/7
 * @Copyright：
 */
public class KafkaConnectorHelper {
    public static Properties getKafkaProperties(){
        Properties props = new Properties();
        //props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.80.188:9092");
        InputStream input = KafkaConnectorHelper.class.getClassLoader().getResourceAsStream("kafkaBasic.properties");
        try {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop105:9092");
        return props;
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
                .setGroupId("flink")
                .setTopics(topic)
                .setValueOnlyDeserializer(new HighwayTransDeSerializer())
                .setStartingOffsets(OffsetsInitializer.latest())
                .build();
    }

    public static KafkaSource getKafkaProvinceTransSource(String topic){
        return KafkaSource.<ProvinceTransaction>builder()
                .setProperties(KafkaConnectorHelper.getKafkaProperties())
                .setGroupId("flink")
                .setTopics(topic)
                .setValueOnlyDeserializer(new ProvinceTransDeSerializer())
                .setStartingOffsets(OffsetsInitializer.latest())
                .build();
    }
}
