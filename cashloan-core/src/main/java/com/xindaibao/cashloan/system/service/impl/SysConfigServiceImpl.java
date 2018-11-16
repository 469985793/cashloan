package com.xindaibao.cashloan.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.system.domain.SysConfig;
import com.xindaibao.cashloan.system.mapper.SysConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.system.service.SysConfigService;

/**
* User:    mcwang
* DateTime:2016-08-04 03:26:22
* details: 系统参数表,Service实现层
* source:  代码生成器
*/
@Service(value = "sysConfigService")
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig,Long> implements SysConfigService {
	/**
	 * 日志操作
	 */
    private static final Logger log = LoggerFactory.getLogger(SysConfigServiceImpl.class);
    /**
	 * 系统参数表dao层
	 */
    @Resource
    private SysConfigMapper sysConfigMapper;
	
	/**
	*
	*继承关系
	*/
	@Override
	public BaseMapper<SysConfig, Long> getMapper() {
		return sysConfigMapper;
	}
	
	/**
	 * 系统参数表表,插入数据
	 * @param collateral 系统参数表类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public long insertSysConfig(SysConfig sysConfig) throws ServiceException{
		try {
			return	sysConfigMapper.save(sysConfig);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	* 系统参数表表,修改数据
	* @param collateral 系统参数表类
	* @return           返回页面map
	* @throws ServiceException
	*/
	public long updateSysConfig(SysConfig sysConfig) throws ServiceException {
		try {
			return	sysConfigMapper.update(sysConfig);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	/**
	 * 系统参数表表,查询数据
	 * @param sysConfig 系统参数表类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public Page<SysConfig> getSysConfigPageList(int currentPage,int pageSize,Map<String, Object> paramMap) throws ServiceException {
		
		try {
			PageHelper.startPage(currentPage, pageSize);
			return(Page<SysConfig>)sysConfigMapper.listSelective(paramMap);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
		//返回已经查询完毕的参数
	}
	
	/**
	 * 系统参数表表,查询数据
	 * @param map类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public int getTotalCount(Map<String, Object> map) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			
			return sysConfigMapper.total(map);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public List<SysConfig> findAll() {
		return sysConfigMapper.findAll();
	}

	@Override
	public String selectByCode(String code) {
		return sysConfigMapper.selectByCode(code).getValue();
	}

	/**
	 * 系统参数表表,根据code模糊查询数据
	 * @return    返回list
	 */
	@Override
	public List<SysConfig> listByCode(String code) {
		return sysConfigMapper.listByCode(code);
	}
	
	@Override
	public List<SysConfig> getList(Map<String, Object> map) {
		return sysConfigMapper.getList(map);
	}
	
	@Override
	public SysConfig findByCode(String code){
		return sysConfigMapper.selectByCode(code);
	}
}