package com.gx.railwaystation.mapper;

import com.gx.railwaystation.po.SysSaddie;
import org.springframework.stereotype.Repository;


@Repository
public interface SysSaddieMapper {

    /**
     * 根据saddieId查询金额
     */
    SysSaddie SelectFilter(Integer saddieId);

    /**
     * 修改座位数
     */
    boolean update(Integer saddieId,Integer saddieCount);
}