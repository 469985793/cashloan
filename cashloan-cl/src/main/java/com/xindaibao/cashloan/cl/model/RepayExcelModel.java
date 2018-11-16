package com.xindaibao.cashloan.cl.model;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 * 确认还款Excel文档工具类
 * */
public class RepayExcelModel {

	private static final Logger logger = Logger.getLogger(RepayExcelModel.class);
	
    /**
     * 创建excel文档，
     * [@param](http://my.oschina.net/u/2303379) list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<List<String>> list,String title) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(title);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<100;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }
        // 创建第一行
        sheet.createRow((short) 0);
        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row = sheet.createRow((short) i);
            List<String> keys=list.get(i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.size();j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(keys.get(j));
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }
    /**
     * 数据集合保存到文件中
     * @param list
     * @param title
     * @param fileName
     * @return
     * @throws IOException 
     * @throws Exception
     */
    public String saveExcelByList(List<List<String>> list, String title,String fileName,HttpServletRequest request) {
    	Workbook wb=createWorkBook(list,title);
    	// 保存文件
    	String path="/data/xls/batchRepay";
 		String filePath = request.getSession().getServletContext().getRealPath("/")+path;
        File file = new File(filePath);
        //文件不存在，则新增目录，
        if (!file.exists()) {
        	file.mkdirs();
        }
        logger.info("=11=="+fileName);
         fileName = fileName.substring(0,fileName.lastIndexOf("."))+".xls";
         logger.info("=22=="+fileName);
         file = new File(filePath+File.separator+fileName);    
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            wb.write(outputStream);
        } catch (IOException e) {
        	logger.error(e);
		}  finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
            	logger.error(e);
            }
        }
        return path+"/"+fileName;
    }
    /**
     * 数据集合写入excel下载
     * @param list
     * @param title
     * @param sFileName
     * @param request
     * @param response
     * @throws Exception
     */
	public void exportExcel(List<List<String>> list, String title,String sFileName,
			HttpServletRequest request, HttpServletResponse response)  {
	        try {
	        	OutputStream out = response.getOutputStream();
	    		// 火狐浏览器导出excel乱码
	    		String agent = request.getHeader("User-Agent");
	    		// 判断是否火狐浏览器
	    		boolean isFirefox = (agent != null && agent.contains("Firefox"));
	    		String sFileName_;
	    		if (isFirefox) {
	    			sFileName_ = new String(sFileName.getBytes("UTF-8"), "ISO-8859-1");
	    		} else {
	    			sFileName_ = URLEncoder.encode(sFileName, "UTF8");
	    		}
	    		response.setHeader("Content-Disposition", "attachment; filename=".concat(sFileName_));
	    		response.setHeader("Content-Type", "application/vnd.ms-excel");
	    		response.setCharacterEncoding("utf-8");
	            response.setContentType("application/vnd.ms-excel");
	            createWorkBook(list,title).write(out);
	            out.flush();
	            out.close();
	        } catch (IOException e) {
	        	logger.error(e);
	        }
	}
	public void exportFile(String path,HttpServletRequest request, HttpServletResponse response)  {
		OutputStream out = null;
		InputStream fis = null;
		try {
			String fileName = path.substring(path.lastIndexOf("/"));
			String path_=request.getSession().getServletContext().getRealPath("/")+path;
			// TODO Auto-generated method stub
			out = response.getOutputStream();
			// 火狐浏览器导出excel乱码
			String agent = request.getHeader("User-Agent");
			// 判断是否火狐浏览器
			boolean isFirefox = (agent != null && agent.contains("Firefox"));
			if (isFirefox) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			} else {
				fileName = URLEncoder.encode(fileName, "UTF8");
			}
			// 以流的形式下载文件。
	        fis = new BufferedInputStream(new FileInputStream(path_));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        // 清空response
	        response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName));
			response.setHeader("Content-Type", "application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
	        response.setContentType("application/vnd.ms-excel");
	        out.write(buffer);
	        out.flush();
		} catch (IOException e) {
			logger.error(e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}

}
