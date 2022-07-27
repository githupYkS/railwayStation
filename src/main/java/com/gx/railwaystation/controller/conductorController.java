package com.gx.railwaystation.controller;

import com.gx.railwaystation.po.SysStaff;
import com.gx.railwaystation.service.SysStaffService;
import com.gx.railwaystation.service.SysTrainService;
import com.gx.railwaystation.service.SysUpdateFareService;
import com.gx.railwaystation.util.MD5Util;
import com.gx.railwaystation.util.ProjectParameter;
import com.gx.railwaystation.util.Tools;
import com.gx.railwaystation.vo.JsonMsg;
import com.gx.railwaystation.vo.LayuiTableData;
import com.gx.railwaystation.vo.trainVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/conductor")
@Slf4j
public class conductorController {

    //保存的图片路径
    private static final String UPLOAD_PATH="F:/picture/";

    private SysStaffService sysStaffService;

    private SysUpdateFareService sysUpdateFareService;

    private SysTrainService sysTrainService;

    @Autowired
    public conductorController(SysStaffService sysStaffService,SysUpdateFareService sysUpdateFareService,SysTrainService sysTrainService) {
        this.sysStaffService = sysStaffService;
        this.sysUpdateFareService = sysUpdateFareService;
        this.sysTrainService = sysTrainService;
    }

    /*---------------------------------------------------售票主页面------------------------------------------------------*/

