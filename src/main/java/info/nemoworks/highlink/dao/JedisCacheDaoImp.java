package info.nemoworks.highlink.dao;

import info.nemoworks.highlink.connector.JedisConnectorHelper;
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
                    //读src下的文件用类加载器的方式  - 类加载器去加载成流，再使用Properties类来读
                    InputStream inputStream= JedisCacheDaoImp.class.getClassLoader().getResourceAsStream("jedis.properties");
                    Properties properties = new Properties();
                    try {
                        //将流中的数据读成map
                        properties.load(inputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 1:创建连接池的配置对象
                    JedisPoolConfig config = new JedisPoolConfig();
                    //设置最大链接数
                    config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
                    //设置空闲连接数  "3"
                    config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
                    //2:创建连接池
                    pool = new JedisPool(config, properties.getProperty("url"), Integer.parseInt(properties.getProperty("port")));
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
