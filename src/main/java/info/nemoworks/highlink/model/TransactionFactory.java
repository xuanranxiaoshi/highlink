package info.nemoworks.highlink.model;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionFactory {

    public static HighwayTransaction fromJson(JsonNode json) throws Exception {
        if (json.isArray())
            throw new Exception();
        ObjectMapper mapper = new ObjectMapper();

        if (json.get("EXTOLLSTATION") != null)
            return mapper.treeToValue(json, ExitTransaction.class);
        if (json.get("GANTRYID") != null)
            return mapper.treeToValue(json, GantryTransaction.class);
        if (json.get("PARKOPERATORID") != null)
            return mapper.treeToValue(json, ParkTransaction.class);
        return mapper.treeToValue(json, EntryTransaction.class);
    }
}
