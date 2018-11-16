package com.xindaibao.cashloan.system.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.system.domain.SysAccessCode;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.mapper.SysAccessCodeMapper;
import com.xindaibao.cashloan.system.model.SysAccessCodeModel;
import com.xindaibao.cashloan.system.service.SysAccessCodeService;

/**
 * 访问码ServiceImpl
 * 
 * @author
 * @version 1.0.0


 */

@Service("sysAccessCodeService")
public class SysAccessCodeServiceImpl extends BaseServiceImpl<SysAccessCode, Long> implements SysAccessCodeService {

	@Resource
	private SysAccessCodeMapper sysAccessCodeMapper;

	@Override
	public BaseMapper<SysAccessCode, Long> getMapper() {
		return sysAccessCodeMapper;
	}

	@Override
	public Page<SysAccessCodeModel> listAccessCodeModel(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<SysAccessCodeModel> list = sysAccessCodeMapper.listAccessCodeModel(params);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SysAccessCodeModel code = new SysAccessCodeModel();
				code.setState(list.get(i).getState());
				list.get(i).setStateStr(code.getStateStr());
			}
		}
		return (Page<SysAccessCodeModel>) list;
	}

	@Override
	public int save(SysAccessCode accessCode, String time) {
		Date exceedTime = null;
		Calendar ca = Calendar.getInstance();
		Date now = ca.getTime(); 
		switch(time){
		case SysAccessCode.TWO_HOUR:
			ca.add(Calendar.HOUR_OF_DAY, 2);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.HALF_DAY:
			ca.add(Calendar.HOUR_OF_DAY, 12);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.ONE_DAY:
			ca.add(Calendar.DATE, 1);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.TWO_DAY:
			ca.add(Calendar.DATE, 2);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.SEVEN_DAY:
			ca.add(Calendar.DATE, 7);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.ONE_MOUNTH:
			ca.add(Calendar.DATE, 30);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.THREE_MOUNTH:
			ca.add(Calendar.DATE, 90);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.SIX_MOUNTH:
			ca.add(Calendar.DATE, 180);
			exceedTime = ca.getTime();
			break;
		}
		accessCode.setExceedTime(exceedTime);
		accessCode.setCreateTime(now);
		accessCode.setState("10");
		return sysAccessCodeMapper.save(accessCode);
	}

	@Override
	public int update(SysAccessCode ac,String time) {
		Date exceedTime = null;
		Calendar ca = Calendar.getInstance();
		ca.setTime(ac.getCreateTime());
		switch(time){
		case SysAccessCode.TWO_HOUR:
			ca.add(Calendar.HOUR_OF_DAY, 2);
			exceedTime = ca.getTime();
		case SysAccessCode.HALF_DAY:
			ca.add(Calendar.HOUR_OF_DAY, 12);
		case SysAccessCode.ONE_DAY:
			ca.add(Calendar.DATE, 1);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.TWO_DAY:
			ca.add(Calendar.DATE, 2);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.SEVEN_DAY:
			ca.add(Calendar.DATE, 7);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.ONE_MOUNTH:
			ca.add(Calendar.DATE, 30);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.THREE_MOUNTH:
			ca.add(Calendar.DATE, 90);
			exceedTime = ca.getTime();
			break;
		case SysAccessCode.SIX_MOUNTH:
			ca.add(Calendar.DATE, 180);
			exceedTime = ca.getTime();
			break;
		}
		ac.setExceedTime(exceedTime);
		return sysAccessCodeMapper.update(ac);
	}

	@Override
	public int countCode(long sysUserId, String code) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sysUserId", sysUserId);
		data.put("code", code);
		return sysAccessCodeMapper.countCode(data);
	}

	@Override
	public List<SysAccessCode> listSysAccessCode(Long sysUserId) {
		return sysAccessCodeMapper.listSysAccessCode(sysUserId);
	}

	@Override
	public SysAccessCode findSysAccessCode(Map<String,Object> map) {
		return sysAccessCodeMapper.findSysAccessCode(map);
	}

	@Override
	public int updateState(SysAccessCode ac) {
		return sysAccessCodeMapper.update(ac);
	}

	@Override
	public List<SysUser> listUserName() {
		return sysAccessCodeMapper.listUserName();
	}
}