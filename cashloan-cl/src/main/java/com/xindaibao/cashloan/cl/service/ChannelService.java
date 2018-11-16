package com.xindaibao.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.Channel;
import com.xindaibao.cashloan.cl.model.ChannelCountModel;
import com.xindaibao.cashloan.cl.model.ChannelModel;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 渠道信息Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-03 10:52:07


 * 

 */
public interface ChannelService extends BaseService<Channel, Long>{

	/**
	 * 保存渠道信息
	 * 
	 * @param channel
	 * @return
	 */
	boolean save(Channel channel);
	
	/**
	 * 更新渠道信息
	 * 
	 * @param channel
	 * @return
	 */
	boolean update(Map<String, Object> paramMap);
	
	/**
	 * 根据code查询主键
	 * @param code
	 * @return
	 */
	long findIdByCode(String code);
	
	/**
	 * 根据code查询对象
	 * @param code
	 * @return
	 */
	Channel findByCode(String code);

	/**
	 * 列表查询渠道信息
	 * 
	 * @param current
	 * @param pageSize
	 * @param searchMap
	 * @return
	 */
	Page<ChannelModel> page(int current, int pageSize,
			Map<String, Object> searchMap);
	/**
	 * 渠道用户统计
	 * 
	 * @param current
	 * @param pageSize
	 * @param searchMap
	 * @return
	 */
	Page<ChannelCountModel> channelUserList(int current, int pageSize,
			Map<String, Object> searchMap);

	/**
	 * 查出所有渠道信息
	 */
	List<Channel> listChannel();
	
	/**
	 * 查询没有版本信息的渠道id和名称
	 */
	List<Channel> listChannelWithoutApp();
}
