package com.xindaibao.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.xindaibao.cashloan.cl.domain.RcJiGuangLbsCheckLog;
import com.xindaibao.cashloan.cl.domain.RcJiGuangLbsFuzzyCheckLog;
import com.xindaibao.cashloan.cl.domain.UserEquipmentInfo;
import com.xindaibao.cashloan.cl.mapper.RcJiGuangLbsCheckLogMapper;
import com.xindaibao.cashloan.cl.mapper.RcJiGuangLbsFuzzyCheckLogMapper;
import com.xindaibao.cashloan.cl.service.RcJiGuangLbsCheckLogService;
import com.xindaibao.cashloan.cl.service.RcJiGuangLbsFuzzyCheckLogService;
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
@Service("rcJiGuangLbsFuzzyCheckLogService")
public class RcJiGuangLbsFuzzyCheckLogServiceImpl extends BaseServiceImpl<RcJiGuangLbsFuzzyCheckLog, Long>
		implements RcJiGuangLbsFuzzyCheckLogService
{

    private static final Logger logger = LoggerFactory.getLogger(RcJiGuangLbsFuzzyCheckLogServiceImpl.class);

    @Resource
    private RcJiGuangLbsFuzzyCheckLogMapper rcJiGuangLbsFuzzyCheckLogMapper;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private UserEquipmentInfoService userEquipmentInfoService;

	@Override
	public BaseMapper<RcJiGuangLbsFuzzyCheckLog, Long> getMapper() {
		return rcJiGuangLbsFuzzyCheckLogMapper;
	}



	@Override
	public  int queryJiGuangLbsFuzzyCheck(Borrow borrow, TppBusiness business) {
		Map<String,Object> businessMap=JSONObject.parseObject(business.getExtend(),Map.class);
		final String APIHOST = business.getUrl();//测试接口地址 -- "https://api.data.jiguang.cn/v2/anti-fraud/users/lbs_fuzzy_check"
		final String APIKEY = businessMap.get("apikey")+"";
		final String SECRETKEY = businessMap.get("secretkey")+"";
		final String channelNo = businessMap.get("channelno")+"";
		final String interfaceName = businessMap.get("interfacename")+"";
		String distanceRange = businessMap.get("distanceRange")+"";
		if(null == distanceRange){
			distanceRange = "0";
		}

		try {
			//获取手机号
			UserBaseInfo baseInfo=userBaseInfoService.findByUserId(borrow.getUserId());
			UserEquipmentInfo userEquipmentInfo = userEquipmentInfoService.findSelective(borrow.getUserId());

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("phone", baseInfo.getPhone());
			jsonObject.put("address_txt", borrow.getAddress());
			jsonObject.put("distance_range", Long.parseLong(distanceRange));
			jsonObject.put("mac", userEquipmentInfo.getMac());
			jsonObject.put("imei", userEquipmentInfo.getPhoneMark());
			jsonObject.put("imsi", userEquipmentInfo.getBlackBox());

			String strResult = postRequest(
					APIHOST,
					headerPost(APIKEY, SECRETKEY),
					jsonObject.toJSONString());

			RcJiGuangLbsFuzzyCheckLog rcJiGuangLbsFuzzyCheckLog=new RcJiGuangLbsFuzzyCheckLog();
			rcJiGuangLbsFuzzyCheckLog.setCreateTime(new Date());
			rcJiGuangLbsFuzzyCheckLog.setUserId(borrow.getUserId());
			if(strResult!=null&&!strResult.equals("")){
				Map<String,Object> resultMap=JSONObject.parseObject(strResult);
				String code=resultMap.get("code")+"";
				if(code.equals("2000")){

					rcJiGuangLbsFuzzyCheckLog.setOrderNo(resultMap.get("req_id")+"");
					rcJiGuangLbsFuzzyCheckLog.setRespCode(code);
					rcJiGuangLbsFuzzyCheckLog.setRespParams(strResult);

					if(resultMap.get("data")!=null){
						// FIXME: 2017/10/26 match字段表示是否命中, 即指定的设备标识在极光数据中的地址信息与输入的地址
						// 距离是否在指定的距离阈值之内, 如符合为1,不符合为0 为黑名单

						Map<String,Object> resMap=JSONObject.parseObject(resultMap.get("data")+"");
						rcJiGuangLbsFuzzyCheckLog.setIsBlack(resMap.get("match")+"");
					}
				}else{
					rcJiGuangLbsFuzzyCheckLog.setRespCode(code);
					rcJiGuangLbsFuzzyCheckLog.setRespParams(strResult);
				}
			}else{
				rcJiGuangLbsFuzzyCheckLog.setRespParams("第三方请求返回空值，请求参数如下：APIHOST:"+APIHOST+"---APIKEY:"+APIKEY+"---SECRETKEY:"+SECRETKEY
						+"---channelNo:"+channelNo+"---interfaceName:"+interfaceName+"---userId:"+borrow.getUserId()+"---phone:"+baseInfo.getPhone());
			}
			logger.info("BlackList"+strResult);
			return rcJiGuangLbsFuzzyCheckLogMapper.save(rcJiGuangLbsFuzzyCheckLog);
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