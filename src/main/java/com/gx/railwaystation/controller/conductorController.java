package com.gx.railwaystation.controller;

import com.gx.railwaystation.po.SysStaff;
import com.gx.railwaystation.util.MD5Util;
import com.gx.railwaystation.util.ProjectParameter;
import com.gx.railwaystation.vo.JsonMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/conductor")
@Slf4j
public class conductorController {

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
}
