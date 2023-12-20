package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.model.TollChangeTransactions;
import info.nemoworks.highlink.model.extendTransaction.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author：jimi
 * @date: 2023/12/20
 * @Copyright：
 */
@Mapper
public interface ExtensionMapper {
    ExtensionMapper INSTANCE = Mappers.getMapper(ExtensionMapper.class);

    TollChangeTransactions extRawToTollChangeTrans(ExtendRawTransaction extendRawTransaction);

    ExtLocalTransaction extRawToExtLocalTrans(ExtendRawTransaction extendRawTransaction);

    ExtForeignGasTransaction extRawToExtForeignGasTrans(ExtendRawTransaction extendRawTransaction);

    ExtForeignParkTransaction extRawToExtForeignParkTrans(ExtendRawTransaction extendRawTransaction);

   ExtForeignMunicipalTransaction extRawToExtForeignMunicipalTrans(ExtendRawTransaction extendRawTransaction);
}
