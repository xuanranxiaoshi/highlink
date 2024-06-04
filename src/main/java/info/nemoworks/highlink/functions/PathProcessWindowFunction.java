package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/17
 * @Copyright：
 */
public class PathProcessWindowFunction extends ProcessWindowFunction<List<PathTransaction>, List<PathTransaction>, String, TimeWindow> {
    @Override
    public void process(String s,
                        ProcessWindowFunction<List<PathTransaction>, List<PathTransaction>, String, TimeWindow>.Context context,
                        Iterable<List<PathTransaction>> iterable,
                        Collector<List<PathTransaction>> collector) throws Exception {

        long startTs = context.window().getStart();
        long endTs = context.window().getEnd();
        String windowStart = DateFormatUtils.format(startTs, "yyyy-MM-dd HH:mm:ss.SSS");
        String windowEnd = DateFormatUtils.format(endTs, "yyyy-MM-dd HH:mm:ss.SSS");

        Iterator<List<PathTransaction>> iterator = iterable.iterator();
        List<PathTransaction> transList = iterator.next();

        String pathData = getData(transList);
        String pathOut = "cur: " +  DateFormatUtils.format(context.currentWatermark(), "yyyy-MM-dd HH:mm:ss.SSS") +
                "[" + windowStart + "|" + windowEnd + "] " + s + " 包含" + transList.size() + "条数据 " + pathData;

        System.out.println(pathOut);

        collector.collect(transList);
    }



    public static String getData(List<PathTransaction> transList){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (int i = 0; i < transList.size(); i++) {
            PathTransaction pathTransaction = transList.get(i);
            if( pathTransaction instanceof EntryRawTransaction entryRawTransaction){
                stringBuilder.append(" => (" + pathTransaction.peekTime() +") " + entryRawTransaction.getENTOLLSTATIONHEX());
            }else if (pathTransaction instanceof GantryRawTransaction gantryRawTransaction){
                stringBuilder.append(" -> (" + pathTransaction.peekTime() +") " + gantryRawTransaction.getGANTRYHEX());
            }else if (pathTransaction instanceof ExitRawTransaction exitRawTransaction){
                stringBuilder.append(" => (" + pathTransaction.peekTime() +") " + exitRawTransaction.getEXTOLLSTATIONHEX());
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
/**
 * [2023-02-23 14:23:29|2023-02-23 14:23:29] 89827872878 包含5条数据 [ => (2023-02-23 14:23:29) 7827AB -> (2023-02-23 14:23:29) 7827AB ]
 */