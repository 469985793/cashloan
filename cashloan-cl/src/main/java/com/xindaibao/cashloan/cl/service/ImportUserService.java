package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.AccfundInfo;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.model.KanyaUser;
import com.xindaibao.cashloan.core.model.KanyaUserInfo;
import com.xindaibao.cashloan.core.model.KanyaUserJob;
import com.xindaibao.cashloan.core.model.KanyaUserLive;
import com.xindaibao.cashloan.core.model.KanyaUserContactInfo;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserCredit;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserObtainState;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserState;

import java.util.Date;
import java.util.List;

/**
 * 导入用户表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-16 10:56:12




 */
public interface ImportUserService extends BaseService<KanyaUser, Long>{
	/**
	 * 导入用户
	 */
	int saveUser(List<KanyaUser> list);
	int saveUsersInfo(List<KanyaUserInfo> list);
	int saveUsersJob(List<KanyaUserJob> list);
	int saveUsersLive(List<KanyaUserLive> list);
	int saveUsersContactInfo(List<KanyaUserContactInfo> list);
	int saveUsersState(List<KanyaUserState> list);
	int saveUsersObtainState(List<KanyaUserObtainState> list);
	int saveUsersCredit(List<KanyaUserCredit> list);
	void callAble();
}
