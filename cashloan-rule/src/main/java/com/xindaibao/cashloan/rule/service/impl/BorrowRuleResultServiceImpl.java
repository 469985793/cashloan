package com.xindaibao.cashloan.rule.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.rule.domain.BorrowRuleResult;
import com.xindaibao.cashloan.rule.mapper.BorrowRuleResultMapper;
import com.xindaibao.cashloan.rule.service.BorrowRuleResultService;


/**
 * 规则匹配结果ServiceImpl
 */
 
@Service("borrowRuleResultService")
public class BorrowRuleResultServiceImpl extends BaseServiceImpl<BorrowRuleResult, Long> implements BorrowRuleResultService {
	
    private static final Logger logger = LoggerFactory.getLogger(BorrowRuleResultServiceImpl.class);
   
    @Resource
    private BorrowRuleResultMapper borrowRuleResultMapper;

	@Override
	public BaseMapper<BorrowRuleResult, Long> getMapper() {
		return borrowRuleResultMapper;
	}

	@Override
	public Page<BorrowRuleResult> borrowRuleResult(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<BorrowRuleResult> list = borrowRuleResultMapper.listSelective(params);
		return (Page<BorrowRuleResult>)list;
	}
	
}