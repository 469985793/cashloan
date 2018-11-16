package com.xindaibao.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.Opinion;
import com.xindaibao.cashloan.cl.model.OpinionModel;
import com.xindaibao.cashloan.core.common.service.BaseService;


/**
 * 意见反馈表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:30:57


 * 

 */
public interface OpinionService extends BaseService<Opinion, Long> {
	
	int save(long userId, String opinion);
	
	int updateSelective(Map<String, Object> searchMap);

	List<Opinion> getOpinion(Map<String, Object> paramMap);
	
	Page<OpinionModel> page(Map<String, Object> searchMap, int current, int pageSize);

}