    /**
     * 修改售票员密码
     * @param session
     * @param oldPassword
     * @return
     */
    @RequestMapping(value = "/selectPassword",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg selectPassword(HttpSession session,String oldPassword){
        JsonMsg jsonMsg = new JsonMsg();
        //获取session中保存的数据
        SysStaff sysStaff = (SysStaff) session.getAttribute(ProjectParameter.SESSION_staff);
        if (sysStaff!=null){
            String md5Pass = MD5Util.getMD5(oldPassword+sysStaff.getStaffSalt());
            if (sysStaff.getStaffPassword().equals(md5Pass)){
                jsonMsg.setState(true);
                jsonMsg.setCode(0);
            }else {
                jsonMsg.setState(true);
                jsonMsg.setCode(1);
            }
        }else {
            jsonMsg.setMsg("请登录后再进行操作");
        }
        return jsonMsg;
    }

    /**
     * 修改当前登录的售票员数据
     * @param oldPassword
     * @param session
     * @return
     */
    @RequestMapping(value = "/updateStaffPassword",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg updateStaffPassword(String oldPassword,HttpSession session){
        JsonMsg jsonMsg = new JsonMsg();
        //从session中获取当前的售票员数据
        SysStaff sysStaff1 = (SysStaff) session.getAttribute(ProjectParameter.SESSION_staff);
        if (sysStaff1!=null){
            if (Tools.isNotNull(oldPassword)){
                //生成盐 -使用Random方法
                Random random = new Random();
                //生成一个随机的8位数  10000000 ~ 99999999
                String salt =String.valueOf(random.nextInt(90000000)+10000000);
                //对输入的密码+盐 取MD5值
                String password =MD5Util.getMD5(oldPassword+salt);
                //将密码和盐值保存到需要修改的数据中
                sysStaff1.setStaffPassword(password);
                sysStaff1.setStaffSalt(salt);

                //调用修改的方法
                try{
                    boolean isOK = this.sysStaffService.updateStaff(sysStaff1);
                    if (isOK){
                        jsonMsg.setState(true);
                        jsonMsg.setMsg("修改成功");
                    }else {
                        jsonMsg.setMsg("修改失败");
                    }
                }catch (RuntimeException e){
                    throw new RuntimeException("修改异常");
                }
            }else {
                jsonMsg.setMsg("请输入需要修改的密码");
            }
        }else {
            jsonMsg.setMsg("请登录后在进行操作");
        }
        return jsonMsg;
    }

    /**
     * 实时查询当前登录的售票员数据
     * @param session
     * @return
     */
    @RequestMapping(value = "/selectStaff",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg selectStaff(HttpSession session){
        JsonMsg jsonMsg = new JsonMsg();
        //从session中获取当前登录的售票员数据
        SysStaff sysStaff = (SysStaff) session.getAttribute(ProjectParameter.SESSION_staff);
        //获取售票员的id
        Integer staffId = sysStaff.getStaffId();
        //实时查询当前的登录的信息
        SysStaff sysStaff1 = this.sysStaffService.selectStaff(staffId);
        if (sysStaff1!=null){
            jsonMsg.setState(true);
            jsonMsg.setData(sysStaff1);
        }else {
            jsonMsg.setMsg("查询出来的数据为空");
        }
        return jsonMsg;
    }

    /*--------------------------------------------------------售票员个人资料------------------------------------------------------------*/

    /**
     * 修改售票员信息
     * @param session
     * @param sysStaff
     * @param portrait
     * @return
     */
    @RequestMapping(value = "/updateStaff",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg updateStaff(HttpSession session, SysStaff sysStaff, MultipartFile portrait) throws IOException {
        JsonMsg jsonMsg = new JsonMsg();

        //时间转换格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");

        //判断需要修改的id是否为空
        if (sysStaff.getStaffId()==null){
            jsonMsg.setMsg("请检查需要修改的数据");
        }
        //获取需要修改的数据
        SysStaff staff = this.sysStaffService.selectStaff(sysStaff.getStaffId());
        if (staff==null){
            jsonMsg.setMsg("当前数据库中没有这条数据");
        }
        //判断文件路径是否存在
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        //判断图片名是否为空，不为空保存到硬盘中
        if (portrait!=null && portrait.getBytes().length>0){
            String fileName = dateFormat.format(new Date())+System.nanoTime()+Tools.getFileExt(portrait.getOriginalFilename());
            //存放路径
            String filePath = UPLOAD_PATH + fileName;
            File saveFile = new File(filePath);
            System.err.println(filePath);
            //保存文件到硬盘
            portrait.transferTo(saveFile);
            //把文件名保存到需要新增的对象中
            sysStaff.setStaffHead(fileName);
        }
        if (!Tools.isNotNull(sysStaff.getStaffAccount())){
            jsonMsg.setMsg("请输入登录的账号");
            return jsonMsg;
        }
        if (!Tools.isNotNull(sysStaff.getStaffName())){
            jsonMsg.setMsg("请输入姓名");
            return jsonMsg;
        }
        if (!Tools.isNotNull(sysStaff.getStaffIdentification())){
            jsonMsg.setMsg("请输入身份证件号码");
            return jsonMsg;
        }
        if (!Tools.isNotNull(sysStaff.getStaffSex().toString())){
            jsonMsg.setMsg("请选择性别");
            return jsonMsg;
        }

        //是否有就图片
        String oldPortraitImageName=null;
        //是否有新的图片上传，有查询就旧的图片名称
        if (Tools.isNotNull(sysStaff.getStaffHead())){
            oldPortraitImageName = staff.getStaffHead();
        }

        try{
            boolean isOk = this.sysStaffService.updateStaff(sysStaff);
            if (isOk){
                //旧图片存在时删除旧图片
                if (Tools.isNotNull(oldPortraitImageName)){
                    File oldImage=new File(UPLOAD_PATH,oldPortraitImageName);
                    if (oldImage.exists()){
                        oldImage.delete();
                    }
                }
                jsonMsg.setState(true);
                jsonMsg.setMsg("修改成功");
            }else {
                jsonMsg.setMsg("修改失败");
            }
        }catch (RuntimeException e){
            jsonMsg.setMsg("修改异常");
        }
        return jsonMsg;
    }


    /**
     * 逻辑注销售票员
     * @return
     */
    @RequestMapping(value = "/deleteByStaff",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg deleteByStaff(HttpSession session){
        JsonMsg jsonMsg = new JsonMsg();

        //从session中获取售票员数据
        SysStaff sysStaff = (SysStaff) session.getAttribute(ProjectParameter.SESSION_staff);
        if (sysStaff == null){
            jsonMsg.setMsg("请登录后再进行操作");
        }
        if (sysStaff.getStaffId()==null && sysStaff.getStaffId() <0){
            jsonMsg.setMsg("您无法注销，请检查");
        }
        try{
            boolean isOk = this.sysStaffService.deleteByStaffId(sysStaff.getStaffId());
            if (isOk){
                jsonMsg.setState(true);
                jsonMsg.setMsg("注销成功");
            }else {
                jsonMsg.setMsg("注销失败");
            }
        }catch (RuntimeException e){
            jsonMsg.setMsg("注销异常");
        }
        return jsonMsg;
    }

    /*----------------------------------------------------售票员查询信息主页面-----------------------------------------------------------*/

    /**
     * 售票员查询界面-》时刻表查询
     * @param page
     * @param limit
     * @param reserveTime
     * @return
     */
    @RequestMapping(value = "/selectPageList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public LayuiTableData<trainVo> selectPageList(int page, int limit, String reserveTime){
        //开始时间和结束时间   2021-04-22 - 2021-05-22
        /*String startDate = null;
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
        }*/
        return null;
    }

    /**
     * 售票员查询界面-》列车状态查询
     */
    @RequestMapping(value = "/selectTypeList",produces = "appliation/json;chatset=utf-8")
    @ResponseBody
    public LayuiTableData<trainVo> selectTypeList(Integer page,Integer limit){
        return null;
    }

    /**
     *售票员查询界面-》票价查询
     * @return
     */
    @RequestMapping(value = "/selectMoney",produces = "application/json;charset=utf-8")
    @ResponseBody
    public LayuiTableData<trainVo> selectMoney(Integer page,Integer limit){
        return null;
    }

    /*
    *售票员查询界面-》票数查询
    */
    @RequestMapping(value = "/selectPiaoshu",produces = "application/json;charset=utf-8")
    @ResponseBody
    public LayuiTableData<trainVo> selectPiaoshu(Integer page,Integer limit){
        return null;
    }

    /**
     * 售票员查询界面-》站点查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/selectSeat",produces = "application/json;charset=utf-8")
    @ResponseBody
    public LayuiTableData<trainVo> selectSeat(Integer page,Integer limit){
        return null;
    }



    /*--------------------------------------------------------退票主界面-------------------------------------------------*/

    /**
     * 售票员退票界面-》查询需要退票的数据
     * @param reserveId
     * @return
     */
    public JsonMsg selectFrave(Integer reserveId){
        JsonMsg jsonMsg = new JsonMsg();
        return jsonMsg;
    }
}
