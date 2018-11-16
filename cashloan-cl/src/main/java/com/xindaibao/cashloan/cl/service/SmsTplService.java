package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.SmsTpl;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 短信记录Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-13 18:36:01


 * 

 */
public interface SmsTplService extends BaseService<SmsTpl, Long>{
	Page<SmsTpl> list(Map<String, Object> params,int currentPage, int pageSize);
	
	int updateSelective(Map<String, Object> paramMap);
}
