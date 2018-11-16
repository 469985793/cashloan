package com.xindaibao.creditrank.cr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.domain.CreditLog;
import com.xindaibao.creditrank.cr.service.CreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.creditrank.cr.domain.Credit;
import com.xindaibao.creditrank.cr.mapper.CreditLogMapper;
import com.xindaibao.creditrank.cr.mapper.CreditMapper;
import com.xindaibao.creditrank.cr.model.CreditModel;


/**
 * 授信额度管理ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-15 15:47:24


 * 

 */
 
@Service("creditService")
public class CreditServiceImpl extends BaseServiceImpl<Credit, Long> implements CreditService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);
   
    @Resource
    private CreditMapper creditMapper;
    @Resource
    private CreditLogMapper creditLogMapper;


	@Override
	public BaseMapper<Credit, Long> getMapper() {
		return creditMapper;
	}

	@Override
	public Page<CreditModel> page(Map<String, Object> searchMap,
			int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<CreditModel> list = creditMapper.page(searchMap);
		return (Page<CreditModel>)list;
	}

	@Override
	public Page<CreditModel> listAll(Map<String, Object> searchMap,
			int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<CreditModel> list = creditMapper.listAll(searchMap);
		return (Page<CreditModel>)list;
	}
	
	@Override
	public Credit findSelective(Map<String, Object> searchMap) {
		return creditMapper.findSelective(searchMap);
	}

	@Override
	public Credit findByPrimary(long id) {
		return creditMapper.findByPrimary(id);
	}

	@Override
	public int updateSelective(Map<String, Object> param) {
		return creditMapper.updateSelective(param);
	}

	@Override
	public List<Credit> findByConsumerNo(String consumerNo) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("consumerNo", consumerNo);
		List<Credit> list = creditMapper.listSelective(result);
		return list;
	}

	@Override
	public int updateSelective(long id, double unuse,SysUser sysUser,String remark){
		int msg ;
		Credit credit = creditMapper.findByPrimary(id);
		Map<String,Object> param = new HashMap<String,Object>();
		double total = credit.getTotal(); //变动前额度
		double result = unuse-credit.getUnuse();//变动额度
		double later = credit.getTotal()+result;//变动后额度
		param.put("id", credit.getId());
		param.put("total", later);
		param.put("unuse", unuse);
		msg = creditMapper.updateSelective(param);
		
		if (msg>0) {
			if (unuse>credit.getUnuse()) {
				msg = saveLog(credit, sysUser, total, result, later, remark, "10");
			}else {
				msg = saveLog(credit, sysUser, total, result, later, remark, "20");
			}
			
		}
		return msg;
	}

	@Override
	public int updateState(long id, String state,SysUser sysUser) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("state", state);
		int msg = creditMapper.updateSelective(param);
		Credit credit = creditMapper.findByPrimary(id);
		if (msg>0) {
			if ("10".equals(state)) {
				msg = saveLog(credit, sysUser, 0.0, credit.getUnuse(), credit.getUnuse(), "解冻", "40");
			}else if(("20".equals(state))) {
				msg = saveLog(credit, sysUser, credit.getUnuse(),credit.getUnuse(), 0.0, "冻结", "30");
			}
		}
		return msg;
	}

	public int saveLog(Credit credit,SysUser sysUser,
			double total,double result,double later,String remark,String type){//保存数据
		CreditLog log = new CreditLog();
		log.setModifyTime(new Date());
		log.setConsumerNo(credit.getConsumerNo());
		log.setCreditType(credit.getCreditType());
//		log.setModifyUser(sysUser.getUserName());
		log.setModifyUser("system");
		log.setPre(total);
		log.setNow(later);
		log.setModifyTotal(Math.abs(result));
		log.setRemark(remark);
		log.setType(type);
		int msg = creditLogMapper.save(log);
		return msg;
	}

	@Override
	public int updateByAuth(Map<String, Object> param) {
		int i = creditMapper.updateByAuth(param);
		return i;
	}

	@Override
	public int updateByApproval(long id, double total, String state,
			SysUser sysUser) {
		Credit credit = creditMapper.findByPrimary(id);
		Map<String,Object> param = new HashMap<String,Object>();
		double oriTotal = credit.getTotal(); //变动前额度
		double result = 0.0; 
		double later = 0.0;
		param.put("id", id);
		param.put("state", state);
		if ("10".equals(state)){//审批通过
			param.put("total", total);//被修改的审批额度
			param.put("unuse", total);
			result = oriTotal-total;//变动额度
			later = total;//变动后额度
		} else if("40".equals(state)){//不通过
			
		}
		int msg = creditMapper.updateSelective(param);
		if (msg>0) {
			if(("10".equals(state))) {
				msg = saveLog(credit, sysUser, oriTotal,result, later, "审批通过", "50");
			}else if(("40".equals(state))){
				msg = saveLog(credit, sysUser, oriTotal,result, later, "审批不通过", "60");
			}
		}
		return msg;
	}
}
