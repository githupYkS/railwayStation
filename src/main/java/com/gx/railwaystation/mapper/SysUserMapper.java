package com.gx.railwaystation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gx.railwaystation.po.SysUser;
import com.gx.railwaystation.vo.MoneyVo;
import org.springframework.stereotype.Repository;

/**
 * SysUserMapper继承基类
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户输入账号查询数据
     */
    SysUser userAccount(String account);

    /**
     * 注册用户
     */
    boolean register(SysUser user);

    /*
    *查询当前的登录信息
    */
    MoneyVo selectAllByUserId(Integer userId);

    /*
    *查询当前的登录的账户信息
    */
    SysUser selectUserById(Integer userId);

    /*
    *修改当前的用户信息
    */
    Integer updateUser(SysUser user);

    /*
     *删除当前的用户信息
     */
    Integer deleteByUserId(Integer userId);
}