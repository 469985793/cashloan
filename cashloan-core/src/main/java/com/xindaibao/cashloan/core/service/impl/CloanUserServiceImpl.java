package com.xindaibao.cashloan.core.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.mapper.KanyaUserMapper;
import com.xindaibao.cashloan.core.mapper.KanyaUserInfoMapper;
import com.xindaibao.cashloan.core.mapper.UserMapper;
import com.xindaibao.cashloan.core.model.CloanUserModel;
import com.xindaibao.cashloan.core.model.KanyaUser;
import com.xindaibao.cashloan.core.model.KanyaUserInfo;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.system.mapper.SysDictDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.DateUtil;
import java.text.SimpleDateFormat;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.code.MD5;

/**
 * 用户认证ServiceImpl
 *
 * @author
 * @version 1.0.0
 * @date 2017-02-21 13:39:06


 *

 */

@Service("cloanUserService")
public class CloanUserServiceImpl extends BaseServiceImpl<KanyaUser, Long> implements CloanUserService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CloanUserServiceImpl.class);

	@Resource
	private UserMapper userMapper;
	@Resource
	private KanyaUserMapper  kanyaUserMapper;
	@Resource
	private KanyaUserInfoMapper  kanyaUserInfoMapper;
	@Resource
	private SysDictDetailMapper sysDictDetailMapper;

	@Override
	public BaseMapper<KanyaUser, Long> getMapper() {
		return kanyaUserMapper;
	}

	@Override
	public Page<KanyaUser> listUser(Map<String, Object> params,
                                         int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<KanyaUser> list = kanyaUserMapper.findByPrimary(params);
		return (Page<KanyaUser>) list;
	}

	@Override
	public CloanUserModel getModelById(Long id) {
		CloanUserModel model = userMapper.getModel(id);
		return model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findAllDic() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = userMapper.queryAllDic();
		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> o : list) {
				Map<String, Object> fmap = new HashMap<String, Object>();
				String typeCode = o.get("typeCode").toString();
				List<Map<String, Object>> zlist = new ArrayList<Map<String, Object>>();
				if (!result.isEmpty()) {
			    	boolean flag=false;
			    	 for (Map<String, Object> r: result) {
			    		 if(r.containsKey(typeCode)){
			    			 flag=true;
			    			 zlist=(List<Map<String, Object>>) r.get(typeCode);
			    			 break;
			    		 }
					  }
			    	 Map<String, Object> zmap=new HashMap<String, Object>();
			    	 zmap.put(o.get("itemCode").toString(), o.get("itemValue").toString());
				     //zmap.put("itemCode", o.get("itemCode").toString());
				     //zmap.put("itemValue",o.get("itemValue").toString());

			    	 if(flag){
			    		 zlist.add(zmap);
			    	 }else{
					     fmap.put(typeCode, zlist);
						 result.add(fmap);
			    	 }
			    }else{
			    	Map<String, Object> zmap=new HashMap<String, Object>();
			    	zmap.put(o.get("itemCode").toString(), o.get("itemValue").toString());
			    	//zmap.put("itemCode", o.get("itemCode").toString());
			    	//zmap.put("itemValue",o.get("itemValue").toString());
			    	zlist.add(zmap);
			    	fmap.put(typeCode, zlist);
					result.add(fmap);
			    }
			}
		}
		return result;
	}

	@Override
	public boolean updateByUuid(Map<String, Object> paramMap) {
		int result = userMapper.updateByUuid(paramMap);
		if (result > 0){
			return true;
		}
		return false;
	}

	@Override
	public User findByPhone(String phone) {
		return userMapper.findByLoginName(phone);
	}

	@Override
	public long todayCount() {
		return userMapper.todayCount();
	}

	@Override
	public void modify(String loginName) {
		Map<String,Object> map = new HashMap<>();
		map.put("loginName", loginName);
		User user = userMapper.findSelective(map);
		if (user!=null) {
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("id", user.getId());
			paramMap.put("loginTime", DateUtil.getNow());
			userMapper.updateSelective(paramMap);
		}
	}
	/**
	 * 导入用户
	 */
	@Override
	public boolean saveUser(String firstName,String lastName,String nationId,String mobile){
		//密码MD5加密
		//String password = MD5.md5Pwd(MD5.String2SHA256StrJava("1234"));
		//直接加密后的“1234”
		String password ="96EF9496AD3AD87AC01293BFBB0FD46F625957C2F89704DF58056FA5AF30366C";
		KanyaUser kanyaUser1 = new KanyaUser();
		kanyaUser1.setUserName(firstName);
		kanyaUser1.setMobile(mobile);
		kanyaUser1.setPassword(password);
		kanyaUser1.setStatus((byte) 1);
		kanyaUser1.setChannelCode("HAKIKA");
		kanyaUser1.setCreatedTime(new Date());
		kanyaUser1.setUpdatedTime(new Date());
		kanyaUserMapper.save(kanyaUser1);
		KanyaUser kanyaUser4=kanyaUserMapper.findByMobile(mobile);
		//创建用户基本信息
		KanyaUserInfo kanyaUserInfo=new KanyaUserInfo();
		kanyaUserInfo.setUid(kanyaUser4.getId());
		kanyaUserInfo.setFirstName(firstName);
		kanyaUserInfo.setLastName(lastName);
		kanyaUserInfo.setNationalId(nationId);
		kanyaUserInfo.setCreatedTime(new Date());
		kanyaUserInfo.setUpdatedTime(new Date());
		kanyaUserInfo.setStatus((byte) 1);
		kanyaUserInfoMapper.save(kanyaUserInfo);
		return true;
	}
}