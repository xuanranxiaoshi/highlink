package info.nemoworks.highlink.model.multiProvince;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * @description:
 * @author：jimi
 * @date: 2024/3/21
 * @Copyright：
 */
@Data
public class SerTollSum implements ProvinceTransaction{
    @JsonProperty("SERPROVINCEID")
    public String sERPROVINCEID;
    @JsonProperty("ISSUERID")
    public String iSSUERID;
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("PASSID")
    public String pASSID;
    @JsonProperty("COUNT")
    public String cOUNT;
    @JsonProperty("PAYFEE")
    public String pAYFEE;
    @JsonProperty("FEE")
    public String fEE;
    @JsonProperty("DISCOUNTFEE")
    public String dISCOUNTFEE;
    @JsonProperty("ENPOINTID")
    public String eNPOINTID;
    @JsonProperty("EXPOINTID")
    public String eXPOINTID;
    @JsonProperty("ENTOLLSTATIONNAME")
    public String eNTOLLSTATIONNAME;
    @JsonProperty("EXTOLLSTATIONNAME")
    public String eXTOLLSTATIONNAME;
    @JsonProperty("ENTIME")
    public String eNTIME;
    @JsonProperty("EXTIME")
    public String eXTIME;
    @JsonProperty("ETCCARDTYPE")
    public String eTCCARDTYPE;
    @JsonProperty("ETCCARDNET")
    public String eTCCARDNET;
    @JsonProperty("ETCCARDID")
    public String eTCCARDID;
    @JsonProperty("OBUID")
    public String oBUID;
    @JsonProperty("OBUSIGN")
    public String oBUSIGN;
    @JsonProperty("VEHICLETYPE")
    public String vEHICLETYPE;
    @JsonProperty("VLP")
    public String vLP;
    @JsonProperty("VLPC")
    public String vLPC;
    @JsonProperty("SECTIONID")
    public String sECTIONID;
    @JsonProperty("SECTIONNAME")
    public String sECTIONNAME;
    @JsonProperty("TOLLINTERVALID")
    public String tOLLINTERVALID;
    @JsonProperty("TOLLINTERVALNAME")
    public String tOLLINTERVALNAME;
    @JsonProperty("TOLLINTERVALFEE")
    public String tOLLINTERVALFEE;
    @JsonProperty("TOLLINTERVALPAYFEE")
    public String tOLLINTERVALPAYFEE;
    @JsonProperty("TOLLINTERVALDISCOUNTFEE")
    public String tOLLINTERVALDISCOUNTFEE;
    @JsonProperty("VEHICLESIGN")
    public String vEHICLESIGN;
    @JsonProperty("DISCOUNTTYPE")
    public String dISCOUNTTYPE;
    @JsonProperty("PROVINCEDISCOUNTFEE")
    public String pROVINCEDISCOUNTFEE;
    @JsonProperty("ORIGINFEE")
    public String oRIGINFEE;
    @JsonProperty("TRANSNUM")
    public String tRANSNUM;
    @JsonProperty("ISFULL")
    public String iSFULL;
    @JsonProperty("GENTIME")
    public String gENTIME;
    @JsonProperty("STATUS")
    public String sTATUS;
    @JsonProperty("RESPONSECODE")
    public String rESPONSECODE;
    @JsonProperty("RESPONSEINFO")
    public String rESPONSEINFO;
    @JsonProperty("RECEIVETIME")
    public String rECEIVETIME;
    @JsonProperty("PROTIME")
    public String pROTIME;
    @JsonProperty("BATCHFILENAME")
    public String bATCHFILENAME;
    @JsonProperty("DATACHECKFLAG")
    public String dATACHECKFLAG;
    @JsonProperty("MULTIPROVINCE")
    public String mULTIPROVINCE;
    @JsonProperty("IFTOLLPROVICE")
    public String iFTOLLPROVICE;
    @JsonProperty("MEDIATYPE")
    public String mEDIATYPE;
    @JsonProperty("PROVINCECOUNT")
    public String pROVINCECOUNT;
    @JsonProperty("SENDTIME")
    public String sENDTIME;
    @JsonProperty("SPARE1")
    public String sPARE1;
    @JsonProperty("SPARE2")
    public String sPARE2;
    @JsonProperty("SPARE3")
    public String sPARE3;
    @JsonProperty("IFPRESPLIT")
    public String iFPRESPLIT;
    @JsonProperty("SECTIONID1")
    public String sECTIONID1;
    @JsonProperty("TOLLINTERVALID1")
    public String tOLLINTERVALID1;
    @JsonProperty("SECTIONID2")
    public String sECTIONID2;
    @JsonProperty("TOLLINTERVALID2")
    public String tOLLINTERVALID2;
    @JsonProperty("INSPLITFLAG")
    public String iNSPLITFLAG;
    @JsonProperty("OBUISSUEID")
    public String oBUISSUEID;
    @JsonProperty("CPUNETID")
    public String cPUNETID;
    @JsonProperty("VEHICLEUSERTYPE")
    public String vEHICLEUSERTYPE;
    @JsonProperty("MATCHSTATUS")
    public String mATCHSTATUS;
}
