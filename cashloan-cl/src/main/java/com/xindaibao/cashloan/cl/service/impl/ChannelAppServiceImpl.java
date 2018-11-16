package com.xindaibao.cashloan.cl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.service.ChannelAppService;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.cl.domain.ChannelApp;
import com.xindaibao.cashloan.cl.mapper.ChannelAppMapper;
import com.xindaibao.cashloan.cl.model.ChannelAppModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * app渠道版本表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-10 10:29:55




 */
@Service("channelAppService")
public class ChannelAppServiceImpl extends BaseServiceImpl<ChannelApp, Long> implements ChannelAppService {
	
    @Resource
    private ChannelAppMapper channelAppMapper;

	@Override
	public BaseMapper<ChannelApp, Long> getMapper() {
		return null;
	}

	@Override
	public List<ChannelAppModel> listChannelAppModel() {
		List<ChannelAppModel> list = channelAppMapper.listChannelAppModel();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ChannelAppModel app = new ChannelAppModel();
				app.setAppType(list.get(i).getAppType());
				list.get(i).setAppTypeStr(app.getAppTypeStr());
			}
		}
		return list;
	}
    

	@Override
	public List<ChannelApp> listChannelApp() {
		return channelAppMapper.listSelective();
	}

	@Override
	public ChannelApp findByPrimary(long id) {
		return channelAppMapper.findByPrimary(id);
	}

	@Override
	public int save(ChannelApp channelApp) {
		return channelAppMapper.save(channelApp);
	}

	@Override
	public int updateSelective(Map<String, Object> paramMap) {
		return channelAppMapper.updateSelective(paramMap);
	}
	






	
}