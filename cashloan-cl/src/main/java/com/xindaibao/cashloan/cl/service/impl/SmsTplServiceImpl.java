package com.xindaibao.cashloan.cl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.mapper.SmsTplMapper;
import com.xindaibao.cashloan.cl.service.SmsTplService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.SmsTpl;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
/**
 * 短信记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-13 18:36:01


 * 

 */
@SuppressWarnings("unused")
@Service("clSmsTplService")
public class SmsTplServiceImpl extends BaseServiceImpl<SmsTpl, Long> implements SmsTplService {
	
	private static final Logger logger = LoggerFactory.getLogger(SmsTplServiceImpl.class);
   
    @Resource
    private SmsTplMapper clSmsTplMapper;

	@Override
	public BaseMapper<SmsTpl, Long> getMapper() {
		return clSmsTplMapper;
	}

	@Override
	public Page<SmsTpl> list(Map<String, Object> params, int currentPage,
			int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<SmsTpl> list = clSmsTplMapper.listSelective(params);
	
		return (Page<SmsTpl>) list;
	}

	@Override
	public int updateSelective(Map<String, Object> paramMap) {
		return clSmsTplMapper.updateSelective(paramMap);
	}
	
	
}