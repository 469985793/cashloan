package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.service.ImportUserService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.mapper.*;
import com.xindaibao.cashloan.core.model.*;
import com.xindaibao.cashloan.core.service.impl.CloanUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.xindaibao.cashloan.cl.mapper.KanyaUserCreditMapper;
import com.xindaibao.cashloan.cl.mapper.KanyaUserIntegralMapper;
import com.xindaibao.cashloan.cl.mapper.KanyaUserObtainStateMapper;
import com.xindaibao.cashloan.cl.mapper.KanyaUserStateMapper;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserCredit;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserIntegral;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserObtainState;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserState;
import javax.annotation.Resource;
import java.util.*;


/**
 * 导入用户表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-16 10:56:12




 */
 
@Service("importUserInfoService")
public class ImportUserServiceImpl extends BaseServiceImpl<KanyaUser, Long> implements ImportUserService{
	private static final Logger logger = LoggerFactory.getLogger(ImportUserServiceImpl.class);
	@Resource
	private KanyaUserMapper kanyaUserMapper;
	@Resource
	private KanyaUserInfoMapper kanyaUserInfoMapper;
	@Resource
	private KanyaUserJobMapper kanyaUserJobMapper;
	@Resource
	private KanyaUserLiveMapper kanyaUserLiveMapper;
	@Resource
	private KanyaUserContactInfoMapper kanyaUserContactInfoMapper;
	@Resource
	private KanyaUserStateMapper kanyaUserStateMapper;
	@Resource
	private KanyaUserObtainStateMapper kanyaUserObtainStateMapper;
	@Resource
	private KanyaUserCreditMapper kanyaUserCreditMapper;
	@Resource
	private KanyaUserIntegralMapper kanyaUserIntegralMapper;
	@Override
	public BaseMapper<KanyaUser, Long> getMapper() { return kanyaUserMapper; }

	/**
	 * 导入用户
	 */
	@Override
	public void callAble() {
		kanyaUserMapper.callAble();
	}
	/**
	 * 导入用户
	 */
	@Override
	public int saveUser(List<KanyaUser> list){
		int result=kanyaUserMapper.saveUsers(list);
		return result;
	}
	@Override
	public int saveUsersInfo(List<KanyaUserInfo> list){
		int result=kanyaUserInfoMapper.saveUsersInfo(list);
		return result;
	}
	@Override
	public int saveUsersJob(List<KanyaUserJob> list){
		int result=kanyaUserJobMapper.saveUsersJob(list);
		return result;
	}
	@Override
	public int saveUsersLive(List<KanyaUserLive> list){
		int result=kanyaUserLiveMapper.saveUsersLive(list);
		return result;
	}
	@Override
	public int saveUsersContactInfo(List<KanyaUserContactInfo> list){
		int result=kanyaUserContactInfoMapper.saveUsersContactInfo(list);
		return result;
	}
	@Override
	public int saveUsersState(List<KanyaUserState> list){
		int result=kanyaUserStateMapper.saveUsersState(list);
		return result;
	}
	@Override
	public int saveUsersObtainState(List<KanyaUserObtainState> list){
		int result=kanyaUserObtainStateMapper.saveUsersObtainState(list);
		return result;
	}
	@Override
	public int saveUsersCredit(List<KanyaUserCredit> list){
		int result=kanyaUserCreditMapper.saveUsersCredit(list);
		return result;
	}
	@Override
	public List completeUserInfo(){
		List result=kanyaUserMapper.completeUserInfo();
		return result;
	}
	
}