package com.xindaibao.cashloan.cl.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.model.ManageBRepayModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowModel;
import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.Borrow;

/**
 * 还款计划Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 13:42:32


 * 

 */
public interface BorrowRepayService extends BaseService<BorrowRepay, Long>{

	/**
	 * 保存还款计划
	 * @param borrowRepay
	 * @return
	 */
	int save(BorrowRepay borrowRepay);
	
//	/**
//	 * 生成还款计划
//	 * @param borrow
//	 * @return
//	 */
//	boolean genRepayPlan(Borrow borrow);
	
	/**
	 * 生成还款计划
	 * 
	 * @param borrow
	 * @param calcStartTime
	 *            计算开始时间
	 * @return
	 */
	boolean genRepayPlan(Borrow borrow, Date calcStartTime);
	/**
	 * 还款计划 放款 成功之后 银行卡授权
	 * 
	 * @param userId
	 */
	void authSignApply(Long userId);
	
	
	 /**
	  * 后台列表
	  * @param params
	  * @param currentPage
	  * @param pageSize
	  * @return
	  */
	Page<ManageBRepayModel> listModel(Map<String, Object> params, int currentPage,
                                      int pageSize);

	/**
	 * 确认还款生产还款记录
	 * @param param
	 * @return
	 */
	void confirmRepay(Map<String, Object> param);

	/**
	 * 查询所有
	 * @param paramMap
	 * @return
	 */
	List<BorrowRepay> listSelective(Map<String, Object> paramMap);

	/**
	 * 逾期更新信息
	 * @param data
	 * @return
	 */
	int updateLate(LoanRecord data);
	
	/**
	 * 条件更新还款计划数据
	 * @param br
	 * @return
	 */
	int updateSelective(Map<String, Object> paramMap);
	

	/**
	 * 催收借款信息接口
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowModel> listRepayModel(Map<String, Object> params,
                                           int currentPage, int pageSize);

	/**
	 * 逾期未入催
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowModel> listModelNotUrge(Map<String, Object> params,
			int currentPage, int pageSize);

	/**
	 * 查询未还款列表
	 * 
	 * @param params
	 * @return
	 */
	List<BorrowRepay> findUnRepay(Map<String, Object> paramMap);
	
	
	/**
	 * 查询还款计划
	 * 
	 * @param paramMap
	 * @return
	 */
	BorrowRepay findSelective(Map<String, Object> paramMap);

	/**
	 * 查询所有还款信息
	 * @param params
	 * @return
	 */
	List<ManageBRepayModel> listAllModel(Map<String, Object> params);
	/**
	 * 文件解析批量还款
	 * @param repayFile
	 * @param type
	 * @throws IOException 
	 */
	List<List<String>> fileBatchRepay(MultipartFile repayFile, String type) throws Exception;
	
	/**
	 * 主动支付 参数封装
	 * 
	 * @param type
	 * @param amount
	 * @param ip
	 * @param body
	 * @param orderNo
	 * @return
	 */
	Map<String, String> paySdkParams(Long userId,String type, double amount, String ip, String body, String orderNo);
	
	/**
	 * 还款回调
	 * 
	 * @param payLog
	 * @param tradeStatus
	 * @param amount
	 * @param payType
	 */
	void repaymentNotify(PayLog payLog, String logState, String amount, String repayWay, String repayAccount);
	
	/**
	 * 还款（SDK参数封装）
	 * 
	 * @param type：支付类型：50，微信APP；51，支付宝APP；52，连连认证支付
	 * @param borrowId
	 * @param userId
	 * @param ip：微信必传
	 * @return
	 */
	Map<String, String> repayment(String type, Long borrowId, Long userId, String ip);
	

	/**
	 * 还款同步处理
	 * 
	 * @param payType
	 * @param payResult
	 * @param payOrderNo
	 */
	void repaymentReturn(String payType, String payResult, String payOrderNo);

	/**
	 * 当天
	 * 查询未还款列表
	 * @param paramMap
	 * @return
	 */
	List<BorrowRepay> findUnRepayIntraday(Map<String, Object> paramMap);
}
