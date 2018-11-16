package com.xindaibao.cashloan.cl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.mapper.ClBorrowMapper;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.ConfirmPaymentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tool.util.DateUtil;
import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.mapper.PayLogMapper;
import com.xindaibao.cashloan.cl.model.ManagePayLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.service.PayLogService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.model.BorrowModel;


/**
 * 支付记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 16:18:56


 * 

 */
 
@Service("payLogService")
public class PayLogServiceImpl extends BaseServiceImpl<PayLog, Long> implements PayLogService {
	private static Logger logger = LoggerFactory.getLogger(PayLogServiceImpl.class);
	@Resource
	private PayLogMapper payLogMapper;
	@Resource
	private ClBorrowMapper clBorrowMapper;

	@Override
	public BaseMapper<PayLog, Long> getMapper() {
		return payLogMapper;
	}
	
	@Override
	public boolean save(PayLog payLog) {
		payLog.setCreateTime(DateUtil.getNow());
		int result = payLogMapper.save(payLog);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ManagePayLogModel> page(int current, int pageSize,
			Map<String, Object> searchMap) {
		PageHelper.startPage(current, pageSize);
		Page<ManagePayLogModel> page = (Page<ManagePayLogModel>) payLogMapper
				.page(searchMap);
		return page;
	}

	@Override
	public ManagePayLogModel findDetail(Long id) {
		return payLogMapper.findDetail(id);
	}

	@Override
	public boolean auditPay(Long id, String state) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", state);
		int result = payLogMapper.updateSelective(paramMap);

		if (PayLogModel.STATE_AUDIT_PASSED.equals(state)) {
			PayLog payLog = payLogMapper.findByPrimary(id);
			confirmPayment(payLog);
		}

		if (result > 0L) {
			return true;
		}
		return false;
	}
	
	/**
	 * 调用连连支付接口进行支付
	 * @param payLog
	 */
	private void confirmPayment(PayLog payLog){
		Map<String, Object> confirmPayMap = new HashMap<>();
		confirmPayMap.put("payOrderNo", payLog.getOrderNo());
		confirmPayMap.put("confirmCode", payLog.getConfirmCode());
		// 场景设定支付通知地址
		String notifyUrl = "";
		if (PayLogModel.SCENES_LOANS.equals(payLog.getScenes())) {
			notifyUrl = Global.getValue("server_host") + "/pay/lianlian/paymentNotify.htm";
		} else if (PayLogModel.SCENES_PROFIT.equals(payLog.getScenes())) {
			notifyUrl = Global.getValue("server_host") + "/pay/lianlian/profitNotify.htm";
		} else if (PayLogModel.SCENES_REFUND.equals(payLog.getScenes())) {
			notifyUrl = Global.getValue("server_host") + "/pay/lianlian/refundNotify.htm";
		}
		confirmPayMap.put("notifyUrl",notifyUrl);
		
		ConfirmPaymentModel confirmPayment = (ConfirmPaymentModel) LianLianHelper.confirmPayment(confirmPayMap);
		if (confirmPayment.checkReturn()) {
			logger.info("确认支付 " + payLog.getOrderNo());
		} else {
			logger.info("确认付款出错,原因：" + confirmPayment.getRet_msg());
		}
	}

	@Override
	public Map<String, Object> checkPayLogState(Long id, String state) {
		PayLog log = payLogMapper.findByPrimary(id);

		Map<String, Object> check = new HashMap<String, Object>();
		if (!PayLogModel.STATE_PENDING_REVIEW.equals(log.getState())) {
			check.put(Constant.RESPONSE_CODE_MSG, "当前交易记录状态不允许审核！");
		}

		// 若借款不是审核通过或放款失败 则不允许审核通过
		if (PayLogModel.STATE_AUDIT_PASSED.equals(state)
				&& StringUtil.isNotBlank(log.getBorrowId())) {

			Borrow borrow = clBorrowMapper.findByPrimary(log.getBorrowId());
			if (BorrowModel.STATE_AUTO_PASS.equals(borrow.getState())
					|| BorrowModel.STATE_PASS.equals(borrow.getState())
					|| BorrowModel.STATE_REPAY_FAIL.equals(borrow.getState())) {
			} else {
				check.put(Constant.RESPONSE_CODE_MSG, "当前借款状态不允许审核通过！");
			}
		}
		return check;
	}

	@Override
	public PayLog findByOrderNo(String orderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderNo", orderNo);
		return payLogMapper.findSelective(paramMap);
	}
	
	@Override
	public boolean updateSelective(Map<String, Object> paramMap){
		int result  = payLogMapper.updateSelective(paramMap);
		if(result > 0L){
			return true;
		}
		return false;
	}

	@Override
	public PayLog findSelective(Map<String, Object> paramMap) {
		return payLogMapper.findSelective(paramMap);
	}
	
	@Override
	public PayLog findLatestOne(Map<String, Object> paramMap) {
		return payLogMapper.findLatestOne(paramMap);
	}

	@Override
	public PayLog findRepayLatestOne(Long userId ,Long borrowId) {
		// FIXME: 2017/11/16 扣款分手动、自动，查询需重写
		Map<String, Object> payLogMap = new HashMap<String, Object>();
		payLogMap.put("userId", userId);
		payLogMap.put("borrowId", borrowId);
		payLogMap.put("type", PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN);
		payLogMap.put("scenes", PayLogModel.SCENES_ACTIVE_REPAYMENT);
		PayLog repaymentLog = payLogMapper.findLatestOne(payLogMap);

		// FIXME: 2017/11/22 不改变查询代码前提下，无自动扣款记录，再查询手动还款记录
		if(null == repaymentLog ){
			payLogMap.put("type", PayLogModel.TYPE_COLLECT);
			payLogMap.put("scenes", PayLogModel.SCENES_REPAYMENT);
			repaymentLog = payLogMapper.findLatestOne(payLogMap);
		}
		if(null == repaymentLog ){
			payLogMap.put("type", PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN_FAST);
			payLogMap.put("scenes", PayLogModel.SCENES_ACTIVE_REPAYMENT);
			repaymentLog = payLogMapper.findLatestOne(payLogMap);
		}
		return repaymentLog;
	}

	@Override
	public List<PayLog> findCheckList(Map<String, Object> paramMap){
		return payLogMapper.findCheckList(paramMap);
	}

	@Override
	public boolean judge(long borrowId) {
		Map<String,Object> map = new HashMap<>();
		map.put("borrowId", borrowId);
		List<PayLog> plist = payLogMapper.listSelective(map);
		boolean flag = true;
		for (PayLog payLog : plist) {
			if ("10".equals(payLog.getScenes())&&"15".equals(payLog.getState())) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List listPayLog(String params) {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(params)) {
			searchMap = JsonUtil.parse(params, Map.class);
		}
		String type  = StringUtil.isNull(searchMap.get("type"));
		String[] typeArray = type.split(",");
		
		List<String> typeList =  new ArrayList<String>();
		for (String typeStr : typeArray) {
			if (StringUtil.isNotBlank(typeStr)) {
				typeList.add(typeStr);
			}
		}
		searchMap.put("type", typeList);
		List<ManagePayLogModel> list = payLogMapper.page(searchMap);
		return list;
	}

	@Override
	public int doRepaymentNum(long borrowId) {
		return payLogMapper.doRepaymentCount(borrowId);
	}
	
}
