package info.nemoworks.highlink.model;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExitTransaction implements HighwayTransaction {

    @JsonProperty("ID")
    private String iD;
    @JsonProperty("MODIFYFLAG")
    private int mODIFYFLAG;
    @JsonProperty("SOURCEID")
    private String sOURCEID;
    @JsonProperty("PAYFEE")
    private int pAYFEE;
    @JsonProperty("TRANSCODE")
    private String tRANSCODE;
    @JsonProperty("LDATE")
    private String lDATE;
    @JsonProperty("SHIFT")
    private String sHIFT;
    @JsonProperty("BATCHNUM")
    private String bATCHNUM;
    @JsonProperty("LOGINTIME")
    private String lOGINTIME;
    @JsonProperty("TRIGGERTIME")
    private String tRIGGERTIME;
    @JsonProperty("OPERID")
    private int oPERID;
    @JsonProperty("OPERNAME")
    private String oPERNAME;
    @JsonProperty("MONITOR")
    private int mONITOR;
    @JsonProperty("MONITORNAME")
    private String mONITORNAME;
    @JsonProperty("MONITORTIME")
    private String mONITORTIME;
    @JsonProperty("LANEAPPVER")
    private String lANEAPPVER;
    @JsonProperty("ENWEIGHT")
    private int eNWEIGHT;
    @JsonProperty("ENAXLECOUNT")
    private int eNAXLECOUNT;
    @JsonProperty("ENTOLLSTATION")
    private int eNTOLLSTATION;
    @JsonProperty("ENTOLLLANE")
    private String eNTOLLLANE;
    @JsonProperty("ENTOLLSTATIONHEX")
    private String eNTOLLSTATIONHEX;
    @JsonProperty("ENTOLLLANEHEX")
    private String eNTOLLLANEHEX;
    @JsonProperty("ENTOLLSTATIONID")
    private String eNTOLLSTATIONID;
    @JsonProperty("ENTOLLLANEID")
    private String eNTOLLLANEID;
    @JsonProperty("ENTIME")
    private String eNTIME;
    @JsonProperty("ENTRYOPERATORID")
    private int eNTRYOPERATORID;
    @JsonProperty("LANETYPE")
    private int lANETYPE;
    @JsonProperty("EXTOLLSTATION")
    private int eXTOLLSTATION;
    @JsonProperty("EXTOLLLANE")
    private int eXTOLLLANE;
    @JsonProperty("EXTOLLSTATIONHEX")
    private String eXTOLLSTATIONHEX;
    @JsonProperty("EXTOLLLANEHEX")
    private String eXTOLLLANEHEX;
    @JsonProperty("EXTOLLSTATIONID")
    private String eXTOLLSTATIONID;
    @JsonProperty("EXTOLLLANEID")
    private String eXTOLLLANEID;
    @JsonProperty("EXTIME")
    private String eXTIME;
    @JsonProperty("BL_SUBCENTER")
    private int bL_SUBCENTER;
    @JsonProperty("BL_CENTER")
    private int bL_CENTER;
    @JsonProperty("MEDIATYPE")
    private int mEDIATYPE;
    @JsonProperty("MEDIANO")
    private long mEDIANO;
    @JsonProperty("OBUSIGN")
    private String oBUSIGN;
    @JsonProperty("OBUISSUEFLAG")
    private String oBUISSUEFLAG;
    @JsonProperty("OBUID")
    private String oBUID;
    @JsonProperty("CARDCNT")
    private int cARDCNT;
    @JsonProperty("VCOUNT")
    private int vCOUNT;
    @JsonProperty("INDUCTCNT")
    private int iNDUCTCNT;
    @JsonProperty("ENVLP")
    private String eNVLP;
    @JsonProperty("ENVLPC")
    private int eNVLPC;
    @JsonProperty("EXVLP")
    private String eXVLP;
    @JsonProperty("EXVLPC")
    private int eXVLPC;
    @JsonProperty("IDENTIFYVLP")
    private String iDENTIFYVLP;
    @JsonProperty("IDENTIFYVLPC")
    private int iDENTIFYVLPC;
    @JsonProperty("ENVEHICLETYPE")
    private int eNVEHICLETYPE;
    @JsonProperty("EXVEHICLETYPE")
    private int eXVEHICLETYPE;
    @JsonProperty("ENVEHICLECLASS")
    private int eNVEHICLECLASS;
    @JsonProperty("EXVEHICLECLASS")
    private int eXVEHICLECLASS;
    @JsonProperty("EXWEIGHT")
    private int eXWEIGHT;
    @JsonProperty("AXISINFO")
    private long aXISINFO;
    @JsonProperty("LIMITWEIGHT")
    private int lIMITWEIGHT;
    @JsonProperty("OVERWEIGHTRATE")
    private int oVERWEIGHTRATE;
    @JsonProperty("AXLECOUNT")
    private int aXLECOUNT;
    @JsonProperty("ELECTRICALPERCENTAGE")
    private int eLECTRICALPERCENTAGE;
    @JsonProperty("TAC")
    private String tAC;
    @JsonProperty("TRANSTYPE")
    private String tRANSTYPE;
    @JsonProperty("TERMINALTRANSNO")
    private String tERMINALTRANSNO;
    @JsonProperty("TERMINALNO")
    private String tERMINALNO;
    @JsonProperty("SERVICETYPE")
    private int sERVICETYPE;
    @JsonProperty("ALGORITHMIDENTIFIER")
    private int aLGORITHMIDENTIFIER;
    @JsonProperty("TOLLDISTANCE")
    private int tOLLDISTANCE;
    @JsonProperty("REALDISTANCE")
    private int rEALDISTANCE;
    @JsonProperty("VSPEED")
    private int vSPEED;
    @JsonProperty("OVERTIME")
    private int oVERTIME;
    @JsonProperty("FREETYPE")
    private int fREETYPE;
    @JsonProperty("FREEMODE")
    private String fREEMODE;
    @JsonProperty("FREEINFO")
    private String fREEINFO;
    @JsonProperty("BALANCEBEFORE")
    private long bALANCEBEFORE;
    @JsonProperty("BALANCEAFTER")
    private long bALANCEAFTER;
    @JsonProperty("FEE")
    private int fEE;
    @JsonProperty("COLLECTFEE")
    private int cOLLECTFEE;
    @JsonProperty("DISCOUNTFEE")
    private int dISCOUNTFEE;
    @JsonProperty("REBATEMONEY")
    private int rEBATEMONEY;
    @JsonProperty("CARDCOSTFEE")
    private int cARDCOSTFEE;
    @JsonProperty("UNPAYFEE")
    private int uNPAYFEE;
    @JsonProperty("UNPAYFLAG")
    private int uNPAYFLAG;
    @JsonProperty("UNPAYCARDCOST")
    private int uNPAYCARDCOST;
    @JsonProperty("TICKETFEE")
    private int tICKETFEE;
    @JsonProperty("UNIFIEDFEE")
    private int uNIFIEDFEE;
    @JsonProperty("ENTOLLMONEY")
    private int eNTOLLMONEY;
    @JsonProperty("ENFREEMONEY")
    private int eNFREEMONEY;
    @JsonProperty("ENLASTMONEY")
    private int eNLASTMONEY;
    @JsonProperty("RETURNMONEYSN")
    private String rETURNMONEYSN;
    @JsonProperty("PAYCARDTYPE")
    private String pAYCARDTYPE;
    @JsonProperty("PAYCARDNET")
    private String pAYCARDNET;
    @JsonProperty("PAYCARDID")
    private String pAYCARDID;
    @JsonProperty("PAYCARDTRANSN")
    private int pAYCARDTRANSN;
    @JsonProperty("PAYTYPE")
    private int pAYTYPE;
    @JsonProperty("PAYORDERNUM")
    private String pAYORDERNUM;
    @JsonProperty("PAYREBATE")
    private int pAYREBATE;
    @JsonProperty("IDENTIFICATION")
    private int iDENTIFICATION;
    @JsonProperty("INVOICETYPE")
    private int iNVOICETYPE;
    @JsonProperty("INVOICECODE")
    private String iNVOICECODE;
    @JsonProperty("INVOICEID")
    private String iNVOICEID;
    @JsonProperty("INVOICECNT")
    private int iNVOICECNT;
    @JsonProperty("SIGNSTATUS")
    private int sIGNSTATUS;
    @JsonProperty("SPECIALTYPE")
    private String sPECIALTYPE;
    @JsonProperty("LANESPINFO")
    private double lANESPINFO;
    @JsonProperty("SPINFO")
    private String sPINFO;
    @JsonProperty("VEHICLESIGNID")
    private String vEHICLESIGNID;
    @JsonProperty("MULTIPROVINCE")
    private int mULTIPROVINCE;
    @JsonProperty("KEYNUM")
    private int kEYNUM;
    @JsonProperty("KEYPRESSINFO")
    private String kEYPRESSINFO;
    @JsonProperty("PARAVER")
    private String pARAVER;
    @JsonProperty("PROVINCEGROUP")
    private int pROVINCEGROUP;
    @JsonProperty("GANTRYIDGROUP")
    private String gANTRYIDGROUP;
    @JsonProperty("TOLLINTERVALSCOUNT")
    private int tOLLINTERVALSCOUNT;
    @JsonProperty("TOLLINTERVALSGROUP")
    private String tOLLINTERVALSGROUP;
    @JsonProperty("TRANSTIMEGROUP")
    private String tRANSTIMEGROUP;
    @JsonProperty("CHARGEFEEGROUP")
    private String cHARGEFEEGROUP;
    @JsonProperty("CHARGEDISCOUNTGROUP")
    private String cHARGEDISCOUNTGROUP;
    @JsonProperty("RATEMODEVERSIONGROUP")
    private String rATEMODEVERSIONGROUP;
    @JsonProperty("RATEPARAVERSIONGROUP")
    private String rATEPARAVERSIONGROUP;
    @JsonProperty("TOLLFEEGROUP")
    private int tOLLFEEGROUP;
    @JsonProperty("PASSID")
    private String pASSID;
    @JsonProperty("STATIONRECEIVETIME")
    private String sTATIONRECEIVETIME;
    @JsonProperty("RECEIVETIME")
    private String rECEIVETIME;
    @JsonProperty("VERIFYCODE")
    private String vERIFYCODE;
    @JsonProperty("VERIFYFLAG")
    private int vERIFYFLAG;
    @JsonProperty("TRANSFLAG")
    private int tRANSFLAG;
    @JsonProperty("REMARKS")
    private String rEMARKS;
    @JsonProperty("SPARE1")
    private String sPARE1;
    @JsonProperty("SPARE2")
    private String sPARE2;
    @JsonProperty("SPARE3")
    private String sPARE3;
    @JsonProperty("SPARE4")
    private String sPARE4;
    @JsonProperty("SPARE5")
    private String sPARE5;
    @JsonProperty("SUPPLIERID")
    private int sUPPLIERID;
    @JsonProperty("ROADTYPE")
    private int rOADTYPE;
    @JsonProperty("ENTOLLSTATIONNAME")
    private String eNTOLLSTATIONNAME;
    @JsonProperty("EXTOLLSTATIONNAME")
    private String eXTOLLSTATIONNAME;
    @JsonProperty("SECTIONGROUP")
    private String sECTIONGROUP;
    @JsonProperty("GANTRYPASSCOUNT")
    private String gANTRYPASSCOUNT;
    @JsonProperty("GANTRYPASSINFO")
    private String gANTRYPASSINFO;
    @JsonProperty("FEEPROVINFO")
    private String fEEPROVINFO;
    @JsonProperty("PAYCODE")
    private String pAYCODE;
    @JsonProperty("DESCRIPTION")
    private int dESCRIPTION;
    @JsonProperty("VERIFYPASSTIME")
    private String vERIFYPASSTIME;
    @JsonProperty("VEHICLESIGN")
    private String vEHICLESIGN;
    @JsonProperty("CHECKSIGN")
    private int cHECKSIGN;
    @JsonProperty("OBUVERSION")
    private int oBUVERSION;
    @JsonProperty("CARDVERSION")
    private int cARDVERSION;
    @JsonProperty("SHORTFEE")
    private int sHORTFEE;
    @JsonProperty("ACTUALFEECLASS")
    private int aCTUALFEECLASS;
    @JsonProperty("OBUTOTALCOUNT")
    private int oBUTOTALCOUNT;
    @JsonProperty("NOCARDCOUNT")
    private int nOCARDCOUNT;
    @JsonProperty("ETCTOTALAMOUNT")
    private int eTCTOTALAMOUNT;
    @JsonProperty("FEEBOARDPLAY")
    private int fEEBOARDPLAY;
    @JsonProperty("SPCRATEVERSION")
    private int sPCRATEVERSION;
    @JsonProperty("CHARGEMODE")
    private int cHARGEMODE;
    @JsonProperty("TRANSPAYTYPE")
    private int tRANSPAYTYPE;
    @JsonProperty("FEEMILEAGE")
    private int fEEMILEAGE;
    @JsonProperty("PROVFEE")
    private int pROVFEE;
    @JsonProperty("PROVTRANSCOUNT")
    private int pROVTRANSCOUNT;
    @JsonProperty("PROVINCECOUNT")
    private int pROVINCECOUNT;
    @JsonProperty("PROVINCETRANSGROUP")
    private String pROVINCETRANSGROUP;
    @JsonProperty("TRANSFEE")
    private int tRANSFEE;
    @JsonProperty("OBUPAYFEE")
    private String oBUPAYFEE;
    @JsonProperty("OBUDISCOUNTFEE")
    private String oBUDISCOUNTFEE;
    @JsonProperty("SHORTFEEMILEAGE")
    private int sHORTFEEMILEAGE;
    @JsonProperty("OBUTOTALAMOUNT")
    private String oBUTOTALAMOUNT;
    @JsonProperty("OBUTOTALDISCOUNTAMOUNT")
    private String oBUTOTALDISCOUNTAMOUNT;
    @JsonProperty("TOLLPROVINCEID")
    private int tOLLPROVINCEID;
    @JsonProperty("OPENTYPE")
    private int oPENTYPE;
    @JsonProperty("DISCOUNTTYPE")
    private String dISCOUNTTYPE;
    @JsonProperty("PROVINCEDISCOUNTFEE")
    private int pROVINCEDISCOUNTFEE;
    @JsonProperty("ORIGINFEE")
    private int oRIGINFEE;
    @JsonProperty("FEERATE")
    private int fEERATE;
    @JsonProperty("DISPUTEDSTATUS")
    private int dISPUTEDSTATUS;
    @JsonProperty("DISPUTETIME")
    private String dISPUTETIME;
    @JsonProperty("LETGOFLAG")
    private int lETGOFLAG;
    @JsonProperty("VACCINEFLAG")
    private int vACCINEFLAG;
    @JsonProperty("WASTESPARE1")
    private String wASTESPARE1;
    @JsonProperty("WASTESPARE2")
    private String wASTESPARE2;
    @JsonProperty("WASTESPARE3")
    private String wASTESPARE3;
    @JsonProperty("WASTESPARE4")
    private String wASTESPARE4;
    @JsonProperty("WASTESPARE5")
    private int wASTESPARE5;
    @JsonProperty("APPOINTID")
    private String aPPOINTID;
    @JsonProperty("ORIGINALFLAG")
    private int oRIGINALFLAG;
    @JsonProperty("EXINDISPUTEDTYPE")
    private int eXINDISPUTEDTYPE;

    @Override
    public String getID() {
        return this.iD;
    }

}
