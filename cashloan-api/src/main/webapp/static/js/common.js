/**
 * @author myron
 * @version 2016051301
*/
/***********************************begin******************************************/
function jumpTo(url){
    window.location.href = url;
}

/**
 * 调用原生客户端-复制信息
 * @param text
 * @returns
 */
function copyText(text){
    return nativeMethod.copyTextMethod('{"text":"'+text+'","tip":"复制成功!"}');
}
/**
 * 调用原生客户端-返回原生页
 * @returns
 */
function returnNative(type){
    type = type || 0;
    var str = '{"type" : "'+ type +'"}';
    return nativeMethod.returnNativeMethod(str);
}

/*
 *获取ID
*/
function ID(id){
    return !id ? null : document.getElementById(id);
}

/*
 *获取url参数的值
*/
function getQueryString(name){
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/*
 *获取来源URL
*/
function getSourceUrl(){
    return document.referrer ? document.referrer : '/';
}

/*
 *拨打电话
*/
function callPhoneMehtod(phone){
    jumpTo("tel:" + phone);
}

/*
 *根据屏幕调整大小
*/
function fontSize(){
    $(document.body).css("font-size",$(document.body).width() / 480*120 + '%');
}

/*
 *小于一屏以一屏显示
*/
function isOneScreen(){
    if( $(document.body).outerHeight(true) < $(window).innerHeight() ){
        $(document.body).height($(window).innerHeight() + 'px');
    }
}

/*
 *判断客户端是否为 PC 还是手持设备
*/
function isPC(){
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++){
        if (userAgentInfo.indexOf(Agents[v]) > 0){
            flag = false;
            break;
        }
    }
    return flag;
}

/**
 * 不足补零
 * @param num 必需。规定被补零的数字
 * @param n 可选。 规定补零后的长度，默认两位
 */
function appendZero(num, n){
    n = n ? n : 2;
    var remainLen = n-(''+num).length > 0 ? n-(''+num).length : 0;
    return Array(remainLen+1).join(0) + num;  
}

/**
 * url参数拼接
 * @url
 * @params string
*/
function createUrl(url,params){
    return url + (url.indexOf("?") == -1 ? '?' : '&') + params;
}

/**
 * 字符串替换 默认去除所有空格
 * @string  必需。规定被搜索的字符串。
 * @find 可选。规定要查找的值。
 * @replace 可选。规定替换 find 中的值的值。
 * @place 可选。规定替换 string 的位置：左右 左 右 所有，默认所有。
*/
function strReplace(string,find,replace,place){
    find = typeof(find) == 'undefined' ? ' ' : find;
    replace = typeof(replace) == 'undefined' ? '' : replace;
    place = typeof(place) == 'undefined' ? 'all' : place;
    var placeList = {
        "left" : "^[" + find + "]",
        "right" : "[" + find + "]$",
        "both" : "^[" + find + "]|[" + find + "]$",
        "all" : "[" + find + "]"
    };
    placeList[place] = typeof(placeList[place]) == 'undefined' ? placeList['all'] : placeList[place];
    var reg =new RegExp(placeList[place],"ig"); // reg为 /[变量]/ig
    return string.replace(reg,replace);
}

/*
 *判断是否是手机号码
*/
function isPhone(num){
    var reg = /^[1]\d{10}$/;
    return reg.test(num);
}

/*
 *判断是否是IP地址
*/
function isIP(str){
    var reg = /((([1-9]?|1\d)\d|2([0-4]\d|5[0-5]))\.){3}(([1-9]?|1\d)\d|2([0-4]\d|5[0-5]))/;
    return reg.test(str);
}

/**
* 模糊手机号
* eg：13917883434 变成 139****3434
*/
function blurPhone(phone){
    phone = strReplace(phone);
    return phone.substring( 0, 3) + '****' + phone.substring(7);
}

/**
 * 格式化手机号
 * eg：13917883434 变成 139 1788 3434
 */
