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

    TollChangeTransactions exdRawToTollChangeTrans(ParkTransWasteRec extendRawTransaction);

    ExdLocalTransaction exdRawToExtLocalTrans(ParkTransWasteRec extendRawTransaction);

    ExdForeignGasTransaction exdRawToExtForeignGasTrans(ParkTransWasteRec extendRawTransaction);

    ExdForeignParkTransaction exdRawToExtForeignParkTrans(ParkTransWasteRec extendRawTransaction);

   ExdForeignMunicipalTransaction exdRawToExtForeignMunicipalTrans(ParkTransWasteRec extendRawTransaction);
}
