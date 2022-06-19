package com.gx.railwaystation.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysMoney implements Serializable {
    /**
     * 金额ID
     */
    private Integer moneyId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 金额
     */
    private BigDecimal moneySun;

    private static final long serialVersionUID = 1L;

    public Integer getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Integer moneyId) {
        this.moneyId = moneyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getMoneySun() {
        return moneySun;
    }

    public void setMoneySun(BigDecimal moneySun) {
        this.moneySun = moneySun;
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
        SysMoney other = (SysMoney) that;
        return (this.getMoneyId() == null ? other.getMoneyId() == null : this.getMoneyId().equals(other.getMoneyId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMoneySun() == null ? other.getMoneySun() == null : this.getMoneySun().equals(other.getMoneySun()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMoneyId() == null) ? 0 : getMoneyId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMoneySun() == null) ? 0 : getMoneySun().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", moneyId=").append(moneyId);
        sb.append(", userId=").append(userId);
        sb.append(", moneySun=").append(moneySun);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}