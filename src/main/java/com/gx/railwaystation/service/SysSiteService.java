package com.gx.railwaystation.service;

import com.gx.railwaystation.vo.LayuiTreeVo;

import java.util.List;

public interface SysSiteService {

    /**
     * 查询车站 for layui tree 无根节点
     * @return layui tree 所需数据
     */
    List<LayuiTreeVo> selectForLayuiTree();
}
