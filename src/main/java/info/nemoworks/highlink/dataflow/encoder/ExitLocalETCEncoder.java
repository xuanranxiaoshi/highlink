package info.nemoworks.highlink.dataflow.encoder;

import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import org.apache.flink.api.common.serialization.Encoder;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/11
 * @Copyright：
 */
public class ExitLocalETCEncoder implements Encoder<ExitLocalETCTrans> {
    @Override
    public void encode(ExitLocalETCTrans exitLocalETCTrans, OutputStream outputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("passID: ").append(exitLocalETCTrans.getPASSID()).append(" {\n")
                .append(" proTime: ").append(exitLocalETCTrans.getPROTIME()).append("\n")
                .append("chargeUnitesStr: ").append(exitLocalETCTrans.getSPLITOWNERGROUP()).append("\n")
                .append("feeStr: ").append(exitLocalETCTrans.getSPLITOWNERFEEGROUP()).append("\n")
                .append("payFeeStr: ").append(exitLocalETCTrans.getSPLITOWNERPAYFEEGROUP()).append("\n")
                .append("discountStr: ").append(exitLocalETCTrans.getSPLITOWNERDISFEEGROUP()).append("\n")
                .append("}\n");

        outputStream.write(stringBuilder.toString().getBytes());
    }
}
