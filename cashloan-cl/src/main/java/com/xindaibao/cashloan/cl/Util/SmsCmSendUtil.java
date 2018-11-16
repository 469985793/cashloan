package com.xindaibao.cashloan.cl.Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import java.util.concurrent.*;

public class SmsCmSendUtil {
	private static final Log logger = LogFactory.getLog(SmsCmSendUtil.class);

	public static final String URL = "https://gw.cmtelecom.com/v1.0/message";

	public static final String protoken = "C97D728B-8B0E-4FA6-BEC5-7E316549EA92";//南非的 48876A96-E3DD-4927-BDA1-08AF217C3132
	public static final UUID productToken = UUID.fromString(protoken);
	public static final String from = "ANTELOPE";
	public static final String toArea = "00254";
	
//	public static final String[] numbers = {"+27766394420", "+27845065373", "+27825752006"};
//	public static final String content = "Verification Code[%s],Thank you very much for accepting this SMS test and wish you a happy life.";
	
	
	public static Map<String, String> WhiteList = new HashMap<String, String>();
	private static SmsCmSendUtil instance;
	
	public static SmsCmSendUtil getInstance() {
		if (instance == null) {
			synchronized (SmsCmSendUtil.class) {
				if (instance == null) {
					instance = new SmsCmSendUtil();
				}
			}
		}
		return instance;
	}
	
	private SmsCmSendUtil() {
	}
	
	public boolean send(String mobile, String message) {
		if(WhiteList.containsKey(mobile)) {
			return true;
		}
		List<String> mobileArr = new ArrayList<String>();
		mobileArr.add(toArea + mobile);

		return send(mobileArr, message);
	}

	public boolean send(String[] mobiles, String message) {
		List<String> mobileArr = new ArrayList<String>();//Arrays.asList(mobiles);
		for(String mobile: mobiles) {
			mobileArr.add(toArea + mobile);
		}
		return send(mobileArr, message);
	}

	public static void main(String[] args) {
		boolean sendrs = SmsCmSendUtil.getInstance().send("757629056", "Dear friend, please confirm that your M-Pesa account is fully funded and we advise that you repay it as soon as possible. Overdue repayment will increase additional charges and also impact your credit.[JumboPesa]");
		System.out.println(sendrs);
	}
	public boolean send(List<String> mobiles, String message) {
		ExecutorService exec = Executors.newCachedThreadPool();
		int timeout = 5; // 访问接口的时间限制 秒
		TradeCountThread task = new TradeCountThread(mobiles, message);
		Future<Boolean> future = exec.submit(task);
		boolean taskResult = false;
		try {
			// 等待计算结果，最长等待timeout秒，timeout秒后中止任务
			taskResult = future.get(timeout, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.error("主线程在等待返回结果时被中断！", e);
		} catch (ExecutionException e) {
			logger.error("主线程等待返回结果，但任务本身抛出异常！", e);
		} catch (TimeoutException e) {
			logger.error("主线程等待计算结果超时，因此中断任务线程！", e);
			exec.shutdownNow();
		} finally {
			exec.shutdown();
		}

		return taskResult;
	}

//	public static boolean send(List<String> mobiles, String message) {
//		try {
////			JSONObject numberjson = new JSONObject();
////	    	numberjson.put("number", mobile);
////	    	JSONArray tojson = new JSONArray();
////	    	tojson.add(numberjson);
//	    	
//	    	JSONArray tojson = new JSONArray();
//	        for (String number : mobiles) {
//	        	JSONObject numberjson = new JSONObject();
//	        	numberjson.put("number", number);
////	        	JSONObject tojson = new JSONObject();
//	        	tojson.add(numberjson);
//			}
//	    	
//	    	
//	    	JSONObject contentjson = new JSONObject(16, true);
//	    	contentjson.put("type", "AUTO");
//	    	contentjson.put("content", message);
//
//	    	JSONObject msgjson = new JSONObject(16, true);
//	    	msgjson.put("from", from);
//	    	msgjson.put("to", tojson);
//	    	msgjson.put("minimumNumberOfMessageParts", 1);
//	    	msgjson.put("maximumNumberOfMessageParts", 8);
//	    	msgjson.put("body", contentjson);
//	    	
//	    	JSONArray msgArr = new JSONArray();
//	    	msgArr.add(msgjson);
//	    	
//	    	JSONObject pjson = new JSONObject();
//	    	pjson.put("producttoken", productToken);
//	    	
//	    	JSONObject json = new JSONObject(16, true);
//	    	json.put("authentication", pjson);
//	    	json.put("msg", msgArr);
//
//	    	JSONObject jsonMsg = new JSONObject();
//	    	jsonMsg.put("messages", json);
//	    	
//	    	String response = HttpUtil.postJson(URL, jsonMsg);
//	    	JSONObject result = JSONObject.parseObject(response);
//	    	if (result.getIntValue("code") == 200) {
//				return true;
//			}
//	    	logger.error(response);
//		} catch (Exception e) {
//			logger.error("消息发送失败", e);
//		}
//    	return false;
//	}

	
	
	public class TradeCountThread implements Callable<Boolean> {
//        private String url;
//        private JSONObject content;
		private List<String> mobiles;
		private String message;
		
		public TradeCountThread(List<String> mobiles, String message) {
			this.mobiles = mobiles;
			this.message = message;
		}

		public Boolean call() throws Exception {
			try {
				JSONArray tojson = new JSONArray();
				for (String number : mobiles) {
					JSONObject numberjson = new JSONObject();
					numberjson.put("number", number);
//    	        	JSONObject tojson = new JSONObject();
					tojson.add(numberjson);
				}

				JSONObject contentjson = new JSONObject(16, true);
				contentjson.put("type", "AUTO");
				contentjson.put("content", message);

				JSONObject msgjson = new JSONObject(16, true);
				msgjson.put("from", from);
				msgjson.put("to", tojson);
				msgjson.put("minimumNumberOfMessageParts", 1);
				msgjson.put("maximumNumberOfMessageParts", 8);
				msgjson.put("body", contentjson);

				JSONArray msgArr = new JSONArray();
				msgArr.add(msgjson);

				JSONObject pjson = new JSONObject();
				pjson.put("producttoken", productToken);

				JSONObject json = new JSONObject(16, true);
				json.put("authentication", pjson);
				json.put("msg", msgArr);

				JSONObject jsonMsg = new JSONObject();
				jsonMsg.put("messages", json);

				String response = HttpUtil.postJson(URL, jsonMsg);
				JSONObject result = JSONObject.parseObject(response);
//				if (result.getIntValue("code") == 200) {
//					return true;
//				}
				if (result.getIntValue("code") == 0) {
					return true;
				}
				logger.error(response);
			} catch (Exception e) {
				logger.error("消息发送失败", e);
				throw e;
			}
			return false;
		}
	}

}
