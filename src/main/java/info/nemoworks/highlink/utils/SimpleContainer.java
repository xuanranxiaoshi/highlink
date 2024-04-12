package info.nemoworks.highlink.utils;

import info.nemoworks.highlink.dao.CacheDao;
import info.nemoworks.highlink.dao.CachePool;
import info.nemoworks.highlink.sink.WriteOnlyAnnotationIntrospector;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/13
 * @Copyright：
 */
public class SimpleContainer {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setAnnotationIntrospector(new WriteOnlyAnnotationIntrospector());
    }

    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    public static CachePool getCachePool(){
        // 从配置文件中读取具体实现类的类名
        String cacheDaoImplClassName = Config.getProperty("cache.dao.impl");
        CachePool cachePool = null;
        try {
            String className;
            if("redis".equals(cacheDaoImplClassName)){
                className = "info.nemoworks.highlink.dao.RedisPool";
            }else {
                className = "info.nemoworks.highlink.dao.MapdbPool";
            }
            // 使用反射实例化具体实现类对象
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor();
            cachePool = (CachePool) constructor.newInstance();
        }catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return cachePool;
    }
}
