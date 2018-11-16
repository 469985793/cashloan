package com.xindaibao.cashloan.cl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.service.OpinionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.Opinion;
import com.xindaibao.cashloan.cl.mapper.OpinionMapper;
import com.xindaibao.cashloan.cl.model.OpinionModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.DateUtil;

@Service("opinionService")
public class OpinionServiceImpl extends BaseServiceImpl<Opinion, Long> implements OpinionService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(OpinionServiceImpl.class);
   
    @Resource
    private OpinionMapper opinionMapper;

	@Override
	public BaseMapper<Opinion, Long> getMapper() {
		return opinionMapper;
	}

	@Override
	public int save(long userId, String opinion) {
		Opinion o = new Opinion();
		o.setUserId(userId);
		o.setOpinion(opinion);
		o.setCreateTime(DateUtil.getNow());
		o.setState(OpinionModel.STATE_WAITE_CONFIRM);
		return opinionMapper.save(o);
	}
	
	@Override
	public int updateSelective(Map<String, Object> searchMap) {
		return opinionMapper.updateSelective(searchMap);
	}

	@Override
	public List<Opinion> getOpinion(Map<String, Object> paramMap) {
		List<Opinion> list = opinionMapper.listSelective(paramMap);
		return list;
	}

	@Override
	public Page<OpinionModel> page(Map<String, Object> searchMap,
			int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<OpinionModel> list = opinionMapper.listModel(searchMap);
		for (int i = 0; i < list.size(); i++) {
			OpinionModel opinionModel = list.get(i);
			if(OpinionModel.STATE_WAITE_CONFIRM.equals(opinionModel.getState())){
				opinionModel.setStateStr("待确认");
			} else if(OpinionModel.STATE_CONFIRMED.equals(opinionModel.getState())){
				opinionModel.setStateStr("已确认");
			}
			list.set(i, opinionModel);
		}
		return (Page<OpinionModel>)list;
	}

}
