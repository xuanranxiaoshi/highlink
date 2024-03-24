package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.exitTransaction.ExitForeignETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitForeignOtherTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.tollChangeTransaction.TollChangeTransactions;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignGasTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignMunicipalTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignParkTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdLocalTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryEtcTransaction;

public class CreateTableSQL {


    public static void main(String[] args) {
        System.out.println("出口交易数据的建表语句: ");
        JdbcConnectorHelper.getCreateTableString(ExitForeignETCTrans.class);
        JdbcConnectorHelper.getCreateTableString(ExitForeignOtherTrans.class);
        JdbcConnectorHelper.getCreateTableString(ExitLocalETCTrans.class);
        JdbcConnectorHelper.getCreateTableString(ExitLocalOtherTrans.class);

        System.out.println("拓展交易数据的建表语句: ");
        JdbcConnectorHelper.getCreateTableString(ExdForeignGasTransaction.class);
        JdbcConnectorHelper.getCreateTableString(ExdForeignMunicipalTransaction.class);
        JdbcConnectorHelper.getCreateTableString(ExdForeignParkTransaction.class);
        JdbcConnectorHelper.getCreateTableString(ExdLocalTransaction.class);

        System.out.println("门架交易数据的建表语句: ");
        JdbcConnectorHelper.getCreateTableString(GantryCpcTransaction.class);
        JdbcConnectorHelper.getCreateTableString(GantryEtcTransaction.class);

        System.out.println("通行费变更数据的建表语句: ");
        JdbcConnectorHelper.getCreateTableString(TollChangeTransactions.class);

    }
}
