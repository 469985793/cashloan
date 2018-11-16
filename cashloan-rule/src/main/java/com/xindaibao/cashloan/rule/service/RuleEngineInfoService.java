package com.xindaibao.cashloan.rule.service;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rule.domain.RuleEngineInfo;

/**
 * 规则评分设置管理Service
 */
public interface RuleEngineInfoService extends BaseService<RuleEngineInfo, Long>{
	/**
	 * 保存
	 * @param rule
	 * @param list
	 * @return
	 */
	int saveIntegralInfo(Map<String, Object> rule, List list);
	/**
	 * 查询
	 * @param search
	 * @return
	 */
	List<RuleEngineInfo> findByMap(Map<String, Object> search);
}
