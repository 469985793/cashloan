<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统提示信息</title>
<script type="text/javascript" src="${webroot}/static/js/jquery.js"></script>
<script type="text/javascript" src="${webroot}/static/js/jquery-ui.min.js"></script>
</head>
 <style type="text/css">
   .center{width:140px;height:186px;text-align:center}
   .tips{width:138px;height:138px;text-align:center}
   .msg{color:#4C99E9;font-size:16px;text-align:center;font-weight:bold;font-family:宋体;margin-top:10px}
   </style>
<body>
<div id="main" class="clearfix">
	<div class="box">
		<div class="box-con">
			<p class="reg-pro">
           	 系统提示信息 
            </p>
            <div align="center" id="message">
           	<br/> <br/>返回系统中，请稍后…
            </div>
            <div id="useragent"></div>
		</div>
	</div>
<a href="mobile://toinvest?res_code=0&res_msg=encodeURIComponent('${msg!}')" id="return"></a>
</div>
<script type="text/javascript">
var ua = navigator.userAgent;
var timer =  setInterval("testTime()",1000); 
var code='${code!}';
var msg='${msg!}';
function testTime() {
           if(code!="200"){
            		 document.getElementById('message').innerHTML = "<br/><br/>"+"<div id='msg'>"+msg+"</div>";
        			 $("#msg").addClass("msg");
            		 clearInterval(timer);
            		 setTimeout("returnApp(0,'"+'${msg!}'+"')",1000);
             }else{
            		 var msg3='${r_msg}';
            	     document.getElementById('message').innerHTML = "<br/><br/>"+"<div id='msg'>"+msg+"</div>";
	            	 $("#msg").addClass("msg");
	            	 clearInterval(timer);
	                 setTimeout("returnApp(1,'"+'${msg!}'+"')",1000); 
             }
      		 
}
	function returnApp(code,data) {
		if (ua.indexOf('Android') > -1) {
   		 window.webReturn.webReturn(code,data);
   	     }else if(ua.indexOf('iPhone') > -1 || ua.indexOf('iPod') > -1
   				|| ua.indexOf('iPad') > -1){                		 
   		 var ret = document.getElementById("return");
   		 ret.href="mobile://toinvest?res_code="+code+"&res_msg="+data+"";
   		 ret.click();
        } 
	}
</script>
</body>
</html>