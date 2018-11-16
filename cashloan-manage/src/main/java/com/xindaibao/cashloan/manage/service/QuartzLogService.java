package com.xindaibao.cashloan.manage.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.manage.domain.QuartzLog;
import com.xindaibao.cashloan.manage.model.QuartzLogModel;

/**
 * 定时任务记录Service
 */
public interface QuartzLogService extends BaseService<QuartzLog, Long>{

	/**
	 * 保存日志
	 */
	int save(QuartzLog ql);

	Page<QuartzLogModel> page(Map<String, Object> searchMap, int current,
                              int pageSize);

}
