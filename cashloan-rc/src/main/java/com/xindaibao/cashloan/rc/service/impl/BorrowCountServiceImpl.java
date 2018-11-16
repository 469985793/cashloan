package com.xindaibao.cashloan.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.rc.mapper.BorrowCountMapper;
import com.xindaibao.cashloan.rc.mapper.RcBorrowCountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.mapper.UserMapper;
import com.xindaibao.cashloan.rc.domain.BorrowCount;
import com.xindaibao.cashloan.rc.mapper.RcBorrowRepayCountMapper;
import com.xindaibao.cashloan.rc.service.BorrowCountService;


/**
 * 借款统计ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-10 14:59:59


 * 

 */
@SuppressWarnings("unused")
@Service("borrowCountService")
public class BorrowCountServiceImpl extends BaseServiceImpl<BorrowCount, Long> implements BorrowCountService {
	
	private static final Logger logger = LoggerFactory.getLogger(BorrowCountServiceImpl.class);
   
    @Resource
    private BorrowCountMapper borrowCountMapper;
    @Resource
	private UserMapper userMapper;
    @Resource
    private RcBorrowCountMapper rcBorrowCountMapper;
    @Resource
    private RcBorrowRepayCountMapper rcBorrowRepayCountMapper;

	@Override
	public BaseMapper<BorrowCount, Long> getMapper() {
		return borrowCountMapper;
	}

	/**
	 * 历史借款信息统计
	 * @param userId
	 * @return
	 */
	public int countBorrowRefusedTimes(Long userId){
		BorrowCount bc = new BorrowCount();
		bc.setUserId(userId);
		//借款人信息统计
		bc.setCount(rcBorrowCountMapper.borrowCount(userId));//借款总次数
		bc.setFailCount(rcBorrowCountMapper.borrowFailCount(userId));//借款不通过次数
		bc.setDayFailCount(rcBorrowCountMapper.dayFailCount(userId));//当日借款不通过次数
		bc.setCountOne(rcBorrowCountMapper.failCount(userId));//逾期未还次数
		bc.setCountTwo(rcBorrowRepayCountMapper.countFailNinety(userId));//逾期90天以上已还次数
		bc.setCountThree(rcBorrowRepayCountMapper.countFailThirty(userId));//逾期90天以内,30天以上已还次数
		bc.setCountFour(rcBorrowRepayCountMapper.countFailWithinThirty(userId));//逾期30天以内已还次数
		//紧急联系人信息统计
		bc.setCountFive(rcBorrowCountMapper.failCountRelative(userId));//紧急联系人有逾期未还借款数
		bc.setCountSix(rcBorrowRepayCountMapper.countRelativeNinety(userId));//紧急联系人逾期超过90天的借款
		bc.setCountSeven(rcBorrowRepayCountMapper.countRelativeThirty(userId));//紧急联系人逾期超过30天,小于90天的借款
		bc.setCountEight(rcBorrowRepayCountMapper.countRelativeWithinThirty(userId));//紧急联系人逾期小于30天的借款
		
		bc.setCreateTime(DateUtil.getNow());//统计时间
		
		return borrowCountMapper.save(bc);
	}

	@Override
	public int save() {
		int msg = 0;
		Map<String,Object> paramMap = new HashMap<>();
		List<User> list = userMapper.listSelective(paramMap);
		BorrowCount bc = new BorrowCount();
		for (User user : list) {
			bc.setUserId(user.getId());
			//借款人信息统计
			bc.setCount(rcBorrowCountMapper.borrowCount(user.getId()));//借款总次数
			bc.setFailCount(rcBorrowCountMapper.borrowFailCount(user.getId()));//借款不通过次数
			bc.setDayFailCount(rcBorrowCountMapper.dayFailCount(user.getId()));//当日借款不通过次数
			bc.setCountOne(rcBorrowCountMapper.failCount(user.getId()));//逾期未还次数
			bc.setCountTwo(rcBorrowRepayCountMapper.countFailNinety(user.getId()));//逾期90天以上已还次数
			bc.setCountThree(rcBorrowRepayCountMapper.countFailThirty(user.getId()));//逾期90天以内,30天以上已还次数
			bc.setCountFour(rcBorrowRepayCountMapper.countFailWithinThirty(user.getId()));//逾期30天以内已还次数
			//紧急联系人信息统计
			bc.setCountFive(rcBorrowCountMapper.failCountRelative(user.getId()));//紧急联系人有逾期未还借款数
			bc.setCountSix(rcBorrowRepayCountMapper.countRelativeNinety(user.getId()));//紧急联系人逾期超过90天的借款
			bc.setCountSeven(rcBorrowRepayCountMapper.countRelativeThirty(user.getId()));//紧急联系人逾期超过30天,小于90天的借款
			bc.setCountEight(rcBorrowRepayCountMapper.countRelativeWithinThirty(user.getId()));//紧急联系人逾期小于30天的借款
			
			bc.setCreateTime(DateUtil.getNow());//统计时间
			
			msg = borrowCountMapper.save(bc);
		}
		return msg;
	}
	
	public int dispose(long userId,int i){
		int msg = 0;
		return msg;
	}
}