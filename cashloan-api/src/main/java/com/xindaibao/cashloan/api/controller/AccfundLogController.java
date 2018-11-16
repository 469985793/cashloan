package com.xindaibao.cashloan.api.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.xindaibao.cashloan.cl.service.AccfundLogService;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;

 /**
 * 公积金详细信息表(流水)Controller
 */
@Controller
public class AccfundLogController extends BaseController {

	@Resource
	private AccfundLogService accfundLogService;

}
