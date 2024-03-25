package info.nemoworks.highlink.model.entryTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.mapper.BinaryToHexDeserializer;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
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

    @JsonProperty(value = "ID", access = JsonProperty.Access.WRITE_ONLY)
    private String iD;
    @JsonProperty(value = "TRANSCODE", access = JsonProperty.Access.WRITE_ONLY)
    private String tRANSCODE;
    @JsonProperty(value = "BL_SUBCENTER", access = JsonProperty.Access.WRITE_ONLY)
    private Object bL_SUBCENTER;
    @JsonProperty(value = "BL_CENTER", access = JsonProperty.Access.WRITE_ONLY)
    private Object bL_CENTER;
    @JsonProperty(value = "LDATE", access = JsonProperty.Access.WRITE_ONLY)
    private String lDATE;
    @JsonProperty(value = "SHIFT", access = JsonProperty.Access.WRITE_ONLY)
    private Object sHIFT;
    @JsonProperty(value = "BATCHNUM", access = JsonProperty.Access.WRITE_ONLY)
    private String bATCHNUM;
    @JsonProperty(value = "LOGINTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String lOGINTIME;
    @JsonProperty(value = "TRIGGERTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String tRIGGERTIME;
    @JsonProperty(value = "OPERID", access = JsonProperty.Access.WRITE_ONLY)
    private Object oPERID;
    @JsonProperty(value = "OPERNAME", access = JsonProperty.Access.WRITE_ONLY)
    private Object oPERNAME;
    @JsonProperty(value = "MONITOR", access = JsonProperty.Access.WRITE_ONLY)
    private Object mONITOR;
    @JsonProperty(value = "MONITORNAME", access = JsonProperty.Access.WRITE_ONLY)
    private Object mONITORNAME;
    @JsonProperty(value = "MONITORTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String mONITORTIME;
    @JsonProperty(value = "LANEAPPVER", access = JsonProperty.Access.WRITE_ONLY)
    private String lANEAPPVER;
    @JsonProperty(value = "LANETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int lANETYPE;
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
    @JsonProperty(value = "MEDIATYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int mEDIATYPE;
    @JsonProperty(value = "OBUSIGN", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUSIGN;
    @JsonProperty(value = "OBUISSUEFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUISSUEFLAG;
    @JsonProperty(value = "OBUID", access = JsonProperty.Access.WRITE_ONLY)
    private Object oBUID;
    @JsonProperty(value = "SUPPLIERID", access = JsonProperty.Access.WRITE_ONLY)
    private int sUPPLIERID;
    @JsonProperty(value = "VCOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int vCOUNT;
    @JsonProperty(value = "BALANCEBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private Object bALANCEBEFORE;
    @JsonProperty(value = "TRANSFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int tRANSFEE;
    @JsonProperty(value = "CARDTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private Object cARDTYPE;
    @JsonProperty(value = "CARDNET", access = JsonProperty.Access.WRITE_ONLY)
    private Object cARDNET;
    @JsonProperty(value = "CARDID", access = JsonProperty.Access.WRITE_ONLY)
    private Object cARDID;
    @JsonProperty(value = "CARDBOX", access = JsonProperty.Access.WRITE_ONLY)
    private int cARDBOX;
    @JsonProperty(value = "CARDCOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int cARDCOUNT;
    @JsonProperty(value = "CARDSN", access = JsonProperty.Access.WRITE_ONLY)
    private long cARDSN;
    @JsonProperty(value = "CARDCNT", access = JsonProperty.Access.WRITE_ONLY)
    private int cARDCNT;
    @JsonProperty(value = "INDUCTCNT", access = JsonProperty.Access.WRITE_ONLY)
    private int iNDUCTCNT;
    @JsonProperty(value = "VLP", access = JsonProperty.Access.WRITE_ONLY)
    private String vLP;
    @JsonProperty(value = "VLPC", access = JsonProperty.Access.WRITE_ONLY)
    private int vLPC;
    @JsonProperty(value = "IDENTIFYVLP", access = JsonProperty.Access.WRITE_ONLY)
    private String iDENTIFYVLP;
    @JsonProperty(value = "IDENTIFYVLPC", access = JsonProperty.Access.WRITE_ONLY)
    private int iDENTIFYVLPC;
    @JsonProperty(value = "VEHICLETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int vEHICLETYPE;
    @JsonProperty(value = "VEHICLECLASS", access = JsonProperty.Access.WRITE_ONLY)
    private int vEHICLECLASS;
    @JsonProperty(value = "TAC", access = JsonProperty.Access.WRITE_ONLY)
    private String tAC;
    @JsonProperty(value = "TRANSTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String tRANSTYPE;
    @JsonProperty(value = "TERMINALNO", access = JsonProperty.Access.WRITE_ONLY)
    private Object tERMINALNO;
    @JsonProperty(value = "ENWEIGHT", access = JsonProperty.Access.WRITE_ONLY)
    private int eNWEIGHT;
    @JsonProperty(value = "AXISINFO", access = JsonProperty.Access.WRITE_ONLY)
    private int aXISINFO;
    @JsonProperty(value = "LIMITWEIGHT", access = JsonProperty.Access.WRITE_ONLY)
    private int lIMITWEIGHT;
    @JsonProperty(value = "OVERWEIGHTRATE", access = JsonProperty.Access.WRITE_ONLY)
    private int oVERWEIGHTRATE;
    @JsonProperty(value = "ENAXLECOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int eNAXLECOUNT;
    @JsonProperty(value = "ELECTRICALPERCENTAGE", access = JsonProperty.Access.WRITE_ONLY)
    private int eLECTRICALPERCENTAGE;
    @JsonProperty(value = "SIGNSTATUS", access = JsonProperty.Access.WRITE_ONLY)
    private int sIGNSTATUS;
    @JsonProperty(value = "DESCRIPTION", access = JsonProperty.Access.WRITE_ONLY)
    private int dESCRIPTION;
    @JsonProperty(value = "PARAVER", access = JsonProperty.Access.WRITE_ONLY)
    private String pARAVER;
    @JsonProperty(value = "KEYNUM", access = JsonProperty.Access.WRITE_ONLY)
    private int kEYNUM;
    @JsonProperty(value = "KEYPRESSINFO", access = JsonProperty.Access.WRITE_ONLY)
    private String kEYPRESSINFO;
    @JsonProperty(value = "SPECIALTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private Object sPECIALTYPE;
    @JsonProperty(value = "LANESPINFO", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = BinaryToHexDeserializer.class)
    private Object lANESPINFO;
    @JsonProperty(value = "SPINFO", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = BinaryToHexDeserializer.class)
    private String sPINFO;
    @JsonProperty(value = "VEHICLESIGNID", access = JsonProperty.Access.WRITE_ONLY)
    private String vEHICLESIGNID;
    @JsonProperty(value = "PASSID", access = JsonProperty.Access.WRITE_ONLY)
    private String pASSID;
    @JsonProperty(value = "VERIFYCODE", access = JsonProperty.Access.WRITE_ONLY)
    private String vERIFYCODE;
    @JsonProperty(value = "OBUMAC", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUMAC;
    @JsonProperty(value = "OBUSN", access = JsonProperty.Access.WRITE_ONLY)
    private Object oBUSN;
    @JsonProperty(value = "KEYVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private int kEYVERSION;
    @JsonProperty(value = "DIRECTION", access = JsonProperty.Access.WRITE_ONLY)
    private int dIRECTION;
    @JsonProperty(value = "STATIONRECEIVETIME", access = JsonProperty.Access.WRITE_ONLY)
    private String sTATIONRECEIVETIME;
    @JsonProperty(value = "VERIFYFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int vERIFYFLAG;
    @JsonProperty(value = "TRANSFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int tRANSFLAG;
    @JsonProperty(value = "REMARKS", access = JsonProperty.Access.WRITE_ONLY)
    private String rEMARKS;
    @JsonProperty(value = "RECEIVETIME", access = JsonProperty.Access.WRITE_ONLY)
    private String rECEIVETIME;
    @JsonProperty(value = "SPARE1", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE1;
    @JsonProperty(value = "SPARE2", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE2;
    @JsonProperty(value = "SPARE3", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE3;
    @JsonProperty(value = "SPARE4", access = JsonProperty.Access.WRITE_ONLY)
    private int sPARE4;
    @JsonProperty(value = "SPARE5", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE5;
    @JsonProperty(value = "VERIFYPASSTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String vERIFYPASSTIME;
    @JsonProperty(value = "VEHICLESIGN", access = JsonProperty.Access.WRITE_ONLY)
    private String vEHICLESIGN;
    @JsonProperty(value = "TERMINALTRANSNO", access = JsonProperty.Access.WRITE_ONLY)
    private String tERMINALTRANSNO;
    @JsonProperty(value = "OBUVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUVERSION;
    @JsonProperty(value = "CARDVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private int cARDVERSION;
    @JsonProperty(value = "OPERATIONMEDIA", access = JsonProperty.Access.WRITE_ONLY)
    private int oPERATIONMEDIA;
    @JsonProperty(value = "CHARGEMODE", access = JsonProperty.Access.WRITE_ONLY)
    private int cHARGEMODE;
    @JsonProperty(value = "WASTESPARE4", access = JsonProperty.Access.WRITE_ONLY)
    private String wASTESPARE4;
    @JsonProperty(value = "WASTESPARE5", access = JsonProperty.Access.WRITE_ONLY)
    private String wASTESPARE5;
    @JsonProperty(value = "WASTESPARE1", access = JsonProperty.Access.WRITE_ONLY)
    private String wASTESPARE1;
    @JsonProperty(value = "WASTESPARE2", access = JsonProperty.Access.WRITE_ONLY)
    private String wASTESPARE2;
    @JsonProperty(value = "WASTESPARE3", access = JsonProperty.Access.WRITE_ONLY)
    private String wASTESPARE3;

    @Override
    public String peekTime() {
        return eNTIME;
    }
}


