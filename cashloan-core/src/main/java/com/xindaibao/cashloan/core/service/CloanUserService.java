package com.xindaibao.cashloan.core.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.model.CloanUserModel;
import com.xindaibao.cashloan.core.model.KanyaUser;

/**
 * 用户Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-21 13:39:06


 * 

 */
public interface CloanUserService extends BaseService<KanyaUser, Long>{
	/**
	 * 查询用户详细信息列表
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<KanyaUser> listUser(Map<String, Object> params, int currentPage,
							 int pageSize);
	
	/**
	 * 查询用户详细信息
	 * @param id
	 * @return
	 */
	CloanUserModel getModelById(Long id);
	
	/**
	 * 查询所有相关的数据字典
	 * @return
	 */
	List<Map<String, Object>> findAllDic();


	/**
	 * 据uuid修改用户信息
	 * 
	 * @param paramMap
	 * @return
	 */
	boolean updateByUuid(Map<String, Object> paramMap);
	
	/**
	 * 据用户手机号查询用户
	 * @param phone
	 * @return
	 */
	User findByPhone(String phone);
	
	/**
	 * 今日注册用户数
	 * @return
	 */
	long todayCount();

	/**
	 * 修改登陆时间
	 * @param loginName
	 */
	void modify(String loginName);

}
