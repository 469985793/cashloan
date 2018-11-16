package com.xindaibao.cashloan.core.common.util.excel;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.Region;

import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.ReflectUtil;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import tool.util.BigDecimalUtil;
import tool.util.StringUtil;

@SuppressWarnings("deprecation")
public class ExcelUtil {
	
	public static final String UID = "serialVersionUID";
	private static final Logger LOGGER = Logger.getLogger(ExcelUtil.class);

	/**
	 * JavaBean转Map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!StringUtils.equals(name, "class")) {
					params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return params;
	}

	/**
	 * 创建普通表头
	 * 
	 * @param list 表头名称列表
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(List<String> list) {
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for (String title : list) {
			TableColumn tc = new TableColumn();
			tc.setDisplay(title);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}

	/**
	 * 创建普通表头
	 * 
	 * @param titls 表头名称数组
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(String[] titls) {
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for (String title : titls) {
			TableColumn tc = new TableColumn();
			tc.setDisplay(title);
			tc.setGrouped(true);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}

	/**
	 * 创建普通表头
	 * 
	 * @param titls 表头名称数组
	 * @param spanCount 需要行合并的列数。 由第一列数据开始到指定列依次从左到右进行合并操作。 如该值大于表头名称数组，则为全列合并
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(String[] titls, int spanCount) {
		if (spanCount > titls.length)
			spanCount = titls.length;
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for (int i = 0; i < titls.length; i++) {
			TableColumn tc = new TableColumn();
			tc.setDisplay(titls[i]);
			if (i < spanCount)
				tc.setGrouped(true);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}

	/**
	 * 创建合并表头
	 * 
	 * @param parents 父表头数组
	 * @param children 子表头数组
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(String[] parents, String[][] children) {
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		TableColumn parentColumn = null;
		TableColumn sonColumn = null;
		for (int i = 0; i < parents.length; i++) {
			parentColumn = new TableColumn();
			parentColumn.setDisplay(parents[i]);
			if (children != null && children[i] != null) {
				for (int j = 0; j < children[i].length; j++) {
					sonColumn = new TableColumn();
					sonColumn.setDisplay(children[i][j]);
					parentColumn.addChild(sonColumn);
				}
			}
			headMeta.addColumn(parentColumn);
		}
		return headMeta;
	}

	/**
	 * 拼装数据
	 * 
	 * @param list 数据集
	 * @param headMeta 表头
	 * @param fields 对象或Map属性数组（注意：顺序要与表头标题顺序对应，如数据集为List<Object[]>，则该参数可以为null）
	 * @return TableData
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableData createTableData(List list, TableHeaderMetaData headMeta, String[] fields) {

		TableData td = new TableData(headMeta);
		TableDataRow row = null;
		if (list != null && list.size() > 0) {
			if (list.get(0).getClass().isArray()) {// 数组类型
				for (Object obj : list) {
					row = new TableDataRow(td);
					for (Object o : (Object[]) obj) {
						row.addCell(o);
					}
					td.addRow(row);
				}
			} else {// JavaBean或Map类型
				for (Object obj : list) {
					row = new TableDataRow(td);
					Map<String, Object> map = (obj instanceof Map) ? (Map<String, Object>) obj : beanToMap(obj);
					for (String key : fields) {
						row.addCell(map.get(key));
					}
					td.addRow(row);
				}
			}
		}
		return td;
	}

	/**
	 * 创建压缩输出流
	 * 
	 * @param response
	 * @param zipName 压缩包名次
	 * @return
	 */
	public static ZipOutputStream createZipStream(HttpServletResponse response, String zipName) {
		response.reset();
		response.setContentType("application/vnd.ms-excel"); // 不同类型的文件对应不同的MIME类型
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=".concat(String.valueOf(URLEncoder.encode(zipName + ".zip", "UTF-8"))));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e);
		}
		OutputStream os = null;
		try {
			os = response.getOutputStream();
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return new ZipOutputStream(os);
	}

