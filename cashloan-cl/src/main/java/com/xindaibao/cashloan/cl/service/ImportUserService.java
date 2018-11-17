package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.AccfundInfo;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.model.KanyaUser;

import java.util.Date;

/**
 * 导入用户表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-16 10:56:12




 */
public interface ImportUserService extends BaseService<KanyaUser, Long>{
	/**
	 * 导入用户
	 * @param firstName
	 * @param lastName
	 * @param nationId
	 * @param mobile
	 */
	boolean saveUser(String firstName,String lastName,String nationId,String mobile);

}
