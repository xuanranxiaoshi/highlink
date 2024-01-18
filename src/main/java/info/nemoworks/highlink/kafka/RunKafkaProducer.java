package info.nemoworks.highlink.kafka;

import info.nemoworks.highlink.dataflow.PrepareDateFromFiles;
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
}
