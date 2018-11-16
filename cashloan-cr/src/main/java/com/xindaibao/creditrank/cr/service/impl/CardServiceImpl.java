package com.xindaibao.creditrank.cr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.mapper.CardMapper;
import com.xindaibao.creditrank.cr.service.FactorService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.CreditException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.NidGenerator;
import com.xindaibao.creditrank.cr.domain.Card;
import com.xindaibao.creditrank.cr.service.CardService;
import com.xindaibao.creditrank.cr.service.ItemService;


/**
 * 评分卡ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:06:51


 * 

 */
 
@Service("cardService")
public class CardServiceImpl extends BaseServiceImpl<Card, Long> implements CardService {
	
   
    @Resource
    private CardMapper cardMapper;
    
    @Resource
    private ItemService itemSercice;
    
    @Resource
    private FactorService factorService;
    
    @Resource
    private CardService cardService;
    

	@Override
	public BaseMapper<Card, Long> getMapper() {
		return cardMapper;
	}



	/**
	 * 分页查询
	 */
	@Override
	public Page<Card> page(Map<String, Object> params,int current,int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<Card> list = cardMapper.listSelective(params);
		return (Page<Card>)list;
	}

	/**
	 * 保存数据
	 * @throws CreditException 
	 */
	@Override
	public Map<String,Object> save(String cardName) throws CreditException {
		Card card = new Card();
		card.setCardName(cardName);
		card.setAddTime(new Date());
		card.setScore(0);
		card.setType(Card.CARD_TYPE_UNUSED);
		card.setState(Card.CARD_STATE_DISABLE);
		card.setNid(NidGenerator.getCardNid());
		int msg = cardMapper.save(card);
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "新增成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "新增失败");
		}
		return result;
	}

	/**
	 * 主键查询
	 */
	@Override
	public Card findByPrimary(long cardId) {
		return cardMapper.findByPrimary(cardId);
	}


	@Override
	public Map<String,Object> updateState(long id,String state) throws CreditException {
		int msg = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("state", state);
		msg = cardMapper.updateState(map);
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
		}
		return result;
	}



	@Override
	public void updateSelective(Map<String, Object> cardMap) {
		cardMapper.updateSelective(cardMap);
	}



	@Override
	public List<Card> findAll() {
		return cardMapper.findAll();
	}



	@Override
	public Map<String, Object> update(long id,String cardName) {
		int msg = 0;
		Card card = cardMapper.findByPrimary(id);
		if (Card.CARD_TYPE_UNUSED.equals(card.getType())) {
			Map<String, Object> cardMap = new HashMap<String, Object>();
			cardMap.put("id", id);
			cardMap.put("cardName", cardName);
			msg = cardMapper.updateSelective(cardMap);
		}else {
			msg = -1;
		}
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改失败");
			if (msg==-1) {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "评分卡已被使用!");
			}
		}
		return result;
	}
	
}
