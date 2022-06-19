package com.gx.railwaystation.vo;

import com.gx.railwaystation.po.SysReserve;

public class reserveVo extends SysReserve {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 支付ID
     */
    private Integer reserveTypeId;

    /**
     * 支付类型
     */
    private String reserveTypeName;

    /**
     * 座位ID
     */
    private Integer saddieId;

    /**
     * 座位类型
     */
    private String saddieName;

    /**
     * 列车编号
     */
    private String trainNumber;

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Integer getReserveTypeId() {
        return reserveTypeId;
    }

    @Override
    public void setReserveTypeId(Integer reserveTypeId) {
        this.reserveTypeId = reserveTypeId;
    }

    public String getReserveTypeName() {
        return reserveTypeName;
    }

    public void setReserveTypeName(String reserveTypeName) {
        this.reserveTypeName = reserveTypeName;
    }

    @Override
    public Integer getSaddieId() {
        return saddieId;
    }

    @Override
    public void setSaddieId(Integer saddieId) {
        this.saddieId = saddieId;
    }

    public String getSaddieName() {
        return saddieName;
    }

    public void setSaddieName(String saddieName) {
        this.saddieName = saddieName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Override
    public String toString() {
        return "reserveVo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", reserveTypeId=" + reserveTypeId +
                ", reserveTypeName='" + reserveTypeName + '\'' +
                ", saddieId=" + saddieId +
                ", saddieName='" + saddieName + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                '}';
    }
}
