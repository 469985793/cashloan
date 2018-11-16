package com.xindaibao.cashloan.cl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.BorrowProgress;
import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.mapper.BankCardMapper;
import com.xindaibao.cashloan.cl.mapper.BorrowProgressMapper;
import com.xindaibao.cashloan.cl.mapper.BorrowRepayMapper;
import com.xindaibao.cashloan.cl.mapper.ClBorrowMapper;
import com.xindaibao.cashloan.cl.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import tool.util.BigDecimalUtil;
import tool.util.DateUtil;
import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.BorrowRepayLog;
import com.xindaibao.cashloan.cl.mapper.BorrowRepayLogMapper;
import com.xindaibao.cashloan.cl.service.BorrowProgressService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.model.BorrowModel;


/**
 * 借款进度表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 10:33:38


 * 

 */
 
@Service("borrowProgressService")
public class BorrowProgressServiceImpl extends BaseServiceImpl<BorrowProgress, Long> implements BorrowProgressService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BorrowProgressServiceImpl.class);
   
    @Resource
    private BorrowProgressMapper borrowProgressMapper;
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Resource
    private BorrowRepayMapper borrowRepayMapper;
    @Resource
    private BorrowRepayLogMapper borrowRepayLogMapper;
    @Resource
    private BankCardMapper bankCardMapper;
    @Resource
	private UserBaseInfoMapper userBaseInfoMapper;
    
	@Override
	public BaseMapper<BorrowProgress, Long> getMapper() {
		return borrowProgressMapper;
	}

	
	@Override
	public Map<String, Object> result(Borrow  borrow) {
		long originalId = 0;
		String originalOrderNo = "";
		if (borrow.getRenewMark() != null && borrow.getRenewMark() > 0) {
			originalId = borrow.getOriginalId();
			originalOrderNo = borrow.getOriginalOrderNo();
		} else {
			originalId = borrow.getId();
			originalOrderNo = borrow.getOrderNo();
		}

		// 查询续借次数
		Map<String, Object> params = new HashMap<>();
		params.put("originalId", originalId);
		params.put("originalOrderNo", originalOrderNo);
		List<Borrow> renewList = clBorrowMapper.listSelective(params);

		int renewCount = 0;
		if (renewList != null && !renewList.isEmpty()) {
			renewCount = renewList.size();
		}

		Borrow lastRenewBorrow = null;
		// 若存在续借 则详情显示续借订单
		if (renewCount > 0) {
			Map<String, Object> renewMap = new HashMap<>();
			renewMap.put("userId", borrow.getUserId());
			renewMap.put("originalId", originalId);
			renewMap.put("originalOrderNo", originalOrderNo);
			lastRenewBorrow = clBorrowMapper.findLastBorrow(renewMap);
		}

		ClBorrowModel clBorrowModel = new ClBorrowModel();

		if (lastRenewBorrow != null) {
			BeanUtils.copyProperties(lastRenewBorrow, clBorrowModel);
		} else {
			BeanUtils.copyProperties(borrow, clBorrowModel);
		}
		
		
		clBorrowModel.setPenalty("20");

		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("borrowId",clBorrowModel.getId() );
		BorrowRepayLog log = borrowRepayLogMapper.findLast(searchMap);


		List<BorrowRepay> repayList = new ArrayList<BorrowRepay>();
		BorrowRepay repay = borrowRepayMapper.findByBorrowId(clBorrowModel.getId());
		if (null != repay) {
			clBorrowModel.setPenaltyAmount(repay.getPenaltyAmout());
			if (repay.getPenaltyAmout() > 0) {
				Double overdueAmount = BigDecimalUtil.add(repay.getAmount(),repay.getPenaltyAmout());
				clBorrowModel.setPenalty("10");
				clBorrowModel.setOverdueAmount(overdueAmount.toString());
			}else{
				clBorrowModel.setOverdueAmount(repay.getAmount().toString());
			}
			clBorrowModel.setRepayTime(repay.getRepayTime());
		} else {
			clBorrowModel.setOverdueAmount(clBorrowModel.getAmount().toString());
		}
		if (null != log) {
			clBorrowModel.setRealTime(log.getRepayTime());
			BorrowRepayModel borrowRepayModel= new BorrowRepayModel();
			BeanUtils.copyProperties(repay,borrowRepayModel);
			borrowRepayModel.setRealRepayAmount(log.getAmount());//实际还款金额
			borrowRepayModel.setRealRepayTime(log.getRepayTime());//实际还款时间
			repayList.add(borrowRepayModel);
		}else{
			repayList.add(repay);
		}

		


		searchMap.clear();
		searchMap.put("userId", borrow.getUserId());
		BankCard bankCard = bankCardMapper.findSelective(searchMap);
		if (bankCard != null) {
			String cardNo = bankCard.getCardNo();
			if (StringUtil.isNotBlank(bankCard.getCardNo()) && bankCard.getCardNo().length() > 8) {
				int cardNoLength = bankCard.getCardNo().length();
				cardNo = cardNo.substring(0, 4) + " **** **** " + cardNo.substring(cardNoLength - 4, cardNoLength);
			}
			clBorrowModel.setCardNo(cardNo);
			clBorrowModel.setBank(bankCard.getBank());
		}

		// 当前进度说明和时间
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("borrowId", clBorrowModel.getId());
		param.put("state", clBorrowModel.getState());
		List<BorrowProgressModel> progressList = borrowProgressMapper.listIndex(param);
		if (StringUtil.isNotBlank(progressList) && progressList.size() > 0) {
			BorrowProgressModel p = progressList.get(0);
			clBorrowModel.setProgressTime(p.getCreateTime());
		}

		
		// 10 不可以 20 可以
		if (!BorrowModel.STATE_REPAY.equals(clBorrowModel.getState()) && !BorrowModel.STATE_RENEW.equals(clBorrowModel.getState())
				&& !BorrowModel.STATE_DELAY.equals(clBorrowModel.getState())) {
			clBorrowModel.setRenewState("10");
		} else {
			clBorrowModel.setRenewState("20");
		}

		if (!BorrowModel.STATE_REPAY.equals(clBorrowModel.getState()) && !BorrowModel.STATE_RENEW.equals(clBorrowModel.getState())
				&& !BorrowModel.STATE_DELAY.equals(clBorrowModel.getState())) {
			clBorrowModel.setRepayState("10");
		} else {
			clBorrowModel.setRepayState("20");
		}
		
//		clBorrowModel.setType(clBorrowModel.getDetailAppType(clBorrowModel.getState()));
		
		clBorrowModel.setRenewCount(renewCount);

		// 重置申请时间
		clBorrowModel.setCreateTime(borrow.getCreateTime());
		clBorrowModel.setCreditTimeStr(DateUtil.dateStr(borrow.getCreateTime(),"yyyy-M-d"));

		Map<String, Object> result = new HashMap<>();
		List<ClBorrowModel> brList = new ArrayList<ClBorrowModel>();
		brList.add(clBorrowModel);
		result.put("borrow", brList);
		result.put("repay", repayList);
		return result;
	}
	
