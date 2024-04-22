package info.nemoworks.highlink.model.multiProvince;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/13
 * @Copyright：
 */
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExitWaste implements ProvinceTransaction{
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
}
