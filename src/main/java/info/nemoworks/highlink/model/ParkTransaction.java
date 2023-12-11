package info.nemoworks.highlink.model;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ParkTransaction implements HighwayTransaction {
    @JsonProperty("ID")
    private double iD;
    @JsonProperty("PARKOPERATORID")
    private int pARKOPERATORID;
    @JsonProperty("ISSUERID")
    private int iSSUERID;
    @JsonProperty("PARKID")
    private long pARKID;
    @JsonProperty("BATCHNUM")
    private String bATCHNUM;
    @JsonProperty("TRANSTIME")
    private String tRANSTIME;
    @JsonProperty("PARKTIME")
    private int pARKTIME;
    @JsonProperty("FEE")
    private int fEE;
    @JsonProperty("ETCCARDTYPE")
    private int eTCCARDTYPE;
    @JsonProperty("ETCCARDNET")
    private int eTCCARDNET;
    @JsonProperty("ETCCARDID")
    private long eTCCARDID;
    @JsonProperty("TERMINALTRANSNO")
    private String tERMINALTRANSNO;
    @JsonProperty("OBUID")
    private String oBUID;
    @JsonProperty("VLP")
    private String vLP;
    @JsonProperty("VLPC")
    private int vLPC;
    @JsonProperty("VEHICLETYPE")
    private int vEHICLETYPE;
    @JsonProperty("TAC")
    private String tAC;
    @JsonProperty("TRANSNO")
    private int tRANSNO;
    @JsonProperty("TRANSTYPE")
    private String tRANSTYPE;
    @JsonProperty("TERMINALNO")
    private String tERMINALNO;
    @JsonProperty("PREBALANCE")
    private long pREBALANCE;
    @JsonProperty("POSTBALANCE")
    private long pOSTBALANCE;
    @JsonProperty("SERVICETYPE")
    private int sERVICETYPE;
    @JsonProperty("ALGORITHMIDENTIFIER")
    private int aLGORITHMIDENTIFIER;
    @JsonProperty("REMARK")
    private String rEMARK;
    @JsonProperty("REQUESTNAME")
    private String rEQUESTNAME;
    @JsonProperty("RESPONSENAME")
    private String rESPONSENAME;
    @JsonProperty("RECEIVETIME")
    private String rECEIVETIME;
    @JsonProperty("MSGID")
    private String mSGID;
    @JsonProperty("SPARE1")
    private String sPARE1;
    @JsonProperty("SPARE2")
    private String sPARE2;
    @JsonProperty("SPARE3")
    private String sPARE3;
    @JsonProperty("SPARE4")
    private String sPARE4;
    @JsonProperty("VERIFYPASSTIME")
    private String vERIFYPASSTIME;
    @JsonProperty("REMARKS")
    private String rEMARKS;
    @JsonProperty("MODIFYFLAG")
    private int mODIFYFLAG;
    @JsonProperty("SOURCEID")
    private String sOURCEID;

    @Override
    public String getID() {
        return String.valueOf(this.iD);
    }
}
