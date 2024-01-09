package info.nemoworks.highlink;

import info.nemoworks.highlink.dataflow.PrepareDataFromKafka;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Main {

    public static void main(String[] args) throws Exception {

        // 1. 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(new Configuration());

        // 2. 配置检查点信息
        env.enableCheckpointing(5000, CheckpointingMode.EXACTLY_ONCE);
        CheckpointConfig checkpointConfig = env.getCheckpointConfig();

        // todo: 修改为本地路径
        checkpointConfig.setCheckpointStorage("file:///WDC/users/chensc/modules/flink-1.18.0/checkpoints");
        checkpointConfig.setMaxConcurrentCheckpoints(1);
        checkpointConfig.setMinPauseBetweenCheckpoints(1000);
        checkpointConfig.setExternalizedCheckpointCleanup(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        checkpointConfig.setTolerableCheckpointFailureNumber(10);

        // 2. 读入数据进行预处理
        // PrepareDateFromFiles.start(env);
        PrepareDataFromKafka.start(env);

        env.execute();
    }
}
