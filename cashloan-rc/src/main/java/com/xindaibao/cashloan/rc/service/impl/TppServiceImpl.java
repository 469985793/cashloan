package com.xindaibao.cashloan.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.rc.model.ManageTppModel;
import com.xindaibao.cashloan.rc.model.TppModel;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.rc.domain.Tpp;
import com.xindaibao.cashloan.rc.mapper.TppMapper;
import com.xindaibao.cashloan.rc.service.TppService;


/**
 * 第三方征信信息ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 13:41:05




 */
 
@Service("tppService")
public class TppServiceImpl extends BaseServiceImpl<Tpp, Long> implements TppService {

	@Resource
	private TppMapper tppMapper;

	@Override
	public BaseMapper<Tpp, Long> getMapper() {
		return tppMapper;
	}

	@Override
	public Page<ManageTppModel> page(Map<String, Object> params,
                                     int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		Page<ManageTppModel> page = (Page<ManageTppModel>) tppMapper.list(params);
		return page;
	}

	@Override
	public List<TppModel> listAll() {
		return tppMapper.listAll();
	}

	@Override
	public List<TppModel> listAllWithBusiness() {
		return tppMapper.listAllWithBusiness();
	}

	@Override
	public boolean save(Tpp tpp) {
		tpp.setState("10");
		tpp.setAddTime(DateUtil.getNow());
		
		int result = tppMapper.save(tpp);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Tpp tpp) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", tpp.getId());
		paramMap.put("name", tpp.getName());
		paramMap.put("signType", tpp.getSignType());
		paramMap.put("key", tpp.getKey());
		paramMap.put("extend", tpp.getExtend());
		paramMap.put("merNo", tpp.getMerNo());
		paramMap.put("nid", tpp.getNid());
		int result = tppMapper.updateSelective(paramMap);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean enable(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", "10");
		int result = tppMapper.updateSelective(paramMap);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean disable(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", "20");
		int result = tppMapper.updateSelective(paramMap);
		if (result > 0L) {
			return true;
		}
		return false;
	}
	
}