package com.gx.railwaystation.vo;

import com.gx.railwaystation.po.SysTrain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MoneyPlace extends SysTrain implements Serializable {
    /*
    *起点站名称
    */
    private String siteName;

    /*
    *终点站查询
    */
    private String berthPlace;
}
