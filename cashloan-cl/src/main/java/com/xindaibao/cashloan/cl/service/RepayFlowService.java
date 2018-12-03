package com.xindaibao.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.model.kenya.RepayFlow;
import com.xindaibao.cashloan.core.common.service.BaseService;

import java.util.List;

/**
 * 还款记录流水Service
 */
public interface RepayFlowService extends BaseService<RepayFlow, Long>{
    Page<RepayFlow> listFlowModel(Long id, int currentPage, int pageSize);
}
