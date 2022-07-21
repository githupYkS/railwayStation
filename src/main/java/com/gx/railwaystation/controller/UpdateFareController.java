package com.gx.railwaystation.controller;

import com.gx.railwaystation.mapper.SysTrainMapper;
import com.gx.railwaystation.po.SysBerth;
import com.gx.railwaystation.po.SysSaddie;
import com.gx.railwaystation.po.SysTrain;
import com.gx.railwaystation.service.SysSaddieService;
import com.gx.railwaystation.service.SysSiteService;
import com.gx.railwaystation.service.SysTrainService;
import com.gx.railwaystation.service.SysUpdateFareService;
import com.gx.railwaystation.util.Tools;
import com.gx.railwaystation.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/amend")
public class UpdateFareController {

    @Autowired
    private SysUpdateFareService sysUpdateFareService;

    @Autowired
    private SysSiteService service;

    @Autowired
    private SysTrainService sysTrainService;

    @Autowired
    private SysSaddieService sysSaddieService;

    /**
     * 修改车票信息
     */
    @RequestMapping("/updateFare")
    public String updateFare(){
        return "user/updateFare";
    }

    /**
     * 表格渲染
     */
    @RequestMapping(value = "/selectPageList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public LayuiTableData<FareVo> selectPageList(int page, int limit,String reserveNumber,String reserveTime){
        //开始时间和结束时间   2021-04-22 - 2021-05-22
        String startDate = null;
        String endDate = null;
        if (Tools.isNotNull(reserveTime)) {
            String[] strDates = reserveTime.split(" - ");
            if (strDates.length == 2) {
                if (strDates[0].matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    startDate = strDates[0];
                }
                if (strDates[1].matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    endDate = strDates[1];
                }
            }
        }
        return this.sysUpdateFareService.selectPageList(page, limit, reserveNumber, startDate,endDate);
    }


    /*查询票价下拉框*/
    @RequestMapping(value = "/selectBerthId",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object selectBerthId(){
        return this.service.selectAllBreath();
    }


    /*监听下拉框回填数据*/
    @RequestMapping(value = "SelectSaddieId",produces = "application/json;charset=utf-8")
    @ResponseBody
    private JsonMsg SelectSaddieId(Integer saddieId){
        JsonMsg jsonMsg = new JsonMsg();
        //查询座位的金额
        SysSaddie sysSaddie = this.sysSaddieService.SelectFilter(saddieId);
        jsonMsg.setData(sysSaddie);
        jsonMsg.setState(true);
        return jsonMsg;
    }

    /*
    *分页查询数据
    */
    @RequestMapping(value = "/selectTabMoside",produces = "application/json;charset=utf-8")
    @ResponseBody
    private LayuiTableData<trainVo> selectTabMoside(Integer limit,Integer page,Integer berthId){
        return this.sysTrainService.selectPageMosize(limit,page,berthId);
    }
}
