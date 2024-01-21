package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.model.EntryRawTransaction;
import info.nemoworks.highlink.model.ExitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.PathTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
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
public class PathProcessWindowFunction extends ProcessWindowFunction<LinkedList<PathTransaction>, LinkedList<PathTransaction>, String, TimeWindow> {
    @Override
    public void process(String s,
                        ProcessWindowFunction<LinkedList<PathTransaction>, LinkedList<PathTransaction>, String, TimeWindow>.Context context,
                        Iterable<LinkedList<PathTransaction>> iterable,
                        Collector<LinkedList<PathTransaction>> collector) throws Exception {
        long startTs = context.window().getStart();
        long endTs = context.window().getEnd();
        String windowStart = DateFormatUtils.format(startTs, "yyyy-MM-dd HH:mm:ss.SSS");
        String windowEnd = DateFormatUtils.format(endTs, "yyyy-MM-dd HH:mm:ss.SSS");

        Iterator<LinkedList<PathTransaction>> iterator = iterable.iterator();
        LinkedList<PathTransaction> transList = iterator.next();

        System.out.println("passId = " + s + "的窗口[" + windowStart + "," + windowEnd + "]包含" + transList.size() + "条数据===>" + getData(transList));

        collector.collect(transList);
    }


    public static String getData(LinkedList<PathTransaction> transList){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (int i = 0; i < transList.size(); i++) {
            PathTransaction pathTransaction = transList.get(i);
            if( pathTransaction instanceof EntryRawTransaction){
                stringBuilder.append("EntryRaw { psID: " + pathTransaction.getPASSID() + ", enTime: " + pathTransaction.getENTIME() +" } ");
            }else if (pathTransaction instanceof GantryRawTransaction){
                stringBuilder.append("GantryRaw { psID: " + pathTransaction.getPASSID() + ", enTime: " + pathTransaction.getENTIME() +" } ");
            }else if (pathTransaction instanceof ExitRawTransaction){
                stringBuilder.append("ExitRaw { psID: " + pathTransaction.getPASSID() + ", enTime: " + pathTransaction.getENTIME() +" } ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
