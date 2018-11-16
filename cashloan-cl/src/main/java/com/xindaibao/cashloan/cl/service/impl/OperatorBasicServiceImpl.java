package com.xindaibao.cashloan.cl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.OperatorBasic;
import com.xindaibao.cashloan.cl.mapper.OperatorBasicMapper;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.cl.service.OperatorBasicService;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;



/**
 * 运营商信息-基础信息ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-13 16:35:43


 * 

 */
 
@Service("operatorBasicService")
public class OperatorBasicServiceImpl extends BaseServiceImpl<OperatorBasic, Long> implements OperatorBasicService {
	
   
    @Resource
    private OperatorBasicMapper operatorBasicMapper;

	@Override
	public BaseMapper<OperatorBasic, Long> getMapper() {
		return operatorBasicMapper;
	}

	@Override
	public void saveRecords(List<OperatorBasic> basics) throws BussinessException {
		for(OperatorBasic basic : basics){
			operatorBasicMapper.save(basic);
		}
	}

	@Override
	public void countOperatorVoice(Long userId) throws BussinessException {
		
	}

	@Override
	public void countOperatorBorrow(Long userId) throws BussinessException {
		
	}
	
}