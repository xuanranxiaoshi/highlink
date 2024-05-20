package info.nemoworks.highlink.utils;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.sink.TransactionSinks;
import org.apache.flink.api.common.serialization.Encoder;
import org.apache.flink.connector.file.sink.FileSink;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.sink.filesystem.OutputFileConfig;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;
import java.util.concurrent.TimeUnit;


/**
 * @description:
 * @author：jimi
 * @date: 2024/3/6
 * @Copyright：
 */
public class SinkUtils {
    private static void addLogSinkToStream(DataStream<info.nemoworks.highlink.model.HighwayTransaction> dataStream, Class clazz, String name){
        dataStream.addSink(new TransactionSinks.LogSink<>());
    }
    public static void addInsertSinkToStream(DataStream<Object> dataStream, Class clazz) {
        dataStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(clazz),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions()));
    }
//    public static void addInsertSinkToStream(DataStream dataStream, Class clazz, String name) {
//        dataStream.addSink(JdbcSink.sink(
//                JdbcConnectorHelper.getInsertTemplateString(clazz),
//                JdbcConnectorHelper.getStatementBuilder(),
//                JdbcConnectorHelper.getJdbcExecutionOptions(),
//                JdbcConnectorHelper.getJdbcConnectionOptions())).name(name).setParallelism(1);
//    }
    // 全部写入 clickhouse 数据库
    public static void addInsertSinkToStream(DataStream dataStream, Class clazz, String name) {
        dataStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(clazz),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions())).name(name).setParallelism(1);
    }

    public static void addStream2CH(DataStream dataStream, Class clazz, String name){
        dataStream.addSink(JdbcSink.sink(
                JdbcConnectorHelper.getInsertTemplateString(clazz),
                JdbcConnectorHelper.getStatementBuilder(),
                JdbcConnectorHelper.getJdbcExecutionOptions(),
                JdbcConnectorHelper.getJdbcConnectionOptions())).name(name).setParallelism(1);
    }
    public static void addFileSinkToStream(DataStream dataStream, String filename, Encoder encoder){

        OutputFileConfig config = OutputFileConfig
                .builder()
                .withPartPrefix(filename)
                .withPartSuffix(".txt")
                .build();

        FileSink fileSink = FileSink.forRowFormat(new Path(Config.getProperty("flink.fileDataPath") + filename), encoder)
                .withRollingPolicy(
                        DefaultRollingPolicy.builder()
                                .withRolloverInterval(TimeUnit.MINUTES.toMillis(1))
                                .withInactivityInterval(TimeUnit.MINUTES.toMillis(5))
                                .withMaxPartSize(200 * 1024 * 1024)
                                .build())
                .withOutputFileConfig(config)
                .build();
        dataStream.sinkTo(fileSink).name(filename).setParallelism(1);
    }
}
