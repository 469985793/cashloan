
var u = navigator.userAgent;
window.browser = {};
window.browser.iPhone = u.indexOf('iPhone') > -1;
window.browser.android = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1;//android or uc
window.browser.ipad = u.indexOf('iPad') > -1;
window.browser.isclient = u.indexOf('lyWb') > -1;
window.browser.ios = u.match(/Mac OS/); //ios
window.browser.width = window.innerWidth;
window.browser.height = window.innerHeight;
window.browser.wx = u.match(/MicroMessenger/);
getQueryString('source_tag') && window.localStorage.setItem("source_tag",getQueryString('source_tag'));
window.source_tag = localStorage.source_tag ? localStorage.source_tag : 'wap';

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
function callPhoneMehtod(phone){
    if (window.browser.android) {
        // window.JavaMethod.callPhoneMethod(phone);
        window.location = "tel:" + phone;
    }else{
        window.location = "tel:" + phone;
    }
}
function downLoad() {
    if (window.browser.iPhone || window.browser.ipad || window.browser.ios) {
        iosDownload();
    } else {
        androidDownload();
    }
}
function iosDownload() {
    if (!window.browser.wx){
        window.location.href = "https://itunes.apple.com/cn/app/id953061503?mt=8";
    }else{
        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.kdkj.koudailicai";
    }
}
function androidDownload() {
    if (!window.browser.wx){
        if(window.source_tag){
            window.location.href = "http://res.koudailc.com/download/app/koudailicai_"+window.source_tag+".apk";
        }else{
            window.location.href = "http://res.koudailc.com/download/app/koudailicai.apk";
        }
    }else{
        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.kdkj.koudailicai";
    }
}

function fontSize(){
    !function(x){
        var dom = x.document;
        function handleBody(){
            var cssTxt = 'body{font-size:100% !important;}';
            var _style = dom.createElement('style');
            _style.type = 'text/css';
            dom.getElementsByTagName("head")[0].appendChild(_style);
            try {
                _style.innerHTML = cssTxt;
            } catch (c) {
                _style.innerText = cssTxt;
            }
        }
        function handleHtml(){
            var el = dom.documentElement;
            el.style.fontSize = Math.min(100,el.clientWidth / 480*120 ) + '%';
        }
        if(!!x.addEventListener){
            x.addEventListener("resize", function() { handleHtml(); });
            x.addEventListener("load", function() { handleBody(); });
        }else{
            x.attachEvent("onresize", function() { handleHtml(); });
            x.attachEvent("onload", function() { handleBody(); });
        }
        handleHtml();
    }(window);
}

function isOneScreen(){
    if( $(document.body).outerHeight(true) < $(window).innerHeight() ){
        $(document.body).height($(window).innerHeight() + 'px');
    }
}


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

function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}


function isPhone(num){
    var reg = /^[1]\d{10}$/;
    return reg.test(num);
}

function copyToClipboard(maintext){
    if (window.clipboardData){
        window.clipboardData.setData("Text", maintext);
    }else if (window.netscape){
        try{
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        }catch(e){
            alert("...");
        }
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if (!clip) return;
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans) return;
        trans.addDataFlavor('text/unicode');
        var str = new Object();
        var len = new Object();
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext=maintext;
        str.data=copytext;
        trans.setTransferData("text/unicode",str,copytext.length*2);
        var clipid=Components.interfaces.nsIClipboard;
        if (!clip) return false;
            clip.setData(trans,null,clipid.kGlobalClipboard);
    }
    alert("...\r\n" + maintext);
}


function Appendzero (obj) {
    if (obj < 10) return "0" + obj; else return obj;
}

function UnixToDate(unixTime, isFull, isSeconds, timeZone) {
    if (typeof (timeZone) == 'number')
    {
        unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
    }
    var time = new Date(unixTime * 1000);
    var ymdhis = "";
    ymdhis += time.getFullYear() + "-";
    ymdhis += Appendzero( (time.getMonth()+1) ) + "-";
    ymdhis += Appendzero( time.getDate() );
    if (isFull === true)
    {
        ymdhis += " " + time.getHours() + ":";
        ymdhis += Appendzero(time.getMinutes());
        if (isSeconds === false){
            return ymdhis;
        }
        ymdhis += ":" + Appendzero(time.getSeconds());

    }
    return ymdhis;
}


