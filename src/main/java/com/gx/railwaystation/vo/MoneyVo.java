package com.gx.railwaystation.vo;

import com.gx.railwaystation.po.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MoneyVo extends SysUser {


    /*
    *账户id
    */
    private Integer moneyId;

    /*
    *账户余额
    */
    private BigDecimal moneySun;
}
