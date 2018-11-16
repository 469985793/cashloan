<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="#7CD88E" />
<title>【还款方式】</title>
<script src="/static/js/mobile.js"></script>
<script
	src="/static/js/zepto.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/static/css/general.css" />
<style type="text/css">
body {
	font-size: 16px;
}

html, body, div, span, h1, h2, h3, p, em, img, dl, dt, dd, ol, ul, li,
	table, tr, th, td, form, input, select {
	margin: 0;
	padding: 0;
}

body {
	min-width: 320px;
	max-width: 480px;
	min-height: 100%;
	margin: 0 auto;
}

.bg {
	display: none;
	/*position: absolute;*/
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background-color: rgba(0, 0, 0, .7);
}

.bg img {
	display: block;
	position: absolute;
	width: 65%;
	top: 5px;
	height: auto;
	float: left;
	left: 25%;
}
</style>
</head>
<body>
	<div class="container">
		<style type="text/css">
.container {
	margin: 16px;
}
</style>
		<p>①【推荐-银行卡转账还款】，可以转账给银行卡账号<span class='bank'> </span>，需要备注好姓名以及注册手机号；</p>
		<p>②【推荐-支付宝转账还款】，可以还款到支付宝账号
			<span class='airPay'> </span>，需要备注好姓名以及注册手机号。</p>
		<p>③【到期平台代扣】，在还款日之前将所需还款金额放在绑定银行卡，还款日平台将会在四个时间段进行扣款（每天10:00，13:00，16:00，20:00），扣款成功后会给您发送短信。</p>
	</div>
	<script src="/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="/static/js/config.js" ></script>
	<script>
		$('.bank').text(getBank());
		$('.airPay').text(getAirpay());
	</script>
</body>
</html>


