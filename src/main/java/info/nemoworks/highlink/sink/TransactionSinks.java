package info.nemoworks.highlink.sink;

import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.nemoworks.highlink.connector.JdbcConnectorHelper;
import info.nemoworks.highlink.model.GantryCpcTransaction;
import info.nemoworks.highlink.model.GantryEtcTransaction;
import info.nemoworks.highlink.model.HighwayTransaction;

public class TransactionSinks {

    public static final SinkFunction<GantryCpcTransaction> gantryCpcSink = JdbcSink.sink(
            JdbcConnectorHelper.getInsertTemplateString(GantryCpcTransaction.class),
            JdbcConnectorHelper.getStatementBuilder(),
            JdbcConnectorHelper.getJdbcExecutionOptions(),
            JdbcConnectorHelper.getJdbcConnectionOptions());

    public static final SinkFunction<GantryEtcTransaction> gantryEtcSink = JdbcSink.sink(
            JdbcConnectorHelper.getInsertTemplateString(GantryCpcTransaction.class),
            JdbcConnectorHelper.getStatementBuilder(),
            JdbcConnectorHelper.getJdbcExecutionOptions(),
            JdbcConnectorHelper.getJdbcConnectionOptions());

    public static class LogSink<T extends HighwayTransaction> implements SinkFunction<T> {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";

        private static final long serialVersionUID = 1L;

        private static final Logger LOG = LoggerFactory.getLogger(LogSink.class);

        @Override
        public void invoke(HighwayTransaction value, Context context) {

            LOG.info(ANSI_GREEN + value.getID() + ANSI_RESET);
        }
    }

}
