package com.xindaibao.cashloan.system.service;


import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.system.domain.SysDownloadLog;

import java.util.Map;

/**
 * 后台下载日志统计Service
 */
public interface SysDownloadLogService extends BaseService<SysDownloadLog, Long> {

    Page<SysDownloadLog> page(int current, int pageSize,
                              Map<String, Object> searchMap);

}
