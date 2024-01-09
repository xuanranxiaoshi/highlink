package info.nemoworks.highlink.kafka;

import info.nemoworks.highlink.dataflow.PrepareDateFromFiles;
import lombok.SneakyThrows;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/6
 * @Copyright：
 */
public class KafkaProducerEmulator implements Runnable {

    private JsonNode jsonNode;
    private String topic;

    private int count;

    private Random random = new Random();
    private ObjectMapper mapper = new ObjectMapper();

    private KafkaProducer producer;


    public KafkaProducerEmulator(JsonNode jsonNode, String topic) {
        this.jsonNode = jsonNode;
        this.topic = topic;
        this.count = random.nextInt(100);

        // 1. 配置 kafka
        // 设置参数
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop105:9092");
        // 把发送的key从字符串序列化为字节数组，这里不采用jdk的序列化，而是自定义序列化方式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //把发送消息value从字符串序列化为字节数组
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 2. 创建生产消息的客户端，传入参数
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);

    }

    @SneakyThrows
    @Override
    public void run() {
        while (count > 0) {
            TimeUnit.SECONDS.sleep(random.nextInt(4));
            // 3.创建消息: 随机发送文中的一条 jason 数据
            String message = null;

            message = mapper.writeValueAsString(jsonNode.get(random.nextInt(jsonNode.size() - 1)));
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "oneKey", message);
            // 4.同步发送
            producer.send(producerRecord);
            System.out.println("Send topic ["+ topic + "] :" + message);
            count--;
        }
    }
}
