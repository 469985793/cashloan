package com.xindaibao.cashloan.core.service;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.model.KanyaUserLocation;
import com.xindaibao.cashloan.core.model.ManagerUserModel;
import com.xindaibao.cashloan.core.model.UserWorkInfoModel;


/**
 * 用户详情表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:08:04


 * 

 */
public interface UserBaseInfoService extends BaseService<UserBaseInfo, Long> {

	/**
	 * 据userId查询用户基本信息
	 * 
	 * @param userId
	 * @return
	 */
	UserBaseInfo findByUserId(Long userId);

	/**
	 * 据条件查询用户基本信息
	 * 
	 * @param paramMap
	 * @return
	 */
	UserBaseInfo findSelective(Map<String, Object> paramMap);

	List<Map<String, Object>> getDictsCache(String type);
	
	/**
	 * 查询信息
	 * @param id
	 * @return
	 */
	KanyaUserLocation getBaseModelByUserId(Long userId);

	/**
	 * 添加取现黑名单
	 * @param id
	 * @param state
	 * @return
	 */
	int updateState(long id, String state);

	/**
	 * 添加取现黑名单
	 * @param id
	 * @param state
	 * @return
	 */
	int updateKenyaUserState(long id, String state);
	
	/**
	 * 修改用户基本信息
	 * 
	 * @param paramMap
	 * @return
	 */
	boolean updateSelective(Map<String, Object> paramMap);
	
	/**
	 * 查询用户工作信息
	 * @param userId
	 * @return
	 */
	UserWorkInfoModel getWorkInfo(Long userId);
	
}
