package com.xindaibao.cashloan.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.rc.domain.SceneBusiness;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;
import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.rc.mapper.SceneBusinessMapper;
import com.xindaibao.cashloan.rc.model.ManageSceneBusinessModel;
import com.xindaibao.cashloan.rc.service.SceneBusinessService;


/**
 * 场景与第三方征信接口关联关系ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 13:42:36




 */
 
@Service("sceneBusinessService")
public class SceneBusinessServiceImpl extends BaseServiceImpl<SceneBusiness, Long> implements SceneBusinessService {
	
   
    @Resource
    private SceneBusinessMapper sceneBusinessMapper;

	@Override
	public BaseMapper<SceneBusiness, Long> getMapper() {
		return sceneBusinessMapper;
	}

	@Override
	public Page<ManageSceneBusinessModel> page(Map<String, Object> paramMap,
			int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<ManageSceneBusinessModel> page = (Page<ManageSceneBusinessModel>) sceneBusinessMapper.list(paramMap);
		return page;
	}
	
	@Override
	public boolean save(SceneBusiness sceneBusiness) {
		sceneBusiness.setState("10");
		sceneBusiness.setAddTime(DateUtil.getNow());
		
		int result = sceneBusinessMapper.save(sceneBusiness);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(SceneBusiness sceneBusiness) {
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", sceneBusiness.getId());
//		paramMap.put("scene",sceneBusiness.getScene());
//		paramMap.put("businessId",sceneBusiness.getBusinessId());
//		paramMap.put("getWay",sceneBusiness.getGetWay());
//		if(StringUtil.isBlank(sceneBusiness.getPeriod())){
//			paramMap.put("period",0);
//		}else{
//			paramMap.put("period",sceneBusiness.getPeriod());
//		}
		
		int result = sceneBusinessMapper.update(sceneBusiness);
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
		int result = sceneBusinessMapper.updateSelective(paramMap);
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
		int result = sceneBusinessMapper.updateSelective(paramMap);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public boolean validExist(String scene, Long businessId,String type) {
		if(StringUtil.isNotBlank(scene) && StringUtil.isNotBlank(type) && businessId!=null && businessId>0){
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("scene", scene);
			params.put("businessId", businessId);
			params.put("type", type);
			List<SceneBusiness> business = sceneBusinessMapper.listSelective(params);
			if(business.size()>0){
				return true;
			}
		}else{
			return false;
		}
		
		return false;
	}

}