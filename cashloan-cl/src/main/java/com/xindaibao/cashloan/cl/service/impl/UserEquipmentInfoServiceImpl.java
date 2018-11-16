package com.xindaibao.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.xindaibao.cashloan.cl.domain.UserEquipmentInfo;
import com.xindaibao.cashloan.cl.mapper.UserEquipmentInfoMapper;
import com.xindaibao.cashloan.cl.service.UserEquipmentInfoService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.mapper.UserMapper;

/**
 * 用户设备信息表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-17 17:32:05




 */
 
@Service("userEquipmentInfoService")
public class UserEquipmentInfoServiceImpl extends BaseServiceImpl<UserEquipmentInfo, Long> implements UserEquipmentInfoService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserEquipmentInfoServiceImpl.class);
   
    @Resource
    private UserEquipmentInfoMapper userEquipmentInfoMapper;
    @Resource
	private UserMapper userMapper;




	@Override
	public BaseMapper<UserEquipmentInfo, Long> getMapper() {
		return userEquipmentInfoMapper;
	}




	@Override
	public int saveOrUpdate(UserEquipmentInfo uei) {
		Map<String,Object> map = new HashMap<>();
		map.put("userId", uei.getUserId());
		UserEquipmentInfo uinfo = userEquipmentInfoMapper.findSelective(map);
		if (uinfo==null) {
			return userEquipmentInfoMapper.save(uei);
		}
		map.put("id", uinfo.getId());
		map.put("operatingSystem", uei.getOperatingSystem());
		map.put("systemVersions", uei.getSystemVersions());
		map.put("phoneType", uei.getPhoneType());
		map.put("phoneBrand", uei.getPhoneBrand());
		map.put("phoneMark", uei.getPhoneMark());
		map.put("versionName", uei.getVersionName());
		map.put("versionCode", uei.getVersionCode());
		map.put("mac", uei.getMac());
		return userEquipmentInfoMapper.updateSelective(map);
	}




	@Override
	public UserEquipmentInfo findSelective(long userId) {
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		return userEquipmentInfoMapper.findSelective(map);
	}




	@Override
	public void save(String loginName, String blackBox) {
		if (blackBox==null) {
			return;
		}
		Map<String,Object> map = new HashMap<>();
		map.put("loginName", loginName);
		User user = userMapper.findSelective(map);
		map = new HashMap<>();
		map.put("userId", user.getId());
		UserEquipmentInfo uinfo = userEquipmentInfoMapper.findSelective(map);
		if (user!=null) {
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("id", user.getId());
			paramMap.put("loginTime", DateUtil.getNow());
			userMapper.updateSelective(paramMap);
			if (uinfo==null) {
				UserEquipmentInfo uei = new UserEquipmentInfo();
				uei.setUserId(user.getId());
				uei.setBlackBox(blackBox);
				userEquipmentInfoMapper.save(uei);
			}else {
				map.put("blackBox", blackBox);
				map.put("id", uinfo.getId());
				userEquipmentInfoMapper.updateSelective(map);
			}
		}
	}
	
}