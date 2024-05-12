package info.nemoworks.highlink.model.splitTransaction;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * @description: 拆分明细数据，对应数据表：tbl_OtherSplitDetail_Ext 和 tbl_ETCSplitDetail_Ext
 * @author：jimi
 * @date: 2024/3/24
 * @Copyright：
 */

@Data
public class SplitDetailExit implements  ProvinceTransaction{
    @JsonProperty("SPLITID")
    public String sPLITID;
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("SERPROVINCEID")
    public String sERPROVINCEID;
    @JsonProperty("TOLLFEE")
    public String tOLLFEE;
    @JsonProperty("ENPOINTID")
    public String eNPOINTID;
    @JsonProperty("EXPOINTID")
    public String eXPOINTID;
    @JsonProperty("ENTOLLSTATIONNAME")
    public String eNTOLLSTATIONNAME;
    @JsonProperty("EXTOLLSTATIONNAME")
    public String eXTOLLSTATIONNAME;
    @JsonProperty("ENTIME")
    public String eNTIME;
    @JsonProperty("EXTIME")
    public String eXTIME;
    @JsonProperty("TOLLINTERVALS")
    public String tOLLINTERVALS;
    @JsonProperty("TOLLINTERVALCHARGEFEE")
    public String tOLLINTERVALCHARGEFEE;
    @JsonProperty("TOLLINTERVALPAYFEE")
    public String tOLLINTERVALPAYFEE;
    @JsonProperty("TOLLINTERVALDISCOUNTFEE")
    public String tOLLINTERVALDISCOUNTFEE;
    @JsonProperty("TOLLINTERVALRATEVERSION")
    public String tOLLINTERVALRATEVERSION;
    @JsonProperty("SPLITFLAG")
    public String sPLITFLAG;
    @JsonProperty("PROSPLITTIME")
    public String pROSPLITTIME;
    @JsonProperty("PROSPLITTYPE")
    public String pROSPLITTYPE;
    @JsonProperty("SPLITOWNERCOUNT")
    public String sPLITOWNERCOUNT;
    @JsonProperty("SPLITOWNERGROUP")
    public String sPLITOWNERGROUP;
    @JsonProperty("SPLITOWNERFEEGROUP")
    public String sPLITOWNERFEEGROUP;
    @JsonProperty("SPLITOWNERPAYFEEGROUP")
    public String sPLITOWNERPAYFEEGROUP;
    @JsonProperty("SPLITOWNERDISFEEGROUP")
    public String sPLITOWNERDISFEEGROUP;
    @JsonProperty("SPLITREMARK")
    public String sPLITREMARK;
    @JsonProperty("FIRSTCLEARFLAG")
    public String fIRSTCLEARFLAG;
    @JsonProperty("TOLLINTERVALS1")
    public String tOLLINTERVALS1;
    @JsonProperty("TOLLINTERVALS2")
    public String tOLLINTERVALS2;
    @JsonProperty("TOLLINTERVALRATEVERSION1")
    public String tOLLINTERVALRATEVERSION1;
    @JsonProperty("TOLLINTERVALRATEVERSION2")
    public String tOLLINTERVALRATEVERSION2;
    @JsonProperty("SPLITOWNERGROUP1")
    public String sPLITOWNERGROUP1;
    @JsonProperty("SPLITOWNERGROUP2")
    public String sPLITOWNERGROUP2;
    @JsonProperty("SPLITBASE")
    public String sPLITBASE;
}
