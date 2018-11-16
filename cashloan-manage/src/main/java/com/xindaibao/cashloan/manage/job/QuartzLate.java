package com.xindaibao.cashloan.manage.job;

import java.math.BigDecimal;
import java.util.*;

import com.xindaibao.cashloan.cl.Util.SmsCmSendUtil;
import com.xindaibao.cashloan.cl.model.kenya.LoanProduct;
import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;
import com.xindaibao.cashloan.cl.Util.SmsCmSendUtil;
import com.xindaibao.cashloan.manage.domain.QuartzInfo;
import com.xindaibao.cashloan.manage.model.OverDueSMSModel;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import tool.util.BeanUtil;
import tool.util.BigDecimalUtil;
import tool.util.DateUtil;
import tool.util.StringUtil;

import com.xindaibao.cashloan.cl.domain.BorrowProgress;
import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.domain.UrgeRepayOrder;
import com.xindaibao.cashloan.cl.model.BorrowRepayModel;
import com.xindaibao.cashloan.cl.model.UrgeRepayOrderModel;
import com.xindaibao.cashloan.cl.service.BorrowProgressService;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.cl.service.ClSmsService;
import com.xindaibao.cashloan.cl.service.UrgeRepayOrderService;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.model.BorrowModel;
import com.xindaibao.cashloan.manage.domain.QuartzLog;
import com.xindaibao.cashloan.manage.service.QuartzInfoService;
import com.xindaibao.cashloan.manage.service.QuartzLogService;


@Component
@Lazy(value = false)
public class QuartzLate implements Job{
	
	private static final Logger logger = Logger.getLogger(QuartzLate.class);
	
