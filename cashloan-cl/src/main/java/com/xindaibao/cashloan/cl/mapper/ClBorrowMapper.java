package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.model.ClBorrowModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowModel;
import com.xindaibao.cashloan.cl.model.kenya.LoanProduct;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.cl.model.IndexModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowExportModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowTestModel;
import com.xindaibao.cashloan.cl.model.RepayModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.core.domain.Borrow;

/**
 * 借款信息表Dao
 * 
 * @author
 * @version 1.0.0


 * 

 */
@RDBatisDao
public interface ClBorrowMapper extends BaseMapper<Borrow, Long> {

//	/**
//	 * 查询用户未完成的借款
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	List<Borrow> findUserUnFinishedBorrow(@Param("userId") Long userId);

	/**
	 * 首页信息查询
	 * 
	 * @return
	 */
	List<IndexModel> listIndex();

	/**
	 * 订单查询
	 * 
	 * @param searchMap
	 * @return
	 */
	List<ClBorrowModel> findBorrow(Map<String, Object> searchMap);

	/**
	 * 查看借款
	 * 
	 * @param searchMap
	 * @return
	 */
	List<RepayModel> findRepay(Map<String, Object> searchMap);

	/**
	 * 计时任务计算逾期
	 * 
	 * @return
	 */
	List<RepayModel> compute();

	/**
	 * 查询
	 * 
	 * @param params
	 * @return
	 */
	List<ManageBorrowModel> listModel(Map<String, Object> params);

	/**
	 * 查询所有
	 * 
	 * @param searchMap
	 * @return
	 */
	List<ClBorrowModel> listAll(Map<String, Object> searchMap);

	/**
	 * 逾期未入催
	 * 
	 * @param params
	 * @return
	 */
	List<ManageBorrowModel> listModelNotUrge(Map<String, Object> params);

	/**
	 * 查询未还款订单
	 * 
	 * @param borrowMap
	 * @return
	 */
	Borrow findRepayBorrow(Map<String, Object> borrowMap);

	/**
	 * 查询可借款用户
	 * 
	 * @return
	 */
	List<ManageBorrowTestModel> seleteUser();

	/**
	 * 更新借款状态
	 * 
	 * @param satet
	 * @param id
	 * @return
	 */
	int updateState(@Param("state") String state, @Param("id") Long id);

	/**
	 * 借款部分还款信息
	 * 
	 * @param params
	 * @return
	 */
	List<LoanProduct> searchBorrowModelByKenya(Map<String, Object> params);

	/**
	 * 还款计划信息导出
	 *
	 * @param params
	 * @return
	 */
	List<Object> repayLogPlanExport(Map<String, Object> params);

	/**
	 * 查询失败记录
	 * 
	 * @param searchMap
	 * @return
	 */
	Borrow findLast(Map<String, Object> searchMap);

	/**
	 * 查询未还款
	 * 
	 * @param paramMap
	 * @return
	 */
	Borrow findByUserIdAndState(Map<String, Object> paramMap);

	/**
	 * 支付时更新Borrow状态
	 * 
	 * @param paramMap
	 * @return
	 */
	int updatePayState(Map<String, Object> paramMap);

	/**
	 * 统计成功借款次数
	 * 
	 * @param l
	 * @return
	 */
	long countBorrow(long l);

	/**
	 * 导出查询
	 * 
	 * @param params
	 * @return
	 */
	List<ManageBorrowExportModel> listExportModel(Map<String, Object> params);

	/**
	 * 人工复审修改状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	int reviewState(Map<String, Object> map);

	/**
	 * 人工复审修改状态
	 *
	 * @param id
	 * @param state
	 * @return
	 */
	int reviewStatus(Map<String, Object> map);

	/**
	 * 复审通过查询
	 * 
	 * @return
	 */
	List<ManageBorrowModel> listReview(Map<String, Object> params);

	/**
	 * 查询最新一条借款记录
	 * 
	 * @param userId
	 * @return
	 */
	Borrow findLastBorrow(Map<String, Object> borrowMap);

	/**
	 * 查询当天借款总额
	 * 
	 * @param map
	 * @return
	 */
	double borrowAmountSum();

	/**
	 * 查询用户还款成功的记录数
	 */
	int userBorrowCount(long userId);

	/**
	 * 放款审核修改状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	int loanState(Map<String, Object> map);

	/**
	 * 查询用户未完成的借款
	 * 
	 * @param userId
	 * @return
	 */
	List<Borrow> findUserUnFinishedBorrow(Map<String, Object> searchMap);
	

	/**
	 * 支付时更新Borrow状态
	 * 
	 * @param paramMap
	 * @return
	 */
	int syncUpdateState(Map<String, Object> paramMap);

	List<Borrow> findNotInTongdunBorrow();

}
