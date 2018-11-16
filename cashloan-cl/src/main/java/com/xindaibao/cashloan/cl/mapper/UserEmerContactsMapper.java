package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.UserEmerContacts;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;


/**
 * 紧急联系人表Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:24:05


 * 

 */
@RDBatisDao
public interface UserEmerContactsMapper extends BaseMapper<UserEmerContacts,Long> {

	public List<UserEmerContacts> getUserEmerContactsByUserId(Map<String,Object> paramMap);

}
