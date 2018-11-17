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
import java.util.Date;
import java.util.Random;
import java.util.UUID;


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
	public boolean saveUser(String firstName,String lastName,String nationId,String mobile){
		//密码MD5加密
		//String password = MD5.md5Pwd(MD5.String2SHA256StrJava("1234"));
		//直接加密后的“1234”
		//判断手机号是否已存在
		KanyaUser user = kanyaUserMapper.findByMobile(mobile);
		if (user != null && user.getId() > 0) {
			return false;
		}
		String password ="96EF9496AD3AD87AC01293BFBB0FD46F625957C2F89704DF58056FA5AF30366C";
		KanyaUser kanyaUser1 = new KanyaUser();
		kanyaUser1.setUserName(firstName);
		kanyaUser1.setMobile(mobile);
		kanyaUser1.setPassword(password);
		kanyaUser1.setStatus((byte) 1);
		kanyaUser1.setChannelCode("HAKIKA");
		kanyaUser1.setCreatedTime(new Date());
		kanyaUser1.setUpdatedTime(new Date());
		//生成自己的邀请码
		String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		kanyaUser1.setInviteCode(sb.toString());
		kanyaUser1.setIconImgcode(UUID.randomUUID().toString());
		kanyaUserMapper.save(kanyaUser1);
		KanyaUser kanyaUser4=kanyaUserMapper.findByMobile(mobile);
		Long uid=kanyaUser4.getId();
		KanyaUserInfo kanyaUserInfo1=kanyaUserInfoMapper.selectByNationId(nationId);
		if (kanyaUserInfo1 != null && kanyaUserInfo1.getId() > 0) {
			return false;
		}
		//创建用户基本信息
		KanyaUserInfo kanyaUserInfo=new KanyaUserInfo();
		kanyaUserInfo.setUid(uid);
		kanyaUserInfo.setFirstName(firstName);
		kanyaUserInfo.setLastName(lastName);
		kanyaUserInfo.setNationalId(nationId);
		kanyaUserInfo.setCreatedTime(new Date());
		kanyaUserInfo.setStatus((byte) 1);
		kanyaUserInfoMapper.save(kanyaUserInfo);
		//创建用户工作信息表
		KanyaUserJob userJob=new KanyaUserJob();
		userJob.setUid(uid);
		userJob.setCreatedTime(new Date());
		userJob.setStatus((byte) 1);
		kanyaUserJobMapper.save(userJob);
		//创建用户居住信息表
		KanyaUserLive userLive=new KanyaUserLive();
		userLive.setUid(uid);
		userLive.setCreatedTime(new Date());
		userLive.setStatus((byte) 1);
		kanyaUserLiveMapper.save(userLive);
		//创建用户联系人表
		KanyaUserContactInfo userContactInfo=new KanyaUserContactInfo();
		userContactInfo.setUid(uid);
		userContactInfo.setCreatedTime(new Date());
		userContactInfo.setStatus((byte) 1);
		kanyaUserContactInfoMapper.save(userContactInfo);
		//创建用户状态信息表
		KanyaUserState userState=new KanyaUserState();
		userState.setUid(uid);
		userState.setStatus((byte) 1);
		userState.setCreatedTime(new Date());
		userState.setCurrentState((byte) 1);
		kanyaUserStateMapper.save(userState);
		//创建用户授权状态信息表
		KanyaUserObtainState userObtainState=new KanyaUserObtainState();
		userObtainState.setUid(uid);
		userObtainState.setStatus((byte) 1);
		userObtainState.setCreatedTime(new Date());
		kanyaUserObtainStateMapper.save(userObtainState);
		//创建用户额度表
		KanyaUserCredit userCredit=new KanyaUserCredit();
		userCredit.setUid(uid);
		userCredit.setCreatedTime(new Date());
		userCredit.setStatus((byte) 1);
		kanyaUserCreditMapper.save(userCredit);
		//创建用户积分表
//		KanyaUserIntegral userIntegral=new KanyaUserIntegral();
//		userIntegral.setUid(kanyaUser4.getId());
//		userIntegral.setCreatedTime(new Date());
//		userIntegral.setStatus((byte) 1);
//		kanyaUserIntegralMapper.save(userIntegral);
		return true;
	}
	
}