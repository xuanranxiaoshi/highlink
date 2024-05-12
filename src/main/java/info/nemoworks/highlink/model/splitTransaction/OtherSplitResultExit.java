package info.nemoworks.highlink.model.splitTransaction;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/21
 * @Copyright：
 */
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OtherSplitResultExit implements  ProvinceTransaction, SplitResult{
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("TOLLPROVINCEID")
    public String tOLLPROVINCEID;
    @JsonProperty("MEDIATYPE")
    public String mEDIATYPE;
    @JsonProperty("MEDIANO")
    public String mEDIANO;
    @JsonProperty("EXITFEETYPE")
    public String eXITFEETYPE;
    @JsonProperty("OBUSIGN")
    public String oBUSIGN;
    @JsonProperty("IDENTIFICATION")
    public String iDENTIFICATION;
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
    @JsonProperty("VEHICLESIGNID")
    public String vEHICLESIGNID;
    @JsonProperty("VEHICLETYPE")
    public String vEHICLETYPE;
    @JsonProperty("VEHICLECLASS")
    public String vEHICLECLASS;
    @JsonProperty("EXWEIGHT")
    public String eXWEIGHT;
    @JsonProperty("AXLECOUNT")
    public String aXLECOUNT;
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
    @JsonProperty("ENWEIGHT")
    public String eNWEIGHT;
    @JsonProperty("ENAXLECOUNT")
    public String eNAXLECOUNT;
    @JsonProperty("ENTOLLSTATIONNAME")
    public String eNTOLLSTATIONNAME;
    @JsonProperty("EXTOLLSTATIONNAME")
    public String eXTOLLSTATIONNAME;
    @JsonProperty("EXTOLLLANEID")
    public String eXTOLLLANEID;
    @JsonProperty("SPLITTIME")
    public String sPLITTIME;
    @JsonProperty("PASSID")
    public String pASSID;
    @JsonProperty("VEHICLESIGN")
    public String vEHICLESIGN;
    @JsonProperty("FEE")
    public String fEE;
    @JsonProperty("NOCARDCOUNT")
    public String nOCARDCOUNT;
    @JsonProperty("FEEMILEAGE")
    public String fEEMILEAGE;
    @JsonProperty("SHORTFEE")
    public String sHORTFEE;
    @JsonProperty("SHORTFEEMILEAGE")
    public String sHORTFEEMILEAGE;
    @JsonProperty("FEERATE")
    public String fEERATE;
    @JsonProperty("SPCRATEVERSION")
    public String sPCRATEVERSION;
    @JsonProperty("CARDTOTALAMOUNT")
    public String cARDTOTALAMOUNT;
    @JsonProperty("OBUTOTALAMOUNT")
    public String oBUTOTALAMOUNT;
    @JsonProperty("OBUTOTALDISCOUNTAMOUNT")
    public String oBUTOTALDISCOUNTAMOUNT;
    @JsonProperty("OBUPROVINCEFEE")
    public String oBUPROVINCEFEE;
    @JsonProperty("TOTALCOUNT")
    public String tOTALCOUNT;
    @JsonProperty("PROVTRANSCOUNT")
    public String pROVTRANSCOUNT;
    @JsonProperty("PROVINCECOUNT")
    public String pROVINCECOUNT;
    @JsonProperty("DISCOUNTTYPE")
    public String dISCOUNTTYPE;
    @JsonProperty("PROVINCEDISCOUNTFEE")
    public String pROVINCEDISCOUNTFEE;
    @JsonProperty("ORIGINFEE")
    public String oRIGINFEE;
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
    @JsonProperty("SPARE1")
    public String sPARE1;
    @JsonProperty("SPARE2")
    public String sPARE2;
    @JsonProperty("SPARE3")
    public String sPARE3;
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
    @JsonProperty("REVERSEFLAG")
    public String rEVERSEFLAG;
    @JsonProperty("APPOINTID")
    public String aPPOINTID;
}
