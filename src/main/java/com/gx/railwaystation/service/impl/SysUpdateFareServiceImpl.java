package com.gx.railwaystation.service.impl;

import com.gx.railwaystation.mapper.SysUpdateFareMapper;
import com.gx.railwaystation.service.SysUpdateFareService;
import com.gx.railwaystation.vo.FareVo;
import com.gx.railwaystation.vo.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUpdateFareServiceImpl implements SysUpdateFareService {

    @Autowired
    private SysUpdateFareMapper sysUpdateFareMapper;

    /**
     * 分页查询数据
     */
    @Override
    public LayuiTableData<FareVo> selectPageList(int page, int limit, String reserveNumber, String startDate, String endDate) {
        //查询数据
        List<FareVo> fareVos = this.sysUpdateFareMapper.selectPageList(page,limit,reserveNumber,startDate,endDate);
        //查询分页总条数
        int count = this.sysUpdateFareMapper.countAll(reserveNumber, startDate, endDate);
        return new LayuiTableData<>(count,fareVos);
    }
}
