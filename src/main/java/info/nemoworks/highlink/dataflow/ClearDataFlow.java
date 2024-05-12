package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.model.clearTransaction.ClearResult;
import info.nemoworks.highlink.model.clearTransaction.ETCClearResult;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.splitTransaction.*;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

import java.util.List;

/**
 * @description: 清分业务处理，清分对象为拆分结果
 * @author：jimi
 * @date: 2024/5/12
 * @Copyright：
 */
public class ClearDataFlow {

    /**
     * 清分业务处理:
     *    1. 将传入的每条拆分结果转化为多条清分明细数据
     *    2. 将清分明细数据存入 clickhouse 数据仓库
     * @param splitResultDataStream
     */
    public void flow(DataStream<SplitResult> splitResultDataStream){

        // 1. 将传入的每条拆分结果转化为多条清分明细数据
        // 如何将 6 种不同的拆分结果转化为 3 种清分明细数据
        SingleOutputStreamOperator<ETCClearResult> etcClearResultStream = splitResultDataStream.process(new ProcessFunction<SplitResult, ETCClearResult>() {
            @Override
            public void processElement(SplitResult value, ProcessFunction<SplitResult, ETCClearResult>.Context ctx, Collector<ETCClearResult> out) throws Exception {
                if(value instanceof ETCSplitResultGantry etcSplitResultGantry){

                }else if (value instanceof ETCSplitResultExit etcSplitResultExit){

                }else if (value instanceof OtherSplitResultExit otherSplitResultExit){

                }else if (value instanceof OtherSplitResultGantry){

                }else if (value instanceof ExitLocalOtherTrans exitLocalOtherTrans){

                }else if (value instanceof ExitLocalETCTrans exitLocalETCTrans){

                }
            }
        });

        // 2. 将清分明细数据存入 clickhouse 数据仓库

    }
}