function formatPhone(phone){
    phone = strReplace(phone);
    return phone.substring( 0, 3) + ' ' + phone.substring( 3 , 7) + ' ' + phone.substring(7);
}

/**
 * 时间戳转换日期
 * @param <int> unixTime    待时间戳(秒)
 * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)
 * @param <int>  timeZone   时区
 */
function unixToDate(unixTime, isFull, isSeconds, timeZone){
    if (typeof (timeZone) == 'number')
    {
        unixTime = intval(unixTime) + intval(timeZone) * 60 * 60;
    }
    var time = new Date(unixTime * 1000);
    var ymdhis = "";
    ymdhis += time.getFullYear() + "-";
    ymdhis += appendZero( (time.getMonth()+1) ) + "-";
    ymdhis += appendZero( time.getDate() );
    if (isFull === true)
    {
        ymdhis += " " + time.getHours() + ":";
        ymdhis += appendZero(time.getMinutes());
        if (isSeconds === false){
            return ymdhis;
        }
        ymdhis += ":" + appendZero(time.getSeconds());

    }
    return ymdhis;
}

/*
 *限制只能输入数字
 * 含value属性的标签
 * eg: onkeyup事件
*/
function justInt(e){
    e.value = e.value.replace(/[^\d]/g,'');
}

/*
 *限制只能输入浮点数
 * 含value属性的标签
 * eg: onkeyup事件
*/
function justFloat(e){
    e.value=e.value.replace(/[^\d+(\.\d+)?$]/g,'');
}

/*
 * 转换成整型
*/
function intval(num){
    var ret = parseInt(num);
    return isNaN(ret) ?  0 : ret;
}

/**
 * 表单提交
 * @param URL
 * @param PARAMS 
 * @param METHOD
 */
