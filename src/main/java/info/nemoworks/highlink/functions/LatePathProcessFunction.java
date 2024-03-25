package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.connector.JedisConnectorHelper;
import info.nemoworks.highlink.model.mapper.LocalObjectMapper;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import redis.clients.jedis.Jedis;

import java.util.LinkedList;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/13
 * @Copyright：
 */
public class LatePathProcessFunction extends ProcessFunction<LinkedList<PathTransaction>, LinkedList<PathTransaction>> {

    private static Jedis jedis;
    private static ObjectMapper objectMapper;

    public LatePathProcessFunction(){
        jedis = JedisConnectorHelper.getRedis();
        objectMapper = LocalObjectMapper.getObjectMapper();
    }
    @Override
    public void processElement(LinkedList<PathTransaction> value,
                               ProcessFunction<LinkedList<PathTransaction>, LinkedList<PathTransaction>>.Context ctx,
                               Collector<LinkedList<PathTransaction>> out) throws Exception {
        PathTransaction pathTransaction = value.get(0);
        String passid = pathTransaction.getPASSID();
        String s = jedis.get(passid);
        // 触发对超时数据的he
        if(s != null){

        }
    }
}
