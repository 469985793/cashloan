package com.xindaibao.cashloan.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.UserOtherInfo;
import com.xindaibao.cashloan.core.mapper.UserOtherInfoMapper;
import com.xindaibao.cashloan.core.service.UserOtherInfoService;


/**
 * 用户更多信息ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 19:37:25


 * 

 */
 
@Service("userOtherInfoService")
public class UserOtherInfoServiceImpl extends BaseServiceImpl<UserOtherInfo, Long> implements UserOtherInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserOtherInfoServiceImpl.class);
   
    @Resource
    private UserOtherInfoMapper userOtherInfoMapper;

	@Override
	public UserOtherInfo getInfoByUserId(Long userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		UserOtherInfo otherInfo = null;
		try {
			otherInfo = userOtherInfoMapper.findSelective(paramMap);
		} catch (Exception e) {
			logger.error("查询用户userId："+userId+"其他信息异常", e);
		}
		return otherInfo;
	}

	@Override
	public boolean save(UserOtherInfo otherInfo) {
		otherInfo.setCreateTime(DateUtil.getNow());
		int result = userOtherInfoMapper.save(otherInfo);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(UserOtherInfo otherInfo) {
		int result = userOtherInfoMapper.update(otherInfo);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateSelective(Map<String, Object> paramMap) {
		int result = userOtherInfoMapper.updateSelective(paramMap);
		if (result > 0L) {
			return true;
		}
		return false;
	}


	@Override
	public BaseMapper<UserOtherInfo, Long> getMapper() {
		return userOtherInfoMapper;
	}
}