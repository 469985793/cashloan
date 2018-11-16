package com.xindaibao.cashloan.api.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xindaibao.cashloan.cl.service.UserInviteService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.service.CloanUserService;

 /**
 * 邀请记录Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 15:54:41


 * 

 */
@Scope("prototype")
@Controller
public class UserInviteController extends BaseController {

	@Resource
	private UserInviteService userInviteService;
	@Resource
	private CloanUserService cloanUserService;
	
	/**
	 * 电话查询
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/userInvite/findPhone.htm")
	public void findPhone() throws Exception {
        Object userIdOnSession = request.getSession().getAttribute("userId");
        if (userIdOnSession == null) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put(Constant.RESPONSE_DATA, "");
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "查询失败");
            ServletUtils.writeToResponse(response, result);
        } else {
            Long userId = Long.valueOf(userIdOnSession.toString());
            Map<String, Object> data = userInviteService.findPhone(userId);
            Map<String, Object> result = new HashMap<String, Object>();
            result.put(Constant.RESPONSE_DATA, data);
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
            ServletUtils.writeToResponse(response, result);
        }
	}

	/**
	 * 邀请页面
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/userInvite/findInvite.htm")
	public void findInvite(@RequestParam(value="userId") long id)  {
		String serverHost = Global.getValue("server_host");
		int rate = Global.getInt("level_one");
		String path =  request.getSession().getServletContext().getRealPath("/") ;
		StringBuilder buffer = new StringBuilder(path).append(File.separatorChar)
				.append("static").append(File.separatorChar).append("images")
				.append(File.separator).append("invite_logo.png");
		User user = new User();//cloanUserService.getById(id);

		Map<String,Object> data = new HashMap<>();
		String title  = Global.getValue("title");
		String remark_invite  = Global.getValue("remark_invite");
		String url = Global.getValue("h5_invite");//邀请注册地址
		String filePath = StringUtil.isNull(buffer);

		data.put("url", url+"?invitationCode="+user.getInvitationCode());
		data.put("title",title);
		data.put("remark",remark_invite);
		data.put("inviteLogo",serverHost +"/readFile.htm?path="+filePath);
		data.put("rate", rate+"%");
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	
}
