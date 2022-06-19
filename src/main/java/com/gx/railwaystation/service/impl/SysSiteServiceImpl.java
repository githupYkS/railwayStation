package com.gx.railwaystation.service.impl;

import com.gx.railwaystation.mapper.SysSiteMapper;
import com.gx.railwaystation.po.SysSite;
import com.gx.railwaystation.service.SysSiteService;
import com.gx.railwaystation.vo.LayuiTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysSiteServiceImpl implements SysSiteService {

    @Autowired
    private SysSiteMapper sysSiteMapper;

    /**
     * 查询车站 for layui tree 无根节点
     */
    @Override
    public List<LayuiTreeVo> selectForLayuiTree() {
        //查询车站全部信息
        List<SysSite> sysSites = this.sysSiteMapper.selectAll();
        return LayuiTreeListData(sysSites,0);
    }

    /**
     * 递归处理  Layui Tree数据
     * @param sysSiteList 原始部门数据List
     * @param pid 父id
     * @return  子部门List
     */
    private List<LayuiTreeVo> LayuiTreeListData(List<SysSite> sysSiteList,int pid){
        List<LayuiTreeVo> list = new ArrayList<>();
        //遍历sysSiteList 查找出 指定pid的数据 添加到 layuiTreeVo
        for (SysSite sysSite : sysSiteList){
            //查询对应的pid
            if (sysSite.getParentId() == pid){
                LayuiTreeVo layuiTreeVo = new LayuiTreeVo();
                layuiTreeVo.setTitle(sysSite.getSiteName());
                layuiTreeVo.setId(sysSite.getSiteId());
                layuiTreeVo.setSpread(true);//默认打开
                //继续获取下级 将当前的id作为下级的父id
                layuiTreeVo.setChildren(LayuiTreeListData(sysSiteList,sysSite.getSiteId()));
                list.add(layuiTreeVo);
            }
        }
        //判断
        if (list.size() == 0){
            return null;
        }

        return list;
    }
}
