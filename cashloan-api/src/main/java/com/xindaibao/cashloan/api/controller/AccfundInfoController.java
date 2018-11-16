package com.xindaibao.cashloan.api.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.xindaibao.cashloan.cl.service.AccfundInfoService;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;

 /**
 * 公积金基本信息表Controller
 */
@Controller
public class AccfundInfoController extends BaseController {

	@Resource
	private AccfundInfoService accfundInfoService;

}
