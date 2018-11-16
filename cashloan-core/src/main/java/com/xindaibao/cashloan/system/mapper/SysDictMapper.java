package com.xindaibao.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.system.domain.SysDict;

/**
 * 
 * 数据字典DAO接口
 * @version 1.0
 * @author
 * @created 2014年9月23日 上午9:44:54
 */
@RDBatisDao
public interface SysDictMapper extends BaseMapper<SysDict,Long> {

	/**
	 * 取得数据字典类型列表
	 * 
	 * @return 数据字典类型列表
	 */
	List<String> getTypeList();
	
	/**
	 * 查询数据字典
	 * @param typeArr 字典组名
	 * @return 数据字典
	 */
	List<SysDict> getDictByTypeArr(Map<String, Object> map);
	
	
	
	Long saveOrUpdate(Map<String, Object> arg,String status) ;
	
	
	List<Map<String, Object>> getDictsCache(String typeDict) ;

	List<SysDict> getItemListByMap(Map<String, Object> map);

	Long getCount(Map<String, Object> arg);

	long deleteById(Long id);

	SysDict findByTypeCode(@Param("code")String typeCode);
	
	

}
