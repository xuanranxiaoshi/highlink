package info.nemoworks.highlink.model.exitTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.model.splitTransaction.SplitResult;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExitLocalETCTrans implements HighwayTransaction, PathTransaction, SplitResult {
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
    @JsonProperty("OPEN")
    public int oPEN;
    @JsonProperty("LANETYPE")
    public int lANETYPE;
    @JsonProperty("BL_SUBCENTER")
    public String bL_SUBCENTER;
    @JsonProperty("BL_CENTER")
    public String bL_CENTER;
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
    @JsonProperty("CHARGESTATUS")
    public int cHARGESTATUS;
    @JsonProperty("ISSUECHARGETIME")
    public String iSSUECHARGETIME;
    @JsonProperty("CHARGEBATCH")
    public String cHARGEBATCH;
    @JsonProperty("SIGNSTATUS")
    public int sIGNSTATUS;
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
    @JsonProperty("PACKSTATUS")
    public int pACKSTATUS;
    @JsonProperty("MESSAGEID")
    public String mESSAGEID;
    @JsonProperty("NATIONCLEARDATE")
    public String nATIONCLEARDATE;
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
    @JsonProperty("REMARKS")
    public String rEMARKS;
    @JsonProperty("SPARE1")
    public String sPARE1;
    @JsonProperty("SPARE2")
    public String sPARE2;
    @JsonProperty("SPARE3")
    public String sPARE3;
    @JsonProperty("FEERATE")
    public int fEERATE;
    @JsonProperty("FIRSTCLEARFLAG")
    public int fIRSTCLEARFLAG;
    @JsonProperty("SPLITOWNERGROUP1")
    public String sPLITOWNERGROUP1;
    @JsonProperty("SPLITOWNERGROUP2")
    public String sPLITOWNERGROUP2;
    @JsonProperty("ORIGINALFLAG")
    public int oRIGINALFLAG;
    @JsonProperty("PACKTIME")
    public String pACKTIME;
    @JsonProperty("SPLITBASE")
    public int sPLITBASE;
    @JsonProperty("SPLITREMARK1")
    public String sPLITREMARK1;
    @JsonProperty("VACCINEFLAG")
    public int vACCINEFLAG;
    @JsonProperty("APPOINTID")
    public String aPPOINTID;

    @JsonIgnore
    public int version = 1;
    @Override
    public String peekTime() {
        return this.getEXTIME();
    }
}
