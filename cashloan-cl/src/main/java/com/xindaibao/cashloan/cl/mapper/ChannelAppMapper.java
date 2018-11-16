package com.xindaibao.cashloan.cl.mapper;

import java.util.List;

import com.xindaibao.cashloan.cl.domain.ChannelApp;
import com.xindaibao.cashloan.cl.model.ChannelAppModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;


/**
 * app渠道版本表Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-10 10:29:55




 */
@RDBatisDao
public interface ChannelAppMapper extends BaseMapper<ChannelApp,Long> {

	/**
	 * 查询app渠道更新版本信息
	 * @return
	 */
	public List<ChannelAppModel> listChannelAppModel();

	/**
	 * 查询app更新信息
	 */
	public List<ChannelApp> listSelective();
	
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
	public int updateSelective(ChannelApp channelApp);
}