	public static void copySheetStyle(HSSFWorkbook destwb, HSSFSheet dest, HSSFWorkbook srcwb, HSSFSheet src) {
		if (src == null || dest == null)
			return;

		dest.setAlternativeExpression(src.getAlternateExpression());
		dest.setAlternativeFormula(src.getAlternateFormula());
		dest.setAutobreaks(src.getAutobreaks());
		dest.setDialog(src.getDialog());
		if (src.getColumnBreaks() != null) {
			for (int col : src.getColumnBreaks()) {
				dest.setColumnBreak((short) col);
			}
		}
		dest.setDefaultColumnWidth(src.getDefaultColumnWidth());
		dest.setDefaultRowHeight(src.getDefaultRowHeight());
		dest.setDefaultRowHeightInPoints(src.getDefaultRowHeightInPoints());
		dest.setDisplayGuts(src.getDisplayGuts());
		dest.setFitToPage(src.getFitToPage());
		dest.setHorizontallyCenter(src.getHorizontallyCenter());
		dest.setDisplayFormulas(src.isDisplayFormulas());
		dest.setDisplayGridlines(src.isDisplayGridlines());
		dest.setDisplayRowColHeadings(src.isDisplayRowColHeadings());
		dest.setGridsPrinted(src.isGridsPrinted());
		dest.setPrintGridlines(src.isPrintGridlines());

		for (int i = 0; i < src.getNumMergedRegions(); i++) {
			Region r = src.getMergedRegionAt(i);
			dest.addMergedRegion(r);
		}

		if (src.getRowBreaks() != null) {
			for (int row : src.getRowBreaks()) {
				dest.setRowBreak(row);
			}
		}
		dest.setRowSumsBelow(src.getRowSumsBelow());
		dest.setRowSumsRight(src.getRowSumsRight());

		short maxcol = 0;
		for (int i = 0; i <= src.getLastRowNum(); i++) {
			HSSFRow row = src.getRow(i);
			if (row != null) {
				if (maxcol < row.getLastCellNum())
					maxcol = row.getLastCellNum();
			}
		}
		for (short col = 0; col <= maxcol; col++) {
			if (src.getColumnWidth(col) != src.getDefaultColumnWidth())
				dest.setColumnWidth(col, src.getColumnWidth(col));
			dest.setColumnHidden(col, src.isColumnHidden(col));
		}
	}

	public static String dumpCellStyle(HSSFCellStyle style) {
		StringBuilder sb = new StringBuilder();
		sb.append(style.getHidden()).append(",");
		sb.append(style.getLocked()).append(",");
		sb.append(style.getWrapText()).append(",");
		sb.append(style.getAlignment()).append(",");
		sb.append(style.getBorderBottom()).append(",");
		sb.append(style.getBorderLeft()).append(",");
		sb.append(style.getBorderRight()).append(",");
		sb.append(style.getBorderTop()).append(",");
		sb.append(style.getBottomBorderColor()).append(",");
		sb.append(style.getDataFormat()).append(",");
		sb.append(style.getFillBackgroundColor()).append(",");
		sb.append(style.getFillForegroundColor()).append(",");
		sb.append(style.getFillPattern()).append(",");
		sb.append(style.getIndention()).append(",");
		sb.append(style.getLeftBorderColor()).append(",");
		sb.append(style.getRightBorderColor()).append(",");
		sb.append(style.getRotation()).append(",");
		sb.append(style.getTopBorderColor()).append(",");
		sb.append(style.getVerticalAlignment());

		return sb.toString();
	}

	public static String dumpFont(HSSFFont font) {
		StringBuilder sb = new StringBuilder();
		sb.append(font.getItalic()).append(",").append(font.getStrikeout()).append(",").append(font.getBoldweight())
				.append(",").append(font.getCharSet()).append(",").append(font.getColor()).append(",")
				.append(font.getFontHeight()).append(",").append(font.getFontName()).append(",")
				.append(font.getTypeOffset()).append(",").append(font.getUnderline());
		return sb.toString();
	}

	public static void copyCellStyle(HSSFWorkbook destwb, HSSFCell dest, HSSFWorkbook srcwb, HSSFCell src) {
		if (src == null || dest == null)
			return;

		HSSFCellStyle nstyle = findStyle(src.getCellStyle(), srcwb, destwb);
		if (nstyle == null) {
			nstyle = destwb.createCellStyle();
			copyCellStyle(destwb, nstyle, srcwb, src.getCellStyle());
		}
		dest.setCellStyle(nstyle);
	}

	private static boolean isSameColor(short a, short b, HSSFPalette apalette, HSSFPalette bpalette) {
		if (a == b)
			return true;
		HSSFColor acolor = apalette.getColor(a);
		HSSFColor bcolor = bpalette.getColor(b);
		if (acolor == null)
			return true;
		if (bcolor == null)
			return false;
		return acolor.getHexString().equals(bcolor.getHexString());
	}

