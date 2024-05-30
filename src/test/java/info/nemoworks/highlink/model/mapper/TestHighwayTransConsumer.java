package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.connector.KafkaConnectorHelper;
import info.nemoworks.highlink.model.HighwayTransaction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/9
 * @Copyright：
 */
public class TestHighwayTransConsumer {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<HighwayTransaction> dataStreamSource = env.fromSource(KafkaConnectorHelper.getKafkaHighWayTransSource("HighLink"),
                WatermarkStrategy.noWatermarks(),
                "HighLinkSource",
                TypeInformation.of(HighwayTransaction.class));

        dataStreamSource.print();

        env.execute();
    }
}
