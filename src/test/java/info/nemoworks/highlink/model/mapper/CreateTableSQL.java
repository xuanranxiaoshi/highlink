package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.ExitTransaction.ExitForeignETCTrans;
import info.nemoworks.highlink.model.ExitTransaction.ExitForeignOtherTrans;
import info.nemoworks.highlink.model.ExitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.ExitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.TollChangeTransactions;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignGasTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignMunicipalTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignParkTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdLocalTransaction;

public class CreateTableSQL {
    public static void main(String[] args) {
        String createTableString = JdbcConnectorHelper.getCreateTableString(ExitLocalETCTrans.class);
        System.out.println(createTableString);
    }
}
