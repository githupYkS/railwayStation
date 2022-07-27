package com.gx.railwaystation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gx.railwaystation.vo.FareVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUpdateFareMapper extends BaseMapper<FareVo> {

    /**
     * 查询分页数据
     */
    List<FareVo> selectPageList(@Param("page") Integer page,
                                @Param("limit") Integer limit,
                                @Param("reserveNumber") String reserveNumber,
                                @Param("startDate") String startDate,
                                @Param("endDate") String endDate);

    /**
     * 查询分页总条数
     */
    int countAll(@Param("reserveNumber") String reserveNumber,
                 @Param("startDate") String startDate,
                 @Param("endDate") String endDate);
}
