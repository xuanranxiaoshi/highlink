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

    @JsonProperty(value = "ID"   )
    public String iD;
    @JsonProperty(value = "TRANSCODE"   )
    public String tRANSCODE;
    @JsonProperty(value = "BL_SUBCENTER"   )
    public String bL_SUBCENTER;
    @JsonProperty(value = "BL_CENTER"   )
    public String bL_CENTER;
    @JsonProperty(value = "LDATE"   )
    public String lDATE;
    @JsonProperty(value = "SHIFT"   )
    public String sHIFT;
    @JsonProperty(value = "BATCHNUM"   )
    public String bATCHNUM;
    @JsonProperty(value = "LOGINTIME"   )
    public String lOGINTIME;
    @JsonProperty(value = "TRIGGERTIME"   )
    public String tRIGGERTIME;
    @JsonProperty(value = "OPERID"   )
    public String oPERID;
    @JsonProperty(value = "OPERNAME"   )
    public String oPERNAME;
    @JsonProperty(value = "MONITOR"   )
    public String mONITOR;
    @JsonProperty(value = "MONITORNAME"   )
    public String mONITORNAME;
    @JsonProperty(value = "MONITORTIME"   )
    public String mONITORTIME;
    @JsonProperty(value = "LANEAPPVER"   )
    public String lANEAPPVER;
    @JsonProperty(value = "LANETYPE"   )
    public int lANETYPE;
    @JsonProperty(value = "ENTOLLSTATION"   )
    public String eNTOLLSTATION;
    @JsonProperty(value = "ENTOLLLANE"   )
    public String eNTOLLLANE;
    @JsonProperty(value = "ENTOLLSTATIONHEX"   )
    public String eNTOLLSTATIONHEX;
    @JsonProperty(value = "ENTOLLLANEHEX"   )
    public String eNTOLLLANEHEX;
    @JsonProperty(value = "ENTOLLSTATIONID"   )
    public String eNTOLLSTATIONID;
    @JsonProperty(value = "ENTOLLLANEID"   )
    public String eNTOLLLANEID;
    @JsonProperty(value = "ENTIME"   )
    public String eNTIME;
    @JsonProperty(value = "MEDIATYPE"   )
    public int mEDIATYPE;
    @JsonProperty(value = "OBUSIGN"   )
    public int oBUSIGN;
    @JsonProperty(value = "OBUISSUEFLAG"   )
    public String oBUISSUEFLAG;
    @JsonProperty(value = "OBUID"   )
    public String oBUID;
    @JsonProperty(value = "SUPPLIERID"   )
    public int sUPPLIERID;
    @JsonProperty(value = "VCOUNT"   )
    public int vCOUNT;
    @JsonProperty(value = "BALANCEBEFORE"   )
    public String bALANCEBEFORE;
    @JsonProperty(value = "TRANSFEE"   )
    public int tRANSFEE;
    @JsonProperty(value = "CARDTYPE"   )
    public String cARDTYPE;
    @JsonProperty(value = "CARDNET"   )
    public String cARDNET;
    @JsonProperty(value = "CARDID"   )
    public String cARDID;
    @JsonProperty(value = "CARDBOX"   )
    public int cARDBOX;
    @JsonProperty(value = "CARDCOUNT"   )
    public int cARDCOUNT;
    @JsonProperty(value = "CARDSN"   )
    public long cARDSN;
    @JsonProperty(value = "CARDCNT"   )
    public int cARDCNT;
    @JsonProperty(value = "INDUCTCNT"   )
    public int iNDUCTCNT;
    @JsonProperty(value = "VLP"   )
    public String vLP;
    @JsonProperty(value = "VLPC"   )
    public int vLPC;
    @JsonProperty(value = "IDENTIFYVLP"   )
    public String iDENTIFYVLP;
    @JsonProperty(value = "IDENTIFYVLPC"   )
    public int iDENTIFYVLPC;
    @JsonProperty(value = "VEHICLETYPE"   )
    public int vEHICLETYPE;
    @JsonProperty(value = "VEHICLECLASS"   )
    public int vEHICLECLASS;
    @JsonProperty(value = "TAC"   )
    public String tAC;
    @JsonProperty(value = "TRANSTYPE"   )
    public String tRANSTYPE;
    @JsonProperty(value = "TERMINALNO"   )
    public String tERMINALNO;
    @JsonProperty(value = "ENWEIGHT"   )
    public int eNWEIGHT;
    @JsonProperty(value = "AXISINFO"   )
    public int aXISINFO;
    @JsonProperty(value = "LIMITWEIGHT"   )
    public int lIMITWEIGHT;
    @JsonProperty(value = "OVERWEIGHTRATE"   )
    public int oVERWEIGHTRATE;
    @JsonProperty(value = "ENAXLECOUNT"   )
    public int eNAXLECOUNT;
    @JsonProperty(value = "ELECTRICALPERCENTAGE"   )
    public int eLECTRICALPERCENTAGE;
    @JsonProperty(value = "SIGNSTATUS"   )
    public int sIGNSTATUS;
    @JsonProperty(value = "DESCRIPTION"   )
    public int dESCRIPTION;
    @JsonProperty(value = "PARAVER"   )
    public String pARAVER;
    @JsonProperty(value = "KEYNUM"   )
    public int kEYNUM;
    @JsonProperty(value = "KEYPRESSINFO"   )
    public String kEYPRESSINFO;
    @JsonProperty(value = "SPECIALTYPE"   )
    public String sPECIALTYPE;
    @JsonProperty(value = "LANESPINFO"   )
    @JsonDeserialize(using = BinaryToHexDeserializer.class)
    public String lANESPINFO;
    @JsonProperty(value = "SPINFO"   )
    @JsonDeserialize(using = BinaryToHexDeserializer.class)
    public String sPINFO;
    @JsonProperty(value = "VEHICLESIGNID"   )
    public String vEHICLESIGNID;
    @JsonProperty(value = "PASSID"   )
    public String pASSID;
    @JsonProperty(value = "VERIFYCODE"   )
    public String vERIFYCODE;
    @JsonProperty(value = "OBUMAC"   )
    public String oBUMAC;
    @JsonProperty(value = "OBUSN"   )
    public String oBUSN;
    @JsonProperty(value = "KEYVERSION"   )
    public int kEYVERSION;
    @JsonProperty(value = "DIRECTION"   )
    public int dIRECTION;
    @JsonProperty(value = "STATIONRECEIVETIME"   )
    public String sTATIONRECEIVETIME;
    @JsonProperty(value = "VERIFYFLAG"   )
    public int vERIFYFLAG;
    @JsonProperty(value = "TRANSFLAG"   )
    public int tRANSFLAG;
    @JsonProperty(value = "REMARKS"   )
    public String rEMARKS;
    @JsonProperty(value = "RECEIVETIME"   )
    public String rECEIVETIME;
    @JsonProperty(value = "SPARE1"   )
    public String sPARE1;
    @JsonProperty(value = "SPARE2"   )
    public String sPARE2;
    @JsonProperty(value = "SPARE3"   )
    public String sPARE3;
    @JsonProperty(value = "SPARE4"   )
    public int sPARE4;
    @JsonProperty(value = "SPARE5"   )
    public String sPARE5;
    @JsonProperty(value = "VERIFYPASSTIME"   )
    public String vERIFYPASSTIME;
    @JsonProperty(value = "VEHICLESIGN"   )
    public String vEHICLESIGN;
    @JsonProperty(value = "TERMINALTRANSNO"   )
    public String tERMINALTRANSNO;
    @JsonProperty(value = "OBUVERSION"   )
    public int oBUVERSION;
    @JsonProperty(value = "CARDVERSION"   )
    public int cARDVERSION;
    @JsonProperty(value = "OPERATIONMEDIA"   )
    public int oPERATIONMEDIA;
    @JsonProperty(value = "CHARGEMODE"   )
    public int cHARGEMODE;
    @JsonProperty(value = "WASTESPARE4"   )
    public String wASTESPARE4;
    @JsonProperty(value = "WASTESPARE5"   )
    public String wASTESPARE5;
    @JsonProperty(value = "WASTESPARE1"   )
    public String wASTESPARE1;
    @JsonProperty(value = "WASTESPARE2"   )
    public String wASTESPARE2;
    @JsonProperty(value = "WASTESPARE3"   )
    public String wASTESPARE3;

    @Override
    public String peekTime() {
        return eNTIME;
    }
}


