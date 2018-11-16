package com.xindaibao.creditrank.cr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.domain.Card;
import com.xindaibao.creditrank.cr.domain.Factor;
import com.xindaibao.creditrank.cr.domain.FactorParam;
import com.xindaibao.creditrank.cr.domain.Item;
import com.xindaibao.creditrank.cr.mapper.CardMapper;
import com.xindaibao.creditrank.cr.mapper.FactorMapper;
import com.xindaibao.creditrank.cr.mapper.ItemMapper;
import com.xindaibao.creditrank.cr.model.FactorModel;
import com.xindaibao.creditrank.cr.service.FactorService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.CreditException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.NidGenerator;
import com.xindaibao.creditrank.cr.mapper.FactorParamMapper;
import com.xindaibao.creditrank.cr.model.FactorParamModel;


/**
 * 评分因子ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:11:15


 * 

 */
 
@Service("factorService")
public class FactorServiceImpl extends BaseServiceImpl<Factor, Long> implements FactorService {
	
    Map<String,Object> data;
    @Resource
    private FactorMapper factorMapper;
    @Resource
    private ItemMapper itemMapper;
    @Resource
	private FactorParamMapper factorParamMapper;
	@Resource
	private CardMapper cardMapper;

	@Override
	public BaseMapper<Factor, Long> getMapper() {
		return factorMapper;
	}


	@Override
	public Factor findByFactorName(String factorName) {
		return factorMapper.findByFactorName(factorName);
	}

	@Override
	public Page<FactorModel> page(Map<String, Object> secreditrankhMap, int current,
                                  int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<FactorModel> list = factorMapper.listSelect(secreditrankhMap);
		return (Page<FactorModel>)list;
	}
	

	@Override
	public int updateSelective(Map<String, Object> updateMap) {
		return factorMapper.updateSelective(updateMap);
	}


	@Override
	public List<FactorModel> listSelect(Map<String, Object> factor) {
		return factorMapper.listSelect(factor);
	}


	@Override
	public Factor findByPrimary(long id) {
		return factorMapper.findByPrimary(id);
	}


	@Override
	public List<Factor> findByItemId(long id) {
		return factorMapper.findByItemId(id);
	}


