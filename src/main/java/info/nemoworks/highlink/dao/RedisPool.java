package info.nemoworks.highlink.dao;

import info.nemoworks.highlink.utils.Config;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/12
 * @Copyright：
 */
public class RedisPool implements CachePool, Serializable{
    private volatile static JedisPool pool;

    public RedisPool(){
        this.init();
    }

    private void init(){
        if (pool == null) {
            synchronized (JedisCacheDaoImp.class) {
                if (pool == null) {
                    //创建连接池的配置对象
                    JedisPoolConfig config = new JedisPoolConfig();
                    //设置最大链接数
                    config.setMaxTotal(Integer.parseInt(Config.getProperty("redis.maxTotal")));
                    //设置空闲连接数  "3"
                    config.setMaxIdle(Integer.parseInt(Config.getProperty("redis.maxIdle")));
                    config.setTestOnBorrow(true);
                    config.setTestWhileIdle(true);
                    config.setTestOnReturn(true);
                    //创建连接池
                    pool = new JedisPool(config, Config.getProperty("redis.url"), Integer.parseInt(Config.getProperty("redis.port")));
                }
            }
        }
    }

    @Override
    public CacheDao getDaoImp() {
        if(pool == null){
            init();
        }
        Jedis jedis = pool.getResource();
        return new JedisCacheDaoImp(jedis, pool);
    }

    @Override
    public CacheDao getDaoImp(String key) {
        if(pool == null){
            init();
        }
        Jedis jedis = pool.getResource();
        jedis.select(key.hashCode() % 16);
        return new JedisCacheDaoImp(jedis, pool);
    }

    @Override
    public void close(CacheDao cacheDao) {
        cacheDao.close();
    }
}
