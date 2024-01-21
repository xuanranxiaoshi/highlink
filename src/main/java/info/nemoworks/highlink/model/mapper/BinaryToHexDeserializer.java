package info.nemoworks.highlink.model.mapper;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JacksonException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonParser;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.DeserializationContext;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/19
 * @Copyright：
 */
public class BinaryToHexDeserializer extends StdDeserializer<String> {
    protected BinaryToHexDeserializer() {
        super(String.class);
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String binaryString = node.asText();

        // 将二进制字符串转换为 BigInteger
        BigInteger bigInteger = new BigInteger(binaryString, 2);

        // 将 BigInteger 转换为 16 进制字符串
        String hexString = bigInteger.toString(16);

        return hexString.toUpperCase();

    }
}
