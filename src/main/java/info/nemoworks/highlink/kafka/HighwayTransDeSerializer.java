package info.nemoworks.highlink.kafka;

import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.RawTransactionFactory;
import lombok.SneakyThrows;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.json.JsonReadFeature;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/9
 * @Copyright：
 */
public class HighwayTransDeSerializer implements DeserializationSchema<HighwayTransaction>{

    private ObjectMapper mapper;

    public HighwayTransDeSerializer(){
        mapper = new ObjectMapper();
        mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
    }
    @Override
    public HighwayTransaction deserialize(byte[] bytes) throws IOException {
        JsonNode jsonNode = mapper.readTree(new String(bytes));
        try {
            return RawTransactionFactory.fromJson(jsonNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEndOfStream(HighwayTransaction highwayTransaction) {
        return false;
    }

    @Override
    public TypeInformation<HighwayTransaction> getProducedType() {
        return TypeInformation.of(HighwayTransaction.class);
    }
}
