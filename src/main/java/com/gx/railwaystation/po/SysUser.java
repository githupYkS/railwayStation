package com.gx.railwaystation.po;

import java.io.Serializable;

public class SysUser implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 盐
     */
    private String userSalt;

    /**
     * 性别(0:未知1:男2:女)
     */
    private Byte userSex;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 电话
     */
    private String userPhone;

    /**
     * 身份证
     */
    private String userIdentification;

    /**
     * 头像
     */
    private String userHead;

    /*
    *是否注销
    */
    private Integer  isCancel;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public Byte getUserSex() {
        return userSex;
    }

    public void setUserSex(Byte userSex) {
        this.userSex = userSex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserIdentification() {
        return userIdentification;
    }

    public void setUserIdentification(String userIdentification) {
        this.userIdentification = userIdentification;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userSalt='" + userSalt + '\'' +
                ", userSex=" + userSex +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userIdentification='" + userIdentification + '\'' +
                ", userHead='" + userHead + '\'' +
                ", isCancel=" + isCancel +
                '}';
    }
}