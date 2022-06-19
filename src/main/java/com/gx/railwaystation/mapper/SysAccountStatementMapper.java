package com.gx.railwaystation.mapper;

import com.gx.railwaystation.po.SysAccountStatement;
import com.gx.railwaystation.vo.StatementVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAccountStatementMapper {

    /*
    *新增资产明细数据
    */
    Integer insertAccountStatement(SysAccountStatement accountStatement);

    /**
     * 根据用户id查询当前账户信息
     */
    List<StatementVo> selectStatement(Integer userId);

    /**
     * 查询当前的点击事件，分类
     */
    List<SysAccountStatement> selectByType(Integer moneyId,Integer consumerType,Integer limitDays);
}
