package com.xindaibao.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.exception.PersistentDataException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.system.domain.SysUser;


/**
 * 
 * 用户DAO
 * 
 * @version 1.0
 * @author
 * @created 2014年9月22日 下午2:53:15
 */
@RDBatisDao
public interface SysUserMapper extends BaseMapper<SysUser, Long> {

	/**
	 * 编辑用户登录信息
	 * @param sysUser
	 *            用户实体
	 * @return Boolean
	 * @version 1.0
	 * @author
	 * @created 2014年9月22日
	 */
	Boolean editUserLoginInfo(SysUser sysUser);

	/**
	 * 编辑用户密码
	 * @param sysUser
	 *            用户实体
	 * @return Boolean
	 * @version 1.0
	 * @author
	 * @created 2014年9月22日
	 */
	Boolean editUserPassWord(SysUser sysUser);

    /**
     *根据ID更新  通用更新
     *@param map
     *@return Boolean
     */ 
    int updateSysUserById(Map<String , Object> map);

	int queryRoleUserIsUse(Map<String, Object> data) throws PersistentDataException;

    SysUser getUserByUserName(String userName) throws PersistentDataException;

	int getPageCountOracle(Map<String, Object> map);

	List<Map<String, Object>> getUserInfoOracle(Map<String, Object> mapdata);

	Map<String, Object> queryTheLeastBusyUserByMap(Map<String, Object> params);

	Map<String, Object> queryTheUserWhoDidThisTask(Map<String, Object> paramMap);

	SysUser getUserByMap(Map<String, Object> paramMap);

	List<Map<String, Object>> getUserInfo(Map<String, Object> params);
	
	List<Map<String, Object>> listUserInfo(Map<String, Object> mapdata);
	
	/**
	 * 根据角色查找用户列表
	 * @param params
	 * @return
	 */
	List<SysUser> listByRole(Map<String, Object> params);

	void updateState(Map<String, Object> result);
}
