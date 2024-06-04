package info.nemoworks.highlink.sink;

import info.nemoworks.highlink.dao.CacheDao;
import info.nemoworks.highlink.dataflow.SplitDataFlow;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.utils.SimpleContainer;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/2
 * @Copyright：
 */
public class MultiProvincePathCacheSink extends RichSinkFunction<List<PathTransaction>> {
    private transient CacheDao cacheDao;
    private transient ObjectMapper objectMapper;

    @Override
    public void open(Configuration config) {
        cacheDao = SimpleContainer.getCachePool().getDaoImp();
        objectMapper = SimpleContainer.getObjectMapper();
    }

    @Override
    public void invoke(List<PathTransaction> pathTransactionLinkedList, Context context) throws Exception {
        PathTransaction pathTransaction = pathTransactionLinkedList.get(0);
        String passid = pathTransaction.getPASSID();
        String key = SplitDataFlow.F2_PREFIX + passid;
        String pathStr = objectMapper.writeValueAsString(pathTransactionLinkedList);
        cacheDao.set(key, pathStr);
    }

    @Override
    public void close() throws Exception {
        cacheDao.close();
    }
}
