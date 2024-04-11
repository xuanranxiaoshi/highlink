package info.nemoworks.highlink.sink;

import info.nemoworks.highlink.dao.CacheDao;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.utils.Config;
import info.nemoworks.highlink.utils.SimpleContainer;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/1
 * @Copyright：
 */
public class PathListCacheSink extends RichSinkFunction<LinkedList<PathTransaction>> {
    private transient CacheDao cacheDao;
    private transient ObjectMapper objectMapper;
    @Override
    public void open(Configuration config) {
        // 从配置文件中读取具体实现类的类名
        String cacheDaoImplClassName = Config.getProperty("cache.dao.impl");
        cacheDao = SimpleContainer.getCacheDao();
        objectMapper = SimpleContainer.getObjectMapper();
    }

    @Override
    public void invoke(LinkedList<PathTransaction> pathTransactionLinkedList, Context context) throws Exception {
        PathTransaction pathTransaction = pathTransactionLinkedList.get(0);
        String passid = pathTransaction.getPASSID();
        cacheDao.set(passid, objectMapper.writeValueAsString(pathTransactionLinkedList));
    }

    @Override
    public void close() throws Exception {
        cacheDao.close();
    }
}
