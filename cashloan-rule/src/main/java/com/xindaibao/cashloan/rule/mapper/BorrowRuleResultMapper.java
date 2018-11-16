package com.xindaibao.cashloan.rule.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.rule.domain.BorrowRuleResult;
import com.xindaibao.cashloan.rule.model.ManageReviewModel;
import com.xindaibao.cashloan.rule.model.ManageRuleResultModel;

/**
 * 规则匹配结果Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-21 15:04:28


 * 

 */
@RDBatisDao
public interface BorrowRuleResultMapper extends BaseMapper<BorrowRuleResult,Long> {

	/**
	 * 查询规则名称
	 * @param borrowId
	 * @return
	 */
	List<ManageReviewModel> findRuleResult(long borrowId);

	/**
	 * 查询审核信息
	 * @param borrowId
	 * @return
	 */
	List<ManageRuleResultModel> findResult(long borrowId);

	/**
	 * 查询审核明细
	 * @param borrowId
	 * @param id 
	 * @return
	 */
	List<BorrowRuleResult> findRule(long borrowId, long id);

	List<BorrowRuleResult> findRule(Map<String, Object> search);


}
