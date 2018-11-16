package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;




@RDBatisDao
public interface SystemCountMapper {
	/**
	 * 统计当日注册用户数量
	 * @param param 
	 * @return
	 */
	Integer countRegister(Map<String, Object> param);

	/**
	 * 统计当日登陆用户数量
	 * @param param 
	 * @return
	 */
	Integer countLogin(Map<String, Object> param);
	
	/**
	 * 统计当日借款申请通过的数量
	 * @param param 
	 * @return
	 */
	double countBorrow(Map<String, Object> param);
	
	/**
	 * 统计当日借款申请通过的数量
	 * @param param 
	 * @return
	 */
	double countBorrowPass(Map<String, Object> param);
	
	/**
	 * 统计当日借款申请放款数量
	 * @param param 
	 * @return
	 */
	Integer countBorrowLoan(Map<String, Object> param);
	
	/**
	 * 统计当日还款量
	 * @param param 
	 * @return
	 */
	Integer countBorrowRepay(Map<String, Object> param);
	
	/**
	 * 统计历史放款总量
	 * @return
	 */
	Integer countBorrowLoanHistory();
	
	/**
	 * 统计历史还款总量
	 * @return
	 */
	Integer countBorrowRepayHistory();
	
	/**
	 * 待还款总额
	 * @return
	 */
	Double sumBorrowNeedRepay();
	
	/**
	 * 逾期未还款总额
	 * @return
	 */
	Double sumBorrowOverdueRepay();
	
	/**
	 * 累计成功借款金额(按地区分组)
	 * @param address 
	 * @return
	 */
	String sumBorrowAmtByProvince(String address);
	
	/**
	 * 累计还款金额(按地区分组)
	 * @return
	 */
	String sumBorrowRepayByProvince(String address);
	
	/**
	 * 累计新增用户(按地区分组)
	 * @return
	 */
	String countRegisterByProvince(String address);
	
	/**
	 * 最近15日每天放款量
	 * @return
	 */
	List<Map<String,Object>> countFifteenDaysLoan();
	
	/**
	 * 还款来源 10代扣，20银行卡转账，30支付宝转账
	 * @return
	 */
	List<Map<String,Object>> countRepaySource();
	
	/**
	 * 最近15日应还款量
	 * @return
	 */
	List<Map<String,Object>> countFifteenDaysNeedRepay();
	
	/**
	 * 最近15日实际还款量
	 * @return
	 */
	List<Map<String,Object>> countFifteenDaysRealRepay();
}
