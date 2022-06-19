package com.gx.railwaystation.po;

import java.io.Serializable;

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

    private static final long serialVersionUID = 1L;

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
        SysStaff other = (SysStaff) that;
        return (this.getStaffId() == null ? other.getStaffId() == null : this.getStaffId().equals(other.getStaffId()))
            && (this.getPositionId() == null ? other.getPositionId() == null : this.getPositionId().equals(other.getPositionId()))
            && (this.getStaffAccount() == null ? other.getStaffAccount() == null : this.getStaffAccount().equals(other.getStaffAccount()))
            && (this.getStaffPassword() == null ? other.getStaffPassword() == null : this.getStaffPassword().equals(other.getStaffPassword()))
            && (this.getStaffSalt() == null ? other.getStaffSalt() == null : this.getStaffSalt().equals(other.getStaffSalt()))
            && (this.getStaffSex() == null ? other.getStaffSex() == null : this.getStaffSex().equals(other.getStaffSex()))
            && (this.getStaffName() == null ? other.getStaffName() == null : this.getStaffName().equals(other.getStaffName()))
            && (this.getStaffPhone() == null ? other.getStaffPhone() == null : this.getStaffPhone().equals(other.getStaffPhone()))
            && (this.getStaffIdentification() == null ? other.getStaffIdentification() == null : this.getStaffIdentification().equals(other.getStaffIdentification()))
            && (this.getStaffHead() == null ? other.getStaffHead() == null : this.getStaffHead().equals(other.getStaffHead()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStaffId() == null) ? 0 : getStaffId().hashCode());
        result = prime * result + ((getPositionId() == null) ? 0 : getPositionId().hashCode());
        result = prime * result + ((getStaffAccount() == null) ? 0 : getStaffAccount().hashCode());
        result = prime * result + ((getStaffPassword() == null) ? 0 : getStaffPassword().hashCode());
        result = prime * result + ((getStaffSalt() == null) ? 0 : getStaffSalt().hashCode());
        result = prime * result + ((getStaffSex() == null) ? 0 : getStaffSex().hashCode());
        result = prime * result + ((getStaffName() == null) ? 0 : getStaffName().hashCode());
        result = prime * result + ((getStaffPhone() == null) ? 0 : getStaffPhone().hashCode());
        result = prime * result + ((getStaffIdentification() == null) ? 0 : getStaffIdentification().hashCode());
        result = prime * result + ((getStaffHead() == null) ? 0 : getStaffHead().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", staffId=").append(staffId);
        sb.append(", positionId=").append(positionId);
        sb.append(", staffAccount=").append(staffAccount);
        sb.append(", staffPassword=").append(staffPassword);
        sb.append(", staffSalt=").append(staffSalt);
        sb.append(", staffSex=").append(staffSex);
        sb.append(", staffName=").append(staffName);
        sb.append(", staffPhone=").append(staffPhone);
        sb.append(", staffIdentification=").append(staffIdentification);
        sb.append(", staffHead=").append(staffHead);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}