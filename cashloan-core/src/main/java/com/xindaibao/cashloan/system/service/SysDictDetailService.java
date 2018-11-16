package com.xindaibao.cashloan.system.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.system.domain.SysDictDetail;

/**
 * Service
 * @author
 * @version 1.0
 * @since 2014-11-03
 */
public interface SysDictDetailService extends BaseService<SysDictDetail,Long> {

    /**
     * 字典详情信息表删除
     * 
     * @param id 主键ID
     */
    Boolean deleteSysDictDetail(Long id) throws ServiceException ;
    
    /**
     * 详情个数查询
     * @param arg
     * @return
     * @throws ServiceException
     */
    Long getItemCountMap(Map<String, Object> arg) throws ServiceException;
    
    /**
     * 新增或者修改字典详情信息
     * @param dictDetail
     * @param stauts
     * @throws ServiceException
     */
    void addOrModify(SysDictDetail dictDetail,String stauts) throws ServiceException;
    
    /**
     * 查询所有信息
     * @return
     * @throws ServiceException
     */
    List<Map<String,Object>> queryAllDic() throws ServiceException;
    
    /**
     * 字典详情分页查询
     * @param currentPage
     * @param pageSize
     * @param data
     * @return
     * @throws ServiceException
     */
    Page<SysDictDetail> getDictDetailList(int currentPage,int pageSize,Map<String, Object> data) throws ServiceException;
    
    /**
     * 查询字典详情信息
     * @param code
     * @param parentId
     * @return
     * @throws ServiceException
     */
    SysDictDetail findDetail(String code,String prentName) throws ServiceException;
    
    /**
     * 根据类型查询字典信息，无分页列表，请勿添加其它参数，否则影响页面显示的联动数据
     * @param parentName
     * @return
     */
    List<Map<String, Object>> queryAllDicByParentName(String parentName);
    /**
     * 新增时查询名称列表
     * @param data
     * @return
     */
	List<SysDictDetail> listByTypeCode(Map<String, Object> data);
	/**
	 * 修改时查询名称列表
	 * @param data
	 * @return
	 */
	List<SysDictDetail> listUpdateCode(Map<String, Object> data);
}
