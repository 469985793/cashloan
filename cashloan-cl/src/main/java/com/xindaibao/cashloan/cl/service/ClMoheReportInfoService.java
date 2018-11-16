package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.ClMoheReportInfo;
import com.xindaibao.cashloan.cl.model.mohe.MoheParamHolder;
import com.xindaibao.cashloan.core.common.service.BaseService;


/**
 * 魔盒
 *
 */
public interface ClMoheReportInfoService extends BaseService<ClMoheReportInfo, Long> {

    void runMohe(MoheParamHolder moheParamHolder);
    
   

    /**
     *
     * @param userId
     * @param taskId
     * @param resp 接口返回JSON 数据
     */
    void processResponseData(Long userId, String taskId, String resp);

}