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

/**
 * @description:
 * @author：jimi
 * @date: 2024/5/11
 * @Copyright：
 */
public class TestBasicOp {

    // JDBC 驱动和数据库 URL
    String JDBC_DRIVER = "com.clickhouse.jdbc.ClickHouseDriver";
    String DB_URL = "jdbc:clickhouse://localhost:8123/highLinks";

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
    public void TestCreateTable(){
        String createTableString = JdbcConnectorHelper.getCHCreateTableString(ETCClearResult.class);
        String createTableString1 = JdbcConnectorHelper.getCHCreateTableString(CashClearResult.class);
        String createTableString2 = JdbcConnectorHelper.getCHCreateTableString(ExpandClearResult.class);

        createTableString = createTableString.split(";")[0];
        createTableString1 = createTableString1.split(";")[0];
        createTableString2 = createTableString2.split(";")[0];

        createTableString += " PRIMARY KEY (TOLLINTERVALID)";
        createTableString1 += " PRIMARY KEY (TOLLINTERVALID)";
        createTableString2 += " PRIMARY KEY (CROPID)";

        System.out.println(createTableString);
        System.out.println(createTableString1);
        System.out.println(createTableString2);
    }


//    @Test
//    public void TestSink() throws Exception {
//        // 创建执行环境
//        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        // 创建输入数据源
//        String[] words = new String[] {"csc1", "csc2", "csc3", "csc4"};
//        // 将数组转换为Flink数据流
//        DataStream<String> dataSource = env.fromElements(words);
//
//        SingleOutputStreamOperator<ETCClearResult> map = dataSource.map(new MapFunction<String, ETCClearResult>() {
//            static int num = 0;
//            @Override
//            public ETCClearResult map(String value) throws Exception {
//                ETCClearResult result = new ETCClearResult();
//                result.setTOLLINTERVALID(value);
//                return result;
//            }
//        });
//
//
//        JdbcConnectionOptions build = new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
//                .withUrl(Config.getProperty("CH.url"))
//                .withDriverName(Config.getProperty("CH.driver-class-name"))
//                .withUsername(Config.getProperty("CH.username"))
//                .withPassword(Config.getProperty("CH.password"))
//                .build();
//
//
//
//
//        // 创建 ClickHouse 连接配置
//        map.addSink(JdbcSink.sink(
//                JdbcConnectorHelper.getInsertTemplateString(ETCClearResult.class),
//                (ps, t) -> {
//                    Field[] fields = t.getClass().getDeclaredFields();
//
//                    for (int i = 0; i < fields.length; i++) {
//                        fields[i].setAccessible(true);
//                        try {
//                            ps.setObject(i + 1, fields[i].get(t));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            ps.setObject(i + 1, null);
//                            System.out.println("index: "+ (i+1) +" setObject error");
//                        }
//                    }
//                },
//                build
//        ));
//
//        // 启动流处理程序
//        env.execute("Flink Array Source Demo");
//    }
//
//    @Test
//    public void testInsertString(){
//        System.out.println(JdbcConnectorHelper.getInsertTemplateString(ETCClearResult.class));
//    }

}


