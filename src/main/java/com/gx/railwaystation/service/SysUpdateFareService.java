package com.gx.railwaystation.service;

import com.gx.railwaystation.vo.FareVo;
import com.gx.railwaystation.vo.LayuiTableData;

public interface SysUpdateFareService {

    /**
     * 查询分页数据
     */
    LayuiTableData<FareVo> selectPageList(int page, int limit,String reserveNumber,String startDate,String endDate);
}
