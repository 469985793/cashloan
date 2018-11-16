<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <title>还款方式</title>
    <meta name="keywords" content="贷款,小额借钱,借贷,贷款app,急用钱,短期快速放贷,极速借款借钱,小额贷款">
    <meta name="description" content="现金贷专注于为个人提供正规小额贷款、无抵押贷款、个人贷款、闪电借钱等服务">
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
        <p>在还款日当天，系统会自动从您绑定的银行卡中扣除相应的额度，所以请在还款日当天保持卡内有足够的金额。</p>
      </li>
      <li>绑定的银行卡余额不足扣款会成功吗？
        <p>绑定的银行卡账户余额不足会导致扣款失败。</p>
      </li>
      <li>逾期后如何还款？
        <p>在还款日当天未能完全还款即为逾期，则剩余应还款会在之后的每天继续尝试扣除剩余本金和其它费用，直至还款成功。</p>
      </li>
      <li>除了在绑定的银行卡卡中扣钱外，是否支持其它形式的还款？
        <p>一般不支持其它形式的还款。如遇到问题导致无法成功请联系客服</p>
      </li>
      <li>绑定的银行卡内有钱，扣款失败怎么办？
        <p>如遇自动扣款不成功或其它问题，可以关注微信公众号“小粒友”并联系客服。</p>
      </li>
      <li>客服如何联系？
        <p>唯一方式是关注微信公众号“小粒友”并联系客服。请勿相信任何其它联系方式。</p>
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
