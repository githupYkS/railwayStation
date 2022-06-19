package com.gx.railwaystation.vo;

import com.gx.railwaystation.po.SysTrain;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FareVo extends SysTrain implements Serializable {

    /**
     * 站点ID
     */
    private Integer siteId;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 停靠ID
     */
    private Integer berthId;

    /**
     * 停靠地点
     */
    private String berthPlace;

    /**
     * 编号
     */
    private String reserveNumber;

    /**
     * 订票时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd - HH:mm:ss")
    private Date reserveTime;

    /**
     * 座位类型ID
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

    @Override
    public Integer getSiteId() {
        return siteId;
    }

    @Override
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @Override
    public Integer getBerthId() {
        return berthId;
    }

    @Override
    public void setBerthId(Integer berthId) {
        this.berthId = berthId;
    }

    public String getBerthPlace() {
        return berthPlace;
    }

    public void setBerthPlace(String berthPlace) {
        this.berthPlace = berthPlace;
    }

    public String getReserveNumber() {
        return reserveNumber;
    }

    public void setReserveNumber(String reserveNumber) {
        this.reserveNumber = reserveNumber;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

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
        return "FareVo{" +
                "siteId=" + siteId +
                ", siteName='" + siteName + '\'' +
                ", berthId=" + berthId +
                ", berthPlace='" + berthPlace + '\'' +
                ", reserveNumber='" + reserveNumber + '\'' +
                ", reserveTime=" + reserveTime +
                ", saddieId=" + saddieId +
                ", saddieName='" + saddieName + '\'' +
                ", saddieCount=" + saddieCount +
                ", saddieMoney=" + saddieMoney +
                '}';
    }
}
