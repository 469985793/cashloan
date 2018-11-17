package com.xindaibao.cashloan.core.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.mapper.*;
import com.xindaibao.cashloan.core.model.*;
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
	private KanyaUserJobMapper  kanyaUserJobMapper;
	@Resource
	private KanyaUserLiveMapper kanyaUserLiveMapper;
	@Resource
	private KanyaUserContactInfoMapper  kanyaUserContactInfoMapper;
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

}