function formPost(URL, PARAMS, METHOD){
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = METHOD || 'POST';
    temp.style.display = "none";
    for (var x in PARAMS){
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

/**
 *===================倒计时begin=============
 * 启用：setTimes();
*/
var stop = 0;
var ctime = 0;
var etime = 0;
function display(stamp){
    stamp = stamp*1000;
    rtime = etime-ctime+stamp;
    if(rtime < 0){
        stop = 1;
        return false;
    }
    var h = intval(rtime%86400000/3600000);
    var m = intval(rtime%86400000%3600000/60000);
    var s = intval(rtime%86400000%3600000%60000/1000);
    var ms = intval(rtime%1000/10);
    var str = '';
    if( h > 0 ){
        str += appendZero(h)+":";
    }
    str += appendZero(m)+":"+appendZero(s)+"."+appendZero(ms);
    return str;
}

function setTimes(){
    etime = new Date().getTime();
    checkTime();
}

function checkTime(){
    ctime = new Date().getTime();
    if( countDown() ){
        window.setTimeout("checkTime()",10);
    }
}
function countDown(){
    $.each($('.alltime'),function(){
        var ret = display($(this).attr('time'));
        if(ret){
            $(this).html(ret);
        }else{
            window.location.reload();
        }
    });
    if( stop ){
        return false;
    }
    return true;
}
/**
*===================倒计时end=============
*/

/**
 *===================文字滚动begin=============
 * 启用：window.marquee.Content = txt;initMarquee();
*/
window.marquee = {};
window.marquee.Content = ''; // 滚动内容
window.marquee.H = '1.2em'; // 内容高度
window.marquee.T = 30; // 滚动速度
// 定义要使用到的函数
function initMarquee(){
    var str= window.marquee.Content;
    var html = '';
    if( isPC() ){
        html += '<div class="_width_limit" id="marqueeBox" style="width:100%;overflow:hidden;height:'+window.marquee.H+';line-height:'+window.marquee.H+';" onmouseover="clearInterval(timing);" onmouseout="timing=setInterval(startMarquee,window.marquee.T);"';
    }else{
        html += '<div class="_width_limit" id="marqueeBox" style="width:100%;overflow:hidden;height:'+window.marquee.H+';line-height:'+window.marquee.H+';" ontouchstart="clearInterval(timing);" ontouchend="timing=setInterval(startMarquee,window.marquee.T);">';
    }
    html += '<div style="width:8000%;">';
    html += '<div id="Box1" style="float:left;word-break:keep-all;word-wrap:normal;">'+str+'</div>';
    html += '<div id="Box2" style="float:left;word-break:keep-all;word-wrap:normal;"></div>';
    html += '<div style="clear:both;"></div></div></div>';
    ID("Marquee").innerHTML = html;
    timing = setInterval(startMarquee,window.marquee.T);
}
function startMarquee(){
    var marqueeBox = ID("marqueeBox");
    var Box1 = ID("Box1");
    var Box2 = ID("Box2");
    Box2.innerHTML = ID("Box1").innerHTML;
    if( marqueeBox.scrollLeft - Box2.offsetWidth >= 0 ){
        marqueeBox.scrollLeft -= Box1.offsetWidth;
    }else{
        marqueeBox.scrollLeft++;
    }
}
/**
*===================文字滚动end=============
*/

/**
 *===================加载进度条begin=============
*/
function drawCircle(){
    var circle = new Sonic({
        width: 100,
        height: 100,
        stepsPerFrame: 1,
        trailLength: 1,
        pointDistance: .02,
        fps: 30,
        fillColor: '#fb5353',
        step: function (point, index){
            this._.beginPath();
            this._.moveTo(point.x, point.y);
            this._.arc(point.x, point.y, index * 7, 0, Math.PI * 2, false);
            this._.closePath();
            this._.fill();
        },
        path: [
            ['arc', 50, 50, 30, 0, 360]
        ]
    });
    circle.play();
    $("#circle-hint").html(circle.canvas);
    $("#circle-hint,#circle-mask").css('display', 'block');
}
function hideCircle(){
    $("#circle-mask,#circle-hint").css('display', 'none');
}
/**
*===================加载进度条end=============
*/

/**
 * 调用原生分享
 * @param title 分享标题
 * @param body 分享内容
 * @param url 分享url
 * @param logo 分享logo
 * @param type 0|1，0直接分享，1右上角出现分享按钮
 * @returns
 */
function nativeShare(title,body,url,logo,type){
    var type = type == 1 ? 1 : 0;
    return nativeMethod.shareMethod('{"share_title":"'+title+'","share_body":"'+body+'","share_url":"'+url+'","share_logo":"'+logo+'","type":"'+type+'"}');
}

/**
 *===================遮罩层begin=============
*/
function showExDialog(tips,btn1,func1,btn2,func2){
    hideExDialog();
    func1 = arguments[2] ? arguments[2] : 'hideExDialog';
    func2 = arguments[4] ? arguments[4] : 'hideExDialog';
    func1 = func1.indexOf('(') > 0 ? func1+';' : func1+'();';
    func2 = func2.indexOf('(') > 0 ? func2+';' : func2+'();';
    var str = '';
    str += '<div id="mask"></div>';
    str += '<div id="exception_dialog_wraper"><div id="exception_dialog">';
    str += '<div class="a_center" id="exception_dialog_tips">'+tips+'</div>';
    if(arguments[1]){
        str += '<div class="_inline_block exception_dialog_btn" onclick="'+func1+'">'+btn1+'</div>';
    }
    if(arguments[3]){
        str += '<div class="_inline_block exception_dialog_btn" onclick="'+func2+'">'+btn2+'</div>';
    }
    str += '</div></div>';
    $(".kdlc_mobile_wraper > div").append(str);
    if( !arguments[3] && !arguments[4] ){
        $(".exception_dialog_btn").width("80%");
    }
    $(document.body).css({"overflow-x":"hidden","overflow-y":"hidden"});
}
function hideExDialog(){
    $("#mask,#exception_dialog_wraper").remove();
    $(document.body).css({"overflow-x":"auto","overflow-y":"auto"});
}
/**
*===================遮罩层end=============
*/

 /***********************************end******************************************/