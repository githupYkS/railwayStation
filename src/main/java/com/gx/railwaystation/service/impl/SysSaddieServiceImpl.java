package com.gx.railwaystation.service.impl;

import com.gx.railwaystation.mapper.SysSaddieMapper;
import com.gx.railwaystation.po.SysSaddie;
import com.gx.railwaystation.service.SysSaddieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysSaddieServiceImpl implements SysSaddieService {

    @Autowired
    private SysSaddieMapper sysSaddieMapper;

    /**
     * 根据座位ID查询数据
     */
    @Override
    public SysSaddie SelectFilter(Integer saddieId) {
        return this.sysSaddieMapper.SelectFilter(saddieId);
    }
}
