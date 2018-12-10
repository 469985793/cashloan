package com.xindaibao.cashloan.cl.mapper;

import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RDBatisDao
public interface LoanRecordMapper extends BaseMapper<LoanRecord, Long> {

    int updateLate(LoanRecord data);

    LoanRecord findByPrimary(long id);


    int updateParam(Map<String, Object> paramMap);

    int loanApplicationCount();

    int throughCount();

    double loanPassThroughRate();

    int loanAmount();

    int reimbursementAmount();

    int totalHistoricalLoan();

    int totalReimbursementCount();

    int totalAmountRepaid();

    int overdueAmountPrincipal();

    int updateStatus(Map<String, Object> paramMap);

    List<LoanRecord> selectCreditLoan();
}