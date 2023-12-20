import org.junit.jupiter.api.Assertions;

import java.sql.*;

/**
 * @description:
 * @author：jimi
 * @date: 2023/12/19
 * @Copyright：
 */
public class H2test {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:E:\\develop\\h2Database\\data", "root", "root");
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE MY_USER"); //删除已经存在的表
        stmt.execute("CREATE TABLE MY_USER(ID VARCHAR(10) PRIMARY KEY,NAME VARCHAR(50))"); //创建表
        stmt.executeUpdate("INSERT INTO MY_USER VALUES('001','刘备')"); //插入数据
        stmt.executeUpdate("INSERT INTO MY_USER VALUES('002','关羽')");
        stmt.executeUpdate("INSERT INTO MY_USER VALUES('003','张飞')");
        stmt.executeUpdate("INSERT INTO MY_USER VALUES('004','孙策')");
        stmt.executeUpdate("INSERT INTO MY_USER VALUES('005','周瑜')");

        ResultSet rs = stmt.executeQuery("SELECT NAME FROM MY_USER WHERE ID = '001'"); //查询
        while(rs.next()){
            String name = rs.getString("NAME");
            Assertions.assertTrue(name.equals("刘备"));
        }
        rs.close();
        stmt.close();
        conn.close();

    }
}
