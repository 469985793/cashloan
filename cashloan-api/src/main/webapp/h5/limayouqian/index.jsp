<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
	String invitationCode = request.getParameter("invitationCode");
	String channelCode = request.getParameter("channelCode");
	String inviteUserId = request.getParameter("userId");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
  <title>首页</title>
  <style>
    html{
      font-family: "微软雅黑";
    }
    .clearfix:after{
      content: ".";
      display: block;
      height: 0;
      clear: both;
      visibility: hidden;
    }
    html,.probody,.proView{
      width: 100%;
      height: 100%;
      margin: 0px;
      padding: 0px;
    }
    .proTop{
      position: relative;
    }
    .proTop .probg{
      width: 100%;
      height: auto;
      display: block;
    }
    .nobtnLeft{
      position: absolute;
      bottom: 8%;
      left: 11%;
      width: 16.4%;
      height: auto;
    }
    .nobtnLeft img{
      width: 100%;
      height: auto;
      display: block;
    }
    .nobtnRight{
      position: absolute;
      bottom: 8%;
      right: 11%;
      width: 16.4%;
      height: auto;
    }
    .nobtnRight img{
      width: 100%;
      height: auto;
      display: block;
    }
    .noBtn{
      position: absolute;
      bottom: 7%;
      left: 50%;
      width: 16.4%;
      margin-left: -8.2%;
      height: auto;
    }
    .noBtn img{
      width: 100%;
      height: auto;
      display: block;
    }
    .proBtm{
      width: 100%;
      background: #fdfae2;
      padding-top: 1.5em;
    }
    .txtTop{
      position: relative;
      border: 1px solid #ea316a;
      padding: 2em 1em 1.5em;
      background: #fff;
      border-radius: 5px;
      margin: 0 6%;
    }
    .txtTop img{
      width: 50%;
      height: auto;
      display: block;
      position: absolute;
      left: 50%;
      top: -1.2em;
      margin-left: -25%;
    }
    .txtTop p{
      margin: 0;
      padding: 0;
      color: #333;
      line-height: 20px;
      font-size: 14px;
    }
    .txtBtm{
      position: relative;
      border: 1px solid #ea316a;
      padding: 4em 1em 0;
      background: #fff;
      border-radius: 5px;
      margin: 1em 6%;
    }
    .txtBtm .txtBtmImg{
      width: 50%;
      height: auto;
      display: block;
      position: absolute;
      left: 50%;
      top: 1em;
      margin-left: -25%;
    }
    .txtBtm ul{
      margin: 0;
      padding: 0;
      list-style: none;
    }
    .txtBtm ul li{
      border-bottom: 1px dashed #eee;
      padding: 0.2em 0;
    }
    .txtBtm ul li:last-child{
      border-bottom: 0 none;
    }
    .txtBtm ul li img{
      width: 12%;
      height: auto;
      display: block;
      margin: 0.5em 0.5em 0.5em 1em;
      float: left;
    }
    .txtBtm ul li .txtBtmTxt{
      float: left;
      width: 75%;
      padding-left: 0.4em;
      padding-top: 0.3em;
    }
    .txtBtm ul li .txtBtmTxt h3{
      margin: 0;
      padding: 0;
      color: #eb0b50;
      font-weight: 500;
      font-size: 14px;
    }
    .txtBtm ul li .txtBtmTxt p{
      margin: 0;
      padding: 0;
      color: #333;
      font-size: 12px;
    }
    .proView .regBtn-a{
      width: 100%;
      text-align: center;
      background: #eb0b50;
      display: block;
      height: 48px;
      line-height: 48px;
      color: #fff;
      font-size: 16px;
      text-decoration: initial;
    }
  </style>
</head>
<body class="probody">
  <div class="proView">
      <div class="proTop">
        <img src=" " alt="" class="probg" />
        <div class="nobtnLeft">
          <img src="<%=basePath%>static/images/nobtnLeft.png" alt="" />
        </div>
        <div class="noBtn">
          <a href="<%=basePath%>h5/invite.jsp?invitationCode=<%=invitationCode%>&channelCode=<%=channelCode%>">
            <img src="<%=basePath%>static/images/noBtn.png"/>
          </a>
        </div>
        <div class="nobtnRight">
          <img src="<%=basePath%>static/images/nobtnRight.png" alt="" />
        </div>
      </div>
      <div class="proBtm">
        <div class="txtTop">
          <img src=" " id='txtTopImg'/>
          <p>
           富银弘远（厦门）信息科技有限公司 为用户打造一个极速金融借贷平台，用户可以在这里快速申请贷款，放款快捷，为用户轻松解决资金问题。
          </p>
        </div>
        <div class="txtBtm">
          <img src=" " class="txtBtmImg" />
          <ul>
            <li class="clearfix">
              <img src="<%=basePath%>static/images/ico001.png" alt="" />
              <div class="txtBtmTxt">
                <h3>一分钟认证</h3>
                <p>刷脸即可认证借钱，简单快捷</p>
              </div>
            </li>
            <li class="clearfix">
              <img src="<%=basePath%>static/images/ico002.png" alt="" />
              <div class="txtBtmTxt">
                <h3>10分钟放款</h3>
                <p>认证简单，一分钟授信，10分钟放款</p>
              </div>
            </li>
            <li class="clearfix">
              <img src="<%=basePath%>static/images/ico003.png" alt="" />
              <div class="txtBtmTxt">
                <h3>无需任何抵押</h3>
                <p>纯信用贷款，无需任何抵押</p>
              </div>
            </li>
            <li class="clearfix">
              <img src="<%=basePath%>static/images/ico004.png" alt="" />
              <div class="txtBtmTxt">
                <h3>随时随地借款</h3>
                <p>可移动的ATM，随时可以申请借款</p>
              </div>
            </li>
            <li class="clearfix">
              <img src="<%=basePath%>static/images/ico006.png" alt="" />
              <div class="txtBtmTxt">
                <h3>还款方式多样</h3>
                <p>支持支付宝还款，自动代扣，还款方式多样，不用担心逾期带来的信用问题</p>
              </div>
            </li>
            <li class="clearfix">
              <img src="<%=basePath%>static/images/ico007.png" alt="" />
              <div class="txtBtmTxt">
                <h3>信用优质好处多</h3>
                <p>信用评级越高，审核越快</p>
              </div>
            </li>
          </ul>
        </div>
        <a href="<%=basePath%>h5/invite.jsp?invitationCode=<%=invitationCode%>&channelCode=<%=channelCode%>" class="regBtn-a">立即注册</a>
      </div>
  </div>
  <script src="<%=basePath%>static/js/jquery.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>static/js/config.js" ></script>
  <script>
    $('.probg').attr('src',getIndex_img1()); //头部图片
    $('#txtTopImg').attr('src',getIndex_img2()); //第一个横幅
    $('.txtBtmImg').attr('src',getIndex_img3()); //第二个横幅
  </script>
</body>
</html>