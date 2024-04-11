package info.nemoworks.highlink.utils;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/2
 * @Copyright：
 */
import org.h2.tools.Server;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class H2Server {

    private static final String PORT = "8082";

    public static void startServer(){
        try {
            if(!"TRUE".equalsIgnoreCase(Config.getProperty("h2.console.enabled"))){
                return;
            }
            String port = Config.getProperty("h2.console.port");

            // 启动H2控制台服务
            Server h2Server = Server.createWebServer("-tcp", "-tcpAllowOthers", "-tcpPort", "3306", "-webAllowOthers", "-web", "-webPort", Objects.requireNonNullElse(port, PORT)).start();
            System.out.println("H2 server started.");

            // 注册关闭钩子
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down H2 server...");
                h2Server.stop();
                System.out.println("H2 server stopped.");
            }));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 连接到H2数据库
        Class.forName(Config.getProperty("datasource.driver-class-name"));
        String dbUrl = Config.getProperty("datasource.url");
        String username = Config.getProperty("datasource.username");
        String password = Config.getProperty("datasource.password");
        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static void initialize(){
        String schemaLocation = Config.getProperty("datasource.schema");
        if(schemaLocation == null){
            schemaLocation = "db/h2/schema.sql";
        }
        try(Connection connection = getConnection()) {
            InputStream inputStream = H2Server.class.getClassLoader().getResourceAsStream(schemaLocation);
            executeSqlFile(connection, inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void executeSqlFile(Connection conn, InputStream inputStream) throws Exception {
        System.out.println("H2 DataBase Initial!");
        StringBuilder sql = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                sql.append(line).append("\n");
            }
        }

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql.toString());
        }
    }
    public static void main(String[] args) {
        boolean initializeFlag = false;
        for (String arg : args) {
            if (arg.equals("init")) {
                initializeFlag = true;
                break;
            }
        }
        if (initializeFlag) {
            H2Server.initialize();
        }
        H2Server.startServer();
    }

}

