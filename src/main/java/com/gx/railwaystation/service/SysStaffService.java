package com.gx.railwaystation.service;

import com.gx.railwaystation.po.SysStaff;

public interface SysStaffService {

    /**
     * 根据页面输入账号查询数据
     */
    SysStaff StaffAccount(String account);
}
