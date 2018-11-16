package com.xindaibao.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.UserEmerContacts;
import com.xindaibao.cashloan.core.common.service.BaseService;


/**
 * 紧急联系人表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:24:05


 * 

 */
public interface UserEmerContactsService extends BaseService<UserEmerContacts, Long>{

	List<UserEmerContacts> getUserEmerContactsByUserId(Map<String,Object> paramMap);

	List<UserEmerContacts> getUserEmerContactsByUserId(Long userId);
}
