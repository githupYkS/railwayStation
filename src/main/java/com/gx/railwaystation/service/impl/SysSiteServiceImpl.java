package com.gx.railwaystation.service.impl;

import com.gx.railwaystation.mapper.SysBerthMapper;
import com.gx.railwaystation.mapper.SysSiteMapper;
import com.gx.railwaystation.po.SysBerth;
import com.gx.railwaystation.po.SysSite;
import com.gx.railwaystation.service.SysSiteService;
import com.gx.railwaystation.vo.H5SelectVo;
import com.gx.railwaystation.vo.LayuiTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysSiteServiceImpl implements SysSiteService {

    private SysSiteMapper sysSiteMapper;

    private SysBerthMapper sysBerthMapper;

    @Autowired
    public SysSiteServiceImpl(SysSiteMapper sysSiteMapper, SysBerthMapper sysBerthMapper) {
        this.sysSiteMapper = sysSiteMapper;
        this.sysBerthMapper = sysBerthMapper;
    }

    /**
     * 查询车站 for layui tree 无根节点
     */
    @Override
    public List<LayuiTreeVo> selectForLayuiTree() {
        //查询车站全部信息
        List<SysSite> sysSites = this.sysSiteMapper.selectAll();
        return LayuiTreeListData(sysSites,0);
    }

    @Override
    public List<H5SelectVo> selectAllBreath() {
        //查询数据
        List<SysBerth> sysBerths = this.sysBerthMapper.selectAllBreath();
        List<H5SelectVo> h5SelectVos = new ArrayList<>();
        for (SysBerth sysberth: sysBerths) {
            h5SelectVos.add(new H5SelectVo(String.valueOf(sysberth.getBerthId()),sysberth.getBerthPlace()));
        }
        return h5SelectVos;
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
