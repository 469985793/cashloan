package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheMobileInfo;
import com.xindaibao.cashloan.cl.mapper.ClMoheMobileInfoMapper;
import com.xindaibao.cashloan.cl.service.ClMoheMobileInfoService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheMobileInfoStatsService")
public class ClMoheMobileInfoServiceImpl
        extends BaseServiceImpl<ClMoheMobileInfo, Long> implements ClMoheMobileInfoService {

    @Autowired private ClMoheMobileInfoMapper clMoheMobileInfoMapper;

    @Override
    public BaseMapper<ClMoheMobileInfo, Long> getMapper() {
        return clMoheMobileInfoMapper;
    }
}
