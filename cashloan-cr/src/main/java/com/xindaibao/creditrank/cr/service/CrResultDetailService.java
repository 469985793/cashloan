package com.xindaibao.creditrank.cr.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.creditrank.cr.domain.CrResultDetail;
import com.xindaibao.creditrank.cr.model.CrResultDetailModel;

/**
 * 评分结果Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-05 16:46:34


 * 

 */
public interface CrResultDetailService extends BaseService<CrResultDetail, Long>{

	Page<CrResultDetail> page(Map<String, Object> secreditrankhMap, int current,int pageSize);

	List<CrResultDetailModel> listDetailTree(Long resultId);

	List<CrResultDetail> listInfo(Long cardId);
}
