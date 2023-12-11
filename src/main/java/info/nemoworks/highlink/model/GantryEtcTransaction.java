package info.nemoworks.highlink.model;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GantryEtcTransaction implements HighwayTransaction {

    @JsonProperty("ALGORITHMIDENTIFIER")
    private int aLGORITHMIDENTIFIER;
    @JsonProperty("CARDTOTALAMOUNT")
    private int cARDTOTALAMOUNT;
    @JsonProperty("DESCRIPTION")
    private String dESCRIPTION;
    @JsonProperty("DIRECTION")
    private int dIRECTION;
    @JsonProperty("DISCOUNTFEE")
    private int dISCOUNTFEE;
    @JsonProperty("DISCOUNTTYPE")
    private String dISCOUNTTYPE;
    @JsonProperty("ELECTRICALPERCENTAGE")
    private String eLECTRICALPERCENTAGE;
    @JsonProperty("ENAXLECOUNT")
    private String eNAXLECOUNT;
    @JsonProperty("ENTIME")
    private String eNTIME;
    @JsonProperty("ENTOLLLANEID")
    private String eNTOLLLANEID;
    @JsonProperty("ENWEIGHT")
    private String eNWEIGHT;
    @JsonProperty("ETCCARDID")
    private long eTCCARDID;
    @JsonProperty("ETCCARDNET")
    private int eTCCARDNET;
    @JsonProperty("ETCCARDTYPE")
    private int eTCCARDTYPE;
    @JsonProperty("FEE")
    private int fEE;
    @JsonProperty("FEECALCSPECIALS")
    private int fEECALCSPECIALS;
    @JsonProperty("GANTRYHEX")
    private String gANTRYHEX;
    @JsonProperty("GANTRYKEY")
    private String gANTRYKEY;
    @JsonProperty("GANTRYTYPE")
    private int gANTRYTYPE;
    @JsonProperty("GANTRYVERIFY")
    private int gANTRYVERIFY;
    @JsonProperty("GENTIME")
    private String gENTIME;
    @JsonProperty("ID")
    private String iD;
    @JsonProperty("IDENTIFYVEHICLETYPE")
    private String iDENTIFYVEHICLETYPE;
    @JsonProperty("IDENTIFYVLP")
    private String iDENTIFYVLP;
    @JsonProperty("IDENTIFYVLPC")
    private String iDENTIFYVLPC;
    @JsonProperty("INSPLITFLAG")
    private int iNSPLITFLAG;
    @JsonProperty("KEYVERSION")
    private int kEYVERSION;
    @JsonProperty("NOCARDCOUNT")
    private int nOCARDCOUNT;
    @JsonProperty("OBUID")
    private long oBUID;
    @JsonProperty("OBUMAC")
    private String oBUMAC;
    @JsonProperty("OBUPROVINCEFEE")
    private int oBUPROVINCEFEE;
    @JsonProperty("OBUSIGN")
    private String oBUSIGN;
    @JsonProperty("OBUSN")
    private long oBUSN;
    @JsonProperty("OBUTOTALAMOUNT")
    private int oBUTOTALAMOUNT;
    @JsonProperty("ORIGINALFLAG")
    private int oRIGINALFLAG;
    @JsonProperty("ORIGINFEE")
    private String oRIGINFEE;
    @JsonProperty("PASSID")
    private String pASSID;
    @JsonProperty("PAYFEE")
    private int pAYFEE;
    @JsonProperty("PROVINCECOUNT")
    private int pROVINCECOUNT;
    @JsonProperty("PROVINCEDISCOUNTFEE")
    private String pROVINCEDISCOUNTFEE;
    @JsonProperty("PROVTRANSCOUNT")
    private int pROVTRANSCOUNT;
    @JsonProperty("RATEVERSION")
    private String rATEVERSION;
    @JsonProperty("REMARKS")
    private String rEMARKS;
    @JsonProperty("SECTIONID")
    private String sECTIONID;
    @JsonProperty("SECTIONNAME")
    private String sECTIONNAME;
    @JsonProperty("SPARE1")
    private String sPARE1;
    @JsonProperty("SPARE2")
    private String sPARE2;
    @JsonProperty("SPARE3")
    private String sPARE3;
    @JsonProperty("STATUS")
    private int sTATUS;
    @JsonProperty("SUMTIME")
    private String sUMTIME;
    @JsonProperty("TAC")
    private String tAC;
    @JsonProperty("TERMINALNO")
    private String tERMINALNO;
    @JsonProperty("TERMINALTRANSNO")
    private String tERMINALTRANSNO;
    @JsonProperty("TOLLGANTRYID")
    private String tOLLGANTRYID;
    @JsonProperty("TOLLINTERVALDISCOUNTFEE")
    private int tOLLINTERVALDISCOUNTFEE;
    @JsonProperty("TOLLINTERVALFEE")
    private int tOLLINTERVALFEE;
    @JsonProperty("TOLLINTERVALID")
    private String tOLLINTERVALID;
    @JsonProperty("TOLLINTERVALNAME")
    private String tOLLINTERVALNAME;
    @JsonProperty("TOLLINTERVALPAYFEE")
    private int tOLLINTERVALPAYFEE;
    @JsonProperty("TOLLINTERVALSIGN")
    private int tOLLINTERVALSIGN;
    @JsonProperty("TOTALCOUNT")
    private int tOTALCOUNT;
    @JsonProperty("TRANSFEE")
    private int tRANSFEE;
    @JsonProperty("TRANSNUM")
    private int tRANSNUM;
    @JsonProperty("TRANSTIME")
    private String tRANSTIME;
    @JsonProperty("TRANSTYPE")
    private String tRANSTYPE;
    @JsonProperty("TYPE")
    private int tYPE;
    @JsonProperty("UNIONPAYFLAG")
    private int uNIONPAYFLAG;
    @JsonProperty("UNIONPAYTYPEGROUP")
    private String uNIONPAYTYPEGROUP;
    @JsonProperty("VEHICLECLASS")
    private int vEHICLECLASS;
    @JsonProperty("VEHICLESEAT")
    private int vEHICLESEAT;
    @JsonProperty("VEHICLESIGN")
    private String vEHICLESIGN;
    @JsonProperty("VEHICLESIGNID")
    private String vEHICLESIGNID;
    @JsonProperty("VEHICLETYPE")
    private int vEHICLETYPE;
    @JsonProperty("VLP")
    private String vLP;
    @JsonProperty("VLPC")
    private int vLPC;

    @Override
    public String getID() {

        return this.iD;
    }
}
