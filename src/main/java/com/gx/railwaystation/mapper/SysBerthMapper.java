package com.gx.railwaystation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gx.railwaystation.po.SysBerth;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysBerthMapper extends BaseMapper<SysBerth> {

    /*
     *查询全部停靠站信息
     */
    List<SysBerth> selectAllBreath();
}
