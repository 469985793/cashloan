package com.xindaibao.cashloan.cl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.ProfitLog;
import com.xindaibao.cashloan.cl.mapper.ClBorrowMapper;
import com.xindaibao.cashloan.cl.mapper.ProfitAgentMapper;
import com.xindaibao.cashloan.cl.mapper.ProfitAmountMapper;
import com.xindaibao.cashloan.cl.mapper.ProfitLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.ProfitAgent;
import com.xindaibao.cashloan.cl.domain.UserInvite;
import com.xindaibao.cashloan.cl.mapper.UserInviteMapper;
import com.xindaibao.cashloan.cl.model.ManageCashLogModel;
import com.xindaibao.cashloan.cl.model.ProfitLogModel;
import com.xindaibao.cashloan.cl.service.ProfitLogService;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.mapper.UserMapper;

/**
 * 分润记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 17:04:10


 * 

 */
 
@Service("profitLogService")
public class ProfitLogServiceImpl extends BaseServiceImpl<ProfitLog, Long> implements ProfitLogService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProfitLogServiceImpl.class);
   
    @Resource
    private ProfitLogMapper profitLogMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Resource
    private UserInviteMapper userInviteMapper;
    @Resource
    private ProfitAgentMapper profitAgentMapper;
    @Resource
    private ProfitAmountMapper profitAmountMapper;

	@Override
	public BaseMapper<ProfitLog, Long> getMapper() {
		return profitLogMapper;
	}

	@Override
	public Page<ProfitLogModel> page(Map<String, Object> searchMap, int current,
			int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<ProfitLogModel> list = profitLogMapper.listInfo(searchMap);
		for (ProfitLogModel profitLogModel : list) {
			profitLogModel.setMsg("借款"+profitLogModel.getMoney()+"元,综合费用"+profitLogModel.getFee()+"元");
		}
		return (Page<ProfitLogModel>) list;
	}
	
	@Override
	public Page<ManageCashLogModel> findCashLog(String phone,String userName, int current,
			int pageSize) {
		User user = userMapper.findByLoginName(phone);
		PageHelper.startPage(current, pageSize);
		Map<String,Object> map = new HashMap<>();
		map.put("id", user.getId());
		if (StringUtil.isNotBlank(userName)) {
			map.put("userName", "%"+userName+"%");
		}
		List<ManageCashLogModel> list = profitLogMapper.findCashLog(map);
		return (Page<ManageCashLogModel>)list;
	}

	@Override
	public Page<ManageCashLogModel> findCashLog(String userName, int current,
			int pageSize) {
		PageHelper.startPage(current, pageSize);
		Map<String,Object> map = new HashMap<>();
		if (StringUtil.isNotBlank(userName)) {
			map.put("userName", "%"+userName+"%");
		}
		List<ManageCashLogModel> list = profitLogMapper.findSysCashLog(map);
		return (Page<ManageCashLogModel>)list;
	}
	
	@Override
	public int save(long borrowId, Date date) {
		int msg;

		// 判断借款
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		if (StringUtil.isBlank(borrow)) {
			throw new BussinessException("此借款不存在");
		}
		
		// 判断用户
		User user = userMapper.findByPrimary(borrow.getUserId());
		if (StringUtil.isBlank(user)) {
			throw new BussinessException("此用户不存在");
		}
		
		// 判断是否有邀请人
		Map<String, Object> inviteMap = new HashMap<>();
		inviteMap.put("inviteId", user.getId());
		UserInvite invite = userInviteMapper.findSelective(inviteMap);
		/*if (StringUtil.isNotBlank(invite)) {
			throw new BussinessException("此用户无邀请人");
		}*/
		Map<String, Object> profitAgentMap = new HashMap<>();
		profitAgentMap.put("userId", invite.getUserId());
		ProfitAgent agent = profitAgentMapper.findSelective(profitAgentMap);
		
		Map<String, Object> params = new HashMap<String, Object>();
		Double amount;
		if (StringUtil.isNotBlank(agent)) {// 判断邀请人是否代理商
			amount = borrow.getFee() * agent.getRate();
			params.put("userId", agent.getUserId());
			params.put("amount", amount);
			profitAmountMapper.addNocashedAmount(params);
			msg = saveLog(date, agent.getUserId(), amount, borrow.getId(),agent.getRate(), user.getId());

			if (agent.getLevel() == 2) {
				if(agent.getLeaderId()!=null&&agent.getLeaderId()!=0){
					Map<String, Object> profitAgentLevel  = new HashMap<>();
					profitAgentLevel.put("userId", agent.getLeaderId());
					ProfitAgent agentLeader = profitAgentMapper.findSelective(profitAgentLevel);
					if(StringUtil.isNotBlank(agentLeader)){
						amount = borrow.getFee() * (agentLeader.getRate() - agent.getRate());
						params.clear();
						params.put("userId", agentLeader.getUserId());
						params.put("amount", amount);
						profitAmountMapper.addNocashedAmount(params);
						msg = saveLog(date, agentLeader.getUserId(), amount,borrow.getId(), agentLeader.getRate(), user.getId());
					}
				}
			}
		} else {// 非代理商
			Double rate = Double.parseDouble(Global.getValue("levelThree"));
			amount = borrow.getFee() * rate/100;
			params.clear();
			params.put("userId", invite.getUserId());
			params.put("amount", amount);
			profitAmountMapper.addNocashedAmount(params);
			msg = saveLog(date, invite.getUserId(), amount,borrow.getId(), rate, user.getId());
		}
		return msg;
	}
	

	private int saveLog(Date date, Long agentId, double d, Long borrowId, Double rate,
			Long userId) {
		ProfitLog log = new ProfitLog();
		log.setAddTime(date);
		log.setAgentId(agentId);
		log.setAmount(d);
		log.setBorrowId(borrowId);
		log.setRate(rate);
		log.setUserId(userId);
		return profitLogMapper.save(log);
	}
	
}