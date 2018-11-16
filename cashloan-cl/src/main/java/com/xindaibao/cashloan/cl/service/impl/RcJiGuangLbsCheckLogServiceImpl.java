package com.xindaibao.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.xindaibao.cashloan.cl.domain.RcJiGuangBlacklistLog;
import com.xindaibao.cashloan.cl.domain.RcJiGuangLbsCheckLog;
import com.xindaibao.cashloan.cl.domain.UserEquipmentInfo;
import com.xindaibao.cashloan.cl.mapper.RcJiGuangBlacklistLogMapper;
import com.xindaibao.cashloan.cl.mapper.RcJiGuangLbsCheckLogMapper;
import com.xindaibao.cashloan.cl.service.RcJiGuangBlacklistLogService;
import com.xindaibao.cashloan.cl.service.RcJiGuangLbsCheckLogService;
import com.xindaibao.cashloan.cl.service.UserEquipmentInfoService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;


/**
 * 极光记录表ServiceImpl
 */
@Service("rcJiGuangLbsCheckLogService")
public class RcJiGuangLbsCheckLogServiceImpl extends BaseServiceImpl<RcJiGuangLbsCheckLog, Long>
		implements RcJiGuangLbsCheckLogService
{

    private static final Logger logger = LoggerFactory.getLogger(RcJiGuangLbsCheckLogServiceImpl.class);

    @Resource
    private RcJiGuangLbsCheckLogMapper rcJiGuangLbsCheckLogMapper;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private UserEquipmentInfoService userEquipmentInfoService;

	@Override
	public BaseMapper<RcJiGuangLbsCheckLog, Long> getMapper() {
		return rcJiGuangLbsCheckLogMapper;
	}


	@Override
	public  int queryJiGuangLbsCheck(Borrow borrow, TppBusiness business) {
		Map<String,Object> businessMap=JSONObject.parseObject(business.getExtend(),Map.class);
		final String APIHOST = business.getUrl();//测试接口地址 -- "https://api.data.jiguang.cn/v2/anti-fraud/users/lbs_check"
		final String APIKEY = businessMap.get("apikey")+"";
		final String SECRETKEY = businessMap.get("secretkey")+"";
		final String channelNo = businessMap.get("channelno")+"";
		final String interfaceName = businessMap.get("interfacename")+"";
		String distanceRange = businessMap.get("distanceRange")+"";
		if(null == distanceRange){
			distanceRange = "0";
		}

		//获取手机号
		UserBaseInfo baseInfo=userBaseInfoService.findByUserId(borrow.getUserId());
		UserEquipmentInfo userEquipmentInfo = userEquipmentInfoService.findSelective(borrow.getUserId());
		try {
			// FIXME: 2017/10/22 分級查詢 ，需确认各级地址写法 ，目前 borrow 对象只有 address ；
			JSONObject jsonObject = lbsRequestData(baseInfo.getPhone(), borrow.getAddress(),
					borrow.getAddress(), borrow.getAddress(), borrow.getAddress(),
					borrow.getAddress(), userEquipmentInfo, distanceRange);

			String strResult = postRequest(
					APIHOST,
					headerPost(APIKEY, SECRETKEY),
					jsonObject.toJSONString());

			RcJiGuangLbsCheckLog rcJiGuangLbsCheckLog=new RcJiGuangLbsCheckLog();
			rcJiGuangLbsCheckLog.setCreateTime(new Date());
			rcJiGuangLbsCheckLog.setUserId(borrow.getUserId());
			if(strResult!=null&&!strResult.equals("")){
				Map<String,Object> resultMap=JSONObject.parseObject(strResult);
				String code=resultMap.get("code")+"";
				if(code.equals("2000")){

					rcJiGuangLbsCheckLog.setOrderNo(resultMap.get("req_id")+"");
					rcJiGuangLbsCheckLog.setRespCode(code);
					rcJiGuangLbsCheckLog.setRespParams(strResult);

					if(resultMap.get("data")!=null){
						Map<String,Object> resMap=JSONObject.parseObject(resultMap.get("data")+"");
						rcJiGuangLbsCheckLog.setIsBlack(resMap.get("match")+"");
					}
				}else{
					rcJiGuangLbsCheckLog.setRespCode(code);
					rcJiGuangLbsCheckLog.setRespParams(strResult);
				}
			}else{
				rcJiGuangLbsCheckLog.setRespParams("第三方请求返回空值，请求参数如下：APIHOST:"+APIHOST+"---APIKEY:"+APIKEY+"---SECRETKEY:"+SECRETKEY
						+"---channelNo:"+channelNo+"---interfaceName:"+interfaceName+"---userId:"+borrow.getUserId()+"---phone:"+baseInfo.getPhone());
			}
			logger.info("BlackList"+strResult);
			return rcJiGuangLbsCheckLogMapper.save(rcJiGuangLbsCheckLog);
		} catch (Exception e){
			logger.error("jiguang blacklist error:{}",e);
		}
		return 0;
	}


	private static ImmutableMap<String, String> headerPost(String APIKEY, String SECRETKEY) throws UnsupportedEncodingException {
		return ImmutableMap.of("Authorization", autoHeaderGet(APIKEY, SECRETKEY),
				"Content-Type", "application/json", "accept",
				"application/json");
	}

	private static String autoHeaderGet(String APIKEY, String SECRETKEY) throws UnsupportedEncodingException {
		String key = APIKEY +":" + SECRETKEY;
		String str = new BASE64Encoder().encode(key.getBytes("utf-8"));
		return  "Basic " + str;
	}

	private static JSONObject lbsRequestData(String phone, String province, String city, String district,
											 String town, String road, UserEquipmentInfo userEquipmentInfo, String distanceRange ) {
		JSONObject jsonObjectAddress = new JSONObject();
		jsonObjectAddress.put("province", province);
		jsonObjectAddress.put("city", city);
		jsonObjectAddress.put("district", district);
		jsonObjectAddress.put("town", town);
		jsonObjectAddress.put("road", road);


		JSONObject jsonObject = new JSONObject();
		jsonObject.put("phone", phone);
		jsonObject.put("address", jsonObjectAddress);
		jsonObject.put("mac", userEquipmentInfo.getMac());
		jsonObject.put("imei", userEquipmentInfo.getPhoneMark());
		jsonObject.put("imsi", userEquipmentInfo.getBlackBox());
		jsonObject.put("distance_range", Long.parseLong(distanceRange));
		return jsonObject;
	}


	public static String postRequest(String url, Map<String, String> headers,
									 String jsonStr) throws UnsupportedEncodingException {
		StringBuilder response = new StringBuilder();
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();

		PostMethod method = new PostMethod(url);
		RequestEntity requestEntity = new StringRequestEntity(jsonStr,
				"application/json", "UTF-8");
		method.setRequestEntity(requestEntity);
		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				method.addRequestHeader(entry.getKey(), entry.getValue());
			}
		}

		try {
			client.executeMethod(method);

			response.append(method.getResponseBodyAsString());
		} catch (IOException e) {
			logger.error("jiguang post request error :{}",e);
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}
}