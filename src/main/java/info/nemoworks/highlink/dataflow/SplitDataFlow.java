package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.dataflow.encoder.ExitLocalETCEncoder;
import info.nemoworks.highlink.dataflow.encoder.ExitLocalOthersEncoder;
import info.nemoworks.highlink.dataflow.utils.utils;
import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.pathTransaction.SingleProvincePathTrans;
import org.apache.flink.streaming.api.datastream.DataStream;
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
    public static void flow(DataStream<LinkedList<PathTransaction>> aggregatePathStream){

        final OutputTag<SingleProvincePathTrans> singleProvinceOutputTag = new OutputTag<SingleProvincePathTrans>("singleProvince") {};

        // 1. 不同拆分业务分流
        SingleOutputStreamOperator<SingleProvincePathTrans> singleProvinceSplitStream = aggregatePathStream.process(
                new ProcessFunction<LinkedList<PathTransaction>, SingleProvincePathTrans>() {
                    @Override
                    public void processElement(LinkedList<PathTransaction> pathList,
                                               ProcessFunction<LinkedList<PathTransaction>, SingleProvincePathTrans>.Context ctx,
                                               Collector<SingleProvincePathTrans> out) throws Exception {
                        PathTransaction exitTrans = pathList.getLast();    // 取出路径的最后一条数据
                        if (exitTrans instanceof ExitRawTransaction exitRawTransaction) {
                            // todo: 完善单省判断逻辑
                            if (exitRawTransaction.peekLocal()) {   // 单省拆分流
                                out.collect(new SingleProvincePathTrans(pathList));
                            } else {  // 跨省拆分流
                                System.out.println("[Error] 外省数据");
                            }
                        } else {
                            System.out.println("[Error] 路径最后一条数据不为 Exit 数据(异常/出口为外省), passID = "+ pathList.get(0).getPASSID());
                        }
                    }
                }
        );


        // 2. 单省拆分逻辑
        final OutputTag<ExitLocalOtherTrans> exitLocalOtherOutputTag = new OutputTag<ExitLocalOtherTrans>("exitLocalOtherOutputTag") {};

        SingleOutputStreamOperator<ExitLocalETCTrans> exitLocalETCTransStream = singleProvinceSplitStream.process(new ProcessFunction<SingleProvincePathTrans, ExitLocalETCTrans>() {
            @Override
            public void processElement(SingleProvincePathTrans value,
                                       ProcessFunction<SingleProvincePathTrans, ExitLocalETCTrans>.Context ctx,
                                       Collector<ExitLocalETCTrans> out) throws Exception {
                value.splitCharge();    // 进行单省拆分
                HighwayTransaction exitTrans = value.getUpdateRes();
                if (exitTrans instanceof ExitLocalETCTrans exitLocalETCTrans) {
                    out.collect(exitLocalETCTrans); // 单省 ETC
                } else if (exitTrans instanceof ExitLocalOtherTrans exitLocalOtherTrans) {
                    ctx.output(exitLocalOtherOutputTag, exitLocalOtherTrans);   // 单省 CPC
                }
            }
        });

        SideOutputDataStream<ExitLocalOtherTrans> exitLocalOtherTransStream = exitLocalETCTransStream.getSideOutput(exitLocalOtherOutputTag);

        // 重写更新数据库文件
        utils.addFileSinkToStream(exitLocalETCTransStream, "exit_local_etc", new ExitLocalETCEncoder());
        utils.addFileSinkToStream(exitLocalOtherTransStream, "exit_local_other", new ExitLocalOthersEncoder());

    }


}
