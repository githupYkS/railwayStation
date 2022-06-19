package com.gx.railwaystation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysAccountStatement implements Serializable {

    /*
    *资产明细id
    */
    private Integer detailId;

    /*
    *金额id
    */
    private Integer moneyId;

    /*
    *消费类型
    */
    private Integer consumerType;

    /*
    *消费时间
    */
    private Date consumerTime;

    /**
     * 金额变更
     */
    private BigDecimal ammountChange;
 }
