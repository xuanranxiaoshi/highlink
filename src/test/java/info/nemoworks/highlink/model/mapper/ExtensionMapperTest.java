package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.model.RawTransactionFactory;
import info.nemoworks.highlink.model.extendTransaction.*;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @description:
 * @author：jimi
 * @date: 2023/12/20
 * @Copyright：
 */
public class ExtensionMapperTest {
    // given
    JsonNode extendRaw = new ObjectMapper().readTree(ExtensionMapperTest.class.getResourceAsStream("/extendraw.json"));

    ParkTransWasteRec rawTransaction = (ParkTransWasteRec) RawTransactionFactory.fromJson(extendRaw);

    public ExtensionMapperTest() throws Exception {
    }

    @Test
    public void shouldMapRawToLocalTrans() throws Exception {
        // when
        ExdLocalTransaction localTransaction = ExtensionMapper.INSTANCE.exdRawToExtLocalTrans(rawTransaction);

        // then
        assertEquals(localTransaction.getID(), rawTransaction.getID());
    }

    @Test
    public void shouldMapRawToForeignGasTrans() throws Exception {
        // when
        ExdForeignGasTransaction gasTransaction = ExtensionMapper.INSTANCE.exdRawToExtForeignGasTrans(rawTransaction);

        // then
        assertEquals(gasTransaction.getID(), rawTransaction.getID());
    }

    @Test
    public void shouldMapRawToForeignParkTrans() throws Exception {
        // when
        ExdForeignParkTransaction parkTransaction = ExtensionMapper.INSTANCE.exdRawToExtForeignParkTrans(rawTransaction);

        // then
        assertEquals(parkTransaction.getID(), rawTransaction.getID());
    }

    @Test
    public void shouldMapRawToForeignMunicipalTrans() throws Exception {
        // when
        ExdForeignMunicipalTransaction parkTransaction = ExtensionMapper.INSTANCE.exdRawToExtForeignMunicipalTrans(rawTransaction);

        // then
        assertEquals(parkTransaction.getID(), rawTransaction.getID());
    }


}
