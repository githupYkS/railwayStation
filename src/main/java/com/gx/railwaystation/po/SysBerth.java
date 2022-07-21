package com.gx.railwaystation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysBerth implements Serializable {

    /*
    *终点站id
    */
    private Integer berthId;

    /**
     * 终点站名称
     */
    private String berthPlace;
}
