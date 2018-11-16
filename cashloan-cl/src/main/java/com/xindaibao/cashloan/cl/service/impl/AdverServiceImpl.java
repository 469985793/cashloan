package com.xindaibao.cashloan.cl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.Adver;
import com.xindaibao.cashloan.cl.mapper.AdverMapper;
import com.xindaibao.cashloan.cl.service.AdverService;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 广告管理ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-06-21 14:33:20



 */
 
@Service("adverService")
public class AdverServiceImpl extends BaseServiceImpl<Adver, Long> implements AdverService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AdverServiceImpl.class);
   
    @Resource
    private AdverMapper adverMapper;

	@Override
	public BaseMapper<Adver, Long> getMapper() {
		return adverMapper;
	}
	
	@Override
	public boolean updateSelective(Map<String, Object> params) throws ServiceException {
		int count = adverMapper.updateSelective(params);
		if (count > 0L) {
			return true;
		}
		return false;
	}
	
	@Override
	public Page<Adver> page(int current, int pageSize, Map<String, Object> params)
			throws ServiceException {
		PageHelper.startPage(current, pageSize);
		Page<Adver> page = (Page<Adver>) adverMapper.listSelective(params);
		return page;
	}

	@Override
	public List<Adver> getBanner() {
		return adverMapper.getBanner();
	}
}