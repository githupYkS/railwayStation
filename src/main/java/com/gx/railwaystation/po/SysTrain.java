package com.gx.railwaystation.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class SysTrain implements Serializable {
    /**
     * 列车id
     */
    private Integer trainId;

    /**
     * 站点ID
     */
    private Integer siteId;

    /**
     * 车类ID 
     */
    private Integer carId;

    /**
     * 时刻ID
     */
    private Integer momentId;

    /**
     * 出发时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trainOutTime;

    /**
     * 到达时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trainReachTime;

    /**
     * 座位
     */
    private Integer trainSeat;

    /**
     * 车辆编号
     */
    private String trainNumber;

    /**
     * 列车状态ID
     */
    private Integer trainTypeId;

    /**
     * 停靠ID
     */
    private Integer berthId;

    /**
     * 订票ID
     */
    private Integer reserveId;

    /**
     * 票价
     */
    private BigDecimal reserveFares;

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Date getTrainOutTime() {
        return trainOutTime;
    }

    public void setTrainOutTime(Date trainOutTime) {
        this.trainOutTime = trainOutTime;
    }

    public Date getTrainReachTime() {
        return trainReachTime;
    }

    public void setTrainReachTime(Date trainReachTime) {
        this.trainReachTime = trainReachTime;
    }

    public Integer getTrainSeat() {
        return trainSeat;
    }

    public void setTrainSeat(Integer trainSeat) {
        this.trainSeat = trainSeat;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Integer getTrainTypeId() {
        return trainTypeId;
    }

    public void setTrainTypeId(Integer trainTypeId) {
        this.trainTypeId = trainTypeId;
    }

    public Integer getBerthId() {
        return berthId;
    }

    public void setBerthId(Integer berthId) {
        this.berthId = berthId;
    }

    public Integer getReserveId() {
        return reserveId;
    }

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public BigDecimal getReserveFares() {
        return reserveFares;
    }

    public void setReserveFares(BigDecimal reserveFares) {
        this.reserveFares = reserveFares;
    }

    @Override
    public String toString() {
        return "SysTrain{" +
                "trainId=" + trainId +
                ", siteId=" + siteId +
                ", carId=" + carId +
                ", momentId=" + momentId +
                ", trainOutTime=" + trainOutTime +
                ", trainReachTime=" + trainReachTime +
                ", trainSeat=" + trainSeat +
                ", trainNumber='" + trainNumber + '\'' +
                ", trainTypeId=" + trainTypeId +
                ", berthId=" + berthId +
                ", reserveId=" + reserveId +
                ", reserveFares=" + reserveFares +
                '}';
    }
}