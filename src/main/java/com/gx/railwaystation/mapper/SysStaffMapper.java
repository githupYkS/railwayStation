package com.gx.railwaystation.mapper;

import com.gx.railwaystation.po.SysStaff;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SysStaffMapper {

    /**
     * 根据页面输入账号查询数据
     */
    SysStaff StaffAccount(String account);

    /**
     * 查询员工表ID等于1的数据
     */
    SysStaff SelectStaffId(Integer staffId);

    /**
     * 修改工表ID等于1的数据
     */
    boolean update(Integer staffId, BigDecimal staffWallet);

    /**
     * 修改售票员数据
     */
    Integer updateStaff(SysStaff sysStaff);

    /**
     * 逻辑删除数据
     */
    Integer deleteByStaffId(Integer staffId);
}