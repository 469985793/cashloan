package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheCallDurationStats2hour;
import com.xindaibao.cashloan.cl.mapper.ClMoheCallDurationStats2hourMapper;
import com.xindaibao.cashloan.cl.service.ClMoheCallDurationStats2hourService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheCallDurationStats2hourStatsService")
public class ClMoheCallDurationStats2hourServiceImpl
        extends BaseServiceImpl<ClMoheCallDurationStats2hour, Long> implements ClMoheCallDurationStats2hourService {

    @Autowired private ClMoheCallDurationStats2hourMapper clMoheCallDurationStats2hourMapper;

    @Override
    public BaseMapper<ClMoheCallDurationStats2hour, Long> getMapper() {
        return clMoheCallDurationStats2hourMapper;
    }
}
