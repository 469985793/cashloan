package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.ClMoheData;
import com.xindaibao.cashloan.cl.domain.ClMoheRiskContactStats;
import com.xindaibao.cashloan.core.common.service.BaseService;

public interface ClMoheRiskContactStatsService extends BaseService<ClMoheRiskContactStats, Long> {


    void saveStatsByRiskType(ClMoheData clMoheData);
}