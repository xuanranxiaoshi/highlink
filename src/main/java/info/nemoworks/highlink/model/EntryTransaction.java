package info.nemoworks.highlink.model;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

public class EntryTransaction implements Transaction {
    
    @JsonProperty("ID") 
    public String iD;
    @JsonProperty("TRANSCODE") 
    public String tRANSCODE;
    @JsonProperty("BL_SUBCENTER") 
    public Object bL_SUBCENTER;
    @JsonProperty("BL_CENTER") 
    public Object bL_CENTER;
    @JsonProperty("LDATE") 
    public String lDATE;
    @JsonProperty("SHIFT") 
    public Object sHIFT;
    @JsonProperty("BATCHNUM") 
    public String bATCHNUM;
    @JsonProperty("LOGINTIME") 
    public String lOGINTIME;
    @JsonProperty("TRIGGERTIME") 
    public String tRIGGERTIME;
    @JsonProperty("OPERID") 
    public Object oPERID;
    @JsonProperty("OPERNAME") 
    public Object oPERNAME;
    @JsonProperty("MONITOR") 
    public Object mONITOR;
    @JsonProperty("MONITORNAME") 
    public Object mONITORNAME;
    @JsonProperty("MONITORTIME") 
    public String mONITORTIME;
    @JsonProperty("LANEAPPVER") 
    public String lANEAPPVER;
    @JsonProperty("LANETYPE") 
    public int lANETYPE;
    @JsonProperty("ENTOLLSTATION") 
    public Object eNTOLLSTATION;
    @JsonProperty("ENTOLLLANE") 
    public Object eNTOLLLANE;
    @JsonProperty("ENTOLLSTATIONHEX") 
    public Object eNTOLLSTATIONHEX;
    @JsonProperty("ENTOLLLANEHEX") 
    public Object eNTOLLLANEHEX;
    @JsonProperty("ENTOLLSTATIONID") 
    public String eNTOLLSTATIONID;
    @JsonProperty("ENTOLLLANEID") 
    public String eNTOLLLANEID;
    @JsonProperty("ENTIME") 
    public String eNTIME;
    @JsonProperty("MEDIATYPE") 
    public int mEDIATYPE;
    @JsonProperty("OBUSIGN") 
    public Object oBUSIGN;
    @JsonProperty("OBUISSUEFLAG") 
    public String oBUISSUEFLAG;
    @JsonProperty("OBUID") 
    public Object oBUID;
    @JsonProperty("SUPPLIERID") 
    public int sUPPLIERID;
    @JsonProperty("VCOUNT") 
    public int vCOUNT;
    @JsonProperty("BALANCEBEFORE") 
    public Object bALANCEBEFORE;
    @JsonProperty("TRANSFEE") 
    public int tRANSFEE;
    @JsonProperty("CARDTYPE") 
    public Object cARDTYPE;
    @JsonProperty("CARDNET") 
    public Object cARDNET;
    @JsonProperty("CARDID") 
    public Object cARDID;
    @JsonProperty("CARDBOX") 
    public int cARDBOX;
    @JsonProperty("CARDCOUNT") 
    public int cARDCOUNT;
    @JsonProperty("CARDSN") 
    public long cARDSN;
    @JsonProperty("CARDCNT") 
    public int cARDCNT;
    @JsonProperty("INDUCTCNT") 
    public int iNDUCTCNT;
    @JsonProperty("VLP") 
    public String vLP;
    @JsonProperty("VLPC") 
    public int vLPC;
    @JsonProperty("IDENTIFYVLP") 
    public String iDENTIFYVLP;
    @JsonProperty("IDENTIFYVLPC") 
    public int iDENTIFYVLPC;
    @JsonProperty("VEHICLETYPE") 
    public int vEHICLETYPE;
    @JsonProperty("VEHICLECLASS") 
    public int vEHICLECLASS;
    @JsonProperty("TAC") 
    public String tAC;
    @JsonProperty("TRANSTYPE") 
    public String tRANSTYPE;
    @JsonProperty("TERMINALNO") 
    public Object tERMINALNO;
    @JsonProperty("ENWEIGHT") 
    public int eNWEIGHT;
    @JsonProperty("AXISINFO") 
    public int aXISINFO;
    @JsonProperty("LIMITWEIGHT") 
    public int lIMITWEIGHT;
    @JsonProperty("OVERWEIGHTRATE") 
    public int oVERWEIGHTRATE;
    @JsonProperty("ENAXLECOUNT") 
    public int eNAXLECOUNT;
    @JsonProperty("ELECTRICALPERCENTAGE") 
    public int eLECTRICALPERCENTAGE;
    @JsonProperty("SIGNSTATUS") 
    public int sIGNSTATUS;
    @JsonProperty("DESCRIPTION") 
    public int dESCRIPTION;
    @JsonProperty("PARAVER") 
    public String pARAVER;
    @JsonProperty("KEYNUM") 
    public int kEYNUM;
    @JsonProperty("KEYPRESSINFO") 
    public String kEYPRESSINFO;
    @JsonProperty("SPECIALTYPE") 
    public Object sPECIALTYPE;
    @JsonProperty("LANESPINFO") 
    public Object lANESPINFO;
    @JsonProperty("SPINFO") 
    public String sPINFO;
    @JsonProperty("VEHICLESIGNID") 
    public String vEHICLESIGNID;
    @JsonProperty("PASSID") 
    public String pASSID;
    @JsonProperty("VERIFYCODE") 
    public String vERIFYCODE;
    @JsonProperty("OBUMAC") 
    public String oBUMAC;
    @JsonProperty("OBUSN") 
    public Object oBUSN;
    @JsonProperty("KEYVERSION") 
    public int kEYVERSION;
    @JsonProperty("DIRECTION") 
    public int dIRECTION;
    @JsonProperty("STATIONRECEIVETIME") 
    public String sTATIONRECEIVETIME;
    @JsonProperty("VERIFYFLAG") 
    public int vERIFYFLAG;
    @JsonProperty("TRANSFLAG") 
    public int tRANSFLAG;
    @JsonProperty("REMARKS") 
    public String rEMARKS;
    @JsonProperty("RECEIVETIME") 
    public String rECEIVETIME;
    @JsonProperty("SPARE1") 
    public String sPARE1;
    @JsonProperty("SPARE2") 
    public String sPARE2;
    @JsonProperty("SPARE3") 
    public String sPARE3;
    @JsonProperty("SPARE4") 
    public int sPARE4;
    @JsonProperty("SPARE5") 
    public String sPARE5;
    @JsonProperty("VERIFYPASSTIME") 
    public String vERIFYPASSTIME;
    @JsonProperty("VEHICLESIGN") 
    public String vEHICLESIGN;
    @JsonProperty("TERMINALTRANSNO") 
    public String tERMINALTRANSNO;
    @JsonProperty("OBUVERSION") 
    public int oBUVERSION;
    @JsonProperty("CARDVERSION") 
    public int cARDVERSION;
    @JsonProperty("OPERATIONMEDIA") 
    public int oPERATIONMEDIA;
    @JsonProperty("CHARGEMODE") 
    public int cHARGEMODE;
    @JsonProperty("WASTESPARE4") 
    public String wASTESPARE4;
    @JsonProperty("WASTESPARE5") 
    public String wASTESPARE5;
    @JsonProperty("WASTESPARE1") 
    public String wASTESPARE1;
    @JsonProperty("WASTESPARE2") 
    public String wASTESPARE2;
    @JsonProperty("WASTESPARE3") 
    public String wASTESPARE3;
    @Override
    public String getID() {
        return this.iD;
    }
}
