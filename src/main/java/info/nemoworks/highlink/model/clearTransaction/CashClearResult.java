package info.nemoworks.highlink.model.clearTransaction;

/**
 * @description: 对应 现金清分结果表（tbl_ExClearResultCash）
 * @author：jimi
 * @date: 2024/5/12
 * @Copyright：
 */

import lombok.Data;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

@Data
public class CashClearResult implements ClearResult, Cloneable {
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

    private Integer AMOUNT;

    private Integer DISCOUNTAMOUNT;

    private Integer CHARGEAMOUNT;

    private String REPORTFLAG;

    private Timestamp LASTTIME;

    private String REPORTDATE;

    private String EXTOLLSTATION;


    @Override
    public Object clone() {
        CashClearResult res = null;
        try {
            res = (CashClearResult) super.clone();   //浅拷贝
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return res;
    }
}
