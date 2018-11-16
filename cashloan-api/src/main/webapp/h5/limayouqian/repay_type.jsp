<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <title>还款方式</title>
    <meta name="keywords" content="贷款,小额借钱,借贷,贷款app,急用钱,短期快速放贷,极速借款借钱,小额贷款">
    <meta name="description" content="立马有钱专注于为个人提供正规小额贷款、无抵押贷款、个人贷款、闪电借钱等服务">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <script src="/static/js/flexable.js"></script>
    <link href="/static/css/style1.css" rel="stylesheet"/>
	    <script src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/spin.js"></script>
    <script type="text/javascript" src="/static/js/common.js"></script>
    <script>
      var _hmt = _hmt || [];
      (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?985acfc678db5c774efb3ed1a2235b53";
        var s = document.getElementsByTagName("script")[0]; 
        s.parentNode.insertBefore(hm, s);
      })();
  </script>
  </head>
  <body>
    <div class="repayment-description">
    <h3>到期自动扣款</h3>
    <ul>
      <li>平台如何进行自动扣款？
        <p>若在借款期限内未主动发起还款，则平台会在还款日当天从绑定银行卡中扣除所借款项，扣款成功后会向注册手机号发送短信提醒。</p>
      </li>
      <li>平台自动扣款的时间段有哪些？
        <p>平台会在还款日当天的四个时间段：10:00、13:00、16:00、20:00，进行自动扣款。</p>
      </li>
      <li>银行卡账户余额不足会影响自动扣款吗?
        <p>银行卡账户余额不足，会导致平台扣款失败，可能会造成逾期，请保证在扣款之前银行卡账户资金充足。</p>
      </li>
      <li>邮政储蓄银行卡不支持自动扣款，怎么办?
        <p>可通过支付宝还款或打款至对公账号两种方式进行还款。对公银行账号为<span class='bank'></span>，还款后请与客服取得联系，将注册用户手机号、转账金额和转入银行卡信息截图提供给客服。客服电话：<span class='phone'></span>（工作日9:00-18:00）</p>
      </li>
    </ul>
    <h3>支付宝转账</h3>
    <ul>
      <li>如何进行支付宝转账？
        <p>进入支付宝首页，点击“转账”，选择“转到支付宝账户”，输入支付宝账户<span class='airPay'></span>，点击“下一步”，输入转账金额，并将姓名和注册手机号添加到备注中，点击“确认转账”，输入支付密码即可完成还款。</p>
      </li>
      <li>如何确认支付宝转账是否成功?
        <p>还款后请及时在“借款详情”页查看还款状态，当页面显示“已还款”时，则表示还款成功。如有疑问请联系客服。客服电话：<span calss='phone'></span>（工作日9:00-18:00）</p>
      </li>
    </ul>
  </div>
  <script type="text/javascript" src="/static/js/config.js" ></script>
  <script>
  $(function() {
    $("li").each(function(k,v){
      $(v).attr('id',k); 
    })
    $("li").click(function(){
      $(this).toggleClass("active");
      var id = $(this).attr('id');
      var lis = $('ul li').filter(function(i,e){
         return $(e).attr('id') != id;
      })
      lis.removeClass();
      
   });
 })
 $('.bank').text(getBank2());
 $('.airPay').text(getAirpay());
 $('.phone').text(getPhone());
 </script>  </body>
</html>
