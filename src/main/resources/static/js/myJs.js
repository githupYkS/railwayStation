//时间定时器
function setspanTime() {
    var dt = new Date();
    var month = (dt.getMonth() + 1) < 10 ? "0" + (dt.getMonth() + 1) : dt.getMonth() + 1;
    var day = dt.getDate() < 10 ? "0" + dt.getDate() : dt.getDate();
    var hours = dt.getHours() < 10 ? "0" + dt.getHours() : dt.getHours();
    var minutes = dt.getMinutes() < 10 ? "0" + dt.getMinutes() : dt.getMinutes();
    var seconds = dt.getSeconds() < 10 ? "0" + dt.getSeconds() : dt.getSeconds();
    spanTime.innerText = dt.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}

//获取现在的时间
function time() {
    var dt = new Date();
    var month = (dt.getMonth() + 1) < 10 ? "0" + (dt.getMonth() + 1) : dt.getMonth() + 1;
    var day = dt.getDate() < 10 ? "0" + dt.getDate() : dt.getDate();
    var hours = dt.getHours() < 10 ? "0" + dt.getHours() : dt.getHours();
    var minutes = dt.getMinutes() < 10 ? "0" + dt.getMinutes() : dt.getMinutes();
    var seconds = dt.getSeconds() < 10 ? "0" + dt.getSeconds() : dt.getSeconds();
    return dt.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}

//数据表格时间格式(时间戳 --> 时间) 年-月-日 时:分:秒
function tableTime(timeStamp) {
    var dt = timeStamp;
    var month = (dt.getMonth() + 1) < 10 ? "0" + (dt.getMonth() + 1) : dt.getMonth() + 1;
    var day = dt.getDate() < 10 ? "0" + dt.getDate() : dt.getDate();
    var hours = dt.getHours() < 10 ? "0" + dt.getHours() : dt.getHours();
    var minutes = dt.getMinutes() < 10 ? "0" + dt.getMinutes() : dt.getMinutes();
    var seconds = dt.getSeconds() < 10 ? "0" + dt.getSeconds() : dt.getSeconds();
    return dt.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}

//数据表格时间格式(时间戳 --> 时间) 年-月-日
function tableTimeTo(timeStamp) {
    var dt = timeStamp;
    var month = (dt.getMonth() + 1) < 10 ? "0" + (dt.getMonth() + 1) : dt.getMonth() + 1;
    var day = dt.getDate() < 10 ? "0" + dt.getDate() : dt.getDate();
    var hours = dt.getHours() < 10 ? "0" + dt.getHours() : dt.getHours();
    var minutes = dt.getMinutes() < 10 ? "0" + dt.getMinutes() : dt.getMinutes();
    var seconds = dt.getSeconds() < 10 ? "0" + dt.getSeconds() : dt.getSeconds();
    return dt.getFullYear() + "-" + month + "-" + day;
}

//订单状态
function orderStatus(isUser, status) {
    if (status === 1) {
        return "未处理";
    } else if (status === 2) {
        if (isUser === 1) {
            return "商家已确认";
        } else {
            return "已确认";
        }
    } else if (status === 3) {
        return "待派送";
    } else if (status === 4) {
        return "派送中";
    } else if (status === 5) {
        return "已完成";
    } else if (status === 6) {
        return "已评价";
    } else if (status === 7) {
        return "已取消";
    }
}

//交易类型
function consumerType(type) {
    if (type === 1) {
        return "充值";
    } else if (type === 2) {
        return "余额消费";
    } else if (type === 3) {
        return "金币兑换";
    } else if (type === 4) {
        return "会员充值";
    } else if (type === 5) {
        return "订单退款";
    } else if (type === 6) {
        return "提现";
    }
}

//动态生成桌位显示状态及遍历
function tableStatus(data) {
    var table = "";
    if (data.tableStatus === 0) {
        table = '<div class="table step6" aria-valuetext="' + data.id + '"><p>' + data.tableNo + ' 号桌</p><p>' + data.tablePeople + ' 人</p></div>';
    } else if (data.tableStatus === 1) {
        table = '<div class="table step7" aria-valuetext="' + data.id + '"><p>' + data.tableNo + ' 号桌</p><p>' + data.tablePeople + ' 人</p></div>';
    } else if (data.tableStatus === 2) {
        table = '<div class="table step8" aria-valuetext="' + data.id + '"><p>' + data.tableNo + ' 号桌</p><p>' + data.tablePeople + ' 人</p></div>';
    } else {
        table = '<div class="table step9" aria-valuetext="' + data.id + '"><p>' + data.tableNo + ' 号桌</p><p>' + data.tablePeople + ' 人</p></div>';
    }
    $("#box").append(table);
}

//动态生成用户的所有地址
function selectAddress(data) {
    $("#container").empty();//生成之前要先清空
    var table = "";
    if (data != null && data !== "" && data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            table += '<div class="layui-col-md3"><div class="layui-card"><div class="layui-card-body myAddress"><p>' + data[i].addressPerson + '<button onclick="editAddress(' + data[i].id + ')">修改</button><button onclick="deleteAddress(' + data[i].id + ')">删除</button></p><p>' + data[i].addressPhone + '</p><p>' + data[i].addressName + '</p></div></div></div>';
        }
        table += '<div class="layui-col-md3" id="btnInsert" onclick="addAddress()"><div class="layui-card"><div class="layui-card-body myAddress btnInsert">+ 添加新地址</div></div></div>';
        $("#container").append(table);
    } else {
        var src = "./static/images/tanqi.jpg";
        table = '<div class="bottom_card_img"><div><img src="' + src + '"></div></div><span class="bottom_card_span">暂无地址，快去 <a style="color: #007DDB;cursor: pointer" onclick="addAddress()">添加</a> 吧~~~</span>';
        $("#container").append(table);
    }
}

//性别
function sexFormat(data) {
    if (data === 0) {
        return "未知";
    } else if (data === 1) {
        return "男";
    } else {
        return "女";
    }
}

//桌位状态
function tableStatus2(data) {
    if (data.tableStatus === 0) {
        return "空闲";
    } else if (data.tableStatus === 1) {
        return "已预订";
    } else if (data.tableStatus === 2) {
        return "已开台";
    } else {
        return "停用";
    }
}

//是否是积分卡，储值卡，折扣卡
function iscard(card) {
    if (card === 0) {
        return '<i class="layui-icon">&#x1006;</i>';
    } else {
        return '<i class="layui-icon">&#xe605;</i>';
    }
}

//是否是0或者null
function isZeroOrNull(data) {
    if (data === 0 || data === null) {
        return "";
    } else {
        return data;
    }
}

//充值申请状态
function askForFillStatus(data){
    if (data === 0) {
        return "待处理";
    } else if (data === 1) {
        return '<span style="color: #007DDB">已通过</span>'
    } else {
        return '<span style="color: red">已拒绝</span>';
    }
}






