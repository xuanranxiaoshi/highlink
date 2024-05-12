package info.nemoworks.highlink.kafka;

import info.nemoworks.highlink.model.RawTransactionFactory;
import info.nemoworks.highlink.model.splitTransaction.ProvinceTransaction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.json.JsonReadFeature;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/24
 * @Copyright：
 */
public class ProvinceTransDeSerializer implements DeserializationSchema<ProvinceTransaction> {

    private ObjectMapper mapper;

    public ProvinceTransDeSerializer(){
        mapper = new ObjectMapper();
        mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
    }
    @Override
    public ProvinceTransaction deserialize(byte[] bytes) throws IOException {
        JsonNode jsonNode = mapper.readTree(new String(bytes));
        try {
            return RawTransactionFactory.getProvinceTransFromJson(jsonNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEndOfStream(ProvinceTransaction provinceTransaction) {
        return false;
    }

    @Override
    public TypeInformation<ProvinceTransaction> getProducedType() {
        return TypeInformation.of(ProvinceTransaction.class);
    }
}
