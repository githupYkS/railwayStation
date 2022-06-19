package com.gx.railwaystation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysMoney extends SysAccountStatement implements Serializable {

    /*
    *账户id
    */
    private Integer moneyId;

    /*
    *用户id
    */
    private Integer userId;

    /*
     *账户金额
     */
    private BigDecimal moneySun;
}
