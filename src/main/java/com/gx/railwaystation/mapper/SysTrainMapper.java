package com.gx.railwaystation.mapper;

import com.gx.railwaystation.po.SysTrain;
import com.gx.railwaystation.po.SysTrainType;
import com.gx.railwaystation.vo.trainVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface SysTrainMapper {

    /**
     * 列车状态下拉框
     */
    List<SysTrainType> trainTypeId();

    /**
     *  分页查询数据
     */
    List<trainVo> selectPageList(@Param("page") int page,
                                 @Param("limit") int limit,
                                 @Param("siteId") Integer siteId,
                                 @Param("trainNumber") String trainNumber,
                                 @Param("startDate") String startDate,
                                 @Param("endDate") String endDate,
                                 @Param("trainTypeId") Integer trainTypeId);

    /**
     * 查询分页总条数
     */
    int countAll(@Param("siteId") Integer siteId,
                 @Param("trainNumber") String trainNumber,
                 @Param("startDate") String startDate,
                 @Param("endDate") String endDate,
                 @Param("trainTypeId") Integer trainTypeId);

    /**
     * 查询回填数据
     */
    trainVo queryTrain(Integer trainId);

    /**
     * 修改座位数
     */
    boolean update(Integer trainId,Integer trainSeat);

    /**
     * 根据列车ID查询数据
     */
    SysTrain SelectAll(Integer trainId);

    /*
    *查询票价
    */
    List<trainVo> selectByreservFares(
            @Param("page") int page,
            @Param("limit") int limit,
            @Param("minMoney") Integer minMoney,
            @Param("maxMoney") Integer maxMoney);

    /*分页查询总数*/
    Integer countAllMon(
            @Param("minMoney") Integer minMoney,
            @Param("maxMoney") Integer maxMoney);

}