package info.nemoworks.highlink.model.exitTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExitForeignETCTrans implements HighwayTransaction{
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("MODIFYFLAG")
    public int mODIFYFLAG;
    @JsonProperty("MULTIPROVINCE")
    public int mULTIPROVINCE;
    @JsonProperty("MEDIATYPE")
    public int mEDIATYPE;
    @JsonProperty("MEDIANO")
    public long mEDIANO;
    @JsonProperty("TRANSPAYTYPE")
    public int tRANSPAYTYPE;
    @JsonProperty("TOLLPROVINCEID")
    public int tOLLPROVINCEID;
    @JsonProperty("ISSUERID")
    public int iSSUERID;
    @JsonProperty("OBUSIGN")
    public String oBUSIGN;
    @JsonProperty("IDENTIFICATION")
    public int iDENTIFICATION;
    @JsonProperty("OBUID")
    public String oBUID;
    @JsonProperty("ETCCARDTYPE")
    public int eTCCARDTYPE;
    @JsonProperty("ETCCARDNET")
    public int eTCCARDNET;
    @JsonProperty("ETCCARDID")
    public long eTCCARDID;
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
    @JsonProperty("TAC")
    public String tAC;
    @JsonProperty("TRANSFEE")
    public int tRANSFEE;
    @JsonProperty("TRANSTYPE")
    public String tRANSTYPE;
    @JsonProperty("PAYCARDTRANSN")
    public int pAYCARDTRANSN;
    @JsonProperty("TERMINALTRANSNO")
    public String tERMINALTRANSNO;
    @JsonProperty("TERMINALNO")
    public String tERMINALNO;
    @JsonProperty("PREBALANCE")
    public long pREBALANCE;
    @JsonProperty("POSTBALANCE")
    public long pOSTBALANCE;
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
    public int eNTOLLSTATION;
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
    public int eXTOLLSTATION;
    @JsonProperty("EXTOLLLANE")
    public int eXTOLLLANE;
    @JsonProperty("EXTOLLSTATIONID")
    public String eXTOLLSTATIONID;
    @JsonProperty("EXTOLLLANEID")
    public String eXTOLLLANEID;
    @JsonProperty("EXTOLLSTATIONHEX")
    public int eXTOLLSTATIONHEX;
    @JsonProperty("EXTOLLLANEHEX")
    public long eXTOLLLANEHEX;
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
    @JsonProperty("OPEN")
    public int oPEN;
    @JsonProperty("LANETYPE")
    public int lANETYPE;
    @JsonProperty("BL_SUBCENTER")
    public int bL_SUBCENTER;
    @JsonProperty("BL_CENTER")
    public int bL_CENTER;
    @JsonProperty("LDATE")
    public String lDATE;
    @JsonProperty("BATCHNUM")
    public String bATCHNUM;
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
    @JsonProperty("EXITFEETYPE")
    public int eXITFEETYPE;
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
    @JsonProperty("SIGNSTATUS")
    public int sIGNSTATUS;
    @JsonProperty("ROADTYPE")
    public int rOADTYPE;
    @JsonProperty("VERIFYPASSTIME")
    public String vERIFYPASSTIME;
    @JsonProperty("STATIONRECEIVETIME")
    public String sTATIONRECEIVETIME;
    @JsonProperty("RECEIVETIME")
    public String rECEIVETIME;
    @JsonProperty("GENTIME")
    public String gENTIME;
    @JsonProperty("MESSAGEID")
    public String mESSAGEID;
    @JsonProperty("REMARKS")
    public String rEMARKS;
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
    @JsonProperty("CHARGESTATUS")
    public int cHARGESTATUS;
    @JsonProperty("ISSUECHARGETIME")
    public String iSSUECHARGETIME;
    @JsonProperty("CHARGERECEIVETIME")
    public String cHARGERECEIVETIME;
    @JsonProperty("CHARGETIME")
    public String cHARGETIME;
    @JsonProperty("DISPUTEDTYPE")
    public String dISPUTEDTYPE;
    @JsonProperty("DISPUTEDDESC")
    public String dISPUTEDDESC;
    @JsonProperty("DISPUTEDPROTIME")
    public String dISPUTEDPROTIME;
    @JsonProperty("DISPUTEDVERSION")
    public String dISPUTEDVERSION;
    @JsonProperty("NATIONCLEARDATE")
    public String nATIONCLEARDATE;
    @JsonProperty("NATIONCLEARFLAG")
    public int nATIONCLEARFLAG;
    @JsonProperty("NATIONCLEARTIME")
    public String nATIONCLEARTIME;
    @JsonProperty("SPLITINFEE")
    public int sPLITINFEE;
    @JsonProperty("SPLITCROSSFEE")
    public int sPLITCROSSFEE;
    @JsonProperty("NATIONSPLITTIME")
    public String nATIONSPLITTIME;
    @JsonProperty("NATIONSPLITTYPE")
    public int nATIONSPLITTYPE;
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
    @JsonProperty("SPLITCHECKFLAG")
    public int sPLITCHECKFLAG;
    @JsonProperty("SPLITCNT")
    public int sPLITCNT;
    @JsonProperty("SPLITFEE")
    public int sPLITFEE;
    @JsonProperty("REALSPLITCNT")
    public int rEALSPLITCNT;
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
    @JsonProperty("CHARGEFEE")
    public int cHARGEFEE;
    @JsonProperty("CHARGEDISCOUNTTYPE")
    public String cHARGEDISCOUNTTYPE;
    @JsonProperty("CHARGEPROVINCEDISCOUNTFEE")
    public String cHARGEPROVINCEDISCOUNTFEE;
    @JsonProperty("CHARGERORIGINFEE")
    public String cHARGERORIGINFEE;
    @JsonProperty("FEERATE")
    public int fEERATE;
    @JsonProperty("SPLITOWNERGROUP1")
    public String sPLITOWNERGROUP1;
    @JsonProperty("SPLITOWNERGROUP2")
    public String sPLITOWNERGROUP2;
    @JsonProperty("SECFREETYPE")
    public int sECFREETYPE;
    @JsonProperty("SECPRORESULT")
    public int sECPRORESULT;
    @JsonProperty("SECPROTIME")
    public String sECPROTIME;
    @JsonProperty("SECBEFFEE")
    public String sECBEFFEE;
    @JsonProperty("SECFREEFEE")
    public String sECFREEFEE;
    @JsonProperty("ORIGINALFLAG")
    public int oRIGINALFLAG;
    @JsonProperty("PACKTIME")
    public String pACKTIME;
    @JsonProperty("SPLITCHECKDATE")
    public String sPLITCHECKDATE;
    @JsonProperty("SPLITCHECKSTATUS")
    public String sPLITCHECKSTATUS;
    @JsonProperty("SPLITCHECKTIME")
    public String sPLITCHECKTIME;
    @JsonProperty("VACCINEFLAG")
    public int vACCINEFLAG;
    @JsonProperty("APPOINTID")
    public String aPPOINTID;
    @JsonProperty("CHARGEAPPOINTID")
    public String cHARGEAPPOINTID;
}
