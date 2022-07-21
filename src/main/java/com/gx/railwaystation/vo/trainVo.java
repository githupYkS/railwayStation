package com.gx.railwaystation.vo;


import com.gx.railwaystation.po.SysTrain;
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
public class trainVo extends SysTrain implements Serializable {

    /**
     * 车类ID
     */
    private Integer carId;

    /**
     * 车类名称
     */
    private String carName;

    /**
     * 站点ID
     */
    private Integer siteId;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 状态ID
     */
    private Integer trainTypeId;

    /**
     * 状态名称
     */
    private String trainTypeName;

    /**
     * 停靠ID
     */
    private Integer berthId;

    /**
     * 停靠地点
     */
    private String berthPlace;

    /**
     * 订票ID
     */
    private Integer reserveId;

    /*
    *座位表金额
    */
    private BigDecimal saddieMoney;
}
