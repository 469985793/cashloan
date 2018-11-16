package com.xindaibao.cashloan.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.KanyaUserMapper;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.model.KanyaUserLocation;
import com.xindaibao.cashloan.core.model.ManagerUserModel;
import com.xindaibao.cashloan.core.model.UserWorkInfoModel;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 用户详情表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:08:04


 * 

 */
 
@Service("userBaseInfoService")
public class UserBaseInfoServiceImpl extends BaseServiceImpl<UserBaseInfo, Long> implements UserBaseInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserBaseInfoServiceImpl.class);
	
    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
	@Resource
	private KanyaUserMapper kanyaUserMapper;
 
	@Override
	public BaseMapper<UserBaseInfo, Long> getMapper() {
		return  userBaseInfoMapper;
	}
	
	@Override
	public UserBaseInfo findByUserId(Long userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		UserBaseInfo baseInfo = null;
		try {
			baseInfo = userBaseInfoMapper.findSelective(paramMap);
		} catch (Exception e) {
			logger.error("查询用户基本信息异常", e);
		}

		return baseInfo;
	}

	@Override
	public UserBaseInfo findSelective(Map<String, Object> paramMap) {
		return userBaseInfoMapper.findSelective(paramMap);
	}

	@Override
	public List<Map<String, Object>> getDictsCache(String type) {
		return userBaseInfoMapper.getDictsCache(type);
	}

	@Override
	public KanyaUserLocation getBaseModelByUserId(Long userId) {
		return kanyaUserMapper.getBaseModelByUserId(userId);
	}

	@Override
	public int updateState(long id, String state) {
		int i = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", id);
		UserBaseInfo base=userBaseInfoMapper.findSelective(paramMap);
		if (base != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", base.getId());
			map.put("state", state);
			i = userBaseInfoMapper.updateSelective(map);
		}
		return i;
	}

	@Override
	public int updateKenyaUserState(long id, String state) {
		int i = 0;
		KanyaUserLocation base=kanyaUserMapper.getBaseModelByUserId(id);
		if (base != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", base.getId());
			map.put("state", state);
			map.put("updatedTime",new Date());
			i = kanyaUserMapper.updateUserState(map);
		}
		return i;
	}

	@Override
	public boolean updateSelective(Map<String, Object> paramMap) {
		int result = userBaseInfoMapper.updateSelective(paramMap);
		if(result >0L){
			return true;
		}
		return false;
	}
	
	@Override
	public UserWorkInfoModel getWorkInfo(Long userId){
		return userBaseInfoMapper.findWorkInfo(userId);
	}


}