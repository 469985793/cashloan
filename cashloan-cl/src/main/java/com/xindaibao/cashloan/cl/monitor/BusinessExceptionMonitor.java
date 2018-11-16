package com.xindaibao.cashloan.cl.monitor;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.xindaibao.cashloan.core.common.util.DateUtil;

public class BusinessExceptionMonitor {

	//private static Logger logger = LoggerFactory.getLogger(BusinessExceptionMonitor.class);
	//private static Logger logger = LoggerFactory.getLogger("businessExceptionMonitor");
	private static Logger logger = Logger.getLogger(BusinessExceptionMonitor.class);
	
	private static Map<String, Integer> exception = new HashMap<String, Integer>();
	
	/** 芝麻信用 - 征信分数查询  */
	public static String TYPE_1 = "Zmxy-CreditScoreQuery";
	
	/** 芝麻信用 - 黑名单查询  */
	public static String TYPE_2 = "Zmxy-BlackQuery";
	
	/** 大圣数据 - 短信  */
	public static String TYPE_3 = "Dsdata-Sms";
	
	/** 大圣数据 - 浅橙贷前审核 */
	public static String TYPE_4 = "Dsdata-QcRisk";
	
	/** 大圣数据 - 上树运营商  */
	public static String TYPE_5 = "Dsdata-ShangShu";
	
	/** 大圣数据 - 人脸识别（face++）  */
	public static String TYPE_6 = "Dsdata-face++";
	
	/** 大圣数据 - 人脸识别（小视）  */
	public static String TYPE_7 = "Dsdata-XiaoShi";
	
	/** 大圣数据 - 人脸识别（商汤）  */
	public static String TYPE_8 = "Dsdata-ShangTang";
	
	/** 连连支付  */
	public static String TYPE_9 = "LianLianPay";
	
	/** 同盾贷前审核 */
	public static String TYPE_10 = "TongdunApply";
	
	/**
	 * 增加监控信息
	 * @param type
	 * @param orderNo
	 * @param msg
	 */
	public static void add(String type, Object keyInfo, String msg) {
		String date = DateUtil.dateStr2(DateUtil.getNow());
		String key = date + "_" + type;
		Integer count = exception.get(key);
		count = count == null ? 0 : count;
		int newCount = count + 1;
		exception.put(key, newCount);
		logger.warn(type + "：" + " 关键信息, " + keyInfo + ", 提示信息：" + msg + ", 今日异常次数：" + newCount);
		
		// 每增加n次发送一次推送消息
		if (newCount % 10 == 0) {
			// 推送
		}
	}
}
