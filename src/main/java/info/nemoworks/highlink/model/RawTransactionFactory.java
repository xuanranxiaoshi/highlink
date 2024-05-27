package info.nemoworks.highlink.model;

import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.extendTransaction.ParkTransWasteRec;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.splitTransaction.*;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class RawTransactionFactory {
    private static ObjectMapper mapper = new ObjectMapper();

    public static HighwayTransaction fromJson(JsonNode json) throws Exception {
        if (json.isArray()) {
            throw new Exception();
        }

        if (json.get("EXTOLLSTATION") != null) {
            return mapper.treeToValue(json, ExitRawTransaction.class);
        }
        if (json.get("GANTRYID") != null) {
            return mapper.treeToValue(json, GantryRawTransaction.class);
        }
        if (json.get("PARKOPERATORID") != null) {
            return mapper.treeToValue(json, ParkTransWasteRec.class);
        }
        return mapper.treeToValue(json, EntryRawTransaction.class);
    }

    public static ProvinceTransaction getProvinceTransFromJson(JsonNode json) throws Exception {
        if (json.isArray()) {
            throw new Exception();
        }

        if (json.size() == 32){
            return mapper.treeToValue(json, SplitDetailExit.class);
        }
        if(json.size() == 70){
            return mapper.treeToValue(json, ETCSplitResultExit.class);
        }
        if(json.size() == 59){
            return mapper.treeToValue(json, ETCSplitResultGantry.class);
        }
        if(json.size() == 24){
            return mapper.treeToValue(json, ExitWaste.class);
        }
        if(json.size() == 84){
            return mapper.treeToValue(json, OtherSplitResultExit.class);
        }
        if(json.size() == 53){
            return mapper.treeToValue(json, OtherSplitResultGantry.class);
        }
        if(json.size() == 61){
            return mapper.treeToValue(json, SerTollSum.class);
        }

        throw new Exception();
    }
}
