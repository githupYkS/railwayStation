package com.gx.railwaystation.vo;


import com.gx.railwaystation.po.SysTrain;

import java.io.Serializable;

public class trainVo extends SysTrain implements Serializable {

    /**
     * 车类ID
     */
    private Integer carId;

    /**
     * 车类名称
     */
    private String carName;

    /**
     * 站点ID
     */
    private Integer siteId;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 状态ID
     */
    private Integer trainTypeId;

    /**
     * 状态名称
     */
    private String trainTypeName;

    /**
     * 停靠ID
     */
    private Integer berthId;

    /**
     * 停靠地点
     */
    private String berthPlace;

    /**
     * 订票ID
     */
    private Integer reserveId;

    @Override
    public Integer getCarId() {
        return carId;
    }

    @Override
    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

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
    public Integer getTrainTypeId() {
        return trainTypeId;
    }

    @Override
    public void setTrainTypeId(Integer trainTypeId) {
        this.trainTypeId = trainTypeId;
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }

    public void setTrainTypeName(String trainTypeName) {
        this.trainTypeName = trainTypeName;
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

    @Override
    public Integer getReserveId() {
        return reserveId;
    }

    @Override
    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    @Override
    public String toString() {
        return "trainVo{" +
                "carId=" + carId +
                ", carName='" + carName + '\'' +
                ", siteId=" + siteId +
                ", siteName='" + siteName + '\'' +
                ", trainTypeId=" + trainTypeId +
                ", trainTypeName='" + trainTypeName + '\'' +
                ", berthId=" + berthId +
                ", berthPlace='" + berthPlace + '\'' +
                ", reserveId=" + reserveId +
                '}';
    }
}
