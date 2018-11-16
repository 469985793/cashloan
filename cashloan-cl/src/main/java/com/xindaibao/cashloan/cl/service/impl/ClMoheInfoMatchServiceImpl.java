package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheInfoMatch;
import com.xindaibao.cashloan.cl.mapper.ClMoheInfoMatchMapper;
import com.xindaibao.cashloan.cl.service.ClMoheInfoMatchService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheInfoMatchStatsService")
public class ClMoheInfoMatchServiceImpl
        extends BaseServiceImpl<ClMoheInfoMatch, Long> implements ClMoheInfoMatchService {

    @Autowired private ClMoheInfoMatchMapper clMoheInfoMatchMapper;

    @Override
    public BaseMapper<ClMoheInfoMatch, Long> getMapper() {
        return clMoheInfoMatchMapper;
    }
}
