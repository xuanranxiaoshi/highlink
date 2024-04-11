package info.nemoworks.highlink.model.pathTransaction;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.dao.CacheDao;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.RawTransactionFactory;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.extendTransaction.ParkTransWasteRec;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.utils.SimpleContainer;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/6
 * @Copyright：
 */
public class TestSingleProvincePathTrans {
    ObjectMapper mapper = SimpleContainer.getObjectMapper();

    @Test
    public  void testETC() {
        // 1. 构造数据
        EntryRawTransaction entryRawTransaction = new EntryRawTransaction();
        ExitRawTransaction exitRawTransaction = new ExitRawTransaction();
        exitRawTransaction.setPAYTYPE(3);   // payWithETC
        GantryRawTransaction gantry1 = new GantryRawTransaction();
        GantryRawTransaction gantry2 = new GantryRawTransaction();
        GantryRawTransaction gantry3 = new GantryRawTransaction();

        gantry1.setTOLLINTERVALID("G001532003001520|G001532003001521");
        gantry1.setPAYFEEGROUP("1000|2000");
        gantry1.setFEEGROUP("800|1300");
        gantry1.setDISCOUNTFEEGROUP("200|700");

        gantry2.setTOLLINTERVALID("G001532003001521");
        gantry2.setPAYFEEGROUP("3000");
        gantry2.setFEEGROUP("2500");
        gantry2.setDISCOUNTFEEGROUP("500");

        gantry3.setTOLLINTERVALID("G001532003001521|G001532003001522");
        gantry3.setPAYFEEGROUP("1000|2000");
        gantry3.setFEEGROUP("800|1300");
        gantry3.setDISCOUNTFEEGROUP("200|700");

        LinkedList<PathTransaction> pathTransactions = new LinkedList<>();
        pathTransactions.add(entryRawTransaction);
        pathTransactions.addAll(Arrays.asList(gantry1, gantry2, gantry3));
        pathTransactions.add(exitRawTransaction);

        SingleProvincePathTrans singleProvincePathTrans = new SingleProvincePathTrans(pathTransactions);
        singleProvincePathTrans.splitCharge();
        HighwayTransaction updateRes = singleProvincePathTrans.getUpdateRes();
        if (updateRes instanceof ExitLocalETCTrans){
            System.out.println("true");
        }
        System.out.println("finish");
    }

    @Test
    public void testPathMapper() throws Exception {


        EntryRawTransaction entryRawTransaction = mapper.readValue(TestSingleProvincePathTrans.class.getClassLoader().getResourceAsStream("onejson/EnWasteRec.json"), EntryRawTransaction.class);
        GantryRawTransaction gantry1 = mapper.readValue(TestSingleProvincePathTrans.class.getClassLoader().getResourceAsStream("onejson/GantryWasteRec.json"), GantryRawTransaction.class);
        GantryRawTransaction gantry2 = mapper.readValue(TestSingleProvincePathTrans.class.getClassLoader().getResourceAsStream("onejson/GantryWasteRec.json"), GantryRawTransaction.class);
        GantryRawTransaction gantry3 = mapper.readValue(TestSingleProvincePathTrans.class.getClassLoader().getResourceAsStream("onejson/GantryWasteRec.json"), GantryRawTransaction.class);
        ExitRawTransaction exitRawTransaction = mapper.readValue(TestSingleProvincePathTrans.class.getClassLoader().getResourceAsStream("onejson/ExWasteRec.json"), ExitRawTransaction.class);

        String passID = "0123456789123456";
        entryRawTransaction.setPASSID(passID);
        gantry1.setPASSID(passID);
        gantry2.setPASSID(passID);
        gantry3.setPASSID(passID);
        exitRawTransaction.setPASSID(passID);

        gantry1.setTOLLINTERVALID("G001532003001520|G001532003001521");
        gantry1.setPAYFEEGROUP("1000|2000");
        gantry1.setFEEGROUP("800|1300");
        gantry1.setDISCOUNTFEEGROUP("200|700");

        gantry2.setTOLLINTERVALID("G001532003001521");
        gantry2.setPAYFEEGROUP("3000");
        gantry2.setFEEGROUP("2500");
        gantry2.setDISCOUNTFEEGROUP("500");

        gantry3.setTOLLINTERVALID("G001532003001521|G001532003001522");
        gantry3.setPAYFEEGROUP("1000|2000");
        gantry3.setFEEGROUP("800|1300");
        gantry3.setDISCOUNTFEEGROUP("200|700");


        LinkedList<PathTransaction> pathTransactions = new LinkedList<>();
        pathTransactions.add(entryRawTransaction);
        pathTransactions.addAll(Arrays.asList(gantry1, gantry2, gantry3));
        pathTransactions.add(exitRawTransaction);

        // pathList to json
        String pathStr = mapper.writeValueAsString(pathTransactions);

        // json to PathList
        JsonNode jsonNode = mapper.readTree(pathStr);
        LinkedList<PathTransaction> list2 = new LinkedList<>();
        for (int i = 0; i < jsonNode.size(); i++) {
            JsonNode json = jsonNode.get(i);
            PathTransaction pathTransaction;
            System.out.println(json.toString());
            if (json.get("EXTOLLSTATION") != null) {
                pathTransaction = mapper.treeToValue(json, ExitRawTransaction.class);
            }else if (json.get("GANTRYID") != null) {
                pathTransaction = mapper.treeToValue(json, GantryRawTransaction.class);
            }else{
                pathTransaction = mapper.treeToValue(json, EntryRawTransaction.class);
            }
            list2.add(pathTransaction);
        }

        System.out.println(list2);
//        CacheDao cacheDao = SimpleContainer.getCacheDao();
//        cacheDao.set("test", pathStr);
//        System.out.println("get: " + cacheDao.get("test"));

    }
}
