package info.nemoworks.highlink.kafka;

import com.csvreader.CsvReader;
import info.nemoworks.highlink.connector.KafkaConnectorHelper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 简单路径数据模拟生成，每个线程生成一个 passID 的完整路径数据之后，再生成其他路径的数据
 * @author：jimi
 * @date: 2024/1/17
 * @Copyright：
 */
public class GenerateGantryPath {
    public static <Lock> void main(String[] args) throws IOException {

        InputStream resourceAsStream = RunKafkaProducer.class.getClassLoader().getResourceAsStream("0117_prepareInput/tbl_GantryWasteRec.csv");
        HashMap<String, String> passIds = new HashMap<>();


        // 1. 统计 passId
        CsvReader csvReader = new CsvReader(resourceAsStream, '\t', Charset.forName("gb18030"));
        csvReader.readHeaders();

        int count = 0;
        while (csvReader.readRecord()) {
            count++;
            String passId = csvReader.get("PASSID");
            String mediaType = csvReader.get("MEDIATYPE");
            if (!passIds.containsKey(passId)) {
                passIds.put(passId, mediaType);
            }
        }
        System.out.println("数据条数：" + count + ", passId 数：" + passIds.size());

        // 2. 创建锁映射，每个passId对应一个独立的ReentrantLock
        Map<String, ReentrantLock> locks = new HashMap<>(10);
        passIds.keySet().forEach(passId -> locks.put(passId, new ReentrantLock()));


        // 3. 读取 entry、gantry、exit 的生成样本
        ObjectMapper mapper = new ObjectMapper();
        JsonNode enWasteRec = mapper.readTree(RunKafkaProducer.class.getClassLoader().getResourceAsStream("0117_prepareInput/onejson/EnWasteRec.json"));
        JsonNode gantryWasteRec = mapper.readTree(RunKafkaProducer.class.getClassLoader().getResourceAsStream("0117_prepareInput/onejson/GantryWasteRec.json"));
        JsonNode exitWasteRec = mapper.readTree(RunKafkaProducer.class.getClassLoader().getResourceAsStream("0117_prepareInput/onejson/ExWasteRec.json"));


        // 4. 每个线程随机选择一个 passID 产生相应的 entry, gantry， exit 时序数据
        // 4.1 配置 kafka
        Properties props = KafkaConnectorHelper.getKafkaProperties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        int numberOfThreads = 1;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            // 提交任务给线程池，使用KafkaProducerEmulator作为Runnable
            executorService.submit(new GenerateDataRunnable(enWasteRec,gantryWasteRec,exitWasteRec,passIds,locks,"HighLink", props));
        }

    }


    public static class GenerateDataRunnable implements Runnable {

        private JsonNode enWasteRec;
        private JsonNode gantryWasteRec;
        private JsonNode exitWasteRec;
        private HashMap<String, String> passIds;
        private Map<String, ReentrantLock> locks;
        private Random random = new Random();
        private ObjectMapper mapper = new ObjectMapper();
        private KafkaProducer producer;
        private String topic;
        private Properties props;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public GenerateDataRunnable(JsonNode enWasteRec, JsonNode gantryWasteRec, JsonNode exitWasteRec, HashMap<String, String> passIds, Map<String, ReentrantLock> locks, String topic, Properties props) {
            this.enWasteRec = enWasteRec;
            this.gantryWasteRec = gantryWasteRec;
            this.exitWasteRec = exitWasteRec;
            this.passIds = passIds;
            this.locks = locks;
            this.topic = topic;
            this.props = props;
            this.producer = new KafkaProducer<>(props);
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
            Thread.sleep(random.nextInt(1, 3) * 1000L);
            return date.getTime();
        }

        private long sendGantryTrans(String passID, String media, long threadID, long enTime) throws Exception{
            // 随机生成 3 -10 条数据
            int recordsNum = random.nextInt(3, 10);
            // 生成数据
            ObjectNode objectNode = (ObjectNode) gantryWasteRec;
            for (int i = 0; i < recordsNum; i++) {
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
               Thread.sleep(random.nextInt(1, 3) * 1000L);
            }
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
            Thread.sleep(random.nextInt(2, 5) * 1000L);
            // 返回当前数据的时间
            return enTime;
        }

        private static long getCurrentThreadId() {
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(Thread.currentThread().getId());
            return threadInfo.getThreadId();
        }

        @Override
        public void run() {
            while (true) {
                // 1. 随机选取一个 passId
                Object[] keys = passIds.keySet().toArray();
                Object randomId = keys[random.nextInt(keys.length)];
                String passId = (String) randomId;
                String media = passIds.get(passId);

                long threadId = getCurrentThreadId();

                // 2. 尝试获取相应的锁
                ReentrantLock lock = locks.get(passId);
                if (lock.tryLock()) {
                    try {
                        // 3. 生成该 passId 的 path数据
                        // 3.1 生成 entry 数据
                        long curTime = sendEntryTrans(passId, media, threadId);
                        // 3.2 生成 gantry 数据
                        curTime = sendGantryTrans(passId, media, threadId, curTime);
                        // 3.3 生成 exit 数据
                        sendExitTrans(passId, media, threadId, curTime);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("Thread [" + threadId + "] generate the passId: " + passId + " failed!");
                }
            }
        }
    }
}
