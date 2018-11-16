package com.xindaibao.cashloan.rc.controller;

import javax.annotation.Resource;

import com.xindaibao.cashloan.rc.service.TppReqLogService;
import org.springframework.stereotype.Controller;

import com.xindaibao.cashloan.core.common.web.controller.BaseController;

/**
 * 第三方征信请求记录Controller
 *
 * @version 1.0.0
 * @date 2017-03-20 13:50:34
 */
@Controller
public class TppReqLogController extends BaseController {

	@Resource
	private TppReqLogService tppReqLogService;

}
