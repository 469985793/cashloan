package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheInfoCheck;
import com.xindaibao.cashloan.cl.mapper.ClMoheInfoCheckMapper;
import com.xindaibao.cashloan.cl.service.ClMoheInfoCheckService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheInfoCheckStatsService")
public class ClMoheInfoCheckServiceImpl
        extends BaseServiceImpl<ClMoheInfoCheck, Long> implements ClMoheInfoCheckService {

    @Autowired private ClMoheInfoCheckMapper clMoheInfoCheckMapper;

    @Override
    public BaseMapper<ClMoheInfoCheck, Long> getMapper() {
        return clMoheInfoCheckMapper;
    }
}
