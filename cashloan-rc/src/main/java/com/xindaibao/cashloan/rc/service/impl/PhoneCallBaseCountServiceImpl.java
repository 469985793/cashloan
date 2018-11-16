package com.xindaibao.cashloan.rc.service.impl;

import javax.annotation.Resource;

import com.xindaibao.cashloan.rc.domain.PhoneCallBaseCount;
import com.xindaibao.cashloan.rc.mapper.PhoneCallBaseCountMapper;
import com.xindaibao.cashloan.rc.service.PhoneCallBaseCountService;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 运营商通话信息统计ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-18 14:34:09




 */
 
@Service("phoneCallBaseCountService")
public class PhoneCallBaseCountServiceImpl extends BaseServiceImpl<PhoneCallBaseCount, Long> implements PhoneCallBaseCountService {
   
    @Resource
    private PhoneCallBaseCountMapper phoneCallBaseCountMapper;

	@Override
	public BaseMapper<PhoneCallBaseCount, Long> getMapper() {
		return phoneCallBaseCountMapper;
	}
	
}