package info.nemoworks.highlink.dataflow.encoder;

import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import org.apache.flink.api.common.serialization.Encoder;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/12
 * @Copyright：
 */
public class LatePathEncoder implements Encoder<PathTransaction> {
    @Override
    public void encode(PathTransaction pathTransaction, OutputStream outputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(pathTransaction.getPASSID()).append("]").append(pathTransaction.peekTime()).append("\n");
        outputStream.write(stringBuilder.toString().getBytes());
    }
}
