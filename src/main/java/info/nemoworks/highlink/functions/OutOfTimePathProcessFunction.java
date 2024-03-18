package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.connector.JedisConnectorHelper;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.mapper.LocalObjectMapper;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
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
public class OutOfTimePathProcessFunction extends ProcessFunction<LinkedList<PathTransaction>, LinkedList<PathTransaction>> {

    private static Jedis jedis;
    private static ObjectMapper objectMapper;

    public OutOfTimePathProcessFunction(){
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
        // 1. redis 不存在记录，不处理，直接流过记录
        if(s == null){
            out.collect(value);
        }
        // 2. redis 中已有数据，则取出进行合并后写入
        else{
            JsonNode jsonNode = objectMapper.readTree(s);
            LinkedList<PathTransaction> preList = new LinkedList<>();
            JsonNode curNode;
            PathTransaction curPathTrans;
            for (int i = 0; i < jsonNode.size(); i++) {
                curNode = jsonNode.get(i);
                if (curNode.get("EXTOLLSTATION".toLowerCase()) != null) {
                    curPathTrans = objectMapper.treeToValue(curNode, ExitRawTransaction.class);
                }else if (curNode.get("GANTRYID".toLowerCase()) != null) {
                    curPathTrans = objectMapper.treeToValue(curNode, GantryRawTransaction.class);
                }else{
                    curPathTrans = objectMapper.treeToValue(curNode, EntryRawTransaction.class);
                }
                preList.add(curPathTrans);
            }
            value.addAll(preList);
            out.collect(value);
        }
    }

}
