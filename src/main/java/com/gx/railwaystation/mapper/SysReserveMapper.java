package com.gx.railwaystation.mapper;

import com.gx.railwaystation.po.SysReserve;
import com.gx.railwaystation.vo.reserveVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SysReserveMapper继承基类
 */
@Repository
public interface SysReserveMapper {

    /**
     * 座位类型下拉框
     */
    List<reserveVo> SysSaddieId();

    /**
     * 支付类型下拉框
     */
    List<reserveVo> SysReserveTypeId();

    /**
     * 新增订票数据
     */
    boolean inset(SysReserve reserve);
}