package com.xindaibao.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.system.model.MenuModel;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.exception.PersistentDataException;
import com.xindaibao.cashloan.system.domain.SysMenu;

/**
 * 
 * 系统菜单DAO
 * @version 1.0
 * @author
 * @created 2014年9月22日 下午2:57:58
 */
@RDBatisDao
public interface SysMenuMapper extends BaseMapper<SysMenu,Long> {

	/**
	 * 根据pid查询当前目录的子目录
	 * @param pid 父级id
	 * @return 菜单目录
	 */
	List<SysMenu> menuFindByPid(long pid);

	/**
	 * 根据父类ID删除子类信息(逻辑删除)
	 * @param pid 父类ID
	 */
	void update(long pid);

	/**
	 * 获取使用中的菜单栏
	 * @param userId 用户ID
	 * @param isMenu 是否菜单
	 * @return 菜单
	 */
	List<SysMenu> menuUseList(long userId, byte isMenu);

	/**
	 * 获取用户是否有此url的权限
	 * @param userId 用户ID
	 * @param href 菜单路径
	 * @return 菜单
	 */
	SysMenu getMenuPermission(String userName, String href);
	
	/**
	 * 根据角色ids查询菜单model list信息
	 * @param roleIds 角色ids
	 * @return 菜单model list
	 */
	List<MenuModel> getMenuListByRoleIds(List<Long> roleIds);
	
	SysMenu getMenuByHref(String href);
	
	/**
	 * 软删除菜单信息
	 * @param roleIds 角色ids
	 * @return 菜单model list
	 */
	void editMenuIsDelete(SysMenu menu);
	
	/**
	 * 获得 用户左侧面板
	 * @param userName
	 * @param parentId
	 * @return
	 * @throws PersistentDataException 
	 * @throws SerialException 
	 */
	List<? extends SysMenu> getMenuPanelByParentId(String userName, String parentId,int code,List<Long> roles) throws PersistentDataException;
	
	
	
	int updateMenu(Map<String, Object> menu);
	
	/**
	 * 添加
	 * @param menu
	 * @return
	 */
	int insertmap(Map<String, Object> menu);
	
	
	
	List<Map<String, Object>> getRoleMenuList(int roleId);
	
	
	boolean saveOrupdateRoleMenu(String roleId,String ids) throws PersistentDataException;
	
	/**
	 * 获得满足条件的菜单列表
	 * @param param
	 * @return 
	 * @version 1.0
	 * @author
	 * @created 2014年12月19日
	 */
	List<SysMenu> getMenuList(Map<String, Object> param);

	Map<String,Object> getMenuInfoById(Long id) throws PersistentDataException;
	
	
	List<Map<String, Object>> getAllMenuList(Map<String, Object> map) throws PersistentDataException;
	
	List<Map<String, Object>> fetchRoleMenuHas(Long roleId);
	
	List<Map<String,Object>> getRoleSysMenu(String roleIds, String sysType);
	
	List<Map<String,Object>> getMenuParentId(@Param(value="menuLeafIds") String[] menuLeafIds);
	
	List<Map<String,Object>> getMenuByParentIds(@Param(value="ids") String[] ids);

	/**
	 * @description
	 * @return
	 * @author
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	*/
	List<Map<String, Object>> fetchAllMenu();
}
