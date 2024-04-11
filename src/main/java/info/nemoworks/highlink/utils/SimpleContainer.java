package info.nemoworks.highlink.utils;

import info.nemoworks.highlink.dao.CacheDao;
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

    private static CacheDao cacheDao;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setAnnotationIntrospector(new WriteOnlyAnnotationIntrospector());

        // 从配置文件中读取具体实现类的类名
        String cacheDaoImplClassName = Config.getProperty("cache.dao.impl");

        try {
            // 使用反射实例化具体实现类对象
            Class<?> clazz = Class.forName(cacheDaoImplClassName);
            Constructor<?> constructor = clazz.getConstructor();
            cacheDao = (CacheDao) constructor.newInstance();
        }catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    public static CacheDao getCacheDao(){
        return cacheDao;
    }
}
