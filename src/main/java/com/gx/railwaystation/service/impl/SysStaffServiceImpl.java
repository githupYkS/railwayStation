package com.gx.railwaystation.service.impl;

import com.gx.railwaystation.mapper.SysStaffMapper;
import com.gx.railwaystation.po.SysStaff;
import com.gx.railwaystation.service.SysStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysStaffServiceImpl implements SysStaffService {

    @Autowired
    private SysStaffMapper sysStaffMapper;

    /**
     * 根据页面输入账号查询数据
     */
    @Override
    public SysStaff StaffAccount(String account) {
        return this.sysStaffMapper.StaffAccount(account);
    }

    @Override
    public boolean updateStaff(SysStaff sysStaff) {
        return this.sysStaffMapper.updateStaff(sysStaff)>0;
    }

    @Override
    public SysStaff selectStaff(Integer StaffId) {
        return this.sysStaffMapper.SelectStaffId(StaffId);
    }
}
