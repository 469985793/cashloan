package com.xindaibao.cashloan.cl.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.Util.SmsCmSendUtil;
import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.domain.Sms;
import com.xindaibao.cashloan.cl.domain.UrgeRepayOrder;
import com.xindaibao.cashloan.cl.mapper.BankCardMapper;
import com.xindaibao.cashloan.cl.mapper.BorrowRepayMapper;
import com.xindaibao.cashloan.cl.model.BorrowRepayModel;
import com.xindaibao.cashloan.cl.service.ClSmsService;
import com.xindaibao.cashloan.core.common.util.sms.SmsClientAccessTool;
import org.activiti.engine.impl.util.json.XML;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import smscredit.SmsCreditRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.SmsTpl;
import com.xindaibao.cashloan.cl.mapper.ClBorrowMapper;
import com.xindaibao.cashloan.cl.mapper.SmsMapper;
import com.xindaibao.cashloan.cl.mapper.SmsTplMapper;
import com.xindaibao.cashloan.cl.mapper.UrgeRepayOrderMapper;
import com.xindaibao.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.HttpUtil;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.common.util.code.MD5;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.mapper.UserMapper;

import credit.Header;

@Service("clSmsService")
public class ClSmsServiceImpl extends BaseServiceImpl<Sms, Long> implements ClSmsService {

	public static final Logger logger = LoggerFactory.getLogger(ClSmsServiceImpl.class);

	@Resource
	private SmsMapper smsMapper;
	@Resource
	private SmsTplMapper smsTplMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private BorrowRepayMapper borrowRepayMapper;
	@Resource
	private ClBorrowMapper clBorrowMapper;
	@Resource
	private UrgeRepayOrderMapper urgeRepayOrderMapper;
	@Resource
	private UserBaseInfoMapper userBaseInfoMapper;
	@Resource
	private BankCardMapper bankCardMapper;

	@Override
	public BaseMapper<Sms, Long> getMapper() {
		return smsMapper;
	}

	@Override
	public long findTimeDifference(String phone, String type) {
		int countdown = Global.getInt("sms_countdown");
		Map<String, Object> data = new HashMap<>();
		data.put("phone", phone);
		data.put("smsType", type);
		Sms sms = smsMapper.findTimeMsg(data);
		long times = 0;
		if (sms != null) {
			Date d1 = sms.getSendTime();
			Date d2 = DateUtil.getNow();
			long diff = d2.getTime() - d1.getTime();
			if (diff < countdown * 1000) {
				times = countdown - (diff / 1000);
			} else {
				times = 0;
			}
		}
		return times;
	}

	@Override
	public int countDayTime(String phone, String type) {
		String mostTimes = Global.getValue("sms_day_most_times");
		int mostTime = JSONObject.parseObject(mostTimes).getIntValue(type);

		Map<String, Object> data = new HashMap<>();
		data.put("phone", phone);
		data.put("smsType", type);
		int times = smsMapper.countDayTime(data);

		return mostTime - times;
	}

	@Override
	public String sendSms(String phone, String type) {
		Map<String, Object> search = new HashMap<>();
		search.put("type", type);
		search.put("state", "10");
		SmsTpl tpl = smsTplMapper.findSelective(search);
		if (tpl != null) {
			Map<String, Object> payload = new HashMap<>();
			int vcode = (int) (Math.random() * 9000) + 1000;
			payload.put("mobile", phone);
			payload.put("message", change(type) + vcode);
			String result = sendCode(payload, tpl.getNumber());
			logger.info("发送短信，phone：" + phone + ", vcode: "+vcode+"， type：" + type + "，同步响应结果：" + result);
			return result(result, phone, type, vcode);
		}
		logger.error("发送短信，phone：" + phone + "， type：" + type + "，没有获取到smsTpl");
		return null;
	}

