package com.xindaibao.cashloan.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.system.domain.SysDictDetail;
import com.xindaibao.cashloan.system.mapper.SysDictDetailMapper;
import com.xindaibao.cashloan.system.service.SysDictDetailService;

/**
 * Service
 * 
 * @author
 * @version 1.0
 * @since 2014-11-03
 */
@Service(value = "sysDictDetailService")
public class SysDictDetailServiceImpl extends BaseServiceImpl<SysDictDetail,Long> implements
		SysDictDetailService {

	@Resource
	private SysDictDetailMapper sysDictDetailMapper;

	@Override
	public Boolean deleteSysDictDetail(Long id) throws ServiceException {
		Boolean isTrue = false;
		int num = sysDictDetailMapper.deleteByPrimary(id);
		 if(num > 0){
			 isTrue = true;
		 }
		return isTrue;
	}

	@Override
	public Long getItemCountMap(Map<String, Object> arg) throws ServiceException {
		long num ;
		num = sysDictDetailMapper.getCount(arg);
		return num;
	}

	@Override
	public void addOrModify(SysDictDetail agr, String stauts)
			throws ServiceException {
		if (stauts != null && Constant.INSERT.equals(stauts)) {
			sysDictDetailMapper.save(agr);
		} else if (stauts != null && Constant.UPDATE.equals(stauts)) {
			sysDictDetailMapper.update(agr);
		}
	}
	
	@Override
	public Page<SysDictDetail> getDictDetailList(int currentPage,int  pageSize,Map<String, Object> data) throws ServiceException {
		PageHelper.startPage(currentPage, pageSize);
		return (Page<SysDictDetail>)sysDictDetailMapper.listSelective(data);
	}
	
	

	@Override
	public List<Map<String, Object>> queryAllDic() throws ServiceException {
		return sysDictDetailMapper.queryAllDic();
	}

	@Override
	public BaseMapper<SysDictDetail,Long> getMapper() {
		return sysDictDetailMapper;
	}

	@Override
	public SysDictDetail findDetail(String code, String parentName) throws ServiceException {
		return sysDictDetailMapper.findDetail(code, parentName);
	}

	@Override
	public List<Map<String, Object>> queryAllDicByParentName(String parentName) {
		return sysDictDetailMapper.queryAllDicByParentName(parentName);
	}
	
	@Override
	public List<SysDictDetail> listByTypeCode(Map<String, Object> data){
		return sysDictDetailMapper.listByTypeCode(data);
	}

	@Override
	public List<SysDictDetail> listUpdateCode(Map<String, Object> data) {
		return sysDictDetailMapper.listUpdateCode(data);
	}
}