package com.xindaibao.cashloan.manage.job;

import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.model.BorrowRepayLogModel;
import com.xindaibao.cashloan.cl.model.BorrowRepayModel;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.QueryRepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.service.*;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.model.BorrowModel;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.manage.domain.QuartzInfo;
import com.xindaibao.cashloan.manage.domain.QuartzLog;
import com.xindaibao.cashloan.manage.service.QuartzInfoService;
import com.xindaibao.cashloan.manage.service.QuartzLogService;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tool.util.BeanUtil;
import tool.util.BigDecimalUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 还款提醒
 */
@Component
@Lazy(value = false)
public class QuartzRepayInform implements Job {

	
	private static final Logger logger = Logger.getLogger(QuartzRepayInform.class);

	private String repayInform() throws ServiceException {
		logger.info("进入还款提醒...");
		BorrowRepayService borrowRepayService = (BorrowRepayService) BeanUtil.getBean("borrowRepayService");
		ClSmsService clSmsService = (ClSmsService) BeanUtil.getBean("clSmsService");

		// 查询待还计划，日期为还款日的前一天，状态为 未还款
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("repayTime",DateUtil.dateAddDays(DateUtil.getNow(),1));
		paramMap.put("state", BorrowRepayModel.STATE_REPAY_NO);
		List<BorrowRepay> borrowRepayList = borrowRepayService.findUnRepayIntraday(paramMap);
		logger.info("还款提醒任务，待处理的还款提醒总数为：" + borrowRepayList.size());
		
		String quartzRemark = null;
		int succeed = 0;
		int fail = 0;
		int total = 0;
		for (BorrowRepay borrowRepay : borrowRepayList) {
			logger.info("还款提醒任务，提醒UserId：" + borrowRepay.getUserId() + "开始处理");
			try {
				//发送未逾期短信通知
				clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(), "frontRepayInform");
				succeed++;
				total++;
			} catch (Exception e) {
				fail++;
				total++;
				logger.error(e.getMessage(), e);
			}
		}
		quartzRemark = "处理总数"+total+"个，成功"+succeed+"个，失败"+fail+"个";
		logger.info("还款提醒任务，执行完毕，" + quartzRemark);
		return quartzRemark;

	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
		QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
		// 查询当前任务信息
		QuartzInfo quartzInfo = quartzInfoService.findByCode("doRepayInform");
		Map<String, Object> qiData = new HashMap<>();
		qiData.put("id", quartzInfo.getId());

		QuartzLog quartzLog = new QuartzLog();
		quartzLog.setQuartzId(quartzInfo.getId());
		quartzLog.setStartTime(DateUtil.getNow());
		try {
			String remark = repayInform();
			quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
			quartzLog.setResult("10");
			quartzLog.setRemark(remark);
			qiData.put("succeed", quartzInfo.getSucceed() + 1);
		} catch (Exception e) {
			quartzLog.setResult("20");
			qiData.put("fail", quartzInfo.getFail() + 1);
			logger.error(e.getMessage(), e);
		} finally {
			logger.info("保存还款提醒定时任务执行记录");
			quartzLogService.save(quartzLog);
			quartzInfoService.update(qiData);
		}

	}
}