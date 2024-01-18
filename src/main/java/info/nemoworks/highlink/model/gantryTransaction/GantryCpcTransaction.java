package info.nemoworks.highlink.model.gantryTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GantryCpcTransaction implements HighwayTransaction {
    @JsonProperty("ALGORITHMIDENTIFIER")
    public int aLGORITHMIDENTIFIER;
    @JsonProperty("CARDTOTALAMOUNT")
    public int cARDTOTALAMOUNT;
    @JsonProperty("DESCRIPTION")
    public String dESCRIPTION;
    @JsonProperty("DIRECTION")
    public int dIRECTION;
    @JsonProperty("DISCOUNTFEE")
    public int dISCOUNTFEE;
    @JsonProperty("DISCOUNTTYPE")
    public String dISCOUNTTYPE;
    @JsonProperty("ELECTRICALPERCENTAGE")
    public String eLECTRICALPERCENTAGE;
    @JsonProperty("ENAXLECOUNT")
    public String eNAXLECOUNT;
    @JsonProperty("ENTIME")
    public String eNTIME;
    @JsonProperty("ENTOLLLANEID")
    public String eNTOLLLANEID;
    @JsonProperty("ENWEIGHT")
    public String eNWEIGHT;
    @JsonProperty("ETCCARDID")
    public long eTCCARDID;
    @JsonProperty("ETCCARDNET")
    public int eTCCARDNET;
    @JsonProperty("ETCCARDTYPE")
    public int eTCCARDTYPE;
    @JsonProperty("FEE")
    public int fEE;
    @JsonProperty("FEECALCSPECIALS")
    public int fEECALCSPECIALS;
    @JsonProperty("GANTRYHEX")
    public String gANTRYHEX;
    @JsonProperty("GANTRYKEY")
    public String gANTRYKEY;
    @JsonProperty("GANTRYTYPE")
    public int gANTRYTYPE;
    @JsonProperty("GANTRYVERIFY")
    public int gANTRYVERIFY;
    @JsonProperty("GENTIME")
    public String gENTIME;
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("IDENTIFYVEHICLETYPE")
    public String iDENTIFYVEHICLETYPE;
    @JsonProperty("IDENTIFYVLP")
    public String iDENTIFYVLP;
    @JsonProperty("IDENTIFYVLPC")
    public String iDENTIFYVLPC;
    @JsonProperty("INSPLITFLAG")
    public int iNSPLITFLAG;
    @JsonProperty("KEYVERSION")
    public int kEYVERSION;
    @JsonProperty("NOCARDCOUNT")
    public int nOCARDCOUNT;
    @JsonProperty("OBUID")
    public long oBUID;
    @JsonProperty("OBUMAC")
    public String oBUMAC;
    @JsonProperty("OBUPROVINCEFEE")
    public int oBUPROVINCEFEE;
    @JsonProperty("OBUSIGN")
    public String oBUSIGN;
    @JsonProperty("OBUSN")
    public long oBUSN;
    @JsonProperty("OBUTOTALAMOUNT")
    public int oBUTOTALAMOUNT;
    @JsonProperty("ORIGINALFLAG")
    public int oRIGINALFLAG;
    @JsonProperty("ORIGINFEE")
    public String oRIGINFEE;
    @JsonProperty("PASSID")
    public String pASSID;
    @JsonProperty("PAYFEE")
    public int pAYFEE;
    @JsonProperty("PROVINCECOUNT")
    public int pROVINCECOUNT;
    @JsonProperty("PROVINCEDISCOUNTFEE")
    public String pROVINCEDISCOUNTFEE;
    @JsonProperty("PROVTRANSCOUNT")
    public int pROVTRANSCOUNT;
    @JsonProperty("RATEVERSION")
    public String rATEVERSION;
    @JsonProperty("REMARKS")
    public String rEMARKS;
    @JsonProperty("SECTIONID")
    public String sECTIONID;
    @JsonProperty("SECTIONNAME")
    public String sECTIONNAME;
    @JsonProperty("SPARE1")
    public String sPARE1;
    @JsonProperty("SPARE2")
    public String sPARE2;
    @JsonProperty("SPARE3")
    public String sPARE3;
    @JsonProperty("STATUS")
    public int sTATUS;
    @JsonProperty("SUMTIME")
    public String sUMTIME;
    @JsonProperty("TAC")
    public String tAC;
    @JsonProperty("TERMINALNO")
    public String tERMINALNO;
    @JsonProperty("TERMINALTRANSNO")
    public String tERMINALTRANSNO;
    @JsonProperty("TOLLGANTRYID")
    public String tOLLGANTRYID;
    @JsonProperty("TOLLINTERVALDISCOUNTFEE")
    public int tOLLINTERVALDISCOUNTFEE;
    @JsonProperty("TOLLINTERVALFEE")
    public int tOLLINTERVALFEE;
    @JsonProperty("TOLLINTERVALID")
    public String tOLLINTERVALID;
    @JsonProperty("TOLLINTERVALNAME")
    public String tOLLINTERVALNAME;
    @JsonProperty("TOLLINTERVALPAYFEE")
    public int tOLLINTERVALPAYFEE;
    @JsonProperty("TOLLINTERVALSIGN")
    public int tOLLINTERVALSIGN;
    @JsonProperty("TOTALCOUNT")
    public int tOTALCOUNT;
    @JsonProperty("TRANSFEE")
    public int tRANSFEE;
    @JsonProperty("TRANSNUM")
    public int tRANSNUM;
    @JsonProperty("TRANSTIME")
    public String tRANSTIME;
    @JsonProperty("TRANSTYPE")
    public String tRANSTYPE;
    @JsonProperty("TYPE")
    public int tYPE;
    @JsonProperty("UNIONPAYFLAG")
    public int uNIONPAYFLAG;
    @JsonProperty("UNIONPAYTYPEGROUP")
    public String uNIONPAYTYPEGROUP;
    @JsonProperty("VEHICLECLASS")
    public int vEHICLECLASS;
    @JsonProperty("VEHICLESEAT")
    public String vEHICLESEAT;
    @JsonProperty("VEHICLESIGN")
    public String vEHICLESIGN;
    @JsonProperty("VEHICLESIGNID")
    public String vEHICLESIGNID;
    @JsonProperty("VEHICLETYPE")
    public String vEHICLETYPE;
    @JsonProperty("VLP")
    public String vLP;
    @JsonProperty("VLPC")
    public int vLPC;

    @Override
    public String getID() {
        return this.iD;
    }

}
