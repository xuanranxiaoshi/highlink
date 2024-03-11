package info.nemoworks.highlink.model.tollChangeTransaction;

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
public class TollChangeTransactions implements HighwayTransaction {
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("MODIFYFLAG")
    public int mODIFYFLAG;
    @JsonProperty("CROSSFLAG")
    public int cROSSFLAG;
    @JsonProperty("TOLLPROVINCEID")
    public int tOLLPROVINCEID;
    @JsonProperty("ISSUERID")
    public int iSSUERID;
    @JsonProperty("PAYERID")
    public int pAYERID;
    @JsonProperty("RECEIVEID")
    public int rECEIVEID;
    @JsonProperty("RECEIVETYPE")
    public int rECEIVETYPE;
    @JsonProperty("TRANSACTIONID")
    public String tRANSACTIONID;
    @JsonProperty("ORDERID")
    public String oRDERID;
    @JsonProperty("EXTIME")
    public String eXTIME;
    @JsonProperty("LDATE")
    public String lDATE;
    @JsonProperty("CARD")
    public String cARD;
    @JsonProperty("VLPC")
    public int vLPC;
    @JsonProperty("VLP")
    public String vLP;
    @JsonProperty("PAYTYPE")
    public int pAYTYPE;
    @JsonProperty("ORIGINALFEE")
    public int oRIGINALFEE;
    @JsonProperty("FEE")
    public int fEE;
    @JsonProperty("STATUS")
    public int sTATUS;
    @JsonProperty("PROTIME")
    public String pROTIME;
    @JsonProperty("GENTIME")
    public String gENTIME;
    @JsonProperty("SPLITOWNERCOUNT")
    public int sPLITOWNERCOUNT;
    @JsonProperty("SPLITOWNERGROUP")
    public String sPLITOWNERGROUP;
    @JsonProperty("SPLITOWNERFEEGROUP")
    public int sPLITOWNERFEEGROUP;
    @JsonProperty("SPLITOWNERPAYFEEGROUP")
    public int sPLITOWNERPAYFEEGROUP;
    @JsonProperty("SPLITOWNERDISFEEGROUP")
    public int sPLITOWNERDISFEEGROUP;
    @JsonProperty("DESCRIPTION")
    public String dESCRIPTION;
    @JsonProperty("EXTOLLSTATION")
    public String eXTOLLSTATION;
    @JsonProperty("EXTOLLSTATIONNAME")
    public String eXTOLLSTATIONNAME;
    @JsonProperty("EXTOLLSTATIONID")
    public String eXTOLLSTATIONID;
    @JsonProperty("PAYORDERNUM")
    public String pAYORDERNUM;
    @JsonProperty("PAYCODE")
    public String pAYCODE;
    @JsonProperty("ROADTYPE")
    public int rOADTYPE;
    @JsonProperty("BATCHNUM")
    public String bATCHNUM;
    @JsonProperty("NATIONCLEARDATE")
    public String nATIONCLEARDATE;
    @JsonProperty("CLEARBATCHSTATUS")
    public int cLEARBATCHSTATUS;
    @JsonProperty("CHARGEBATCH")
    public String cHARGEBATCH;
    @JsonProperty("CLEARBATCH")
    public String cLEARBATCH;
    @JsonProperty("CLEARDATE")
    public String cLEARDATE;
    @JsonProperty("ETCCARDNET")
    public int eTCCARDNET;
    @JsonProperty("VERIFYPASSTIME")
    public String vERIFYPASSTIME;
    @JsonProperty("ETCCARDID")
    public long eTCCARDID;
    @JsonProperty("SPLITFLAG")
    public int sPLITFLAG;
    @JsonProperty("SPLITTIME")
    public String sPLITTIME;
    @JsonProperty("ISSUECHARGETIME")
    public String iSSUECHARGETIME;
    @JsonProperty("DISPUTEDPROTIME")
    public String dISPUTEDPROTIME;
    @JsonProperty("CHARGERESULT")
    public int cHARGERESULT;
    @JsonProperty("EXTOLLLANEID")
    public String eXTOLLLANEID;
    @JsonProperty("ETCCARDTYPE")
    public int eTCCARDTYPE;
    @JsonProperty("EXVEHICLETYPE")
    public int eXVEHICLETYPE;
    @JsonProperty("PASSID")
    public double pASSID;
    @JsonProperty("WASTETYPE")
    public int wASTETYPE;
    @JsonProperty("REMARKS")
    public String rEMARKS;
    @JsonProperty("SPLITOWNERGROUP1")
    public String sPLITOWNERGROUP1;
    @JsonProperty("SPLITOWNERGROUP2")
    public String sPLITOWNERGROUP2;
    @JsonProperty("CLEARREMARK")
    public String cLEARREMARK;
    @JsonProperty("SPLITCOUNT")
    public String sPLITCOUNT;
    @JsonProperty("SPLITGROUP")
    public String sPLITGROUP;
    @JsonProperty("SPLITFEEGROUP")
    public String sPLITFEEGROUP;
    @JsonProperty("RETURNFLAG")
    public int rETURNFLAG;
    @JsonProperty("ORIGINALFLAG")
    public int oRIGINALFLAG;
}
