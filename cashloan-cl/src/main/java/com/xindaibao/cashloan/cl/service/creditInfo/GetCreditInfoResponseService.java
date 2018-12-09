package com.xindaibao.cashloan.cl.service.creditInfo;


import com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg.CreditInfoResponse;
import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/2
 */
public interface GetCreditInfoResponseService {

    CreditInfoResponse getCreditInfoResponse(String nationalId, LoanRecord loanRecord) throws Exception;
}
