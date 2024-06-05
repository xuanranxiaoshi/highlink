package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.connector.KafkaConnectorHelper;
import info.nemoworks.highlink.dataflow.encoder.PathEncoder;
import info.nemoworks.highlink.functions.HashPartitioner;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.splitTransaction.ProvinceTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.source.ProvinceRedisSource;
import info.nemoworks.highlink.utils.Config;
import info.nemoworks.highlink.utils.SinkUtils;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/5
 * @Copyright：
 */
public class DataFlows {
    public static void start(StreamExecutionEnvironment env) throws Exception {

        WatermarkStrategy<HighwayTransaction> watermarkStrategy = WatermarkStrategy
                // 乱序流水位线
                .<HighwayTransaction>forBoundedOutOfOrderness(Duration.ofMinutes(1))
                .withTimestampAssigner(new SerializableTimestampAssigner<HighwayTransaction>() {
                    @Override
                    public long extractTimestamp(HighwayTransaction element, long recordTimestamp) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = null;
                        try {
                            date = dateFormat.parse(element.peekTime());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        // 返回的时间戳，毫秒
                        return date.getTime();
                    }
                })
                .withIdleness(Duration.ofMinutes(1));

        // 0.1 预处理系统输入
        DataStream<HighwayTransaction> unionStream = env.fromSource(KafkaConnectorHelper.getKafkaHighWayTransSource("HighLink"),
                        watermarkStrategy,
                        "预处理接收流水",
                        TypeInformation.of(HighwayTransaction.class))
                .setParallelism(8)
                .keyBy(HighwayTransaction::getPASSID);

        // 0.2 拆分子系统输入
        DataStream<ProvinceTransaction> provinceStream = env.fromSource(KafkaConnectorHelper.getKafkaProvinceTransSource("Province"),
                WatermarkStrategy.noWatermarks(),
                "部中心接收流水",
                TypeInformation.of(ProvinceTransaction.class))
                .setParallelism(1);

        DataStream<ProvinceTransaction> provinceUnionStream = provinceStream;

        // 定时从 redis 中读取 指定 key 格式的数据
        if("redis".equals(Config.getProperty("cache.dao.impl"))){
            // 0.3 缓存数据输入
            String pattern = "B*:" + SplitDataFlow.F2_PREFIX + "*";
            SingleOutputStreamOperator<ProvinceTransaction> provinceCacheStream = env.addSource(new ProvinceRedisSource(pattern, 100))
                    .setParallelism(1).name("缓存数据接收流水");
            provinceUnionStream = provinceStream.union(provinceCacheStream);
        }


        // 1. 预处理子系统: 对输入数据流进行拆分预处理、返回聚合的路径数据
        SingleOutputStreamOperator<List<PathTransaction>> aggregatePathStream =
                PrepareFlow.flow(unionStream);

        // 1.5 异常数据存储：对异常路径进行清洗
        DataStream<List<PathTransaction>> cleanPathFlow = ExceptionFlowDev.flow(aggregatePathStream);

        // 输出清洗后的数据流； 备份一份，输出到文件，方便查看聚合结果
        DataStream<List<PathTransaction>> cleanPathCopyFlow = cleanPathFlow.broadcast();
        SinkUtils.addFileSinkToStream(cleanPathCopyFlow, "aggregatedPath", new PathEncoder());

        // 2. 拆分子系统：对车辆路径进行收费金额拆分
        DataStream<info.nemoworks.highlink.model.splitTransaction.SplitResult> splitResStream = SplitDataFlow.flow(cleanPathFlow, provinceUnionStream);

        // 3. 清分子系统：对拆分后的数据进行入库；
        ClearDataFlow.flow(splitResStream);
    }
}
