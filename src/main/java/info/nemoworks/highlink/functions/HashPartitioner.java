package info.nemoworks.highlink.functions;

import org.apache.flink.api.common.functions.Partitioner;

/**
 * @description: 自定义分区器，根据字符串的 hashCode 进行分区
 * @author：jimi
 * @date: 2024/5/29
 * @Copyright：
 */
public class HashPartitioner implements Partitioner<String> {

    @Override
    public int partition(String passId, int numPartitions) {
        return passId.hashCode() % numPartitions;
    }
}
