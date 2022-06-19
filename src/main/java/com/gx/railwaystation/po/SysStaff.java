package com.gx.railwaystation.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysStaff implements Serializable {
    /**
     * 员工id
     */
    private Integer staffId;

    /**
     * 职位id
     */
    private Integer positionId;

    /**
     * 账号
     */
    private String staffAccount;

    /**
     * 密码
     */
    private String staffPassword;

    /**
     * 盐
     */
    private String staffSalt;

    /**
     * 性别(0:未知1:男2:女)
     */
    private Byte staffSex;

    /**
     * 性名
     */
    private String staffName;

    /**
     * 电话
     */
    private String staffPhone;

    /**
     * 身份证
     */
    private String staffIdentification;

    /**
     * 头像
     */
    private String staffHead;

    /**
     * 员工余额
     */
    private BigDecimal staffWallet;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getStaffAccount() {
        return staffAccount;
    }

    public void setStaffAccount(String staffAccount) {
        this.staffAccount = staffAccount;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffSalt() {
        return staffSalt;
    }

    public void setStaffSalt(String staffSalt) {
        this.staffSalt = staffSalt;
    }

    public Byte getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(Byte staffSex) {
        this.staffSex = staffSex;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffIdentification() {
        return staffIdentification;
    }

    public void setStaffIdentification(String staffIdentification) {
        this.staffIdentification = staffIdentification;
    }

    public String getStaffHead() {
        return staffHead;
    }

    public void setStaffHead(String staffHead) {
        this.staffHead = staffHead;
    }

    public BigDecimal getStaffWallet() {
        return staffWallet;
    }

    public void setStaffWallet(BigDecimal staffWallet) {
        this.staffWallet = staffWallet;
    }

    @Override
    public String toString() {
        return "SysStaff{" +
                "staffId=" + staffId +
                ", positionId=" + positionId +
                ", staffAccount='" + staffAccount + '\'' +
                ", staffPassword='" + staffPassword + '\'' +
                ", staffSalt='" + staffSalt + '\'' +
                ", staffSex=" + staffSex +
                ", staffName='" + staffName + '\'' +
                ", staffPhone='" + staffPhone + '\'' +
                ", staffIdentification='" + staffIdentification + '\'' +
                ", staffHead='" + staffHead + '\'' +
                ", staffWallet=" + staffWallet +
                '}';
    }
}