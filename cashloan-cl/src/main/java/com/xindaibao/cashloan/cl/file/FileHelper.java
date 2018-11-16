package com.xindaibao.cashloan.cl.file;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xindaibao.cashloan.cl.model.FileTypeUtil;
import com.xindaibao.cashloan.cl.model.UploadFileRes;
import com.xindaibao.cashloan.core.common.exception.RDRuntimeException;
import com.xindaibao.cashloan.core.common.util.StringUtil;

import tool.util.DateUtil;
/**
 * 
 *	@Description 文件上传方法类
 *  @author
 *  @CreatTime 2017年6月22日 上午9:55:45
 *  @since version 1.0.0
 */
public class FileHelper {
	
	public static final Logger logger = LoggerFactory.getLogger(FileHelper.class);
	
	/**
	 * 
	 * @description 图片上传
	 * @param file
	 * @param prefix 前缀
	 * @param args 自定义参数
	 * @return
	 * @author
	 * @return UploadFileRes
	 * @since  1.0.0
	 */
	public static UploadFileRes uploadImg(MultipartFile file,String prefix,String args) {
		
		UploadFileRes model = new UploadFileRes();
		model.setCreateTime(DateUtil.getNow());
		// 文件名称-特定前缀
		model.setOldName(file.getOriginalFilename());
	//	String picName = prefix+file.getOriginalFilename();   
		
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File newFile = (File) fi.getStoreLocation();
		logger.info("图片----------"+newFile);
		// 文件格式
		String fileType = FileTypeUtil.getFileType(newFile);
		String picName=prefix+DateUtil.dateStr(DateUtil.getNow(),DateUtil.DATEFORMAT_STR_016)+"."+fileType;  
		
		if (StringUtil.isBlank(fileType) || !FileTypeUtil.isImage(newFile, fileType)) {
			model.setErrorMsg("图片格式错误或内容不规范");
			return model;
		}
		// 校验图片大小
		Long picSize = file.getSize();
		if (picSize.compareTo(20971520L) > 0) {
			model.setErrorMsg("文件超出20M大小限制");
			return model;
		}
		// 保存文件
		String s=File.separator;
		String filePath = s+"data"+s+"image"+s + args + s +
				DateUtil.dateStr(DateUtil.getNow(), DateUtil.DATEFORMAT_STR_013) + s + picName;
		if(s.equals("\\")){
			filePath="D:"+filePath;
		}
		File files = new File(filePath);
		if (!files.exists()) {
			try {
				files.mkdirs();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				model.setErrorMsg("文件目录不存在");
				return model;
			}
		}
		try {
			file.transferTo(files);
		} catch (IllegalStateException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new RDRuntimeException(e.getMessage(),e);
		}
		// 转存文件
		model.setResPath(filePath);
		model.setFileName(picName);
		model.setFileFormat(fileType);
		model.setFileSize(new BigDecimal(picSize));
		return model;
	}
	

}
