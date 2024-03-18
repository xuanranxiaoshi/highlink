package info.nemoworks.highlink.dataflow.encoder;

import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import org.apache.flink.api.common.serialization.Encoder;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/11
 * @Copyright：
 */
public class ExitLocalOthersEncoder implements Encoder<ExitLocalOtherTrans> {
    @Override
    public void encode(ExitLocalOtherTrans exitLocalOtherTrans, OutputStream outputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("passID: ").append(exitLocalOtherTrans.getPASSID()).append(" {\n")
                .append(" proTime: ").append(exitLocalOtherTrans.getPROTIME()).append("\n")
                .append("chargeUnitesStr: ").append(exitLocalOtherTrans.getSPLITOWNERGROUP()).append("\n")
                .append("feeStr: ").append(exitLocalOtherTrans.getSPLITOWNERFEEGROUP()).append("\n")
                .append("payFeeStr: ").append(exitLocalOtherTrans.getSPLITOWNERPAYFEEGROUP()).append("\n")
                .append("discountStr: ").append(exitLocalOtherTrans.getSPLITOWNERDISFEEGROUP()).append("\n")
                .append("}\n");

        outputStream.write(stringBuilder.toString().getBytes());
    }
}
