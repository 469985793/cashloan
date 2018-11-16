package com.xindaibao.cashloan.rule.service;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rule.domain.BorrowRuleConfig;
import com.xindaibao.cashloan.rule.model.BorrowRuleConfigModel;

/**
 * 借款规则详细配置表Service
 *
 */
public interface BorrowRuleConfigService extends BaseService<BorrowRuleConfig, Long>{

	List<BorrowRuleConfigModel> findConfig(Map<String, Object> params);

	void deleteByMap(Map<String, Object> map);

	List<BorrowRuleConfig> findBorrowRuleId(Map<String, Object> paramMap);

}
