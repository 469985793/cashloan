package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheAllContactDetail;
import com.xindaibao.cashloan.cl.mapper.ClMoheAllContactDetailMapper;
import com.xindaibao.cashloan.cl.service.ClMoheAllContactDetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheAllContactDetailStatsService")
public class ClMoheAllContactDetailServiceImpl
        extends BaseServiceImpl<ClMoheAllContactDetail, Long> implements ClMoheAllContactDetailService {

    @Autowired private ClMoheAllContactDetailMapper clMoheAllContactDetailMapper;

    @Override
    public BaseMapper<ClMoheAllContactDetail, Long> getMapper() {
        return clMoheAllContactDetailMapper;
    }
}
