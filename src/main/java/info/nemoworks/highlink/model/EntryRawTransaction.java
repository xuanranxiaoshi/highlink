package info.nemoworks.highlink.model;

import info.nemoworks.highlink.model.mapper.BinaryToHexDeserializer;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @description: 对应预处理输入中入口接收的 “入口车道流水接收表（tbl_EnWasteRec）”
 * @author：jimi
 * @date: 2024/1/21
 * @Copyright：
 */

@Data
public class EntryRawTransaction implements HighwayTransaction, PathTransaction {

    @JsonProperty("ID")
    private String iD;
    @JsonProperty("TRANSCODE")
    private String tRANSCODE;
    @JsonProperty("BL_SUBCENTER")
    private Object bL_SUBCENTER;
    @JsonProperty("BL_CENTER")
    private Object bL_CENTER;
    @JsonProperty("LDATE")
    private String lDATE;
    @JsonProperty("SHIFT")
    private Object sHIFT;
    @JsonProperty("BATCHNUM")
    private String bATCHNUM;
    @JsonProperty("LOGINTIME")
    private String lOGINTIME;
    @JsonProperty("TRIGGERTIME")
    private String tRIGGERTIME;
    @JsonProperty("OPERID")
    private Object oPERID;
    @JsonProperty("OPERNAME")
    private Object oPERNAME;
    @JsonProperty("MONITOR")
    private Object mONITOR;
    @JsonProperty("MONITORNAME")
    private Object mONITORNAME;
    @JsonProperty("MONITORTIME")
    private String mONITORTIME;
    @JsonProperty("LANEAPPVER")
    private String lANEAPPVER;
    @JsonProperty("LANETYPE")
    private int lANETYPE;
    @JsonProperty("ENTOLLSTATION")
    private Object eNTOLLSTATION;
    @JsonProperty("ENTOLLLANE")
    private Object eNTOLLLANE;
    @JsonProperty("ENTOLLSTATIONHEX")
    private Object eNTOLLSTATIONHEX;
    @JsonProperty("ENTOLLLANEHEX")
    private Object eNTOLLLANEHEX;
    @JsonProperty("ENTOLLSTATIONID")
    private String eNTOLLSTATIONID;
    @JsonProperty("ENTOLLLANEID")
    private String eNTOLLLANEID;
    @JsonProperty("ENTIME")
    private String eNTIME;
    @JsonProperty("MEDIATYPE")
    private int mEDIATYPE;
    @JsonProperty("OBUSIGN")
    private Object oBUSIGN;
    @JsonProperty("OBUISSUEFLAG")
    private String oBUISSUEFLAG;
    @JsonProperty("OBUID")
    private Object oBUID;
    @JsonProperty("SUPPLIERID")
    private int sUPPLIERID;
    @JsonProperty("VCOUNT")
    private int vCOUNT;
    @JsonProperty("BALANCEBEFORE")
    private Object bALANCEBEFORE;
    @JsonProperty("TRANSFEE")
    private int tRANSFEE;
    @JsonProperty("CARDTYPE")
    private Object cARDTYPE;
    @JsonProperty("CARDNET")
    private Object cARDNET;
    @JsonProperty("CARDID")
    private Object cARDID;
    @JsonProperty("CARDBOX")
    private int cARDBOX;
    @JsonProperty("CARDCOUNT")
    private int cARDCOUNT;
    @JsonProperty("CARDSN")
    private long cARDSN;
    @JsonProperty("CARDCNT")
    private int cARDCNT;
    @JsonProperty("INDUCTCNT")
    private int iNDUCTCNT;
    @JsonProperty("VLP")
    private String vLP;
    @JsonProperty("VLPC")
    private int vLPC;
    @JsonProperty("IDENTIFYVLP")
    private String iDENTIFYVLP;
    @JsonProperty("IDENTIFYVLPC")
    private int iDENTIFYVLPC;
    @JsonProperty("VEHICLETYPE")
    private int vEHICLETYPE;
    @JsonProperty("VEHICLECLASS")
    private int vEHICLECLASS;
    @JsonProperty("TAC")
    private String tAC;
    @JsonProperty("TRANSTYPE")
    private String tRANSTYPE;
    @JsonProperty("TERMINALNO")
    private Object tERMINALNO;
    @JsonProperty("ENWEIGHT")
    private int eNWEIGHT;
    @JsonProperty("AXISINFO")
    private int aXISINFO;
    @JsonProperty("LIMITWEIGHT")
    private int lIMITWEIGHT;
    @JsonProperty("OVERWEIGHTRATE")
    private int oVERWEIGHTRATE;
    @JsonProperty("ENAXLECOUNT")
    private int eNAXLECOUNT;
    @JsonProperty("ELECTRICALPERCENTAGE")
    private int eLECTRICALPERCENTAGE;
    @JsonProperty("SIGNSTATUS")
    private int sIGNSTATUS;
    @JsonProperty("DESCRIPTION")
    private int dESCRIPTION;
    @JsonProperty("PARAVER")
    private String pARAVER;
    @JsonProperty("KEYNUM")
    private int kEYNUM;
    @JsonProperty("KEYPRESSINFO")
    private String kEYPRESSINFO;
    @JsonProperty("SPECIALTYPE")
    private Object sPECIALTYPE;
    @JsonProperty("LANESPINFO")
    @JsonDeserialize(using = BinaryToHexDeserializer.class)
    private Object lANESPINFO;
    @JsonProperty("SPINFO")
    @JsonDeserialize(using = BinaryToHexDeserializer.class)
    private String sPINFO;
    @JsonProperty("VEHICLESIGNID")
    private String vEHICLESIGNID;
    @JsonProperty("PASSID")
    private String pASSID;
    @JsonProperty("VERIFYCODE")
    private String vERIFYCODE;
    @JsonProperty("OBUMAC")
    private String oBUMAC;
    @JsonProperty("OBUSN")
    private Object oBUSN;
    @JsonProperty("KEYVERSION")
    private int kEYVERSION;
    @JsonProperty("DIRECTION")
    private int dIRECTION;
    @JsonProperty("STATIONRECEIVETIME")
    private String sTATIONRECEIVETIME;
    @JsonProperty("VERIFYFLAG")
    private int vERIFYFLAG;
    @JsonProperty("TRANSFLAG")
    private int tRANSFLAG;
    @JsonProperty("REMARKS")
    private String rEMARKS;
    @JsonProperty("RECEIVETIME")
    private String rECEIVETIME;
    @JsonProperty("SPARE1")
    private String sPARE1;
    @JsonProperty("SPARE2")
    private String sPARE2;
    @JsonProperty("SPARE3")
    private String sPARE3;
    @JsonProperty("SPARE4")
    private int sPARE4;
    @JsonProperty("SPARE5")
    private String sPARE5;
    @JsonProperty("VERIFYPASSTIME")
    private String vERIFYPASSTIME;
    @JsonProperty("VEHICLESIGN")
    private String vEHICLESIGN;
    @JsonProperty("TERMINALTRANSNO")
    private String tERMINALTRANSNO;
    @JsonProperty("OBUVERSION")
    private int oBUVERSION;
    @JsonProperty("CARDVERSION")
    private int cARDVERSION;
    @JsonProperty("OPERATIONMEDIA")
    private int oPERATIONMEDIA;
    @JsonProperty("CHARGEMODE")
    private int cHARGEMODE;
    @JsonProperty("WASTESPARE4")
    private String wASTESPARE4;
    @JsonProperty("WASTESPARE5")
    private String wASTESPARE5;
    @JsonProperty("WASTESPARE1")
    private String wASTESPARE1;
    @JsonProperty("WASTESPARE2")
    private String wASTESPARE2;
    @JsonProperty("WASTESPARE3")
    private String wASTESPARE3;

    @Override
    public String getID() {
        return this.iD;
    }

}


