package com.xindaibao.creditrank.cr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.model.RankModel;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.creditrank.cr.domain.Rank;
import com.xindaibao.creditrank.cr.domain.RankDetail;
import com.xindaibao.creditrank.cr.mapper.RankDetailMapper;
import com.xindaibao.creditrank.cr.mapper.RankMapper;
import com.xindaibao.creditrank.cr.service.RankDetailService;
import com.xindaibao.creditrank.cr.service.RankService;


/**
 * 评分等级ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:09:59


 * 

 */
 
@Service("rankService")
public class RankServiceImpl extends BaseServiceImpl<Rank, Long> implements RankService {
	
   
    @Resource
    private RankMapper rankMapper;
    @Resource
	private RankService rankService;
    @Resource
	private RankDetailService rankDetailService;
    @Resource
    private RankDetailMapper rankDetailMapper;

	@Override
	public BaseMapper<Rank, Long> getMapper() {
		return rankMapper;
	}


	@Override
	public Page<Rank> page(Map<String, Object> searchMap, int current,
			int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<Rank> list = rankMapper.listSelective(searchMap);
		return (Page<Rank>)list;
	}

	@Override
	public int updateSelective(Map<String, Object> updateMap) {
		return rankMapper.updateSelective(updateMap);
	}

	@Override
	public List<Rank> listSelective(Map<String, Object> search) {
		return rankMapper.listSelective(search);
	}

	@Override
	public Map<String,Object> save(List<Map<String, Object>> list,String rankName, long id) {
		//不需要删除的rankDetail
		List<Long> noDeleteList = new ArrayList<Long>();
		//所有的rankDetail
		List<Long> allList = new ArrayList<Long>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rankId", id);
		List<RankDetail> detailList = rankDetailMapper.listSelective(paramMap);
		for (RankDetail rd:detailList){
			allList.add(rd.getId());
		}
		int msg = 0;
		Rank rank = new Rank();
		if (id>0) {
			rank.setId(id);
			Map<String,Object> rankMap = new HashMap<String, Object>();
			rankMap.put("id", id);
			rankMap.put("rankName", rankName);
			msg = rankMapper.updateSelective(rankMap);
		}else {
			rank.setRankName(rankName);
			msg = rankMapper.save(rank);
		}
		if (msg>0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> rankDetailMap = list.get(i);
				RankDetail rankDetail = new RankDetail();
				rankDetail.setRankId(rank.getId());
				rankDetail.setRank(rankDetailMap.get("rank").toString());
				rankDetail.setRtype(rankDetailMap.get("rtype").toString());
				BigDecimal amountMax = BigDecimal.valueOf(Double.valueOf(rankDetailMap.get("amountMax").toString()));
				BigDecimal amountMin = BigDecimal.valueOf(Double.valueOf(rankDetailMap.get("amountMin").toString()));
				rankDetail.setAmountMax(amountMax);
				rankDetail.setAmountMin(amountMin);
				rankDetail.setScoreMax(Integer.parseInt(rankDetailMap.get("scoreMax").toString()));
				rankDetail.setScoreMin(Integer.parseInt(rankDetailMap.get("scoreMin").toString()));
				if (Long.parseLong(rankDetailMap.get("id").toString())==0) {
					rankDetail.setState("10");
					rankDetail.setAddTime(new Date());
					msg = rankDetailService.save(rankDetail);
				}else {
					rankDetail.setId(Long.parseLong(rankDetailMap.get("id").toString()));
					rankDetail.setState(rankDetailMap.get("state").toString());
					msg = rankDetailService.updateSelective(rankDetailMap);
					noDeleteList.add(Long.parseLong(rankDetailMap.get("id").toString()));
				}
			}
		}
		int i = 0;
		boolean bb = allList.removeAll(noDeleteList);//集合相减得出需要删除的list
		if(bb && allList.size()>0){//执行删除
			for(Long rankDetailId:allList){
				i = rankDetailMapper.deleteSelective(rankDetailId);
			}
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg<0 || i<0) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		}
		return result;
	}


	@Override
	public Rank findSelective(Map<String, Object> search) {
		return rankMapper.findSelective(search);
	}


	@Override
	public Map<String,Object> deleteSelective(long id) {
		int msg = rankMapper.deleteSelective(id);
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "删除失败");
		}
		return result;
	}



	@Override
	public Page<RankModel> countList(Map<String, Object> searchMap, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<RankModel> list = rankMapper.countList(searchMap);
		return (Page<RankModel>)list;
	}


	@Override
	public List<Rank> findAll() {
		return rankMapper.findAll();
	}
	
	
	
}
