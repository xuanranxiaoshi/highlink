package info.nemoworks.highlink.connector;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;

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
            if (i != fields.length - 1)
                query += ",";
        }
        query += ")";
        System.out.println(query);
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
        System.out.println(query);
        return query;
    }

    public static <T> JdbcStatementBuilder<T> getStatementBuilder() {
        return new JdbcStatementBuilder<T>() {

            @Override
            public void accept(PreparedStatement t, T u) throws SQLException {

                Field[] fields = u.getClass().getDeclaredFields();

                for (int i = 0; i < fields.length; i++) {
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
        return new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                .withUrl("jdbc:h2:~/test")
                .withDriverName("org.h2.Driver")
                .withUsername("sa")
                .withPassword("")
                .build();
    }

    public static JdbcExecutionOptions getJdbcExecutionOptions() {
        return JdbcExecutionOptions.builder()
                .withBatchSize(1000)
                .withBatchIntervalMs(200)
                .withMaxRetries(5)
                .build();
    }
}
