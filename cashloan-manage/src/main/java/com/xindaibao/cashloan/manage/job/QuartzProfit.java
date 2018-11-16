package com.xindaibao.cashloan.manage.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.manage.domain.QuartzInfo;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import tool.util.BeanUtil;
import tool.util.DateUtil;

import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.domain.ProfitAmount;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.PaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.service.BankCardService;
import com.xindaibao.cashloan.cl.service.PayLogService;
import com.xindaibao.cashloan.cl.service.ProfitAmountService;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.util.OrderNoUtil;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.model.UserBaseInfoModel;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.manage.domain.QuartzLog;
import com.xindaibao.cashloan.manage.service.QuartzInfoService;
import com.xindaibao.cashloan.manage.service.QuartzLogService;

/**
 * 自动奖励发放
 */
@Component
@Lazy(value = false)
public class QuartzProfit implements Job {

	private static final Logger logger = Logger.getLogger(QuartzProfit.class);

	/**
	 * 自动奖励发放
	 * 
	 * @throws ServiceException
	 */
	public String profit() {
		ProfitAmountService profitAmountService = (ProfitAmountService) BeanUtil.getBean("profitAmountService");
		BankCardService bankCardService = (BankCardService) BeanUtil.getBean("bankCardService");
		UserBaseInfoService userBaseInfoService = (UserBaseInfoService) BeanUtil.getBean("userBaseInfoService");
		PayLogService payLogService = (PayLogService) BeanUtil.getBean("payLogService");


		List<ProfitAmount> profitAmountList = profitAmountService.listNoCash();

		String quartzRemark = null;
		int succeed = 0;
		int fail = 0;
		int total = 0;
		for (ProfitAmount profitAmount : profitAmountList) {
			try {
				double amountGrantMin = Global.getDouble("amount_grant_min");//奖金发放下限
				//奖金达到指定额度才给予发放
				if (profitAmount.getNoCashed()>=amountGrantMin) {
					BankCard bankCard = bankCardService.getBankCardByUserId(profitAmount.getUserId());
					UserBaseInfo baseInfo = userBaseInfoService.findByUserId(profitAmount.getUserId());
					if(baseInfo != null && UserBaseInfoModel.USER_STATE_BLACK.equals(baseInfo.getState())){
						continue;
					}
					String orderNo = OrderNoUtil.getSerialNumber();
					Date date = DateUtil.getNow();
					
					Map<String, Object> paymentMap = new HashMap<>();
					paymentMap.put("payTime", date);
					paymentMap.put("amount", profitAmount.getNoCashed());
					paymentMap.put("cardNo", bankCard.getCardNo());
					paymentMap.put("realName", baseInfo.getRealName());
					paymentMap.put("orderMemoInfo", "奖励分润" + profitAmount.getId() + "付款");
					paymentMap.put("notifyUrl", Global.getValue("server_host") + "/pay/lianlian/profitNotify.htm");
					PaymentModel payment = (PaymentModel) LianLianHelper.payment(paymentMap);
					
					PayLog payLog = new PayLog();
					payLog.setOrderNo(payment.getNo_order());
					payLog.setUserId(profitAmount.getUserId());
					payLog.setAmount(payment.getAmount());
					payLog.setCardNo(bankCard.getCardNo());
					payLog.setBank(bankCard.getBank());
					payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
					payLog.setType(PayLogModel.TYPE_PAYMENT);
					payLog.setScenes(PayLogModel.SCENES_PROFIT);
					if (payment.checkReturn()) { // 已生成连连支付单，付款处理中（交易成功，不是指付款成功，是指流程正常）
						payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
					} else if ("4002".equals(payment.getRet_code())
							|| "4003".equals(payment.getRet_code())
							|| "4004".equals(payment.getRet_code())) { // 疑似重复订单，待人工审核
						payLog.setState(PayLogModel.STATE_PENDING_REVIEW);
						payLog.setConfirmCode(payment.getConfirm_code());
						payLog.setUpdateTime(DateUtil.getNow());
					} else if ("4006".equals(payment.getRet_code()) // 敏感信息加密异常
							|| "4007".equals(payment.getRet_code()) // 敏感信息解密异常
							|| "4009".equals(payment.getRet_code())) { // 验证码异常
						payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
					} else {
						payLog.setState(PayLogModel.STATE_PAYMENT_FAILED);
						payLog.setUpdateTime(DateUtil.getNow());
					}
					payLog.setRemark(payment.getRet_msg());
					payLog.setPayReqTime(date);
					payLog.setCreateTime(DateUtil.getNow());
					payLogService.save(payLog);
				}
				succeed++;
				total++;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				fail++;
				total++;
			}
		}
		quartzRemark = "执行总次数"+total+",成功"+succeed+"次,失败"+fail+"次";
		return quartzRemark;

	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
		QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
		// 查询当前任务信息
		QuartzInfo quartzInfo = quartzInfoService.findByCode("doProfit");
		QuartzLog quartzLog = new QuartzLog();
		Map<String, Object> qiData = new HashMap<String, Object>();
		qiData.put("id", quartzInfo.getId());
		quartzLog.setQuartzId(quartzInfo.getId());
		quartzLog.setStartTime(DateUtil.getNow());

		try {

			String remark = profit();

			quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
			quartzLog.setResult("10");
			quartzLog.setRemark(remark);
			qiData.put("succeed", quartzInfo.getSucceed() + 1);
			logger.info("自动扣款完成...");
		} catch (Exception e) {
			quartzLog.setResult("20");
			qiData.put("fail", quartzInfo.getFail() + 1);
			logger.error(e.getMessage(), e);
		} finally {
			logger.info("保存定时任务日志");
			quartzLogService.save(quartzLog);
			quartzInfoService.update(qiData);
		}

	}
}