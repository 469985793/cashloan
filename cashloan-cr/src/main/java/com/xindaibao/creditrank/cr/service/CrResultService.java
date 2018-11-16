package com.xindaibao.creditrank.cr.service;


import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.creditrank.cr.domain.CrResult;

/**
 * 评分结果Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-05 16:22:54


 * 

 */
public interface CrResultService extends BaseService<CrResult, Long>{

	/**
	 * 统计用户的总评分和总额度
	 * @param userId
	 * @return
	 */
	public Map<String,Object> findUserResult(Long userId);
	
	/**
	 * 查询用户各借款类型的总额度
	 * @param userId
	 * @return
	 */
	public List<CrResult> findAllBorrowTypeResult(Long userId);
	
}
