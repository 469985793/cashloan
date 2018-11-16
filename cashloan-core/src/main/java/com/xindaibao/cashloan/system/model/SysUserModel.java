package com.xindaibao.cashloan.system.model;

import com.xindaibao.cashloan.core.common.exception.OperatorException;
import com.xindaibao.cashloan.core.common.util.code.MD5;
import com.xindaibao.cashloan.system.domain.SysUser;
import org.springframework.beans.BeanUtils;

import tool.util.StringUtil;

import com.xindaibao.cashloan.core.common.util.ValidateUtil;

/**
 * 
 * 用户model
 * 
 * @version 1.0
 * @author
 * @created 2014年9月23日 上午11:53:39
 */
public class SysUserModel extends SysUser {

	/** 序列化 */
	private static final long serialVersionUID = -6789313850889066219L;
	/** 原密码 **/
	private String oldPassword;

	/** 确认新修改密码 **/
	private String confirmPassword;
 
	/**
	 * 实体转换model
	 * 
	 * @param user
	 *            实体
	 * @return model
	 */
	public static SysUserModel instance(SysUser sysUser) {
		SysUserModel userModel = new SysUserModel();
		BeanUtils.copyProperties(sysUser, userModel);
		return userModel;
	}

	/**
	 * model转换实体
	 * 
	 * @return 实体
	 */
	public SysUser prototype() {
		SysUser user = new SysUser();
		BeanUtils.copyProperties(this, user);
		return user;
	}

	/**
	 * 登录 检查提交的数据格式
	 * 
	 * @return
	 */
	public int validLoginModel() {
		if (StringUtil.isBlank(getUserName())) {
			throw new OperatorException("用户名不能为空！", 1);
		}
		if (StringUtil.isBlank(getPassword())) {
			throw new OperatorException("密码不能为空！", 1);
		}
		return -1;
	}

	/**
	 * 验证用户登录密码
	 * 
	 * @param user
	 *            用户
	 * @return
	 */
	public String validModifyPwdModel(SysUser user) {
		if (getOldPassword() == null) {
			throw new OperatorException("请输入您的原密码！", 1);
		} else if (!MD5.encode(getOldPassword()).equals(user.getPassword())) {
			throw new OperatorException("原密码错误！", 1);
		} else if (getPassword().equals(getOldPassword())) {
			throw new OperatorException("新密码不能和原密码相同！", 1);
		} else if (!getPassword().equals(getConfirmPassword())) {
			throw new OperatorException("新密码和确认密码不相同！", 1);
		}
		return "";
	}

	/**
	 * 添加用户 检查提交的数据格式
	 * 
	 * @return
	 */
	public int validRegModel() {
		if (!ValidateUtil.isUser_name(getUserName())) {
			throw new OperatorException("用户名格式错误！", 1);
		}
		if (StringUtil.isBlank(getPassword())) {
			throw new OperatorException("密码不能为空！", 1);
		}
		if (StringUtil.isBlank(getConfirmPassword())) {
			throw new OperatorException("确认密码不能为空！");
		}
		if (!getPassword().equals(getConfirmPassword())) {
			throw new OperatorException("两次输入的密码不一致！");
		}
		return -1;
	}

	/**
	 * 用户选择部门公司校验
	 * 
	 * @return 结果
	 */
	public int validOfficeModel() {
		/*
		 * if (this.getOffice() == null || this.getOffice().getType() !=
		 * SystemConstant.OFFICE_TYPE_DEPARTMENT) { throw new
		 * OperatorException("用户所选择的部门错误！", 1); }
		 */
		return -1;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
