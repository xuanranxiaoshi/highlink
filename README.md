# Highlink

## 一、环境配置说明

### 1. kafka 配置
- 下载 Kafka（目前用的版本是 kafka_2.12-3.6.1）: https://kafka.apache.org/downloads
- 配置 kraft 模式的参数（config/kraft/server.properties 文件中的节点地址、log位置等）：https://kafka.apache.org/documentation/#kraft_config
- 格式化数据目录（第一次启动）： (1) 用kafka-storage.sh 生成一个唯一的集群ID (2) 根据产生的 ID，进行初始化
```
./bin/kafka-storage.sh random-uuid
```
 #会生成一个uuid
```
./bin/kafka-storage.sh format -t <这里为生成的uuid> -c ./config/kraft/server.properties
```
- 启动kafka时: 
```
./bin/kafka-server-start.sh ./config/kraft/server.properties
```
- 运行以下代码创建 “HighLink” 主题（--bootstrap-server 后为配置文件中配置的 kafka 节点的地址）：
```
./bin/kafka-topics.sh --create --topic HighLink --bootstrap-server 192.168.0.113:9092 
```

### 2. flink 服务器配置
- 下载 flink 安装包并在服务器自定义目录解压 (目前用的版本是 flink-1.18.0): https://flink.apache.org/downloads/
- 修改 config/flink-conf.yaml 文件， 配置集群中节点信息：
```
# JobManager 节点地址.
jobmanager.rpc.address: localhost
jobmanager.bind-host: 0.0.0.0
rest.address: localhost
rest.bind-address: 0.0.0.0
# TaskManager 节点地址.需要配置为当前机器名
taskmanager.bind-host: 0.0.0.0
taskmanager.host: localhost
taskmanager.numberOfTaskSlots: 12
```
- 修改 workers 文件，指定工作节点
```agsl
localhost
```
- 修改 masters 文件，指定主节点
```agsl
localhost:8081
```
- 启动 flink 集群（这里只有单个节点; Standalone 运行模式）
```
./bin/start-cluster.sh 
```
- 访问 服务器ip:8081 地址查看flink 集群信息

------





## 二、程序运行
### 1. 修改配置文件

* 修改 `src/main/resources/kafkaBasic.properties` 文件中 kafka 的连接地址
* 在 `src/main/resources/config.properties` 配置数据库、缓存、文件夹路径等
  * `datasource.type`:  系统使用的数据库类型，取值为 `h2` 、`mysql` 或 `CH`(clickhouse) ；h2 数据库集成在程序中，因此无需配置，选择 mysql 或 CH 则需修改文件中相关的连接信息
  * `cache.dao.impl`: 系统缓存机制的实现方式，取值为 `info.nemoworks.highlink.dao.MapdbDaoImp` 或 `info.nemoworks.highlink.dao.JedisCacheDaoImp` ; MapdbDaoImp 采用内置的 mapdb 实现，无需额外配置， JedisCacheDaoImp使用 redis 作为缓存数据库，需修改文件中 redis 相关的连接信息
  * `flink.fileDataPath`: 系统文件数据的输出路径
  * `flink.checkPointPath`:  系统检查点的保存路径

* H2 数据库配置
  * 修改文件存储路径（`jdbc:h2:tcp://localhost:3306/` 后的内容，如下面的储存路径为 `~/highLinks`）
    ```properties
     h2.url=jdbc:h2:tcp://localhost:3306/~/highLinks
    ```
  - 修改 web console 的端口号，默认为 8084
    ```properties
      h2.console.port=8084
    ```
  - 修改 web console 的远程访问地址，请修改为自己 H2 服务器的 ip 地址
    ```properties
      h2.webExternalNames={ip}
    ```
  以h2数据库模式运行程序后，则可浏览器访问 `http://{ip}:8084` 登录 H2 数据库 Web 界面

### 2. 提交任务至服务器运行

- 将程序打包，将 jar 包（highlink-0.0.1-SNAPSHOT-jar-with-dependencies.jar）上传到 flink 安装目录下
- 配置 flink 服务器（修改 conf/flink-conf.yaml）, 分配 6G 内存，启用增量 checkpoint，配置存储位置
```agsl
# 重新分配 flink 的内存大小
taskmanager.memory.process.size: 6144m
taskmanager.memory.managed.fraction: 0.4
taskmanager.memory.network.fraction: 0.05
# 启用状态后端的通用增量 checkpoint
state.backend.changelog.enabled: true
state.backend.changelog.storage: filesystem

# ！！！修改为相应路径
dstl.dfs.base-path: file:///WDC/users/chensc/modules/flink-1.18.0/changeLog/
state.backend.changelog.periodic-materialize.interval: 3 min

```
- 启动集群
```
./bin/start-cluster.sh
```
- 通过命令行，向集群提交任务（-m 后的服务器地址根据自己配置的 master 调整）：
```
./bin/flink run -m localhost:8081 -c info.nemoworks.highlink.Main ./highlink-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```
- 此时可以访问 `localhost:8081` 在 web-ui 上查看任务的详细信息 
  ![img.png](src/main/resources/static/runningJob.png)



### 3. 模拟产生数据

* 执行 ExpresswaySimulator 模拟器，向 kafka 产生模拟数据

- 查看数据流处理过程中的 metrics。对于数据流中每一个以 Counter 结尾命名的算子，都可以通过点击选中该算子后，在 web-ui 右侧 Metric 菜单中添加同名的 Metric 监控信息，如下图所示，为`RawGantryTransCounter`算子添加 RawGantryTransCounter.RawGantryTransCounter metric 信息：
  ![img.png](src/main/resources/static/counterMetrics.png)
  ![img.png](src/main/resources/static/counterMetrics2.png)

- 在 flink 的 web-ui 上查看 Running Job 的详细信息, 数据流相对于此前增加了数据聚合窗口
  ![dataflow2.png](src%2Fmain%2Fresources%2Fstatic%2Fdataflow2.png)

- 查看 checkpoint 保存情况：定时、通用增量式存储; CheckPointed Data Size 为当前检查点相对于上一轮新增的数据，Full Checkpoint Data Size 为检查点当前的数据总量
  ![checkpoints.png](src%2Fmain%2Fresources%2Fstatic%2Fcheckpoints.png)


