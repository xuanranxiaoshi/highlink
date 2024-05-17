package info.nemoworks.highlink.connector;


import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import info.nemoworks.highlink.utils.Config;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;

public class JdbcConnectorHelper {
    public static <T> String getCreateTableString(Class<T> clazz) {
        String query = "CREATE TABLE " + clazz.getSimpleName().toUpperCase() + "(";
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().getSimpleName().toLowerCase().equals("long")
                    || fields[i].getType().getSimpleName().toLowerCase().contains("int")) {
                query += fields[i].getName().toUpperCase() + " bigint";
            } else if (fields[i].getType().getSimpleName().toLowerCase().contains("double")
                    || fields[i].getType().getSimpleName().toLowerCase().equals("float")) {
                query += fields[i].getName().toUpperCase() + " float";
            } else if (fields[i].getType().getSimpleName().toLowerCase().contains("string")) {
                query += fields[i].getName().toUpperCase() + " text";
            }
            if (i != fields.length - 1) {
                query += ",";
            }
        }
        query += ");";
        // System.out.println(query);
        return query;
    }

    /**
     * 生成 clickhouse 的建表语句
     * @param clazz
     * @param primaryKey
     * @return
     * @param <T>
     */
    public static <T> String getCHCreateTableString(Class<T> clazz, String primaryKey)  {
        String query = "CREATE TABLE " + clazz.getSimpleName().toUpperCase() + "(";
        Field[] fields = clazz.getDeclaredFields();

        // 开发环境下很多字段为空，所以设置 Nullable；如果是主键不能为空
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].getName().toUpperCase().equals(primaryKey.toUpperCase())){
                if (fields[i].getType().getSimpleName().toLowerCase().equals("long")
                        || fields[i].getType().getSimpleName().toLowerCase().contains("int")) {
                    query += fields[i].getName().toUpperCase() + " bigint";
                } else if (fields[i].getType().getSimpleName().toLowerCase().contains("double")
                        || fields[i].getType().getSimpleName().toLowerCase().equals("float")) {
                    query += fields[i].getName().toUpperCase() + " float";
                } else if (fields[i].getType().getSimpleName().toLowerCase().contains("string")) {
                    query += fields[i].getName().toUpperCase() + " text";
                }
                else if(fields[i].getType().getSimpleName().toLowerCase().contains("timestamp")){
                    query += fields[i].getName().toUpperCase() + " DateTime";
                }
                if (i != fields.length - 1) {
                    query += ",";
                }
            }else{
                if (fields[i].getType().getSimpleName().toLowerCase().equals("long")
                        || fields[i].getType().getSimpleName().toLowerCase().contains("int")) {
                    query += fields[i].getName().toUpperCase() + " Nullable(bigint)";
                } else if (fields[i].getType().getSimpleName().toLowerCase().contains("double")
                        || fields[i].getType().getSimpleName().toLowerCase().equals("float")) {
                    query += fields[i].getName().toUpperCase() + " Nullable(float)";
                } else if (fields[i].getType().getSimpleName().toLowerCase().contains("string")) {
                    query += fields[i].getName().toUpperCase() + " Nullable(text)";
                }
                else if(fields[i].getType().getSimpleName().toLowerCase().contains("timestamp")){
                    query += fields[i].getName().toUpperCase() + " Nullable(DateTime)";
                }
                if (i != fields.length - 1) {
                    query += ",";
                }
            }

        }
        query += ") PRIMARY KEY (" + primaryKey.toUpperCase() + ")";
        return query;
    }

    public static <T> String getInsertTemplateString(Class<T> clazz) {

        String query = "INSERT INTO " + clazz.getSimpleName().toUpperCase();
        Field[] fields = clazz.getDeclaredFields();

        String columes = " (", qmarks = "(";

        for (int i = 0; i < fields.length; i++) {
            columes += " " + fields[i].getName().toUpperCase();
            qmarks += " ?";
            if (i != fields.length - 1) {
                columes += ",";
                qmarks += ",";
            }
        }

        query = query + columes + ") VALUES" + qmarks + ")";
        // System.out.println(query);
        return query;
    }

    public static <T> JdbcStatementBuilder<T> getStatementBuilder() {
        return new JdbcStatementBuilder<T>() {

            @Override
            public void accept(PreparedStatement t, T u) throws SQLException {

                Field[] fields = u.getClass().getDeclaredFields();

                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    try {
                        t.setObject(i + 1, fields[i].get(u));
                    } catch (Exception e) {
                        t.setObject(i + 1, null);
                    }
                }

            }
        };
    }

    public static JdbcConnectionOptions getJdbcConnectionOptions() {
        String type = Config.getProperty( "datasource.type");
        return new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                .withUrl(Config.getProperty(type + ".url"))
                .withDriverName(Config.getProperty(type + ".driver-class-name"))
                .withUsername(Config.getProperty(type + ".username"))
                .withPassword(Config.getProperty(type + ".password"))
                .build();
    }

    public static JdbcConnectionOptions getClickHouseConnectionOptions() {
        return new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                .withUrl(Config.getProperty( "CH.url"))
                .withDriverName(Config.getProperty("CH.driver-class-name"))
                .withUsername(Config.getProperty("CH.username"))
                .withPassword(Config.getProperty("CH.password"))
                .build();
    }

    public static JdbcExecutionOptions getJdbcExecutionOptions() {
        return JdbcExecutionOptions.builder()
                .withBatchSize(200)
                .withBatchIntervalMs(200)
                .withMaxRetries(3)
                .build();
    }
}
