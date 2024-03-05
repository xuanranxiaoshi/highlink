package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.model.EntryRawTransaction;
import info.nemoworks.highlink.model.PathTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/17
 * @Copyright：
 */
public class PathProcessWindowFunction2 extends ProcessWindowFunction<LinkedList<PathTransaction>, String, String, TimeWindow> {


    public static String getData(LinkedList<PathTransaction> transList) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (int i = 0; i < transList.size(); i++) {
            PathTransaction pathTransaction = transList.get(i);
            if (pathTransaction instanceof EntryRawTransaction entryRawTransaction) {
                stringBuilder.append(" => (" + pathTransaction.getTime() + ") " + entryRawTransaction.getENTOLLSTATIONHEX());
            } else if (pathTransaction instanceof GantryRawTransaction gantryRawTransaction) {
                stringBuilder.append(" -> (" + pathTransaction.getTime() + ") " + gantryRawTransaction.getGANTRYHEX());
            } else if (pathTransaction instanceof ExitRawTransaction exitRawTransaction) {
                stringBuilder.append("=> (" + pathTransaction.getTime() + ") " + exitRawTransaction.getEXTOLLSTATIONHEX());
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * @param s        The key for which this window is evaluated.
     * @param context  The context in which the window is being evaluated.
     * @param iterable The elements in the window being evaluated.
     * @param out      A collector for emitting elements.
     * @throws Exception
     */
    @Override
    public void process(String s,
                        ProcessWindowFunction<LinkedList<PathTransaction>, String, String, TimeWindow>.Context context,
                        Iterable<LinkedList<PathTransaction>> iterable,
                        Collector<String> out) throws Exception {

        long startTs = context.window().getStart();
        long endTs = context.window().getEnd();
        String windowStart = DateFormatUtils.format(startTs, "yyyy-MM-dd HH:mm:ss.SSS");
        String windowEnd = DateFormatUtils.format(endTs, "yyyy-MM-dd HH:mm:ss.SSS");

        Iterator<LinkedList<PathTransaction>> iterator = iterable.iterator();
        LinkedList<PathTransaction> transList = iterator.next();

        String pathData = getData(transList);
        String pathOut = "[" + windowStart + "|" + windowEnd + "] " + s + " 包含" + transList.size() + "条数据 " + pathData;

        out.collect(pathOut);
    }
}
/**
 * [2023-02-23 14:23:29|2023-02-23 14:23:29] 89827872878 包含5条数据 [ => (2023-02-23 14:23:29) 7827AB -> (2023-02-23 14:23:29) 7827AB ]
 */