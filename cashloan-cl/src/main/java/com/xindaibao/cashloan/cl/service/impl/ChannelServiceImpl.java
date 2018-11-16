package com.xindaibao.cashloan.cl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.mapper.ChannelMapper;
import com.xindaibao.cashloan.cl.model.ChannelCountModel;
import com.xindaibao.cashloan.cl.model.ChannelModel;
import com.xindaibao.cashloan.cl.service.ChannelService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.Channel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;

@Service("channelService")
public class ChannelServiceImpl extends BaseServiceImpl<Channel, Long> implements ChannelService {
	
    @Resource
    private ChannelMapper channelMapper;
    
    @Override
	public BaseMapper<Channel, Long> getMapper() {
		return channelMapper;
	}

	@Override
	public boolean save(Channel channel) {
		channel.setCreateTime(new Date());
		channel.setState(ChannelModel.STATE_ENABLE);
		int result = channelMapper.save(channel);
		if(result >0 ){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean update(Map<String, Object> paramMap) {
		int result = channelMapper.updateSelective(paramMap);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public long findIdByCode(String code) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", code);
		return channelMapper.findIdSelective(paramMap);
	}
	
	@Override
	public Channel findByCode(String code) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", code);
		return channelMapper.findSelective(paramMap);
	}

	@Override
	public Page<ChannelModel> page(int current, int pageSize,
			Map<String, Object> searchMap) {
		PageHelper.startPage(current, pageSize);
		Page<ChannelModel> page = (Page<ChannelModel>) channelMapper
				.page(searchMap);
		return page;
	}

	@Override
	public Page<ChannelCountModel> channelUserList(int current, int pageSize,
                                                   Map<String, Object> searchMap) {
		PageHelper.startPage(current, pageSize);
		Page<ChannelCountModel> page = (Page<ChannelCountModel>) channelMapper.channelUserList(searchMap);
		return page;
	}

	@Override
	public List<Channel> listChannel() {
		return channelMapper.listChannel();
	}

	@Override
	public List<Channel> listChannelWithoutApp() {
		return channelMapper.listChannelWithoutApp();
	}

}