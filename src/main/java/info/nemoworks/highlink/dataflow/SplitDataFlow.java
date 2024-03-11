package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.dataflow.utils.utils;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.pathTransaction.SingleProvincePathTrans;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * @author：jimi 拆分子系统
 * @date: 2024/3/5
 * @Copyright：
 */
public class SplitDataFlow {
    public static Logger logger = Logger.getLogger(SplitDataFlow.class.getName());
    public static void flow(SingleOutputStreamOperator<LinkedList<PathTransaction>> aggregatePathStream){

        final OutputTag<SingleProvincePathTrans> singleProvinceOutputTag = new OutputTag<SingleProvincePathTrans>("singleProvince") {};

        // 1. 不同拆分业务分流
        SingleOutputStreamOperator<LinkedList<PathTransaction>> process = aggregatePathStream.process(
                new ProcessFunction<LinkedList<PathTransaction>, LinkedList<PathTransaction>>() {
                    @Override
                    public void processElement(LinkedList<PathTransaction> pathList,
                                               ProcessFunction<LinkedList<PathTransaction>, LinkedList<PathTransaction>>.Context ctx,
                                               Collector<LinkedList<PathTransaction>> out) throws Exception {
                        PathTransaction exitTrans = pathList.pollLast();    // 取出路径的最后一条数据
                        if (exitTrans instanceof ExitRawTransaction exitRawTransaction) {
                            // todo: 完善单省判断逻辑
                            if (exitRawTransaction.isLocal()) {   // 单省拆分流
                                ctx.output(singleProvinceOutputTag, new SingleProvincePathTrans(pathList));
                            } else {  // 跨省拆分流

                            }
                        } else {
                            logger.warning("路径最后一条数据不为 Exit 数据(异常/出口为外省)");
                        }
                    }
                }
        );


        // 2. 单省拆分逻辑
        SideOutputDataStream<SingleProvincePathTrans> singleProvinceSplitStream = process.getSideOutput(singleProvinceOutputTag);
        final OutputTag<ExitLocalOtherTrans> exitLocalOtherOutputTag = new OutputTag<ExitLocalOtherTrans>("exitLocalOtherOutputTag") {};

        SingleOutputStreamOperator<ExitLocalETCTrans> exitLocalETCTransStream = singleProvinceSplitStream.process(new ProcessFunction<SingleProvincePathTrans, ExitLocalETCTrans>() {
            @Override
            public void processElement(SingleProvincePathTrans value,
                                       ProcessFunction<SingleProvincePathTrans, ExitLocalETCTrans>.Context ctx,
                                       Collector<ExitLocalETCTrans> out) throws Exception {
                value.splitCharge();    // 进行单省拆分
                HighwayTransaction exitTrans = value.getUpdateRes();
                if (exitTrans instanceof ExitLocalETCTrans exitLocalETCTrans) {
                    out.collect(exitLocalETCTrans);
                } else if (exitTrans instanceof ExitLocalOtherTrans exitLocalOtherTrans) {
                    ctx.output(exitLocalOtherOutputTag, exitLocalOtherTrans);
                }
            }
        });

        SideOutputDataStream<ExitLocalOtherTrans> exitLocalOtherTransStream = exitLocalETCTransStream.getSideOutput(exitLocalOtherOutputTag);

        // 重写更新数据库文件
        // fixme: 具体的更新策略
        utils.addSinkToStream(exitLocalETCTransStream, ExitLocalETCTrans.class, "ExitLocalETCTrans");
        utils.addSinkToStream(exitLocalOtherTransStream, ExitLocalOtherTrans.class, "ExitLocalOtherTrans");

    }


}
