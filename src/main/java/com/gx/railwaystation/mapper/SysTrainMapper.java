package com.gx.railwaystation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gx.railwaystation.po.SysTrain;
import com.gx.railwaystation.po.SysTrainType;
import com.gx.railwaystation.vo.MoneyPlace;
import com.gx.railwaystation.vo.MoneyVo;
import com.gx.railwaystation.vo.trainVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface SysTrainMapper extends BaseMapper<SysTrainType> {

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
    *票价查询
    */
    List<MoneyPlace> selectByMoney(
            @Param("limit") Integer limit,
            @Param("page") Integer page,
            @Param("minMoney") BigDecimal minMoney,
            @Param("maxMoney") BigDecimal maxMoney);

    /*
    *查询总条数
    */
    Integer countAllMoney(
            @Param("minMoney") BigDecimal minMoney,
            @Param("maxMoney")BigDecimal maxMoney);

    /*
    *form表单查询数据
    */
    List<trainVo> selectPageMosize(
            @Param("limit") Integer limit,
            @Param("page") Integer page,
            @Param("berthId") Integer berthId);

    /*
    *查询总数
    */
    Integer countAllMosize(Integer berthId);

    /**
     * 售票员分页的各种查询
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param trainTypeId
     * @param siteId
     * @param trainSeat
     * @return
     */
    List<trainVo> selectPageListCo(@Param("page") Integer page,
                                   @Param("limit") Integer limit,
                                   @Param("startDate") String startDate,
                                   @Param("endDate") String endDate,
                                   @Param("trainTypeId") Integer trainTypeId,
                                   @Param("siteId") Integer siteId,
                                   @Param("trainSeat") Integer trainSeat,
                                   @Param("reserveFares") Integer reserveFares);

    /**
     *统计售票员查询出来的各种数据
     * @param startDate
     * @param endDate
     * @param trainTypeId
     * @param siteId
     * @param trainSeat
     * @return
     */
    Integer countAllCo(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("trainTypeId") Integer trainTypeId,
            @Param("siteId") Integer siteId,
            @Param("trainSeat") Integer trainSeat,
            @Param("reserveFares") Integer reserveFares);

}