package info.nemoworks.highlink.dao;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/12
 * @Copyright：
 */
public interface CachePool {
    public CacheDao getDaoImp();

    public CacheDao getDaoImp(String key);

    public void close(CacheDao cacheDao);
}