	private static short findColor(short index, HSSFWorkbook srcwb, HSSFWorkbook destwb) {
		Integer id = new Integer(index);
		if (HSSFColor.getIndexHash().containsKey(id))
			return index;
		if (index == HSSFColor.AUTOMATIC.index)
			return index;
		HSSFColor color = srcwb.getCustomPalette().getColor(index);
		if (color == null) {
			return index;
		}

		HSSFColor ncolor = destwb.getCustomPalette().findColor((byte) color.getTriplet()[0],
				(byte) color.getTriplet()[1], (byte) color.getTriplet()[2]);
		if (ncolor != null)
			return ncolor.getIndex();
		destwb.getCustomPalette().setColorAtIndex(index, (byte) color.getTriplet()[0], (byte) color.getTriplet()[1],
				(byte) color.getTriplet()[2]);
		return index;
	}

	public static HSSFCellStyle findStyle(HSSFCellStyle style, HSSFWorkbook srcwb, HSSFWorkbook destwb) {
		HSSFPalette srcpalette = srcwb.getCustomPalette();
		HSSFPalette destpalette = destwb.getCustomPalette();

		for (short i = 0; i < destwb.getNumCellStyles(); i++) {
			HSSFCellStyle old = destwb.getCellStyleAt(i);
			if (old == null)
				continue;

			if (style.getAlignment() == old.getAlignment()
					&& style.getBorderBottom() == old.getBorderBottom()
					&& style.getBorderLeft() == old.getBorderLeft()
					&& style.getBorderRight() == old.getBorderRight()
					&& style.getBorderTop() == old.getBorderTop()
					&& isSameColor(style.getBottomBorderColor(), old.getBottomBorderColor(), srcpalette, destpalette)
					&& style.getDataFormat() == old.getDataFormat()
					&& isSameColor(style.getFillBackgroundColor(), old.getFillBackgroundColor(), srcpalette,
							destpalette)
					&& isSameColor(style.getFillForegroundColor(), old.getFillForegroundColor(), srcpalette,
							destpalette) && style.getFillPattern() == old.getFillPattern()
					&& style.getHidden() == old.getHidden() && style.getIndention() == old.getIndention()
					&& isSameColor(style.getLeftBorderColor(), old.getLeftBorderColor(), srcpalette, destpalette)
					&& style.getLocked() == old.getLocked()
					&& isSameColor(style.getRightBorderColor(), old.getRightBorderColor(), srcpalette, destpalette)
					&& style.getRotation() == old.getRotation()
					&& isSameColor(style.getTopBorderColor(), old.getTopBorderColor(), srcpalette, destpalette)
					&& style.getVerticalAlignment() == old.getVerticalAlignment()
					&& style.getWrapText() == old.getWrapText()) {

				HSSFFont oldfont = destwb.getFontAt(old.getFontIndex());
				HSSFFont font = srcwb.getFontAt(style.getFontIndex());
				if (oldfont.getBoldweight() == font.getBoldweight() && oldfont.getItalic() == font.getItalic()
						&& oldfont.getStrikeout() == font.getStrikeout() && oldfont.getCharSet() == font.getCharSet()
						&& isSameColor(oldfont.getColor(), font.getColor(), srcpalette, destpalette)
						&& oldfont.getFontHeight() == font.getFontHeight()
						&& oldfont.getFontName().equals(font.getFontName())
						&& oldfont.getTypeOffset() == font.getTypeOffset()
						&& oldfont.getUnderline() == font.getUnderline()) {
					return old;
				}
			}
		}
		return null;
	}

