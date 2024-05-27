package info.nemoworks.highlink.clickHouse;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.clearTransaction.CashClearResult;
import info.nemoworks.highlink.model.clearTransaction.ETCClearResult;
import info.nemoworks.highlink.model.clearTransaction.ExpandClearResult;
import info.nemoworks.highlink.model.splitTransaction.ETCSplitResultGantry;
import info.nemoworks.highlink.utils.Config;
import lombok.Data;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Date;

/**
 * @description:
 * @author：jimi
 * @date: 2024/5/11
 * @Copyright：
 */
public class TestBasicOp {

    // JDBC 驱动和数据库 URL
    String JDBC_DRIVER = "com.clickhouse.jdbc.ClickHouseDriver";
    String DB_URL = Config.getProperty("CH.url");

    String user = "default";

    String password = "";

    @Test
    public void TestConnect() throws SQLException {

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

            // 执行查询获取表名
            String sql = "SELECT name FROM system.tables WHERE database = 'highLinks'";
            ResultSet rs = stmt.executeQuery(sql);

            // 处理结果集
            System.out.println("当前数据库中的表名:");
            while (rs.next()) {
                // 获取表名
                String tableName = rs.getString("name");
                System.out.println(tableName);
            }

            // 关闭资源
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }

        System.out.println("查询完毕!");
    }

    @Test
    public void TestCreateTableStr(){
        String createTableString = JdbcConnectorHelper.getCHCreateTableString(ETCClearResult.class, "TOLLINTERVALID");
        String createTableString1 = JdbcConnectorHelper.getCHCreateTableString(CashClearResult.class, "TOLLINTERVALID");
        String createTableString2 = JdbcConnectorHelper.getCHCreateTableString(ExpandClearResult.class, "CROPID");

        System.out.println(createTableString);
        System.out.println(createTableString1);
        System.out.println(createTableString2);
    }


}


