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

}
