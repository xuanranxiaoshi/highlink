package info.nemoworks.highlink.sink;

import info.nemoworks.highlink.connector.JedisConnectorHelper;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.util.LinkedList;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/13
 * @Copyright：
 */
public class PathListRedisSink extends RichSinkFunction<LinkedList<PathTransaction>> {
    private transient Jedis jedis;
    private transient ObjectMapper objectMapper;

    @Override
    public void open(Configuration config) {
        jedis = JedisConnectorHelper.getRedis();
        objectMapper = new ObjectMapper();
    }

    @Override
    public void invoke(LinkedList<PathTransaction> pathTransactionLinkedList, Context context) throws Exception {
        PathTransaction pathTransaction = pathTransactionLinkedList.get(0);
        String passid = pathTransaction.getPASSID();
        jedis.set(passid, objectMapper.writeValueAsString(pathTransactionLinkedList));
    }

    @Override
    public void close() throws Exception {
        JedisConnectorHelper.close(jedis);
    }
}
