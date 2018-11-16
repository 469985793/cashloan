package com.xindaibao.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.UserInvite;
import com.xindaibao.cashloan.cl.mapper.ProfitAgentMapper;
import com.xindaibao.cashloan.cl.model.InviteBorrowModel;
import com.xindaibao.cashloan.cl.model.ManageAgentModel;
import com.xindaibao.cashloan.cl.model.ManageProfitModel;
import com.xindaibao.cashloan.cl.service.UserInviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.ProfitAgent;
import com.xindaibao.cashloan.cl.mapper.ProfitCashLogMapper;
import com.xindaibao.cashloan.cl.mapper.UserInviteMapper;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.mapper.UserMapper;

/**
 * 邀请记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 15:54:41


 * 

 */
 
@Service("userInviteService")
public class UserInviteServiceImpl extends BaseServiceImpl<UserInvite, Long> implements UserInviteService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserInviteServiceImpl.class);
   
    @Resource
    private UserInviteMapper userInviteMapper;
    @Resource
    private ProfitAgentMapper profitAgentMapper;
    @Resource
    private ProfitCashLogMapper profitCashLogMapper;
    @Resource
    private UserMapper userMapper;
    

	@Override
	public BaseMapper<UserInvite, Long> getMapper() {
		return userInviteMapper;
	}

	@Override
	public Map<String, Object> findPhone(long userId) {
		boolean isPhone = true;
		String phone = Global.getValue("phone");
		Map<String,Object> phoneMap = new HashMap<String, Object>();
		phoneMap.put("phone",phone);
		Map<String,Object> inviteMap = new HashMap<String, Object>();
		inviteMap.put("inviteId", userId);
		UserInvite ui = userInviteMapper.findSelective(inviteMap);
		Map<String,Object> profitMap = new HashMap<String, Object>();
		ProfitAgent pa;
		if (StringUtil.isNotBlank(ui)) {
			profitMap.put("userId", ui.getUserId());
			pa = profitAgentMapper.findSelective(profitMap);
			if (StringUtil.isNotBlank(pa)&&pa.getLevel()!=3) {
				phoneMap.put("phone", ui.getUserName());
			}
		}
		profitMap.put("userId", userId);
		pa = profitAgentMapper.findSelective(profitMap);
		if (StringUtil.isNotBlank(pa)&&pa.getLevel()==1) {
			isPhone = false;
		}
		phoneMap.put("isPhone", isPhone);
		return phoneMap;
	}

	@Override
	public Page<ManageProfitModel> findAgent(String phone , String userName, int current, int pageSize) throws ServiceException {
		Map<String,Object> map = new HashMap<>();
		User user = userMapper.findByLoginName(phone);
		map.put("id", user.getId());
		map.put("userId", user.getId());
		if (StringUtil.isNotBlank(userName)) {
			map.put("userName", "%"+userName+"%");
		}
		PageHelper.startPage(current, pageSize);
		List<ManageProfitModel> list = userInviteMapper.findAgent(map);
		return (Page<ManageProfitModel>)list;
	}

	@Override
	public Page<ManageAgentModel> findSysAgent(String userName, int current,
                                               int pageSize) {
		PageHelper.startPage(current, pageSize);
		Map<String,Object> map = new HashMap<>();
		if (StringUtil.isNotBlank(userName)) {
			map.put("userName", "%"+userName+"%");
		}
		List<ManageAgentModel> list = userInviteMapper.findSysAgent(map);
		for (ManageAgentModel manageAgentModel : list) {
			if ("1".equals(manageAgentModel.getLevel())) {
				manageAgentModel.setCount(userInviteMapper.count(manageAgentModel.getUserId())+"");
			}else {
				manageAgentModel.setCount("0");
			}
		}
		return (Page<ManageAgentModel>)list;
	}

	@Override
	public Page<InviteBorrowModel> listInviteBorrow(long userId, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		List<InviteBorrowModel> list = userInviteMapper.listInviteBorrow(map);
		return (Page<InviteBorrowModel>)list;
	}


}