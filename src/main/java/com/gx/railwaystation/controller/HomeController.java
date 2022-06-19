package com.gx.railwaystation.controller;

import com.gx.railwaystation.po.*;
import com.gx.railwaystation.service.*;
import com.gx.railwaystation.util.ProjectParameter;
import com.gx.railwaystation.util.Tools;
import com.gx.railwaystation.vo.*;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/home")
public class HomeController {
    /*
     *图片路径
     */
    private static final String UPLOAD_PATH="F:/picture/";

    /*车站*/
    private SysSiteService service;
    private SysTrainService sysTrainService;
    private SysReserveService sysReserveService;
    private SysSaddieService sysSaddieService;
    private SysUserService userService;

    @Autowired
    public HomeController(SysSiteService service, SysTrainService sysTrainService, SysReserveService sysReserveService, SysSaddieService sysSaddieService, SysUserService userService) {
        this.service = service;
        this.sysTrainService = sysTrainService;
        this.sysReserveService = sysReserveService;
        this.sysSaddieService = sysSaddieService;
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
     * 查询信息页面
     */
    @RequestMapping("/userQuery")
    public String userQuery(){
        return "user/QueryTrains";
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
     * 车站树形
     */
    @RequestMapping("/selectDepartmentForTree")
    @ResponseBody
    public List<LayuiTreeVo> selectDepartmentForTree(){
        return this.service.selectForLayuiTree();
    }



    /**
     * 列车下拉框
     */
    @RequestMapping("/trainTypeId")
    @ResponseBody
    public Object trainTypeId(){
        return this.sysTrainService.trainTypeId();
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

    /**
     *  分页查询数据
     */
    @RequestMapping(value = "/selectPageList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public LayuiTableData<trainVo> selectPageList(int page, int limit, Integer siteId, String trainNumber, String trainOutTime, Integer trainTypeId){
        //开始时间和结束时间   2021-04-22 - 2021-05-22
        String startDate = null;
        String endDate = null;
        if (Tools.isNotNull(trainOutTime)) {
            String[] strDates = trainOutTime.split(" - ");
            if (strDates.length == 2) {
                if (strDates[0].matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    startDate = strDates[0];
                }
                if (strDates[1].matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    endDate = strDates[1];
                }
            }
        }
        return this.sysTrainService.selectPageList(page,limit,siteId,trainNumber,startDate,endDate,
                trainTypeId);
    }

    /**
     * 查询回填数据
     */
    @RequestMapping("/queryTrain")
    @ResponseBody
    public JsonMsg queryTrain(Integer trainId){
        JsonMsg jsonMsg = new JsonMsg();
        //id不能为空
        if (trainId!=null){
            try{
                trainVo data = this.sysTrainService.queryTrain(trainId);
                jsonMsg.setState(true);
                jsonMsg.setData(data);
            }catch (RuntimeException e){
                System.out.println("异常数据:"+e.getMessage());
            }
        }else{
            jsonMsg.setMsg("数据异常");
        }

        return jsonMsg;
    }

    /**
     * 获取编号、列车编号、用户姓名
     */
    @RequestMapping("/ReserveTrainId")
    @ResponseBody
    public JsonMsg ReserveTrainId(HttpSession session,Integer trainId){
        JsonMsg jsonMsg = new JsonMsg();
        //取出session中的user数据
        SysUser vo = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        //判断ID不能为空
        if (trainId!=null){
            //获取三位数的随机数  自动生成编号
            Random random = new Random();
            int threeNum = random.nextInt(900) + 100;
            //拼接编号
            String autoCode ="DB"+threeNum;//DB123
            //获取当前列车编号
            trainVo vo1 = this.sysTrainService.queryTrain(trainId);
            //获取当前用户ID和姓名
            reserveVo data = new reserveVo();
            data.setUserId(vo.getUserId());
            data.setUserName(vo.getUserName());
            data.setTrainId(vo1.getTrainId());
            data.setTrainNumber(vo1.getTrainNumber());
            data.setReserveFares(vo1.getReserveFares());
            //返回数据
            jsonMsg.setMsg(autoCode);
            jsonMsg.setState(true);
            jsonMsg.setData(data);
        }else{
            jsonMsg.setMsg("数据异常");
        }

        return jsonMsg;
    }

    /**
     * 座位类型下拉框
     */
    @RequestMapping("/SysSaddieId")
    @ResponseBody
    public Object SysSaddieId(){
        return this.sysReserveService.SysSaddieId();
    }

    /**
     * 支付方式下拉框
     */
    @RequestMapping("/SysReserveTypeId")
    @ResponseBody
    public Object SysReserveTypeId(){
        return this.sysReserveService.SysReserveTypeId();
    }

    /**
     * 座位下拉框监听事件
     */
    @RequestMapping("/SelectFilter")
    @ResponseBody
    public JsonMsg SelectFilter(Integer saddieId,Integer trainId){
        JsonMsg jsonMsg = new JsonMsg();

        //查询座位类型的金额
        SysSaddie sysSaddie = this.sysSaddieService.SelectFilter(saddieId);
        //查询列车票价
        SysTrain dd = this.sysTrainService.queryTrain(trainId);
        BigDecimal set= sysSaddie.getSaddieMoney();//座位金额
        BigDecimal dat = dd.getReserveFares();//票价
        BigDecimal dataInt = dat.add(set);

        jsonMsg.setState(true);
        jsonMsg.setData(dataInt);

        return jsonMsg;
    }

    /**
     * 新增订票数据
     */
    @RequestMapping(value = "/inset",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg inset(SysReserve reserve){
        JsonMsg jsonMsg = new JsonMsg();
        //判断页面传递数据
        if (reserve.getSaddieId()!=null){
            if (reserve.getReserveTypeId()!=null){
                jsonMsg = this.sysReserveService.inset(reserve);
                if (jsonMsg==null){
                    throw new RuntimeException("新增失败");
                }
            }else{
                jsonMsg.setMsg("请选择支付类型");
            }
        }else{
            jsonMsg.setMsg("请选择座位类型");
        }
        return jsonMsg;
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
