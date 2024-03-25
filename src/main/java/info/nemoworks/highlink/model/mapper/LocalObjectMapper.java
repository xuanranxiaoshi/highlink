package info.nemoworks.highlink.model.mapper;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/13
 * @Copyright：
 */
public class LocalObjectMapper {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }
}
