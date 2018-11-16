package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheFinanceContactDetail;
import com.xindaibao.cashloan.cl.mapper.ClMoheFinanceContactDetailMapper;
import com.xindaibao.cashloan.cl.service.ClMoheFinanceContactDetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheFinanceContactDetailStatsService")
public class ClMoheFinanceContactDetailServiceImpl
        extends BaseServiceImpl<ClMoheFinanceContactDetail, Long> implements ClMoheFinanceContactDetailService {

    @Autowired private ClMoheFinanceContactDetailMapper clMoheFinanceContactDetailMapper;

    @Override
    public BaseMapper<ClMoheFinanceContactDetail, Long> getMapper() {
        return clMoheFinanceContactDetailMapper;
    }
}
