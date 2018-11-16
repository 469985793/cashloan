package com.xindaibao.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.Adver;
import com.xindaibao.cashloan.cl.domain.BaiRongGrade;
import com.xindaibao.cashloan.cl.domain.BaiRongLoginRecord;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;

import java.util.List;
import java.util.Map;

/**
 * 广告管理Service
 * 
 * wmc
 * @version 1.0.0
 * @date 2017-06-21 14:33:20
 * Copyright zuoli company  cashloan All Rights Reserved
 *
 *
 */
public interface BaiRongGradeService extends BaseService<BaiRongGrade, Long>{

	String findToken();

	int requestGrade(Long userId);

	void updateToken(String token);
}
