package com.gx.railwaystation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gx.railwaystation.po.SysAccountStatement;
import com.gx.railwaystation.vo.StatementVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAccountStatementMapper extends BaseMapper<SysAccountStatement> {

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
    List<SysAccountStatement> selectByType(
            @Param("limit") int limit,
            @Param("page") int page,
            @Param("moneyId") Integer moneyId,
            @Param("consumerType") Integer consumerType,
            @Param("limitDays") Integer limitDays
    );

    /*分页查询总数*/
    Integer countAll(
            @Param("moneyId") Integer moneyId,
            @Param("consumerType") Integer consumerType,
            @Param("limitDays") Integer limitDays
    );
}
