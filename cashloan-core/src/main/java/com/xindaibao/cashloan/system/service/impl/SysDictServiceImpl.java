package com.xindaibao.cashloan.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.system.domain.SysDict;
import com.xindaibao.cashloan.system.mapper.SysDictDetailMapper;
import com.xindaibao.cashloan.system.mapper.SysDictMapper;
import com.xindaibao.cashloan.system.service.SysDictService;

@Service(value = "sysDictService")
public class SysDictServiceImpl implements SysDictService {
	@Resource
	private SysDictMapper sysDictMapper;
	@Resource
	private SysDictDetailMapper sysDictDetailMapper;
	
	@Override
	public List<SysDict> getDictByTypeArr(String... typeArr) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeArr", typeArr);
		return this.sysDictMapper.getDictByTypeArr(map);
	}
	
	@Override
	public Page<SysDict> getDictPageList(int currentPage,int pageSize,Map<String, Object> searchMap) throws ServiceException {
		PageHelper.startPage(currentPage,pageSize);
		Page<SysDict> pages = (Page<SysDict>) sysDictMapper.listSelective(searchMap);
		return pages;
	}

	@Override
	public Long getDictCount(Map<String, Object> arg) throws ServiceException {
		Long count = null;
		count = sysDictMapper.getCount(arg);
		return count;
	}
	
	@Override
	public long getDictDetailCount(Map<String, Object> data)
			throws ServiceException {
		Long count = null;
		count = sysDictDetailMapper.getCount(data);
		return count;
	}


	@Override
	public boolean addOrModify(SysDict sysDict, String status) throws ServiceException {
		long num = 0;
		boolean isTrue;
		if (status != null && Constant.INSERT.equals(status)) {
			num = sysDictMapper.save(sysDict);
		} else if (status != null && Constant.UPDATE.equals(status)) {
			num = sysDictMapper.update(sysDict);
		}
        isTrue= num > 0L ?true:false;
		return isTrue;
	}

	@Override
	public boolean deleteDict(Long id) throws ServiceException {
		boolean flag =false;
		long num = sysDictMapper.deleteById(id);
		if(num>0 ){
			flag=true;
		}
		return flag;
	}

	@Cacheable(value="dictionaryCache",key="#p0")
	@Override
	public List<Map<String, Object>> getDictsCache(String typeDict)
			throws ServiceException {
		return sysDictMapper.getDictsCache(typeDict);
	}

	@Override
	public List<String> getItemVlueByParentId(String id)
			throws ServiceException {
		return sysDictDetailMapper.getItemVlueByParentId(id);
	}

	@Override
	public Page<Map<String, Object>> getDictDetailList(Map<String, Object> data)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysDict findByTypeCode(String typeCode) {
		return sysDictMapper.findByTypeCode(typeCode);
	}
	
}