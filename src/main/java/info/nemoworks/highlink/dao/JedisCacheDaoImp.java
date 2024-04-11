package info.nemoworks.highlink.dao;

import info.nemoworks.highlink.connector.JedisConnectorHelper;
import info.nemoworks.highlink.utils.Config;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/1
 * @Copyright：
 */
public class JedisCacheDaoImp implements CacheDao {

    private volatile static JedisPool pool;

    private Jedis jedis;

    public JedisCacheDaoImp(){

        if (pool == null) {
            synchronized (JedisCacheDaoImp.class) {
                if (pool == null) {
                    //创建连接池的配置对象
                    JedisPoolConfig config = new JedisPoolConfig();
                    //设置最大链接数
                    config.setMaxTotal(Integer.parseInt(Config.getProperty("redis.maxTotal")));
                    //设置空闲连接数  "3"
                    config.setMaxIdle(Integer.parseInt(Config.getProperty("redis.maxIdle")));
                    //创建连接池
                    pool = new JedisPool(config, Config.getProperty("redis.url"), Integer.parseInt(Config.getProperty("redis.port")));
                }
            }
        }

        jedis = pool.getResource();
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedis.set(key, value);
    }

    @Override
    public long del(String key) {
        return jedis.del(key);
    }

    @Override
    public void close() {
        JedisConnectorHelper.close(jedis);
    }
}
