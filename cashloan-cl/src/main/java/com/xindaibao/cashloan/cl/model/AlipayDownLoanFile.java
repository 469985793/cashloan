package com.xindaibao.cashloan.cl.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.avalon.framework.parameters.ParameterException;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class AlipayDownLoanFile {
	
	private static final Logger logger = Logger.getLogger(AlipayDownLoanFile.class);
	
	
	public static List<AlipayModel> parseAlipayByUrl(String urlStr) throws IOException, ParameterException{
		/*//获取项目当前地址
		File f= new File("");
		String  savePath=f.getAbsolutePath()+"/src/main/webapp/alipay";//下载下来的文件存放地点
		String filePath=downLoadByUrl(urlStr,savePath);
		//解压
	    savePath=f.getAbsolutePath()+"/src/main/webapp/alipay/file";//存放解压之后的文件
		String file=unzip(filePath,savePath, false);
		logger.info("======="+file);
		//String file="D:/现金贷/cashloan/framework-manage/src/main/webapp/alipay/file/20881021698578250156_20170406_业务明细.csv";
		//解析
		List<AlipayModel> alipayList=CsvParser.parserByFile(file);*/
		return null;
	}
	
	
	
	/**
     * 从Url中下载文件保存，返回本地地址
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
	public static String downLoadByUrl(String urlStr, String savePath)  {
		InputStream inputStream = null;
		FileOutputStream fos = null;
		String fileName = "";
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置超时间为3秒
			conn.setConnectTimeout(3 * 100000);
			if (conn.getResponseCode() == 200) {
				String file = conn.getURL().getFile();
				String fileUrl = file.substring(file.lastIndexOf('?') + 1);
				Map<String, Object> fileMap = getUrlParams(fileUrl);
				fileName = (String) fileMap.get("downloadFileName");
			}
			// 得到输入流
			inputStream = conn.getInputStream();
			// 获取自己数组
			byte[] getData = readInputStream(inputStream);
			// 文件保存位置
			File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			File file = new File(saveDir + File.separator + fileName);
			logger.info("=保存支付宝账单文件路径==" + saveDir + File.separator + fileName);
			fos = new FileOutputStream(file);
			fos.write(getData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null){
					fos.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return savePath + File.separator + fileName;
	}

    /** 
     * 将url参数转换成map 
     * @param param aa=11&bb=22&cc=33 
     * @return 
     */  
    public static Map<String, Object> getUrlParams(String param) {  
        Map<String, Object> map = new HashMap<String, Object>(0);  
        if (StringUtils.isEmpty(param)) {  
            return map;  
        }  
        String[] params = param.split("&");  
        for (int i = 0; i < params.length; i++) {  
            String[] p = params[i].split("=");  
            if (p.length == 2) {  
                map.put(p[0], p[1]);  
            }  
        }  
        return map;  
    }  
      
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    }  

    /** 
     * 解压缩zip包 
     * @param zipFilePath zip文件的全路径 
     * @param unzipFilePath 解压后的文件保存的路径 
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含 
     * @throws ParameterException 
     * @throws IOException 
     */  
    @SuppressWarnings("unchecked")  
    public static String unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName) throws ParameterException  {  
    	ZipFile zip = null;
    	BufferedOutputStream bos = null;
    	String files = "";
    	try {
			// logger.info("==需要解压的文件="+zipFilePath);
			if (StringUtils.isEmpty(zipFilePath) || StringUtils.isEmpty(unzipFilePath)) {
				throw new ParameterException("需解压地址不能为空");
			}
			File zipFile = new File(zipFilePath);
			// 如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径
			if (includeZipFileName) {
				String fileName = zipFile.getName();
				if (!StringUtils.isEmpty(fileName)) {
					fileName = fileName.substring(0, fileName.lastIndexOf("."));
				}
				unzipFilePath = unzipFilePath + File.separator + fileName;
			}
			// 创建解压缩文件保存的路径
			File unzipFileDir = new File(unzipFilePath);
			if (!unzipFileDir.exists() || !unzipFileDir.isDirectory()) {
				unzipFileDir.mkdirs();
			}

			// 开始解压
			ZipEntry entry;
			String entryFilePath, entryDirPath;
			File entryFile, entryDir;
			int index, count, bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			BufferedInputStream bis;
			Charset gbk = Charset.forName("GBK");
			zip = new ZipFile(zipFile, gbk);
			Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.entries();
			// 循环对压缩包里的每一个文件进行解压
			while (entries.hasMoreElements()) {
				entry = entries.nextElement();
				// 构建压缩包中一个文件解压后保存的文件全路径
				entryFilePath = unzipFilePath + File.separator + entry.getName();
				// 构建解压后保存的文件夹路径
				index = entryFilePath.lastIndexOf(File.separator);
				if (index != -1) {
					entryDirPath = entryFilePath.substring(0, index);
				} else {
					entryDirPath = "";
				}
				entryDir = new File(entryDirPath);
				// 如果文件夹路径不存在，则创建文件夹
				if (!entryDir.exists() || !entryDir.isDirectory()) {
					entryDir.mkdirs();
				}

				// 创建解压文件
				entryFile = new File(entryFilePath);
				if (entryFile.exists()) {
					// 检测文件是否允许删除，如果不允许删除，将会抛出SecurityException
					// SecurityManager securityManager = new SecurityManager();
					// securityManager.checkDelete(entryFilePath);
					// 删除已存在的目标文件
					entryFile.delete();
				}

				// 写入文件
				bos = new BufferedOutputStream(new FileOutputStream(entryFile));
				bis = new BufferedInputStream(zip.getInputStream(entry));
				while ((count = bis.read(buffer, 0, bufferSize)) != -1) {
					bos.write(buffer, 0, count);
				}
				bos.flush();

				// 获取需要的文件
				if (entry.getName().contains("业务明细.")) {
					files = entryFilePath;
				}
			}
    	} catch (IOException e) {
    		logger.error(e);
		} finally {
    		try {
				zip.close();
				bos.close();
			} catch (IOException e) {
				logger.error(e);
			}
    	}
    	return files;
    } 
    
    
	public static void main(String[] args) {
        try{
        	String filePath="D:/现金贷/cashloan/framework-manage/src/main/webapp/alipay/20881021698578250156_20170406.csv.zip";
        	//解压
    	     String  savePath="D:/现金贷/cashloan/framework-manage/src/main/webapp/alipay/file";//存放解压之后的文件
    		 unzip(filePath,savePath, false);
        }catch (Exception e) {
        	logger.error(e.getMessage(),e);
        }
    }
}
