package info.nemoworks.highlink.dataflow.utils;

import info.nemoworks.highlink.connector.Configure;
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
public class utils {
    private static void addLogSinkToStream(DataStream dataStream, Class clazz, String name){
        dataStream.addSink(new TransactionSinks.LogSink<>());
    }
    public static void addSinkToStream(DataStream dataStream, Class clazz) {
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

    public static void addFileSinkToStream(DataStream dataStream, String filename, Encoder encoder){

        OutputFileConfig config = OutputFileConfig
                .builder()
                .withPartPrefix(filename)
                .withPartSuffix(".txt")
                .build();

        FileSink fileSink = FileSink.forRowFormat(new Path(Configure.DATA_PATH + filename), encoder)
                .withRollingPolicy(
                        DefaultRollingPolicy.builder()
                                .withRolloverInterval(TimeUnit.MINUTES.toMillis(1))
                                .withInactivityInterval(TimeUnit.MINUTES.toMillis(5))
                                .withMaxPartSize(200 * 1024 * 1024)
                                .build())
                .withOutputFileConfig(config)
                .build();
        dataStream.sinkTo(fileSink);
    }
}
