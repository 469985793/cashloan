package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.UserContacts;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 用户资料--联系人Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-04 11:52:26


 * 

 */
@RDBatisDao
public interface UserContactsMapper extends BaseMapper<UserContacts,Long> {
	
	/**
	 * 根据表名查询表数量
	 * @param tableName
	 * @return
	 */
	int countTable(String tableName);
	
	/**
	 * 根据表名创建表
	 * @param tableName
	 */
	void createTable(@Param("tableName") String tableName);
	
	/**
	 * (分表)新增
	 * @param tableName
	 * @param userContacts
	 * @return
	 */
	int saveShard(@Param("tableName")String tableName, @Param("item")UserContacts userContacts);
	
	/**
	 * 删除原有记录
	 * @param tableName
	 * @param userId
	 * @return
	 */
	int deleteShardByUserId(@Param("tableName")String tableName, @Param("userId")long userId);
	
	/**
	 * 根据参数(分表)查询
	 * @param tableName
	 * @param beginRow
	 * @param pageSize
	 * @param params
	 * @return
	 */
	List<UserContacts> listShardSelective(
			@Param("tableName") String tableName,
			@Param("params") Map<String, Object> params);

}
