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

    TollChangeTransactions exdRawToTollChangeTrans(ExtendRawTransaction extendRawTransaction);

    ExdLocalTransaction exdRawToExtLocalTrans(ExtendRawTransaction extendRawTransaction);

    ExdForeignGasTransaction exdRawToExtForeignGasTrans(ExtendRawTransaction extendRawTransaction);

    ExdForeignParkTransaction exdRawToExtForeignParkTrans(ExtendRawTransaction extendRawTransaction);

   ExdForeignMunicipalTransaction exdRawToExtForeignMunicipalTrans(ExtendRawTransaction extendRawTransaction);
}
