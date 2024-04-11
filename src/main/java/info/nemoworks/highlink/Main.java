package info.nemoworks.highlink;

import info.nemoworks.highlink.dataflow.DataFlows;
import info.nemoworks.highlink.utils.Config;
import info.nemoworks.highlink.utils.H2Server;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class Main {

    public static void main(String[] args) throws Exception {
        printJvm();

        H2Server.initialize();

//        if ("h2".equals(Config.getProperty("datasource.type"))) {
//            H2Server.startServer();
//        }

        // 1. 本地 web-ui 显示方式
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(setConfig());

        // 2. 配置检查点
        String checkPath = "file://" + Config.getProperty("flink.checkPointPath");
        setCheckPoint(checkPath, env);

        // 3. 设置状态后端
        env.setStateBackend(new RocksDBStateBackend("file://" + Config.getProperty("flink.checkPointPath"), true));

        // 3. 读入数据进行预处理
        // PrepareGantryFromKafka.start(env);

        // 4. 进入业务数据流
        DataFlows.start(env);

        env.execute();

    }

    public static void printJvm(){
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();

        // 输出堆内存使用情况
        System.out.println("Heap Memory Usage:");
        System.out.println("  Initial: " + heapMemoryUsage.getInit() / 1024 + " KB");
        System.out.println("  Used: " + heapMemoryUsage.getUsed() / 1024 + " KB");
        System.out.println("  Committed: " + heapMemoryUsage.getCommitted() / 1024 + " KB");
        System.out.println("  Max: " + heapMemoryUsage.getMax() / 1024 + " KB");

        // 获取非堆内存使用情况（如方法区）
        MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();

        // 输出非堆内存使用情况
        System.out.println("Non-Heap Memory Usage:");
        System.out.println("  Initial: " + nonHeapMemoryUsage.getInit() / 1024 + " KB");
        System.out.println("  Used: " + nonHeapMemoryUsage.getUsed() / 1024 + " KB");
        System.out.println("  Committed: " + nonHeapMemoryUsage.getCommitted() / 1024 + " KB");
        System.out.println("  Max: " + nonHeapMemoryUsage.getMax() / 1024 + " KB");
    }

    public static void setCheckPoint(String path, StreamExecutionEnvironment env) {

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

    public static Configuration setConfig(){
        Configuration configuration = new Configuration();
        configuration.setString("taskmanager.memory.process.size", "6144m");
        configuration.setInteger("taskmanager.numberOfTaskSlots", 12);
        configuration.setInteger("parallelism.default", 1);
//        configuration.setBoolean("state.backend.changelog.enabled", true);
//        configuration.setString("state.backend.changelog.storage", "filesystem");
//        configuration.setString("dstl.dfs.base-path", "file://" + Config.getProperty("flink.dstl.dfs.base-path"));
        configuration.setDouble("taskmanager.memory.managed.fraction", 0.4);
        configuration.setDouble("taskmanager.memory.network.fraction", 0.05);
        configuration.setInteger("rest.port", 8081);
        return configuration;
    }
}
