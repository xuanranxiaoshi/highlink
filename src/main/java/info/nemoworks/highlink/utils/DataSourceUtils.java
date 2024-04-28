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

public class DataSourceUtils {

    private static final String PORT = "8082";

    private static final String SOURCE_TYPE = Config.getProperty("datasource.type");
    public static void startH2Server(){
        try {
            if(!"h2".equals(SOURCE_TYPE) || !"TRUE".equalsIgnoreCase(Config.getProperty("h2.console.enabled"))){
                return;
            }
            String port = Config.getProperty("h2.console.port");

            // 启动H2控制台服务
            Server h2Server = Server.createTcpServer("-tcpAllowOthers", "-tcpPort", "3306").start();
            Server h2WebServer = Server.createWebServer("-webAllowOthers", "-webPort", Objects.requireNonNullElse(port, PORT)).start();
            System.out.println("H2 server started.");

            // 注册关闭钩子
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down H2 server...");
                h2Server.stop();
                h2WebServer.stop();
                System.out.println("H2 server stopped.");
            }));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 连接到数据库
        Class.forName(Config.getProperty(SOURCE_TYPE + ".driver-class-name"));
        String dbUrl = Config.getProperty(SOURCE_TYPE + ".url");
        String username = Config.getProperty(SOURCE_TYPE + ".username");
        String password = Config.getProperty(SOURCE_TYPE + ".password");
        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static void initialize(){
        String schemaLocation = Config.getProperty(SOURCE_TYPE + ".schema");
        if(schemaLocation == null){
            schemaLocation = "db/mysql/schema.sql";
        }
        try(Connection connection = getConnection()) {
            InputStream inputStream = DataSourceUtils.class.getClassLoader().getResourceAsStream(schemaLocation);
            executeSqlFile(connection, inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void executeSqlFile(Connection conn, InputStream inputStream) throws Exception {
        System.out.println(SOURCE_TYPE + " DataBase Initial!");
        StringBuilder sql = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                sql.append(line);
            }
        }

        try (Statement stmt = conn.createStatement()) {
            String s = sql.toString();
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
            DataSourceUtils.initialize();
        }
        if("h2".equals(SOURCE_TYPE)){
            DataSourceUtils.startH2Server();
        }

    }

}

