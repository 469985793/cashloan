package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.Channel;
import com.xindaibao.cashloan.cl.model.ChannelCountModel;
import com.xindaibao.cashloan.cl.model.ChannelModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 渠道信息Dao
 */
@RDBatisDao
public interface ChannelMapper extends BaseMapper<Channel,Long> {
	
	/**
	 * 根据条件查询主键
	 */
	long findIdSelective(Map<String, Object> paramMap);
	
	/**
	 * 根据条件查询对象
	 */
	Channel findSelective(Map<String, Object> paramMap);

	/**
	 * 列表查询
	 * @param paramMap
	 * @return
	 */
	List<ChannelModel> page(Map<String, Object> paramMap);
	/**
	 * 渠道用户信息统计
	 * @param paramMap
	 * @return
	 */
	Page<ChannelCountModel> channelUserList(Map<String, Object> searchMap);

	
	/**
	 * 查出所有渠道信息
	 */
	List<Channel> listChannel();
	
	/**
	 * 查询没有版本信息的渠道id和名称
	 */
	List<Channel> listChannelWithoutApp();
}
