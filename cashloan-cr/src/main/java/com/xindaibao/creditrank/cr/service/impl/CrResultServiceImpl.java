package com.xindaibao.creditrank.cr.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.service.CrResultService;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.creditrank.cr.domain.CrResult;
import com.xindaibao.creditrank.cr.mapper.CrResultMapper;


/**
 * 评分结果ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-05 16:22:54


 * 

 */
 
@Service("crResultService")
public class CrResultServiceImpl extends BaseServiceImpl<CrResult, Long> implements CrResultService {
	
    @Resource
    private CrResultMapper crResultMapper;

	@Override
	public BaseMapper<CrResult, Long> getMapper() {
		return crResultMapper;
	}

	@Override
	public Map<String, Object> findUserResult(Long userId) {
		return crResultMapper.findUserResult(userId);
	}

	@Override
	public List<CrResult> findAllBorrowTypeResult(Long userId) {
		return crResultMapper.findAllBorrowTypeResult(userId);
	}
}