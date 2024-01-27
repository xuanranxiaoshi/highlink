package info.nemoworks.highlink.kafka;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 模拟系统中同时有 10w 窗口存在的 path 数据生成
 * @author：jimi
 * @date: 2024/1/17
 * @Copyright：
 */
public class GenerateBigWindowGantryPath {
    public static void main(String[] args) throws IOException {

        long sleep = 200;

        if (args.length != 0) {
            sleep = Long.parseLong(args[0]);
            System.out.println("sleep " + sleep + "millionSeconds!");
        }


        // 1. 读取 entry、gantry、exit 的生成样本
        ObjectMapper mapper = new ObjectMapper();
        JsonNode enWasteRec = mapper.readTree(RunKafkaProducer.class.getClassLoader().getResourceAsStream("0117_prepareInput/onejson/EnWasteRec.json"));
        JsonNode gantryWasteRec = mapper.readTree(RunKafkaProducer.class.getClassLoader().getResourceAsStream("0117_prepareInput/onejson/GantryWasteRec.json"));
        JsonNode exitWasteRec = mapper.readTree(RunKafkaProducer.class.getClassLoader().getResourceAsStream("0117_prepareInput/onejson/ExWasteRec.json"));


        // 2. 配置 kafka
        Properties props = new Properties();
        // todo：修改 kafka 连接地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.80.188:9092");
        // props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop105:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 3. 线程配置
        int numberOfThreads = 40;
        int windowNum = 100000;


        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {

            // 提交任务给线程池，使用KafkaProducerEmulator作为Runnable
            executorService.submit(new GenerateDataRunnable(enWasteRec, gantryWasteRec, exitWasteRec, i, windowNum / numberOfThreads, 10, "HighLink", props, 200));
        }


        // 100000 / 40 = 2500  【0-2500） 【2500-5000）
    }


    public static class GenerateDataRunnable implements Runnable {

        private JsonNode enWasteRec;
        private JsonNode gantryWasteRec;
        private JsonNode exitWasteRec;
        private Random random = new Random();
        private ObjectMapper mapper = new ObjectMapper();
        private KafkaProducer producer;
        private String topic;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private long sleep;
        private int threadNumber;
        private int pathNum;
        private int pathLength;

        public GenerateDataRunnable(JsonNode enWasteRec, JsonNode gantryWasteRec, JsonNode exitWasteRec, int threadNumber, int pathNum, int pathLength, String topic, Properties props, long sleep) {
            this.enWasteRec = enWasteRec;
            this.gantryWasteRec = gantryWasteRec;
            this.exitWasteRec = exitWasteRec;
            this.threadNumber = threadNumber;
            this.topic = topic;
            this.pathNum = pathNum;
            this.producer = new KafkaProducer<>(props);
            this.sleep = sleep;
            this.pathLength = pathLength;
        }

        private long sendEntryTrans(String passID, String media, long threadID) throws Exception {
            // 当前时间
            Date date = new Date();
            // 生成数据
            ObjectNode objectNode = (ObjectNode) enWasteRec;
            objectNode.put("PASSID", passID);
            objectNode.put("MEDIATYPE", media);
            objectNode.put("ENTIME", dateFormat.format(date));
            // 发送数据
            String message = mapper.writeValueAsString(objectNode);
            ProducerRecord<String, String> entryRecord = new ProducerRecord<>(topic, "oneKey", message);
            producer.send(entryRecord);
            System.out.println("Thread [" + threadID + "] send Entry { passID: " + passID + ", enTime: " + dateFormat.format(date) + "}");
            // 返回当前数据的时间
            Thread.sleep(sleep);
            return date.getTime();
        }

        private long sendGantryTrans(String passID, String media, long threadID, long enTime) throws Exception {
            // 生成数据
            ObjectNode objectNode = (ObjectNode) gantryWasteRec;

            objectNode.put("PASSID", passID);
            objectNode.put("MEDIATYPE", media);
            // 随机增加 10 —— 120 分钟
            enTime += (random.nextInt(111) + 10) * 60 * 1000;
            Date date = new Date(enTime);
            objectNode.put("ENTIME", dateFormat.format(date));
            // 发送数据
            String message = mapper.writeValueAsString(objectNode);
            ProducerRecord<String, String> entryRecord = new ProducerRecord<>(topic, "oneKey", message);
            producer.send(entryRecord);
            System.out.println("Thread [" + threadID + "] send Gantry { passID: " + passID + ", enTime: " + dateFormat.format(date) + "}");
            Thread.sleep(sleep);

            // 返回当前数据的时间
            return enTime;
        }

        private long sendExitTrans(String passID, String media, long threadID, long enTime) throws Exception {
            // 生成数据
            ObjectNode objectNode = (ObjectNode) exitWasteRec;

            objectNode.put("PASSID", passID);
            objectNode.put("MEDIATYPE", media);
            // 随机增加 10 —— 120 分钟
            enTime += (random.nextInt(111) + 10) * 60 * 1000;
            Date date = new Date(enTime);
            objectNode.put("ENTIME", dateFormat.format(date));
            // 发送数据
            String message = mapper.writeValueAsString(objectNode);
            ProducerRecord<String, String> entryRecord = new ProducerRecord<>(topic, "oneKey", message);
            producer.send(entryRecord);
            System.out.println("Thread [" + threadID + "] send Exit { passID: " + passID + ", enTime: " + dateFormat.format(date) + "}");
            Thread.sleep(sleep);
            // 返回当前数据的时间
            return enTime;
        }

        private static long getCurrentThreadId() {
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(Thread.currentThread().getId());
            return threadInfo.getThreadId();
        }

        @SneakyThrows
        @Override
        public void run() {

            long threadId = getCurrentThreadId();
            HashMap<Integer, Long> times = new HashMap<>(pathNum);

            while (true) {

                // pathID 起始值
                int startID = pathNum * threadNumber;

                // 1. 为每条 path 生成 entry 数据
                for (int passID = startID; passID < startID + pathNum; passID++) {
                    String media = passID % 2 == 0 ? "1" : "2";
                    long curTime = sendEntryTrans(String.valueOf(passID), media, threadId);
                    times.put(passID, curTime);
                }

                // 2. 为每条 path 生成 gantry 数据
                for (int i = 0; i < pathLength; i++) {
                    for (int passID = startID; passID < startID + pathNum; passID++) {
                        String media = passID % 2 == 0 ? "1" : "2";
                        long curTime = times.get(passID);
                        long nextTime = sendGantryTrans(String.valueOf(passID), media, threadId, curTime);
                        times.put(passID, nextTime);
                    }
                }

                // 3. 为每条 path 生成 exit 数据
                for (int i = 0; i < pathLength; i++) {
                    for (int passID = startID; passID < startID + pathNum; passID++) {
                        String media = passID % 2 == 0 ? "1" : "2";
                        long curTime = times.get(passID);
                        sendExitTrans(String.valueOf(passID), media, threadId, curTime);
                    }
                }
            }
        }
    }
}
