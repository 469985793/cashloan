package com.xindaibao.cashloan.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.ShardTableUtil;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.mapper.UserMapper;
import com.xindaibao.cashloan.rc.domain.ContactCount;
import com.xindaibao.cashloan.rc.mapper.ContactCountMapper;
import com.xindaibao.cashloan.rc.mapper.RcUserContactsCountMapper;
import com.xindaibao.cashloan.rc.service.ContactCountService;


/**
 * 通讯录统计ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-10 15:04:13


 * 

 */
 
@Service("contactCountService")
public class ContactCountServiceImpl extends BaseServiceImpl<ContactCount, Long> implements ContactCountService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ContactCountServiceImpl.class);
   
    @Resource
    private ContactCountMapper contactCountMapper;
    @Resource
	private UserMapper userMapper;
    @Resource
    private RcUserContactsCountMapper rcUserContactsCountMapper;

	@Override
	public BaseMapper<ContactCount, Long> getMapper() {
		return contactCountMapper;
	}
	
	public int countContacts(Long userId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("tableName", ShardTableUtil.generateTableNameById("cl_user_contacts", userId, 30000));
		ContactCount cc = new ContactCount();
		cc.setUserId(userId);
		cc.setCount(rcUserContactsCountMapper.count(params));//通讯录总条数
		cc.setCountOne(rcUserContactsCountMapper.countSucceed(params));//通讯录借款未逾期人数
		cc.setCountTwo(rcUserContactsCountMapper.countFail(params));//通讯录借款逾期人数
		cc.setCountThree(rcUserContactsCountMapper.countNinety(params));//通讯录借款逾期大于90天人数
		cc.setCountFour(rcUserContactsCountMapper.countThirty(params));//通讯录借款逾期大于30天小于90天人数
		cc.setCountFive(rcUserContactsCountMapper.countWithinThirty(params));//通讯录借款逾期小于30天人数
		cc.setCreateTime(DateUtil.getNow());
		return contactCountMapper.save(cc);
	}

	@Override
	public int save() {
		int msg = 0;
		Map<String,Object> paramMap = new HashMap<>();
		List<User> list = userMapper.listSelective(paramMap);
		ContactCount cc = new ContactCount();
		
		for (User user : list) {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", user.getId());
			params.put("tableName", ShardTableUtil.generateTableNameById("cl_user_contacts", user.getId(), 30000));
			cc.setUserId(user.getId());
			cc.setCount(rcUserContactsCountMapper.count(params));//通讯录总条数
			cc.setCountOne(rcUserContactsCountMapper.countSucceed(params));//通讯录借款未逾期人数
			cc.setCountTwo(rcUserContactsCountMapper.countFail(params));//通讯录借款逾期人数
			cc.setCountThree(rcUserContactsCountMapper.countNinety(params));//通讯录借款逾期大于90天人数
			cc.setCountFour(rcUserContactsCountMapper.countThirty(params));//通讯录借款逾期大于30天小于90天人数
			cc.setCountFive(rcUserContactsCountMapper.countWithinThirty(params));//通讯录借款逾期小于30天人数
			cc.setCreateTime(DateUtil.getNow());
			msg = contactCountMapper.save(cc);
		}
		return msg;
	}
	
}