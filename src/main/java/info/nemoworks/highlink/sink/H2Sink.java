package info.nemoworks.highlink.sink;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import info.nemoworks.highlink.model.HighwayTransaction;

public class H2Sink<T extends HighwayTransaction> implements SinkFunction<T> {

    public static SinkFunction<T> getSink(){
        return JdbcSink.sink(
                            "insert into books (id, title, authors) values (?, ?, ?)",
                            (statement, book) -> {
                                    statement.setLong(1, book.id);
                                    statement.setString(2, book.title);
                                    statement.setString(3, book.authors);
                                    // statement.setInt(4, book.year);
                            },
                            JdbcExecutionOptions.builder()
                                            .withBatchSize(1000)
                                            .withBatchIntervalMs(200)
                                            .withMaxRetries(5)
                                            .build(),
                            new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                                            .withUrl("jdbc:h2:~/test")
                                            .withDriverName("org.h2.Driver")
                                            .withUsername("sa")
                                            .withPassword("")
                                            .build()));
    }
}
