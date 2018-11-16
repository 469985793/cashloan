package com.xindaibao.creditrank.cr.service.impl;

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
import com.xindaibao.creditrank.cr.mapper.FactorParamMapper;
import com.xindaibao.creditrank.cr.mapper.ItemMapper;
import com.xindaibao.creditrank.cr.model.FactorParamModel;
import com.xindaibao.creditrank.cr.service.FactorParamService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.CreditException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 评分因子参数ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-05 11:13:30


 * 

 */
 
@Service("factorParamService")
public class FactorParamServiceImpl extends BaseServiceImpl<FactorParam, Long> implements FactorParamService {
	
   
    @Resource
    private FactorParamMapper factorParamMapper;
    @Resource
    private FactorMapper factorMapper;
    @Resource
    private ItemMapper itemMapper;
	@Resource
	private CardMapper cardMapper;

	@Override
	public BaseMapper<FactorParam, Long> getMapper() {
		return factorParamMapper;
	}

	@Override
	public Page<FactorParam> page(Map<String, Object> secreditrankhMap, int current,
			int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<FactorParam> list = factorParamMapper.listSelective(secreditrankhMap);
		return (Page<FactorParam>)list;
	}

	@Override
	public Map<String,Object> deleteSelective(long id) throws CreditException {
		int msg = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		FactorParam fp = factorParamMapper.findByPrimary(id);
		Factor ft = factorMapper.findByPrimary(fp.getFactorId());
		Item item = itemMapper.findByPrimary(ft.getItemId());
		Card card = cardMapper.findByPrimary(item.getCardId());
		
		if ("10".equals(card.getType())) {
			msg = factorParamMapper.deleteSelective(id);
		}
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "删除成功");
			//修改评分因子分值
			long factorId = ft.getId();
			Map<String, Object> factorMap = new HashMap<>();
			factorMap.put("id", ft.getId());
			factorMap.put("factorScore", factorParamMapper.findMaxScore(factorId));
			factorMapper.updateSelective(factorMap);
			//修改评分项目分值
			long itemId = item.getId();
			Map<String, Object> itemMap = new HashMap<String,Object>();
			itemMap.put("id", item.getId());
			itemMap.put("score", factorMapper.findSumScore(itemId));
			itemMapper.updateSelective(itemMap);
			//修改评分卡分值
			long cardId = card.getId();
			Map<String, Object> cardMap = new HashMap<String,Object>();
			cardMap.put("id", card.getId());
			cardMap.put("score", itemMapper.findSumScore(cardId));
			cardMapper.updateSelective(cardMap);
			
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "删除失败,请查看评分卡是否被使用");
		}
		return result;
	}
	
	@Override
	public int updateSelective(Map<String, Object> updateMap) {
		return factorParamMapper.updateSelective(updateMap);
	}

	@Override
	public List<FactorParamModel> listSelect(Map<String, Object> param) {
		return factorParamMapper.listSelect(param);
	}

	@Override
	public int save(FactorParam fp) {
		return factorParamMapper.save(fp);
	}

	@Override
	public FactorParam findByPrimary(long id) {
		return factorParamMapper.findByPrimary(id);
	}

	@Override
	public int deleteSelective(Long id) {
		return factorParamMapper.deleteSelective(id);
	}

	@Override
	public int findMaxScore(long factorId) {
		return factorParamMapper.findMaxScore(factorId);
	}

}