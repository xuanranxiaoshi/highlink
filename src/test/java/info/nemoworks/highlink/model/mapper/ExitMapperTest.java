package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.model.ExitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.ExitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.RawTransactionFactory;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitMapperTest {
    @Test
    public void shouldMapRawToLocalETCTrans() throws Exception{
        // given
        JsonNode exitRaw = new ObjectMapper().readTree(ExitMapperTest.class.getResourceAsStream("/exitraw.json"));

        ExitRawTransaction rawTransaction = (ExitRawTransaction) RawTransactionFactory.fromJson(exitRaw);

        // when
        ExitLocalETCTrans localTransaction = ExitMapper.INSTANCE.exitRawToExitLocalETC(rawTransaction);

        // then
        assertEquals(localTransaction.getID(), rawTransaction.getID());
    }
}
