package com.gx.railwaystation.controller;

import com.gx.railwaystation.po.SysStaff;
import com.gx.railwaystation.service.SysStaffService;
import com.gx.railwaystation.util.MD5Util;
import com.gx.railwaystation.util.ProjectParameter;
import com.gx.railwaystation.util.Tools;
import com.gx.railwaystation.vo.JsonMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Random;

@RestController
@RequestMapping("/conductor")
@Slf4j
public class conductorController {

    //保存的图片路径
    private static final String UPLOAD_PATH="F:/picture/";

    private SysStaffService sysStaffService;

    @Autowired
    public conductorController(SysStaffService sysStaffService) {
        this.sysStaffService = sysStaffService;
    }

    /*---------------------------------------------------售票主页面----------------------------------------------------------*/

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

    /*--------------------------------------------------------修改个人资料------------------------------------------------------------*/

    /**
     * 修改售票员信息
     * @param session
     * @param sysStaff
     * @param portraitFile
     * @return
     */
    @RequestMapping(value = "/updateStaff",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg updateStaff(HttpSession session, SysStaff sysStaff, MultipartFile portraitFile){
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
        return null;
    }
}
