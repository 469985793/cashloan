package com.xindaibao.cashloan.rc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import com.xindaibao.cashloan.rc.model.ManageTppBusinessModel;
import com.xindaibao.cashloan.rc.model.TppBusinessModel;

/**
 * 第三方征信接口信息Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 13:41:57




 */
public interface TppBusinessService extends BaseService<TppBusiness, Long>{
	
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<TppBusinessModel> listAll();

	/**
	 * 第三方征信接口信息分页查询
	 * 
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageTppBusinessModel> page(Map<String, Object> params,
                                      int currentPage, int pageSize);

	/**
	 * 据条件查询集合
	 * 
	 * @param paramMap
	 * @return
	 */
	List<TppBusiness> listSelective(Map<String, Object> paramMap);

	/**
	 * 添加第三方征信接口信息
	 * 
	 * @param tppBusiness
	 * @return
	 */
	boolean save(TppBusiness tppBusiness);

	/**
	 * 修改第三方征信接口信息
	 * 
	 * @param tppBusiness
	 * @return
	 */
	boolean update(TppBusiness tppBusiness);

	/**
	 * 启用第三方征信接口信息
	 * 
	 * @param id
	 * @return
	 */
	boolean enable(Long id);

	/**
	 * 禁用第三方征信接口信息
	 * 
	 * @param id
	 * @return
	 */
	boolean disable(Long id);
	
	boolean tppBusinessExist(TppBusiness business);
	
	/**
	 * 根据Nid查找
	 * @param nid
	 * @return
	 */
	TppBusiness findByNid(String nid,Long tppId);
}
