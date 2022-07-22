package com.gx.railwaystation.service;

import com.gx.railwaystation.po.SysStaff;

public interface SysStaffService {

    /**
     * 根据页面输入账号查询数据
     */
    SysStaff StaffAccount(String account);

    /**
     * 修改售票员数据
     */
    boolean updateStaff(SysStaff sysStaff);

    /**
     * 查询当前的登录的信息
     */
    SysStaff selectStaff(Integer StaffId);
}
