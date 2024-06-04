package info.nemoworks.highlink.dao;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/1
 * @Copyright：
 */
public interface CacheDao extends AutoCloseable{
    String get(String key);

    String set(String key, String value);

    long del(String key);

    void close();
}
