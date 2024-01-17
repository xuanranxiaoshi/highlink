package info.nemoworks.highlink.functions;

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
public class CpcProcessWindowFunction extends ProcessWindowFunction<LinkedList<GantryCpcTransaction>, LinkedList<GantryCpcTransaction>, String, TimeWindow> {
    @Override
    public void process(String s,
                        ProcessWindowFunction<LinkedList<GantryCpcTransaction>,
                                LinkedList<GantryCpcTransaction>, String, TimeWindow>.Context context,
                        Iterable<LinkedList<GantryCpcTransaction>> iterable,
                        Collector<LinkedList<GantryCpcTransaction>> collector) throws Exception {
        long startTs = context.window().getStart();
        long endTs = context.window().getEnd();
        String windowStart = DateFormatUtils.format(startTs, "yyyy-MM-dd HH:mm:ss.SSS");
        String windowEnd = DateFormatUtils.format(endTs, "yyyy-MM-dd HH:mm:ss.SSS");
        long count = iterable.spliterator().estimateSize();
        System.out.println("key=" + s + "的窗口[" + windowStart + "," + windowEnd + "]包含" + count + "条数据===>" + iterable.toString());
        Iterator<LinkedList<GantryCpcTransaction>> iterator = iterable.iterator();
        collector.collect(iterator.next());
    }
}
