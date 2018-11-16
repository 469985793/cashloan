package com.xindaibao.cashloan.core.common.util.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.xindaibao.cashloan.core.common.exception.BaseException;

/**
 * 读取Excel
 * 
 * @author
 */
public class ReadExcelUtils {
	private Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);
	private Workbook wb;
	private Sheet sheet;
	private Row row;

	public ReadExcelUtils(String filepath) {
		if(filepath==null){
			return;
		}
		String ext = filepath.substring(filepath.lastIndexOf("."));
		InputStream is = null;
		try {
			is = new FileInputStream(filepath);
			if(".xls".equals(ext)){
				wb = new HSSFWorkbook(is);
			}else if(".xlsx".equals(ext)){
				wb = new XSSFWorkbook(is);
			}else{
				wb=null;
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				logger.error("异常",e);
			}
		}
	}
	public ReadExcelUtils(MultipartFile file) throws IOException  {
		InputStream is = file.getInputStream();
		try{
			wb = new XSSFWorkbook(is);
	     }catch (Exception e) {
	        wb = new HSSFWorkbook(is);
	    }
	}
	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 * @author
	 * @throws Exception 
	 */
	public String[] readExcelTitle() {
		if(wb==null){
			throw new BaseException("Workbook对象为空！");
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = row.getCell(i).getCellFormula();
		}
		return title;
	}

	/**
	 * 读取Excel数据内容
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 * @author
	 */
	public List<List<String>>  readExcelContent() {
		if(wb==null){
			throw new BaseException("Workbook对象为空！");
		}
		 List<List<String>> content=new ArrayList<List<String>>();
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				int colNum = row.getPhysicalNumberOfCells();
				int j = 0;
				List<String> cellValue = new ArrayList<String>();
				while (j < colNum) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						Object obj = getCellFormatValue(cell);
						String value=obj!=null?obj.toString().replace("/t", "").trim():"";
						cellValue.add(value);
					}
					j++;
				}
				  content.add(cellValue);
			}else{
				continue;
			}
		}
		return content;
	}

	/**
	 * 
	 * 根据Cell类型设置数据
	 * 
	 * @param cell
	 * @return
	 * @author
	 */
	private Object getCellFormatValue(Cell cell) {
		Object cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
			case Cell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					cellvalue =tool.util.DateUtil.dateStr(date,"yyyy/MM/dd HH:mm:ss");
				} else {// 如果是纯数字

					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:// 默认的Cell值
				cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
}