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
public class ExpandClearResult implements ClearResult, Cloneable{
    private String CROPID;

    private String VEHICLETYPE;

    private String PAYCARDTYPE;

    private String LDATE;

    private String NATIONCLEARDATE;

    private String CLEARDATE;

    private String BUSIBATCH;

    private String CLEARTYPE;

    private String ISSUERID;

    private String AMOUNT;

    private String REPORTFLAG;

    private String LASTTIME;

    private String MODIFYFLAG;

    @Override
    public Object clone() {
        ExpandClearResult res = null;
        try {
            res = (ExpandClearResult) super.clone();   //浅拷贝
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return res;
    }
}
