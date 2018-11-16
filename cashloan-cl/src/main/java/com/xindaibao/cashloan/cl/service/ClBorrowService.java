package com.xindaibao.cashloan.cl.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.model.BorrowProgressModel;
import com.xindaibao.cashloan.cl.model.ClBorrowModel;
import com.xindaibao.cashloan.cl.model.IndexModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowTestModel;
import com.xindaibao.cashloan.cl.model.RepayModel;
import com.xindaibao.cashloan.cl.model.kenya.LoanProduct;
import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.Borrow;

/**
 * 借款信息表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 10:13:31


 * 

 */
public interface ClBorrowService extends BaseService<Borrow, Long>{
	
	/**
	 * 借款规则审核
	 * @param borrow
	 * @param adaptedId
	 * @return
	 */
	String verifyBorrow(Borrow borrow, String adaptedId);
	
	/**
	 * 保存浅橙返回信息
	 * @param qcRsMsg
	 * @param borrow
	 */
	void saveQcResult(String qcRsMsg,Borrow borrow);
	
	/**
	 * 判断是否可以借款
	 * @param borrow
	 * @param tradePwd
	 * @return
	 */
	boolean isCanBorrow(Borrow borrow,String tradePwd);


	/**
	 * 保存借款申请
	 * 
	 * @param borrow
	 * @param orderNo 订单号  
	 * @param renewMark 续借标位
	 * @return
	 */
	Borrow saveBorrow(Borrow borrow, String orderNo, Integer renewMark ,double renewAmount);
	
	/**
	 * 修改借款状态
	 * @param id
	 * @param state
	 * @return
	 */
	int modifyState(long id, String state) ;

	/**
	 * 添加借款进度
	 * 
	 * @param borrow
	 * @param state
	 */
	void savePressState(Borrow borrow, String state);

	/**
	 * 信用额度修改
	 * 
	 * @param borrow
	 * @param state
	 */
	int modifyCredit(Long userId, double amount, String type);

	/**
	 * 首页信息查询
	 * @param userId 
	 * @return
	 */
	Map<String,Object> findIndex(String userId);
	
	/**
	 * 选择借款金额和期限
	 * app里选择借款金额和期限，返回实际到账金额、服务费、服务费明细
	 * @param amount
	 * @param timeLimit
	 * @return
	 */
	Map<String, Object> choice(double amount, String timeLimit);
	
	/**
	 * 查询所有借款费用信息
	 * @return
	 */
	List<Map<String,Object>> choices();
	
	/**
	 * 查询
	 * @param searchMap
	 * @return
	 */
	List<Borrow> findBorrowByMap(Map<String, Object> searchMap);

	/**
	 * 查询最新10条借款信息
	 * @return
	 */
	List<IndexModel> listIndex();

	/**
	 * 借款记录查看
	 * @param searchMap
	 * @return
	 */
	List<RepayModel> findRepay(Map<String, Object> searchMap);

	/**
	 * 分页查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ClBorrowModel> page(Map<String, Object> searchMap, int current,
                             int pageSize);
	
	/**
	 * 关联用户的借款分页查询后台列表显示
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowModel> listModel(Map<String, Object> params,
                                      int currentPage, int pageSize);

	/**
	 * 修改数据
	 * @param data
	 * @return
	 */
	int updateSelective(Map<String, Object> data);

	/**
	 * 匹配规则查询
	 * @param borrowId
	 * @return
	 */
	Map<String, Object> findResult(long borrowId);
    
    /**
     * 查询可借款用户
     * @return
     */
	List<ManageBorrowTestModel> seleteUser();

	/**
	 * 放款
	 * @param borrow
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	void borrowLoan(Borrow borrow,Date date) ;
	
	/**
	 * 后台人工复审功能
	 * @param borrowId
	 * @param state
	 * @param remark
	 * @return 
	 */
	int manualVerifyBorrow(Long borrowId, String state, String remark);
	
	/**
	 * 借款部分还款信息
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<LoanProduct> listBorrowModel(Map<String, Object> params,
									  int currentPage, int pageSize);

	LoanProduct getBorrowModelForIndentNo(Map<String, Object> params);
	 /**
	  * 借款详细信息
	  * @param borrowId
	  * @return
	  */
	ManageBorrowModel getModelByBorrowId(long borrowId);

