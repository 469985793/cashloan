package com.xindaibao.cashloan.manage.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.xindaibao.cashloan.cl.model.AlipayDownLoanFile;
import com.xindaibao.cashloan.cl.model.AlipayDownloadUrlQueryModel;
import com.xindaibao.cashloan.cl.model.AlipayModel;
import com.xindaibao.cashloan.cl.model.BaseAliPayModel;
import com.xindaibao.cashloan.cl.model.BorrowRepayLogModel;
import com.xindaibao.cashloan.cl.model.ManageBRepayModel;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.manage.domain.QuartzLog;
import com.xindaibao.cashloan.manage.service.QuartzInfoService;
import com.xindaibao.cashloan.manage.service.QuartzLogService;

@Component
@Lazy(value = false)
public class QuartzBatchRepay implements Job{
	
	
	
	private static final Logger logger = Logger.getLogger(QuartzBatchRepay.class);
	
	/**
	 * 更新支付宝账单，批量确认还款  该功能暂不使用
	 * jdd
	 * @throws ServiceException
	 */
	public void queryUserZmScore() throws ServiceException {
		int msg = 0;
		QuartzInfoService quartzInfoService = (QuartzInfoService)BeanUtil.getBean("quartzInfoService");
		QuartzLogService quartzLogService = (QuartzLogService)BeanUtil.getBean("quartzLogService");
		
		QuartzLog ql = new QuartzLog();
		QuartzInfo qi = quartzInfoService.findByCode("doBatchRepay");
		Map<String,Object> qiData = new HashMap<>();
		qiData.put("id", qi.getId());
		
		ql.setQuartzId(qi.getId());
		ql.setStartTime(DateUtil.getNow());
		try {
			logger.info("进入批量确认还款定期任务。。。。。");
			AlipayDownloadUrlQueryModel model=new AlipayDownloadUrlQueryModel ();
			Date beginDate = new Date();
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Date endDate = dft.parse(dft.format(date.getTime()));
			String bill_date=dft.format(endDate);
			model.setBill_date(bill_date);
			msg=alipayQuery(model);
			if (msg<1) { 
				ql.setTime(DateUtil.getNow().getTime()-ql.getStartTime().getTime());
				ql.setResult("20");
				qiData.put("succeed", qi.getFail()+1);
				logger.error("定时批量还款定期任务修改数据失败");
			}else {
				ql.setTime(DateUtil.getNow().getTime()-ql.getStartTime().getTime());
				ql.setResult("10");
				ql.setRemark("执行成功");
				qiData.put("succeed", qi.getSucceed()+1);
			}
			
			logger.info("定时批量还款定期任务结束...");
		} catch (Exception e) {
			ql.setTime(DateUtil.getNow().getTime()-ql.getStartTime().getTime());
			ql.setResult("20");
			ql.setRemark("执行失败");
			qiData.put("succeed", qi.getFail()+1);
			logger.error(e.getMessage(),e);
		}finally{
			quartzLogService.save(ql);
			quartzInfoService.update(qiData);
		}
	}
	/**
	 * 获取账单文件地址
	 * @throws Exception
	 */
	public int alipayQuery(AlipayDownloadUrlQueryModel model) throws Exception{
		int  reuslt=0;
		logger.info("查询支付宝账单信息。。。。。");
		model.setBill_type("trade");
		BaseAliPayModel alipay=new BaseAliPayModel();
		AlipayClient alipayClient =alipay.defaultAlipayClient(alipay);
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		request.setBizContent(JSONObject.toJSONString(model));
		AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			reuslt=1;
			logger.info("调用成功"+response.getBillDownloadUrl());
		    BorrowRepayService borrowRepayService = (BorrowRepayService)BeanUtil.getBean("borrowRepayService");
		    List<AlipayModel> apliPayList=AlipayDownLoanFile.parseAlipayByUrl(response.getBillDownloadUrl());
			if (apliPayList != null && !apliPayList.isEmpty()) {
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("state", 20);
			    List<ManageBRepayModel> list = borrowRepayService.listAllModel(params);
			    for(AlipayModel pay:apliPayList){
				    for(ManageBRepayModel  repay:list){
				    	//账单中存在未还款的用户信息
				    	if(pay.getRemark().contains(repay.getPhone())||pay.getRemark().contains(repay.getRealName())){
				    		Map<String,Object> param = new HashMap<String,Object>();
				    		param.put("id", repay.getId());
				    		param.put("repayTime",pay.getRepayTime());
				    		param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_ALIPAY_TRANSFER);
				    		param.put("repayAccount", pay.getAccount());
				    		param.put("penaltyAmout", 0);
				    		if(Double.valueOf(pay.getAmount())<repay.getRepayAmount()){
					    		  param.put("state", "20");
					    	}else{
					    		  param.put("state", "10");
					    	}
				    		param.put("amount", pay.getAmount());
//				    		Map<String,Object> resultMap=borrowRepayService.confirmRepay(param);
				    		borrowRepayService.confirmRepay(param);
				    	}
				    }
			    }
			}
		} else {
		 logger.info("调用失败"+response.getMsg()+"===="+response.getSubMsg());
		}
		return reuslt;
	}
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
			try {
				queryUserZmScore();
			} catch (ServiceException e) {
				logger.error(e.getMessage(),e);
			}
		
		
	}
}
