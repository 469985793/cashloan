package com.xindaibao.cashloan.cl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.domain.UrgeRepayOrder;
import com.xindaibao.cashloan.cl.mapper.*;
import com.xindaibao.cashloan.cl.model.UrgeRepayCountModel;
import com.xindaibao.cashloan.cl.model.UrgeRepayOrderModel;
import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;
import com.xindaibao.cashloan.cl.service.UrgeRepayOrderService;
import com.xindaibao.cashloan.core.mapper.KanyaUserInfoMapper;
import com.xindaibao.cashloan.core.mapper.KanyaUserMapper;
import com.xindaibao.cashloan.core.model.KanyaUser;
import com.xindaibao.cashloan.core.model.KanyaUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.BigDecimalUtil;
import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.model.BorrowModel;
import com.xindaibao.cashloan.system.mapper.SysUserMapper;


/**
 * 催款计划表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 14:21:58


 * 

 */
 
@Service("urgeRepayOrderService")
public class UrgeRepayOrderServiceImpl extends BaseServiceImpl<UrgeRepayOrder, Long> implements UrgeRepayOrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(UrgeRepayOrderServiceImpl.class);
    
	@Resource
    private UrgeRepayOrderMapper urgeRepayOrderMapper;
	@Resource
	private UrgeRepayOrderLogMapper urgeRepayOrderLogMapper;
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Resource
    private BorrowRepayMapper borrowRepayMapper;
    @Resource
    private UserBaseInfoMapper userBaseinfoMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private LoanRecordMapper loanRecordMapper;
    @Resource
    private KanyaUserMapper  kanyaUserMapper;
    @Resource
    private KanyaUserInfoMapper  kanyaUserInfoMapper;
    

	@Override
	public BaseMapper<UrgeRepayOrder, Long> getMapper() {
		return urgeRepayOrderMapper;
	}


	@Override
	public Page<UrgeRepayOrder> list(Map<String, Object> params, int current,
			int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<UrgeRepayOrder> list = urgeRepayOrderMapper.listSelective(params);
			for (int i=0;i<list.size();i++){
				Long OrderNo=Long.parseLong(list.get(i).getOrderNo());
				String indenNo;
				if(loanRecordMapper.findByPrimary(OrderNo)==null){
					indenNo=" ";
				}else {
					indenNo=loanRecordMapper.findByPrimary(OrderNo).getIndentNo();
				}
				list.get(i).setOrderNo(indenNo);
			}
		return (Page<UrgeRepayOrder>)list;
	}
	
 
	public Page<UrgeRepayOrderModel> listModel(Map<String, Object> params,
                                               int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<UrgeRepayOrderModel> list = urgeRepayOrderMapper
				.listModel(params);
		return (Page<UrgeRepayOrderModel>) list;
	}


	@Override
	public int orderAllotUser(Map<String, Object> params) {
		// TODO Auto-generated method stub
		int r=urgeRepayOrderMapper.updateSelective(params);
		return r;
	}


	@Override
	public Map<String, Object> saveOrder(Long id) {
		Map<String, Object> result=new HashMap<String, Object>();
		LoanRecord b=loanRecordMapper.findByPrimary(id);
		if(b!=null){
			//是否逾期标判断
			if(b.getStatus().toString().equals(BorrowModel.STATE_DELAY)){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("borrowId", b.getId());
				List<UrgeRepayOrder> list = urgeRepayOrderMapper.listSelective(params);
				if(list.size()<=0){
					UrgeRepayOrder order=new UrgeRepayOrder();
					order.setBorrowId(b.getId());
					order.setOrderNo(b.getId().toString());
					order.setBorrowTime(b.getCreatedTime());
					order.setTimeLimit(b.getCycle().toString());
				 
				    params = new HashMap<String, Object>();
					params.put("id", b.getUid());
					KanyaUser users = kanyaUserMapper.findSelective(params);
					if(users!=null){
					order.setPhone(users.getMobile());
					}
					params = new HashMap<String, Object>();
					params.put("uid", b.getUid());
					KanyaUserInfo userInfo = kanyaUserInfoMapper.findSelective(params);
					if(userInfo != null) {
						order.setBorrowName(userInfo.getFirstName() + " " + userInfo.getLastName());
					}
				    order.setAmount(b.getBalance().doubleValue());
				    order.setRepayTime(b.getShouldbackTime());
					order.setPenaltyDay(Long.valueOf(Math.abs(DateUtil.daysBetween(new Date(),b.getShouldbackTime()))));
					order.setPenaltyAmout(b.getOverdueFee().doubleValue());
					order.setLevel(UrgeRepayOrderModel.getLevelByDay(Long.valueOf(Math.abs(DateUtil.daysBetween(new Date(),b.getShouldbackTime())))));
					order.setState(UrgeRepayOrderModel.STATE_ORDER_PRE); 
					order.setCreateTime(new Date());
					order.setCount(Long.valueOf(0));
			  
					urgeRepayOrderMapper.save(order);
					
					result.put("code",  Constant.SUCCEED_CODE_VALUE);
					result.put("msg", "提交成功");
					return result;
				}else{
					result.put("code",  Constant.FAIL_CODE_VALUE);
					result.put("msg", "已存在催收订单中，请勿重复添加");	
				}
			}else{
				result.put("code",  Constant.FAIL_CODE_VALUE);
				result.put("msg", "借款信息未逾期");	
			}
		}else{
			result.put("code",  Constant.FAIL_CODE_VALUE);
			result.put("msg", "借款信息不存在");	
		}
		return result;
	}

	@Override
	public List<UrgeRepayOrder> listAll(HashMap<String, Object> hashMap) {
		List<UrgeRepayOrder> list = urgeRepayOrderMapper.listSelective(hashMap);
		return list;
	}

	@Override
	public Page<UrgeRepayCountModel> memberCount(Map<String, Object> params, int current, int pageSize) {
		params = params == null ? new HashMap<String, Object>() : params;
		params.put("roleNid", "collectionSpecialist");
		
		PageHelper.startPage(current, pageSize);
		List<UrgeRepayCountModel> modelList = urgeRepayOrderMapper.listSysUserByRole(params);
		for (UrgeRepayCountModel model : modelList) {
			long userId = model.getUserId();
			
			params.clear();
			params.put("userId", userId);
			model.setCount(urgeRepayOrderMapper.countOrder(params));
			
			params.put("state", UrgeRepayOrderModel.STATE_ORDER_WAIT);
			model.setWaitCount(urgeRepayOrderMapper.countOrder(params));
			
			params.put("state", UrgeRepayOrderModel.STATE_ORDER_SUCCESS);
			model.setWaitCount(urgeRepayOrderMapper.countOrder(params));
			
			Date yester = DateUtil.rollDay(DateUtil.getNow(), -1);
			params.put("startTime", DateUtil.getDayStartTime(yester));
			params.put("endTime", DateUtil.getDayEndTime(yester));
			model.setYesterdayCount(urgeRepayOrderLogMapper.countLog(params));
			
		}
		
		return (Page<UrgeRepayCountModel>)modelList;
	}


	@Override
	public List<UrgeRepayCountModel> orderCount(Map<String, Object> params) {
		List<UrgeRepayCountModel> list = null;
		try {
			list = new ArrayList<>();
			Date startTime = new Date();
			Date endTime = new Date();
			List<Date> dateList = null;
			if (params==null) {
				startTime = DateUtil.getDateBefore(-10, endTime);
				dateList = dateList(startTime, endTime);
			}else {
				endTime = DateUtil.valueOf(params.get("afterTime").toString());
				startTime = DateUtil.valueOf(params.get("beforeTime").toString());
				dateList = dateList(startTime, endTime);
			}
			for (Date date: dateList) {
				Map<String,Object> map = new HashMap<>();
				map.put("date", date);
				UrgeRepayCountModel urcModel = new UrgeRepayCountModel();
				urcModel.setCreateTime(date);
				//累计统计
				String allOrder = urgeRepayOrderMapper.allOrderSum(map);
				urcModel.setAllOrderCount(allOrder!=null?Integer.parseInt(allOrder):0);
				
				String allSuccess = urgeRepayOrderMapper.allSuccessSum(map);
				urcModel.setAllSuccessCount(allSuccess!=null?Integer.parseInt(allSuccess):0);
				
				String allFail = urgeRepayOrderMapper.allFailSum(map);
				urcModel.setAllFailCount(allFail!=null?Integer.parseInt(allFail):0);
				
				if (urcModel.getAllSuccessCount()>urcModel.getAllOrderCount()) {
					urcModel.setAllBackRate(100.00);
				}else if(urcModel.getAllOrderCount()>0){
					double allBackRate = urcModel.getAllSuccessCount()/urcModel.getAllOrderCount();
					if (allBackRate*100>100) {
						urcModel.setAllBackRate(100.00);
					}else {
						urcModel.setAllBackRate(BigDecimalUtil.decimal((allBackRate*100), 2));
					}
				}
				//今日统计
				String newOrder = urgeRepayOrderMapper.newOrderByUser(map);
				urcModel.setOrderCount(newOrder!=null?Integer.parseInt(newOrder):0);
				String repayOrder = urgeRepayOrderMapper.repayOrderByUser(map);
				urcModel.setPromisCount(repayOrder!=null?Integer.parseInt(repayOrder):0);
				String successOrder = urgeRepayOrderMapper.successOrderByUser(map);
				urcModel.setSuccessCount(successOrder!=null?Integer.parseInt(successOrder):0);
				String failOrder = urgeRepayOrderMapper.failOrderByUser(map);
				urcModel.setFailCount(failOrder!=null?Integer.parseInt(failOrder):0);
				String count = urgeRepayOrderMapper.countByUser(map);
				urcModel.setCount(count!=null?Integer.parseInt(count):0);
				urcModel.setBackRate(0.00);
				if (urcModel.getSuccessCount()>urcModel.getOrderCount()) {
					urcModel.setBackRate(100.00);
				}else if(urcModel.getOrderCount()>0){
					double backRate = urcModel.getSuccessCount()/urcModel.getOrderCount();
					if (backRate>100) {
						urcModel.setBackRate(100.00);
					}else {
						urcModel.setBackRate(BigDecimalUtil.decimal((backRate*100), 2));
					}
				}
				
				list.add(urcModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}


	@Override
	public List<UrgeRepayCountModel> urgeCount(Map<String, Object> params) {
		List<UrgeRepayCountModel> list = null;
		try {
			list = new ArrayList<>();
			Date startTime = new Date();
			Date endTime = new Date();
			List<Date> dateList = null;
			if (params==null) {
				startTime = DateUtil.getDateBefore(-10, endTime);
				dateList = dateList(startTime, endTime);
			}else {
				endTime = DateUtil.valueOf(params.get("afterTime").toString());
				startTime = DateUtil.valueOf(params.get("beforeTime").toString());
				dateList = dateList(startTime, endTime);
			}
			for (int i = 0; i < dateList.size(); i++) {
				Map<String,Object> map = new HashMap<>();
				map.put("date", dateList.get(i));
				UrgeRepayCountModel urcModel = new UrgeRepayCountModel();
				urcModel.setCreateTime(dateList.get(i));
				String allOrderCount = urgeRepayOrderMapper.allOrderCount(map);
				urcModel.setOrderCount(Integer.valueOf(allOrderCount==null?"0":allOrderCount));
				String successCount = urgeRepayOrderMapper.successCount(map);
				urcModel.setSuccessCount(Integer.valueOf(successCount==null?"0":successCount));
				String Count = urgeRepayOrderMapper.count(map);
				urcModel.setCount(Integer.valueOf(Count==null?"0":Count));
				if(urcModel.getOrderCount()==0){
					urcModel.setBackRate(0);
				}else{
					urcModel.setBackRate(BigDecimalUtil.decimal(
					Double.valueOf(urcModel.getSuccessCount())/Double.valueOf(urcModel.getOrderCount())*100, 2));
				}
				
				list.add(urcModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}


	@Override
	public List<UrgeRepayCountModel> memberDayCount(Map<String, Object> params) {
		List<UrgeRepayCountModel> list = null;
		try {
			list = new ArrayList<>();
			Date startTime = new Date();
			Date endTime = new Date();
			List<Date> dateList = null;
			if (params==null) {
				startTime = DateUtil.getDateBefore(-10, endTime);
				dateList = dateList(startTime, endTime);
			}else {
				endTime = DateUtil.valueOf(params.get("afterTime").toString());
				startTime = DateUtil.valueOf(params.get("beforeTime").toString());
				dateList = dateList(startTime, endTime);
			}
			for (Date date : dateList) {
				Map<String,Object> map = new HashMap<>();
				List<UrgeRepayOrder> uroList = urgeRepayOrderMapper.listOrder(map);
				for (UrgeRepayOrder urgeRepayOrder : uroList) {
					if (StringUtil.isNull(urgeRepayOrder.getUserName())!=null) {
						UrgeRepayCountModel urcModel = new UrgeRepayCountModel();
						map.put("date", date);
						map.put("userId", urgeRepayOrder.getUserId());
						urcModel.setName(urgeRepayOrder.getUserName());
						String newOrder = urgeRepayOrderMapper.newOrderByUser(map);
						urcModel.setOrderCount(newOrder!=null?Integer.parseInt(newOrder):0);
						String repayOrder = urgeRepayOrderMapper.repayOrderByUser(map);
						urcModel.setPromisCount(repayOrder!=null?Integer.parseInt(repayOrder):0);
						String successOrder = urgeRepayOrderMapper.successOrderByUser(map);
						urcModel.setSuccessCount(successOrder!=null?Integer.parseInt(successOrder):0);
						String failOrder = urgeRepayOrderMapper.failOrderByUser(map);
						urcModel.setFailCount(failOrder!=null?Integer.parseInt(failOrder):0);
						String count = urgeRepayOrderMapper.countByUser(map);
						urcModel.setCount(count!=null?Integer.parseInt(count):0);
						urcModel.setBackRate(0.00);
						if (urcModel.getSuccessCount()>urcModel.getOrderCount()) {
							urcModel.setBackRate(100.00);
						}else if(urcModel.getOrderCount()>0){
							double backRate = urcModel.getSuccessCount()/urcModel.getOrderCount();
							if (backRate>100) {
								urcModel.setBackRate(100.00);
							}else {
								urcModel.setBackRate(BigDecimalUtil.decimal((backRate*100), 2));
							}
						}
						urcModel.setCreateTime(date);
						list.add(urcModel);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}


	@Override
	public UrgeRepayOrder findByBorrowId(long borrowId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrowId", borrowId);
		return urgeRepayOrderMapper.findSelective(map);
	}


	@Override
	public int updateLate(Map<String, Object> uroMap) {
		return urgeRepayOrderMapper.updateSelective(uroMap);
	}


	@Override
	public UrgeRepayOrder findOrderByMap(Map<String, Object> orderMap) {
		// TODO Auto-generated method stub
		return urgeRepayOrderMapper.findSelective(orderMap);
	}


	@Override
	public List<?> listUrgeRepayOrder(Map<String, Object> params) {
		List<UrgeRepayOrder> list = urgeRepayOrderMapper.listSelective(params);
		for (int i=0;i<list.size();i++){
			list.get(i).setAmount(divide(list.get(i).getAmount(),100.00,2));
			list.get(i).setPenaltyAmout(divide(list.get(i).getPenaltyAmout(),100.00,2));
			Long OrderNo=Long.parseLong(list.get(i).getOrderNo());
			String indenNo;
			if(loanRecordMapper.findByPrimary(OrderNo)==null){
				indenNo=" ";
			}else {
				indenNo=loanRecordMapper.findByPrimary(OrderNo).getIndentNo();
			}
			list.get(i).setOrderNo(indenNo);
		}

		for (UrgeRepayOrder uro : list) {
			uro.setState(UrgeRepayOrderModel.change(uro.getState()));
		}
		return list;
	}


	@Override
	public List<?> listUrgeLog(Map<String, Object> params) {
		List<UrgeRepayOrderModel> list = urgeRepayOrderMapper
				.listModel(params);
		for (UrgeRepayOrderModel uroModel : list) {
			uroModel.setState(UrgeRepayOrderModel.change(uroModel.getState()));
			switch (uroModel.getWay()) {
			case "10":
				uroModel.setWay("电话");
				break;
			case "20":
				uroModel.setWay("邮件");
				break;
			case "30":
				uroModel.setWay("短信");
				break;
			case "40":
				uroModel.setWay("现场沟通");
				break;
			case "50":
				uroModel.setWay("其他");
				break;
			}
		}
		return list;
	}
	
	/**
	 * 返回时间集合
	 * @return
	 * @throws Exception
	 */
	private List<Date> dateList(Date startTime,Date endTime) throws Exception{
		startTime = DateUtil.getDateBefore(0, startTime);
		List<Date> lists = DateUtil.dateSplit(startTime, endTime);
		if (!lists.isEmpty()) {
		    return lists;
		}
		return null;
	}
	public static Double divide(Double dividend, Double divisor, Integer scale) {
		         if (scale < 0) {
			             throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		         BigDecimal b1 = new BigDecimal(Double.toString(dividend));
		         BigDecimal b2 = new BigDecimal(Double.toString(divisor));
		         return b1.divide(b2, scale,RoundingMode.HALF_UP).doubleValue();
		     }
}