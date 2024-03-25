package info.nemoworks.highlink.dataflow.encoder;

import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

import java.util.LinkedList;

/**
 * @description: 异常流输出到 redis 的 mapper
 * @author：jimi
 * @date: 2024/3/13
 * @Copyright：
 */
public class PathRedisMapper implements RedisMapper<LinkedList<PathTransaction>> {
    /**
     * 设置 redis 数据类型
     * @return
     */
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public RedisCommandDescription getCommandDescription() {
        return new RedisCommandDescription(RedisCommand.SET);
    }

    /**
     * 设置 key
     * @param pathTransactionLinkedList
     * @return 返回 passID 作为 key
     */
    @Override
    public String getKeyFromData(LinkedList<PathTransaction> pathTransactionLinkedList) {
        return pathTransactionLinkedList.get(0).getPASSID();
    }

    /**
     * 设置 value
     * @param pathTransactionLinkedList
     * @return
     */
    @Override
    public String getValueFromData(LinkedList<PathTransaction> pathTransactionLinkedList) {
        try {
            return mapper.writeValueAsString(pathTransactionLinkedList);
        } catch (JsonProcessingException e) {
            System.out.println("[Error] PathRedisMapper");
            throw new RuntimeException(e);
        }
    }
}
