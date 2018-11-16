package com.xindaibao.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.UserMessages;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserSms;
import com.xindaibao.cashloan.core.common.service.BaseService;

import java.util.List;

/**
 * 用户资料--联系人Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-04 11:54:57


 * 

 */
public interface UserMessagesService extends BaseService<UserMessages, Long>{

	/**
	 * 短信查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UserMessages> listMessages(long userId, int current, int pageSize);
	/**
	 * 短信查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	KanyaUserSms listMessagesForKenya(long userId, int current, int pageSize);

}
