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
    private String CROPID;

    private String TOLLSECTIONID;

    private String TOLLINTERVALID;

    private String TOLLSTATION;

    private String VEHICLETYPE;

    private String PAYTYPE;

    private String PAYCARDTYPE;

    private String PROVINCETYPE;

    private String LDATE;

    private String NATIONCLEARDATE;

    private String NATIONSPLITDATE;

    private String CLEARDATE;

    private String MULTIPROVINCE;

    private String MODIFYFLAG;

    private String ROADTYPE;

    private String BUSIBATCH;

    private String UNIONPAYTYPE;

    private String CLEARTYPE;

    private String ISSUERID;

    private String TOLLPROVINCEID;

    private String SERPROVINCEID;

    private String TOLLCROPID;

    private String SECTIONID;

    private String AMOUNT;

    private String DISCOUNTAMOUNT;

    private String CHARGEAMOUNT;

    private String REPORTFLAG;

    private String LASTTIME;

    private String EXTOLLSTATION;

    @Override
    public Object clone() {
        ETCClearResult res = null;
        try {
            res = (ETCClearResult) super.clone();   //浅拷贝
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return res;
    }
}