function justInt(e){
    e.value = e.value.replace(/[^\d]/g,'');
}


function justFloat(e){
    e.value=e.value.replace(/[^\d+(\.\d+)?$]/g,'');
}

var KD = {};
KD.util = {};
KD.util.post = function(url, data, okfn) {
    KD.util.post.pIndex = (KD.util.post.pIndex || 0) + 1;
    var iframe = $('<iframe name="pIframe_'+KD.util.post.pIndex+'" src="about:blank" style="display:none" width="0" height="0" scrolling="no" allowtransparency="true" frameborder="0"></iframe>').appendTo($(document.body));

    var ipts = [];
    $.each(data, function(k, v){
        ipts.push('<input type="hidden" name="'+k+'" value="" />');
    });
    
    if(!/(\?|&(amp;)?)fmt=[^0 &]+/.test(url)) url += (url.indexOf('?') > 0 ? '&' : '?') + 'fmt=1';

    var form = $('<form action="'+url+'" method="post" target="pIframe_'+KD.util.post.pIndex+'">'+ipts.join('')+'</form>').appendTo($(document.body));

    $.each(data, function(k, v){
        form.children('[name='+k+']').val(v);
    });

    iframe[0].callback = function(o){
        if(typeof okfn == 'function') okfn(o);
        $(this).src = 'about:blank';
        $(this).remove();
        form.remove();
        iframe = form = null;
    };
    if(false && $.browser.msie && $.browser.version == 6.0){ 
        iframe[0].pIndex = KD.util.post.pIndex;
        iframe[0].ie6callback = function(){
            form.target = 'pIframe_' + this.pIndex;
            form.submit();
        };
        iframe[0].src = location.protocol + '//m.koudailc.com/html/ie6post.html';
    } else {
        form.submit();
    }
};

function ID(id) {
    return !id ? null : document.getElementById(id);
}

function jumpTo(url){
    window.location.href = url;
}


function display(stamp){
    stamp = stamp*1000;
    rtime = etime-ctime+stamp;
    if(rtime >= 86400000){
        rtime -= 86400000;
    }
    if (rtime>60000){
        m = intval(rtime/60000);
    }else{
        m = 0;
    }
    if (rtime>1000){
        s = intval(rtime/1000-m*60);
    }else{
        s = 0;
    }
    ms = intval((rtime-m*60*1000-s*1000)/10);
    if( ms < 0 ){
        ms = -1;
       return false;
    }
    return m+":"+((s<10) ? "0"+s : s)+"."+((ms<10) ? "0"+ms : ms);
}

function setTimes(stamp){
    etime = new Date().getTime();
    checkTime();
}

function checkTime(){
    ctime = new Date().getTime();
    if( countDown() ){
        window.setTimeout("checkTime()",10);
    }
}
var ms = -1;
var ctime = 0;
var etime = 0;
function countDown(){
    $.each($('.alltime'),function(){
        var ret = display($(this).attr('time'));
        if(ret){
            $(this).html(ret);
        }else{
            window.location.reload();
        }
    });
    if( ms == -1 ){
        return false;
    }
    return true;
}

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


function getSourceUrl(){
    return document.referrer ? document.referrer : '/';
}

function intval(num){
    var ret = parseInt(num);
    return isNaN(ret) ?  0 : ret;
}


function baiDuiPageTracks(tag, eventType, tagPrefix, optLabel, optValue){
    tag = tag ? tag : 'other';
    eventType = eventType ? eventType : 'onclick';
    tagPrefix = tagPrefix ? tagPrefix : '';
    var params = ['_trackEvent', tagPrefix+tag, eventType];
    optLabel ? params.push(optLabel) : params.push('');
    optValue && params.push(optValue);
    console.log(params);
    window._hmt && window._hmt.push(params);
}