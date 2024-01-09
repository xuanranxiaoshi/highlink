package info.nemoworks.highlink.model.mapper;
import info.nemoworks.highlink.model.EntryRawTransaction;
import info.nemoworks.highlink.kafka.JsonDeSerializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/6
 * @Copyright：
 */
public class TestGeneralConsumer {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 配置 Kafka 连接
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop105:9092");

        FlinkKafkaConsumer<EntryRawTransaction> ExitRawTransactionConsumer = new FlinkKafkaConsumer<>("ENTRY_WASTE", new JsonDeSerializer<>(EntryRawTransaction.class), properties);

        DataStreamSource<EntryRawTransaction> exWaste = env.addSource(ExitRawTransactionConsumer);

        exWaste.print();

        env.execute();
    }

}
