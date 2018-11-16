package com.xindaibao.cashloan.system.mapper;


import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.system.domain.SysDownloadLog;

import java.util.List;
import java.util.Map;

/**
 * 后台下载日志统计Dao
 *
 */
@RDBatisDao
public interface SysDownloadLogMapper extends BaseMapper<SysDownloadLog, Long> {

    /**
     * 分页查询
     *
     * @param searchMap
     * @return
     */
    List<SysDownloadLog> page(Map<String, Object> searchMap);

}
