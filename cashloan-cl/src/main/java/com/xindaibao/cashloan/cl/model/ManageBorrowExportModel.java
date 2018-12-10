package com.xindaibao.cashloan.cl.model;

import java.util.Date;

import com.xindaibao.cashloan.core.domain.Borrow;
import org.activiti.rest.service.api.repository.BaseModelResource;

public class ManageBorrowExportModel extends BaseModelResource {

	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 用户ID
	 */
	private Long uid;
	/**
	 * 用户名
	 */
	private String firstName;
	/**
	 * 订单号
	 */
	private String indentNo;
	/**
	 * 借款金额
	 */
	private Long balance;
	/**
	 * 借款期限
	 */
	private Integer cycle;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 订单状态
	 */
	private Byte status;
	/**
	 * 还款时间
	 */
	private Date lastbackTime;
	/**
	 * 放款时间
	 */
	private Date arriveTime;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 逾期天数
	 */
	private long penaltyDay;
	
	/**
	 * 逾期罚金
	 */
	private Long overdueFee;
	
	/**
	 * 还款时间
	 */
	private Date repayTime;
	
	/**
	 * 还款金额
	 */
	private Long actualbackAmt;
	/**
	 * 导出日期
	 */
	private Date exportTime;
    /**
     * 利息费
     */
    private Integer profit;
    /**
     * 管理费
     */
    private Integer accountManage;
	/**
	 * 状态
	 */
	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getIndentNo() {
		return indentNo;
	}

	public void setIndentNo(String indentNo) {
		this.indentNo = indentNo;
	}

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getLastbackTime() {
		return lastbackTime;
	}

	public void setLastbackTime(Date lastbackTime) {
		this.lastbackTime = lastbackTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getPenaltyDay() {
		return penaltyDay;
	}

	public void setPenaltyDay(long penaltyDay) {
		this.penaltyDay = penaltyDay;
	}

	public Integer getProfit() {
		return profit;
	}

	public void setProfit(Integer profit) {
		this.profit = profit;
	}

	public Integer getAccountManage() {
		return accountManage;
	}

	public void setAccountManage(Integer accountManage) {
		this.accountManage = accountManage;
	}

	public Long getOverdueFee() {
        return overdueFee;
    }

    public void setOverdueFee(Long overdueFee) {
        this.overdueFee = overdueFee;
    }

    public Date getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}

    public Long getActualbackAmt() {
        return actualbackAmt;
    }

    public void setActualbackAmt(Long actualbackAmt) {
        this.actualbackAmt = actualbackAmt;
    }

    public Date getExportTime() {
		return exportTime;
	}

	public void setExportTime(Date exportTime) {
		this.exportTime = exportTime;
	}
}
