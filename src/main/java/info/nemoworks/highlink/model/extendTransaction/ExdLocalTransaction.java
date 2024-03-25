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
public class ExdLocalTransaction implements HighwayTransaction{
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("WASTETYPE")
    public int wASTETYPE;
    @JsonProperty("OPERATORID")
    public int oPERATORID;
    @JsonProperty("ISSUERID")
    public int iSSUERID;
    @JsonProperty("TRANSDATE")
    public String tRANSDATE;
    @JsonProperty("TRANSTIME")
    public String tRANSTIME;
    @JsonProperty("PARKTIME")
    public int pARKTIME;
    @JsonProperty("FUELTYPE")
    public String fUELTYPE;
    @JsonProperty("FUELPRICE")
    public String fUELPRICE;
    @JsonProperty("FUELVOLUME")
    public String fUELVOLUME;
    @JsonProperty("DESCRIBE")
    public String dESCRIBE;
    @JsonProperty("FEE")
    public int fEE;
    @JsonProperty("ETCCARDTYPE")
    public int eTCCARDTYPE;
    @JsonProperty("ETCCARDNET")
    public int eTCCARDNET;
    @JsonProperty("ETCCARDID")
    public long eTCCARDID;
    @JsonProperty("TERMINALTRANSNO")
    public String tERMINALTRANSNO;
    @JsonProperty("OBUID")
    public long oBUID;
    @JsonProperty("VEHICLEID")
    public String vEHICLEID;
    @JsonProperty("VEHICLETYPE")
    public int vEHICLETYPE;
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
    @JsonProperty("DISPUTEDPROTIME")
    public String dISPUTEDPROTIME;
    @JsonProperty("CLEARBATCHSTATUS")
    public int cLEARBATCHSTATUS;
    @JsonProperty("CLEARBATCH")
    public String cLEARBATCH;
    @JsonProperty("CLEARDATE")
    public String cLEARDATE;
    @JsonProperty("BATCHNUM")
    public String bATCHNUM;
    @JsonProperty("CHARGEBATCH")
    public String cHARGEBATCH;
    @JsonProperty("ORGCODE")
    public long oRGCODE;
    @JsonProperty("SPLITFLAG")
    public int sPLITFLAG;
    @JsonProperty("CLEARREMARK")
    public String cLEARREMARK;
    @JsonProperty("NAME")
    public String nAME;
    @JsonProperty("MANAGEID")
    public String mANAGEID;
    @JsonProperty("MODIFYFLAG")
    public int mODIFYFLAG;

}
