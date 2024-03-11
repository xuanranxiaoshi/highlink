package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.model.exitTransaction.*;
import info.nemoworks.highlink.model.RawTransactionFactory;
import info.nemoworks.highlink.model.tollChangeTransaction.TollChangeTransactions;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitMapperTest {

    // given
    JsonNode exitRaw = new ObjectMapper().readTree(ExitMapperTest.class.getResourceAsStream("/exitraw.json"));

    ExitRawTransaction rawTransaction = (ExitRawTransaction) RawTransactionFactory.fromJson(exitRaw);

    public ExitMapperTest() throws Exception {
    }

    @Test
    public void shouldMapRawToLocalETCTrans() throws Exception{
        // when
        ExitLocalETCTrans localTransaction = ExitMapper.INSTANCE.exitRawToExitLocalETC(rawTransaction);

        // then
        assertEquals(localTransaction.getID(), rawTransaction.getID());
    }

    @Test
    public void shouldMapRawToForeignETCTrans(){
        // when
        ExitForeignETCTrans localTransaction = ExitMapper.INSTANCE.exitRawToExitForeignETC(rawTransaction);

        // then
        assertEquals(localTransaction.getID(), rawTransaction.getID());
    }

    @Test
    public void shouldMapRawToLocalOtherTrans(){
        // when
        ExitLocalOtherTrans localTransaction = ExitMapper.INSTANCE.exitRawToExitLocalOther(rawTransaction);

        // then
        assertEquals(localTransaction.getID(), rawTransaction.getID());

    }

    @Test
    public void shouldMapRawToForeignOtherTrans(){
        // when
        ExitForeignOtherTrans localTransaction = ExitMapper.INSTANCE.exitRawToExitForeignOther(rawTransaction);

        // then
        assertEquals(localTransaction.getID(), rawTransaction.getID());

    }

    @Test
    public void shouldMapRawTollChangeTrans(){
        // when
        TollChangeTransactions localTransaction = ExitMapper.INSTANCE.exitRawToTollChangeTrans(rawTransaction);

        // then
        assertEquals(localTransaction.getID(), rawTransaction.getID());

    }




}
