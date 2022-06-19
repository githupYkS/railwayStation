package com.gx.railwaystation.service;

import com.gx.railwaystation.po.SysReserve;
import com.gx.railwaystation.vo.H5SelectVo;
import com.gx.railwaystation.vo.JsonMsg;

import java.util.List;

public interface SysReserveService {

    /**
     * 座位类型下拉框
     */
    List<H5SelectVo> SysSaddieId();

    /**
     * 支付类型下拉框
     */
    List<H5SelectVo> SysReserveTypeId();

    /**
     * 新增订票数据
     * @return
     */
    JsonMsg inset(SysReserve reserve);
}