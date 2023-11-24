package info.nemoworks.highwaystreawm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonNodeSource implements SourceFunction<JsonNode> {
    @Override
    public void run(SourceContext<JsonNode> ctx) throws Exception {
        // 在这里替换为你的 JsonNode 数组数据
        List<JsonNode> jsonNodes = getJsonNodes();

        for (JsonNode jsonNode : jsonNodes) {
            ctx.collect(jsonNode);
        }
    }

    @Override
    public void cancel() {
        // 可以在这里添加取消逻辑
    }

    private List<JsonNode> getJsonNodes() {
        // 这里是一个示例数据集，替换为实际的 JsonNode 数组
        List<JsonNode> jsonNodes = new ArrayList<>();

        try {
            // 使用 ObjectMapper 创建 JSON 解析器
            ObjectMapper objectMapper = new ObjectMapper();

            // 读取 JSON 文件并解析为 JsonNode
            JsonNode jsonNode = objectMapper.readTree(DataStreamJob.class.getResource("/TBL_ENWASTEREC.json"));

            if (jsonNode.isArray()){
                for (JsonNode element : jsonNode) {
                    // 处理每个元素
                   jsonNodes.add(element);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return jsonNodes;

        }

    }
}

