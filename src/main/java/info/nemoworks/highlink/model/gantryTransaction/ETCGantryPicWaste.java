package info.nemoworks.highlink.model.gantryTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import lombok.Data;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @description: 对应预处理输入中门架接收的 “ETC门架牌识数据（tbl_ETCGantryPicWaste）”
 * @author：jimi
 * @date: 2024/1/21
 * @Copyright：
 */
@Data
public class ETCGantryPicWaste implements HighwayTransaction {
    @JsonProperty("PICID")
    private String picid;
    @JsonProperty("GANTRYID")
    private String gantryid;
    @JsonProperty("GANTRYHEX")
    private String gantryhex;
    @JsonProperty("PICTIME")
    private String pictime;
    @JsonProperty("GANTRYORDERNUM")
    private String gantryordernum;
    @JsonProperty("DRIVEDIR")
    private String drivedir;
    @JsonProperty("CAMERANUM")
    private String cameranum;
    @JsonProperty("HOURBATCHNO")
    private String hourbatchno;
    @JsonProperty("SHOOTPOSITION")
    private String shootposition;
    @JsonProperty("LANENUM")
    private String lanenum;
    @JsonProperty("IDENTIFYVLP")
    private String identifyvlp;
    @JsonProperty("IDENTIFYVLPC")
    private String identifyvlpc;
    @JsonProperty("VEHICLESPEED")
    private String vehiclespeed;
    @JsonProperty("IDENTIFYTYPE")
    private String identifytype;
    @JsonProperty("VEHICLEMODEL")
    private String vehiclemodel;
    @JsonProperty("VEHICLECOLOR")
    private String vehiclecolor;
    @JsonProperty("IMAGESIZE")
    private String imagesize;
    @JsonProperty("LICENSEIMAGESIZE")
    private String licenseimagesize;
    @JsonProperty("BINIMAGESIZE")
    private String binimagesize;
    @JsonProperty("RELIABILITY")
    private String reliability;
    @JsonProperty("VEHIFEATURECODE")
    private String vehifeaturecode;
    @JsonProperty("FACEFEATURECODE")
    private String facefeaturecode;
    @JsonProperty("VERIFYCODE")
    private String verifycode;
    @JsonProperty("TRADEID")
    private String tradeid;
    @JsonProperty("MATCHSTATUS")
    private String matchstatus;
    @JsonProperty("VALIDSTATUS")
    private String validstatus;
    @JsonProperty("DEALSTATUS")
    private String dealstatus;
    @JsonProperty("RELATEDPICID")
    private String relatedpicid;
    @JsonProperty("ALLRELATEDPICID")
    private String allrelatedpicid;
    @JsonProperty("STATIONDBTIME")
    private String stationdbtime;
    @JsonProperty("STATIONDEALTIME")
    private String stationdealtime;
    @JsonProperty("STATIONVALIDTIME")
    private String stationvalidtime;
    @JsonProperty("STATIONMATCHTIME")
    private String stationmatchtime;
    @JsonProperty("REQUESTNAME")
    private String requestname;
    @JsonProperty("RESPONSENAME")
    private String responsename;
    @JsonProperty("RECEIVETIME")
    private String receivetime;
    @JsonProperty("MSGID")
    private String msgid;
    @JsonProperty("SPARE1")
    private String spare1;
    @JsonProperty("SPARE2")
    private String spare2;
    @JsonProperty("SPARE3")
    private String spare3;
    @JsonProperty("SPARE4")
    private String spare4;
    @JsonProperty("PROSTATUS")
    private String prostatus;
    @JsonProperty("PROTIME")
    private String protime;
    @JsonProperty("REMARK")
    private String remark;

    @Override
    public String getID() {
        return picid;
    }
}
