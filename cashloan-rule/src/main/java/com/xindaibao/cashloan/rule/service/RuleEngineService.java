package com.xindaibao.cashloan.rule.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rule.domain.RuleEngine;

/**
 * 规则引擎管理Service
 */
public interface RuleEngineService extends BaseService<RuleEngine, Long>{
	/**
	 * 分页查询
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<RuleEngine> findListByPage(Map<String, Object> params, int currentPage,
			int pageSize);
	/**
	 * 编辑保存
	 * @param rule
	 * @return
	 */
	int saveOrUpate(Map<String, Object> rule);
	/**
	 * 查询具体信息
	 * @param id
	 * @return
	 */
	RuleEngine findById(Long id);
	/**
	 * 编辑
	 * @param map
	 * @return
	 */
	int updateByRule(Map<String, Object> map);
	/**
	 * 查询
	 * @param rule
	 * @return
	 */
	List<RuleEngine> selectList(Map<String, Object> rule);
	/**
	 * 查询所有规则信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findAllRule(Map<String, Object> params);
}
