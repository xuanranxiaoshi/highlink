package info.nemoworks.highlink.dao;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/1
 * @Copyright：
 */
public class JedisCacheDaoImp implements CacheDao {

    private  JedisPool pool;

    private Jedis jedis;

    public JedisCacheDaoImp(Jedis jedis, JedisPool pool){
        this.jedis = jedis;
        this.pool = pool;
    }
    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public String set(String key, String value) {
        jedis.set(key, value);
        return value;
    }

    @Override
    public long del(String key) {
        return jedis.del(key);
    }

    @Override
    public void close() {
        if(jedis != null){
            pool.returnResource(jedis);
        }
    }

    public ScanResult<String> scan(String cursor, String pattern, Integer count){
        // 创建 ScanParams 对象，设置模式
        ScanParams scanParams = new ScanParams();
        scanParams.match(pattern);
        scanParams.count(count);

        return jedis.scan(cursor, scanParams);
    }

}
