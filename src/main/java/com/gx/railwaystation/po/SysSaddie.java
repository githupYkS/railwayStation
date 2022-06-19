package com.gx.railwaystation.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 * 
 */
public class SysSaddie implements Serializable {
    /**
     * 车座ID
     */
    private Integer saddieId;

    /**
     * 车辆名称
     */
    private String saddieName;

    /**
     * 座位数量
     */
    private Integer saddieCount;

    /**
     * 座位金额
     */
    private BigDecimal saddieMoney;

    public Integer getSaddieId() {
        return saddieId;
    }

    public void setSaddieId(Integer saddieId) {
        this.saddieId = saddieId;
    }

    public String getSaddieName() {
        return saddieName;
    }

    public void setSaddieName(String saddieName) {
        this.saddieName = saddieName;
    }

    public Integer getSaddieCount() {
        return saddieCount;
    }

    public void setSaddieCount(Integer saddieCount) {
        this.saddieCount = saddieCount;
    }

    public BigDecimal getSaddieMoney() {
        return saddieMoney;
    }

    public void setSaddieMoney(BigDecimal saddieMoney) {
        this.saddieMoney = saddieMoney;
    }

    @Override
    public String toString() {
        return "SysSaddie{" +
                "saddieId=" + saddieId +
                ", saddieName='" + saddieName + '\'' +
                ", saddieCount=" + saddieCount +
                ", saddieMoney=" + saddieMoney +
                '}';
    }
}