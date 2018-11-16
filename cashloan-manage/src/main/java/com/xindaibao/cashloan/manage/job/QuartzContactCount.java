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
import com.xindaibao.cashloan.rc.service.ContactCountService;

@Component
@Lazy(value = false)
public class QuartzContactCount implements Job{
	
	
	
	private static final Logger logger = Logger.getLogger(QuartzContactCount.class);

	/**
	 * 定时统计通讯录信息任务
	 * @throws ServiceException
	 */
	public void count() throws ServiceException {
		int msg = 0;
		ContactCountService contactCountService = (ContactCountService)BeanUtil.getBean("contactCountService");
		QuartzInfoService quartzInfoService = (QuartzInfoService)BeanUtil.getBean("quartzInfoService");
		QuartzLogService quartzLogService = (QuartzLogService)BeanUtil.getBean("quartzLogService");
		
		QuartzLog ql = new QuartzLog();
		QuartzInfo qi = quartzInfoService.findByCode("doContactCount");
		Map<String,Object> qiData = new HashMap<>();
		qiData.put("id", qi.getId());
		
		ql.setQuartzId(qi.getId());
		ql.setStartTime(DateUtil.getNow());
		
		try {
			logger.info("进入借款统计...");
			msg = contactCountService.save();
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
			logger.error("定时统计任务修改数据失败");
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
