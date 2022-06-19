package com.gx.railwaystation.service;

import com.gx.railwaystation.po.SysSaddie;

public interface SysSaddieService {

    /**
     * 根据saddieId查询金额
     */
    SysSaddie SelectFilter(Integer saddieId);
}
