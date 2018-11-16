package com.xindaibao.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.BorrowProgress;
import com.xindaibao.cashloan.cl.model.ManageBorrowProgressModel;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.Borrow;

/**
 * 借款进度表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 10:31:04


 * 

 */
public interface BorrowProgressService extends BaseService<BorrowProgress, Long>{

	/**
	 * 进度查询
	 * @param borrowId
	 * @return
	 */
	Map<String,Object> result(Borrow borrow);

	/**
	 * 后台还款进度列表
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowProgressModel> listModel(Map<String, Object> params,
                                              int currentPage, int pageSize);

	/**
	 * 保存借款进度
	 * @param borrowProgress
	 * @return
	 */
	boolean save(BorrowProgress borrowProgress);

	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	List<BorrowProgress> listSeletetiv(Map<String, Object> map);
}
