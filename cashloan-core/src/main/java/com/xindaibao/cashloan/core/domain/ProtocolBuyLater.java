package com.xindaibao.cashloan.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 借款后协议实体
 */
 public class ProtocolBuyLater implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单号
	 */
	private String order_no;

	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 用户手机号
	 */
	private String userPhone;
	/**
	 * 用户身份证
	 */
	private String userIdCard;
	/**
	 * 用户地址
	 */
	private String userAddress;

	/**
	 * 本金
	 */
	private BigDecimal borrowAmount;

	/**
	 * 本金大写
	 */
	private String borrowAmountCapital;

	/**
	 * 放款日
	 */
	private String loanDate;

	/**
	 * 到期还款日
	 */
	private String repayDate;

	/**
	 * 借款期限
	 */
	private String timeLimit;

	/**
	 * 到期还款总额(借款利息+服务费+信息认证费)
	 */
	private BigDecimal totalFee;

	/**
	 *到期还款总额大写
	 */
	private String totalFeeCapital;

	/**
	 * 用户卡号
	 */
	private String cardNo;

	/**
	 * 开户行
	 */
	private String bankName;

	/**
	 * 服务费
	 */
	private BigDecimal serviceFee;



	public ProtocolBuyLater() {
		super();
	}

	public ProtocolBuyLater(String order_no, String userName, String userPhone, String userIdCard, String userAddress,
							BigDecimal borrowAmount, String borrowAmountCapital, String loanDate, String repayDate, String timeLimit,
							BigDecimal totalFee, String totalFeeCapital, String cardNo, String bankName, BigDecimal serviceFee) {
		super();
		this.order_no = order_no;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userIdCard = userIdCard;
		this.userAddress = userAddress;
		this.borrowAmount = borrowAmount;
		this.borrowAmountCapital = borrowAmountCapital;
		this.loanDate = loanDate;
		this.repayDate = repayDate;
		this.timeLimit = timeLimit;
		this.totalFee = totalFee;
		this.totalFeeCapital = totalFeeCapital;
		this.cardNo = cardNo;
		this.bankName = bankName;
		this.serviceFee = serviceFee;

	}


	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserIdCard() {
		return userIdCard;
	}

	public void setUserIdCard(String userIdCard) {
		this.userIdCard = userIdCard;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public BigDecimal getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public String getBorrowAmountCapital() {
		return borrowAmountCapital;
	}

	public void setBorrowAmountCapital(String borrowAmountCapital) {
		this.borrowAmountCapital = borrowAmountCapital;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getTotalFeeCapital() {
		return totalFeeCapital;
	}

	public void setTotalFeeCapital(String totalFeeCapital) {
		this.totalFeeCapital = totalFeeCapital;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
}
