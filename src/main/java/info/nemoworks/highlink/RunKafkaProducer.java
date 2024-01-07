package info.nemoworks.highlink;

import info.nemoworks.highlink.dataflow.PrepareDateFromFiles;
import info.nemoworks.highlink.kafka.KafkaProducerEmulator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/7
 * @Copyright：
 */
public class RunKafkaProducer {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode enWasteRec = mapper.readTree(PrepareDateFromFiles.class.getClassLoader().getResourceAsStream("TBL_ENWASTEREC.json"));
        JsonNode exWasteRec = mapper.readTree(PrepareDateFromFiles.class.getClassLoader().getResourceAsStream("TBL_EXWASTEREC.json"));
        JsonNode gantryWasteRec = mapper.readTree(PrepareDateFromFiles.class.getClassLoader().getResourceAsStream("TBL_GANTRYWASTEREC.json"));
        JsonNode parkWasteRec = mapper.readTree(PrepareDateFromFiles.class.getClassLoader().getResourceAsStream("tbl_ParkTransWasteRec.json"));

        String[] topics = {"ENTRY_WASTE", "EXIT_WASTE", "GANTRY_WASTE", "EXTEND_WASTE"};
        JsonNode[] jsonNodesArray = new JsonNode[] {enWasteRec, exWasteRec, gantryWasteRec, parkWasteRec};

        // 创建一个固定大小的线程池，可以根据需要调整线程数
        int numberOfThreads = Math.min(jsonNodesArray.length, topics.length);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            // 提交任务给线程池，使用KafkaProducerEmulator作为Runnable
            executorService.submit(new KafkaProducerEmulator(jsonNodesArray[i], topics[i]));
        }

        // 关闭线程池
        executorService.shutdown();
    }
}
