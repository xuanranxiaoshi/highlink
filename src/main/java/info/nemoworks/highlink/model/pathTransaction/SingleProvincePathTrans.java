package info.nemoworks.highlink.model.pathTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.mapper.ExitMapper;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 单省拆分业务
 * @author：jimi
 * @date: 2024/3/5
 * @Copyright：
 */

@Slf4j
public class SingleProvincePathTrans {

    private EntryRawTransaction entryRawTransaction;

    private ExitRawTransaction exitRawTransaction;

    private List<GantryRawTransaction> gantryRawTransactionList;    // 无论 ETC 还是 CPC 直接使用 gantry raw 就行

    private Set<String> chargeUnites = new LinkedHashSet<>();
    private Map<String, Integer> payFeeGroups = new LinkedHashMap<>();
    private Map<String, Integer> feeGroups = new LinkedHashMap<>();
    private Map<String, Integer> discountFeeGroups = new LinkedHashMap<>();

    private String chargeUnitesStr = "";
    private String payFeeStr = "";
    private String feeStr = "";
    private String discountStr = "";



    public SingleProvincePathTrans(List<PathTransaction> pathTransactions){
        if(pathTransactions.get(0) instanceof EntryRawTransaction entryRawTransaction &&
            pathTransactions.get(pathTransactions.size()-1) instanceof ExitRawTransaction exitRawTransaction){
            this.entryRawTransaction = entryRawTransaction;
            this.exitRawTransaction = exitRawTransaction;
        }else {
            System.out.println("[Error] SingleProvincePathETCTrans 构造异常： 路径首/尾不为 entry/exit 数据, pathSize = " + pathTransactions.size() + ", passID = " + pathTransactions.get(0).getPASSID());
        }

        gantryRawTransactionList = new ArrayList<>();
        for (int i = 1; i < pathTransactions.size() - 1; i ++){
            if(pathTransactions.get(i) instanceof GantryRawTransaction gantryRawTransaction){
                gantryRawTransactionList.add(gantryRawTransaction);
            }else{
                System.out.println("[Error] SingleProvincePathETCTrans 构造异常： 路径中心数据不为门架数据");
            }
        }
    }

    /**
     * 单省拆分逻辑实现
     * @return
     */
    public void splitCharge() {
        int exitfeetype = this.exitRawTransaction.getACTUALFEECLASS();
        if(exitfeetype == 1 || exitfeetype == 3){   // 计费方式 1\3
            for (int i = 0; i < gantryRawTransactionList.size(); i++) {
                GantryRawTransaction gantryRawTransaction = gantryRawTransactionList.get(i);
                String[] chargeUnite = gantryRawTransaction.getTOLLINTERVALID().strip().split("\\|");
                String[] payFeeGroup = gantryRawTransaction.getPAYFEEGROUP().strip().split("\\|"); // 应收金额组合
                String[] feeGroup = gantryRawTransaction.getFEEGROUP().strip().split("\\|");  // 交易金额组合 = 应收 - 优惠
                String[] discountFeeGroup = gantryRawTransaction.getDISCOUNTFEEGROUP().strip().split("\\|");  // 优惠金额组合

                chargeUnites.addAll(List.of(chargeUnite));
                for (int j = 0; j < chargeUnite.length; j++) {
                    payFeeGroups.put(chargeUnite[j], payFeeGroups.getOrDefault(chargeUnite[j], 0) + Integer.parseInt(payFeeGroup[j]));
                    feeGroups.put(chargeUnite[j], feeGroups.getOrDefault(chargeUnite[j], 0) + Integer.parseInt(feeGroup[j]));
                    discountFeeGroups.put(chargeUnite[j], discountFeeGroups.getOrDefault(chargeUnite[j], 0) + Integer.parseInt(discountFeeGroup[j]));
                }
            }
        }

        // 拼接为字符串
        map2String();
    }

    public HighwayTransaction getUpdateRes(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String proTime = sdf.format(new Date());

        if(exitRawTransaction.peekPayWithEtc()){  // 单省 ETC 流水
            ExitLocalETCTrans exitLocalETCTrans = ExitMapper.INSTANCE.exitRawToExitLocalETC(exitRawTransaction);
            exitLocalETCTrans.setPROTIME(proTime);
            exitLocalETCTrans.setSPLITOWNERGROUP(chargeUnitesStr);
            exitLocalETCTrans.setSPLITOWNERFEEGROUP(feeStr);
            exitLocalETCTrans.setSPLITOWNERPAYFEEGROUP(payFeeStr);
            exitLocalETCTrans.setSPLITOWNERDISFEEGROUP(discountStr);
            return exitLocalETCTrans;

        }else{  // 单省其他交易流水
            ExitLocalOtherTrans exitLocalOtherTrans = ExitMapper.INSTANCE.exitRawToExitLocalOther(exitRawTransaction);
            exitLocalOtherTrans.setPROTIME(proTime);
            exitLocalOtherTrans.setSPLITOWNERGROUP(chargeUnitesStr);
            exitLocalOtherTrans.setSPLITOWNERFEEGROUP(feeStr);
            exitLocalOtherTrans.setSPLITOWNERPAYFEEGROUP(payFeeStr);
            exitLocalOtherTrans.setSPLITOWNERDISFEEGROUP(discountStr);

            return exitLocalOtherTrans;
        }
    }

    public void map2String(){
        Iterator<String> iterator = chargeUnites.iterator();
        String delimiter = "|";
        while (iterator.hasNext()){
            String key = iterator.next();
            chargeUnitesStr += key;
            feeStr += String.valueOf(feeGroups.get(key));
            payFeeStr += String.valueOf(payFeeGroups.get(key));
            discountStr += String.valueOf(discountFeeGroups.get(key));
            if(iterator.hasNext()){
                chargeUnitesStr += delimiter;
                feeStr += delimiter;
                payFeeStr += delimiter;
                discountStr += delimiter;
            }
        }
    }

}
