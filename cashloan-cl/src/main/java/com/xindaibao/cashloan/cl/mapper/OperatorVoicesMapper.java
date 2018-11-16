package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.OperatorVoices;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;


/**
 * 运营商信息-通话记录Dao
 */
@RDBatisDao
public interface OperatorVoicesMapper extends BaseMapper<OperatorVoices,Long> {
	
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
	 * @param operatorVoices
	 * @return
	 */
	int saveShard(@Param("tableName")String tableName, @Param("item")OperatorVoices operatorVoices);
	
	/**
	 * 根据参数(分表)查询
	 * @param tableName
	 * @param beginRow
	 * @param pageSize
	 * @param params
	 * @return
	 */
	List<OperatorVoices> listShardSelective(
			@Param("tableName") String tableName,
			@Param("params") Map<String, Object> params);
	
}