//	@Override
//	public Map<String,Object> result(Borrow borrow) {
//		Map<String,Object> searchMap = new HashMap<>();
//		searchMap.put("borrowId", borrow.getId());
//		BorrowRepayLog log = borrowRepayLogMapper.findSelective(searchMap);
//		
//		List<BorrowRepayModel> repay = borrowRepayMapper.listSelModel(searchMap);
//		Map<String,Object> result = new HashMap<>();
//		ClBorrowModel clBorrowModel = new ClBorrowModel();
//		BeanUtils.copyProperties(borrow, clBorrowModel);
//		clBorrowModel.setCreditTimeStr(DateUtil.dateStr(clBorrowModel.getCreateTime(),"yyyy-M-d"));
//		clBorrowModel.setPenalty("20");
//		if (!repay.isEmpty()) {
//			clBorrowModel.setPenaltyAmount(repay.get(0).getPenaltyAmout());
//			if (repay.get(0).getPenaltyAmout()>0) {
//				clBorrowModel.setPenalty("10");
//				clBorrowModel.setOverdueAmount(String.valueOf(clBorrowModel.getAmount()+clBorrowModel.getPenaltyAmount()));
//			}
//		}else{
//			clBorrowModel.setOverdueAmount(String.valueOf(clBorrowModel.getAmount()));
//		}
//		searchMap.clear();
//		searchMap.put("userId", borrow.getUserId());
//		BankCard card = bankCardMapper.findSelective(searchMap);
//		if(StringUtil.isNotBlank(searchMap)){
//			clBorrowModel.setCardNo(card.getCardNo());
//			clBorrowModel.setBank(card.getBank());
//		}
//		
//		List<ClBorrowModel> brList = new ArrayList<ClBorrowModel>();
//		brList.add(clBorrowModel);
//		result.put("borrow", brList);
//		for (BorrowRepayModel borrowRepayModel : repay) {
//			borrowRepayModel.setRepayTimeStr(DateUtil.dateStr(borrowRepayModel.getRepayTime(),"yyyy-M-d"));
//			borrowRepayModel.setAmount(borrowRepayModel.getAmount()+borrowRepayModel.getPenaltyAmout());
//		}
//		if (StringUtil.isNotBlank(log)) {
//			for (BorrowRepayModel repayModel : repay) {
//				repayModel.setRealRepayTime(DateUtil.dateStr(log.getRepayTime(),"yyyy-M-d"));
//				repayModel.setRealRepayAmount(String.valueOf(log.getAmount()+log.getPenaltyAmout()));
//			}
//		}
//		result.put("repay", repay);
//		return result;
//	}
	
	/*private void addList(BorrowProgressModel bpModel) {
		if (bpModel.getState().equals(BorrowModel.STATE_PRE)
				||bpModel.getState().equals(BorrowModel.STATE_NEED_REVIEW)) {
			bpModel.setMsg("系统审核中,请耐心等待");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_AUTO_PASS)
				||bpModel.getState().equals(BorrowModel.STATE_PASS)) {
			bpModel.setMsg("恭喜通过风控审核");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_AUTO_REFUSED)
				||bpModel.getState().equals(BorrowModel.STATE_REFUSED)) {
			bpModel.setMsg("很遗憾,您未通过审核");
			bpModel.setType("20");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_REPAY)
				||bpModel.getState().equals(BorrowModel.STATE_REPAY_FAIL)) {
			bpModel.setMsg("打款中,请注意查收短信");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_FINISH)
				||bpModel.getState().equals(BorrowModel.STATE_REMISSION_FINISH)) {
			bpModel.setMsg("已还款");
			bpModel.setType("30");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_DELAY)) {
			bpModel.setMsg("已逾期,请尽快还款");
			bpModel.setType("20");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_BAD)) {
			bpModel.setMsg("已坏账");
			bpModel.setType("20");
		}
	}
	
	private String findBorrowDay(long userId) {
		String remark = null;
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		List<RepayModel> modelList = clBorrowMapper.findRepay(searchMap);
		for (RepayModel repayModel : modelList) {
			if (StringUtil.isNotBlank(repayModel)) {
				int day = DateUtil.daysBetween(new Date(), repayModel.getRepayTime());
				if (day>0) {
					remark = "您需要"+day+"天后还款"+repayModel.getAmount()+"元";
				}else if(day == 0){
					remark = "您需要在今天还款"+repayModel.getAmount()+"元";
				}
			}
		}
		return remark;
	}*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public  Page<ManageBorrowProgressModel> listModel(Map<String, Object> params, int currentPage,
                                                      int pageSize) {
		Map<String, Object> bparams=new HashMap<String, Object>();
		if(StringUtil.isNotBlank(params)){
			if(params.containsKey("realName")){
				bparams.put("realName", params.get("realName"));
			}
			if(params.containsKey("phone")){
				bparams.put("phone", params.get("phone"));
			}
			if(params.containsKey("orderNo")){
				bparams.put("orderNo", params.get("orderNo"));
			}
			List<ManageBorrowModel> borrowList = clBorrowMapper.listModel(bparams);
			
			if(StringUtil.isNotBlank(params)&&StringUtil.isNotBlank(bparams)&&StringUtil.isNotBlank(borrowList)){
				bparams=new HashMap<String, Object>();
				List borrowIds=new ArrayList();
				if(borrowList.size()>0){
					for(ManageBorrowModel b:borrowList){
						borrowIds.add(b.getId());
					}
				}else{
					borrowIds.add("0");
				}
				if(StringUtil.isNotBlank(borrowIds)){
				    params.put("borrowIds", borrowIds);
				}
			}
		}
		
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowProgressModel> list = borrowProgressMapper.listModel(params);
		if (StringUtil.isNotBlank(list)) {
			for (int i = 0; i < list.size(); i++) {
				ManageBorrowProgressModel model = list.get(i);
				Borrow b=clBorrowMapper.findByPrimary(model.getBorrowId());
				list.get(i).setAmount(b.getAmount());
				list.get(i).setOrderNo(b.getOrderNo());
				UserBaseInfo info=userBaseInfoMapper.findByUserId(model.getUserId());
				if (StringUtil.isNotBlank(info)) {
					list.get(i).setPhone(info.getPhone());
					list.get(i).setRealName(info.getRealName());
				}
			}
		}
		
		return (Page<ManageBorrowProgressModel>)list;
	}

	@Override
	public boolean save(BorrowProgress borrowProgress){
		int result = borrowProgressMapper.save(borrowProgress);
		if(result > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<BorrowProgress> listSeletetiv(Map<String, Object> map) {
		return borrowProgressMapper.listSelective(map);
	}
	
}
