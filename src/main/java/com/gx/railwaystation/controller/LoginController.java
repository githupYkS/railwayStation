package com.gx.railwaystation.controller;

import com.gx.railwaystation.po.SysStaff;
import com.gx.railwaystation.po.SysUser;
import com.gx.railwaystation.service.SysStaffService;
import com.gx.railwaystation.service.SysUserService;
import com.gx.railwaystation.util.MD5Util;
import com.gx.railwaystation.util.ProjectParameter;
import com.gx.railwaystation.util.Tools;
import com.gx.railwaystation.vo.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.GenerationType;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class LoginController {

    @Autowired
    private SysStaffService sysStaffService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录页面
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 登录方法
     */
    @RequestMapping(value = "/doLogin",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg doLogin(HttpSession session, String userAccount, String userPassword){
        JsonMsg jsonMsg = new JsonMsg();
        //判断页面数据不能为空
        if (Tools.isNotNull(userAccount)){
            if (Tools.isNotNull(userPassword)){
                //查询出员工表的数据
                SysStaff staff = this.sysStaffService.StaffAccount(userAccount);
                //查询出用户表的数据
                SysUser user = this.sysUserService.userAccount(userAccount);
                //判断哪个查询获取到数据
                if (staff!=null){
                    //员工验证密码 md5(用户输入的密码+盐)
                    String md5Pass = MD5Util.getMD5(userPassword+staff.getStaffSalt());
                    if (staff.getStaffPassword().equals(md5Pass)){
                        //登录成功
                        //把staff保存到session中
                        session.setAttribute(ProjectParameter.SESSION_staff,staff);
                        jsonMsg.setState(true);//成功
                        jsonMsg.setCode(1);
                        jsonMsg.setMsg("登录成功");
                    }else{
                        jsonMsg.setMsg("密码错误");
                    }
                }
                if (user!=null){
                    //员工验证密码 md5(用户输入的密码+盐)
                    String usermd5Pass = MD5Util.getMD5(userPassword+user.getUserSalt());
                    if (user.getUserPassword().equals(usermd5Pass)){
                        //登录成功
                        //把user数据保存到session中
                        session.setAttribute(ProjectParameter.SESSION_USER,user);
                        jsonMsg.setState(true);//成功
                        jsonMsg.setCode(2);
                        jsonMsg.setMsg("登录成功");
                    }else{
                        jsonMsg.setMsg("密码错误");
                    }
                }
            }else{
                jsonMsg.setMsg("请输入密码");
            }
        }else{
            jsonMsg.setMsg("请输入账号");
        }

        return jsonMsg;
    }

    /**
     * 注册用户
     */
    @RequestMapping(value = "/register",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg register(SysUser sysUser){
        JsonMsg jsonMsg = new JsonMsg();
        //数据验证
        if (Tools.isNotNull(sysUser.getUserAccount())){
            if (Tools.isNotNull(sysUser.getUserPassword())){
                //生成盐 -使用Random方法
                Random random = new Random();
                //生成一个随机的8位数  10000000 ~ 99999999
                String salt =String.valueOf(random.nextInt(90000000)+10000000);
                //对输入的密码+盐 取MD5值
                String password =MD5Util.getMD5(sysUser.getUserPassword()+salt);
                //把密码和盐保存在sysUser
                sysUser.setUserPassword(password);
                sysUser.setUserSalt(salt);
                try{
                    boolean isko = this.sysUserService.register(sysUser);
                    if (isko){
                        jsonMsg.setState(true);
                        jsonMsg.setMsg("注册成功");
                    }else {
                        jsonMsg.setMsg("注册失败");
                    }
                }catch (RuntimeException e){
                    System.out.println("新增异常："+e.getMessage());
                }
            }else{
                jsonMsg.setMsg("请输入密码");
            }
        }else{
            jsonMsg.setMsg("请输入账号");
        }
        return jsonMsg;
    }

}
