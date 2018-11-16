package com.xindaibao.cashloan.rule.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rule.domain.RuleInfo;

/**
 * 规则信息Service
 */
public interface RuleInfoService extends BaseService<RuleInfo, Long>{
	/**
	 * 查询
	 * @param map
	 * @return
	 */
	List<RuleInfo> findAll(Map<String, Object> map);
	/**
	 * 分页查询
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<RuleInfo> ruleList(Map<String, Object> params,int currentPage,int pageSize);
	/**
	 * 检验表是否已经保存过
	 * @param list
	 * @param table
	 * @return
	 */
	boolean checkTable(List<RuleInfo> list,String table);
	/**
	 * 检验表字段是否已经保存过
	 * @param list
	 * @param table
	 * @param column
	 * @return
	 */
	boolean checkColumn(List<RuleInfo> list,String table,String column);
	/**
	 * 编辑状态
	 * @param id
	 * @param state
	 * @return
	 */
	int modifyInfoState(Long id, String state);

}
