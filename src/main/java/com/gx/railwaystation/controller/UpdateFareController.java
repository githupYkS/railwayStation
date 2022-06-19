package com.gx.railwaystation.controller;

import com.gx.railwaystation.service.SysUpdateFareService;
import com.gx.railwaystation.util.Tools;
import com.gx.railwaystation.vo.FareVo;
import com.gx.railwaystation.vo.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/amend")
public class UpdateFareController {

    @Autowired
    private SysUpdateFareService sysUpdateFareService;

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
    @RequestMapping("/selectPageList")
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
}
