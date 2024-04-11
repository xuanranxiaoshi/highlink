package info.nemoworks.highlink.model.exitTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.model.mapper.BinaryToHexDeserializer;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Data
public class ExitRawTransaction implements HighwayTransaction, PathTransaction {

    @JsonProperty(value = "ID"  )
    private String iD;
    @JsonProperty(value = "MODIFYFLAG"  )
    private int mODIFYFLAG;
    @JsonProperty(value = "SOURCEID"  )
    private String sOURCEID;
    @JsonProperty(value = "PAYFEE"  )
    private int pAYFEE;
    @JsonProperty(value = "TRANSCODE"  )
    private String tRANSCODE;
    @JsonProperty(value = "LDATE"  )
    private String lDATE;
    @JsonProperty(value = "SHIFT"  )
    private String sHIFT;
    @JsonProperty(value = "BATCHNUM"  )
    private String bATCHNUM;
    @JsonProperty(value = "LOGINTIME"  )
    private String lOGINTIME;
    @JsonProperty(value = "TRIGGERTIME"  )
    private String tRIGGERTIME;
    @JsonProperty(value = "OPERID"  )
    private int oPERID;
    @JsonProperty(value = "OPERNAME"  )
    private String oPERNAME;
    @JsonProperty(value = "MONITOR"  )
    private int mONITOR;
    @JsonProperty(value = "MONITORNAME"  )
    private String mONITORNAME;
    @JsonProperty(value = "MONITORTIME"  )
    private String mONITORTIME;
    @JsonProperty(value = "LANEAPPVER"  )
    private String lANEAPPVER;
    @JsonProperty(value = "ENWEIGHT"  )
    private int eNWEIGHT;
    @JsonProperty(value = "ENAXLECOUNT"  )
    private int eNAXLECOUNT;
    @JsonProperty(value = "ENTOLLSTATION"  )
    private String eNTOLLSTATION;
    @JsonProperty(value = "ENTOLLLANE"  )
    private String eNTOLLLANE;
    @JsonProperty(value = "ENTOLLSTATIONHEX"  )
    private String eNTOLLSTATIONHEX;
    @JsonProperty(value = "ENTOLLLANEHEX"  )
    private String eNTOLLLANEHEX;
    @JsonProperty(value = "ENTOLLSTATIONID"  )
    private String eNTOLLSTATIONID;
    @JsonProperty(value = "ENTOLLLANEID"  )
    private String eNTOLLLANEID;
    @JsonProperty(value = "ENTIME"  )
    private String eNTIME;
    @JsonProperty(value = "ENTRYOPERATORID"  )
    private int eNTRYOPERATORID;
    @JsonProperty(value = "LANETYPE"  )
    private int lANETYPE;
    @JsonProperty(value = "EXTOLLSTATION"  )
    private String eXTOLLSTATION;
    @JsonProperty(value = "EXTOLLLANE"  )
    private String eXTOLLLANE;
    @JsonProperty(value = "EXTOLLSTATIONHEX"  )
    private String eXTOLLSTATIONHEX;
    @JsonProperty(value = "EXTOLLLANEHEX"  )
    private String eXTOLLLANEHEX;
    @JsonProperty(value = "EXTOLLSTATIONID"  )
    private String eXTOLLSTATIONID;
    @JsonProperty(value = "EXTOLLLANEID"  )
    private String eXTOLLLANEID;
    @JsonProperty(value = "EXTIME"  )
    private String eXTIME;
    @JsonProperty(value = "BL_SUBCENTER"  )
    private int bL_SUBCENTER;
    @JsonProperty(value = "BL_CENTER"  )
    private int bL_CENTER;
    @JsonProperty(value = "MEDIATYPE"  )
    private int mEDIATYPE;
    @JsonProperty(value = "MEDIANO"  )
    private long mEDIANO;
    @JsonProperty(value = "OBUSIGN" )
    private String oBUSIGN;
    @JsonProperty(value = "OBUISSUEFLAG"  )
    private String oBUISSUEFLAG;
    @JsonProperty(value = "OBUID"  )
    private String oBUID;
    @JsonProperty(value = "CARDCNT"  )
    private int cARDCNT;
    @JsonProperty(value = "VCOUNT"  )
    private int vCOUNT;
    @JsonProperty(value = "INDUCTCNT"  )
    private int iNDUCTCNT;
    @JsonProperty(value = "ENVLP"  )
    private String eNVLP;
    @JsonProperty(value = "ENVLPC"  )
    private int eNVLPC;
    @JsonProperty(value = "EXVLP"  )
    private String eXVLP;
    @JsonProperty(value = "EXVLPC"  )
    private int eXVLPC;
    @JsonProperty(value = "IDENTIFYVLP"  )
    private String iDENTIFYVLP;
    @JsonProperty(value = "IDENTIFYVLPC"  )
    private int iDENTIFYVLPC;
    @JsonProperty(value = "ENVEHICLETYPE"  )
    private int eNVEHICLETYPE;
    @JsonProperty(value = "EXVEHICLETYPE"  )
    private int eXVEHICLETYPE;
    @JsonProperty(value = "ENVEHICLECLASS"  )
    private int eNVEHICLECLASS;
    @JsonProperty(value = "EXVEHICLECLASS"  )
    private int eXVEHICLECLASS;
    @JsonProperty(value = "EXWEIGHT"  )
    private int eXWEIGHT;
    @JsonProperty(value = "AXISINFO"  )
    private long aXISINFO;
    @JsonProperty(value = "LIMITWEIGHT"  )
    private int lIMITWEIGHT;
    @JsonProperty(value = "OVERWEIGHTRATE"  )
    private int oVERWEIGHTRATE;
    @JsonProperty(value = "AXLECOUNT"  )
    private int aXLECOUNT;
    @JsonProperty(value = "ELECTRICALPERCENTAGE"  )
    private int eLECTRICALPERCENTAGE;
    @JsonProperty(value = "TAC"  )
    private String tAC;
    @JsonProperty(value = "TRANSTYPE"  )
    private String tRANSTYPE;
    @JsonProperty(value = "TERMINALTRANSNO"  )
    private String tERMINALTRANSNO;
    @JsonProperty(value = "TERMINALNO"  )
    private String tERMINALNO;
    @JsonProperty(value = "SERVICETYPE"  )
    private int sERVICETYPE;
    @JsonProperty(value = "ALGORITHMIDENTIFIER"  )
    private int aLGORITHMIDENTIFIER;
    @JsonProperty(value = "TOLLDISTANCE"  )
    private int tOLLDISTANCE;
    @JsonProperty(value = "REALDISTANCE"  )
    private int rEALDISTANCE;
    @JsonProperty(value = "VSPEED"  )
    private int vSPEED;
    @JsonProperty(value = "OVERTIME"  )
    private int oVERTIME;
    @JsonProperty(value = "FREETYPE"  )
    private int fREETYPE;
    @JsonProperty(value = "FREEMODE"  )
    private String fREEMODE;
    @JsonProperty(value = "FREEINFO"  )
    private String fREEINFO;
    @JsonProperty(value = "BALANCEBEFORE"  )
    private long bALANCEBEFORE;
    @JsonProperty(value = "BALANCEAFTER"  )
    private long bALANCEAFTER;
    @JsonProperty(value = "FEE"  )
    private int fEE;
    @JsonProperty(value = "COLLECTFEE"  )
    private int cOLLECTFEE;
    @JsonProperty(value = "DISCOUNTFEE"  )
    private int dISCOUNTFEE;
    @JsonProperty(value = "REBATEMONEY"  )
    private int rEBATEMONEY;
    @JsonProperty(value = "CARDCOSTFEE"  )
    private int cARDCOSTFEE;
    @JsonProperty(value = "UNPAYFEE"  )
    private int uNPAYFEE;
    @JsonProperty(value = "UNPAYFLAG"  )
    private int uNPAYFLAG;
    @JsonProperty(value = "UNPAYCARDCOST"  )
    private int uNPAYCARDCOST;
    @JsonProperty(value = "TICKETFEE"  )
    private int tICKETFEE;
    @JsonProperty(value = "UNIFIEDFEE"  )
    private int uNIFIEDFEE;
    @JsonProperty(value = "ENTOLLMONEY"  )
    private int eNTOLLMONEY;
    @JsonProperty(value = "ENFREEMONEY"  )
    private int eNFREEMONEY;
    @JsonProperty(value = "ENLASTMONEY"  )
    private int eNLASTMONEY;
    @JsonProperty(value = "RETURNMONEYSN"  )
    private String rETURNMONEYSN;
    @JsonProperty(value = "PAYCARDTYPE"  )
    private String pAYCARDTYPE;
    @JsonProperty(value = "PAYCARDNET"  )
    private String pAYCARDNET;
    @JsonProperty(value = "PAYCARDID"  )
    private String pAYCARDID;
    @JsonProperty(value = "PAYCARDTRANSN"  )
    private int pAYCARDTRANSN;
    @JsonProperty(value = "PAYTYPE"  )
    private int pAYTYPE;
    @JsonProperty(value = "PAYORDERNUM"  )
    private String pAYORDERNUM;
    @JsonProperty(value = "PAYREBATE"  )
    private int pAYREBATE;
    @JsonProperty(value = "IDENTIFICATION"  )
    private int iDENTIFICATION;
    @JsonProperty(value = "INVOICETYPE"  )
    private int iNVOICETYPE;
    @JsonProperty(value = "INVOICECODE"  )
    private String iNVOICECODE;
    @JsonProperty(value = "INVOICEID"  )
    private String iNVOICEID;
    @JsonProperty(value = "INVOICECNT"  )
    private int iNVOICECNT;
    @JsonProperty(value = "SIGNSTATUS"  )
    private int sIGNSTATUS;
    @JsonProperty(value = "SPECIALTYPE"  )
    private String sPECIALTYPE;
    @JsonProperty(value = "LANESPINFO"  )
    private double lANESPINFO;
    @JsonProperty(value = "SPINFO"  )
    @JsonDeserialize(using = BinaryToHexDeserializer.class)
    private String sPINFO;
    @JsonProperty(value = "VEHICLESIGNID"  )
    private String vEHICLESIGNID;
    @JsonProperty(value = "MULTIPROVINCE"  )
    private int mULTIPROVINCE;
    @JsonProperty(value = "KEYNUM"  )
    private int kEYNUM;
    @JsonProperty(value = "KEYPRESSINFO"  )
    private String kEYPRESSINFO;
    @JsonProperty(value = "PARAVER"  )
    private String pARAVER;
    @JsonProperty(value = "PROVINCEGROUP"  )
    private int pROVINCEGROUP;
    @JsonProperty(value = "GANTRYIDGROUP"  )
    private String gANTRYIDGROUP;
    @JsonProperty(value = "TOLLINTERVALSCOUNT"  )
    private int tOLLINTERVALSCOUNT;
    @JsonProperty(value = "TOLLINTERVALSGROUP"  )
    private String tOLLINTERVALSGROUP;
    @JsonProperty(value = "TRANSTIMEGROUP"  )
    private String tRANSTIMEGROUP;
    @JsonProperty(value = "CHARGEFEEGROUP"  )
    private String cHARGEFEEGROUP;
    @JsonProperty(value = "CHARGEDISCOUNTGROUP"  )
    private String cHARGEDISCOUNTGROUP;
    @JsonProperty(value = "RATEMODEVERSIONGROUP"  )
    private String rATEMODEVERSIONGROUP;
    @JsonProperty(value = "RATEPARAVERSIONGROUP"  )
    private String rATEPARAVERSIONGROUP;
    @JsonProperty(value = "TOLLFEEGROUP"  )
    private int tOLLFEEGROUP;
    @JsonProperty(value = "PASSID"  )
    private String pASSID;
    @JsonProperty(value = "STATIONRECEIVETIME"  )
    private String sTATIONRECEIVETIME;
    @JsonProperty(value = "RECEIVETIME"  )
    private String rECEIVETIME;
    @JsonProperty(value = "VERIFYCODE"  )
    private String vERIFYCODE;
    @JsonProperty(value = "VERIFYFLAG"  )
    private int vERIFYFLAG;
    @JsonProperty(value = "TRANSFLAG"  )
    private int tRANSFLAG;
    @JsonProperty(value = "REMARKS"  )
    private String rEMARKS;
    @JsonProperty(value = "SPARE1"  )
    private String sPARE1;
    @JsonProperty(value = "SPARE2"  )
    private String sPARE2;
    @JsonProperty(value = "SPARE3"  )
    private String sPARE3;
    @JsonProperty(value = "SPARE4"  )
    private String sPARE4;
    @JsonProperty(value = "SPARE5"  )
    private String sPARE5;
    @JsonProperty(value = "SUPPLIERID"  )
    private int sUPPLIERID;
    @JsonProperty(value = "ROADTYPE"  )
    private int rOADTYPE;
    @JsonProperty(value = "ENTOLLSTATIONNAME"  )
    private String eNTOLLSTATIONNAME;
    @JsonProperty(value = "EXTOLLSTATIONNAME"  )
    private String eXTOLLSTATIONNAME;
    @JsonProperty(value = "SECTIONGROUP"  )
    private String sECTIONGROUP;
    @JsonProperty(value = "GANTRYPASSCOUNT"  )
    private String gANTRYPASSCOUNT;
    @JsonProperty(value = "GANTRYPASSINFO"  )
    private String gANTRYPASSINFO;
    @JsonProperty(value = "FEEPROVINFO"  )
    private String fEEPROVINFO;
    @JsonProperty(value = "PAYCODE"  )
    private String pAYCODE;
    @JsonProperty(value = "DESCRIPTION"  )
    private int dESCRIPTION;
    @JsonProperty(value = "VERIFYPASSTIME"  )
    private String vERIFYPASSTIME;
    @JsonProperty(value = "VEHICLESIGN"  )
    private String vEHICLESIGN;
    @JsonProperty(value = "CHECKSIGN"  )
    private int cHECKSIGN;
    @JsonProperty(value = "OBUVERSION"  )
    private int oBUVERSION;
    @JsonProperty(value = "CARDVERSION"  )
    private int cARDVERSION;
    @JsonProperty(value = "SHORTFEE"  )
    private int sHORTFEE;
    @JsonProperty(value = "ACTUALFEECLASS"  )
    private int aCTUALFEECLASS;
    @JsonProperty(value = "OBUTOTALCOUNT"  )
    private int oBUTOTALCOUNT;
    @JsonProperty(value = "NOCARDCOUNT"  )
    private int nOCARDCOUNT;
    @JsonProperty(value = "ETCTOTALAMOUNT"  )
    private int eTCTOTALAMOUNT;
    @JsonProperty(value = "FEEBOARDPLAY"  )
    private int fEEBOARDPLAY;
    @JsonProperty(value = "SPCRATEVERSION"  )
    private int sPCRATEVERSION;
    @JsonProperty(value = "CHARGEMODE"  )
    private int cHARGEMODE;
    @JsonProperty(value = "TRANSPAYTYPE"  )
    private int tRANSPAYTYPE;
    @JsonProperty(value = "FEEMILEAGE"  )
    private int fEEMILEAGE;
    @JsonProperty(value = "PROVFEE"  )
    private int pROVFEE;
    @JsonProperty(value = "PROVTRANSCOUNT"  )
    private int pROVTRANSCOUNT;
    @JsonProperty(value = "PROVINCECOUNT"  )
    private int pROVINCECOUNT;
    @JsonProperty(value = "PROVINCETRANSGROUP"  )
    private String pROVINCETRANSGROUP;
    @JsonProperty(value = "TRANSFEE"  )
    private int tRANSFEE;
    @JsonProperty(value = "OBUPAYFEE"  )
    private String oBUPAYFEE;
    @JsonProperty(value = "OBUDISCOUNTFEE"  )
    private String oBUDISCOUNTFEE;
    @JsonProperty(value = "SHORTFEEMILEAGE"  )
    private int sHORTFEEMILEAGE;
    @JsonProperty(value = "OBUTOTALAMOUNT"  )
    private String oBUTOTALAMOUNT;
    @JsonProperty(value = "OBUTOTALDISCOUNTAMOUNT"  )
    private String oBUTOTALDISCOUNTAMOUNT;
    @JsonProperty(value = "TOLLPROVINCEID"  )
    private int tOLLPROVINCEID;
    @JsonProperty(value = "OPENTYPE"  )
    private int oPENTYPE;
    @JsonProperty(value = "DISCOUNTTYPE"  )
    private String dISCOUNTTYPE;
    @JsonProperty(value = "PROVINCEDISCOUNTFEE"  )
    private int pROVINCEDISCOUNTFEE;
    @JsonProperty(value = "ORIGINFEE"  )
    private int oRIGINFEE;
    @JsonProperty(value = "FEERATE"  )
    private int fEERATE;
    @JsonProperty(value = "DISPUTEDSTATUS"  )
    private int dISPUTEDSTATUS;
    @JsonProperty(value = "DISPUTETIME"  )
    private String dISPUTETIME;
    @JsonProperty(value = "LETGOFLAG"  )
    private int lETGOFLAG;
    @JsonProperty(value = "VACCINEFLAG"  )
    private int vACCINEFLAG;
    @JsonProperty(value = "WASTESPARE1"  )
    private String wASTESPARE1;
    @JsonProperty(value = "WASTESPARE2"  )
    private String wASTESPARE2;
    @JsonProperty(value = "WASTESPARE3"  )
    private String wASTESPARE3;
    @JsonProperty(value = "WASTESPARE4"  )
    private String wASTESPARE4;
    @JsonProperty(value = "WASTESPARE5"  )
    private int wASTESPARE5;
    @JsonProperty(value = "APPOINTID"  )
    private String aPPOINTID;
    @JsonProperty(value = "ORIGINALFLAG"  )
    private int oRIGINALFLAG;
    @JsonProperty(value = "EXINDISPUTEDTYPE"  )
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
        return this.getMULTIPROVINCE() == 0;
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
