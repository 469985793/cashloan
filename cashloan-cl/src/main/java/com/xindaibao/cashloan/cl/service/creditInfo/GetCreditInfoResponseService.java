package com.xindaibao.cashloan.cl.service.creditInfo;


import com.xindaibao.cashloan.cl.model.CreditInfo.CreditInfoLog;
import com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg.CreditInfoResponse;
import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/2
 */
public interface GetCreditInfoResponseService extends BaseService<CreditInfoLog, Long> {

    CreditInfoResponse getCreditInfoResponse(String nationalId, LoanRecord loanRecord) throws Exception;
}
