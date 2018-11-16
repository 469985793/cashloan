package com.xindaibao.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.TongdunReqLog;
import com.xindaibao.cashloan.cl.model.TongdunReqLogModel;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.rc.domain.TppBusiness;

/**
 * 同盾第三方请求记录Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-26 15:26:56




 */
public interface TongdunReqLogService extends BaseService<TongdunReqLog, Long>{
	/**
	 * 请求同盾返回结果
	 * @param userId
	 * @param borrow
	 * @return
	 */
	int preloanApplyRequest(Long userId, Borrow borrow,TppBusiness bussiness,String mobileType);
	/**
	 * 根据订单号查询同盾审核报告
	 * @param orderId
	 * @param bussiness
	 * @return
	 */
	String preloanReportRequest(String orderId,TppBusiness bussiness,String mobileType);
	/**
	 * 同盾请求记录列表
	 */
	Page<TongdunReqLogModel> pageListModel(Map<String, Object> params, int current,
                                           int pageSize);
	/**
	 * 同盾请求记录详细信息
	 * @param id
	 * @return
	 */
	TongdunReqLogModel getModelById(long id);
	/**
	 * 同盾请求记录查询
	 * @param params
	 * @return
	 */
	List<TongdunReqLogModel> listByMap(Map<String, Object> params);

}
