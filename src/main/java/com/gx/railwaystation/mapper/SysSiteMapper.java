package com.gx.railwaystation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gx.railwaystation.po.SysBerth;
import com.gx.railwaystation.po.SysMoney;
import com.gx.railwaystation.po.SysSite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysSiteMapper extends BaseMapper<SysSite> {

    /**
     * 查询车站全部信息
     */
    List<SysSite> selectAll();

}
