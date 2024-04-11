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
        String inputString = node.asText();

        // 检查输入字符串是否是二进制字符串
        if (isBinaryString(inputString)) {
            // 将二进制字符串转换为 BigInteger
            BigInteger bigInteger = new BigInteger(inputString, 2);
            // 将 BigInteger 转换为 16 进制字符串
            return bigInteger.toString(16).toUpperCase();
        } else {
            // 如果输入的不是二进制字符串，则直接返回原始字符串
            return inputString;
        }
    }

    private boolean isBinaryString(String str) {
        // 二进制字符串只包含 '0' 和 '1'，因此可以使用正则表达式进行匹配
        return str.matches("[01]+");
    }
}
