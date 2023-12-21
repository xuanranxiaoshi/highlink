package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.TollChangeTransactions;

public class CreateTableSQL {
    public static void main(String[] args) {
        String createTableString = JdbcConnectorHelper.getCreateTableString(TollChangeTransactions.class);
        System.out.println(createTableString);
    }
}
