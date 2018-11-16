package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.QianchengReqlog;
import com.xindaibao.cashloan.cl.model.QianchengReqlogModel;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 浅橙借款申请审核Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-15 11:46:54


 * 

 */
public interface QianchengReqlogService extends BaseService<QianchengReqlog, Long>{
	
	/**
	 * 机审请求记录分页查询后台列表显示
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<QianchengReqlogModel> listQianchengReqlog(Map<String, Object> params,
                                                   int currentPage, int pageSize);
	
	/**
     * 根据订单号查找日志
     * @param orderNo
     * @return
     */
	QianchengReqlog findByOrderNo(String orderNo);
	
	/**
	 * 根据借款申请查找
	 * @param borrowId
	 * @return
	 */
	QianchengReqlog findByBorrowId(Long borrowId);
	
	/**
	 * 更新信息
	 * @param reqLog
	 * @return
	 */
	int update(QianchengReqlog reqLog);
}
