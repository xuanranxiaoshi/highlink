package info.nemoworks.highlink.clickHouse;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitForeignETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitForeignOtherTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignGasTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignMunicipalTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignParkTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdLocalTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryEtcTransaction;
import info.nemoworks.highlink.model.splitTransaction.ETCSplitResultExit;
import info.nemoworks.highlink.model.splitTransaction.ETCSplitResultGantry;
import info.nemoworks.highlink.model.splitTransaction.OtherSplitResultExit;
import info.nemoworks.highlink.model.splitTransaction.OtherSplitResultGantry;
import info.nemoworks.highlink.model.tollChangeTransaction.TollChangeTransactions;
import info.nemoworks.highlink.utils.Config;
import info.nemoworks.highlink.utils.DataSourceUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.*;

/**
 * @description:
 * @author：jimi
 * @date: 2024/5/17
 * @Copyright：
 */
public class TestCreateCHTableSQL {
    @Test
    public void testCreateSQL(){
        System.out.println("入口交易数据建表语句:");
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(EntryRawTransaction.class, "ID"));


        System.out.println("出口交易数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(ExitForeignETCTrans.class, "ID"));
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(ExitForeignOtherTrans.class, "ID"));
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(ExitLocalETCTrans.class, "ID"));
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(ExitLocalOtherTrans.class, "ID"));

        System.out.println("拓展交易数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(ExdForeignGasTransaction.class, "ID"));
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(ExdForeignMunicipalTransaction.class, "ID"));
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(ExdForeignParkTransaction.class, "ID"));
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(ExdLocalTransaction.class, "ID"));

        System.out.println("门架交易数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(GantryCpcTransaction.class, "ID"));
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(GantryEtcTransaction.class, "ID"));

        System.out.println("通行费变更数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(TollChangeTransactions.class, "ID"));

        System.out.println("跨省数据的建表语句: ");
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(ETCSplitResultExit.class, "ID"));
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(ETCSplitResultGantry.class, "ID"));
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(OtherSplitResultGantry.class, "ID"));
        System.out.println(JdbcConnectorHelper.getCHCreateTableString(OtherSplitResultExit.class, "ID"));
    }

    /**
     * 通过 schema.sql 文件创建数据表
     */
    @Test
    public void initDataBase() {
        String JDBC_DRIVER = "com.clickhouse.jdbc.ClickHouseDriver";
        String DB_URL = "jdbc:clickhouse://localhost:18123/test";
        String schemaLocation = Config.getProperty("CH" + ".schema");

        Connection conn = null;
        Statement stmt = null;

        try {
            // 注册 ClickHouse JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开连接
            System.out.println("连接到 ClickHouse 数据库...");
            conn = DriverManager.getConnection(DB_URL, "default", "");

            // 创建 Statement 对象
            stmt = conn.createStatement();
            InputStream inputStream = DataSourceUtils.class.getClassLoader().getResourceAsStream(schemaLocation);

            DataSourceUtils.executeSqlFile(conn, inputStream);


            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }

    }

}
