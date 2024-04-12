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

    private final ConcurrentMap<String, String> map;

    public MapdbDaoImp(ConcurrentMap<String, String> map){
        this.map = map;
    }
    @Override
    public String get(String key) {
        return map.get(key);
    }

    @Override
    public String set(String key, String value) {
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
    }
}
