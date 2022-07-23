package com.gx.railwaystation.po;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
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

    /**
     * 是否删除
     */
    private Integer isDelete;


}