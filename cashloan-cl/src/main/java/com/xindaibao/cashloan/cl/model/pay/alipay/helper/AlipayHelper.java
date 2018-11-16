package com.xindaibao.cashloan.cl.model.pay.alipay.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.xindaibao.cashloan.core.common.context.Global;

/**
 * 支付宝支付
 * @author
 * @version 1.0
 * @date 2017年6月23日 上午10:38:39



 */
public class AlipayHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(AlipayHelper.class);
	
	public static String appPay(double amount, String body, String orderNo) {
		String respBody = "";
		//String outTradeNo = OrderNoUtil.getSerialNumber();
		String appId = Global.getValue("alipay_app_id");
		String appPrivateKey = Global.getValue("alipay_private_key");
		String charSet = "UTF-8";
		String alipayPubKey = Global.getValue("alipay_public_key");
		String url = Global.getValue("alipay_sub_url");
		/*String appId = "2017062307554484";
		String appPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAdJdYbntt7njmy+So5ihnv7UnXPv7eUre1nDGGDF18Wt/M+2m4ZF1o8mT+ySSPyTvIKP0crBjpCC8D9u3ifgtHrIrwLsIJ9re8g83AEVJho38n/Fj2L7rpeIcxsgEblplkUDDCARljvlrBIpnPrZGQzJI62U2xQvJb+Hz747Kb3gtfZoR9MauHQgJZKHW/LYpm79hi2QB9aDDcdLHLU2z9HYe81HzcbCBGio0t+mlQqKEVO0y/lP5EAvjDpTfQ37pHfKmR4WL/w+R8eoflA0KNHK9L2+3g0Csq0UBnzo/FVKjl+RYsm+6XOpPldBDrUEiXfayh4BDR9hwLoML2QufAgMBAAECggEASly0XJIgzAP4Y4t3RLtwepcFVNNEYFDYIeY8BMFuqJtooou6MRh174cwo4UIp8z8IZ5gq5tVN2mFNblsdXTegag6do+s9ky0LMouOqXidmz5d1xUDnwkEiDGo+gDVGVZ2WBcAEt82BoLk4NeZ+Rq+oPd8U6sGie56hPl1yRAIjnREjNihPOtaBIcCcWVGysKtVlLhUXf2kD4w6frwD3NhVDjpnwqZu3vNrLwbh/SiimP+ln6d1uM8b1mcw0fAF7zljIWEy4d0jgsVShs7S1GJK16yTVluxclXc9Wj9QOohH4ou4ul6wdqC9fpt6+MbexeZ4ndMZmb2mNtXyMufCkUQKBgQDBUf0fjLcrF90SZHMEPZGNKgg24SXpWd8D4AUQz767YbnB1/fplP5GUheEGOjePnXIqGGR9Nen6DNxR7QJ7mU6hquh70yUGkYk9oI3TvsyGEbU9oKkRzeD4cjYs+cAOvm4xOVM8iRSCPa+7r3hafG2O+QtZt8Vr5w28R+PRzAVxwKBgQCqGq1hJ1hvINEMG62uTSz1/y2cSCpVvTyaJQOsZKOoOS3ez+TDZPk70qWr+oqBAcKE9eVNX8EeGzlNXPluC0WuqNBBLE645X4BaX3eN/Mp+V+YStVsPvUrIXq3LnCjLzbx0dunezY0f4GwBANY3DCDH/fp0CSGXXI6QbM3Vgr7aQKBgGsMPX/06ZChPDdtDAtzFF/4wGezMM0QiN+acvOmNdWI/dWLA6qCaHMEDiWFrZ0zNQGRy8WIsMo3WBUIQXemXMOVwwB+hAcWrXTWU7DQuWQ58S8H+buB/Am5QwBMTkPgZFueG+MJynbqo8eCZ/6kLcZdT7KcXjeh8Hyz9GfgYY4NAoGAVDcM63KNR8dZd8MkQ40V21xQenUH8JbVsALvRKQ2mgxk91C5kzskYUeymehaNfBnG2iZ86qac4tK3VBQdPjDsOsG351OBCAqOAzAMgpFKty5RS6XZxXsT0Qt+w7U2RWBAEvDkbFgbrFJxZjXB2FL6z8if/F6g8Mz0D/CuHyyxzECgYEAj/QkLK+QA+9uVt2CJM7dWdYNSRffbbDBI1Q4b9HF5hU6BiiWRgsCyfDVk+Gm4Pu7EI1rvuhz7I6p9PAEIf4yZkSECcQlWCh4qWAz1i3vvU65nT1yUoYjciycgfCiQbR0FE2m34KcwSQp7HmxDz0F1pIk3bXSSGnLjcPuJklnAAE=";
		String charSet = "UTF-8";
		String alipayPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAisBMBNzU8NygYpzZwTakWkEzhehdrNErvKpLtciuuJq3KIonSvX1VSe4g+BWCZ6/t65upzDno4hmjbkYthGms6kkOnhvQNecH1aFSTESl6avARl8XfqQoPPEvpUHFJEufqeLQl7WeCgXAGNRw5NllCXO1zSbS7SptI0NR+w7aWkkP93kzHX/66TpGnS5x09YV3kq/eOw8f7VPeXLs88WMF6oKK8HTfsoHIVNdVVAzJYQWkd8m3anT682VK16kvUKByoWI4YyXTDN345GZ02blpQxC8XG4BmoteNqxWNtBuI69DSm5wNo6bfsbBaQwgeDo3BDFREMEBRDv4UKbIsxPwIDAQAB";
		String url = "https://openapi.alipay.com/gateway.do";*/
		
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(url, appId,
				appPrivateKey, "json", charSet, alipayPubKey, "RSA2");
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		//model.setBody(body);
		model.setSubject(body);
		model.setOutTradeNo(orderNo);
		model.setTimeoutExpress("30m");
		model.setTotalAmount(String.valueOf(amount));
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(Global.getValue("server_host") + "/api/pay/alipay/notify.htm");
		try {
	        //这里和普通的接口调用不同，使用的是sdkExecute
	        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
	        respBody = response.getBody();
	        logger.info("支付宝SDK封装信息：" + respBody);
	    } catch (AlipayApiException e) {
	    	logger.info("支付宝SDK封装异常：", e);
	    }
		return respBody;
	}
	
}
