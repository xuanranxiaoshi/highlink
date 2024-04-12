package info.nemoworks.highlink.dao;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.concurrent.ConcurrentMap;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/12
 * @Copyright：
 */
public class MapdbPool implements CachePool{

    private DB db;
    private ConcurrentMap<String,String> map;
    public MapdbPool(){
        db = DBMaker
                .memoryDB()
                .make();
        map = db
                .hashMap("map", Serializer.STRING, Serializer.STRING)
                .createOrOpen();
    }
    @Override
    public CacheDao getDaoImp() {
        return  new MapdbDaoImp(map);
    }

    @Override
    public void close(CacheDao cacheDao) {
        cacheDao.close();
    }
}
