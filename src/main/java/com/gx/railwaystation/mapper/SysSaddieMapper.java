package com.gx.railwaystation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gx.railwaystation.po.SysMoney;
import com.gx.railwaystation.po.SysSaddie;
import org.springframework.stereotype.Repository;


@Repository
public interface SysSaddieMapper extends BaseMapper<SysSaddie> {

    /**
     * 根据saddieId查询金额
     */
    SysSaddie SelectFilter(Integer saddieId);

    /**
     * 修改座位数
     */
    boolean update(Integer saddieId,Integer saddieCount);
}