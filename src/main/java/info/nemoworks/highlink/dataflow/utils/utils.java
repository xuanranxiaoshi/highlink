package info.nemoworks.highlink.dataflow.utils;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.sink.TransactionSinks;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.datastream.DataStream;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/6
 * @Copyright：
 */
public class utils {
    private static void addLogSinkToStream(DataStream dataStream, Class clazz, String name){
        dataStream.addSink(new TransactionSinks.LogSink<>());
    }
    public static void addSinkToStream(DataStream dataStream, Class clazz) {
//        dataStream.addSink(new TransactionSinks.LogSink<>());
        dataStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(clazz),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));
    }

    public static void addSinkToStream(DataStream dataStream, Class clazz, String name) {
        dataStream.addSink(new TransactionSinks.LogSink<>()).name(name);
//        dataStream.addSink(JdbcSink.sink(
//                JdbcConnectorHelper.getInsertTemplateString(clazz),
//                JdbcConnectorHelper.getStatementBuilder(),
//                JdbcConnectorHelper.getJdbcExecutionOptions(),
//                JdbcConnectorHelper.getJdbcConnectionOptions())).name(name);
    }
}
