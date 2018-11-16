package com.xindaibao.cashloan.rc.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xindaibao.cashloan.core.common.web.controller.BaseController;


 /**
 * 场景与第三方征信接口关联关系Controller
 *
 * @version 1.0.0
 * @date 2017-03-14 13:42:36
 */
@Controller
@Scope("prototype")
public class SceneBusinessLogController extends BaseController {
	
	public static final Logger logger = LoggerFactory.getLogger(SceneBusinessLogController.class);

}
