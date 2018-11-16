package com.xindaibao.cashloan.cl.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.OperatorBills;
import com.xindaibao.cashloan.cl.model.OperatorBillsModel;
import com.xindaibao.cashloan.cl.service.OperatorBillsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xindaibao.cashloan.cl.mapper.OperatorBillsMapper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;



/**
 * 运营商信息--月账单ServiceImpl
 * @author
 * @version 1.0.0
 * @date 2017-03-13 15:56:20


 * 

 */
@Service("operatorBillsService")
@Transactional(rollbackFor=Exception.class)
public class OperatorBillsServiceImpl extends BaseServiceImpl<OperatorBills, Long> implements OperatorBillsService {
	
   
    @Resource
    private OperatorBillsMapper operatorBillsMapper;

	@Override
	public BaseMapper<OperatorBills, Long> getMapper() {
		return operatorBillsMapper;
	}

	@Override
	public void saveRecords(List<OperatorBillsModel> bills) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(OperatorBillsModel tempBill : bills){
			OperatorBills bill = tempBill;
			bill.setBillMonthDateStart(sdf.parse(tempBill.getBillMonthDate().substring(0, 10)+" 00:00:00"));
			bill.setBillMonthDateEnd(sdf.parse(tempBill.getBillMonthDate().substring(11, 21)+" 23:59:59"));
			operatorBillsMapper.save(bill);
		}
	}
	
}