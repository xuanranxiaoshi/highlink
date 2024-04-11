package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.connector.KafkaConnectorHelper;
import info.nemoworks.highlink.dataflow.encoder.PathEncoder;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.multiProvince.ProvinceTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.utils.SinkUtils;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.LinkedList;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/5
 * @Copyright：
 */
public class DataFlows {
    public static void start(StreamExecutionEnvironment env) throws Exception {

        // 0.1 预处理系统输入
        DataStreamSource unionStream = env.fromSource(KafkaConnectorHelper.getKafkaHighWayTransSource("HighLink"),
                WatermarkStrategy.noWatermarks(),
                "HighLinkSource",
                TypeInformation.of(HighwayTransaction.class));

        // 0.2 拆分子系统输入
        DataStreamSource provinceStream = env.fromSource(KafkaConnectorHelper.getKafkaProvinceTransSource("Province"),
                WatermarkStrategy.noWatermarks(),
                "ProvinceSource",
                TypeInformation.of(ProvinceTransaction.class));

        // 1. 预处理子系统: 对输入数据流进行拆分预处理、返回聚合的路径数据
        SingleOutputStreamOperator<LinkedList<PathTransaction>> aggregatePathStream =
                PrepareFlow.flow(unionStream);

        // 1.5 异常数据存储
        DataStream<LinkedList<PathTransaction>> cleanPathFlow = ExceptionFlow.flow(aggregatePathStream);

        // 输出清洗后的数据流
        DataStream<LinkedList<PathTransaction>> cleanPathCopyFlow = cleanPathFlow.broadcast();
        SinkUtils.addFileSinkToStream(cleanPathCopyFlow, "aggregatedPath", new PathEncoder());

        // 2. 拆分子系统：对车辆路径进行收费金额拆分
        SplitDataFlow.flow(cleanPathFlow, provinceStream);
    }
}
