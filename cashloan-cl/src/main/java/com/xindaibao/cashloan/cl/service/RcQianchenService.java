package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.rc.domain.TppBusiness;

/**
 * 现金白卡风控接口
 * @author
 * @version 1.0
 * @date 2017年3月14日下午2:03:00


 * 

 */
public interface RcQianchenService {

	/**
	 * 调用浅橙风控接口
	 * @param userId 用户ID
	 * @param picPath 图片服务地址
	 * @throws BussinessException
	 */
	String qianchenRiskRequest(Long userId,Borrow borrow,String operatorNo,String reqOrderNo,TppBusiness tpp) throws BussinessException;
}
