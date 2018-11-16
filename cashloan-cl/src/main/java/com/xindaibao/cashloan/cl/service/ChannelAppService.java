package com.xindaibao.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.ChannelApp;
import com.xindaibao.cashloan.cl.model.ChannelAppModel;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * app渠道版本表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-10 10:29:55




 */
public interface ChannelAppService extends BaseService<ChannelApp, Long>{
	/**
	 * 查询app更新版本信息
	 * @return
	 */
	public List<ChannelAppModel> listChannelAppModel();
	
	/**
	 * 查询app更新信息
	 */
	public List<ChannelApp> listChannelApp();
	
	/**
	 * 主键查询信息
	 */
	public ChannelApp findByPrimary(long id);
	
	/**
	 * 保存信息
	 */
	public int save(ChannelApp channelApp);
	
	/**
	 * 更新信息
	 */
	public int updateSelective(Map<String, Object> paramMap);
}
