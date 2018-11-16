package com.xindaibao.cashloan.system.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.system.domain.SysDownloadLog;
import com.xindaibao.cashloan.system.mapper.SysDownloadLogMapper;
import com.xindaibao.cashloan.system.service.SysDownloadLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 后台下载日志统计ServiceImpl
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-11-29 15:39:34



 */
 
@Service("sysDownloadLogService")
public class SysDownloadLogServiceImpl extends BaseServiceImpl<SysDownloadLog, Long> implements SysDownloadLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(SysDownloadLogServiceImpl.class);
   
    @Resource
    private SysDownloadLogMapper sysDownloadLogMapper;

	@Override
	public BaseMapper<SysDownloadLog, Long> getMapper() {
		return sysDownloadLogMapper;
	}

	public Page<SysDownloadLog> page(int current, int pageSize, Map<String, Object> searchMap){
		PageHelper.startPage(current,pageSize);
		Page<SysDownloadLog>  page = (Page<SysDownloadLog>) sysDownloadLogMapper.page(searchMap);
		return page;
	}


}