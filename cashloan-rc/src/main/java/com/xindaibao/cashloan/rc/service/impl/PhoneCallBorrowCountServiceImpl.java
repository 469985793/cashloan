package com.xindaibao.cashloan.rc.service.impl;

import javax.annotation.Resource;

import com.xindaibao.cashloan.rc.domain.PhoneCallBorrowCount;
import com.xindaibao.cashloan.rc.mapper.PhoneCallBorrowCountMapper;
import com.xindaibao.cashloan.rc.service.PhoneCallBorrowCountService;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 运营商联系人借款信息统计ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-18 14:49:11




 */
 
@Service("phoneCallBorrowCountService")
public class PhoneCallBorrowCountServiceImpl extends BaseServiceImpl<PhoneCallBorrowCount, Long> implements PhoneCallBorrowCountService {
	
    @Resource
    private PhoneCallBorrowCountMapper phoneCallBorrowCountMapper;

	@Override
	public BaseMapper<PhoneCallBorrowCount, Long> getMapper() {
		return phoneCallBorrowCountMapper;
	}
	
}