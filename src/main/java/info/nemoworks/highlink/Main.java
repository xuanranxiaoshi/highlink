package info.nemoworks.highlink;

import info.nemoworks.highlink.dataflow.*;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.contrib.streaming.state.EmbeddedRocksDBStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.time.Duration;

public class Main {

    public static void main(String[] args) throws Exception {

        // 1. 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(new Configuration());
        // 本地 web-ui 显示方式
        // StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        // env.setParallelism(1);

        // 2. 配置检查点
        String checkPath = "file:///WDC/users/chensc/modules/flink-1.18.0/checkpoints";
        // String checkPath = "file:///tmp/flinkCheckPoints";
        setCheckPoint(checkPath, env);

        // 3. 设置状态后端
        EmbeddedRocksDBStateBackend rocksDBStateBackend = new EmbeddedRocksDBStateBackend(true);
        env.setStateBackend(rocksDBStateBackend);


        // 3. 读入数据进行预处理
        // PrepareDateFromFiles.start(env);
        PrepareGantryFromKafka.start(env);


        env.execute();
    }

    public static void setCheckPoint(String path, StreamExecutionEnvironment env){



        // 配置检查点信息
        env.enableCheckpointing(20000, CheckpointingMode.EXACTLY_ONCE);

        CheckpointConfig checkpointConfig = env.getCheckpointConfig();

        checkpointConfig.setCheckpointStorage(path);
        checkpointConfig.setMaxConcurrentCheckpoints(1);
        checkpointConfig.setMinPauseBetweenCheckpoints(1000);
        checkpointConfig.setExternalizedCheckpointCleanup(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        checkpointConfig.setTolerableCheckpointFailureNumber(10);
        checkpointConfig.setCheckpointTimeout(20 * 1000 * 60);

        // 启动非 barrier 对齐
//        checkpointConfig.enableUnalignedCheckpoints();
//        checkpointConfig.setAlignedCheckpointTimeout(Duration.ofSeconds(1));
        // 启动 changelog
        env.enableChangelogStateBackend(true);


    }
}