	/**
	 * 定时计算逾期
	 * @throws ServiceException 
	 */
	public String late() throws ServiceException {
		BorrowRepayService borrowRepayService = (BorrowRepayService)BeanUtil.getBean("borrowRepayService");
		BorrowProgressService borrowProgressService = (BorrowProgressService)BeanUtil.getBean("borrowProgressService");
		ClBorrowService clBorrowService = (ClBorrowService)BeanUtil.getBean("clBorrowService");
		UrgeRepayOrderService urgeRepayOrderService = (UrgeRepayOrderService)BeanUtil.getBean("urgeRepayOrderService");
		ClSmsService clSmsService = (ClSmsService)BeanUtil.getBean("clSmsService");


		logger.info("进入逾期计算...");
		String quartzRemark = null;
		int succeed = 0;
		int fail = 0;
		int total = 0;
		Map<String,Object> paramMap = new HashMap<>();
		List stateList = new ArrayList();
		stateList.add(BorrowModel.STATE_REPAY);
		stateList.add(21);
		paramMap.put("stateList",stateList);
		List<LoanProduct> list = clBorrowService.listBorrowModel(paramMap,0,0);
		boolean result = false;

		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				try {
					long day = DateUtil.daysBetween(new Date(), list.get(i).getShouldbackTime());
					logger.info("-----------------------day:"+day);
						if (day<0) {
							double amout = list.get(i).getBalance();
							double percent = 0;
							Map<String,Object> data = new HashMap<>();
							if(Math.abs(day) <= 15){
								percent = 0.01;
							}else if(Math.abs(day) <= 30 && Math.abs(day) >= 15){
								percent = 0.02;
							}else if(Math.abs(day)>30){
								percent = 0.5;
							}
							double overdueFee = penaltyFee(amout,percent);
							amout = repaymentAmount(amout,overdueFee);
							LoanRecord br = new LoanRecord();
							br.setId(list.get(i).getId());
							br.setStatus((byte)21);//修改状态为逾期
							br.setOverdueFee(Long.valueOf(new Double(overdueFee+1000l).longValue()));
							br.setUpdatedTime(new Date());
							logger.info("id--" + list.get(i).getId() + " ==> 已经逾期 " + Math.abs(day) + " 天,逾期费用 " + amout + "元");
							int msg  = borrowRepayService.updateLate(br);

							if (msg>0) {
								//保存逾期进度
								logger.info("---------添加逾期进度---------");
								BorrowProgress bp = new BorrowProgress();
								bp.setBorrowId(list.get(i).getId());
								bp.setCreateTime(new Date());
								bp.setRemark("您已逾期,请尽快还款");
								bp.setState(BorrowModel.STATE_DELAY);
								bp.setUserId(list.get(i).getUid());
								borrowProgressService.save(bp);

								data = new HashMap<>();
								data.put("id", list.get(i).getId());
								data.put("state", BorrowModel.STATE_DELAY);
								msg = clBorrowService.updateSelective(data);
								logger.info("---------添加逾期结束---------");

								//催收计划
								logger.info("---------修改催收计划start-------");
								UrgeRepayOrder uro =  urgeRepayOrderService.findByBorrowId(list.get(i).getId());
								if (StringUtil.isBlank(uro)) {
									urgeRepayOrderService.saveOrder(list.get(i).getId());
									clSmsService.overdue(list.get(i).getId());//逾期第一天发送短信通知
								}else {
									Map<String,Object> uroMap = new HashMap<>();
									uroMap.put("penaltyAmout", Long.valueOf(new Double(overdueFee+1000l).longValue()));
									uroMap.put("penaltyDay", Math.abs(day));
									uroMap.put("id", uro.getId());
									uroMap.put("level", UrgeRepayOrderModel.getLevelByDay(Long.valueOf(Math.abs(day))));
									msg = urgeRepayOrderService.updateLate(uroMap);
								}
								logger.info("---------修改催收计划end-------");
							}else {
								logger.error("定时计算逾期任务修改数据失败");
							}
							//发送逾期短信
							logger.info("---------发送逾期start---------");
							if(Math.abs(day)==1){
								//clSmsService.overdue(list.get(i).getId(),"overdue1");//逾期第1天
								result = SmsCmSendUtil.getInstance().send(list.get(i).getMobile(), OverDueSMSModel.OVER_DUE_ONE);
							}else if(Math.abs(day)==3){
								//clSmsService.overdue(list.get(i).getId(),"overdue3");//逾期第3天
								result = SmsCmSendUtil.getInstance().send(list.get(i).getMobile(),OverDueSMSModel.OVER_DUE_THREE);
							}else if(Math.abs(day)==7){
								//clSmsService.overdue(list.get(i).getId(),"overdue7");//逾期第7天
								result = SmsCmSendUtil.getInstance().send(list.get(i).getMobile(),OverDueSMSModel.OVER_DUE_SAVEN);
							}else if(Math.abs(day)==14){
								//clSmsService.overdue(list.get(i).getId(),"overdue14");//逾期第14天
								result = SmsCmSendUtil.getInstance().send(list.get(i).getMobile(),OverDueSMSModel.OVER_DUE_TWOWEEK);
							} else if(Math.abs(day)==28){
								//clSmsService.overdue(list.get(i).getId(),"overdue28");//逾期第14天
								result = SmsCmSendUtil.getInstance().send(list.get(i).getMobile(),OverDueSMSModel.OVER_DUE_TWOWEEK);
							}
							if(result == true){
								logger.info("逾期短信发送成功。");
							}else{
								logger.error("短信发送失败。请联系管理员!");
							}
							logger.info("---------发送逾期end---------");
						}

					//还款日前一天发送提醒短信
					String SMS = "Dear friend,tomorrow will be due date for the loan you applied for at   "+list.get(i).getShouldbackTime()+". Please confirm that your M-Pesa account is fully funded before 18:00PM  tomorrow to avoid additional charges for overdue repayment.[JumboPesa]";
					if(day==1){
						result = SmsCmSendUtil.getInstance().send(list.get(i).getMobile(),SMS);
						if(result == true){
							logger.info("提醒短信发送成功。");
						}else{
							logger.error("短信发送失败。请联系管理员!");
						}
					}
					succeed++;
					total++;
				} catch (Exception e) {
					fail ++;
					total++;
					logger.error(e.getMessage(),e);
				}
			}
		}
			
		logger.info("逾期计算结束...");
		quartzRemark = "执行总次数"+total+",成功"+succeed+"次,失败"+fail+"次";
		return quartzRemark;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		QuartzInfoService quartzInfoService = (QuartzInfoService)BeanUtil.getBean("quartzInfoService");
		QuartzLogService quartzLogService = (QuartzLogService)BeanUtil.getBean("quartzLogService");
		QuartzLog ql = new QuartzLog();
		Map<String,Object> qiData = new HashMap<>();
		QuartzInfo qi = quartzInfoService.findByCode("doLate");
		try {
			qiData.put("id", qi.getId());
			ql.setQuartzId(qi.getId());
			ql.setStartTime(DateUtil.getNow());
			
			String remark = late();
			
			ql.setTime(DateUtil.getNow().getTime()-ql.getStartTime().getTime());
			ql.setResult("10");
			ql.setRemark(remark);
			qiData.put("succeed", qi.getSucceed()+1);
			
		}catch (Exception e) {
			ql.setResult("20");
			qiData.put("fail", qi.getFail()+1);
			logger.error(e.getMessage(),e);
		}finally{
			logger.info("保存定时任务日志");
			quartzLogService.save(ql);
			quartzInfoService.update(qiData);
		}
	}

	//逾期费计算
	public double penaltyFee(double amount,double percent){
		BigDecimal amounts = new BigDecimal(new Double(amount).toString());
		BigDecimal percents = new BigDecimal(new Double(percent).toString());
		return new Double(amounts.multiply(percents).doubleValue());
	}

	//应还总额计算
	public double repaymentAmount(double amount,double balace){
		BigDecimal amounts = new BigDecimal(new Double(amount).toString());
		BigDecimal balaces = new BigDecimal(new Double(balace).toString());
		return new Double(amounts.add(balaces).doubleValue());
	}
	
}
