package com.xindaibao.cashloan.core.common.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@RDBatisDao
public interface BaseMapper<T, ID extends Serializable> {

	/**
	 * 插入数据
	 * 
	 * @param e
	 *            实体类
	 * @return 主键值
	 */
	int save(T e);
	
	/**
	 * 插入数据，返回对象，包含主键ID
	 * @param e
	 * @return
	 */
	void saveRecord(T e);

	/**
	 * 根据主键更新数据
	 * 
	 * @param e
	 *            更新数据及条件
	 * @return
	 */
	int update(T e);

	/**
	 * 更新数据
	 * 
	 * @param e
	 *            新数据
	 * @param conditions
	 *            更新条件
	 */
	int updateSelective(Map<String, Object> paramMap);

	/**
	 * 获取一条记录
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 查询结果
	 */
	T findSelective(Map<String, Object> paramMap);

	/**
	 * 根据主键查询数据
	 * 
	 * @param primary
	 *            主键值
	 * @return 数据结果
	 */
	T findByPrimary(ID primary);
	
	/**
	 * 数据查询
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 结果集
	 */
	List<T> listSelective(Map<String, Object> paramMap);
	
	
}