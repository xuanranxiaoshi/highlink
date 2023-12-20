package info.nemoworks.highlink.model.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import info.nemoworks.highlink.model.GantryCpcTransaction;
import info.nemoworks.highlink.model.GantryEtcTransaction;
import info.nemoworks.highlink.model.GantryRawTransaction;
import info.nemoworks.highlink.model.RawTransactionFactory;

public class GantryMapperTest {

    @Test
    public void shouldMapRawToEtcTransaction() throws Exception {
        // given
        JsonNode rawGantry = new ObjectMapper().readTree(GantryMapperTest.class.getResourceAsStream("/gantryraw.json"));

        GantryRawTransaction rawTransaction = (GantryRawTransaction) RawTransactionFactory.fromJson(rawGantry);

        // when
        GantryEtcTransaction etcTransaction = GantryMapper.INSTANCE.gantryRawToEtcTransaction(rawTransaction);

        // then
        assertEquals(etcTransaction.getID(), rawTransaction.getID());
    }

    @Test
    public void shouldMapRawToCpcTransaction() throws Exception {
        // given
        JsonNode rawGantry = new ObjectMapper().readTree(GantryMapperTest.class.getResourceAsStream("/gantryraw.json"));

        GantryRawTransaction rawTransaction = (GantryRawTransaction) RawTransactionFactory.fromJson(rawGantry);
        // when
        GantryCpcTransaction cpcTransaction = GantryMapper.INSTANCE.gantryRawToCpcTransaction(rawTransaction);

        // then
        assertEquals(cpcTransaction.getID(), rawTransaction.getID());
    }

}
