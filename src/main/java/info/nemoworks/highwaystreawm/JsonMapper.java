package info.nemoworks.highwaystreawm;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.api.common.functions.MapFunction;

public class JsonMapper implements MapFunction<JsonNode, JsonNode> {
    @Override
    public JsonNode map(JsonNode json) throws Exception {
        // 这里假设CSV文件的每一行就是一个JSON对象，实际情况可能需要根据CSV文件的结构进行解析
        // 在真实场景中，可以使用一些CSV解析库来处理CSV文件
        System.out.print(json.asText());
        return json;
    }
}