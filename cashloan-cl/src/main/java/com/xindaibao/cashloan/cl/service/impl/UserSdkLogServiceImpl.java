package com.xindaibao.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.UserSdkLog;
import com.xindaibao.cashloan.cl.mapper.UserSdkLogMapper;
import com.xindaibao.cashloan.cl.service.UserSdkLogService;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;

/**
 * sdk识别记录表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-20 09:47:04




 */
 
@Service("userSdkLogService")
public class UserSdkLogServiceImpl extends BaseServiceImpl<UserSdkLog, Long> implements UserSdkLogService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserSdkLogServiceImpl.class);
   
    @Resource
    private UserSdkLogMapper userSdkLogMapper;

	@Override
	public BaseMapper<UserSdkLog, Long> getMapper() {
		return userSdkLogMapper;
	}

	@Override
	public Map<String, Object> countDayTime(Map<String, Object> searchMap) {
		String sdk = Global.getValue("sdk_time");
		int faceTimes = JSONObject.parseObject(sdk).getIntValue("faceTime");
		int ocrTimes = JSONObject.parseObject(sdk).getIntValue("ocrTime");
		searchMap.put("timeType", "10");
		int faceTime = userSdkLogMapper.countDayTime(searchMap);
		searchMap.put("timeType", "20");
		int ocrTime = userSdkLogMapper.countDayTime(searchMap);
		Map<String, Object> times = new HashMap<String, Object>();
		times.put("faceTime", faceTimes-faceTime<0?0:faceTimes-faceTime);
		times.put("ocrTime", ocrTimes-ocrTime<0?0:ocrTimes-ocrTime);
		return times;
	}

	@Override
	public int save(UserSdkLog usl) {
		return userSdkLogMapper.save(usl);
	}
	
}