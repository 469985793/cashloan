package com.xindaibao.cashloan.core.service;

import java.util.Map;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.UserOtherInfo;

/**
 * 用户更多信息Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 19:37:25


 * 

 */
public interface UserOtherInfoService extends BaseService<UserOtherInfo, Long> {
	
	/**
	 * 据用户主键获取用户其他信息
	 * 
	 * @param userId
	 * @return
	 */
	UserOtherInfo getInfoByUserId(Long userId);

	/**
	 * 保存用户其他信息
	 * 
	 * @param otherInfo
	 * @return
	 */
	boolean save(UserOtherInfo otherInfo);

	/**
	 * 修改用户其他信息
	 * 
	 * @param otherInfo
	 * @return
	 */
	boolean update(UserOtherInfo otherInfo);

	/**
	 * 修改用户其他信息
	 * 
	 * @param paramMap
	 * @return
	 */
	boolean updateSelective(Map<String, Object> paramMap);
	
}
