package info.nemoworks.highlink.clickHouse;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description:
 * @author：jimi
 * @date: 2024/5/11
 * @Copyright：
 */
public class TestBasicOp {

    @Test
    public void TestConnect() throws SQLException {

        // JDBC 驱动和数据库 URL
        String JDBC_DRIVER = "com.clickhouse.jdbc.ClickHouseDriver";
        String DB_URL = "jdbc:clickhouse://localhost:18123/highLinks";

        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 ClickHouse JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开连接
            System.out.println("连接到 ClickHouse 数据库...");
            conn = DriverManager.getConnection(DB_URL);

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
}
