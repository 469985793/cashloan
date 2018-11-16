package com.xindaibao.cashloan.cl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.QianchengReqlog;
import com.xindaibao.cashloan.cl.mapper.QianchengReqlogMapper;
import com.xindaibao.cashloan.cl.model.QianchengReqlogModel;
import com.xindaibao.cashloan.cl.service.QianchengReqlogService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 浅橙借款申请审核ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-15 11:46:54


 * 

 */
 
@Service("qianchengReqlogService")
public class QianchengReqlogServiceImpl extends BaseServiceImpl<QianchengReqlog, Long> implements QianchengReqlogService {
	
    @Resource
    private QianchengReqlogMapper qianchengReqlogMapper;

	@Override
	public BaseMapper<QianchengReqlog, Long> getMapper() {
		return qianchengReqlogMapper;
	}


	@Override
	public Page<QianchengReqlogModel> listQianchengReqlog(Map<String, Object> params, 
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<QianchengReqlogModel> list = qianchengReqlogMapper.listQianchengReqlog(params);
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				QianchengReqlogModel reqLog = new QianchengReqlogModel();
				reqLog.setState(list.get(i).getState());
				list.get(i).setStateStr(reqLog.getStateStr());
			}
		}
		return (Page<QianchengReqlogModel>) list;
	}


	@Override
	public QianchengReqlog findByOrderNo(String orderNo) {
		return qianchengReqlogMapper.findByOrderNo(orderNo);
	}


	@Override
	public int update(QianchengReqlog reqLog) {
		return qianchengReqlogMapper.update(reqLog);
	}


	@Override
	public QianchengReqlog findByBorrowId(Long borrowId) {
		return qianchengReqlogMapper.findByBorrowId(borrowId);
	}

	
	
}