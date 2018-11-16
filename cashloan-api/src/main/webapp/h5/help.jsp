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
<%--<title>【帮助中心】</title>--%>
<title>帮助中心</title>
<script src="/static/js/mobile.js"></script>
<script
	src="/static/js/zepto.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/static/css/general.css1" />
<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "https://hm.baidu.com/hm.js?48628ee4944a3955ccd3ab3c2d0e9cb8";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();
</script>
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
<script type="text/javascript">
	var u = navigator.userAgent;
	window.browser = {};
	window.browser.iPhone = u.indexOf('iPhone') > -1;
	window.browser.android = u.indexOf('Android') > -1
			|| u.indexOf('Linux') > -1;//android or uc
	window.browser.ipad = u.indexOf('iPad') > -1;
	window.browser.isclient = u.indexOf('lyWb') > -1;
	window.browser.ios = u.match(/Mac OS/); //ios
	window.browser.width = window.innerWidth;
	window.browser.height = window.innerHeight;
	window.browser.wx = u.match(/MicroMessenger/);
	window.source_tag = getQueryString('source_tag');
</script>
</head>
<body>
	<div class="container">
		<style type="text/css">
.container {
	margin: 16px;
}
</style>
		<div class="container">
			<style type="text/css">
.container {
	margin: 0;
}
</style>
			<link rel="stylesheet" type="text/css"
				href="/static/css/general.css" />
			<script type="text/javascript"
				src="/static/js/jquery.min.js"></script>
			<script type="text/javascript"
				src="/static/js/common1.js"></script>
			<link rel="shortcut icon" href="/static/images/ico.ico" />
			<div class="kdlc_mobile_wraper container">
				<div class="bg _hidden">
					<img src="/static/images/logo_320.png" />
				</div>
				<div id="question_wraper">
					<div class="content">
						<div class=" padding info em__9">
							<ol class=" list-paddingleft-2" style="list-style-type: decimal;color:red;font-size:16px;">
								<li><p>客服:<span class='phone'>关注微信公众号“小粒友” </span></p></li>
							</ol>
						</div>
						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
								class="_hidden v_center"
								src="/static/images/top.png"
								width="100%" /><img class="v_center"
								src="/static/images/right.png"
								width="60%" /></span> <span class="_333 v_center"> 什么是现金贷？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>现金贷，是小额现金贷款业务的简称，具有方便灵活的借款与还款方式，以及实时审批、快速到账的特性。</p>
						</div>
						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
								class="_hidden v_center"
								src="/static/images/top.png"
								width="100%" /><img class="v_center"
								src="/static/images/right.png"
								width="60%" /></span> <span class="_333 v_center">可借额度是多少？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>目前分为500,1000,1500三个额度可选。</p>
						</div>
						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
								class="_hidden v_center"
								src="/static/images/top.png"
								width="100%" /><img class="v_center"
								src="/static/images/right.png"
								width="60%" /></span> <span class="_333 v_center">可借期限是多少？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>目前为固定14天。</p>
						</div>
						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
								class="_hidden v_center"
								src="/static/images/top.png"
								width="100%" /><img class="v_center"
								src="/static/images/right.png"
								width="60%" /></span> <span class="_333 v_center">借款服务费？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>包含征信服务费、账户管理费</p>
						</div>
						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
								class="_hidden v_center"
								src="/static/images/top.png"
								width="100%" /><img class="v_center"
								src="/static/images/right.png"
								width="60%" /></span> <span class="_333 v_center">借款人需要满足什么条件？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>年龄达到21岁且小于38岁的中华人民共和国公民，无不良信用记录。</p>
						</div>
