package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.model.RawTransactionFactory;
import info.nemoworks.highlink.model.extendTransaction.ExdForeignGasTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExdLocalTransaction;
import info.nemoworks.highlink.model.extendTransaction.ExtendRawTransaction;
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
    @Test
    public void shouldMapRawToLocalTrans() throws Exception {
        // given
        JsonNode extendRaw = new ObjectMapper().readTree(ExtensionMapperTest.class.getResourceAsStream("/extendraw.json"));

        ExtendRawTransaction rawTransaction = (ExtendRawTransaction) RawTransactionFactory.fromJson(extendRaw);

        // when
        ExdLocalTransaction localTransaction = ExtensionMapper.INSTANCE.extRawToExtLocalTrans(rawTransaction);

        // then
        assertEquals(localTransaction.getID(), rawTransaction.getID());
    }

    @Test
    public void shouldMapRawToForeignGasTrans() throws Exception {
        // given
        JsonNode extendRaw = new ObjectMapper().readTree(ExtensionMapperTest.class.getResourceAsStream("/extendraw.json"));

        ExtendRawTransaction rawTransaction = (ExtendRawTransaction) RawTransactionFactory.fromJson(extendRaw);

        // when
        ExdForeignGasTransaction gasTransaction = ExtensionMapper.INSTANCE.extRawToExtForeignGasTrans(rawTransaction);

        // then
        assertEquals(gasTransaction.getID(), rawTransaction.getID());
    }

}
