package com.xindaibao.cashloan.cl.mapper;

import com.xindaibao.cashloan.cl.model.kenya.RepayFlow;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;

@RDBatisDao
public interface RepayFlowMapper extends BaseMapper<RepayFlow, Long> {
    List<RepayFlow> listFlowModel(Long id);

    /**
     * 插入还款流水表
     * @param repayFlow
     * @return
     */
    Integer saveRepayRecord(RepayFlow repayFlow);
}