package com.xindaibao.cashloan.cl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.mapper.RepayFlowMapper;
import com.xindaibao.cashloan.cl.model.kenya.LoanProduct;
import com.xindaibao.cashloan.cl.model.kenya.RepayFlow;
import com.xindaibao.cashloan.cl.service.RepayFlowService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 还款记录ServiceImpl
 */
 
@Service("repayFlowService")
public class RepayFlowServiceImpl extends BaseServiceImpl<RepayFlow, Long> implements RepayFlowService {
	
    private static final Logger logger = LoggerFactory.getLogger(RepayFlowServiceImpl.class);
   
    @Resource
    private RepayFlowMapper repayFlowMapper;

	@Override
	public BaseMapper<RepayFlow, Long> getMapper() {
		return repayFlowMapper;
	}
    @Override
    public Page<RepayFlow> listFlowModel(Long id, int currentPage, int pageSize){
        PageHelper.startPage(currentPage, pageSize);
	    List<RepayFlow> list=repayFlowMapper.listFlowModel(id);
        return (Page<RepayFlow>) list;
    }
}