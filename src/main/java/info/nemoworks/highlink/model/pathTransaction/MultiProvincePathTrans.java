package info.nemoworks.highlink.model.pathTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalETCTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitLocalOtherTrans;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import info.nemoworks.highlink.model.mapper.ExitMapper;
import info.nemoworks.highlink.utils.SimpleContainer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 单省拆分业务
 * @author：jimi
 * @date: 2024/3/5
 * @Copyright：
 */

@Slf4j
@Data
public class MultiProvincePathTrans {

    private EntryRawTransaction entryRawTransaction;
    private ExitRawTransaction exitRawTransaction;
    private List<GantryRawTransaction> gantryRawTransactionList = new ArrayList<>();

    private Set<String> chargeUnites = new LinkedHashSet<>();
    private Map<String, Integer> payFeeGroups = new LinkedHashMap<>();
    private Map<String, Integer> feeGroups = new LinkedHashMap<>();
    private Map<String, Integer> discountFeeGroups = new LinkedHashMap<>();

    private String chargeUnitesStr = "";
    private String payFeeStr = "";
    private String feeStr = "";
    private String discountStr = "";

    private static ObjectMapper objectMapper = SimpleContainer.getObjectMapper();


    /**
     * 根据 path 的 json 数据构造
     * @param multiProvincePath
     * @throws JsonProcessingException
     */
    public MultiProvincePathTrans(String multiProvincePath) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(multiProvincePath);
        JsonNode curNode;
        PathTransaction curPathTrans;
        for (int i = 0; i < jsonNode.size(); i++) {
            curNode = jsonNode.get(i);
            if (curNode.get("EXTOLLSTATION") != null) {
                curPathTrans = objectMapper.treeToValue(curNode, ExitRawTransaction.class);
                this.exitRawTransaction = (ExitRawTransaction) curPathTrans;
            }else if (curNode.get("GANTRYID") != null) {
                curPathTrans = objectMapper.treeToValue(curNode, GantryRawTransaction.class);
                this.gantryRawTransactionList.add((GantryRawTransaction) curPathTrans);
            }
            // fixmed: 可能没有 entry
            else if(curNode.size() == 91){
                curPathTrans = objectMapper.treeToValue(curNode, EntryRawTransaction.class);
                this.entryRawTransaction = (EntryRawTransaction) curPathTrans;
            }
        }
    }

    /**
     * 多省路径拆分逻辑实现
     * @return
     */
    public void splitCharge() {
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
        // 拼接为字符串
        map2String();
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
