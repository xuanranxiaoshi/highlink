package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.clearTransaction.CashClearResult;
import info.nemoworks.highlink.model.clearTransaction.ClearResult;
import info.nemoworks.highlink.model.clearTransaction.ETCClearResult;
import info.nemoworks.highlink.model.clearTransaction.ExpandClearResult;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitForeignETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitForeignOtherTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.splitTransaction.ETCSplitResultExit;
import info.nemoworks.highlink.model.splitTransaction.ETCSplitResultGantry;
import info.nemoworks.highlink.model.splitTransaction.OtherSplitResultExit;
import info.nemoworks.highlink.model.splitTransaction.OtherSplitResultGantry;
import info.nemoworks.highlink.model.tollChangeTransaction.TollChangeTransactions;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignGasTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignMunicipalTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignParkTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdLocalTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryEtcTransaction;
import org.junit.jupiter.api.Test;

public class CreateTableSQL {

    public static void main(String[] args) {
        System.out.println("入口交易数据建表语句:");
        System.out.println(JdbcConnectorHelper.getCreateTableString(EntryRawTransaction.class));


        System.out.println("出口交易数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCreateTableString(ExitForeignETCTrans.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(ExitForeignOtherTrans.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(ExitLocalETCTrans.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(ExitLocalOtherTrans.class));

        System.out.println("拓展交易数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCreateTableString(ExdForeignGasTransaction.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(ExdForeignMunicipalTransaction.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(ExdForeignParkTransaction.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(ExdLocalTransaction.class));

        System.out.println("门架交易数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCreateTableString(GantryCpcTransaction.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(GantryEtcTransaction.class));

        System.out.println("通行费变更数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCreateTableString(TollChangeTransactions.class));

        System.out.println("跨省数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCreateTableString(ETCSplitResultExit.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(ETCSplitResultGantry.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(OtherSplitResultGantry.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(OtherSplitResultExit.class));

        System.out.println("清分数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCreateTableString(ETCClearResult.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(CashClearResult.class));
        System.out.println(JdbcConnectorHelper.getCreateTableString(ExpandClearResult.class));





    }
    @Test
    public void testGantry()
    {
        JdbcConnectorHelper.getCreateTableString(GantryRawTransaction.class);
    }
}
