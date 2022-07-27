package com.gx.railwaystation.service.impl;

import com.gx.railwaystation.mapper.SysTrainMapper;
import com.gx.railwaystation.po.SysTrain;
import com.gx.railwaystation.po.SysTrainType;
import com.gx.railwaystation.service.SysTrainService;
import com.gx.railwaystation.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysTrainServiceImpl implements SysTrainService {
    @Autowired
    private SysTrainMapper sysTrainMapper;

    /**
     * 列车状态下拉框
     */
    @Override
    public List<H5SelectVo> trainTypeId() {
        List<SysTrainType> list = this.sysTrainMapper.trainTypeId();
        List<H5SelectVo> h5SelectVos = new ArrayList<>();
        for (SysTrainType sysTrain : list){
            h5SelectVos.add(new H5SelectVo(String.valueOf(sysTrain.getTrainTypeId()),sysTrain.getTrainTypeName()));
        }
        return h5SelectVos;
    }

    /**
     * layui table 渲染数据
     */
    @Override
    public LayuiTableData<trainVo> selectPageList(Integer page, Integer limit, Integer siteId, String trainNumber, String startDate, String endDate, Integer trainTypeId) {
        //接收查询数据
        List<trainVo> trainVos = this.sysTrainMapper.selectPageList(page,limit,siteId,trainNumber,startDate,endDate,trainTypeId);
        //查询总条数
        int conut = this.sysTrainMapper.countAll(siteId,trainNumber,startDate,endDate,trainTypeId);
        return new LayuiTableData<>(conut,trainVos);
    }

    /**
     * 查询回填数据
     */
    @Override
    public trainVo queryTrain(Integer trainId) {
        return this.sysTrainMapper.queryTrain(trainId);
    }

    @Override
    public LayuiTableData<MoneyPlace> selectByMoney(Integer limit, Integer page, BigDecimal minMoney, BigDecimal maxMoney) {
        List<MoneyPlace> moneyVos = this.sysTrainMapper.selectByMoney(limit, page, minMoney, maxMoney);
        Integer countA = this.sysTrainMapper.countAllMoney(minMoney, maxMoney);
        return new LayuiTableData<>(countA,moneyVos);
    }

    @Override
    public LayuiTableData<trainVo> selectPageMosize(Integer limit, Integer page, Integer berthId) {
        List<trainVo> trainVos = this.sysTrainMapper.selectPageMosize(limit, page, berthId);
        Integer countAllMosize = this.sysTrainMapper.countAllMosize(berthId);
        return new LayuiTableData<>(countAllMosize,trainVos);
    }

    @Override
    public LayuiTableData<trainVo> selectPageListCo(Integer page, Integer limit, String startDate, String endDate, Integer trainTypeId, Integer siteId, Integer trainSeat, Integer reserveFares) {
        List<trainVo> trainVos = this.sysTrainMapper.selectPageListCo(page, limit, startDate, endDate, trainTypeId, siteId, trainSeat, reserveFares);
        Integer countAll = this.sysTrainMapper.countAllCo(startDate,endDate,trainTypeId,siteId,trainSeat,reserveFares);
        return new LayuiTableData<>(countAll,trainVos);
    }

}
