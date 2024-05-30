package info.nemoworks.highlink.model.extendTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @description: 对应预处理输入中拓展接收的 “停车场ETC交易流水接收表（tbl_ParkTransWasteRec）”， 其他输入未使用
 * @author：jimi
 * @date: 2024/1/21
 * @Copyright：
 */

@Data
public class ParkTransWasteRec implements HighwayTransaction {
    @JsonProperty("ID")
    private String iD;
    @JsonProperty("PARKOPERATORID")
    private int pARKOPERATORID;
    @JsonProperty("ISSUERID")
    private int iSSUERID;
    @JsonProperty("PARKID")
    private long pARKID;
    @JsonProperty("BATCHNUM")
    private String bATCHNUM;
    @JsonProperty("TRANSTIME")
    private String tRANSTIME;
    @JsonProperty("PARKTIME")
    private int pARKTIME;
    @JsonProperty("FEE")
    private int fEE;
    @JsonProperty("ETCCARDTYPE")
    private int eTCCARDTYPE;
    @JsonProperty("ETCCARDNET")
    private int eTCCARDNET;
    @JsonProperty("ETCCARDID")
    private long eTCCARDID;
    @JsonProperty("TERMINALTRANSNO")
    private String tERMINALTRANSNO;
    @JsonProperty("OBUID")
    private String oBUID;
    @JsonProperty("VLP")
    private String vLP;
    @JsonProperty("VLPC")
    private int vLPC;
    @JsonProperty("VEHICLETYPE")
    private int vEHICLETYPE;
    @JsonProperty("TAC")
    private String tAC;
    @JsonProperty("TRANSNO")
    private int tRANSNO;
    @JsonProperty("TRANSTYPE")
    private String tRANSTYPE;
    @JsonProperty("TERMINALNO")
    private String tERMINALNO;
    @JsonProperty("PREBALANCE")
    private long pREBALANCE;
    @JsonProperty("POSTBALANCE")
    private long pOSTBALANCE;
    @JsonProperty("SERVICETYPE")
    private int sERVICETYPE;
    @JsonProperty("ALGORITHMIDENTIFIER")
    private int aLGORITHMIDENTIFIER;
    @JsonProperty("REMARK")
    private String rEMARK;
    @JsonProperty("REQUESTNAME")
    private String rEQUESTNAME;
    @JsonProperty("RESPONSENAME")
    private String rESPONSENAME;
    @JsonProperty("RECEIVETIME")
    private String rECEIVETIME;
    @JsonProperty("MSGID")
    private String mSGID;
    @JsonProperty("SPARE1")
    private String sPARE1;
    @JsonProperty("SPARE2")
    private String sPARE2;
    @JsonProperty("SPARE3")
    private String sPARE3;
    @JsonProperty("SPARE4")
    private String sPARE4;
    @JsonProperty("VERIFYPASSTIME")
    private String vERIFYPASSTIME;
    @JsonProperty("REMARKS")
    private String rEMARKS;
    @JsonProperty("MODIFYFLAG")
    private int mODIFYFLAG;
    @JsonProperty("SOURCEID")
    private String sOURCEID;

    public boolean isPrimaryTrans() {
        return "09".equals(this.getTRANSTYPE());
    }

    public boolean isLocal() {
        return this.getVLP().contains("鲁");
    }

    public boolean isMunicipalTrans() {
        return this.getSERVICETYPE() == 1;
    }

    public boolean isParkTrans() {
        return this.getSERVICETYPE() == 2;
    }

    public boolean isGasTrans() {
        return this.getSERVICETYPE() == 3;
    }

    @Override
    public String getPASSID() {
        return null;
    }
}
