package info.nemoworks.highlink.model.multiProvince;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * @description:
 * @author：jimi
 * @date: 2024/3/21
 * @Copyright：
 */
@Data
public class ETCSplitResultExit implements ProvinceTransaction {
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("RESULTTYPE")
    public String rESULTTYPE;
    @JsonProperty("MEDIATYPE")
    public String mEDIATYPE;
    @JsonProperty("MEDIANO")
    public String mEDIANO;
    @JsonProperty("TRANSPAYTYPE")
    public String tRANSPAYTYPE;
    @JsonProperty("SPLITTIME")
    public String sPLITTIME;
    @JsonProperty("EXITFEETYPE")
    public String eXITFEETYPE;
    @JsonProperty("TOLLPROVINCEID")
    public String tOLLPROVINCEID;
    @JsonProperty("SERPROVINCEID")
    public String sERPROVINCEID;
    @JsonProperty("ISSUERID")
    public String iSSUERID;
    @JsonProperty("CARDID")
    public String cARDID;
    @JsonProperty("ETCCARDTYPE")
    public String eTCCARDTYPE;
    @JsonProperty("EXTIME")
    public String eXTIME;
    @JsonProperty("EXVLP")
    public String eXVLP;
    @JsonProperty("EXVLPC")
    public String eXVLPC;
    @JsonProperty("IDENTIFYVLP")
    public String iDENTIFYVLP;
    @JsonProperty("IDENTIFYVLPC")
    public String iDENTIFYVLPC;
    @JsonProperty("VEHICLETYPE")
    public String vEHICLETYPE;
    @JsonProperty("VEHICLECLASS")
    public String vEHICLECLASS;
    @JsonProperty("TAC")
    public String tAC;
    @JsonProperty("DESCRIPTION")
    public String dESCRIPTION;
    @JsonProperty("SPECIALTYPE")
    public String sPECIALTYPE;
    @JsonProperty("ENTOLLLANEID")
    public String eNTOLLLANEID;
    @JsonProperty("ENTOLLLANEHEX")
    public String eNTOLLLANEHEX;
    @JsonProperty("ENTIME")
    public String eNTIME;
    @JsonProperty("ENTOLLSTATIONNAME")
    public String eNTOLLSTATIONNAME;
    @JsonProperty("EXTOLLSTATIONNAME")
    public String eXTOLLSTATIONNAME;
    @JsonProperty("EXTOLLLANEID")
    public String eXTOLLLANEID;
    @JsonProperty("PASSID")
    public String pASSID;
    @JsonProperty("FEE")
    public String fEE;
    @JsonProperty("ACCOUNTSTATUS")
    public String aCCOUNTSTATUS;
    @JsonProperty("ACCOUNTTIME")
    public String aCCOUNTTIME;
    @JsonProperty("RECEIVETIME")
    public String rECEIVETIME;
    @JsonProperty("RESULT")
    public String rESULT;
    @JsonProperty("PROCESSTIME")
    public String pROCESSTIME;
    @JsonProperty("VEHICLESIGN")
    public String vEHICLESIGN;
    @JsonProperty("VERSION")
    public String vERSION;
    @JsonProperty("STORAGETIME")
    public String sTORAGETIME;
    @JsonProperty("SPLITCHECKSTATUS")
    public String sPLITCHECKSTATUS;
    @JsonProperty("SPLITCHECKTIME")
    public String sPLITCHECKTIME;
    @JsonProperty("SPLITCHECKDATE")
    public String sPLITCHECKDATE;
    @JsonProperty("SPARE1")
    public String sPARE1;
    @JsonProperty("SPARE2")
    public String sPARE2;
    @JsonProperty("SPARE3")
    public String sPARE3;
    @JsonProperty("SPLITINFEE")
    public String sPLITINFEE;
    @JsonProperty("SPLITCROSSFEE")
    public String sPLITCROSSFEE;
    @JsonProperty("NATIONSPLITTIME")
    public String nATIONSPLITTIME;
    @JsonProperty("NATIONSPLITTYPE")
    public String nATIONSPLITTYPE;
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
    @JsonProperty("SPLITCHECKFLAG")
    public String sPLITCHECKFLAG;
    @JsonProperty("SPLITCNT")
    public String sPLITCNT;
    @JsonProperty("SPLITFEE")
    public String sPLITFEE;
    @JsonProperty("REALSPLITCNT")
    public String rEALSPLITCNT;
    @JsonProperty("SPLITREMARK")
    public String sPLITREMARK;
    @JsonProperty("CLEARDATE")
    public String cLEARDATE;
    @JsonProperty("CLEARFLAG")
    public String cLEARFLAG;
    @JsonProperty("CLEARREMARK")
    public String cLEARREMARK;
    @JsonProperty("FIRSTCLEARFLAG")
    public String fIRSTCLEARFLAG;
    @JsonProperty("SPLITOWNERGROUP1")
    public String sPLITOWNERGROUP1;
    @JsonProperty("SPLITOWNERGROUP2")
    public String sPLITOWNERGROUP2;
    @JsonProperty("SPLITREMARK1")
    public String sPLITREMARK1;
    @JsonProperty("SPLITBASE")
    public String sPLITBASE;
    @JsonProperty("APPOINTID")
    public String aPPOINTID;
}
