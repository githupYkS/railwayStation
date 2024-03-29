package com.gx.railwaystation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gx.railwaystation.po.SysMoney;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SysMoneyMapper extends BaseMapper<SysMoney> {

    /*
    *查询当前用户金额
    */
    SysMoney selectByUserId(Integer userId);

    /*
    * 修改当前用户的金额
    */
    Integer updateMoney(SysMoney sysMoney);

    /*
    *新增用户金额
    */
    Integer insertMoney(SysMoney money);
}
