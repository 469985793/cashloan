package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.UserAuth;
import com.xindaibao.cashloan.cl.model.UserAuthModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 用户Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-21 13:42:44


 * 

 */
@RDBatisDao
public interface UserAuthMapper extends BaseMapper<UserAuth,Long> {

	List<UserAuthModel> listUserAuthModel(Map<String, Object> params);

	int updateByUserId(Map<String, Object> paramMap);

//    public Map<String,Object> getAuthState(Map<String,Object> paramMap);

	Map<String,Object> executeSql(Map<String,Object> paramMap);
//	/**
//	 * 芝麻必填查询
//	 * 
//	 * @param authMap
//	 * @return
//	 */
//	Map<String, Object> getZmRequiredAuthState(Map<String, Object> authMap);
//
//	/**
//	 * 芝麻选填查询
//	 * 
//	 * @param authMap
//	 * @return
//	 */
//	Map<String, Object> getZmOptionalAuthState(Map<String, Object> authMap);
//
//	/**
//	 * 芝麻去除查询
//	 * 
//	 * @param authMap
//	 * @return
//	 */
//	Map<String, Object> getZmRemoveAuthState(Map<String, Object> authMap);
	

	int updatePhoneState(Map<String, Object> userAuth);

	

}
