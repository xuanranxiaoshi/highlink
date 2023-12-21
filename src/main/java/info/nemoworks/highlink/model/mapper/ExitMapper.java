package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.model.ExitTransaction.*;
import info.nemoworks.highlink.model.TollChangeTransactions;
import info.nemoworks.highlink.model.extendTransaction.ExtendRawTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExitMapper {
    ExitMapper INSTANCE = Mappers.getMapper(ExitMapper.class);

    TollChangeTransactions exitRawToTollChangeTrans(ExitRawTransaction exitRawTransaction);

    ExitForeignOtherTrans exitRawToExitForeignOther(ExitRawTransaction exitRawTransaction);

    ExitLocalOtherTrans exitRawToExitLocalOther(ExitRawTransaction exitRawTransaction);

    ExitLocalETCTrans exitRawToExitLocalETC(ExitRawTransaction exitRawTransaction);

    ExitForeignETCTrans exitRawToExitForeignETC(ExitRawTransaction exitRawTransaction);

}
