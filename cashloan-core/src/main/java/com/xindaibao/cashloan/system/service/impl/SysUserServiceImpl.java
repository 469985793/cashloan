package com.xindaibao.cashloan.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.domain.SysUserRole;
import com.xindaibao.cashloan.system.mapper.SysUserRoleMapper;
import com.xindaibao.cashloan.system.security.authentication.encoding.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.system.domain.SysRole;
import com.xindaibao.cashloan.system.mapper.SysRoleMapper;
import com.xindaibao.cashloan.system.mapper.SysUserMapper;
import com.xindaibao.cashloan.system.service.SysUserService;

@Service(value = "sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {
    
	public static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	@Resource
	private SysUserMapper sysUserMapper;
	
	@Resource
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Resource
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;// 密码加密

	
	public SysUserMapper getSysUserMapper() {
		return sysUserMapper;
	}

	public void setSysUserMapper(SysUserMapper sysUserMapper) {
		this.sysUserMapper = sysUserMapper;
	}

	public SysUserRoleMapper getSysUserRoleMapper() {
		return sysUserRoleMapper;
	}

	public void setSysUserRoleMapper(SysUserRoleMapper sysUserRoleMapper) {
		this.sysUserRoleMapper = sysUserRoleMapper;
	}

	public SysRoleMapper getSysRoleMapper() {
		return sysRoleMapper;
	}

	public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
		this.sysRoleMapper = sysRoleMapper;
	}

	@Override
	public Boolean editUserLoginInfo(SysUser sysUser) throws ServiceException{
		try {
			return sysUserMapper.editUserLoginInfo(sysUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public Boolean editUserPassWord(SysUser sysUser) throws ServiceException {
		try {
			return sysUserMapper.editUserPassWord(sysUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public SysUser getUserById(long id) throws ServiceException {
		try {
			return sysUserMapper.findByPrimary(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Override
	public SysRole getRoleById(long id) throws ServiceException {
		try {
			return sysRoleMapper.findByPrimary(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int userUpdate(SysUser sysUser) throws ServiceException {
		try {
			// 测试取消 密码更新方法
			return this.sysUserMapper.update(sysUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public Page<Map<String, Object>> getUserPageList(int currentPage,int pageSize,Map<String, Object> mapdata) throws ServiceException {
		try {
			PageHelper.startPage(currentPage, pageSize);

			return (Page<Map<String, Object>>)sysUserMapper.listUserInfo(mapdata);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int getUserSum(Map<String, Object> map) throws ServiceException {
		try {
			return sysUserMapper.getPageCountOracle(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public void addUser(SysUser sysUser, String roleIdArr) throws ServiceException {
		try {
			// 增加用户
			sysUserMapper.save(sysUser);
			String temp = roleIdArr.replaceAll("\\[", "").replaceAll("\\]", "");
			String[] roles = temp.split(","); 
			for(int i=0;i<roles.length;i++){
				String role = roles[i].trim();
				// 增加用户角色关系
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setRoleId(Long.parseLong(role));
				sysUserRole.setUserId(sysUser.getId());
				sysUserRoleMapper.save(sysUserRole);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean updateSysUserById(Map<String, Object> map) throws ServiceException {
		try {
			Boolean result = false;
			//首先删除角色关系
			Long userId = Long.valueOf(String.valueOf(map.get("id")));
			sysUserRoleMapper.deleteByUserId(userId);
			String temp = String.valueOf(map.get("roleId")).replaceAll("\\[", "").replaceAll("\\]", "");			
			String[] roles = temp.split(","); 
			for(int i=0;i<roles.length;i++){
				String role = roles[i].trim();
				// 增加用户角色关系
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setRoleId(Long.parseLong(role));
				sysUserRole.setUserId(userId);
				sysUserRoleMapper.save(sysUserRole);
			}
			int isU = sysUserMapper.updateSysUserById(map);
			if(isU > 0){
				result = true;
			}
			return result;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public SysUser getUserByUserName(String userName) throws ServiceException {
		try {
			return sysUserMapper.getUserByUserName(userName);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int queryRoleUserIsUse(Map<String, Object> data) throws ServiceException {
		try {
			return sysUserMapper.queryRoleUserIsUse(data);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public List<Map<String, Object>> getUserInfo(Map<String, Object> params) throws ServiceException{
		return sysUserMapper.getUserInfo(params);
	}

	@Override
	public BaseMapper<SysUser, Long> getMapper() {
		return sysUserMapper;
	}

}