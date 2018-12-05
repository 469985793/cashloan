package com.xindaibao.cashloan.cl.mapper;

import com.xindaibao.cashloan.cl.model.kenya.RepayRecord;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import org.apache.ibatis.annotations.Param;

@RDBatisDao
public interface RepayRecordMapper extends BaseMapper<RepayRecord, Long> {
    /**
     * 根据贷款表id查找
     * @param loanRecordId
     * @return
     */
    RepayRecord findByLoanRecordId(@Param("loanRecordId")Long loanRecordId);
    /**
     * 插入还款记录表
     * @param repayRecord
     * @return
     */
    Integer saveLoanRecord(RepayRecord repayRecord);
    /**
     * 更新还款记录表
     * @param repayRecord
     * @return
     */
    Integer updateLoanRecord(RepayRecord repayRecord);
}