	public static void copyCellStyle(HSSFWorkbook destwb, HSSFCellStyle dest, HSSFWorkbook srcwb, HSSFCellStyle src) {
		if (src == null || dest == null)
			return;
		dest.setAlignment(src.getAlignment());
		dest.setBorderBottom(src.getBorderBottom());
		dest.setBorderLeft(src.getBorderLeft());
		dest.setBorderRight(src.getBorderRight());
		dest.setBorderTop(src.getBorderTop());
		dest.setBottomBorderColor(findColor(src.getBottomBorderColor(), srcwb, destwb));
		dest.setDataFormat(destwb.createDataFormat().getFormat(srcwb.createDataFormat().getFormat(src.getDataFormat())));
		dest.setFillPattern(src.getFillPattern());
		dest.setFillForegroundColor(findColor(src.getFillForegroundColor(), srcwb, destwb));
		dest.setFillBackgroundColor(findColor(src.getFillBackgroundColor(), srcwb, destwb));
		dest.setHidden(src.getHidden());
		dest.setIndention(src.getIndention());
		dest.setLeftBorderColor(findColor(src.getLeftBorderColor(), srcwb, destwb));
		dest.setLocked(src.getLocked());
		dest.setRightBorderColor(findColor(src.getRightBorderColor(), srcwb, destwb));
		dest.setRotation(src.getRotation());
		dest.setTopBorderColor(findColor(src.getTopBorderColor(), srcwb, destwb));
		dest.setVerticalAlignment(src.getVerticalAlignment());
		dest.setWrapText(src.getWrapText());

		HSSFFont f = srcwb.getFontAt(src.getFontIndex());
		HSSFFont nf = findFont(f, srcwb, destwb);
		if (nf == null) {
			nf = destwb.createFont();
			nf.setBoldweight(f.getBoldweight());
			nf.setCharSet(f.getCharSet());
			nf.setColor(findColor(f.getColor(), srcwb, destwb));
			nf.setFontHeight(f.getFontHeight());
			nf.setFontHeightInPoints(f.getFontHeightInPoints());
			nf.setFontName(f.getFontName());
			nf.setItalic(f.getItalic());
			nf.setStrikeout(f.getStrikeout());
			nf.setTypeOffset(f.getTypeOffset());
			nf.setUnderline(f.getUnderline());
		}
		dest.setFont(nf);
	}

	private static HSSFFont findFont(HSSFFont font, HSSFWorkbook src, HSSFWorkbook dest) {
		for (short i = 0; i < dest.getNumberOfFonts(); i++) {
			HSSFFont oldfont = dest.getFontAt(i);
			if (font.getBoldweight() == oldfont.getBoldweight() && font.getItalic() == oldfont.getItalic()
					&& font.getStrikeout() == oldfont.getStrikeout() && font.getCharSet() == oldfont.getCharSet()
					&& font.getColor() == oldfont.getColor() && font.getFontHeight() == oldfont.getFontHeight()
					&& font.getFontName().equals(oldfont.getFontName())
					&& font.getTypeOffset() == oldfont.getTypeOffset() && font.getUnderline() == oldfont.getUnderline()) {
				return oldfont;
			}
		}
		return null;
	}

	public static void copySheet(HSSFWorkbook destwb, HSSFSheet dest, HSSFWorkbook srcwb, HSSFSheet src) {
		if (src == null || dest == null)
			return;

		copySheetStyle(destwb, dest, srcwb, src);

		for (int i = 0; i <= src.getLastRowNum(); i++) {
			HSSFRow row = src.getRow(i);
			copyRow(destwb, dest.createRow(i), srcwb, row);
		}
	}

	public static void copyRow(HSSFWorkbook destwb, HSSFRow dest, HSSFWorkbook srcwb, HSSFRow src) {
		if (src == null || dest == null)
			return;
		for (short i = 0; i <= src.getLastCellNum(); i++) {
			if (src.getCell(i) != null) {
				HSSFCell cell = dest.createCell(i);
				copyCell(destwb, cell, srcwb, src.getCell(i));
			}
		}

	}

	public static void copyCell(HSSFWorkbook destwb, HSSFCell dest, HSSFWorkbook srcwb, HSSFCell src) {
		if (src == null) {
			dest.setCellType(HSSFCell.CELL_TYPE_BLANK);
			return;
		}

		if (src.getCellComment() != null)
			dest.setCellComment(src.getCellComment());
		if (src.getCellStyle() != null) {
			HSSFCellStyle nstyle = findStyle(src.getCellStyle(), srcwb, destwb);
			if (nstyle == null) {
				nstyle = destwb.createCellStyle();
				copyCellStyle(destwb, nstyle, srcwb, src.getCellStyle());
			}
			dest.setCellStyle(nstyle);
		}
		dest.setCellType(src.getCellType());

		switch (src.getCellType()) {
			case HSSFCell.CELL_TYPE_BLANK:

				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				dest.setCellValue(src.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				dest.setCellFormula(src.getCellFormula());
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				dest.setCellErrorValue(src.getErrorCellValue());
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				dest.setCellValue(src.getNumericCellValue());
				break;
			default:
				dest.setCellValue(new HSSFRichTextString(src.getRichStringCellValue().getString()));
				break;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void writeExcel(String file, List list, Class clazz) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		List<String> flist = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals(UID)) {
				continue;
			}
			flist.add(fields[i].getName());
		}
		writeExcel(file, list, clazz, flist, null);
	}

