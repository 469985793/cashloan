package com.xindaibao.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.UserContacts;
import com.xindaibao.cashloan.cl.mapper.KanyaUserAddressBookMapper;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserAddressBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.mapper.UserContactsMapper;
import com.xindaibao.cashloan.cl.service.UserContactsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.ShardTableUtil;
import com.xindaibao.cashloan.core.common.util.StringUtil;


/**
 * 用户资料--联系人ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-04 11:52:26


 * 

 */
 
@Service("clUserContactsService")
public class UserContactsServiceImpl extends BaseServiceImpl<UserContacts, Long> implements UserContactsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserContactsServiceImpl.class);
   
    @Resource
    private UserContactsMapper userContactsMapper;
 	@Resource
    private KanyaUserAddressBookMapper kanyaUserAddressBookMapper;

	@Override
	public BaseMapper<UserContacts, Long> getMapper() {
		return userContactsMapper;
	}

	@Override
	public KanyaUserAddressBook listContacts(long userId, int current, int pageSize) {
		// 分表
		KanyaUserAddressBook kanyaUserAddressBook = new KanyaUserAddressBook();
//		String tableName = ShardTableUtil.generateTableNameById("cl_user_contacts", userId, 30000);
//		int countTable = userContactsMapper.countTable(tableName);
//		if (countTable == 0) {
//			userContactsMapper.createTable(tableName);
//		}
//
//		PageHelper.startPage(current, pageSize);
//		Map<String,Object> params = new HashMap<>();
//		params.put("userId", userId);
//		List<UserContacts> list = userContactsMapper.listShardSelective(tableName, params);

		kanyaUserAddressBook = kanyaUserAddressBookMapper.findByPrimary(userId);
		return kanyaUserAddressBook;
	}

	@Override
	public boolean deleteAndSave(List<Map<String, Object>> infos, String userId) {
		int msg = 0;
		String name = null;
		String phone = null;
		boolean flag = false;
		
		long userid = Long.parseLong(userId);
		
		// 分表
		String tableName = ShardTableUtil.generateTableNameById("cl_user_contacts", userid, 30000);
		int countTable = userContactsMapper.countTable(tableName);
		if (countTable == 0) {
			userContactsMapper.createTable(tableName);
		}
		
		userContactsMapper.deleteShardByUserId(tableName, userid);
		for (Map<String, Object> map : infos) {
			logger.debug("保存用户userId："+userId+"通讯录，name："+StringUtil.isNull(map.get("name"))+"，phone："+StringUtil.isNull(map.get("phone")));
			name = StringUtil.isNull(map.get("name")).replaceAll("(null)", "").replace("()", "");
			phone = StringUtil.isNull(map.get("phone")).replaceAll("-", "").replaceAll(" ", "");
			logger.debug("保存用户userId："+userId+"通讯录，name___："+name+"，phone___："+phone);
			if(StringUtil.isNotBlank(name) && name.length() <= 20 && StringUtil.isNotBlank(phone) && phone.length() <= 20){
				try {
					UserContacts userContacts = new UserContacts();
					userContacts.setUserId(userid);
					userContacts.setName(name);
					userContacts.setPhone(phone);
					msg = userContactsMapper.saveShard(tableName, userContacts);
				} catch (Exception e) {
					logger.error("保存用户userId："+userId+"通讯录异常， name： " + name + "， phone：" + phone);
				}
			} else {
				logger.error("保存用户userId："+userId+"通讯录失败，name： " + name + "， phone：" + phone);
			}
		}
		if (msg>0) {
			flag = true;
		}
		return flag;
	}
	
}