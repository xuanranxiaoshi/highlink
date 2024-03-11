package info.nemoworks.highlink.model.pathTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/6
 * @Copyright：
 */
public class TestSingleProvince {
    @Test
    public static void testETC() {
        // 1. 构造数据
        EntryRawTransaction entryRawTransaction = new EntryRawTransaction();
        ExitRawTransaction exitRawTransaction = new ExitRawTransaction();
        exitRawTransaction.setPAYTYPE(4);   // payWithETC
        GantryRawTransaction gantry1 = new GantryRawTransaction();
        GantryRawTransaction gantry2 = new GantryRawTransaction();
        GantryRawTransaction gantry3 = new GantryRawTransaction();

        gantry1.setTOLLINTERVALID("G001532003001520|G001532003001521");
        gantry1.setPAYFEEGROUP("1000|2000");
        gantry1.setFEEGROUP("800|1300");
        gantry1.setDISCOUNTFEEGROUP("200|700");

        gantry1.setTOLLINTERVALID("G001532003001521");
        gantry1.setPAYFEEGROUP("3000");
        gantry1.setFEEGROUP("2500");
        gantry1.setDISCOUNTFEEGROUP("500");

        gantry1.setTOLLINTERVALID("G001532003001521|G001532003001522");
        gantry1.setPAYFEEGROUP("1000|2000");
        gantry1.setFEEGROUP("800|1300");
        gantry1.setDISCOUNTFEEGROUP("200|700");

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
}
