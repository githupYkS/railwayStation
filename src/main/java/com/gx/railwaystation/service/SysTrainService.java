package com.gx.railwaystation.service;

import com.gx.railwaystation.po.SysTrain;
import com.gx.railwaystation.vo.H5SelectVo;
import com.gx.railwaystation.vo.LayuiTableData;
import com.gx.railwaystation.vo.trainVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SysTrainService {

    /**
     * 列车状态下拉框
     */
    List<H5SelectVo> trainTypeId();

    /**
     * layui table 渲染数据
     */
    LayuiTableData<trainVo> selectPageList(Integer page, Integer limit,Integer siteId, String trainNumber,String startDate,String endDate,Integer trainTypeId);

    /**
     * 查询回填数据
     */
    trainVo queryTrain(Integer trainId);

    /*
     *查询票价
     */
    LayuiTableData<trainVo> selectByreservFares(int page,int limit,Integer minMoney,Integer maxMoney);

}
