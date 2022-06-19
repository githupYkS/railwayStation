package com.gx.railwaystation.service.impl;

import com.gx.railwaystation.mapper.SysTrainMapper;
import com.gx.railwaystation.po.SysTrain;
import com.gx.railwaystation.po.SysTrainType;
import com.gx.railwaystation.service.SysTrainService;
import com.gx.railwaystation.vo.H5SelectVo;
import com.gx.railwaystation.vo.LayuiTableData;
import com.gx.railwaystation.vo.trainVo;
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
    public LayuiTableData<trainVo> selectByreservFares(int page, int limit, Integer minMoney, Integer maxMoney) {
        List<trainVo> trainVos = this.sysTrainMapper.selectByreservFares(page, limit, minMoney, maxMoney);
        Integer countA = this.sysTrainMapper.countAllMon(minMoney,maxMoney);
        return new LayuiTableData<>(countA,trainVos);
    }
}
