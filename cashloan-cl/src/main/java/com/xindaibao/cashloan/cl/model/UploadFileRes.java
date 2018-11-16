package com.xindaibao.cashloan.cl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 上传文件返回体
 * hj
 * @version 1.0
 * @date 2017年1月3日



 */
public class UploadFileRes {
	/**
	 * 处理人
	 */
	private String sysUserName;
	/**
	 * 处理时间
	 */
	private Date createTime;
	/**
	 * 文件路径
	 */
	private String resPath;
	/**
	 * 文件原名称
	 */
	private String oldName;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件格式
	 */
	private String fileFormat;
	/**
	 * 文件大小
	 */
	private BigDecimal fileSize;
	/**
	 * 错误信息
	 */
	private String errorMsg;
	
	public String getResPath() {
		return resPath;
	}
	public void setResPath(String resPath) {
		this.resPath = resPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public BigDecimal getFileSize() {
		return fileSize;
	}
	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}
	public String getSysUserName() {
		return sysUserName;
	}
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	
	
}
