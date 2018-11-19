package com.xindaibao.cashloan.api.controller;

import com.xindaibao.cashloan.api.user.bean.AppAbsActionWrapper;
import com.xindaibao.cashloan.api.user.bean.AppDbSession;
import com.xindaibao.cashloan.api.user.bean.AppLoginedActionWraper;
import com.xindaibao.cashloan.api.user.service.DBService;
import com.xindaibao.cashloan.api.user.service.MybatisService;
import com.xindaibao.cashloan.api.user.service.SmsService;
import com.xindaibao.cashloan.cl.model.SmsModel;
import com.xindaibao.cashloan.cl.service.ClSmsService;
import com.xindaibao.cashloan.core.common.util.MapUtil;
import com.xindaibao.cashloan.core.common.util.SqlUtil;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.service.CloanUserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tool.util.BeanUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import java.io.InputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
/**
 * Created by lsk on 2017/2/15.
 */
@Scope("prototype")
@Controller
@RequestMapping("/api/act/user")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserController {
	@Resource
	private DBService dbService;

	@Resource
	private MybatisService mybatisService;

	@Resource
	private SmsService smsService;

	@Resource
	private AppDbSession appDbSession;
	
	@Resource
	private CloanUserService userService;






	@RequestMapping("changeLoginPwd")
	public void changeLoginPwd(final HttpServletRequest request,
			HttpServletResponse response, final String oldPwd,
			final String newPwd) {
		new AppLoginedActionWraper(response, request) {
			@Override
			public Object doAction(Map userData, String userId) {
				if (StringUtil.isEmpty(oldPwd) || StringUtil.isEmpty(newPwd)) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "参数不能为空");
					return ret;
				}
				Map user = mybatisService.queryRec("usr.queryUser", userId);

				if (!oldPwd.equals((String) user.get("login_pwd"))) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "原密码不正确");
					return ret;
				}

				dbService.update(SqlUtil.buildUpdateSql("cl_user",
						new Object[][] { { "id", user.get("id") },
								{ "login_pwd", newPwd },
								{ "loginpwd_modify_time", new Date() } }));

				Map ret = new LinkedHashMap();
				ret.put("success", true);
				ret.put("msg", "登录密码修改成功");
				return ret;
			}
		};
	}

	@RequestMapping("changeTradePwd")
	public void changeTradePwd(final HttpServletRequest request,
			HttpServletResponse response, final String oldPwd,
			final String newPwd) {
		new AppLoginedActionWraper(response, request) {
			@Override
			public Object doAction(Map userData, String userId) {
				if (StringUtil.isEmpty(oldPwd) || StringUtil.isEmpty(newPwd)) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "参数不能为空");
					return ret;
				}
				Map user = mybatisService.queryRec("usr.queryUser", userId);

				String oldTradePwd = (String) user.get("trade_pwd");
				if (oldTradePwd == null) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "请先上设置初始交易密码");
					return ret;
				}

				if (!oldPwd.equals(oldTradePwd)) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "原密码不正确");
					return ret;
				}

				dbService.update(SqlUtil.buildUpdateSql("cl_user",
						new Object[][] { { "id", user.get("id") },
								{ "trade_pwd", newPwd },
								{ "tradepwd_modify_time", new Date() } }));

				Map ret = new LinkedHashMap();
				ret.put("success", true);
				ret.put("msg", "交易密码修改成功");
				return ret;
			}
		};
	}

	@RequestMapping("setTradePwd")
	public void setTradePwd(final HttpServletRequest request,
			HttpServletResponse response, final String pwd) {
		new AppLoginedActionWraper(response, request) {
			@Override
			public Object doAction(Map userData, String userId) {
				if (StringUtil.isEmpty(pwd)) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "参数不能为空");
					return ret;
				}
				Map user = mybatisService.queryRec("usr.queryUser", userId);

				if (!StringUtil.isEmpty((String) user.get("trade_pwd"))) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "交易密码已设置,不能重复设置");
					return ret;
				}

				dbService.update(SqlUtil.buildUpdateSql("cl_user",
						new Object[][] { { "id", user.get("id") },
								{ "trade_pwd", pwd },
								{ "tradepwd_modify_time", new Date() } }));

				Map ret = new LinkedHashMap();
				ret.put("success", true);
				ret.put("msg", "交易密码已设置");
				return ret;
			}
		};
	}

	@RequestMapping("validateUser")
	public void validateUser(final HttpServletRequest request,
			HttpServletResponse response, final String idNo,
			final String realName, final String vcode) {
		new AppLoginedActionWraper(response, request) {
			@Override
			public Object doAction(Map userData, String userId) {

				if (StringUtil.isEmpty(idNo) || StringUtil.isEmpty(realName)
						|| StringUtil.isEmpty(vcode)) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "参数不能为空");
					return ret;
				}

				Map detail = mybatisService
						.queryRec("usr.validateUser", userId);

				if (!idNo.equalsIgnoreCase((String) detail.get("id_no"))
						|| !realName.equals(detail.get("real_name"))) {
					Map ret = new LinkedHashMap();
					ret.put("success", true);
					ret.put("msg", "身份证或姓名验证不通过");

					Map data = new LinkedHashMap();
					data.put("pass", false);
					ret.put("data", data);
					return ret;
				}

				ClSmsService clSmsService = (ClSmsService) BeanUtil
						.getBean("clSmsService");
				String loginName = (String) userData.get("login_name");
				String msg;
				int result = clSmsService.verifySms(loginName,
						SmsModel.SMS_TYPE_FINDPAY, vcode);
				if (result == 1) {
					msg = null;
				} else if (result == -1) {
					msg = "验证码已过期";
				} else {
					msg = "手机号码或验证码错误";
				}

				if (msg != null) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", msg);
					return ret;
				}

				Map ret = new LinkedHashMap();
				ret.put("success", true);
				ret.put("msg", "验证通过");

				Map data = new LinkedHashMap();
				data.put("pass", true);
				ret.put("data", data);
				return ret;
			}
		};
	}

	@RequestMapping("resetTradePwd")
	public void resetTradePwd(final HttpServletRequest request,
			HttpServletResponse response, final String newPwd) {
		new AppLoginedActionWraper(response, request) {
			@Override
			public Object doAction(Map userData, String userId) {

				if (StringUtil.isBlank(newPwd) || StringUtil.isBlank(userId)) {
					Map ret = new LinkedHashMap();
					ret.put("success", false);
					ret.put("msg", "参数不能为空");
					return ret;
				}

				dbService.update(SqlUtil.buildUpdateSql("cl_user",
						new Object[][] { { "id", userId },
								{ "trade_pwd", newPwd } }));

				Map ret = new LinkedHashMap();
				ret.put("success", true);
				ret.put("msg", "交易密码已重置");
				return ret;
			}
		};
	}

	@RequestMapping("logout")
	public void logout(final HttpServletRequest request,
			HttpServletResponse response) {
		new AppAbsActionWrapper(response) {

			@Override
			public Object doAction() {
				String token = request.getHeader("token");
				if (appDbSession.remove(token)) {
					Map ret = new LinkedHashMap();
					ret.put("success", true);
					ret.put("msg", "已注销");
					return ret;
				} else {
					Map ret = new LinkedHashMap();
					ret.put("success", true);
					ret.put("msg", "token不存在，无需注销");
					return ret;
				}
			}
		};
	}

	@RequestMapping("info")
	public void accountInfo(final HttpServletRequest request,
			HttpServletResponse response) {
		new AppLoginedActionWraper(response, request) {
			@Override
			public Object doAction(Map userData, String userId) {

				Map rec = mybatisService.queryRec("usr.info", userId);

				boolean dj = "20".equals(rec.get("state"));

				Map ret = new LinkedHashMap();
				ret.put("success", true);
				ret.put("msg", "账户信息获取成功");
				
				Map data = new LinkedHashMap();
				data.put("creditTotal", rec.get("total"));
				data.put("creditUnused", dj ? 0 : rec.get("unuse"));
				data.put("creditUsed", rec.get("used"));
				data.put("invitationCode", rec.get("invitation_code"));
				data.put("phone", rec.get("login_name"));
				data.put("idState", rec.get("id_state"));
				data.put("bankCardState", rec.get("bank_card_state"));

				ret.put("data", data);
				System.out.println("data："+data);
				return ret;
			}
		};
	}

	@RequestMapping("getTradeState")
	public void getTradeState(final HttpServletRequest request,
			HttpServletResponse response) {
		new AppLoginedActionWraper(response, request) {

			private boolean containsEmpty(Map rec, String... keys) {
				for (String key : keys) {
					String value = (String) rec.get(key);
					if (StringUtil.isEmpty(value)) {
						return true;
					}
				}
				return false;
			}

			@Override
			public Object doAction(Map userData, String userId) {

				Map user = mybatisService.queryRec("usr.getTradeState", userId);
				boolean infoEmpty = containsEmpty(user, "real_name", "id_no");
				boolean tradeEmpty = StringUtil.isEmpty((String) user
						.get("trade_pwd"));
				Map ret = new LinkedHashMap();
				ret.put("msg", "交易密码是否可设置状态获取成功");

				Map data = new LinkedHashMap();
				data.put("setable", tradeEmpty);
				data.put("forgetable", !infoEmpty && !tradeEmpty);
				data.put("changeable", !tradeEmpty);
				ret.put("data", data);
				return ret;
			}
		};
	}

	@RequestMapping("validateTradePwd")
	public void validateTradePwd(final HttpServletRequest request,
			HttpServletResponse response, final String tradePwd) {
		new AppLoginedActionWraper(response, request) {
			@Override
			public Object doAction(Map userData, String userId) {
				Map ret = new LinkedHashMap();
				ret.put("success", true);
				ret.put("msg", "操作成功");

				Map data = new LinkedHashMap();
				data.put(
						"pass",
						mybatisService.queryRec(
								"usr.validateTradePwd",
								MapUtil.array2Map(new Object[][] {
										{ "userId", userId },
										{ "tradePwd", tradePwd } })) != null);
				ret.put("data", data);
				return ret;
			}
		};
	}

	@RequestMapping("inviteList")
	public void inviteList(final HttpServletRequest request,
			HttpServletResponse response, final String invitId,
			final int pageNo, final int pageSize) {

		new AppLoginedActionWraper(response, request) {
			@Override
			public Object doAction(Map userData, String userId) {
				Map ret = new LinkedHashMap();
				ret.put("success", true);
				ret.put("msg", "下级代理商获取成功");

				String _id = StringUtil.isEmpty(invitId) ? userId : invitId;

				Map data = new LinkedHashMap();

				data.put(
						"list",
						mybatisService.querySql(
								"usr.inviteList",
								MapUtil.array2Map(new Object[][] {
										{ "userId", _id },
										{ "start", (pageNo - 1) * pageSize },
										{ "length", pageSize } })));

				long total = Long.valueOf(mybatisService
						.queryRec("usr.inviteListCnt", _id).get("cnt")
						.toString());

				ret.put("data", data);
				Map page = new LinkedHashMap();
				page.put("current", pageNo);
				page.put("pageSize", pageSize);
				page.put("total", total);
				page.put("pages", total % pageSize == 0 ? total / pageSize
						: total / pageSize + 1);
				ret.put("page", page);
				return ret;
			}
		};
	}

}
