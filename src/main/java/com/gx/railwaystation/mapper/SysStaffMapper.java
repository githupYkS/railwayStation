package com.gx.railwaystation.mapper;

import com.gx.railwaystation.po.SysStaff;
import org.springframework.stereotype.Repository;

@Repository
public interface SysStaffMapper {

    /**
     * 根据页面输入账号查询数据
     */
    SysStaff StaffAccount(String account);
}