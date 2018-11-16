package com.xindaibao.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.xindaibao.cashloan.cl.domain.RcJiGuangBlacklistLog;
import com.xindaibao.cashloan.cl.domain.UserEquipmentInfo;
import com.xindaibao.cashloan.cl.mapper.RcJiGuangBlacklistLogMapper;
import com.xindaibao.cashloan.cl.service.RcJiGuangBlacklistLogService;
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
@Service("rcJiGuangBlacklistLogService")
public class RcJiGuangBlacklistLogServiceImpl extends BaseServiceImpl<RcJiGuangBlacklistLog, Long>
		implements RcJiGuangBlacklistLogService
{

    private static final Logger logger = LoggerFactory.getLogger(RcJiGuangBlacklistLogServiceImpl.class);

    @Resource
    private RcJiGuangBlacklistLogMapper rcJiGuangBlacklistLogMapper;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private UserEquipmentInfoService userEquipmentInfoService;

	@Override
	public BaseMapper<RcJiGuangBlacklistLog, Long> getMapper() {
		return rcJiGuangBlacklistLogMapper;
	}




	@Override
	public  int queryJiGuangBlackList(Borrow borrow, TppBusiness business) {
		try {
			if(null == borrow || null == business){
				logger.error("jiguang blacklist borrow or business is null, borrow:"+borrow+ "business"+business);
				return 0;
			}
			Map<String,Object> businessMap=JSONObject.parseObject(business.getExtend(),Map.class);
			// FIXME: 2017/10/21 黑名单地址，
			final String APIHOST = business.getUrl();//测试接口地址 -- "https://api.data.jiguang.cn/v2/anti-fraud/users/blacklist/check"
			final String APIKEY = businessMap.get("apikey")+"";
			final String SECRETKEY = businessMap.get("secretkey")+"";
			final String channelNo = businessMap.get("channelno")+"";
			final String interfaceName = businessMap.get("interfacename")+"";
			Long userId = borrow.getUserId();

			UserEquipmentInfo userEquipmentInfo = userEquipmentInfoService.findSelective(userId);
			//获取手机号
			UserBaseInfo baseInfo=userBaseInfoService.findByUserId(userId);

			//请求
			String strResult = getRequest(APIHOST, headerGet(APIKEY, SECRETKEY), queryStr(userEquipmentInfo, baseInfo));

			//处理返回
			RcJiGuangBlacklistLog rcJiGuangBlacklistLog = processReturn(borrow, APIHOST, APIKEY, SECRETKEY, channelNo,
					interfaceName, userId, baseInfo, strResult);

			logger.info("BlackList"+strResult);
			return rcJiGuangBlacklistLogMapper.save(rcJiGuangBlacklistLog);
		} catch (Exception e){
			logger.error("jiguang blacklist error:{}",e);
		}
		return 0;
	}

	private StringBuilder queryStr(UserEquipmentInfo userEquipmentInfo, UserBaseInfo baseInfo) {
		StringBuilder queryString = new StringBuilder("?");
		queryString.append("phone").append("=").append(baseInfo.getPhone());
		queryString.append("&imei").append("=").append(userEquipmentInfo.getPhoneMark());
		queryString.append("&mac").append("=").append(userEquipmentInfo.getMac());
		return queryString;
	}

	private RcJiGuangBlacklistLog processReturn(Borrow borrow, String APIHOST, String APIKEY, String SECRETKEY,
												String channelNo, String interfaceName, Long userId, UserBaseInfo baseInfo,
												String strResult) {
		RcJiGuangBlacklistLog rcJiGuangBlacklistLog=new RcJiGuangBlacklistLog();
		rcJiGuangBlacklistLog.setCreateTime(new Date());
		rcJiGuangBlacklistLog.setUserId(borrow.getUserId());
		if(strResult!=null&&!strResult.equals("")){
            Map<String,Object> resultMap= JSONObject.parseObject(strResult);
            String code=resultMap.get("code")+"";
            if(code.equals("2000")){

                rcJiGuangBlacklistLog.setOrderNo(resultMap.get("req_id")+"");
                rcJiGuangBlacklistLog.setRespCode(code);
                rcJiGuangBlacklistLog.setRespParams(strResult);

                // FIXME: 2017/10/27 0 为黑名单 1为白名单
                if(resultMap.get("data")!=null){
                    Map<String,Object> resMap=JSONObject.parseObject(resultMap.get("data")+"");
					Double score = Double.parseDouble(resMap.get("score")+"");
                    rcJiGuangBlacklistLog.setScore(score.intValue());
                    rcJiGuangBlacklistLog.setHits(resMap.get("hits")+"");
                    rcJiGuangBlacklistLog.setDescription(resMap.get("description")+"");
                }else{
					rcJiGuangBlacklistLog.setScore(9999);
                }
            }else{
                rcJiGuangBlacklistLog.setRespCode(code);
                rcJiGuangBlacklistLog.setRespParams(strResult);
            }
        }else{
            rcJiGuangBlacklistLog.setRespParams("第三方请求返回空值，请求参数如下：APIHOST:"+APIHOST+"---APIKEY:"+APIKEY+"---SECRETKEY:"+SECRETKEY
                    +"---channelNo:"+channelNo+"---interfaceName:"+interfaceName+"---userId:"+userId+"---phone:"+baseInfo.getPhone());
        }
		return rcJiGuangBlacklistLog;
	}


	// FIXME: 2017/10/27 极光基础数据接口,第一版先不接
	public  int queryJiGuangUsers(Long userId) {
//		极光iAudience-基础标签API⽂档1.1.0
		try {
			//get·ÃÎÊÀý×Ó
			String strResult = getRequest(
					"https://api.data.jiguang.cn/v2/users",
					headerGet("",""), new StringBuilder());
			System.out.println("Users"+strResult);

			logger.info("Users"+strResult);
		}catch (Exception e){
			logger.error("jiguang users message error:{}",e);
		}

		return 0;
	}


	private static ImmutableMap<String, String> headerGet(String APIKEY, String SECRETKEY) throws UnsupportedEncodingException {
		return ImmutableMap.of("Authorization", autoHeaderGet(APIKEY, SECRETKEY));
	}

	private static String autoHeaderGet(String APIKEY, String SECRETKEY) throws UnsupportedEncodingException {
//		"bb5f4e7099b84958452ba10b:95cad21fb128f0060d09ee9d"
		String key = APIKEY +":" + SECRETKEY;
		String str = new BASE64Encoder().encode(key.getBytes("utf-8"));
		return  "Basic " + str;
	}


	public static String getRequest(String url, Map<String, String> headers,
									StringBuilder queryString) {
		StringBuilder response = new StringBuilder();
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();

		GetMethod method = new GetMethod(url + queryString.toString());
		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				method.addRequestHeader(entry.getKey(), entry.getValue());
			}
		}

		try {
			client.executeMethod(method);

			response.append(method.getResponseBodyAsString());
		} catch (IOException e) {
			logger.error("jiguang get request error :{}",e);
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}

}