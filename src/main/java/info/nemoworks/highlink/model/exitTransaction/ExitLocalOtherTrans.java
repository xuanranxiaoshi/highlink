package info.nemoworks.highlink.model.exitTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ExitLocalOtherTrans implements HighwayTransaction{
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("WASTETYPE")
    public int wASTETYPE;
    @JsonProperty("MODIFYFLAG")
    public int mODIFYFLAG;
    @JsonProperty("MULTIPROVINCE")
    public int mULTIPROVINCE;
    @JsonProperty("MEDIATYPE")
    public int mEDIATYPE;
    @JsonProperty("MEDIANO")
    public long mEDIANO;
    @JsonProperty("EXITFEETYPE")
    public int eXITFEETYPE;
    @JsonProperty("TOLLPROVINCEID")
    public int tOLLPROVINCEID;
    @JsonProperty("OBUSIGN")
    public String oBUSIGN;
    @JsonProperty("IDENTIFICATION")
    public int iDENTIFICATION;
    @JsonProperty("ETCCARDTYPE")
    public String eTCCARDTYPE;
    @JsonProperty("CARDNET")
    public String cARDNET;
    @JsonProperty("CARDID")
    public int cARDID;
    @JsonProperty("EXTIME")
    public String eXTIME;
    @JsonProperty("VLP")
    public String vLP;
    @JsonProperty("VLPC")
    public int vLPC;
    @JsonProperty("IDENTIFYVLP")
    public String iDENTIFYVLP;
    @JsonProperty("IDENTIFYVLPC")
    public int iDENTIFYVLPC;
    @JsonProperty("VEHICLESIGNID")
    public String vEHICLESIGNID;
    @JsonProperty("VEHICLETYPE")
    public int vEHICLETYPE;
    @JsonProperty("VEHICLECLASS")
    public int vEHICLECLASS;
    @JsonProperty("DESCRIPTION")
    public int dESCRIPTION;
    @JsonProperty("LANESPINFO")
    public String lANESPINFO;
    @JsonProperty("SPINFO")
    public String sPINFO;
    @JsonProperty("SPECIALTYPE")
    public String sPECIALTYPE;
    @JsonProperty("ENTOLLSTATIONNAME")
    public String eNTOLLSTATIONNAME;
    @JsonProperty("ENTOLLSTATION")
    public String eNTOLLSTATION;
    @JsonProperty("ENTOLLLANE")
    public String eNTOLLLANE;
    @JsonProperty("ENTOLLSTATIONID")
    public String eNTOLLSTATIONID;
    @JsonProperty("ENTOLLLANEID")
    public String eNTOLLLANEID;
    @JsonProperty("ENTOLLSTATIONHEX")
    public String eNTOLLSTATIONHEX;
    @JsonProperty("ENTOLLLANEHEX")
    public String eNTOLLLANEHEX;
    @JsonProperty("ENVLP")
    public String eNVLP;
    @JsonProperty("ENVLPC")
    public int eNVLPC;
    @JsonProperty("ENTIME")
    public String eNTIME;
    @JsonProperty("ENWEIGHT")
    public int eNWEIGHT;
    @JsonProperty("ENAXLECOUNT")
    public int eNAXLECOUNT;
    @JsonProperty("EXTOLLSTATIONNAME")
    public String eXTOLLSTATIONNAME;
    @JsonProperty("EXTOLLSTATION")
    public String eXTOLLSTATION;
    @JsonProperty("EXTOLLLANE")
    public String eXTOLLLANE;
    @JsonProperty("EXTOLLSTATIONID")
    public String eXTOLLSTATIONID;
    @JsonProperty("EXTOLLLANEID")
    public String eXTOLLLANEID;
    @JsonProperty("EXTOLLSTATIONHEX")
    public String eXTOLLSTATIONHEX;
    @JsonProperty("EXTOLLLANEHEX")
    public String eXTOLLLANEHEX;
    @JsonProperty("EXWEIGHT")
    public int eXWEIGHT;
    @JsonProperty("AXLECOUNT")
    public int aXLECOUNT;
    @JsonProperty("CARDCOSTFEE")
    public int cARDCOSTFEE;
    @JsonProperty("UNPAYFEE")
    public int uNPAYFEE;
    @JsonProperty("UNPAYFLAG")
    public int uNPAYFLAG;
    @JsonProperty("UNPAYCARDCOST")
    public int uNPAYCARDCOST;
    @JsonProperty("TICKETFEE")
    public int tICKETFEE;
    @JsonProperty("UNIFIEDFEE")
    public int uNIFIEDFEE;
    @JsonProperty("TRANSCODE")
    public String tRANSCODE;
    @JsonProperty("SHIFT")
    public int sHIFT;
    @JsonProperty("OPERID")
    public int oPERID;
    @JsonProperty("OPERNAME")
    public String oPERNAME;
    @JsonProperty("LANEAPPVER")
    public String lANEAPPVER;
    @JsonProperty("ELECTRICALPERCENTAGE")
    public int eLECTRICALPERCENTAGE;
    @JsonProperty("PARAVER")
    public String pARAVER;
    @JsonProperty("CHECKSIGN")
    public int cHECKSIGN;
    @JsonProperty("LANETYPE")
    public int lANETYPE;
    @JsonProperty("OPEN")
    public int oPEN;
    @JsonProperty("BL_SUBCENTER")
    public int bL_SUBCENTER;
    @JsonProperty("BL_CENTER")
    public int bL_CENTER;
    @JsonProperty("LDATE")
    public String lDATE;
    @JsonProperty("BATCHNUM")
    public String bATCHNUM;
    @JsonProperty("PAKAGEID")
    public String pAKAGEID;
    @JsonProperty("PAKAGETIME")
    public String pAKAGETIME;
    @JsonProperty("CHARGEBATCH")
    public String cHARGEBATCH;
    @JsonProperty("PAYCARDTRANSN")
    public int pAYCARDTRANSN;
    @JsonProperty("PAYORDERNUM")
    public String pAYORDERNUM;
    @JsonProperty("PAYCODE")
    public String pAYCODE;
    @JsonProperty("VERIFYPASSTIME")
    public String vERIFYPASSTIME;
    @JsonProperty("PASSID")
    public String pASSID;
    @JsonProperty("VEHICLESIGN")
    public String vEHICLESIGN;
    @JsonProperty("PAYFEE")
    public int pAYFEE;
    @JsonProperty("FEE")
    public int fEE;
    @JsonProperty("DISCOUNTFEE")
    public int dISCOUNTFEE;
    @JsonProperty("NOCARDCOUNT")
    public int nOCARDCOUNT;
    @JsonProperty("FEEMILEAGE")
    public int fEEMILEAGE;
    @JsonProperty("SHORTFEE")
    public int sHORTFEE;
    @JsonProperty("SHORTFEEMILEAGE")
    public int sHORTFEEMILEAGE;
    @JsonProperty("FEERATE")
    public int fEERATE;
    @JsonProperty("SPCRATEVERSION")
    public int sPCRATEVERSION;
    @JsonProperty("CARDTOTALAMOUNT")
    public int cARDTOTALAMOUNT;
    @JsonProperty("OBUTOTALAMOUNT")
    public String oBUTOTALAMOUNT;
    @JsonProperty("OBUTOTALDISCOUNTAMOUNT")
    public String oBUTOTALDISCOUNTAMOUNT;
    @JsonProperty("OBUPROVINCEFEE")
    public int oBUPROVINCEFEE;
    @JsonProperty("TOTALCOUNT")
    public int tOTALCOUNT;
    @JsonProperty("PROVTRANSCOUNT")
    public int pROVTRANSCOUNT;
    @JsonProperty("PROVINCECOUNT")
    public int pROVINCECOUNT;
    @JsonProperty("DISCOUNTTYPE")
    public String dISCOUNTTYPE;
    @JsonProperty("PROVINCEDISCOUNTFEE")
    public String pROVINCEDISCOUNTFEE;
    @JsonProperty("ORIGINFEE")
    public String oRIGINFEE;
    @JsonProperty("PAYTYPE")
    public int pAYTYPE;
    @JsonProperty("ROADTYPE")
    public int rOADTYPE;
    @JsonProperty("SIGNSTATUS")
    public int sIGNSTATUS;
    @JsonProperty("GENTIME")
    public String gENTIME;
    @JsonProperty("STATIONRECEIVETIME")
    public String sTATIONRECEIVETIME;
    @JsonProperty("RECEIVETIME")
    public String rECEIVETIME;
    @JsonProperty("STATUS")
    public int sTATUS;
    @JsonProperty("RESPONSECODE")
    public int rESPONSECODE;
    @JsonProperty("RESPONSEINFO")
    public String rESPONSEINFO;
    @JsonProperty("NATIONRECEIVETIME")
    public String nATIONRECEIVETIME;
    @JsonProperty("PROTIME")
    public String pROTIME;
    @JsonProperty("BATCHFILENAME")
    public String bATCHFILENAME;
    @JsonProperty("SPLITFLAG")
    public int sPLITFLAG;
    @JsonProperty("PROSPLITTIME")
    public String pROSPLITTIME;
    @JsonProperty("PROSPLITTYPE")
    public int pROSPLITTYPE;
    @JsonProperty("SPLITOWNERCOUNT")
    public int sPLITOWNERCOUNT;
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
    public int cLEARFLAG;
    @JsonProperty("CLEARREMARK")
    public String cLEARREMARK;
    @JsonProperty("SPARE1")
    public String sPARE1;
    @JsonProperty("SPARE2")
    public String sPARE2;
    @JsonProperty("SPARE3")
    public String sPARE3;
    @JsonProperty("CHARGERESULT")
    public int cHARGERESULT;
    @JsonProperty("ISSUECHARGETIME")
    public String iSSUECHARGETIME;
    @JsonProperty("FIRSTCLEARFLAG")
    public int fIRSTCLEARFLAG;
    @JsonProperty("SPLITOWNERGROUP1")
    public String sPLITOWNERGROUP1;
    @JsonProperty("SPLITOWNERGROUP2")
    public String sPLITOWNERGROUP2;
    @JsonProperty("ORIGINALFLAG")
    public int oRIGINALFLAG;
    @JsonProperty("SPLITBASE")
    public int sPLITBASE;
    @JsonProperty("SPLITREMARK1")
    public String sPLITREMARK1;
    @JsonProperty("VACCINEFLAG")
    public int vACCINEFLAG;
    @JsonProperty("APPOINTID")
    public String aPPOINTID;
}
