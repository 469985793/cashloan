package com.xindaibao.cashloan.cl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.xindaibao.cashloan.cl.mapper.KanyaUserSmsMapper;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserSms;
import com.xindaibao.cashloan.core.model.KanyaUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.UserMessages;
import com.xindaibao.cashloan.cl.mapper.UserMessagesMapper;
import com.xindaibao.cashloan.cl.service.UserMessagesService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 用户资料--联系人ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-04 11:54:57


 * 

 */
 
@Service("clUserMessagesService")
public class UserMessagesServiceImpl extends BaseServiceImpl<UserMessages, Long> implements UserMessagesService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserMessagesServiceImpl.class);
   
    @Resource
    private UserMessagesMapper clUserMessagesMapper;
    @Resource
    private KanyaUserSmsMapper kanyaUserSmsMapper;




	@Override
	public BaseMapper<UserMessages, Long> getMapper() {
		return clUserMessagesMapper;
	}




	@Override
	public Page<UserMessages> listMessages(long userId, int current,
			int pageSize) {
		PageHelper.startPage(current, pageSize);
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		List<UserMessages> list = clUserMessagesMapper.listSelective(searchMap);
		for (UserMessages clUserMessages : list) {
			if ("10".equals(clUserMessages.getType())) {
				clUserMessages.setType("发送");
			}else {
				clUserMessages.setType("接收");
			}
		}
		return (Page<UserMessages>)list;
	}

	@Override
	public KanyaUserSms listMessagesForKenya(long userId, int current,
												   int pageSize) {
		return kanyaUserSmsMapper.findByPrimary(userId);
	}
	
}