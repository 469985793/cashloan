package com.xindaibao.cashloan.manage.job;

import java.util.HashMap;
import java.util.Map;

import com.xindaibao.cashloan.manage.domain.QuartzInfo;
import com.xindaibao.cashloan.manage.domain.QuartzLog;
import com.xindaibao.cashloan.manage.service.QuartzLogService;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import tool.util.BeanUtil;
import tool.util.DateUtil;

import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.manage.service.QuartzInfoService;
import com.xindaibao.cashloan.rc.service.BorrowCountService;

@Component
@Lazy(value = false)
public class QuartzBorrowCount implements Job{
	
	
	
	private static final Logger logger = Logger.getLogger(QuartzBorrowCount.class);

	/**
	 * 定时统计借款信息任务
	 * @throws ServiceException
	 */
	public void count() throws ServiceException {
		int msg = 0;
		BorrowCountService borrowCountService = (BorrowCountService)BeanUtil.getBean("borrowCountService");
		QuartzInfoService quartzInfoService = (QuartzInfoService)BeanUtil.getBean("quartzInfoService");
		QuartzLogService quartzLogService = (QuartzLogService)BeanUtil.getBean("quartzLogService");
		
		
		QuartzInfo qi = quartzInfoService.findByCode("doBorrowCount");
		Map<String,Object> qiData = new HashMap<>();
		qiData.put("id", qi.getId());

		QuartzLog ql = new QuartzLog();
		ql.setQuartzId(qi.getId());
		ql.setStartTime(DateUtil.getNow());
		
		
		try {
			logger.info("进入借款统计...");
			msg = borrowCountService.save();
			if (msg<1) {
				ql.setTime(DateUtil.getNow().getTime()-ql.getStartTime().getTime());
				ql.setResult("20");
				ql.setRemark("执行失败");
				qiData.put("succeed", qi.getFail()+1);
				logger.error("定时统计任务修改数据失败");
			}else {
				ql.setTime(DateUtil.getNow().getTime()-ql.getStartTime().getTime());
				ql.setResult("10");
				ql.setRemark("执行成功");
				qiData.put("succeed", qi.getSucceed()+1);
			}
			
			logger.info("统计结束...");
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

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
			try {
				count();
			} catch (ServiceException e) {
				logger.error(e.getMessage(),e);
			}
		
		
	}

}
