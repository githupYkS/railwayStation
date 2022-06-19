package com.gx.railwaystation.service.impl;

import com.gx.railwaystation.mapper.*;
import com.gx.railwaystation.po.*;
import com.gx.railwaystation.service.SysReserveService;
import com.gx.railwaystation.vo.H5SelectVo;
import com.gx.railwaystation.vo.JsonMsg;
import com.gx.railwaystation.vo.reserveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysReserveServiceImpl implements SysReserveService {
    @Autowired
    private SysReserveMapper sysReserveMapper;
    @Autowired
    private SysTrainMapper sysTrainMapper;
    @Autowired
    private SysSaddieMapper sysSaddieMapper;
    @Autowired
    private SysMoneyMapper sysMoneyMapper;
    @Autowired
    private SysStaffMapper sysStaffMapper;

    /**
     * 座位类型下拉框
     */
    @Override
    public List<H5SelectVo> SysSaddieId() {
        //接收查询的数据
        List<reserveVo> list = this.sysReserveMapper.SysSaddieId();
        List<H5SelectVo> h5SelectVos = new ArrayList<>();
        for (reserveVo reserve : list){
            h5SelectVos.add(new H5SelectVo(String.valueOf(reserve.getSaddieId()),reserve.getSaddieName()));
        }
        return h5SelectVos;
    }

    /**
     * 支付类型下拉框
     */
    @Override
    public List<H5SelectVo> SysReserveTypeId() {
        //接收查询的数据
        List<reserveVo> list = this.sysReserveMapper.SysReserveTypeId();
        List<H5SelectVo> h5SelectVos = new ArrayList<>();
        for (reserveVo reserve : list){
            h5SelectVos.add(new H5SelectVo(String.valueOf(reserve.getReserveTypeId()),reserve.getReserveTypeName()));
        }
        return h5SelectVos;
    }

    /**
     * 新增数据
     */
    @Override
    public JsonMsg inset(SysReserve reserve) {
        JsonMsg jsonMsg = new JsonMsg();
        try {
            //获取列车票数 -减去一张车票
            SysTrain train = this.sysTrainMapper.SelectAll(reserve.getTrainId());
            //判断车票数不能少于0
            if (train.getTrainSeat()>0){
                Integer trainSeat = train.getTrainSeat() - 1;
                boolean SeatIsok = this.sysTrainMapper.update(reserve.getTrainId(),trainSeat);
                if (SeatIsok) {
                    //获取座位数 - 减去一个座位数
                    SysSaddie saddie = this.sysSaddieMapper.SelectFilter(reserve.getSaddieId());
                    if (saddie.getSaddieCount()>0){
                        Integer saddieCount = saddie.getSaddieCount()-1;
                        boolean saddieIsok = this.sysSaddieMapper.update(reserve.getSaddieId(),saddieCount);
                        if (saddieIsok){
                            //查询出个人余额---余额减去票价
                            SysMoney sysMoney = this.sysMoneyMapper.selectByUserId(reserve.getUserId());
                            //判断余额不能小于票价
                            int intR = sysMoney.getMoneySun().compareTo(reserve.getReserveFares());
                            if (intR>-1){
                                //当前用户余额等于余额-票价
                                BigDecimal moneySun = sysMoney.getMoneySun().subtract(reserve.getReserveFares());
                                //修改用户余额
                                SysMoney sysMoney1 = new SysMoney();
                                sysMoney1.setUserId(reserve.getUserId());
                                sysMoney1.setMoneySun(moneySun);
                                sysMoney1.setMoneyId(sysMoney.getMoneyId());
                                boolean moneyIsOk = this.sysMoneyMapper.updateMoney(sysMoney1)>0;
                                if (moneyIsOk){
                                    //新增订票数据
                                    boolean isok = this.sysReserveMapper.inset(reserve);
                                    if (isok){
                                        //将admin的余额查询出来
                                        Integer staffId = 1;
                                        SysStaff staff = this.sysStaffMapper.SelectStaffId(staffId);
                                        //admin的余额加上票价
                                        BigDecimal staffWallet =  staff.getStaffWallet().add(reserve.getReserveFares());
                                        //修改admin的余额
                                        boolean staffIsok = this.sysStaffMapper.update(staffId,staffWallet);
                                        if (staffIsok){
                                            jsonMsg.setState(true);
                                            jsonMsg.setMsg("订票成功");
                                        }else {
                                            jsonMsg.setMsg("订票失败");
                                        }
                                    }else {
                                        jsonMsg.setMsg("购买失败,请联系工作人员");
                                    }
                                }else{
                                    jsonMsg.setMsg("订票失败");
                                }
                            }else{
                                jsonMsg.setMsg("余额不足,请充值");
                            }
                        }else{
                            jsonMsg.setMsg("订票失败");
                        }
                    }else{
                        jsonMsg.setMsg("车票不足,购买失败");
                    }
                }else {
                    jsonMsg.setMsg("订票失败");
                }
            }else {
                jsonMsg.setMsg("车票不足,购买失败");
            }
        }catch (RuntimeException e){
            System.out.println("新增异常="+e.getMessage());
        }

        return jsonMsg;
    }
}
