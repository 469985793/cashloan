package com.xindaibao.cashloan.cl.service;

import java.util.Date;

import com.xindaibao.cashloan.cl.domain.AccfundInfo;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 公积金基本信息表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-16 10:56:12




 */
public interface AccfundInfoService extends BaseService<AccfundInfo, Long>{

	void saveAccfundInfos(String res, Long userId, Date createTime);

}
