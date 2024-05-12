package info.nemoworks.highlink.source;

import java.util.concurrent.TimeUnit;
import info.nemoworks.highlink.dao.CachePool;
import info.nemoworks.highlink.dao.JedisCacheDaoImp;
import info.nemoworks.highlink.dataflow.SplitDataFlow;
import info.nemoworks.highlink.model.RawTransactionFactory;
import info.nemoworks.highlink.model.splitTransaction.ProvinceTransaction;
import info.nemoworks.highlink.utils.SimpleContainer;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import redis.clients.jedis.resps.ScanResult;

/**
 * @description: 自定义多省数据源，用于定时提取缓存中暂存的跨省数据，重新开始计算
 * @author：jimi
 * @date: 2024/4/29
 * @Copyright：
 */
public class ProvinceRedisSource implements SourceFunction<ProvinceTransaction>{

    private volatile boolean isRunning = true;
    private final String pattern;

    private final Integer count;

    private final ObjectMapper mapper = SimpleContainer.getObjectMapper();

    private final CachePool cachePool = SimpleContainer.getCachePool();

    private final Long sleepTime = 5L;

    public ProvinceRedisSource(String pattern, Integer count){
        this.pattern = pattern;
        this.count = count;
    }


    @Override
    public void run(SourceContext<ProvinceTransaction> ctx) throws Exception{
        while (this.isRunning){
            JedisCacheDaoImp dao = null;
            try {
                dao = (JedisCacheDaoImp) cachePool.getDaoImp();
                // 初始游标为0，表示开始迭代
                String cursor = "0";

                do {
                    ScanResult<String> scanResult = dao.scan(cursor, pattern, count);
                    // 获取下一次迭代的游标位置
                    cursor = scanResult.getCursor();

                    for (String key : scanResult.getResult()) {
                        // 关联的 F2 数据已经到达
                        if(checkLaunch(key, dao)){
                            String data = dao.get(key);
                            JsonNode dataNode = mapper.readTree(data);
                            // 重新加入数据流进行计算
                            ctx.collect(RawTransactionFactory.getProvinceTransFromJson(dataNode));
                            dao.del(key);
                            System.out.println("[B:F] delete & launch: " + key);
                        }
                    }
                    // 当游标为 0 时表示迭代结束
                } while (!"0".equals(cursor));

            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(dao != null) {
                    dao.close();
                }
            }
            TimeUnit.SECONDS.sleep(this.sleepTime);
        }
    }

    @Override
    public void cancel() {
        this.isRunning = false;
    }

    /**
     * 检查缓存的 B2 或者 B4 相关联的 F2 数据是否存在，存在则进行启动
     * @param key
     * @param dao
     * @return
     */
    private boolean checkLaunch(String key, JedisCacheDaoImp dao){
        // 提取 passID
        String passID = null;
        int lastIndex = key.lastIndexOf(":");
        if (lastIndex != -1 && lastIndex < key.length() - 1) {
            passID = key.substring(lastIndex + 1);
        }
        // key 无效
        if(passID == null) {
            return false;
        }
        // 查询关联的 F2 数据
        String s = dao.get(SplitDataFlow.F2_PREFIX + passID);
        return s != null;
    }
}
