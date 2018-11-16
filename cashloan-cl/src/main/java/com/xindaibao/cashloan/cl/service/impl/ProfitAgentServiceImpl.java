package com.xindaibao.cashloan.cl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.mapper.ProfitAgentMapper;
import com.xindaibao.cashloan.cl.model.ManageAgentListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;
import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.ProfitAgent;
import com.xindaibao.cashloan.cl.service.ProfitAgentService;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.mapper.UserMapper;
import com.xindaibao.cashloan.system.constant.SystemConstant;
import com.xindaibao.cashloan.system.domain.SysAccessCode;
import com.xindaibao.cashloan.system.domain.SysRole;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.mapper.SysAccessCodeMapper;
import com.xindaibao.cashloan.system.mapper.SysRoleMapper;
import com.xindaibao.cashloan.system.mapper.SysUserMapper;
import com.xindaibao.cashloan.system.model.SysAccessCodeModel;
import com.xindaibao.cashloan.system.security.authentication.encoding.PasswordEncoder;
import com.xindaibao.cashloan.system.service.SysUserService;

@Service("profitAgentService")
public class ProfitAgentServiceImpl extends BaseServiceImpl<ProfitAgent, Long> implements ProfitAgentService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfitAgentServiceImpl.class);
   
    @Resource
    private ProfitAgentMapper profitAgentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
	private SysUserMapper sysUserMapper;
    @Resource
	private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysAccessCodeMapper sysAccessCodeMapper;
    
	@Override
	public BaseMapper<ProfitAgent, Long> getMapper() {
		return profitAgentMapper;
	}

	@Override
	public int pass(int isUse,long id) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("id", id);
		result.put("isUse", isUse);
		return profitAgentMapper.updateSelective(result);
	}

	@Override
	public int rankUp(long id,long userId) {
		double levelOne = Double.valueOf(Global.getValue("level_one"));//一级代理分润率
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("id", id);
		ProfitAgent agent = profitAgentMapper.findByPrimary(id);
		int msg = 0;
		if (agent!=null) {
			result.put("userId", userId);
			result.put("level", 1);
			result.put("rate", levelOne/100);
			result.put("applyTime", new Date());
			result.put("oldRate", agent.getRate()/100);
			msg  = profitAgentMapper.updateSelective(result);
			if (msg>0) {
				User user = new User();
				user.setId(userId);
				user.setLevel(1);
				msg = userMapper.updateLevel(user);
			}
		}
		return msg;
	}

	@Override
	public Page<ManageAgentListModel> findAgentList(String userName, int current,
                                                    int pageSize) {
		PageHelper.startPage(current, pageSize);
		Map<String,Object> map = new HashMap<>();
		if (StringUtil.isNotBlank(userName)) {
			map.put("userName", "%"+userName+"%");
		}
		List<ManageAgentListModel> list = profitAgentMapper.findAgentList(map);
		for (ManageAgentListModel manageAgentListModel : list) {
			manageAgentListModel.setRate(manageAgentListModel.getRate()*100);
		}
		return (Page<ManageAgentListModel>)list;
	}

	@Override
	public Page<User> findUserLevel(String userName,int current,int pageSize) {
		PageHelper.startPage(current, pageSize);
		Map<String,Object> map = new HashMap<>();
		if (StringUtil.isNotBlank(userName)) {
			map.put("loginName", "%"+userName+"%");
		}
		List<User> list = userMapper.findUserLevel(map);
		return (Page<User>)list;
	}

	@Override
	public int freezeAgent(long userId,Date updateTime) {
		int msg;
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		ProfitAgent pa = profitAgentMapper.findSelective(map);
		if (StringUtil.isNotBlank(pa)) {
			map.put("id", pa.getId());
			map.put("level", 3);
			map.put("rate", 0.05);
			map.put("isUse", 20);
			map.put("updateTime", updateTime);
			msg  = profitAgentMapper.updateSelective(map);
			if (msg>0) {
				User user = new User();
				user.setId(userId);
				user.setLevel(3);
				msg = userMapper.updateLevel(user);
			}
			if (msg > 0 && pa.getLevel() == 1) {
				User user = userMapper.findByPrimary(userId);
				Map<String,Object> result = new HashMap<String,Object>();
				result.put("userName", user.getLoginName());
				SysUser sys = sysUserMapper.findSelective(result);
				if(sys != null){
					result = new HashMap<String,Object>();
					result.put("status", (byte) 1);
					result.put("id", sys.getId());
					sysUserMapper.updateState(result);
				}
			}
		}else {
			return 0;
		}
		return msg;
	}

	@Override
	public int saveOne(long userId,Date updateTime) {
		int msg;
		User user = userMapper.findByPrimary(userId);
		if (StringUtil.isBlank(user)) {
			return 0;
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("userId", user.getId());
		UserBaseInfo ubi = userBaseInfoMapper.findSelective(result);
		if (StringUtil.isBlank(ubi)) {
			return 0;
		}
		
		double levelOne = Double.valueOf(Global.getValue("level_one"));//一级代理分润率
		result.put("userId", userId);
		ProfitAgent pa = profitAgentMapper.findSelective(result);
		result = new HashMap<String,Object>();
		result.put("createTime", DateUtil.getNow());
		if (StringUtil.isBlank(pa)) {
			pa = new ProfitAgent();
			pa.setLevel(1);
			pa.setUserId(userId);
			pa.setRate(levelOne/100);
			pa.setCreateTime(new Date());
			pa.setUpdateTime(new Date());
			pa.setIsUse(10);
			msg = profitAgentMapper.save(pa);
			if (msg>0) {
				sys(user, ubi);
			}
		}else {
			result = new HashMap<String,Object>();
			result.put("level", 1);
			result.put("leaderId", 0);
			result.put("rate", levelOne/100);
			result.put("isUse", 10);
			result.put("id", pa.getId());
			result.put("updateTime", updateTime);
			msg = profitAgentMapper.updateSelective(result);
			if (msg>0) {
				sys(user, ubi);
			}
		}
		if (msg>0) {
			user = new User();
			user.setId(userId);
			user.setLevel(1);
			msg = userMapper.updateLevel(user);
		}
		return msg;
	}

	@Override
	public int saveTwo(long userId,String leaderId, String rate,Date updateTime) {
		int msg;
		User user = userMapper.findByPrimary(userId);
		if (StringUtil.isBlank(user)) {
			return 0;
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("userId", userId);
		ProfitAgent pa = profitAgentMapper.findSelective(result);
		result.put("createTime", DateUtil.getNow());
		if (StringUtil.isBlank(pa)) {
			pa = new ProfitAgent();
			pa.setLevel(2);
			pa.setUserId(userId);
			pa.setRate(Double.parseDouble(rate)/100);
			pa.setLeaderId(Long.parseLong(leaderId));
			pa.setCreateTime(new Date());
			pa.setUpdateTime(new Date());
			pa.setIsUse(10);
			msg = profitAgentMapper.save(pa);
		}else {
			result = new HashMap<String,Object>();
			result.put("level", 2);
			result.put("leaderId", Long.parseLong(leaderId));
			result.put("rate", Double.parseDouble(rate)/100);
			result.put("isUse", 10);
			result.put("id", pa.getId());
			result.put("updateTime", updateTime);
			msg = profitAgentMapper.updateSelective(result);
		}
		if (msg>0) {
			user = new User();
			user.setId(userId);
			user.setLevel(2);
			msg = userMapper.updateLevel(user);
		}
		return msg;
	}
	
	public void sys(User user, UserBaseInfo ubi) {
		Map<String, Object> sysUserMap = new HashMap<String, Object>();
		sysUserMap.put("userName", user.getLoginName());
		SysUser sysUser = sysUserMapper.findSelective(sysUserMap);
		
		if (StringUtil.isBlank(sysUser)) {
			Map<String, Object> roleMap = new HashMap<String, Object>();
			roleMap.put("nid", "agent");
			SysRole role = sysRoleMapper.findSelective(roleMap);
			if(role != null){
				sysUser = new SysUser();
				sysUser.setUserName(user.getLoginName());
				sysUser.setName(ubi.getRealName());
				sysUser.setPhone(user.getLoginName());
				sysUser.setStatus((byte) 0);
				sysUser.setAddTime(new Date());
				sysUser.setPassword(passwordEncoder.encodePassword(SystemConstant.SYSTEM_PASSWORD_DEFAULT));
				
				try {
					sysUserService.addUser(sysUser, role.getId().toString());
				} catch (ServiceException e) {
					logger.error(e.toString(), e);
				}
				
				sysUser = sysUserMapper.findSelective(sysUserMap);
				String accessCodeAble = Global.getValue("access_code_able");// 是否启用访问码，10启用，20关闭
				if(accessCodeAble.equals("10")){
					SysAccessCode accessCode = new SysAccessCode(sysUser.getId(), SystemConstant.SYSTEM_PASSWORD_DEFAULT, 
							SysAccessCodeModel.STATE_ENABLE, DateUtil.rollYear(DateUtil.getNow(), 10));
					sysAccessCodeMapper.save(accessCode);
				}
				
			}
		} else if (sysUser.getStatus() != 0) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("status", (byte) 0);
			map.put("id", sysUser.getId());
			sysUserMapper.updateState(map);
		}
	}
	
}