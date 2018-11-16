package com.xindaibao.cashloan.rule.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rule.domain.BorrowRuleResult;

/**
 * 规则匹配结果Service
 */
public interface BorrowRuleResultService extends BaseService<BorrowRuleResult, Long>{

	/**
	 * 查询匹配结果分页列表
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<BorrowRuleResult> borrowRuleResult(Map<String, Object> params,int currentPage,int pageSize);
}
