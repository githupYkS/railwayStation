package com.gx.railwaystation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gx.railwaystation.po.SysAccountStatement;
import com.gx.railwaystation.po.SysMoney;
import com.gx.railwaystation.po.SysUser;
import com.gx.railwaystation.service.SysAccountStatementService;
import com.gx.railwaystation.service.SysTrainService;
import com.gx.railwaystation.service.SysUserService;
import com.gx.railwaystation.util.MD5Util;
import com.gx.railwaystation.util.ProjectParameter;
import com.gx.railwaystation.util.Tools;
import com.gx.railwaystation.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.tools.Tool;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/profile")
public class PersonalProfileController {

    private static final String UPLOAD_PATH="F:/picture/";

    private SysUserService userService;

    private SysAccountStatementService sysAccountStatementService;

    private SysTrainService sysTrainService;

    @Autowired
    public PersonalProfileController(SysUserService userService, SysAccountStatementService sysAccountStatementService, SysTrainService sysTrainService) {
        this.userService = userService;
        this.sysAccountStatementService = sysAccountStatementService;
        this.sysTrainService = sysTrainService;
    }

    /*
    *查询当前登录的信息并且回填数据
    */
    @RequestMapping(value = "/selectUserById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg selectUserById(HttpSession session){
        JsonMsg jsonMsg = new JsonMsg();
        SysUser user1 = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        if (user1!=null){
            SysUser user = this.userService.selectUserById(Integer.valueOf(user1.getUserId()));
            jsonMsg.setData(user);
            jsonMsg.setState(true);
        }else {
            jsonMsg.setMsg("登录的用户数据为空");
        }
        return jsonMsg;
    }