	/**
	 * 查询一条失败记录
	 * @param searchMap
	 * @return
	 */
	Borrow findLast(Map<String, Object> searchMap);

	/**
	 * 借款进度显示
	 * @param borrow
	 * @param pageFlag
	 * @return
	 */
	List<BorrowProgressModel> borrowProgress(long borrowId);
	
	/**
	 * 支付时更新状态
	 * @return 
	 * @return
	 */
	void updatePayState(Map<String, Object> paramMap);

	/**
	 * 主键查询借款
	 * @param borrowId
	 * @return
	 */
	Borrow findByPrimary(Long borrowId);
	
	/**
	 * 新增借款申请业务处理
	 * @param borrow
	 * @return
	 */
	Borrow rcBorrowApply(Borrow borrow,String tradePwd,String mobileType) throws Exception;
	
	/**
	 * 借款规则审核
	 * @param borrowId
	 */
	void rcBorrowRuleVerify(Long borrowId);
	
	/**
	 * 统计接口接口审核结果
	 * @param state
	 * @param desc
	 * @param borrowId
	 * @param nid
	 * @return
	 */
	void syncSceneBusinessLog(Long borrowId,String nid,int count);
	
	/**
	 * 接口异步通知时更新
	 * @param state
	 * @param desc
	 * @param borrowId
	 * @param nid
	 */
	void syncSceneBusinessLog(String state, String desc,Long borrowId,String nid);

	/**
	 * 查询借款
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List listBorrow(Map<String, Object> params);
	
	/**
	 * 获取风控数据并审核
	 * @param borrowId
	 */
	void verifyBorrowData(long borrowId,String mobileType);
	
	/**
	 * 重新获取风控数据并审核
	 * @param borrowId
	 */
	void reVerifyBorrowData(Long borrowId);

	/**
	 * 复审通过查询
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowModel> listReview(Map<String,Object> params,int currentPage, int pageSize);

	/**
	 * 查询是未完成借款
	 * @param long1
	 * @return
	 */
	List<Borrow> findUserUnFinishedBorrow(long userId);

	/**
	 * 查询最新的一条借款记录
	 * @param userId
	 * @return
	 */
	Borrow findLastBorrow(Map<String, Object> borrowMap);
	
	/**
	 * 
	 * @description 审核放款
	 * @param borrowId
	 * @param state
	 * @param remark
	 * @author
	 * @return void
	 * @since  1.0.0
	 */
	int auditBorrowLoan(Long borrowId, String state, String remark,Long userId);
	
	/**
	 * 
	 * @param borrow
	 * @return
	 */
	boolean isCanRenew(Borrow borrow);

	/**
	 * 更新借款表、添加借款进度状态
	 * 
	 * @param borrowId
	 * @param userId
	 * @param state
	 * @return
	 */
	int updateBorrow(long borrowId, long userId, String state);
	
	/**
	 * 支付同步返回
	 * 
	 * @param payType
	 * @param payResult
	 * @param payOrderNo
	 */
	boolean renewReturn(String payType, String payResult, String payOrderNo);
	
	/**
	 * 条件查询
	 * @param paramMap
	 * @return
	 */
	List<Borrow> listSelective(Map<String, Object> paramMap);
	
	
	/**
	 * 支付服务费成功，生成续借订单
	 * 
	 * @param borrowId
	 *            原订单borrowId]
	 * @param payState
	 *            支付状态
	 * @param repayOrderNo
	 *            支付服务费订单号
	 * @param payType
	 *            支付方式
	 * @param repayAccount
	 *            支付账户
	 * @param repayAmount
	 *            服务费金额
	 */
	void renewNotify(Long borrowId, String payState, String repayOrderNo, String payType, String repayAccount,
			String repayAmount);
	
	/**
     * 查询续借列表
     * 
     * @param paramMap
     * @param current
     * @param pageSize
     * @return
     */
    Page<Borrow> findRenewBorrowPage(Map<String, Object> paramMap, int current, int pageSize);

	LoanRecord findByPrimaryforKenya(long id);

	/**
	 * 查询用户借款信息
	 */
	ClBorrowModel findBorrow(Long borrowId);

}