	@Override
	public int smsBatch(String id) {
		final long[] ids = StringUtil.toLongs(id.split(","));
		new Thread() {
			public void run() {
				Map<String, Object> search = new HashMap<>();
				search.put("type", "overdue");
				search.put("state", "10");
				String smsNo = smsTplMapper.findSelective(search).getNumber();
				if (smsNo != null) {
					logger.info("开始批量发送逾期短信。。");
					for (int i = 0; i < ids.length; i++) {
						UrgeRepayOrder uro = urgeRepayOrderMapper.findById(ids[i]);
						Map<String, Object> map = new HashMap<>();
						map.put("platform", uro.getBorrowTime());
						map.put("loan", div(uro.getAmount(),100,0));
						map.put("time", uro.getRepayTime());
						map.put("overdueDay", uro.getPenaltyDay());
						map.put("amercement", div(uro.getPenaltyAmout(),100,0));
						map.put("phone", uro.getPhone());
                        map.put("borrowTime", uro.getBorrowTime());
                        map.put("amount",div(uro.getAmount(),100,0)+div(uro.getPenaltyAmout(),100,0)+div(uro.getAccountManage(),100,0)+div(uro.getProfit(),100,0));

						Map<String, Object> payload = new HashMap<>();
						payload.put("mobile", uro.getPhone());
						payload.put("message", changeMessage("overdue", map));
						logger.info("[模拟发送短信]"+payload.toString());
						boolean result = SmsCmSendUtil.getInstance().send(uro.getPhone(),changeMessage("overdue", map));
						logger.info("短信发送结果" + result);
					}
				} else {
					return ;
				}
			}
		}.start();
		return 1;
	}

	@Override
	public int verifySms(String phone, String type, String code) {
		if ("dev".equals(Global.getValue("app_environment")) && "0000".equals(code)) {
			return 1;
		}

		if (StringUtil.isBlank(phone) || StringUtil.isBlank(type) || StringUtil.isBlank(code)) {
			return 0;
		}

		if (!StringUtil.isPhone(phone)) {
			return 0;
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("phone", phone);
		data.put("smsType", type);
		Sms sms = smsMapper.findTimeMsg(data);
		if (sms != null) {
			String mostTimes = Global.getValue("sms_day_most_times");
			int mostTime = JSONObject.parseObject(mostTimes).getIntValue("verifyTime");

			data = new HashMap<>();
			data.put("verifyTime", sms.getVerifyTime() + 1);
			data.put("id", sms.getId());
			smsMapper.updateSelective(data);

			if (StringUtil.equals("20", sms.getState()) || sms.getVerifyTime() > mostTime) {
				return 0;
			}

			long timeLimit = Long.parseLong(Global.getValue("sms_time_limit"));

			Date d1 = sms.getSendTime();
			Date d2 = DateUtil.getNow();
			long diff = d2.getTime() - d1.getTime();
			if (diff > timeLimit * 60 * 1000) {
				return -1;
			}
			if (sms.getCode().equals(code)) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", sms.getId());
				map.put("state", "20");
				smsMapper.updateSelective(map);
				return 1;
			}
		}
		return 0;
	}

