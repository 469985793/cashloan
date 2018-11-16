package com.xindaibao.creditrank.cr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.domain.Factor;
import com.xindaibao.creditrank.cr.domain.Item;
import com.xindaibao.creditrank.cr.mapper.CardMapper;
import com.xindaibao.creditrank.cr.mapper.ItemMapper;
import com.xindaibao.creditrank.cr.model.ItemModel;
import org.springframework.stereotype.Service;

import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.CreditException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.NidGenerator;
import com.xindaibao.creditrank.cr.domain.Card;
import com.xindaibao.creditrank.cr.mapper.FactorMapper;
import com.xindaibao.creditrank.cr.mapper.FactorParamMapper;
import com.xindaibao.creditrank.cr.model.FactorParamModel;
import com.xindaibao.creditrank.cr.service.ItemService;


/**
 * 评分项目ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:09:22


 * 

 */
 
@Service("itemService")
public class ItemServiceImpl extends BaseServiceImpl<Item, Long> implements ItemService {
	
   
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private CardMapper cardMapper;
    @Resource
    private FactorMapper factorMapper;
	@Resource
	private FactorParamMapper factorParamMapper;




	@Override
	public BaseMapper<Item, Long> getMapper() {
		return itemMapper;
	}




	@Override
	public Map<String,Object> save(String itemName,Long cardId) throws CreditException {
		int msg = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		Card card = cardMapper.findByPrimary(cardId);
		if (Card.CARD_TYPE_UNUSED.equals(card.getType())) {
			Item item = new Item();
			item.setCardId(cardId);
			item.setItemName(itemName);
			item.setScore(0);
			item.setState("10");
			item.setNid(NidGenerator.getItemNid());
			item.setAddTime(new Date());
			msg = itemMapper.save(item);
			if (msg>0) {
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "新增成功");
			}else {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "新增失败");
			}

		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "评分卡已被使用无法编辑!");
		}
		return result;
	}
	
	@Override
	public int updateSelective(Map<String, Object> map) {
		return itemMapper.updateSelective(map);
	}


	@Override
	public Page<ItemModel> page(Map<String, Object> secreditrankhMap, int current,
                                int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<ItemModel> list = itemMapper.listSelect(secreditrankhMap);
		return (Page<ItemModel>)list;
	}

	@Override
	public Item findByPrimary(long id) {
		return itemMapper.findByPrimary(id);
	}

	@Override
	public List<ItemModel> listSelect(Map<String, Object> secreditrankhMap) {
		return itemMapper.listSelect(secreditrankhMap);
	}

	


	@Override
	public Map<String,Object> deleteSelective(long id) throws CreditException {
		int msg = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		Item item = itemMapper.findByPrimary(id);
		Card card = cardMapper.findByPrimary(item.getCardId());
		if (Card.CARD_TYPE_UNUSED.equals(card.getType())) {
			if (StringUtil.isNotBlank(item)) {
				if (Card.CARD_STATE_DISABLE.equals(card.getState())) {
					msg = itemMapper.deleteSelective(id);
					if (msg>0) {
						List<Factor> fac = factorMapper.findByItemId(id);
						Map<String,Object> param = new HashMap<>();
						for (Factor factor : fac) {
							param.put("factorId", factor.getId());
							msg = factorMapper.deleteSelective(factor.getId());
							List<FactorParamModel> fpList = factorParamMapper.listSelect(param);
							for (FactorParamModel factorParamModel : fpList) {
								msg = factorParamMapper.deleteSelective(factorParamModel.getId());
							}
						}
					}
				}
			}
			if (msg>0) {
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "删除成功");
			}else {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "删除异常,请查看评分卡是否禁用");
			}
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "评分卡已被使用无法编辑!");
		}
		return result;
	}




	@Override
	public Map<String,Object> list(long cardId) {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> secreditrankh = new HashMap<String,Object>();
		secreditrankh.put("cardId", cardId);
		List<Map<String,Object>> item = new ArrayList<Map<String,Object>>();
		List<ItemModel> itemList = itemMapper.listSelect(secreditrankh);
		for (ItemModel itemModel : itemList) {
			Map<String,Object> itemName = new HashMap<String,Object>();
			itemName.put("id", itemModel.getId());
			itemName.put("itemName", itemModel.getItemName());
			item.add(itemName);
		}
		result.put(Constant.RESPONSE_DATA, item);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		return result;
	}




	@Override
	public int findSumScore(long cardId) {
		return itemMapper.findSumScore(cardId);
	}

	
}