package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.model.PathTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
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

        System.out.println("passId = " + s + "的窗口[" + windowStart + "," + windowEnd + "]包含" + transList.size() + "条数据===>" + transList.toString());

        collector.collect(transList);
    }
}
