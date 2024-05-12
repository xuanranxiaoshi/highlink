package info.nemoworks.highlink.model.clearTransaction;

import lombok.Data;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @description: ETC 清分结果表
 * @author：jimi
 * @date: 2024/5/12
 * @Copyright：
 */

@Data
public class ETCClearResult implements ClearResult{
    @JsonProperty("CROPID")
    public String cROPID;
    @JsonProperty("TOLLSECTIONID")
    public String tOLLSECTIONID;
    @JsonProperty("TOLLINTERVALID")
    public String tOLLINTERVALID;
    @JsonProperty("TOLLSTATION")
    public String tOLLSTATION;

    @JsonProperty("VEHICLETYPE")
    public String vEHICLETYPE;
    @JsonProperty("PAYTYPE")
    public String pAYTYPE;
    @JsonProperty("PAYCARDTYPE")
    public String pAYCARDTYPE;
    @JsonProperty("PROVINCETYPE")
    public String pROVINCETYPE;
    @JsonProperty("LDATE")
    public String lDATE;
    @JsonProperty("NATIONCLEARDATE")
    public String nATIONCLEARDATE;
    @JsonProperty("NATIONSPLITDATE")
    public String nATIONSPLITDATE;
    @JsonProperty("CLEARDATE")
    public String cLEARDATE;
    @JsonProperty("MULTIPROVINCE")
    public String mULTIPROVINCE;
    @JsonProperty("MODIFYFLAG")
    public String mODIFYFLAG;
    @JsonProperty("ROADTYPE")
    public String rOADTYPE;
    @JsonProperty("BUSIBATCH")
    public String bUSIBATCH;
    @JsonProperty("UNIONPAYTYPE")
    public String uNIONPAYTYPE;
    @JsonProperty("CLEARTYPE")
    public String cLEARTYPE;
    @JsonProperty("ISSUERID")
    public String iSSUERID;
    @JsonProperty("TOLLPROVINCEID")
    public String tOLLPROVINCEID;
    @JsonProperty("SERPROVINCEID")
    public String sERPROVINCEID;
    @JsonProperty("TOLLCROPID")
    public String tOLLCROPID;
    @JsonProperty("SECTIONID")
    public String sECTIONID;
    @JsonProperty("AMOUNT")
    public String aMOUNT;
    @JsonProperty("DISCOUNTAMOUNT")
    public String dISCOUNTAMOUNT;
    @JsonProperty("CHARGEAMOUNT")
    public String cHARGEAMOUNT;
    @JsonProperty("REPORTFLAG")
    public String rEPORTFLAG;
    @JsonProperty("LASTTIME")
    public String lASTTIME;
    @JsonProperty("EXTOLLSTATION")
    public String eXTOLLSTATION;
}