	@SuppressWarnings("rawtypes")
	public static void writeExcel(String file, List list, Class clazz, List<String> fields, List<String> titles)
		throws Exception {
		OutputStream os = getOutputStream(file);
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
		jxl.write.WritableSheet ws = wwb.createSheet("Sheet1", 0);
		jxl.write.Label label = null;
		int start = 0;
		if (titles != null && titles.size() > 0) {
			for (int j = 0; j < titles.size(); j++) {
				label = new jxl.write.Label(j, 0, titles.get(j));
				ws.addCell(label);
			}
			start++;
		}
		for (int i = start; i < list.size() + start; i++) {
			Object o = list.get(i - start);
			if (o == null) {
				continue;
			}
			for (int j = 0; j < fields.size(); j++) {
				String value = "";
				String field = fields.get(j);
				if (field == null || "serialVersionUID".equals(field)) {
					continue;
				}
				try {
					value = ReflectUtil.invokeGetMethod(clazz, o, field).toString();
				} catch (Exception e) {
					LOGGER.error(e);
				}
				if (field != null && isTime(field)) {
					if (value.isEmpty()) {
						value = "";
					} else {
						value = DateUtil.dateStr4(value);
					}
				}
				// 判断是否包含金钱，如有，将其保留两位有效数字
				if (field != null && isMoney(field)) {
					if (value.isEmpty()) {
						value = "";
					} else {
						value = StringUtil.isNull(BigDecimalUtil.round(value));
					}
				}
				label = new jxl.write.Label(j, i, value);
				ws.addCell(label);
			}
		}
		wwb.write();
		wwb.close();
	}

	@SuppressWarnings("rawtypes")
	public static List[] read(String xls) throws Exception {
		List[] data = null;
		File file = new File(xls);
		if (file.exists()) {
			data = read(file);
		}
		return data;
	}

	@SuppressWarnings("rawtypes")
	public static List[] read(File file) {
		List[] data = null;
		Workbook wb = null;
		try {
			wb = Workbook.getWorkbook(file);
			if (wb != null) {
				Sheet[] sheets = wb.getSheets();
				if (sheets != null && sheets.length >= 0) {
					int rows = sheets[0].getRows();
					data = new List[rows];
					for (int j = 0; j < rows; j++) {
						List<String> rowData = new ArrayList<String>();
						Cell[] cells = sheets[0].getRow(j);
						if (cells != null && cells.length != 0) {
							for (int k = 0; k < cells.length; k++) {
								String cell = cells[k].getContents();
								rowData.add(cell);
							}
						}
						data[j] = rowData;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
		} finally {
			if (wb != null)
				wb.close();
		}
		return data;
	}

	/**
	 * 判断是否是时间
	 * 
	 * @param field
	 * @return
	 */
	private static boolean isTime(String field) {
		String[] times = new String[] { "addtime", "addTime", "repay_time", "verify_time", "repay_yestime",
				"repayment_time", "repayment_yestime", "registertime", "vip_verify_time", "full_verifytime",
				"last_tender_time", "kefu_addtime", "realname_verify_time", "video_verify_time", "scene_verify_time",
				"phone_verify_time", "pwd_modify_time", "vip_end_time", "add_time", "update_time",
				"interest_start_time", "interest_end_time" };
		boolean isTime = false;
		for (String s : times) {
			if (s.equals(field)) {
				isTime = true;
				break;
			}
		}
		return isTime;
	}

	/**
	 * 判断是否是金钱
	 * 
	 * @param field
	 * @return
	 */
	private static boolean isMoney(String field) {
		String[] money = new String[] { "sum", "use_money", "collection", "total", "no_use_money", "money" };
		boolean isMoney = false;
		for (String s : money) {
			if (s.equals(field)) {
				isMoney = true;
				break;
			}
		}
		return isMoney;
	}

	public static OutputStream getOutputStream(String file) throws IOException  {
		File f = new File(file);
		f.createNewFile();
		OutputStream os = new FileOutputStream(f);
		return os;
	}
}
