package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.UserAuth;
import com.xindaibao.cashloan.cl.model.UserAuthModel;
import com.xindaibao.cashloan.core.common.service.BaseService;


/**
 * 用户认证信息表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:18:17


 * 

 */
public interface UserAuthService extends BaseService<UserAuth, Long>{

	public UserAuth getUserAuth(Map<String,Object> paramMap);
	
	public Integer updateByUserId(Map<String,Object> paramMap);
	
	Page<UserAuthModel> listUserAuth(Map<String, Object> params, int currentPage,
                                     int pageSize);

	/**
	 * 查询认证状态
	 * @param userId
	 * @return
	 */
	public UserAuth findSelective(long userId);
	
	public Map<String, Object> getAuthState(Map<String, Object> paramMap);

	public int updatePhoneState(Map<String, Object> userAuth);
	/**
	 * 判断用户是否完成必须认证
	 * @param paramMap
	 * @return
	 */
	public boolean findAuthState(long userId);
}
