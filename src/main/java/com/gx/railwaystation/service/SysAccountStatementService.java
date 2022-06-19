package com.gx.railwaystation.service;

import com.gx.railwaystation.mapper.SysMoneyMapper;
import com.gx.railwaystation.po.SysAccountStatement;
import com.gx.railwaystation.po.SysMoney;
import com.gx.railwaystation.vo.LayuiTableData;
import com.gx.railwaystation.vo.StatementVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysAccountStatementService {

    /*
     *账户--》查询当前用户金额
     */
    SysMoney selectByUserId(Integer userId);

    /*
     *账户--》 修改当前用户的金额
     */
    boolean updateMoney(SysMoney sysMoney);

    /*
     *账户--》新增用户金额
     */
    boolean insertMoney(SysMoney money);

    /*
     *新增资产明细数据-->资产明细
     */
    boolean insertAccountStatement(SysAccountStatement accountStatement);

    /**
     * 根据用户id查询当前账户信息
     */
    List<StatementVo> selectStatement(Integer userId);

    /*
    *分页查询数据
    */
    LayuiTableData<SysAccountStatement> selectByType(int limit, int page, Integer moneyId, Integer consumerType, Integer limitDays);
}
