package com.xindaibao.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;
import tool.util.StringUtil;

import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.mapper.BankCardMapper;
import com.xindaibao.cashloan.cl.model.pay.lianlian.CancelAuthSignModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.service.BankCardService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.mapper.UserMapper;

/**
 * 银行卡ServiceImpl
 * 
 * @author
 * @version 1.0.0


 * 

 */

@Service("bankCardService")
public class BankCardServiceImpl extends BaseServiceImpl<BankCard, Long>
		implements BankCardService {

	private static final Logger logger = LoggerFactory
			.getLogger(BankCardServiceImpl.class);

	@Resource
	private BankCardMapper bankCardMapper;

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserBaseInfoMapper userBaseInfoMapper;

	@Override
	public BaseMapper<BankCard, Long> getMapper() {
		return bankCardMapper;
	}

	@Override
	public boolean save(BankCard bankCard) {
		int result = bankCardMapper.save(bankCard);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public BankCard getBankCardByUserId(Long userId) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			return bankCardMapper.findSelective(paramMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public BankCard findSelective(Map<String, Object> paramMap) {
		return bankCardMapper.findSelective(paramMap);
	}

	@Override
	public int cancelAuthSign(BankCard card) {
		// 查询用户并解约
		User user = userMapper.findByPrimary(card.getUserId());
		if (null != user && StringUtil.isNotBlank(card.getAgreeNo())) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("userUuid", user.getUuid());
			paramMap.put("agreeNo", card.getAgreeNo());
			CancelAuthSignModel model = (CancelAuthSignModel) LianLianHelper
					.cancelAuthSign(paramMap);

			if (model.checkReturn()) {
				logger.info("取消授权成功");
			} else {
				logger.info("取消授权失败 ,原因：" + model.getRet_msg());
			}
		}

		int result = bankCardMapper.update(card);
		return result;
	}

	@Override
	public boolean updateSelective(Map<String, Object> paramMap) {
		int result = bankCardMapper.updateSelective(paramMap);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public BankCard findByUserId(Long userId) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			return bankCardMapper.findSelective(paramMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	
	@Override
	public boolean saveOrUpdate(Long userId, String bank, String cardNo, String agreeNo) {
		BankCard bankCard = bankCardMapper.findByUserId(userId);
		UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(userId);

		int count = 0;
		if (null == bankCard) {
			BankCard card = new BankCard();
			card.setCardNo(cardNo);
			card.setBindTime(DateUtil.getNow());
			card.setUserId(userId);
			card.setBank(bank);
			card.setPhone(baseInfo.getPhone());
			card.setAgreeNo(agreeNo);
			count = bankCardMapper.save(card);
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", bankCard.getId());
			paramMap.put("bank", bank);
			paramMap.put("cardNo", cardNo);
			paramMap.put("phone", baseInfo.getPhone());
			paramMap.put("agreeNo", agreeNo);
			count = bankCardMapper.updateSelective(paramMap);
		}
		if (count > 0){
			return true;
		}else{
			return false;
		}
	}
}
