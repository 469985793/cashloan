package com.xindaibao.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.Adver;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 广告管理Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-06-21 14:33:20



 */
public interface AdverService extends BaseService<Adver, Long>{
	
	public Page<Adver> page(int current, int pageSize, Map<String, Object> params) throws ServiceException;
	
	
	public boolean updateSelective(Map<String, Object> params) throws ServiceException;
	
	
	public List<Adver> getBanner();

}
