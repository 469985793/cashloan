package com.xindaibao.cashloan.manage.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
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
	 * 定时征信验证
	 * @throws ServiceException
	 */
	public void count() throws ServiceException {
		ClBorrowService clBorrowService = (ClBorrowService)BeanUtil.getBean("clBorrowService");

		logger.info("定时验证征信接口开始:");
		List<LoanRecord> loanRecord = clBorrowService.selectCreditLoan();

		if(loanRecord != null) {
			for (int i = 0; i < loanRecord.size(); i++) {
				logger.info("调用开始:");

			}
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
