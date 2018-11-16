package com.xindaibao.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.service.UserCardCreditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.NumberUtil;

import com.xindaibao.cashloan.cl.domain.UserCardCreditLog;
import com.xindaibao.cashloan.cl.mapper.UserCardCreditLogMapper;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.StringUtil;


/**
 * 人脸识别请求记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-10 14:37:56


 * 

 */
 
@Service("userCardCreditLogService")
public class UserCardCreditLogServiceImpl extends BaseServiceImpl<UserCardCreditLog, Long> implements UserCardCreditLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserCardCreditLogServiceImpl.class);
   
    @Resource
    private UserCardCreditLogMapper userCardCreditLogMapper;

	@Override
	public BaseMapper<UserCardCreditLog, Long> getMapper() {
		return userCardCreditLogMapper;
	}

	@Override
	public boolean isCanCredit(Long userId) {
		boolean result=true; 
		String daysMostTimes = Global.getValue("idCardCredit_day_most_times");
		if(StringUtil.isNotBlank(daysMostTimes)){
			int times = NumberUtil.getInt(daysMostTimes);
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("userId", userId);
		    int count=userCardCreditLogMapper.countByUserId(paramMap);
			if(count>=times){
				logger.error("用户"+userId+"今天请求人脸识别次数超过"+times+",请明日再来认证");
				result=false;
			}
		}
		return result;
	}
	
}