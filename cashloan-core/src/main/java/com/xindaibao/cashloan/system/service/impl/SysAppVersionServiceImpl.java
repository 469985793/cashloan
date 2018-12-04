package com.xindaibao.cashloan.system.service.impl;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.system.domain.SysAppVersion;
import com.xindaibao.cashloan.system.mapper.SysAppVersionMapper;
import com.xindaibao.cashloan.system.service.SysAppVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 访问码ServiceImpl
 * 
 * @author
 * @version 1.0.0
 */

@Service("sysAppVersionService")
public class SysAppVersionServiceImpl extends BaseServiceImpl<SysAppVersion, Long> implements SysAppVersionService {

	@Resource
	private SysAppVersionMapper sysAppVersionMapper;

	@Override
	public BaseMapper<SysAppVersion, Long> getMapper() {
		return sysAppVersionMapper;
	}
	@Override
	public List<SysAppVersion> listSysAppVersion() {
		List<SysAppVersion> list  = sysAppVersionMapper.listSysAppVersion();
		return list;
	}

	@Override
	public int save(SysAppVersion appVersion) {
		return sysAppVersionMapper.save(appVersion);
	}

	@Override
	public int delete(SysAppVersion appVersion) {
		return sysAppVersionMapper.delete(appVersion);
	}


}