package info.nemoworks.highlink.dataflow;

import info.nemoworks.highlink.model.clearTransaction.CashClearResult;
import info.nemoworks.highlink.model.clearTransaction.ClearResult;
import info.nemoworks.highlink.model.clearTransaction.ETCClearResult;
import info.nemoworks.highlink.model.clearTransaction.ExpandClearResult;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.splitTransaction.*;
import info.nemoworks.highlink.utils.SinkUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @description: 清分业务处理，清分对象为拆分结果
 * @author：jimi
 * @date: 2024/5/12
 * @Copyright：
 */
public class ClearDataFlow {

    /**
     * 清分业务处理:
     * 1. 将传入的每条拆分结果转化为多条清分明细数据
     * 2. 将清分明细数据存入 clickhouse 数据仓库
     *
     * @param splitResultDataStream
     */
    public static void flow(DataStream<SplitResult> splitResultDataStream) {

        final OutputTag<CashClearResult> cashClearResultOutputTag = new OutputTag<CashClearResult>("cashClearResultOutputTag") {};
        final OutputTag<ExpandClearResult> expandClearResultOutputTag = new OutputTag<ExpandClearResult>("expandClearResultOutputTag") {};

        // 1. 将传入的每条拆分结果转化为多条清分明细数据
        // 如何将 6 种不同的拆分结果转化为 3 种清分明细数据
        SingleOutputStreamOperator<ETCClearResult> etcClearResultStream = splitResultDataStream.process(new ProcessFunction<SplitResult, ETCClearResult>() {
            @Override
            public void processElement(SplitResult value, ProcessFunction<SplitResult, ETCClearResult>.Context ctx, Collector<ETCClearResult> out) throws Exception {
                if (value instanceof ETCSplitResultGantry etcSplitResultGantry) {
                    ETCClearResult etcClearResultTemplate = new ETCClearResult();
                    // 设置基础的主键属性
                    etcClearResultTemplate.setPAYCARDTYPE(etcSplitResultGantry.getETCCARDTYPE());
                    // 设置为出口时间
                    etcClearResultTemplate.setLDATE(dateStr2Int(etcSplitResultGantry.getEXTIME()));

                    etcClearResultTemplate.setCLEARDATE(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                    etcClearResultTemplate.setMULTIPROVINCE("1");
                    etcClearResultTemplate.setCLEARTYPE("3");
                    etcClearResultTemplate.setLASTTIME(new Timestamp(System.currentTimeMillis()));
                    // 根据收费单元拆分为多条明细数据
                    String splitownergroup = etcSplitResultGantry.getSPLITOWNERGROUP();
                    String splitownerfeegroup = etcSplitResultGantry.getSPLITOWNERFEEGROUP();
                    String splitownerpayfeegroup = etcSplitResultGantry.getSPLITOWNERPAYFEEGROUP();
                    String splitownerdisfeegroup = etcSplitResultGantry.getSPLITOWNERDISFEEGROUP();
                    System.out.println("[Clear] ETCSplitResultGantry");
                    LinkedList<ETCClearResult> clearResults =
                            split2ClearDetials(splitownergroup, splitownerfeegroup, splitownerpayfeegroup, splitownerdisfeegroup, etcClearResultTemplate);
                    // 分流
                    for (ETCClearResult clearResult : clearResults){
                        System.out.println("[Clear] TOLLINTERVALID: " + clearResult.getTOLLINTERVALID());
                        out.collect(clearResult);
                    }
                }
                else if (value instanceof ETCSplitResultExit etcSplitResultExit) {
                    ETCClearResult etcClearResultTemplate = new ETCClearResult();
                    // 设置基础的主键属性
                    etcClearResultTemplate.setPAYCARDTYPE(etcSplitResultExit.getETCCARDTYPE());
                    etcClearResultTemplate.setLDATE(dateStr2Int(etcSplitResultExit.getEXTIME()));
                    etcClearResultTemplate.setCLEARDATE(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                    etcClearResultTemplate.setMULTIPROVINCE("1");
                    etcClearResultTemplate.setCLEARTYPE("2");
                    etcClearResultTemplate.setLASTTIME(new Timestamp(System.currentTimeMillis()));
                    // 根据收费单元拆分为多条明细数据
                    String splitownergroup = etcSplitResultExit.getSPLITOWNERGROUP();
                    String splitownerfeegroup = etcSplitResultExit.getSPLITOWNERFEEGROUP();
                    String splitownerpayfeegroup = etcSplitResultExit.getSPLITOWNERPAYFEEGROUP();
                    String splitownerdisfeegroup = etcSplitResultExit.getSPLITOWNERDISFEEGROUP();
                    System.out.println("[Clear] ETCSplitResultExit");
                    LinkedList<ETCClearResult> clearResults =
                            split2ClearDetials(splitownergroup, splitownerfeegroup, splitownerpayfeegroup, splitownerdisfeegroup, etcClearResultTemplate);
                    // 分流
                    for (ETCClearResult clearResult : clearResults){
                        System.out.println("[Clear] TOLLINTERVALID: " + clearResult.getTOLLINTERVALID());
                        out.collect(clearResult);
                    }
                }
                else if (value instanceof OtherSplitResultExit otherSplitResultExit) {
                    CashClearResult cashClearResultTemplate = new CashClearResult();
                    // 设置基础的主键属性
                    cashClearResultTemplate.setPAYCARDTYPE(otherSplitResultExit.getETCCARDTYPE());
                    cashClearResultTemplate.setLDATE(dateStr2Int(otherSplitResultExit.getEXTIME()));
                    cashClearResultTemplate.setCLEARDATE(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                    cashClearResultTemplate.setMULTIPROVINCE("1");
                    cashClearResultTemplate.setCLEARTYPE("5");
                    cashClearResultTemplate.setLASTTIME(new Timestamp(System.currentTimeMillis()));
                    // 根据收费单元拆分为多条明细数据
                    String splitownergroup = otherSplitResultExit.getSPLITOWNERGROUP();
                    String splitownerfeegroup = otherSplitResultExit.getSPLITOWNERFEEGROUP();
                    String splitownerpayfeegroup = otherSplitResultExit.getSPLITOWNERPAYFEEGROUP();
                    String splitownerdisfeegroup = otherSplitResultExit.getSPLITOWNERDISFEEGROUP();
                    System.out.println("[Clear] otherSplitResultExit");
                    LinkedList<CashClearResult> clearResults =
                            split2ClearDetials(splitownergroup, splitownerfeegroup, splitownerpayfeegroup, splitownerdisfeegroup, cashClearResultTemplate);
                    // 分流
                    for (CashClearResult clearResult : clearResults){
                        ctx.output(cashClearResultOutputTag, clearResult);
                    }
                }
                else if (value instanceof OtherSplitResultGantry otherSplitResultGantry) {
                    CashClearResult cashClearResultTemplate = new CashClearResult();
                    // 设置基础的主键属性
                    cashClearResultTemplate.setPAYCARDTYPE(otherSplitResultGantry.getETCCARDTYPE());
                    cashClearResultTemplate.setLDATE(dateStr2Int(otherSplitResultGantry.getEXTIME()));
                    cashClearResultTemplate.setCLEARDATE(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                    cashClearResultTemplate.setMULTIPROVINCE("1");
                    cashClearResultTemplate.setCLEARTYPE("6");
                    cashClearResultTemplate.setLASTTIME(new Timestamp(System.currentTimeMillis()));
                    // 根据收费单元拆分为多条明细数据
                    String splitownergroup = otherSplitResultGantry.getSPLITOWNERGROUP();
                    String splitownerfeegroup = otherSplitResultGantry.getSPLITOWNERFEEGROUP();
                    String splitownerpayfeegroup = otherSplitResultGantry.getSPLITOWNERPAYFEEGROUP();
                    String splitownerdisfeegroup = otherSplitResultGantry.getSPLITOWNERDISFEEGROUP();
                    System.out.println("[Clear] OtherSplitResultGantry");
                    LinkedList<CashClearResult> clearResults =
                            split2ClearDetials(splitownergroup, splitownerfeegroup, splitownerpayfeegroup, splitownerdisfeegroup, cashClearResultTemplate);
                    // 分流
                    for (CashClearResult clearResult : clearResults){
                        ctx.output(cashClearResultOutputTag, clearResult);
                    }
                }
                else if (value instanceof ExitLocalOtherTrans exitLocalOtherTrans) {
                    CashClearResult cashClearResultTemplate = new CashClearResult();
                    // 设置基础的主键属性
                    cashClearResultTemplate.setPAYCARDTYPE(exitLocalOtherTrans.getETCCARDTYPE());
                    cashClearResultTemplate.setLDATE(dateStr2Int(exitLocalOtherTrans.getEXTIME()));
                    cashClearResultTemplate.setCLEARDATE(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                    cashClearResultTemplate.setMULTIPROVINCE("1");
                    cashClearResultTemplate.setCLEARTYPE("6");
                    cashClearResultTemplate.setLASTTIME(new Timestamp(System.currentTimeMillis()));
                    // 根据收费单元拆分为多条明细数据
                    String splitownergroup = exitLocalOtherTrans.getSPLITOWNERGROUP();
                    String splitownerfeegroup = exitLocalOtherTrans.getSPLITOWNERFEEGROUP();
                    String splitownerpayfeegroup = exitLocalOtherTrans.getSPLITOWNERPAYFEEGROUP();
                    String splitownerdisfeegroup = exitLocalOtherTrans.getSPLITOWNERDISFEEGROUP();
                    System.out.println("[Clear] ExitLocalOtherTrans");
                    LinkedList<CashClearResult> clearResults =
                            split2ClearDetials(splitownergroup, splitownerfeegroup, splitownerpayfeegroup, splitownerdisfeegroup, cashClearResultTemplate);
                    // 分流
                    for (CashClearResult clearResult : clearResults){
                        ctx.output(cashClearResultOutputTag, clearResult);
                    }
                }
                else if (value instanceof ExitLocalETCTrans exitLocalETCTrans) {
                    ETCClearResult etcClearResultTemplate = new ETCClearResult();
                    // 设置基础的主键属性
                    etcClearResultTemplate.setPAYCARDTYPE(String.valueOf(exitLocalETCTrans.getETCCARDTYPE()));
                    etcClearResultTemplate.setLDATE(dateStr2Int(exitLocalETCTrans.getEXTIME()));
                    etcClearResultTemplate.setCLEARDATE(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                    etcClearResultTemplate.setMULTIPROVINCE("1");
                    etcClearResultTemplate.setCLEARTYPE("1");
                    etcClearResultTemplate.setLASTTIME(new Timestamp(System.currentTimeMillis()));
                    // 根据收费单元拆分为多条明细数据
                    String splitownergroup = exitLocalETCTrans.getSPLITOWNERGROUP();
                    String splitownerfeegroup = exitLocalETCTrans.getSPLITOWNERFEEGROUP();
                    String splitownerpayfeegroup = exitLocalETCTrans.getSPLITOWNERPAYFEEGROUP();
                    String splitownerdisfeegroup = exitLocalETCTrans.getSPLITOWNERDISFEEGROUP();
                    System.out.println("[Clear] ExitLocalETCTrans");
                    LinkedList<ETCClearResult> clearResults =
                            split2ClearDetials(splitownergroup, splitownerfeegroup, splitownerpayfeegroup, splitownerdisfeegroup, etcClearResultTemplate);
                    // 分流
                    for (ETCClearResult clearResult : clearResults){
                        System.out.println("[Clear] TOLLINTERVALID: " + clearResult.getTOLLINTERVALID());
                        out.collect(clearResult);
                    }
                }
            }
        });

        // 2. 将清分明细数据存入 clickhouse 数据仓库
        SideOutputDataStream<CashClearResult> cashClearResultStream = etcClearResultStream.getSideOutput(cashClearResultOutputTag);

        SinkUtils.addStream2CH(etcClearResultStream, ETCClearResult.class, "ETC清分结果");
        SinkUtils.addStream2CH(cashClearResultStream, CashClearResult.class, "现金清分结果");
    }

    private static LinkedList<ETCClearResult> split2ClearDetials(String splitownergroup,
                                                       String splitownerfeegroup,
                                                       String splitownerpayfeegroup,
                                                       String splitownerdisfeegroup,
                                                       ETCClearResult resultTemplate) {
        String[] disGroup = splitownerdisfeegroup.split("\\|");
        String[] feeGroup = splitownerfeegroup.split("\\|");
        String[] payFeeGroup = splitownerpayfeegroup.split("\\|");
        String[] ownerGroup = splitownergroup.split("\\|");

        // 检查数组长度是否相同
        if (disGroup.length != feeGroup.length || disGroup.length != payFeeGroup.length || disGroup.length != ownerGroup.length) {
            System.out.println("[Clear] group have the different length: ");
            System.out.println("unit["+ ownerGroup.length +"] " + splitownergroup);
            System.out.println("disGroup["+ disGroup.length +"] " + splitownerdisfeegroup);
            System.out.println("feeGroup["+ feeGroup.length +"] " + splitownerfeegroup);
            System.out.println("payFeeGroup["+ payFeeGroup.length +"] " + splitownerpayfeegroup);

            throw new IllegalArgumentException("[Clear]All Split group must have the same length.");
        }

        LinkedList<ETCClearResult> clearResults = new LinkedList<>();
//        for (int i = 0; i < disGroup.length; i++) {
        for (int i = 0; i < disGroup.length; i++) {

            if(Objects.equals(ownerGroup[i], "")){
                System.out.println("[Clear] ownerGroup["+ i +"] is empty, ownerGroup: " + splitownergroup);
            }
            if(Objects.equals(ownerGroup[i], "")){
                System.out.println("[Clear] feeGroup["+ i +"] is empty, feeGroup: " + splitownerfeegroup);
            }
            if(Objects.equals(ownerGroup[i], "")){
                System.out.println("[Clear] disGroup["+ i +"] is empty, disGroup: " + splitownerdisfeegroup);
            }
            if(Objects.equals(ownerGroup[i], "")){
                System.out.println("[Clear] payFeeGroup["+ i +"] is empty, payFeeGroup: " + splitownerpayfeegroup);
            }


            ETCClearResult result = (ETCClearResult) resultTemplate.clone();
            System.out.println("[Clear] ownerGroup[i]: " + ownerGroup[i]);
            result.setTOLLINTERVALID(ownerGroup[i]);
            System.out.println("[Clear] feeGroup[i]: " + feeGroup[i]);
            result.setAMOUNT(Integer.valueOf(feeGroup[i]));
            System.out.println("[Clear] disGroup[i]: " + disGroup[i]);
            result.setDISCOUNTAMOUNT(Integer.valueOf(disGroup[i]));
            System.out.println("[Clear] payFeeGroup[i]: " + payFeeGroup[i]);
            result.setCHARGEAMOUNT(Integer.valueOf(payFeeGroup[i]));
            clearResults.add(result);
        }
        return clearResults;
    }

    private static LinkedList<CashClearResult> split2ClearDetials(String splitownergroup,
                                                          String splitownerfeegroup,
                                                          String splitownerpayfeegroup,
                                                          String splitownerdisfeegroup,
                                                          CashClearResult resultTemplate) {
        String[] disGroup = splitownerdisfeegroup.trim().split("\\|");
        String[] feeGroup = splitownerfeegroup.trim().split("\\|");
        String[] payFeeGroup = splitownerpayfeegroup.trim().split("\\|");
        String[] ownerGroup = splitownergroup.trim().split("\\|");

        // 检查数组长度是否相同
        if (disGroup.length != feeGroup.length || disGroup.length != payFeeGroup.length || disGroup.length != ownerGroup.length) {
            throw new IllegalArgumentException("[Clear]All Split group must have the same length.");
        }

        LinkedList<CashClearResult> clearResults = new LinkedList<>();
        for (int i = 0; i < disGroup.length; i++) {
            CashClearResult result = (CashClearResult) resultTemplate.clone();

            if(Objects.equals(ownerGroup[i], "")){
                ownerGroup[i] = "empty";
                System.out.println("[Clear] ownerGroup["+ i +"] is empty, ownerGroup: " + splitownergroup);
            }
            if(Objects.equals(feeGroup[i], "")){
                feeGroup[i] = "1";
                System.out.println("[Clear] feeGroup["+ i +"] is empty, feeGroup: " + splitownerfeegroup);
            }
            if(Objects.equals(disGroup[i], "")){
                disGroup[i] = "1";
                System.out.println("[Clear] disGroup["+ i +"] is empty, disGroup: " + splitownerdisfeegroup);
            }
            if(Objects.equals(payFeeGroup[i], "")){
                payFeeGroup[i] = "1";
                System.out.println("[Clear] payFeeGroup["+ i +"] is empty, payFeeGroup: " + splitownerpayfeegroup);
            }

            result.setTOLLINTERVALID(ownerGroup[i]);
            result.setAMOUNT(Integer.valueOf(feeGroup[i]));
            result.setDISCOUNTAMOUNT(Integer.valueOf(disGroup[i]));
            result.setCHARGEAMOUNT(Integer.valueOf(payFeeGroup[i]));
            clearResults.add(result);
        }
        return clearResults;
    }

    private static int dateStr2Int(String time){
        try {
            String[] times = time.trim().split(" ");
            String hour = times[1].strip().split(":")[0];
            String day = times[0].replace("-", "");
            return Integer.parseInt(day);
        } catch (Exception e) {
            // 捕获任何异常并输出异常信息
            e.printStackTrace();
            // 返回默认值
            return 2023120901;
        }

    }
}
