package com.xindaibao.cashloan.core.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.xindaibao.cashloan.core.common.context.Global;
import org.apache.log4j.Logger;

import tool.util.NumberUtil;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * ftp文件的上传与下载
 * 
 * @author
 * @version 1.0
 * @date 2016年6月13日 下午4:57:12 



 */

public class SftpUtil {

	private static final Logger logger = Logger.getLogger(SftpUtil.class);

	/**
	 * sftp服务器地址
	 */
	protected static String host;

	/**
	 * sftp服务器端口号
	 */
	protected static int port;

	/**
	 * sftp服务器用户名
	 */
	protected static String username;

	/**
	 * sftp服务器密码
	 */
	protected static String password;

	/**
	 * 文件路径
	 */
	protected static String path;

	/**
	 * 密钥文件路径
	 */
	protected static String privateKey;

	/**
	 * 密钥口令
	 */
	protected static String passphrase;

	/**
	 * 初始化参数
	 */
	private static void init() {
		String sftp = Global.getValue("lianlian_sftp");

		try {
			logger.info("连连sftp配置:" + sftp);

			host = JsonUtil.get(sftp, "host");
			port = NumberUtil.getInt(JsonUtil.get(sftp, "port"));
			username = JsonUtil.get(sftp, "user");
			password = JsonUtil.get(sftp, "passwd");
			path = JsonUtil.get(sftp, "path");
		} catch (Exception e) {
			logger.error("初始化sftp连接出错:" + e.getMessage(), e);
		}
	}

	/**
	 * 获取sftp连接
	 * 
	 * @return
	 */
	public static ChannelSftp connect() {
		init();
		JSch jsch = new JSch();
		Channel channel = null;
		try {
			Session session = jsch.getSession(username, host, port);
			if (password != null && !"".equals(password)) {
				session.setPassword(password);
			}
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			session.setConfig(sshConfig);
			session.setServerAliveInterval(92000);
			session.connect();
			logger.debug("创建sftp连接成功");
			// 参数sftp指明要打开的连接是sftp连接
			channel = session.openChannel("sftp");
			channel.connect();
		} catch (JSchException e) {
			logger.error(e);
		}
		return (ChannelSftp) channel;
	}

	
	public static void main(String[] args) {
		connect();
	}
	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 */
	public static void upload(String directory, String uploadFile,
			ChannelSftp sftp) {
		FileInputStream fis = null;
		try {
			sftp.cd(directory);
			File file = new File(uploadFile);
			fis = new FileInputStream(file);
			sftp.put(fis, file.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveDirectory
	 *            存在本地的路径
	 * @param sftp
	 */
	public static boolean download(String directory, String downloadFile,
			String saveDirectory, ChannelSftp sftp) {
		logger.info("下载sftp对账文件：" + downloadFile);
		// 判断目录，不存在时 是创建还是 return，商户自行决定,默认创建
		File file = new File(saveDirectory);
		if (!file.exists()) {
			file.mkdirs();
		}

		try {
			sftp.cd(directory);
			sftp.get(downloadFile, saveDirectory);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 获取文件流
	 * 
	 * @param directory
	 *            sftp目录
	 * @param fileName
	 *            文件名
	 * @param sftp
	 */
	public static InputStream getFileStream(String fileName, ChannelSftp sftp) {
		InputStream in = null;
		logger.info("读取sftp对账文件输入流：" + fileName);
		try {
			sftp.cd(path);
			in = sftp.get(fileName);
		} catch (Exception e) {
			logger.error("读取sftp输入流异常：", e);
		}
		return in;
	}

}
