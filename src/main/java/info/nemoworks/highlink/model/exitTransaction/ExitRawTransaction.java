package info.nemoworks.highlink.model.exitTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.model.mapper.BinaryToHexDeserializer;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Data
public class ExitRawTransaction implements HighwayTransaction, PathTransaction {

    @JsonProperty(value = "ID", access = JsonProperty.Access.WRITE_ONLY)
    private String iD;
    @JsonProperty(value = "MODIFYFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int mODIFYFLAG;
    @JsonProperty(value = "SOURCEID", access = JsonProperty.Access.WRITE_ONLY)
    private String sOURCEID;
    @JsonProperty(value = "PAYFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int pAYFEE;
    @JsonProperty(value = "TRANSCODE", access = JsonProperty.Access.WRITE_ONLY)
    private String tRANSCODE;
    @JsonProperty(value = "LDATE", access = JsonProperty.Access.WRITE_ONLY)
    private String lDATE;
    @JsonProperty(value = "SHIFT", access = JsonProperty.Access.WRITE_ONLY)
    private String sHIFT;
    @JsonProperty(value = "BATCHNUM", access = JsonProperty.Access.WRITE_ONLY)
    private String bATCHNUM;
    @JsonProperty(value = "LOGINTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String lOGINTIME;
    @JsonProperty(value = "TRIGGERTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String tRIGGERTIME;
    @JsonProperty(value = "OPERID", access = JsonProperty.Access.WRITE_ONLY)
    private int oPERID;
    @JsonProperty(value = "OPERNAME", access = JsonProperty.Access.WRITE_ONLY)
    private String oPERNAME;
    @JsonProperty(value = "MONITOR", access = JsonProperty.Access.WRITE_ONLY)
    private int mONITOR;
    @JsonProperty(value = "MONITORNAME", access = JsonProperty.Access.WRITE_ONLY)
    private String mONITORNAME;
    @JsonProperty(value = "MONITORTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String mONITORTIME;
    @JsonProperty(value = "LANEAPPVER", access = JsonProperty.Access.WRITE_ONLY)
    private String lANEAPPVER;
    @JsonProperty(value = "ENWEIGHT", access = JsonProperty.Access.WRITE_ONLY)
    private int eNWEIGHT;
    @JsonProperty(value = "ENAXLECOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int eNAXLECOUNT;
    @JsonProperty(value = "ENTOLLSTATION", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTOLLSTATION;
    @JsonProperty(value = "ENTOLLLANE", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTOLLLANE;
    @JsonProperty(value = "ENTOLLSTATIONHEX", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTOLLSTATIONHEX;
    @JsonProperty(value = "ENTOLLLANEHEX", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTOLLLANEHEX;
    @JsonProperty(value = "ENTOLLSTATIONID", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTOLLSTATIONID;
    @JsonProperty(value = "ENTOLLLANEID", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTOLLLANEID;
    @JsonProperty(value = "ENTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTIME;
    @JsonProperty(value = "ENTRYOPERATORID", access = JsonProperty.Access.WRITE_ONLY)
    private int eNTRYOPERATORID;
    @JsonProperty(value = "LANETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int lANETYPE;
    @JsonProperty(value = "EXTOLLSTATION", access = JsonProperty.Access.WRITE_ONLY)
    private String eXTOLLSTATION;
    @JsonProperty(value = "EXTOLLLANE", access = JsonProperty.Access.WRITE_ONLY)
    private String eXTOLLLANE;
    @JsonProperty(value = "EXTOLLSTATIONHEX", access = JsonProperty.Access.WRITE_ONLY)
    private String eXTOLLSTATIONHEX;
    @JsonProperty(value = "EXTOLLLANEHEX", access = JsonProperty.Access.WRITE_ONLY)
    private String eXTOLLLANEHEX;
    @JsonProperty(value = "EXTOLLSTATIONID", access = JsonProperty.Access.WRITE_ONLY)
    private String eXTOLLSTATIONID;
    @JsonProperty(value = "EXTOLLLANEID", access = JsonProperty.Access.WRITE_ONLY)
    private String eXTOLLLANEID;
    @JsonProperty(value = "EXTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String eXTIME;
    @JsonProperty(value = "BL_SUBCENTER", access = JsonProperty.Access.WRITE_ONLY)
    private int bL_SUBCENTER;
    @JsonProperty(value = "BL_CENTER", access = JsonProperty.Access.WRITE_ONLY)
    private int bL_CENTER;
    @JsonProperty(value = "MEDIATYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int mEDIATYPE;
    @JsonProperty(value = "MEDIANO", access = JsonProperty.Access.WRITE_ONLY)
    private long mEDIANO;
    @JsonProperty(value = "OBUSIGN", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUSIGN;
    @JsonProperty(value = "OBUISSUEFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUISSUEFLAG;
    @JsonProperty(value = "OBUID", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUID;
    @JsonProperty(value = "CARDCNT", access = JsonProperty.Access.WRITE_ONLY)
    private int cARDCNT;
    @JsonProperty(value = "VCOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int vCOUNT;
    @JsonProperty(value = "INDUCTCNT", access = JsonProperty.Access.WRITE_ONLY)
    private int iNDUCTCNT;
    @JsonProperty(value = "ENVLP", access = JsonProperty.Access.WRITE_ONLY)
    private String eNVLP;
    @JsonProperty(value = "ENVLPC", access = JsonProperty.Access.WRITE_ONLY)
    private int eNVLPC;
    @JsonProperty(value = "EXVLP", access = JsonProperty.Access.WRITE_ONLY)
    private String eXVLP;
    @JsonProperty(value = "EXVLPC", access = JsonProperty.Access.WRITE_ONLY)
    private int eXVLPC;
    @JsonProperty(value = "IDENTIFYVLP", access = JsonProperty.Access.WRITE_ONLY)
    private String iDENTIFYVLP;
    @JsonProperty(value = "IDENTIFYVLPC", access = JsonProperty.Access.WRITE_ONLY)
    private int iDENTIFYVLPC;
    @JsonProperty(value = "ENVEHICLETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int eNVEHICLETYPE;
    @JsonProperty(value = "EXVEHICLETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int eXVEHICLETYPE;
    @JsonProperty(value = "ENVEHICLECLASS", access = JsonProperty.Access.WRITE_ONLY)
    private int eNVEHICLECLASS;
    @JsonProperty(value = "EXVEHICLECLASS", access = JsonProperty.Access.WRITE_ONLY)
    private int eXVEHICLECLASS;
    @JsonProperty(value = "EXWEIGHT", access = JsonProperty.Access.WRITE_ONLY)
    private int eXWEIGHT;
    @JsonProperty(value = "AXISINFO", access = JsonProperty.Access.WRITE_ONLY)
    private long aXISINFO;
    @JsonProperty(value = "LIMITWEIGHT", access = JsonProperty.Access.WRITE_ONLY)
    private int lIMITWEIGHT;
    @JsonProperty(value = "OVERWEIGHTRATE", access = JsonProperty.Access.WRITE_ONLY)
    private int oVERWEIGHTRATE;
    @JsonProperty(value = "AXLECOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int aXLECOUNT;
    @JsonProperty(value = "ELECTRICALPERCENTAGE", access = JsonProperty.Access.WRITE_ONLY)
    private int eLECTRICALPERCENTAGE;
    @JsonProperty(value = "TAC", access = JsonProperty.Access.WRITE_ONLY)
    private String tAC;
    @JsonProperty(value = "TRANSTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String tRANSTYPE;
    @JsonProperty(value = "TERMINALTRANSNO", access = JsonProperty.Access.WRITE_ONLY)
    private String tERMINALTRANSNO;
    @JsonProperty(value = "TERMINALNO", access = JsonProperty.Access.WRITE_ONLY)
    private String tERMINALNO;
    @JsonProperty(value = "SERVICETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int sERVICETYPE;
    @JsonProperty(value = "ALGORITHMIDENTIFIER", access = JsonProperty.Access.WRITE_ONLY)
    private int aLGORITHMIDENTIFIER;
    @JsonProperty(value = "TOLLDISTANCE", access = JsonProperty.Access.WRITE_ONLY)
    private int tOLLDISTANCE;
    @JsonProperty(value = "REALDISTANCE", access = JsonProperty.Access.WRITE_ONLY)
    private int rEALDISTANCE;
    @JsonProperty(value = "VSPEED", access = JsonProperty.Access.WRITE_ONLY)
    private int vSPEED;
    @JsonProperty(value = "OVERTIME", access = JsonProperty.Access.WRITE_ONLY)
    private int oVERTIME;
    @JsonProperty(value = "FREETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int fREETYPE;
    @JsonProperty(value = "FREEMODE", access = JsonProperty.Access.WRITE_ONLY)
    private String fREEMODE;
    @JsonProperty(value = "FREEINFO", access = JsonProperty.Access.WRITE_ONLY)
    private String fREEINFO;
    @JsonProperty(value = "BALANCEBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private long bALANCEBEFORE;
    @JsonProperty(value = "BALANCEAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private long bALANCEAFTER;
    @JsonProperty(value = "FEE", access = JsonProperty.Access.WRITE_ONLY)
    private int fEE;
    @JsonProperty(value = "COLLECTFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int cOLLECTFEE;
    @JsonProperty(value = "DISCOUNTFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int dISCOUNTFEE;
    @JsonProperty(value = "REBATEMONEY", access = JsonProperty.Access.WRITE_ONLY)
    private int rEBATEMONEY;
    @JsonProperty(value = "CARDCOSTFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int cARDCOSTFEE;
    @JsonProperty(value = "UNPAYFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int uNPAYFEE;
    @JsonProperty(value = "UNPAYFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int uNPAYFLAG;
    @JsonProperty(value = "UNPAYCARDCOST", access = JsonProperty.Access.WRITE_ONLY)
    private int uNPAYCARDCOST;
    @JsonProperty(value = "TICKETFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int tICKETFEE;
    @JsonProperty(value = "UNIFIEDFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int uNIFIEDFEE;
    @JsonProperty(value = "ENTOLLMONEY", access = JsonProperty.Access.WRITE_ONLY)
    private int eNTOLLMONEY;
    @JsonProperty(value = "ENFREEMONEY", access = JsonProperty.Access.WRITE_ONLY)
    private int eNFREEMONEY;
    @JsonProperty(value = "ENLASTMONEY", access = JsonProperty.Access.WRITE_ONLY)
    private int eNLASTMONEY;
    @JsonProperty(value = "RETURNMONEYSN", access = JsonProperty.Access.WRITE_ONLY)
    private String rETURNMONEYSN;
    @JsonProperty(value = "PAYCARDTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String pAYCARDTYPE;
    @JsonProperty(value = "PAYCARDNET", access = JsonProperty.Access.WRITE_ONLY)
    private String pAYCARDNET;
    @JsonProperty(value = "PAYCARDID", access = JsonProperty.Access.WRITE_ONLY)
    private String pAYCARDID;
    @JsonProperty(value = "PAYCARDTRANSN", access = JsonProperty.Access.WRITE_ONLY)
    private int pAYCARDTRANSN;
    @JsonProperty(value = "PAYTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int pAYTYPE;
    @JsonProperty(value = "PAYORDERNUM", access = JsonProperty.Access.WRITE_ONLY)
    private String pAYORDERNUM;
    @JsonProperty(value = "PAYREBATE", access = JsonProperty.Access.WRITE_ONLY)
    private int pAYREBATE;
    @JsonProperty(value = "IDENTIFICATION", access = JsonProperty.Access.WRITE_ONLY)
    private int iDENTIFICATION;
    @JsonProperty(value = "INVOICETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int iNVOICETYPE;
    @JsonProperty(value = "INVOICECODE", access = JsonProperty.Access.WRITE_ONLY)
    private String iNVOICECODE;
    @JsonProperty(value = "INVOICEID", access = JsonProperty.Access.WRITE_ONLY)
    private String iNVOICEID;
    @JsonProperty(value = "INVOICECNT", access = JsonProperty.Access.WRITE_ONLY)
    private int iNVOICECNT;
    @JsonProperty(value = "SIGNSTATUS", access = JsonProperty.Access.WRITE_ONLY)
    private int sIGNSTATUS;
    @JsonProperty(value = "SPECIALTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String sPECIALTYPE;
    @JsonProperty(value = "LANESPINFO", access = JsonProperty.Access.WRITE_ONLY)
    private double lANESPINFO;
    @JsonProperty(value = "SPINFO", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = BinaryToHexDeserializer.class)
    private String sPINFO;
    @JsonProperty(value = "VEHICLESIGNID", access = JsonProperty.Access.WRITE_ONLY)
    private String vEHICLESIGNID;
    @JsonProperty(value = "MULTIPROVINCE", access = JsonProperty.Access.WRITE_ONLY)
    private int mULTIPROVINCE;
    @JsonProperty(value = "KEYNUM", access = JsonProperty.Access.WRITE_ONLY)
    private int kEYNUM;
    @JsonProperty(value = "KEYPRESSINFO", access = JsonProperty.Access.WRITE_ONLY)
    private String kEYPRESSINFO;
    @JsonProperty(value = "PARAVER", access = JsonProperty.Access.WRITE_ONLY)
    private String pARAVER;
    @JsonProperty(value = "PROVINCEGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private int pROVINCEGROUP;
    @JsonProperty(value = "GANTRYIDGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYIDGROUP;
    @JsonProperty(value = "TOLLINTERVALSCOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int tOLLINTERVALSCOUNT;
    @JsonProperty(value = "TOLLINTERVALSGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String tOLLINTERVALSGROUP;
    @JsonProperty(value = "TRANSTIMEGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String tRANSTIMEGROUP;
    @JsonProperty(value = "CHARGEFEEGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String cHARGEFEEGROUP;
    @JsonProperty(value = "CHARGEDISCOUNTGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String cHARGEDISCOUNTGROUP;
    @JsonProperty(value = "RATEMODEVERSIONGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String rATEMODEVERSIONGROUP;
    @JsonProperty(value = "RATEPARAVERSIONGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String rATEPARAVERSIONGROUP;
    @JsonProperty(value = "TOLLFEEGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private int tOLLFEEGROUP;
    @JsonProperty(value = "PASSID", access = JsonProperty.Access.WRITE_ONLY)
    private String pASSID;
    @JsonProperty(value = "STATIONRECEIVETIME", access = JsonProperty.Access.WRITE_ONLY)
    private String sTATIONRECEIVETIME;
    @JsonProperty(value = "RECEIVETIME", access = JsonProperty.Access.WRITE_ONLY)
    private String rECEIVETIME;
    @JsonProperty(value = "VERIFYCODE", access = JsonProperty.Access.WRITE_ONLY)
    private String vERIFYCODE;
    @JsonProperty(value = "VERIFYFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int vERIFYFLAG;
    @JsonProperty(value = "TRANSFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int tRANSFLAG;
    @JsonProperty(value = "REMARKS", access = JsonProperty.Access.WRITE_ONLY)
    private String rEMARKS;
    @JsonProperty(value = "SPARE1", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE1;
    @JsonProperty(value = "SPARE2", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE2;
    @JsonProperty(value = "SPARE3", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE3;
    @JsonProperty(value = "SPARE4", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE4;
    @JsonProperty(value = "SPARE5", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE5;
    @JsonProperty(value = "SUPPLIERID", access = JsonProperty.Access.WRITE_ONLY)
    private int sUPPLIERID;
    @JsonProperty(value = "ROADTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int rOADTYPE;
    @JsonProperty(value = "ENTOLLSTATIONNAME", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTOLLSTATIONNAME;
    @JsonProperty(value = "EXTOLLSTATIONNAME", access = JsonProperty.Access.WRITE_ONLY)
    private String eXTOLLSTATIONNAME;
    @JsonProperty(value = "SECTIONGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String sECTIONGROUP;
    @JsonProperty(value = "GANTRYPASSCOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYPASSCOUNT;
    @JsonProperty(value = "GANTRYPASSINFO", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYPASSINFO;
    @JsonProperty(value = "FEEPROVINFO", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEPROVINFO;
    @JsonProperty(value = "PAYCODE", access = JsonProperty.Access.WRITE_ONLY)
    private String pAYCODE;
    @JsonProperty(value = "DESCRIPTION", access = JsonProperty.Access.WRITE_ONLY)
    private int dESCRIPTION;
    @JsonProperty(value = "VERIFYPASSTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String vERIFYPASSTIME;
    @JsonProperty(value = "VEHICLESIGN", access = JsonProperty.Access.WRITE_ONLY)
    private String vEHICLESIGN;
    @JsonProperty(value = "CHECKSIGN", access = JsonProperty.Access.WRITE_ONLY)
    private int cHECKSIGN;
    @JsonProperty(value = "OBUVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUVERSION;
    @JsonProperty(value = "CARDVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private int cARDVERSION;
    @JsonProperty(value = "SHORTFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int sHORTFEE;
    @JsonProperty(value = "ACTUALFEECLASS", access = JsonProperty.Access.WRITE_ONLY)
    private int aCTUALFEECLASS;
    @JsonProperty(value = "OBUTOTALCOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUTOTALCOUNT;
    @JsonProperty(value = "NOCARDCOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int nOCARDCOUNT;
    @JsonProperty(value = "ETCTOTALAMOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int eTCTOTALAMOUNT;
    @JsonProperty(value = "FEEBOARDPLAY", access = JsonProperty.Access.WRITE_ONLY)
    private int fEEBOARDPLAY;
    @JsonProperty(value = "SPCRATEVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private int sPCRATEVERSION;
    @JsonProperty(value = "CHARGEMODE", access = JsonProperty.Access.WRITE_ONLY)
    private int cHARGEMODE;
    @JsonProperty(value = "TRANSPAYTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int tRANSPAYTYPE;
    @JsonProperty(value = "FEEMILEAGE", access = JsonProperty.Access.WRITE_ONLY)
    private int fEEMILEAGE;
    @JsonProperty(value = "PROVFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int pROVFEE;
    @JsonProperty(value = "PROVTRANSCOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int pROVTRANSCOUNT;
    @JsonProperty(value = "PROVINCECOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int pROVINCECOUNT;
    @JsonProperty(value = "PROVINCETRANSGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String pROVINCETRANSGROUP;
    @JsonProperty(value = "TRANSFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int tRANSFEE;
    @JsonProperty(value = "OBUPAYFEE", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUPAYFEE;
    @JsonProperty(value = "OBUDISCOUNTFEE", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUDISCOUNTFEE;
    @JsonProperty(value = "SHORTFEEMILEAGE", access = JsonProperty.Access.WRITE_ONLY)
    private int sHORTFEEMILEAGE;
    @JsonProperty(value = "OBUTOTALAMOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUTOTALAMOUNT;
    @JsonProperty(value = "OBUTOTALDISCOUNTAMOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUTOTALDISCOUNTAMOUNT;
    @JsonProperty(value = "TOLLPROVINCEID", access = JsonProperty.Access.WRITE_ONLY)
    private int tOLLPROVINCEID;
    @JsonProperty(value = "OPENTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int oPENTYPE;
    @JsonProperty(value = "DISCOUNTTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String dISCOUNTTYPE;
    @JsonProperty(value = "PROVINCEDISCOUNTFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int pROVINCEDISCOUNTFEE;
    @JsonProperty(value = "ORIGINFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int oRIGINFEE;
    @JsonProperty(value = "FEERATE", access = JsonProperty.Access.WRITE_ONLY)
    private int fEERATE;
    @JsonProperty(value = "DISPUTEDSTATUS", access = JsonProperty.Access.WRITE_ONLY)
    private int dISPUTEDSTATUS;
    @JsonProperty(value = "DISPUTETIME", access = JsonProperty.Access.WRITE_ONLY)
    private String dISPUTETIME;
    @JsonProperty(value = "LETGOFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int lETGOFLAG;
    @JsonProperty(value = "VACCINEFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int vACCINEFLAG;
    @JsonProperty(value = "WASTESPARE1", access = JsonProperty.Access.WRITE_ONLY)
    private String wASTESPARE1;
    @JsonProperty(value = "WASTESPARE2", access = JsonProperty.Access.WRITE_ONLY)
    private String wASTESPARE2;
    @JsonProperty(value = "WASTESPARE3", access = JsonProperty.Access.WRITE_ONLY)
    private String wASTESPARE3;
    @JsonProperty(value = "WASTESPARE4", access = JsonProperty.Access.WRITE_ONLY)
    private String wASTESPARE4;
    @JsonProperty(value = "WASTESPARE5", access = JsonProperty.Access.WRITE_ONLY)
    private int wASTESPARE5;
    @JsonProperty(value = "APPOINTID", access = JsonProperty.Access.WRITE_ONLY)
    private String aPPOINTID;
    @JsonProperty(value = "ORIGINALFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int oRIGINALFLAG;
    @JsonProperty(value = "EXINDISPUTEDTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int eXINDISPUTEDTYPE;

    public boolean peekPrimaryTrans() {
        return "09".equals(this.getTRANSTYPE());
    }

    public boolean peekOBU() {
        return this.getMEDIATYPE() != 1;
    }

    public boolean peekPayWithEtc() {
        return this.getPAYTYPE() == 4;
    }

    public boolean peekLocal() {
        return this.getIDENTIFYVLP().contains("鲁");
    }

    public boolean peekGreenCar() {
        return this.getSPECIALTYPE().contains("5");
    }

    public boolean peekTruck() {
        return this.getEXVEHICLETYPE() == 12;
    }

    @Override
    public String peekTime() {
        return eXTIME;
    }
}
