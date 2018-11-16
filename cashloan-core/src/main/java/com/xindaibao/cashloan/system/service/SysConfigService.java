package com.xindaibao.cashloan.system.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.system.domain.SysConfig;

/**
* User:    mcwang
* DateTime:2016-08-04 03:26:22
* details: 系统参数表,Service接口层
* source:  代码生成器
*/
public interface SysConfigService {

    /**
     * 系统参数表表,插入数据
     * @param sysConfig 系统参数表类
     * @return           返回页面map
     * @throws ServiceException 
     * @throws Exception
     */
    long insertSysConfig(SysConfig sysConfig) throws ServiceException;

    /**
     * 系统参数表表,修改数据
     * @param sysConfig 系统参数表类
     * @return           返回页面map
     * @throws ServiceException 
     * @throws Exception
     */
  	long updateSysConfig(SysConfig sysConfig) throws ServiceException;


    /**
     * 系统参数表表,查询数据
     * @param sysConfig 系统参数表类
     * @return           返回页面map
     * @throws ServiceException 
     * @throws Exception
     */
  	Page<SysConfig> getSysConfigPageList (int currentPage,int pageSize,Map<String, Object> map) throws ServiceException;
    
    
    /**
     * @throws ServiceException 
    * @Description: 获取总记录数 
    * @param @param map
    * @param @return
    * @param @throws Exception    设定文件 
    * @author
    * @return int    返回类型 
    * @throws
     */
   	 int getTotalCount(Map<String,Object> map) throws ServiceException;
   	 
   	 /**
   	  * 查询所有配置
   	  * @param map
   	  * @return
   	  * @throws Exception
   	  */
   	 List<SysConfig> findAll();

   	/**
   	  * 根据code查询
   	  * @param code
   	  * @return SysConfig
   	  * @throws Exception
   	  */
	String  selectByCode(String code);

	/**
  	  * 根据code查询
  	  * @param code
  	  * @return SysConfig列表
  	  * @throws Exception
  	  */
	List<SysConfig> listByCode(String code);

	List<SysConfig> getList(Map<String, Object> map);

	SysConfig findByCode(String code);
}
