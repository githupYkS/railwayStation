package com.gx.railwaystation.service.impl;

import com.gx.railwaystation.mapper.SysUserMapper;
import com.gx.railwaystation.po.SysUser;
import com.gx.railwaystation.service.SysUserService;
import com.gx.railwaystation.vo.MoneyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户输入账号查询数据
     */
    @Override
    public SysUser userAccount(String account) {
        return this.sysUserMapper.userAccount(account);
    }

    /**
     * 注册用户
     */
    @Override
    public boolean register(SysUser user) {
        return this.sysUserMapper.register(user);
    }

    /*查找用户金额*/
    @Override
    public MoneyVo selectAllByUserId(Integer userId) {
        return this.sysUserMapper.selectAllByUserId(userId);
    }

    @Override
    public SysUser selectUserById(Integer userId) {
        return this.sysUserMapper.selectUserById(userId);
    }

    @Override
    public boolean updateUser(SysUser user) {
        return this.sysUserMapper.updateUser(user)>0;
    }

    @Override
    public boolean deleteByUserId(Integer userId) {
        return this.sysUserMapper.deleteByUserId(userId)>0;
    }
}
