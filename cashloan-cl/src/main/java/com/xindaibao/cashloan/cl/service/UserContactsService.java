package com.xindaibao.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.UserContacts;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserAddressBook;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 用户资料--联系人Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-04 11:52:26


 * 

 */
public interface UserContactsService extends BaseService<UserContacts, Long>{

	/**
	 * 查询通讯录
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	KanyaUserAddressBook listContacts(long userId, int current, int pageSize);

	/**
	 * 保存前删除原有记录
	 * @param userId 
	 * @param userId
	 * @param clUserContacts 
	 * @return
	 */
	boolean deleteAndSave(List<Map<String, Object>> infos, String userId);

}