    /*
    * 修改用户信息
    */
    @RequestMapping(value = "/update",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg update(SysUser saveUser, MultipartFile portraitFile) throws IOException {
        JsonMsg jsonMsg = new JsonMsg();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");

        //判断文件存放目录是否存在
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        if (saveUser.getUserId()==null || saveUser.getUserId()<=0){
            jsonMsg.setMsg("参数异常！");
            return jsonMsg;
        }

        //处理上传 文件
        if (portraitFile!=null && portraitFile.getBytes().length>0){
            //拼接文件名  item.getName()--》文件名
            String fileName = dateFormat.format(new Date()) + System.nanoTime() + Tools.getFileExt(portraitFile.getOriginalFilename());
            //存放路径
            String filePath = UPLOAD_PATH + fileName;
            File saveFile = new File(filePath);
            System.err.println(filePath);
            //保存文件到硬盘
            portraitFile.transferTo(saveFile);
            //把文件名保存到需要新增的对象中
            saveUser.setUserHead(fileName);
        }


        if (!Tools.isNotNull(saveUser.getUserName())){
            jsonMsg.setMsg("请输入姓名！");
            return jsonMsg;
        }
        if (saveUser.getUserAccount()==null || saveUser.getUserAccount()==""){
            jsonMsg.setMsg("请输入用户名！");
            return jsonMsg;
        }
        if (saveUser.getUserSex()==null || saveUser.getUserSex()<=0){
            jsonMsg.setMsg("请选择性别");
            return jsonMsg;
        }
        if (saveUser.getUserIdentification()==null || saveUser.getUserIdentification()==""){
            jsonMsg.setMsg("请输入身份证号");
            return jsonMsg;
        }
        if (saveUser.getUserPhone()==null || saveUser.getUserPhone()==""){
            jsonMsg.setMsg("请输入手机号码！");
            return jsonMsg;
        }
        //是否有就图片
        String oldPortraitImageName=null;
        //是否有新的图片上传，有查询就旧的图片名称
        if (Tools.isNotNull(saveUser.getUserHead())){
            SysUser oldUser=this.userService.selectUserById(saveUser.getUserId());
            oldPortraitImageName=oldUser.getUserHead();
        }

        try {
            boolean isOk = this.userService.updateUser(saveUser);
            if (isOk) {
                //旧图片存在时删除旧图片
                if (Tools.isNotNull(oldPortraitImageName)){
                    File oldImage=new File(UPLOAD_PATH,oldPortraitImageName);
                    if (oldImage.exists()){
                        oldImage.delete();
                    }
                }
                jsonMsg.setState(true);
                jsonMsg.setMsg("修改成功");
            } else {
                jsonMsg.setMsg("修改失败");
            }
        }catch (RuntimeException e){
            jsonMsg.setMsg("修改失败");
        }
        return jsonMsg;
    }

    /*
    * 查询用户密码
    */
    @RequestMapping(value = "/selectPassword",produces = "application/json;charset")
    @ResponseBody
    public JsonMsg selectPassword(HttpSession session,String oldPassword){
        JsonMsg jsonMsg = new JsonMsg();
        SysUser user = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        if (user!=null){
            String md5Pass = MD5Util.getMD5(oldPassword+user.getUserSalt());
            jsonMsg.setState(true);
            if (user.getUserPassword().equals(md5Pass)){
                jsonMsg.setState(true);
                jsonMsg.setCode(0);
            }else {
                jsonMsg.setState(true);
                jsonMsg.setCode(1);
            }
        }else {
            jsonMsg.setMsg("登录的用户数据为空");
        }
        return jsonMsg;
    }

    /*
    * 修改密码
    */
    @RequestMapping(value = "/updatePassword",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg updatePassword(HttpSession session,String newPassword){
        JsonMsg jsonMsg = new JsonMsg();
        SysUser user = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        if (user!=null){
            //查询未修改的数据
            SysUser sysUser = this.userService.selectUserById(Integer.valueOf(user.getUserId()));
            sysUser.setUserId(Integer.valueOf(user.getUserId()));
            Random random = new Random();

            //生成一个随机的8位数作为盐   10000000 ~ 99999999
            String salt = String.valueOf(random.nextInt(90000000) + 10000000);
            //对输入的密码+盐 取MD5值
            String userPassword = MD5Util.getMD5(newPassword + salt);
            sysUser.setUserPassword(userPassword);
            sysUser.setUserSalt(salt);
            /*调用修改的方法*/
            try{
                boolean isOK = this.userService.updateUser(sysUser);
                if (isOK){
                    jsonMsg.setState(true);
                    jsonMsg.setMsg("修改成功");
                }else {
                    jsonMsg.setMsg("修改失败");
                }
            }catch (RuntimeException e){
                jsonMsg.setMsg("修改失败");
            }
        }else {
            jsonMsg.setMsg("保存的数据为空");
        }
        return jsonMsg;
    }

    /*
    *注销用户
    */
    @RequestMapping(value = "/deleteByUserId",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg deleteByUserId(HttpSession session){
        JsonMsg jsonMsg = new JsonMsg();
        SysUser user = (SysUser) session.getAttribute(ProjectParameter.SESSION_USER);
        if (user!=null){
            try {
                boolean isOK = this.userService.deleteByUserId(Integer.valueOf(user.getUserId()));
                if (isOK){
                    jsonMsg.setState(true);
                    jsonMsg.setMsg("数据注销成功");
                }else {
                    jsonMsg.setMsg("数据注销失败");
                }
            }catch (RuntimeException e){
                jsonMsg.setMsg("删除数据失败");
            }
        }else {
            jsonMsg.setMsg("当前登录的数据为空");
        }
        return jsonMsg;
    }


    /*--------------------------------------------------------账户管理-----------------------------------------------*/

    /**
     *新增数据
     */
    @RequestMapping(value = "/insert",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg Insert(String fillId,SysMoney money, SysAccountStatement accountStatement,HttpSession session){
        JsonMsg jsonMsg = new JsonMsg();
        //获取保存的用户数据
        SysUser sysUser = (SysUser)session.getAttribute(ProjectParameter.SESSION_USER);
        //查询当前的账户金额，并且判断有没有数据
        SysMoney sysMoney = this.sysAccountStatementService.selectByUserId(Integer.valueOf(sysUser.getUserId()));
        //充值
        if (accountStatement.getConsumerType()==1){
            if (sysMoney==null){
                //新增数据，判断数据不为空
                SysMoney sysMoney1 = new SysMoney();
                SysAccountStatement sysAccountStatement = new SysAccountStatement();
                if (money.getUserId()>=0){
                    sysMoney1.setUserId(money.getUserId());
                    if (Tools.isNotNull(fillId)){
                        sysMoney1.setMoneySun(BigDecimal.valueOf(Integer.parseInt(fillId)));
                        sysAccountStatement.setAmmountChange(BigDecimal.valueOf(Integer.parseInt(fillId)));
                        boolean isOK = this.sysAccountStatementService.insertMoney(sysMoney1)==true;
                        SysMoney money1 = this.sysAccountStatementService.selectByUserId(Integer.valueOf(sysUser.getUserId()));
                        jsonMsg.setData(money1.getMoneyId());
                        if (money1.getMoneyId()!=null){
                            sysAccountStatement.setMoneyId(money1.getMoneyId());
                            if (accountStatement.getConsumerType()>=0){
                                sysAccountStatement.setConsumerType(accountStatement.getConsumerType());
                                try {
                                        boolean isOK1 = this.sysAccountStatementService.insertAccountStatement(sysAccountStatement);
                                        if (isOK1){
                                            jsonMsg.setState(true);
                                            jsonMsg.setMsg("充值成功");
                                        }else {
                                            jsonMsg.setMsg("充值失败");
                                        }
                                }catch (RuntimeException e){
                                    jsonMsg.setMsg("充值失败");
                                }
                            }else{
                                jsonMsg.setMsg("资产详细类型为空");
                            }
                        }else {
                            jsonMsg.setMsg("新增的金额id为空");
                        }
                    }else {
                        jsonMsg.setMsg("请选择需要充值的金额");
                    }
                }else {
                    jsonMsg.setMsg("登录的用户数据为空");
                }
            }else{
                //修改金额，判断数据不为空
                SysMoney sysMoney1 = new SysMoney();
                SysAccountStatement sysAccountStatement = new SysAccountStatement();
                //判断用户是否为空
                if (money.getUserId()>0){
                    //添加用户余额
                    sysMoney1.setUserId(money.getUserId());
                    if (Tools.isNotNull(fillId)){
                        //现在的金币加上需要充值的金币
                        BigDecimal moneySun = sysMoney.getMoneySun();
                        BigDecimal moneySun1 = BigDecimal.valueOf(Integer.parseInt(fillId));
                        BigDecimal moneySun2 = moneySun.add(moneySun1);
                        sysMoney1.setMoneySun(moneySun2);
                        //添加资产明细表的金额
                        sysAccountStatement.setAmmountChange(BigDecimal.valueOf(Integer.parseInt(fillId)));
                        if (money.getMoneyId()!=null){
                            sysMoney1.setMoneyId(sysMoney.getMoneyId());
                            sysAccountStatement.setMoneyId(money.getMoneyId());
                            if (accountStatement.getConsumerType()>=0){
                                sysAccountStatement.setConsumerType(accountStatement.getConsumerType());
                                try {
                                    boolean isOK = this.sysAccountStatementService.updateMoney(sysMoney1);
                                    if (isOK){
                                        boolean isOK1 = this.sysAccountStatementService.insertAccountStatement(sysAccountStatement);
                                        if (isOK1){
                                            jsonMsg.setState(true);
                                            jsonMsg.setMsg("充值成功");
                                        }else {
                                            jsonMsg.setMsg("充值失败");
                                        }
                                    }
                                }catch (RuntimeException e){
                                    jsonMsg.setMsg("充值失败");
                                }
                            }else{
                                jsonMsg.setMsg("资产详细类型为空");
                            }
                        }else {
                            jsonMsg.setMsg("新增的金额id为空");
                        }
                    }else {
                        jsonMsg.setMsg("请选择需要充值的金额");
                    }
                }else {
                    jsonMsg.setMsg("登录的用户数据为空");
                }
            }
        }else {
            //提现
            if (sysMoney!=null){
                int intR = sysMoney.getMoneySun().compareTo(BigDecimal.valueOf(Integer.parseInt(fillId)));
                if (intR>-1){
                    //修改金额，判断数据不为空
                    SysMoney sysMoney1 = new SysMoney();
                    SysAccountStatement sysAccountStatement = new SysAccountStatement();
                    //判断用户是否为空
                    if (money.getUserId()>0){
                        //添加用户余额
                        sysMoney1.setUserId(money.getUserId());
                        if (Tools.isNotNull(fillId)){
                            //现在的金币加上需要充值的金币
                            BigDecimal moneySun = sysMoney.getMoneySun();
                            BigDecimal moneySun1 = BigDecimal.valueOf(Integer.parseInt(fillId));
                            BigDecimal moneySun2 = moneySun.subtract(moneySun1);
                            sysMoney1.setMoneySun(moneySun2);
                            //添加资产明细表的金额
                            sysAccountStatement.setAmmountChange(BigDecimal.valueOf(Integer.parseInt(fillId)));
                            if (money.getMoneyId()!=null){
                                sysMoney1.setMoneyId(money.getMoneyId());
                                sysAccountStatement.setMoneyId(money.getMoneyId());
                                if (accountStatement.getConsumerType()>=0){
                                    sysAccountStatement.setConsumerType(accountStatement.getConsumerType());
                                    try {
                                        boolean isOK = this.sysAccountStatementService.updateMoney(sysMoney1);
                                        if (isOK){
                                            boolean isOK1 = this.sysAccountStatementService.insertAccountStatement(sysAccountStatement);
                                            if (isOK1){
                                                jsonMsg.setState(true);
                                                jsonMsg.setMsg("提现成功");
                                            }else {
                                                jsonMsg.setMsg("提现失败");
                                            }
                                        }
                                    }catch (RuntimeException e){
                                        jsonMsg.setMsg("提现失败");
                                    }
                                }else{
                                    jsonMsg.setMsg("资产详细类型为空");
                                }
                            }else {
                                jsonMsg.setMsg("新增的金额id为空");
                            }
                        }else {
                            jsonMsg.setMsg("请选择需要充值的金额");
                        }
                    }else {
                        jsonMsg.setMsg("用户为空");
                    }
                }else {
                    jsonMsg.setMsg("金额不足，无法提现");
                }
            }else {
                jsonMsg.setMsg("此时的修改数据");
            }
        }
        return jsonMsg;
    }

    /*
    *更新页面数据
    */
    @RequestMapping(value = "/selectByUserId",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonMsg select(String userId){
        JsonMsg jsonMsg = new JsonMsg();
        //查询当前的页面数据
        SysMoney sysMoney = this.sysAccountStatementService.selectByUserId(Integer.valueOf(userId));
        if (sysMoney!=null){
            //返回数据
            jsonMsg.setState(true);
            jsonMsg.setData(sysMoney);
        }else {
            jsonMsg.setMsg("查询当前的用户数据为空");
        }
        return jsonMsg;
    };

    /*
    * 分页查询数据
    */
    @RequestMapping(value = "/selectPageList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public LayuiTableData<SysAccountStatement> selectPageList(HttpSession session, int limit, int page, Integer moneyId, Integer consumerType, Integer limitDays){
        return this.sysAccountStatementService.selectByType(limit, page, moneyId, consumerType, limitDays);
    }


    /*
    *分页查询票价
    */
    @RequestMapping(value = "/selectMoney",produces = "application/json;charset=utf-8")
    @ResponseBody
    public LayuiTableData<MoneyPlace> selectMoney(Integer limit, Integer page, BigDecimal mimMoney, BigDecimal maxMoney){
        return this.sysTrainService.selectByMoney(limit, page, mimMoney, maxMoney);
    }
}
