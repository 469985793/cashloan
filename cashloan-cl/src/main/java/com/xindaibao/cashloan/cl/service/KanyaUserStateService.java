package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.model.kenya.KanyaUserState;
import com.xindaibao.cashloan.core.common.service.BaseService;

import java.util.Date;

public interface KanyaUserStateService extends BaseService<KanyaUserState, Long>{

	void updateCurrentState(KanyaUserState kanyaUserState);

}
