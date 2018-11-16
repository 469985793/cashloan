package com.xindaibao.cashloan.rule.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rule.domain.BorrowRuleConfig;
import com.xindaibao.cashloan.rule.domain.BorrowRuleEngine;

/**
 * 借款规则管理Service
 */
public interface BorrowRuleEngineService extends BaseService<BorrowRuleEngine, Long>{

	/**
	 * 查询借款规则
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<BorrowRuleEngine> page(Map<String, Object> params,int currentPage,int pageSize);
	
	int insert(BorrowRuleEngine bre);
	
	int updateSelective(Map<String, Object> params);
	
	int deleteById(long id);
	
	/**
	 * 编辑借款规则(规则类型、借款类型和规则类型适用场景)
	 * @param bre
	 * @return
	 */
	int update(BorrowRuleEngine bre);
	 
	/**
	 * 新增或修改借款規則信息
	 * @param brc
	 * @param configlist
	 * @return
	 */
	int update(BorrowRuleEngine brc, List<BorrowRuleConfig> configlist);
}
