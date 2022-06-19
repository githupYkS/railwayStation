package com.gx.railwaystation.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 */
public class SysReserve implements Serializable {
    /**
     * 订票id
     */
    private Integer reserveId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 列车id
     */
    private Integer trainId;

    /**
     * 订票类型ID
     */
    private Integer reserveTypeId;

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
     * 票价
     */
    private BigDecimal reserveFares;

    /**
     * 半票价
     */
    private BigDecimal reserveAdidas;

    /**
     * 是否退票(1:退票2:不退票)
     */
    private Boolean reserveTicket;

    /**
     * 座位类型ID
     */
    private Integer saddieId;

    private static final long serialVersionUID = 1L;

    public Integer getReserveId() {
        return reserveId;
    }

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getReserveTypeId() {
        return reserveTypeId;
    }

    public void setReserveTypeId(Integer reserveTypeId) {
        this.reserveTypeId = reserveTypeId;
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

    public BigDecimal getReserveFares() {
        return reserveFares;
    }

    public void setReserveFares(BigDecimal reserveFares) {
        this.reserveFares = reserveFares;
    }

    public BigDecimal getReserveAdidas() {
        return reserveAdidas;
    }

    public void setReserveAdidas(BigDecimal reserveAdidas) {
        this.reserveAdidas = reserveAdidas;
    }

    public Boolean getReserveTicket() {
        return reserveTicket;
    }

    public void setReserveTicket(Boolean reserveTicket) {
        this.reserveTicket = reserveTicket;
    }

    public Integer getSaddieId() {
        return saddieId;
    }

    public void setSaddieId(Integer saddieId) {
        this.saddieId = saddieId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysReserve other = (SysReserve) that;
        return (this.getReserveId() == null ? other.getReserveId() == null : this.getReserveId().equals(other.getReserveId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTrainId() == null ? other.getTrainId() == null : this.getTrainId().equals(other.getTrainId()))
            && (this.getReserveTypeId() == null ? other.getReserveTypeId() == null : this.getReserveTypeId().equals(other.getReserveTypeId()))
            && (this.getReserveNumber() == null ? other.getReserveNumber() == null : this.getReserveNumber().equals(other.getReserveNumber()))
            && (this.getReserveTime() == null ? other.getReserveTime() == null : this.getReserveTime().equals(other.getReserveTime()))
            && (this.getReserveFares() == null ? other.getReserveFares() == null : this.getReserveFares().equals(other.getReserveFares()))
            && (this.getReserveAdidas() == null ? other.getReserveAdidas() == null : this.getReserveAdidas().equals(other.getReserveAdidas()))
            && (this.getReserveTicket() == null ? other.getReserveTicket() == null : this.getReserveTicket().equals(other.getReserveTicket()))
            && (this.getSaddieId() == null ? other.getSaddieId() == null : this.getSaddieId().equals(other.getSaddieId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReserveId() == null) ? 0 : getReserveId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTrainId() == null) ? 0 : getTrainId().hashCode());
        result = prime * result + ((getReserveTypeId() == null) ? 0 : getReserveTypeId().hashCode());
        result = prime * result + ((getReserveNumber() == null) ? 0 : getReserveNumber().hashCode());
        result = prime * result + ((getReserveTime() == null) ? 0 : getReserveTime().hashCode());
        result = prime * result + ((getReserveFares() == null) ? 0 : getReserveFares().hashCode());
        result = prime * result + ((getReserveAdidas() == null) ? 0 : getReserveAdidas().hashCode());
        result = prime * result + ((getReserveTicket() == null) ? 0 : getReserveTicket().hashCode());
        result = prime * result + ((getSaddieId() == null) ? 0 : getSaddieId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reserveId=").append(reserveId);
        sb.append(", userId=").append(userId);
        sb.append(", trainId=").append(trainId);
        sb.append(", reserveTypeId=").append(reserveTypeId);
        sb.append(", reserveNumber=").append(reserveNumber);
        sb.append(", reserveTime=").append(reserveTime);
        sb.append(", reserveFares=").append(reserveFares);
        sb.append(", reserveAdidas=").append(reserveAdidas);
        sb.append(", reserveTicket=").append(reserveTicket);
        sb.append(", saddieId=").append(saddieId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}