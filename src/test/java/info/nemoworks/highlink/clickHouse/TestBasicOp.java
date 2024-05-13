package info.nemoworks.highlink.clickHouse;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
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
    String DB_URL = "jdbc:clickhouse://localhost:18123/highLinks";

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


//    @Test
//    public void TestSink() throws Exception {
//        // 创建执行环境
//        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        // 创建输入数据源
//        String[] words = new String[] {"csc1", "csc2", "csc3", "csc4"};
//        // 将数组转换为Flink数据流
//        DataStream<String> dataSource = env.fromElements(words);
//
//        SingleOutputStreamOperator<Student> map = dataSource.map(new MapFunction<String, Student>() {
//            static int num = 0;
//            @Override
//            public Student map(String value) throws Exception {
//                Student student = new Student(value, ++num);
//                System.out.println(student);
//                return student;
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
//        // 创建 ClickHouse 连接配置
//        DataStreamSink<Student> sink = map.addSink(JdbcSink.sink(
//                "INSERT INTO STUDENT ( NUM, ID, NAME, AGE, GRADE) VALUES( ?, ?, ?, ?, ?)",
//                (statement, Student) -> {
//                    statement.setInt(1, Student.num);
//                    statement.setInt(2, Student.id);
//                    statement.setString(3, Student.name);
//                    statement.setInt(4, Student.age);
//                    statement.setString(5, Student.grade);
//                },
//                build
//        ));
//
//        // 启动流处理程序
//        env.execute("Flink Array Source Demo");
//    }
//
//
//    @Test
//    public void testStudent(){
//        JdbcConnectorHelper.getCreateTableString(Student .class);
//        System.out.println(JdbcConnectorHelper.getInsertTemplateString(Student.class));
//    }

}

@Data
class Student{
    int num;
    int id;
    String name;

    int age;

    String grade;

    public Student(String name, int id){
        this.name = name;
        this.id = id;
        this.num = id;
        this.age=22;
        this.grade ="A";
    }
}


