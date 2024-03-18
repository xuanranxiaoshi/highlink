package info.nemoworks.highlink.dataflow.encoder;

import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import org.apache.flink.api.common.serialization.Encoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/11
 * @Copyright：
 */
public class PathEncoder implements Encoder<LinkedList<PathTransaction>> {
    @Override
    public void encode(LinkedList<PathTransaction> pathTransactions, OutputStream outputStream) throws IOException {
        String pathData = getData(pathTransactions);
        PathTransaction pathTransaction = pathTransactions.get(0);
        String passid = pathTransaction.getPASSID();
        String pathOut = "[" + passid + "] 包含" + pathTransactions.size() + "条数据 " + pathData + "\n";
        outputStream.write(pathOut.getBytes());
    }

    public static String getData(LinkedList<PathTransaction> transList) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (int i = 0; i < transList.size(); i++) {
            PathTransaction pathTransaction = transList.get(i);
            if (pathTransaction instanceof EntryRawTransaction entryRawTransaction) {
                stringBuilder.append(" => (" + pathTransaction.peekTime() + ") " + entryRawTransaction.getENTOLLSTATIONHEX());
            } else if (pathTransaction instanceof GantryRawTransaction gantryRawTransaction) {
                stringBuilder.append(" -> (" + pathTransaction.peekTime() + ") " + gantryRawTransaction.getGANTRYHEX());
            } else if (pathTransaction instanceof ExitRawTransaction exitRawTransaction) {
                stringBuilder.append("=> (" + pathTransaction.peekTime() + ") " + exitRawTransaction.getEXTOLLSTATIONHEX());
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
