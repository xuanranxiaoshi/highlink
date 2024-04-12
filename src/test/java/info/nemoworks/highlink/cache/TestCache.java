package info.nemoworks.highlink.cache;

import info.nemoworks.highlink.dao.CacheDao;
import info.nemoworks.highlink.dao.CachePool;
import info.nemoworks.highlink.dao.JedisCacheDaoImp;
import info.nemoworks.highlink.utils.SimpleContainer;
import org.junit.jupiter.api.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.ConcurrentMap;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/2
 * @Copyright：
 */
public class TestCache {
    @Test
    public void TestRocksDB() {
        CachePool cachePool = SimpleContainer.getCachePool();
        CacheDao cacheDao = cachePool.getDaoImp();
        String set = cacheDao.set("student:name", "zhangsan");
        System.out.println("set: " + set);
        String name = cacheDao.get("student:name");
        System.out.println("get: " + name);
        System.out.println(cacheDao.del("student:name"));
        System.out.println("get again: " + cacheDao.get("student:name"));
    }

    @Test
    public void TestMapDB() {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        String key = "Hello";
        String val = "simple";
        map.put(key, val);
        System.out.println("第1次取值，" + map.get(key));
        Object remove = map.remove("student");
        System.out.println("remove: " + remove);
    }

    @Test
    public void TestPath() {
        String libraryPath = System.getProperty("java.library.path");
        System.out.println("java.library.path: " + libraryPath);
    }


}
