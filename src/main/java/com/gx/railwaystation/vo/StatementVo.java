package com.gx.railwaystation.vo;

import com.gx.railwaystation.po.SysAccountStatement;

import java.io.Serializable;
import java.math.BigDecimal;

public class StatementVo extends SysAccountStatement implements Serializable {

    /*
    *用户id
    */
    private Integer userId;

    /**
     * 用户金额
     */
    private BigDecimal moneySun;

}
