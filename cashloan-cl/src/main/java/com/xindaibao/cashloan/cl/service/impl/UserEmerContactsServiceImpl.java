package com.xindaibao.cashloan.cl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.collect.Maps;
import com.xindaibao.cashloan.cl.domain.UserEmerContacts;
import com.xindaibao.cashloan.cl.mapper.UserEmerContactsMapper;
import com.xindaibao.cashloan.cl.service.UserEmerContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;

/**
 * 紧急联系人表ServiceImpl
 */
@Service("userEmerContactsService")
public class UserEmerContactsServiceImpl extends BaseServiceImpl<UserEmerContacts, Long> implements UserEmerContactsService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserEmerContactsServiceImpl.class);
   
    @Resource
    private UserEmerContactsMapper userEmerContactsMapper;

	@Override
	public BaseMapper<UserEmerContacts, Long> getMapper() {
		return userEmerContactsMapper;
	}
	
	@Override
	public List<UserEmerContacts> getUserEmerContactsByUserId(Map<String,Object> paramMap) {
		return userEmerContactsMapper.listSelective(paramMap);
	}

    @Override
    public List<UserEmerContacts> getUserEmerContactsByUserId(Long userId) {
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("userId", userId);

        return getUserEmerContactsByUserId(paramMap);
    }
}