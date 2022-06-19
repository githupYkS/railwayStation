package com.gx.railwaystation.mapper;

import com.gx.railwaystation.po.SysSite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysSiteMapper {

    /**
     * 查询车站全部信息
     */
    List<SysSite> selectAll();
}
