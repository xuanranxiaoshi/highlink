package info.nemoworks.highlink.model.splitTransaction;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * @description:
 * @author：jimi
 * @date: 2024/3/21
 * @Copyright：
 */

@Data
public class ETCSplitResultGantry implements ProvinceTransaction, SplitResult{
    @JsonProperty("TOLLPROVINCEID")
    public String tOLLPROVINCEID;
    @JsonProperty("ISSUERID")
    public String iSSUERID;
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("REQUESTERID")
    public String rEQUESTERID;
    @JsonProperty("REQUESTERTYPE")
    public String rEQUESTERTYPE;
    @JsonProperty("EXITTRANSTYPE")
    public String eXITTRANSTYPE;
    @JsonProperty("PROCESSTIME")
    public String pROCESSTIME;
    @JsonProperty("SPLITTIME")
    public String sPLITTIME;
    @JsonProperty("PASSID")
    public String pASSID;
    @JsonProperty("EXTRANSACTIONID")
    public String eXTRANSACTIONID;
    @JsonProperty("EXTIME")
    public String eXTIME;
    @JsonProperty("EXTOLLSTATIONNAME")
    public String eXTOLLSTATIONNAME;
    @JsonProperty("EXTOLLLANEID")
    public String eXTOLLLANEID;
    @JsonProperty("EXFEE")
    public String eXFEE;
    @JsonProperty("FEE")
    public String fEE;
    @JsonProperty("SPLITFEE")
    public String sPLITFEE;
    @JsonProperty("SPLITRULE")
    public String sPLITRULE;
    @JsonProperty("EXITFEETYPE")
    public String eXITFEETYPE;
    @JsonProperty("VEHICLESIGN")
    public String vEHICLESIGN;
    @JsonProperty("TOLLINTERVALID")
    public String tOLLINTERVALID;
    @JsonProperty("TOLLINTERVALSPLITFEE")
    public String tOLLINTERVALSPLITFEE;
    @JsonProperty("TRANSNUM")
    public String tRANSNUM;
    @JsonProperty("VERSION")
    public String vERSION;
    @JsonProperty("STORAGETIME")
    public String sTORAGETIME;
    @JsonProperty("SPLITCHECKDATE")
    public String sPLITCHECKDATE;
    @JsonProperty("SPLITCHECKSTATUS")
    public String sPLITCHECKSTATUS;
    @JsonProperty("SPLITCHECKTIME")
    public String sPLITCHECKTIME;
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
    @JsonProperty("CLEARDATE")
    public String cLEARDATE;
    @JsonProperty("CLEARFLAG")
    public String cLEARFLAG;
    @JsonProperty("CLEARREMARK")
    public String cLEARREMARK;
    @JsonProperty("SPARE1")
    public String sPARE1;
    @JsonProperty("SPARE2")
    public String sPARE2;
    @JsonProperty("SPARE3")
    public String sPARE3;
    @JsonProperty("VEHICLETYPE")
    public String vEHICLETYPE;
    @JsonProperty("SERPROVINCEID")
    public String sERPROVINCEID;
    @JsonProperty("TOLLINTERVALID1")
    public String tOLLINTERVALID1;
    @JsonProperty("TOLLINTERVALID2")
    public String tOLLINTERVALID2;
    @JsonProperty("FIRSTCLEARFLAG")
    public String fIRSTCLEARFLAG;
    @JsonProperty("SPLITOWNERGROUP1")
    public String sPLITOWNERGROUP1;
    @JsonProperty("SPLITOWNERGROUP2")
    public String sPLITOWNERGROUP2;
    @JsonProperty("SPLITBASE")
    public String sPLITBASE;
    @JsonProperty("SPLITREMARK1")
    public String sPLITREMARK1;
    @JsonProperty("VLP")
    public String vLP;
    @JsonProperty("VLPC")
    public String vLPC;
    @JsonProperty("CARDID")
    public String cARDID;
    @JsonProperty("ETCCARDTYPE")
    public String eTCCARDTYPE;
    @JsonProperty("MEDIATYPE")
    public String mEDIATYPE;
    @JsonProperty("MEDIANO")
    public String mEDIANO;
    @JsonProperty("ENTOLLLANEID")
    public String eNTOLLLANEID;
    @JsonProperty("APPOINTID")
    public String aPPOINTID;
}



