package info.nemoworks.highlink.model.clearTransaction;

import lombok.Data;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @description: 对应 拓展清分结果表（tbl_ExClearResultExpand）
 * @author：jimi
 * @date: 2024/5/12
 * @Copyright：
 */

@Data
public class ExpandClearResult implements ClearResult{
    @JsonProperty("CROPID")
    public String cROPID;
    @JsonProperty("VEHICLETYPE")
    public String vEHICLETYPE;
    @JsonProperty("PAYCARDTYPE")
    public String pAYCARDTYPE;
    @JsonProperty("LDATE")
    public String lDATE;
    @JsonProperty("NATIONCLEARDATE")
    public String nATIONCLEARDATE;
    @JsonProperty("CLEARDATE")
    public String cLEARDATE;
    @JsonProperty("BUSIBATCH")
    public String bUSIBATCH;
    @JsonProperty("CLEARTYPE")
    public String cLEARTYPE;
    @JsonProperty("ISSUERID")
    public String iSSUERID;
    @JsonProperty("AMOUNT")
    public String aMOUNT;
    @JsonProperty("REPORTFLAG")
    public String rEPORTFLAG;
    @JsonProperty("LASTTIME")
    public String lASTTIME;
    @JsonProperty("MODIFYFLAG")
    public String mODIFYFLAG;
}