	protected String changeMessage(String smsType, Map<String, Object> map) {
		String message = "";
		if ("overdue".equals(smsType)) {
			message = ret(smsType);
			message = message.replace("{$platform}",StringUtil.isNull(DateUtil.dateStr8((Date) map.get("platform"))))
					.replace("{$loan}", StringUtil.isNull(map.get("loan")))
                    .replace("{$borrowTime}", StringUtil.isNull(DateUtil.dateStr8((Date) map.get("borrowTime"))))
                    .replace("{$amount}", StringUtil.isNull(map.get("amount")))
					.replace("{$time}",StringUtil.isNull(DateUtil.dateStr8((Date) map.get("time"))))
					.replace("{$overdueDay}", StringUtil.isNull(map.get("overdueDay")))
					.replace("{$amercement}", StringUtil.isNull(map.get("amercement")));
		}
		if ("loanInform".equals(smsType)) {
			message = ret(smsType);
			message = message.replace("{$time}", DateUtil.dateStr(DateUtil.valueOf(
					DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()), DateUtil.DATEFORMAT_STR_002)),
					"M月dd日"));
		}
		if ("repayInform".equals(smsType)) {
			message = ret(smsType);
			message = message.replace("{$time}",
					DateUtil.dateStr(DateUtil.valueOf(DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()),
							DateUtil.DATEFORMAT_STR_002)), "M月dd日"))
					.replace("{$loan}", StringUtil.isNull(map.get("loan")));
		}
		if ("repayBefore".equals(smsType)) {
			message = ret(smsType);
			message = message
					.replace("{$loan}",
							StringUtil.isNull(map.get("loan")))
					.replace("{$time}",
							DateUtil.dateStr(
									DateUtil.valueOf(DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()),
											DateUtil.DATEFORMAT_STR_002)),
									"M月dd日"))
					.replace("{$name}", StringUtil.isNull(map.get("name")))
					.replace("{$cardNo}", StringUtil.isNull(map.get("cardNo")))
					.replace("{$bankCardNo}", StringUtil.isNull(map.get("bankCardNo")));
		}
		//打款成功
		if ("loanInformSucess".equals(smsType)) {
			message = ret(smsType);
			message = message
					.replace("{$time}",
							DateUtil.dateStr(
									DateUtil.valueOf(DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()),
											DateUtil.DATEFORMAT_STR_002)),
									"M月dd日"));
		}
		//还款日前一天
		if ("frontRepayInform".equals(smsType)) {
			message = ret(smsType);
			message = message
					.replace("{$time}",
							DateUtil.dateStr(
									DateUtil.valueOf(DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()),
											DateUtil.DATEFORMAT_STR_002)),
									"M月dd日"));
		}
		//逾期一天、三天、七天、十四天、二十八天
		if ("overdue1".equals(smsType) || "overdue3".equals(smsType) || "overdue7".equals(smsType) || "overdue14".equals(smsType)|| "overdue28".equals(smsType)) {
			message = ret(smsType);
			message = message
					.replace("{$username}",
							StringUtil.isNull(map.get("username")))
					.replace("{$sex}",
							StringUtil.isNull(map.get("sex")));
		}
		//未逾期还款成功
		if("noRepayInform".equals(smsType)){
			message = ret(smsType);
		}
		//逾期还款成功
		if( "repayInformSucess".equals(smsType)){
			message = ret(smsType);
		}
		return message;
	}

	public String change(String code) {
		String message = null;
		if ("register".equals(code)) {
			message = ret("register");
		} else if ("findReg".equals(code)) {
			message = ret("findReg");
		} else if ("bindCard".equals(code)) {
			message = ret("bindCard");
		} else if ("findPay".equals(code)) {
			message = ret("findPay");
		} else if ("overdue".equals(code)) {
			message = ret("overdue");
		} else if ("loanInform".equals(code)) {
			message = ret("loanInform");
		} else if ("repayInform".equals(code)) {
			message = ret("repayInform");
		} else if ("repayBefore".equals(code)) {
			message = ret("repayBefore");
		}
		return message;
	}

	public String ret(String type) {
		Map<String, Object> search = new HashMap<>();
		search.put("type", type);
		SmsTpl tpl = smsTplMapper.findSelective(search);
		return tpl.getTpl();
	}

	private String result(String result, String phone, String type, int vcode) {
		String sms_passageway = Global.getValue("sms_passageway");
		String msg = null;


		//和信通 短信通道开启&短信类型为 overdue	逾期催收loanInform	放款通知repayInform	还款通知时，采用和信通通道
		String hxt_sms_passageway = Global.getValue("hxt_sms_passageway");
		if("1".equals(hxt_sms_passageway) && ("overdue".equals(type) || "loanInform".equals(type) || "repayInform".equals(type))){

			//开发环境默认返回成功
			if ("dev".equals(Global.getValue("app_environment"))) {
				return "000000";
			}
			//将xml转为json
			org.activiti.engine.impl.util.json.JSONObject xmlJSONObj = XML.toJSONObject(result);
			//设置缩进
			String jsonPrettyPrintString = xmlJSONObj.toString(4);
			org.activiti.engine.impl.util.json.JSONObject jsonObject= xmlJSONObj.getJSONObject("returnsms");
			msg = jsonObject.getString("taskID");
			return msg;
		}

		if (StringUtil.isBlank(sms_passageway)) {
			sms_passageway = "10";
		}

		if ("10".equals(sms_passageway)) {
			JSONObject resultJson = JSONObject.parseObject(result);

			Integer code;
			if (StringUtil.isNotBlank(resultJson)) {
				code = resultJson.getInteger("code");
				logger.info("发送短信，phone：" + phone + "， type：" + type + "，保存sms时code：" + code);
				Date now = DateUtil.getNow();
				Sms sms = new Sms();
				sms.setPhone(phone);
				sms.setSendTime(now);
				sms.setRespTime(now);
				sms.setSmsType(type);
				sms.setVerifyTime(0);

				if (code == 200) {
					JSONObject resJson = JSONObject.parseObject(StringUtil.isNull(resultJson.get("res")));
					JSONObject tempJson = JSONObject.parseObject(StringUtil.isNull(resultJson.get("tempParame")));
					String orderNo = StringUtil.isNull(resultJson.get("orderNo"));
					Integer tempCode = tempJson.getInteger("code");
					sms.setContent(resJson.getString("result"));
					sms.setResp("短信发送中");
					sms.setCode(StringUtil.isNull(tempCode));
					sms.setOrderNo(orderNo);
					sms.setState("30");
					int ms = smsMapper.save(sms);
					if (ms > 0) {
						msg = orderNo;
					}
					logger.info("ms" + ms);
				} else {
					String message = resultJson.getString("message");
					sms.setContent(message);
					sms.setResp("短信发送失败");
					sms.setCode("");
					sms.setOrderNo("");
					sms.setState("20");
					smsMapper.save(sms);
					BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_3, phone, message);
				}
			}
		}
		if ("20".equals(sms_passageway)) {
			Date now = DateUtil.getNow();
			Sms sms = new Sms();

			if (result.contains("\n")) {
				String[] results = result.split("\n");
				String[] temp = results[0].split(",");
				sms.setPhone(phone);
				sms.setSendTime(now);
				sms.setContent(result);
				SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
				try {
					now = s.parse(temp[0]);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				sms.setRespTime(now);
				sms.setResp("短信已发送");
				sms.setSmsType(type);
				sms.setCode(vcode + "");
				sms.setOrderNo(StringUtil.isNull(results[1]));
				sms.setState("10");
				sms.setVerifyTime(0);
				smsMapper.save(sms);
			} else {
				// String[] results=result.split(",");
				// String message = resultJson.getString("message");
				sms.setPhone(phone);
				sms.setSendTime(now);
				sms.setContent(result);
				sms.setRespTime(now);
				sms.setResp("短信发送失败");
				sms.setSmsType(type);
				sms.setCode("");
				sms.setOrderNo("");
				sms.setState("20");
				sms.setVerifyTime(0);
				smsMapper.save(sms);

				BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_3, phone, result);
			}
		}
		if ("30".equals(sms_passageway)) {
			JSONObject resultJson = JSONObject.parseObject(result);
			Integer code;
			if (StringUtil.isNotBlank(resultJson)) {
				code = resultJson.getInteger("result");
				logger.info("发送短信，phone：" + phone + "， type：" + type + "，保存sms时code：" + code);
				Date now = DateUtil.getNow();
				Sms sms = new Sms();
				sms.setPhone(phone);
				sms.setSendTime(now);
				sms.setRespTime(now);
				sms.setSmsType(type);
				sms.setVerifyTime(0);

				if (code == 0) {
					String orderNo = StringUtil.isNull(resultJson.get("msgid"));

					sms.setContent(result);
					sms.setResp("短信发送中");
					sms.setCode(vcode + "");
					sms.setOrderNo(orderNo);
					sms.setState("10");
					int ms = smsMapper.save(sms);
					if (ms > 0) {
						msg = orderNo;
					}
					logger.info("ms" + ms);
				} else {
					String message = resultJson.getString("message");
					sms.setContent(message);
					sms.setResp("短信发送失败");
					sms.setCode("");
					sms.setOrderNo("");
					sms.setState("20");
					smsMapper.save(sms);
					BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_3, phone, message);
				}
			}
		}

		return msg;
	}

	/**
	 * 判断短信发送渠道，默认大圣数据短信渠道
	 */
	private String sendCode(Map<String, Object> payload, String smsNo) {
		String sms_passageway = Global.getValue("sms_passageway");
		//开发环境默认返回成功
		if ("dev".equals(Global.getValue("app_environment"))) {
			logger.info("[模拟发送短信]"+payload.toString());
			return "{\"result\":\"0\",\"msgid\":\"000000\",\"failPhones\":\"\",\"desc\":\"提交成功\"}";
		}
		//逾期，催收等 走和信通通道
		if(payload.containsKey("send_type")){
			return hxtSendSms(payload);
		}else {
			if (StringUtil.isBlank(sms_passageway)) {
				sms_passageway = "30";
			}

			if ("10".equals(sms_passageway)) {
				return dsSendSms(payload, smsNo);
			}
			if ("20".equals(sms_passageway)) {
				String cl_sms_value = Global.getValue("cl_sms_value");
				@SuppressWarnings("unchecked")
				Map<String, Object> smsMap = JSONObject.parseObject(cl_sms_value, Map.class);
				payload.putAll(smsMap);
				if (payload.containsKey("url") && payload.containsKey("un") && payload.containsKey("pw")
						&& payload.containsKey("mobile") && payload.containsKey("message") && payload.containsKey("rd")
						&& payload.containsKey("ex")) {
					return clSendSms(payload);
				}
			}
			if ("30".equals(sms_passageway)) {
				String dh_sms_value = Global.getValue("dh_sms_value");
				@SuppressWarnings("unchecked")
				Map<String, Object> smsMap = JSONObject.parseObject(dh_sms_value, Map.class);
				payload.putAll(smsMap);
				if (payload.containsKey("dh_sms_account") && payload.containsKey("dh_sms_password")
						&& payload.containsKey("dh_sms_sign") && payload.containsKey("dh_sms_url")) {
					return dhSendSms(payload);
				}

			}
		}
		return "";
	}

	/**
	 * 和信通(逾期、催收)
	 * @param payload
	 * @return
	 */
	private String hxtSendSms(Map<String, Object> payload) {
		StringBuffer urlStr = new StringBuffer();
		try {
			urlStr.append("action="+Global.getValue("hxt_sms_action"))
                    .append("&userid="+Global.getValue("hxt_sms_userid"))
                    .append("&account="+Global.getValue("hxt_sms_account"))
                    .append("&password="+ Global.getValue("hxt_sms_password"))
                    .append("&msgid="+UUID.randomUUID().toString().replaceAll("-", ""))
                    .append("&mobile="+payload.get("mobile"))
                    .append("&content="+URLEncoder.encode(Global.getValue("hxt_sms_sign")+payload.get("message").toString(), "UTF-8"))
                    .append("&sendtime=")
                    .append("&extno=");
		} catch (Exception e) {
			logger.error("组装发送短信参数出现错误",e.getMessage());
		}
		String send = SmsClientAccessTool.getInstance().doAccessHTTPPost(
				Global.getValue("hxt_sms_url").toString(), urlStr.toString(), "utf-8");
		return send;
	}

	private String dhSendSms(Map<String, Object> payload) {


		JSONObject jsonParm = new JSONObject();
		jsonParm.put("account", payload.get("dh_sms_account"));
		jsonParm.put("password", MD5.encode(payload.get("dh_sms_password").toString()).toLowerCase());
		jsonParm.put("msgid", UUID.randomUUID().toString().replaceAll("-", ""));
		jsonParm.put("phones", payload.get("mobile"));
		jsonParm.put("content", payload.get("message"));
		jsonParm.put("sign", "【" + payload.get("dh_sms_sign") + "】");
		jsonParm.put("sendtime", "");
		JSONObject returnObj = HttpUtil.postClient(payload.get("dh_sms_url").toString(), jsonParm.toJSONString());

		return returnObj.toJSONString();
	}


	/**
	 * 创蓝短信
	 *
	 * @param url
	 *            应用地址，类似于http://ip:port/msg/
	 * @param un
	 *            账号
	 * @param pw
	 *            密码
	 * @param phone
	 *            手机号码，多个号码使用","分割
	 * @param msg
	 *            短信内容
	 * @param rd
	 *            是否需要状态报告，需要1，不需要0
	 */
	private String clSendSms(Map<String, Object> payload) {
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		GetMethod method = new GetMethod();
		String returnStr = "";
		try {
			URI base = new URI((String) payload.get("url"), false);
			method.setURI(new URI(base, "send", false));
			method.setQueryString(new NameValuePair[] { new NameValuePair("un", (String) payload.get("un")),
					new NameValuePair("pw", (String) payload.get("pw")),
					new NameValuePair("phone", (String) payload.get("mobile")),
					new NameValuePair("rd", (String) payload.get("rd")),
					new NameValuePair("msg", (String) payload.get("message")),
					new NameValuePair("ex", (String) payload.get("ex")), });
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				returnStr = URLDecoder.decode(baos.toString(), "UTF-8");
			} else {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			method.releaseConnection();
		}
		return returnStr;
	}

	// 大圣短信
	private String dsSendSms(Map<String, Object> payload, String smsNo) {
		final String APIKEY = Global.getValue("apikey");
		final String SECRETKEY = Global.getValue("secretkey");
		final String APIHOST = Global.getValue("sms_apihost");// 发送地址
		final String channelNo = Global.getValue("sms_channelNo");// 渠道编号
		final String interfaceName = Global.getValue("sms_interfaceName");// 接口名称

		long timestamp = new Date().getTime();
		Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);
		SmsCreditRequest creditRequest = new SmsCreditRequest(APIHOST, header, smsNo);
		creditRequest.setPayload(payload);
		creditRequest.signByKey(SECRETKEY);
		String result = null;
		try {
			result = creditRequest.request();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}


	@Override
	public int findUser(String phone) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginName", phone);
		User user = userMapper.findSelective(paramMap);
		if (StringUtil.isNotBlank(user)) {
			return 1;
		}
		return 0;
	}

	@Override
	public String loanInform(long userId, long borrowId) {
		Map<String, Object> search = new HashMap<>();
		User user = userMapper.findByPrimary(userId);
		search.put("borrowId", borrowId);
		Borrow br = clBorrowMapper.findByPrimary(borrowId);
		if (user != null && br != null) {
			search.put("type", "loanInformSucess");
			search.put("state", "10");
			SmsTpl tpl = smsTplMapper.findSelective(search);
			if (tpl != null) {
				search = new HashMap<>();
				search.put("time", DateUtil.dateStr(br.getCreateTime(), DateUtil.DATEFORMAT_STR_001));
				Map<String, Object> payload = new HashMap<>();
				payload.put("mobile", user.getLoginName());
				payload.put("message", changeMessage("loanInform", search));
				payload.put("send_type","loanInform");//发送短信类型 overdue	逾期催收loanInform	放款通知repayInform	还款通知
				String result = sendCode(payload, tpl.getNumber());
				logger.info("短信发送结果" + result);
				return result(result, user.getLoginName(), "loanInform", 0);
			}
		}
		return null;
	}

	@Override
	public String repayInform(long userId, long borrowId,String type) {
		Map<String, Object> search = new HashMap<>();
		User user = userMapper.findByPrimary(userId);
		search.put("borrowId", borrowId);
		Borrow br = clBorrowMapper.findByPrimary(borrowId);
		if (user != null && br != null) {
			search.put("type", type);
			search.put("state", "10");
			SmsTpl tpl = smsTplMapper.findSelective(search);
			if (tpl != null) {
				search = new HashMap<>();
				search.put("time", DateUtil.dateStr(br.getCreateTime(), DateUtil.DATEFORMAT_STR_001));
				search.put("loan", br.getAmount());
				Map<String, Object> payload = new HashMap<>();
				payload.put("mobile", user.getLoginName());
				payload.put("message", changeMessage(type, search));
				payload.put("send_type","repayInform");//发送短信类型 overdue	逾期催收loanInform	放款通知repayInform	还款通知
				String result = sendCode(payload, tpl.getNumber());
				logger.info("短信发送结果" + result);
				return result(result, user.getLoginName(), "repayInform", 0);
			}
		}
		return null;
	}


	@Override
	public int overdue(long borrowId) {
		Map<String, Object> search = new HashMap<>();
		search.put("type", "overdue");
		search.put("state", "10");
		String smsNo = smsTplMapper.findSelective(search).getNumber();
		if (smsNo != null) {
			BorrowRepayModel brm = borrowRepayMapper.findOverdue(borrowId);

			Map<String, Object> map = new HashMap<>();
			map.put("phone", brm.getPhone());
			map.put("platform", brm.getCreateTime());
			map.put("loan", brm.getAmount());
			map.put("time", brm.getRepayTime());
			map.put("overdueDay", brm.getPenaltyDay());
			map.put("amercement", brm.getPenaltyAmout());
			Map<String, Object> payload = new HashMap<>();
			payload.put("mobile", brm.getPhone());
			payload.put("message", changeMessage("overdue", map));
			String result = sendCode(payload, smsNo);
			logger.info("短信发送结果" + result);
			result(result, brm.getPhone(), "overdue", 0);
		} else {
			return 0;
		}
		return 1;
	}
	@Override
	public int overdue(long borrowId,String type) {
		Map<String, Object> search = new HashMap<>();
		search.put("type", type);
		search.put("state", "10");
		String smsNo = smsTplMapper.findSelective(search).getNumber();
		if (smsNo != null) {
			BorrowRepayModel brm = borrowRepayMapper.findOverdue(borrowId);

			UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(brm.getUserId());
			Map<String, Object> map = new HashMap<>();
			map.put("phone", brm.getPhone());
			map.put("platform", brm.getCreateTime());
			map.put("loan", brm.getAmount());
			map.put("time", brm.getRepayTime());
			map.put("overdueDay", brm.getPenaltyDay());
			map.put("amercement", brm.getPenaltyAmout());
			map.put("username",userBaseInfo.getRealName()==null?"":userBaseInfo.getRealName());
			if(StringUtil.isNotBlank(userBaseInfo.getSex())) {
				if ("男".equals(userBaseInfo.getSex()))
					map.put("sex", "先生");
				else if ("女".equals(userBaseInfo.getSex()))
					map.put("sex", "女士");
			}else{
				map.put("sex","");
			}
			Map<String, Object> payload = new HashMap<>();
			payload.put("mobile", brm.getPhone());
			payload.put("message", changeMessage("overdue", map));
			payload.put("send_type","overdue");//发送短信类型 overdue	逾期催收loanInform	放款通知repayInform	还款通知
			String result = sendCode(payload, smsNo);
			logger.info("短信发送结果" + result);
			result(result, brm.getPhone(), "overdue", 0);
		} else {
			return 0;
		}
		return 1;
	}
	@Override
	public String repayBefore(long userId, long borrowId) {
		Map<String, Object> search = new HashMap<>();
		search.put("borrowId", borrowId);
		BorrowRepay repay = borrowRepayMapper.findSelective(search);
		UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(userId);
		search.clear();
		search.put("userId", userId);
		BankCard bankCard = bankCardMapper.findSelective(search);
		if (baseInfo != null && repay != null && bankCard != null) {
			search.clear();
			search.put("type", "repayBefore");
			search.put("state", "10");
			SmsTpl tpl = smsTplMapper.findSelective(search);
			if (tpl != null) {
				search = new HashMap<>();
				search.put("time", DateUtil.dateStr(repay.getRepayTime(), DateUtil.DATEFORMAT_STR_001));// 还款时间
				search.put("cardNo", baseInfo.getPhone().substring(7, 11));// 手机尾号
				search.put("name", baseInfo.getRealName());// 姓名
				search.put("loan", repay.getAmount());// 待还款金额
				int len = bankCard.getCardNo().length();
				search.put("bankCardNo", bankCard.getCardNo().substring(len - 4, len));// 银行卡号
				Map<String, Object> payload = new HashMap<>();
				payload.put("mobile", baseInfo.getPhone());
				payload.put("message", changeMessage("repayBefore", search));
				String result = sendCode(payload, tpl.getNumber());
				logger.info("短信发送结果" + result);
				return result(result, baseInfo.getPhone(), "repayBefore", 0);
			}
		}
		return null;
	}

	@Override
	public Page<Sms> list(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Sms> list = smsMapper.listSelective(params);
		if (list != null && list.size() > 0) {
			int flag = 0;
			for (int i = 0; i < list.size(); i++) {
				if ("30".equals(list.get(i).getState())) {
					flag++;
					getReportByOrderNo(list.get(i).getOrderNo());
				}
			}
			if (flag != 0) {
				PageHelper.startPage(currentPage, pageSize);
				list = smsMapper.listSelective(params);
			}
		}
		return (Page<Sms>) list;
	}

	@SuppressWarnings("unused")
	public int getReportByOrderNo(String orderNo) {
		String sms_passageway = Global.getValue("sms_passageway");
		int status = 0;
		//大圣
		if ("10".equals(sms_passageway)) {
			String result = null;
			final String APIHOST = Global.getValue("sms_apihost_report");// 发送地址
			HttpGet request = new HttpGet(APIHOST + "/" + orderNo);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpClients.createDefault();
			HttpResponse httpResponse;
			try {
				httpResponse = httpClient.execute(request);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					HttpEntity httpEntity = httpResponse.getEntity();
					result = EntityUtils.toString(httpEntity);
				}
				httpClient.close();
			} catch (ClientProtocolException e) {
				logger.error(e.getMessage(), e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}

			JSONObject resultJson = JSONObject.parseObject(result);
			logger.info("短信发送结果" + resultJson);

			Integer code;

			if (StringUtil.isNotBlank(resultJson)) {
				code = resultJson.getInteger("code");
				logger.info("code:" + code);
				if (code == 200) {
					JSONArray array = JSON.parseArray(StringUtil.isNull(resultJson.get("data")));
					for (Object json : array) {
						JSONObject dataJson = JSONObject.parseObject(json.toString());
						Date reporyTime = dataJson.getDate("reportTime");
						status = dataJson.getIntValue("status");
						String reportMessage = dataJson.getString("reportMessage");
						String phone = dataJson.getString("mobile");
						Map<String, Object> paramMap = new HashMap<>();
						if (status == 1) {
							paramMap.put("orderNo", orderNo);
							paramMap.put("resp", "短信已发送");
							paramMap.put("state", "10");
							smsMapper.updateByOrderNo(paramMap);
						} else {
							paramMap.put("orderNo", orderNo);
							paramMap.put("resp", "短信发送失败");
							paramMap.put("state", "30");
							smsMapper.updateByOrderNo(paramMap);
						}

					}

				} else {
					if (code == 201) {
						status = 2;
						logger.info("短信报告获取结果：短信报告尚未获取成功");
					}
				}
			}
		}else if("20".equals(sms_passageway)){

		}else{//大汉三通
			String reportURL=Global.getValue("dh_sms_report");
			JSONObject dhSMSObj =JSONObject.parseObject(Global.getValue("dh_sms_value"));
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("account", dhSMSObj.getString("dh_sms_account"));
			jsonObj.put("password", MD5.encode(dhSMSObj.getString("dh_sms_password")).toLowerCase());
			jsonObj.put("msgid", orderNo);
			JSONObject retJSONObj = HttpUtil.postClient(reportURL, jsonObj.toJSONString());
			logger.info("sms response msg =========> " + retJSONObj.toString());
			if(retJSONObj!=null){
				if("0".equals(retJSONObj.getString("result"))){
					JSONArray array = JSON.parseArray(StringUtil.isNull(retJSONObj.getString("reports")));
					for (Object json : array) {
						JSONObject dataJson = JSONObject.parseObject(json.toString());
						Date reporyTime = dataJson.getDate("time");
						int dhStatus = dataJson.getIntValue("status");
						if(dhStatus==0){
							status=1;
						}else{
							status=0;
						}
						String reportMessage = dataJson.getString("desc");
						String phone = dataJson.getString("phone");
						Map<String, Object> paramMap = new HashMap<>();
						if (dhStatus == 0) {
							paramMap.put("orderNo", orderNo);
							paramMap.put("resp", "短信已发送");
							paramMap.put("state", "10");
							smsMapper.updateByOrderNo(paramMap);
						} else {
							paramMap.put("orderNo", orderNo);
							paramMap.put("resp", "短信发送失败");
							paramMap.put("state", "30");
							smsMapper.updateByOrderNo(paramMap);
						}

					}
				}else{
					status = 2;
					logger.info("短信报告获取结果：短信报告尚未获取成功");
				}
			}


		}
		return status;
	}
    public double div(double d1,double d2,int len) {// 进行除法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,len,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
