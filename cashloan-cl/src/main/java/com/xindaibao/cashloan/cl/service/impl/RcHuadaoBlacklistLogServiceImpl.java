package com.xindaibao.cashloan.cl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.RcHuadaoBlacklistLog;
import com.xindaibao.cashloan.cl.mapper.RcHuadaoBlacklistLogMapper;
import com.xindaibao.cashloan.cl.service.RcHuadaoBlacklistLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.rc.domain.TppBusiness;

import credit.CreditRequest;
import credit.Header;


/**
 * 华道黑名单记录表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-24 10:28:22



 */
 
@Service("rcHuadaoBlacklistLogService")
public class RcHuadaoBlacklistLogServiceImpl extends BaseServiceImpl<RcHuadaoBlacklistLog, Long> implements RcHuadaoBlacklistLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(RcHuadaoBlacklistLogServiceImpl.class);
   
    @Resource
    private RcHuadaoBlacklistLogMapper rcHuadaoBlacklistLogMapper;
    @Resource
    private UserBaseInfoService userBaseInfoService;

	@Override
	public BaseMapper<RcHuadaoBlacklistLog, Long> getMapper() {
		return rcHuadaoBlacklistLogMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int queryHuadaoBlackList(Long userId,TppBusiness business) {
		Map<String,Object> businessMap=JSONObject.parseObject(business.getExtend(),Map.class);
		final String APIHOST = business.getUrl();//测试接口地址
		final String APIKEY = businessMap.get("apikey")+"";
		final String SECRETKEY = businessMap.get("secretkey")+"";
		final String channelNo = businessMap.get("channelno")+"";
		final String interfaceName = businessMap.get("interfacename")+"";
		long timestamp = new Date().getTime();
		//获取手机号
		UserBaseInfo baseInfo=userBaseInfoService.findByUserId(userId);
		
		Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);
		CreditRequest creditRequest = new CreditRequest(APIHOST, header);
		Map<String, Object> payload = new HashMap<>();
		payload.put("Phone", baseInfo.getPhone());
		creditRequest.setPayload(payload);
		creditRequest.signByKey(SECRETKEY);
		String result=null;
		try {
			result = creditRequest.request();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
//		{
//		    "message": "请求成功",
//		    "res": {
//		        "status": "1",
//		        "IS_BLACK": "178****3620 为黑名单号码",
//		        "CODE": "200"
//		    },
//		    "orderNo": "201705230683714954",
//		    "code": 200
//		}
		RcHuadaoBlacklistLog rcHuadaoBlacklistLog=new RcHuadaoBlacklistLog();
		rcHuadaoBlacklistLog.setCreateTime(new Date());
		rcHuadaoBlacklistLog.setUserId(userId);
		if(result!=null&&!result.equals("")){
			Map<String,Object> resultMap=JSONObject.parseObject(result);
			String code=resultMap.get("code")+"";
			if(code.equals("200")){
				rcHuadaoBlacklistLog.setOrderNo(resultMap.get("orderNo")+"");
				rcHuadaoBlacklistLog.setRespCode(code);
				rcHuadaoBlacklistLog.setRespParams(result);
				if(resultMap.get("res")!=null){
					Map<String,Object> resMap=JSONObject.parseObject(resultMap.get("res")+"");
					rcHuadaoBlacklistLog.setIsBlack(resMap.get("status")+"");
				}
			}else{
				rcHuadaoBlacklistLog.setRespCode(code);
				rcHuadaoBlacklistLog.setRespParams(resultMap.get("message")+"");
			}
		}else{
			rcHuadaoBlacklistLog.setRespParams("第三方请求返回空值，请求参数如下：APIHOST:"+APIHOST+"---APIKEY:"+APIKEY+"---SECRETKEY:"+SECRETKEY
					+"---channelNo:"+channelNo+"---interfaceName:"+interfaceName+"---userId:"+userId+"---phone:"+baseInfo.getPhone());
		}
		return rcHuadaoBlacklistLogMapper.save(rcHuadaoBlacklistLog);
	}
	
}