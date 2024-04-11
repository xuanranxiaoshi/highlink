package info.nemoworks.highlink.dao;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.concurrent.ConcurrentMap;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/1
 * @Copyright：
 */
public class MapdbDaoImp implements CacheDao{

    private final DB mapdb;
    private final ConcurrentMap<String, String> map;

    public MapdbDaoImp(){
        mapdb = DBMaker.memoryDB().make();
        map = (ConcurrentMap<String, String>) mapdb.hashMap("map").createOrOpen();
        System.out.println("mapdb open!");
    }
    @Override
    public String get(String key) {
        System.out.println("mapdb get(" + key + ")");
        return map.get(key);
    }

    @Override
    public String set(String key, String value) {
        System.out.println("mapdb set(" + key + ")");
        map.put(key, value);
        return value;
    }

    @Override
    public long del(String key) {
        Object remove = map.remove(key);
        if(remove != null) {
            return 1;
        }
        return 0;
    }

    @Override
    public void close() {
//        mapdb.close();
//        System.out.println("mapdb closed!");
    }
}
