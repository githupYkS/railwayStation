package com.gx.railwaystation.service;

import com.gx.railwaystation.po.SysTrain;
import com.gx.railwaystation.vo.*;
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
    LayuiTableData<trainVo> selectPageList(Integer page, Integer limit, Integer siteId, String trainNumber, String startDate, String endDate, Integer trainTypeId);

    /**
     * 查询回填数据
     */
    trainVo queryTrain(Integer trainId);

    /*
     *票价查询
     */
    LayuiTableData<MoneyPlace> selectByMoney(Integer limit, Integer page, BigDecimal minMoney, BigDecimal maxMoney);


    /*
    *form弹出层表格
    */
    LayuiTableData<trainVo> selectPageMosize(Integer limit,Integer page,Integer berthId);
}