	@Override
	public Map<String,Object> save(Map<String, Object> factorMap,List<Map<String,Object>> list) throws CreditException {
		int msg = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		
		Item item = itemMapper.findByPrimary(Long.parseLong(factorMap.get("itemId").toString()));
		Card card = cardMapper.findByPrimary(item.getCardId());
		if (Card.CARD_TYPE_UNUSED.equals(card.getType())) {
			Factor factor = new Factor();
			factor.setItemId(item.getId());
			factor.setFactorName(factorMap.get("factorName").toString());
			factor.setFactorScore(getScore(list));
			factor.setType(factorMap.get("type").toString());
			factor.setNnid(factorMap.get("nnid").toString());
			factor.setCtable(factorMap.get("ctable").toString());
			factor.setCtableName(factorMap.get("ctableName").toString());
			factor.setCcolumn(factorMap.get("ccolumn").toString());
			factor.setCcolumnName(factorMap.get("ccolumnName").toString());
			factor.setCtype(factorMap.get("ctype").toString());
			factor.setCardId(Long.parseLong(factorMap.get("cardId").toString()));
			factor.setNid(NidGenerator.getFactorNid());
			factor.setState("10");
			factor.setAddTime(new Date());
			long id = factorMapper.save(factor);
			
			if (id>0) {
				if (list != null && !list.isEmpty()) {
					int paramMsg = saveOrUpdate(list,factor);
					msg = paramMsg;
				}
				Map<String,Object> cardMap = new HashMap<String,Object>();
				cardMap.put("id", card.getId());
				cardMap.put("score", card.getScore()+getScore(list));
				cardMapper.updateSelective(cardMap);
				
				Map<String,Object> itemMap = new HashMap<String,Object>();
				itemMap.put("id", item.getId());
				itemMap.put("score", item.getScore()+getScore(list));
				itemMapper.updateSelective(itemMap);
			}
			if (msg>0) {
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "新增成功");
			}else {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "新增异常");
			}
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "评分卡已被使用无法编辑!");
		}
		return result;
	}


	@Override
	public Map<String,Object> updateSelective(Map<String, Object> factorMap,List<Map<String, Object>> list) throws CreditException{
		int msg = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		
		Factor factor = factorMapper.findByPrimary(Long.parseLong(factorMap.get("id").toString()));
		Item item = itemMapper.findByPrimary(factor.getItemId());
		Card card = cardMapper.findByPrimary(item.getCardId());
		
		if (Card.CARD_TYPE_UNUSED.equals(card.getType())) {
			factorMap.put("factorScore", getScore(list));
			msg = factorMapper.updateSelective(factorMap);
			Map<String,Object> secreditrankh = new HashMap<String,Object>();
			secreditrankh.put("factorId", factorMap.get("id"));
			if (msg>0) {
				int code = 0;
				code = getScore(list)-factor.getFactorScore();
				
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("id", factorMap.get("itemId"));
				itemMap.put("score", item.getScore()+code);
				itemMapper.updateSelective(itemMap);
				
				Map<String, Object> cardMap = new HashMap<String,Object>();
				cardMap.put("id", item.getCardId());
				cardMap.put("score", card.getScore()+code);
				cardMapper.updateSelective(cardMap);
				
				if (list != null && !list.isEmpty()) {
					msg = saveOrUpdate(list,factor);
				}
			}
			if (msg>0) {
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "修改成功");
			}else {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "修改失败");
			}
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "评分卡已被使用无法编辑!");
		}
		return result;
	}
	
	public int saveOrUpdate(List<Map<String,Object>> list,Factor factor){
		FactorParam fp = new FactorParam();
		int msg = 0;
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> fpm = list.get(i);
			Factor ft = factorMapper.findByPrimary(factor.getId());
			if (Integer.parseInt(fpm.get("paramScore").toString())<=ft.getFactorScore()) {
				long id = Long.parseLong(fpm.get("id").toString());
				fp.setId(id);
				fp.setParamScore(Integer.parseInt(fpm.get("paramScore").toString()));
				fp.setFormula(fpm.get("formula").toString());
				fp.setCvalue(fpm.get("cvalue").toString());
				fp.setFactorId(factor.getId());
				fp.setAddTime(new Date());
				if (id==0) {
					fp.setState("10");
					msg = factorParamMapper.save(fp);
				}else {
					msg = factorParamMapper.updateSelective(fpm);
				}
			}
		}
		return msg;
	}
	
	public int getScore(List<Map<String,Object>> list){
		int score = 0;
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = list.get(i);
			int factorScore = Integer.parseInt(map.get("paramScore").toString());
			if (factorScore>score) {
				score = factorScore;
			}
		}
		return score;
	}
	
	@Override
	public Map<String,Object> deleteSelective(long id) throws CreditException {
		int msg = 0;
		Factor ft = factorMapper.findByPrimary(id);
		Item item = itemMapper.findByPrimary(ft.getItemId());
		Card card = cardMapper.findByPrimary(item.getCardId());
		Map<String,Object> result = new HashMap<String,Object>();
		if (Card.CARD_TYPE_UNUSED.equals(card.getType())) {
			msg = factorMapper.deleteSelective(id);
			if (msg>0) {
				
				Map<String, Object> cardMap = new HashMap<String,Object>();
				cardMap.put("id", card.getId());
				cardMap.put("score", card.getScore()-ft.getFactorScore());
				cardMapper.updateSelective(cardMap);
				
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("id", item.getId());
				itemMap.put("score", item.getScore()-ft.getFactorScore());
				itemMapper.updateSelective(itemMap);
				
				Map<String,Object> param = new HashMap<>();
				param.put("factorId", ft.getId());
				List<FactorParamModel> fpList = factorParamMapper.listSelect(param);
				for (FactorParamModel factorParamModel : fpList) {
					msg = factorParamMapper.deleteSelective(factorParamModel.getId());
				}
			}
		}
		
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "删除失败,请查看评分卡是否被使用");
		}
		return result;
	}


	@Override
	public int deleteSelective(Long id) {
		return factorMapper.deleteSelective(id);
	}


	@Override
	public int findSumScore(long itemId) {
		return factorMapper.findSumScore(itemId);
	}


	@Override
	public List<FactorModel> listFactorModel(Map<String, Object> factor) {
		return factorMapper.listFactorModel(factor);
	}
}