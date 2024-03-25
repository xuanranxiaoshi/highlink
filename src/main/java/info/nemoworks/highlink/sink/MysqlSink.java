package info.nemoworks.highlink.sink;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/19
 * @Copyright：
 */
public class MysqlSink<T> extends RichSinkFunction<T> {
    private Class<T> type;
    PreparedStatement ps;
    private  Connection connection;

    private List<T> batch;
    private int size;

    public MysqlSink(Class<T> type) {
        this.type = type;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = JdbcConnectorHelper.dataSource.getConnection();
        String insertTemplateString = JdbcConnectorHelper.getInsertTemplateString(type);
        ps = connection.prepareStatement(insertTemplateString);
        batch = new ArrayList<>();
        size = 0;
    }

    @Override
    public void close() throws Exception {
        super.close();
        //关闭连接和释放资源
        if (connection != null) {
            connection.close();
        }
        if (ps != null) {
            ps.close();
        }
    }

    @Override
    public void invoke(T value, Context context) throws Exception {
        super.invoke(value, context);
        if(batch.size() < 100){
            this.batch.add(value);
        }else{
            executeBatch();
        }
    }

    public void executeBatch() throws SQLException {
        if (!this.batch.isEmpty()) {
            Field[] fields = this.type.getDeclaredFields();
            for (T r : this.batch) {
                for (int i = 0; i < fields.length; i++) {
                    try {
                        ps.setObject(i + 1, fields[i].get(r));
                    } catch (Exception e) {
                        ps.setObject(i + 1, null);
                    }
                }
                ps.addBatch();
            }
            ps.executeBatch();
            this.batch.clear();
        }
    }
}
