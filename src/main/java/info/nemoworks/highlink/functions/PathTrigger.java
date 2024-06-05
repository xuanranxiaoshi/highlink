package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/17
 * @Copyright：
 */
public class PathTrigger extends Trigger<PathTransaction, TimeWindow> {

    private String passId;

    @Override
    public TriggerResult onElement(PathTransaction element, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
        // 超时或者 exit 数据到达，则窗口结束
        if (window.maxTimestamp() <= timestamp) {
            return TriggerResult.FIRE;
        }
//        else if (element instanceof ExitRawTransaction) {
//            passId = element.getPASSID();
//            // 提前触发, 清空数据, 否则定时器会再次触发极计算，导致重复数据
//            return TriggerResult.FIRE_AND_PURGE;
//        }
        // 更新超时时间
        else {
            ctx.registerEventTimeTimer(window.maxTimestamp());
            return TriggerResult.CONTINUE;
        }
    }

    @Override
    public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        if (time == window.maxTimestamp()) {
            return TriggerResult.FIRE;
        } else {
            return TriggerResult.CONTINUE;
        }
    }

    @Override
    public boolean canMerge() {
        return true;
    }

    @Override
    public void onMerge(TimeWindow window, OnMergeContext ctx) {
        // only register a timer if the watermark is not yet past the end of the merged window
        // this is in line with the logic in onElement(). If the watermark is past the end of
        // the window onElement() will fire and setting a timer here would fire the window twice.
        long windowMaxTimestamp = window.maxTimestamp();
        if (windowMaxTimestamp > ctx.getCurrentWatermark()) {
            ctx.registerEventTimeTimer(windowMaxTimestamp);
        }
    }

    @Override
    public void clear(TimeWindow window, TriggerContext ctx) throws Exception {
        ctx.deleteEventTimeTimer(window.maxTimestamp());
    }

    @Override
    public String toString() {
        return "PathTrigger()";
    }


}
