package info.nemoworks.highlink.model.extendTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @description:
 * @author：jimi
 * @date: 2023/12/20
 * @Copyright：
 */
@Data
public class ExdForeignGasTransaction implements HighwayTransaction {
    @JsonProperty("ID")
    public double iD;
    @JsonProperty("GASSTATIONOPERATORID")
    public int gASSTATIONOPERATORID;
    @JsonProperty("ISSUERID")
    public int iSSUERID;
    @JsonProperty("MESSAGEID")
    public long mESSAGEID;
    @JsonProperty("FUELTYPE")
    public int fUELTYPE;
    @JsonProperty("FUELPRICE")
    public int fUELPRICE;
    @JsonProperty("FUELVOLUME")
    public int fUELVOLUME;
    @JsonProperty("TRANSDATE")
    public String tRANSDATE;
    @JsonProperty("TRANSTIME")
    public String tRANSTIME;
    @JsonProperty("FEE")
    public int fEE;
    @JsonProperty("TERMINALTRANSNO")
    public String tERMINALTRANSNO;
    @JsonProperty("OBUID")
    public String oBUID;
    @JsonProperty("VEHICLEID")
    public String vEHICLEID;
    @JsonProperty("TAC")
    public String tAC;
    @JsonProperty("TRANSNO")
    public int tRANSNO;
    @JsonProperty("TRANSTYPE")
    public String tRANSTYPE;
    @JsonProperty("TERMINALNO")
    public String tERMINALNO;
    @JsonProperty("PREBALANCE")
    public int pREBALANCE;
    @JsonProperty("POSTBALANCE")
    public int pOSTBALANCE;
    @JsonProperty("SERVICETYPE")
    public int sERVICETYPE;
    @JsonProperty("ALGORITHMIDENTIFIER")
    public int aLGORITHMIDENTIFIER;
    @JsonProperty("REMARK")
    public String rEMARK;
    @JsonProperty("RECEIVETIME")
    public String rECEIVETIME;
    @JsonProperty("GENTIME")
    public String gENTIME;
    @JsonProperty("REMARKS")
    public String rEMARKS;
    @JsonProperty("ISSUECHARGETIME")
    public String iSSUECHARGETIME;
    @JsonProperty("CHARGERESULT")
    public int cHARGERESULT;
    @JsonProperty("DISPUTEDRESULT")
    public String dISPUTEDRESULT;
    @JsonProperty("DISPUTEDID")
    public String dISPUTEDID;
    @JsonProperty("DISPUTEDPROTIME")
    public String dISPUTEDPROTIME;
    @JsonProperty("CLEARDATE")
    public String cLEARDATE;
    @JsonProperty("ETCCARDTYPE")
    public int eTCCARDTYPE;
    @JsonProperty("ETCCARDNET")
    public int eTCCARDNET;
    @JsonProperty("ETCCARDID")
    public long eTCCARDID;
    @JsonProperty("NATIONCLEARDATE")
    public String nATIONCLEARDATE;
    @JsonProperty("CLEARBATCH")
    public String cLEARBATCH;
    @JsonProperty("STATUS")
    public int sTATUS;
    @JsonProperty("ORGCODE")
    public long oRGCODE;
    @JsonProperty("CLEARBATCHSTATUS")
    public int cLEARBATCHSTATUS;
    @JsonProperty("SPLITFLAG")
    public int sPLITFLAG;
    @JsonProperty("NATIONCLEARFLAG")
    public int nATIONCLEARFLAG;
    @JsonProperty("NATIONCLEARTIME")
    public String nATIONCLEARTIME;
    @JsonProperty("NAME")
    public String nAME;
    @JsonProperty("CLEARREMARK")
    public String cLEARREMARK;
    @JsonProperty("MANAGEID")
    public String mANAGEID;

    public String getID(){
        return String.valueOf(this.iD);
    }
}