<%--						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
								class="_hidden v_center"
								src="/static/images/top.png"
								width="100%" /><img class="v_center"
								src="/static/images/right.png"
								width="60%" /></span> <span class="_333 v_center">注册或者更改密码时验证码收不到怎么办？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>重启手机或清理手机缓存；退订过短信，联系客服回复短信发送。</p>
						</div>--%>
						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
								class="_hidden v_center"
								src="/static/images/top.png"
								width="100%" /><img class="v_center"
								src="/static/images/right.png"
								width="60%" /></span> <span class="_333 v_center">申请一笔借款需要哪些步骤？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>需要下载APP后打开，填写客户基本信息申请审核，审核通过即会很快放款。</p>
						</div>
						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
								class="_hidden v_center"
								src="/static/images/top.png"
								width="100%" /><img class="v_center"
								src="/static/images/right.png"
								width="60%" /></span> <span class="_333 v_center">借款申请需要做哪些认证？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>需要提供手机号码、身份证号、联系人、绑定银行卡等信息。</p>
						</div>

						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
									class="_hidden v_center"
									src="/static/images/top.png"
									width="100%" /><img class="v_center"
														src="/static/images/right.png"
														width="60%" /></span> <span class="_333 v_center">如何更换手机号？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>可以用新手机号重新申请。如有特殊情况，可以与客服联系。</p>
						</div>

						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
									class="_hidden v_center"
									src="/static/images/top.png"
									width="100%" /><img class="v_center"
														src="/static/images/right.png"
														width="60%" /></span> <span class="_333 v_center">审核需要多久？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>实时自动审核，通常只需要几分钟。</p>
						</div>

						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
									class="_hidden v_center"
									src="/static/images/top.png"
									width="100%" /><img class="v_center"
														src="/static/images/right.png"
														width="60%" /></span> <span class="_333 v_center">审核通过后多久放款？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>审核通过后立即申请放款，通常只需要1分钟。</p>
						</div>

						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
									class="_hidden v_center"
									src="/static/images/top.png"
									width="100%" /><img class="v_center"
														src="/static/images/right.png"
														width="60%" /></span> <span class="_333 v_center">被拒绝后是否可以再次申请？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>被拒绝后可以在30天后重新申请，系统会重新判断通过与否。</p>
						</div>

						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
									class="_hidden v_center"
									src="/static/images/top.png"
									width="100%" /><img class="v_center"
														src="/static/images/right.png"
														width="60%" /></span> <span class="_333 v_center">目前平台支持的银行卡有哪些？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>绝大多数国内银行卡都可以。如遇特殊情况我们会在各平台通知。</p>
						</div>

						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
									class="_hidden v_center"
									src="/static/images/top.png"
									width="100%" /><img class="v_center"
														src="/static/images/right.png"
														width="60%" /></span> <span class="_333 v_center">目前还款方式有哪些？</span>
						</h3>

						<div class="_hidden padding info em__9">
							<p>到期自动从绑定的银行卡中扣除。</p>
						</div>

						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
									class="_hidden v_center"
									src="/static/images/top.png"
									width="100%" /><img class="v_center"
														src="/static/images/right.png"
														width="60%" /></span> <span class="_333 v_center">
								逾期会收取费用吗？具体多少？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>逾期是按约定收取10%+十五天内0.5%*逾期天数；十五以上0.75%*逾期天数的滞纳金。</p>
						</div>

						<h3 class="em_1 _cursor">
							<span class="_inline_block _wraper"><img
									class="_hidden v_center"
									src="/static/images/top.png"
									width="100%" /><img class="v_center"
														src="/static/images/right.png"
														width="60%" /></span> <span class="_333 v_center">不还款的影响有哪些？</span>
						</h3>
						<div class="_hidden padding info em__9">
							<p>按期还款会提升客户在各个平台的信用指标并降低再次贷款的服务费用，如未还款则会产生额外违约金并影响客户在各个平台的信用指标。</p>
						</div>
					</div>
				</div>
				<script type="text/javascript" src="/static/js/config.js" ></script>
				<script type="text/javascript">
					$(document).ready(function() {
						Initialization();
					});
					$(window).resize(function() {
						Initialization();
					});
					function Initialization() {
						fontSize();
						isOneScreen();
					}
					$("#question_wraper .content h3").click(function() {
						$(this).find("img").toggleClass("_hidden");
						$(this).next(".info").toggleClass("_hidden");
					});
					/*$('.phone').text(getPhone());
					$('.airPay').text(getAirpay());*/
				</script>
			</div>
		</div>
		<!--!doctype-->
	</div>
</body>
</html>