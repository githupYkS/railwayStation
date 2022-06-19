package com.gx.railwaystation.controller;

import com.gx.railwaystation.po.SysStaff;
import com.gx.railwaystation.po.SysUser;
import com.gx.railwaystation.service.SysUserService;
import com.gx.railwaystation.util.ProjectParameter;
import com.gx.railwaystation.util.Tools;
import com.gx.railwaystation.vo.MoneyVo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    /*
    *图片路径
    */
    private static final String UPLOAD_PATH="F:/picture/";

    private SysUserService userService;

    @Autowired
    public HomeController(SysUserService userService) {
        this.userService = userService;
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/loginOut",produces = "application/json;charset=utf-8")
    @ResponseBody
    public boolean loginOut(HttpSession session){
        session.removeAttribute(ProjectParameter.SESSION_USER);
        session.removeAttribute(ProjectParameter.SESSION_staff);
        return true;
    }
    /**
     * 售票员页面
     */
    @RequestMapping("/staff")
    public String staff(Model model, HttpSession session){
        //查询当前登录账号的姓名
        SysStaff staff = (SysStaff) session.getAttribute(ProjectParameter.SESSION_staff);
        model.addAttribute("msg",staff.getStaffName());
        return "conductor/staff";
    }

    /**
     * 用户页面
     */
    @RequestMapping("/user")
    public String user(Model model,HttpSession session){
        //查询当前登录账号的姓名
        SysUser user = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        model.addAttribute("msg",user.getUserName());
        return "user/user";
    }

    /*
    *个人中心
    */
    @RequestMapping("/personalHome")
    public String personalHome(Model model,HttpSession session){
        //查询当前的用户登录账号的所有的信息
        SysUser user = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        model.addAttribute("user",user);
        model.addAttribute("msg",user.getUserName());
        return "user/personal/personalHome";
    }

    /*
    * 用户-》个人中心
    */
    @RequestMapping("/personalCenter")
    public String personalCenter(HttpSession session,Model model){
        SysUser user = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        MoneyVo moneyVo = this.userService.selectAllByUserId(Integer.valueOf(user.getUserId()));
        model.addAttribute("user",user);
        model.addAttribute("moneyVo",moneyVo);
        return "user/personal/personal_center";
    }

    /*
     *用户-》账户管理
     */
    @RequestMapping("/accountBalance")
    public String accountBalance(HttpSession session,Model model){
        SysUser user = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        MoneyVo moneyVo = this.userService.selectAllByUserId(Integer.valueOf(user.getUserId()));
        model.addAttribute("moneyVo",moneyVo);
        return "user/personal/account_balance";
    }

    /*
    * 用户-》个人资料
    */
    @RequestMapping("/personalProfile")
    public String personalProfile(HttpSession session,Model model){
        SysUser user = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        model.addAttribute("user",user);
        return "user/personal/personal_profile";
    }


    /**
     * 根据图片名返回图片流
     */
    @RequestMapping("/getPortraitImage/{userHead}")
    public void getPortraitImage(@PathVariable("userHead")String userHead,HttpServletResponse response) throws IOException {
        // 获取参数
        if (Tools.isNotNull(userHead)) {
            //图片名不未空
            String imgPath = UPLOAD_PATH + userHead;
            File fileImg = new File(imgPath);
            if (fileImg.exists()) {
                //指定返的类型
                response.setContentType(Tools.getImageContentType(userHead));

                InputStream in = null;
                OutputStream out = null;
                try {
                    in = new FileInputStream(fileImg);
                    out = response.getOutputStream();
                    //复制
                    // byte[] buff=new byte[1024*1024*10];//10M
                    // int count=0;
                    // while ((count=in.read(buff,0,buff.length))!=-1){
                    //     out.write(buff,0,count);
                    // }
                    //commons-io
                    IOUtils.copy(in, out);
                    out.flush();
                } finally {
                    if (in != null) in.close();
                    if (out != null) out.close();
                }

            }
        }
    }
}
