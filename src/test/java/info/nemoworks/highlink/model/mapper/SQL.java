package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.TollChangeTransactions;
import info.nemoworks.highlink.model.extendTransaction.ExtForeignGasTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExtForeignMunicipalTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExtForeignParkTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExtLocalTransaction;

/**
 * @description:
 * @author：jimi
 * @date: 2023/12/20
 * @Copyright：
 */
public class SQL {
    public static void main(String[] args) {
        String createTableString = JdbcConnectorHelper.getCreateTableString(TollChangeTransactions.class);
        System.out.println(createTableString);
    }
}
