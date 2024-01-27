package info.nemoworks.highlink.kafka;

import info.nemoworks.highlink.dataflow.PrepareDateFromFiles;
import lombok.SneakyThrows;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 基本数据模拟生成，重复生成读取文件数据
 * @author：jimi
 * @date: 2024/1/7
 * @Copyright：
 */
public class RunKafkaProducer {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode enWasteRec = mapper.readTree(PrepareDateFromFiles.class.getClassLoader().getResourceAsStream("previousData/TBL_ENWASTEREC.json"));
        JsonNode exWasteRec = mapper.readTree(PrepareDateFromFiles.class.getClassLoader().getResourceAsStream("previousData/TBL_EXWASTEREC.json"));
        JsonNode gantryWasteRec = mapper.readTree(PrepareDateFromFiles.class.getClassLoader().getResourceAsStream("previousData/TBL_GANTRYWASTEREC.json"));
        JsonNode parkWasteRec = mapper.readTree(PrepareDateFromFiles.class.getClassLoader().getResourceAsStream("previousData/tbl_ParkTransWasteRec.json"));

        JsonNode[] jsonNodesArray = new JsonNode[] {enWasteRec, exWasteRec, gantryWasteRec, parkWasteRec};

        // 创建一个固定大小的线程池，可以根据需要调整线程数
        ExecutorService executorService = Executors.newFixedThreadPool(jsonNodesArray.length);

        for (int i = 0; i < jsonNodesArray.length; i++) {
            // 提交任务给线程池，使用KafkaProducerEmulator作为Runnable
            executorService.submit(new KafkaProducerEmulator(jsonNodesArray[i], "HighLink"));
        }
        //executorService.submit(new KafkaProducerEmulator(jsonNodesArray[2], "HighLink"));

        // 关闭线程池
        executorService.shutdown();
    }

    public static class KafkaProducerEmulator implements Runnable {

        private JsonNode jsonNode;
        private String topic;

        private int count;

        private Random random = new Random();
        private ObjectMapper mapper = new ObjectMapper();

        private KafkaProducer producer;


        public KafkaProducerEmulator(JsonNode jsonNode, String topic) throws IOException {
            this.jsonNode = jsonNode;
            this.topic = topic;
            this.count = 0;

            // 1. 配置 kafka
            // 设置参数
            Properties props = new Properties();
            // todo：修改 kafka 连接地址
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.80.188:9092");
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop105:9092");
            InputStream input = KafkaProducerEmulator.class.getClassLoader().getResourceAsStream("kafkaBasic.properties");
            props.load(input);

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
            while (true) {
                Thread.sleep(random.nextInt(200, 500));
                // 3.创建消息: 随机发送文中的一条 jason 数据
                String message = null;

                message = mapper.writeValueAsString(jsonNode.get(random.nextInt(jsonNode.size() - 1)));
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "oneKey", message);
                // 4.同步发送
                producer.send(producerRecord);
                System.out.println("Send topic [" + topic + "] :" + count++);
            }
        }
    }

}
