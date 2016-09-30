package com.orange.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Excel工具类（适用指标管理）
 * 
 * @author syq
 * @createDate 2013-10-16
 * 
 */
public class ExcelUtils {

	/**
	 * 上传的文件解析成普通的文件
	 * 
	 * @param multipartRequest
	 * @return
	 */
	public static File multipartFileToFile(
			MultipartHttpServletRequest multipartRequest) {
		MultipartFile multipartFile = multipartRequest.getFile("file");
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(multipartFile.getOriginalFilename());
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(multipartFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 创建excel
	 * 
	 * @author syq
	 * @createDate 2013-10-16
	 * @param filePath
	 * @param fileName
	 * @param title
	 * @param list
	 * @return
	 */
	public static File CreateExcel(File file, String[] title,
			List<Map<String, Object>> listMap, int[] SETCOLUMNVIEW) {
		try {
			if (file != null) {
				// 打开文件
				WritableWorkbook book = Workbook.createWorkbook(file);
				// 生成名为fileName的工作表，参数0表示这是第一页
				WritableSheet sheet = book.createSheet(file.getName(), 0);
				// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
				Label label = null;
				for (int i = 0; i < title.length; i++) {
					label = new Label(i, 0, title[i]);
					sheet.addCell(label);
					sheet.setColumnView(i, SETCOLUMNVIEW[i]); // 设置列的宽度
					int j = 1;
					for (Map<String, Object> map : listMap) {
						String excelText = null;
						if (map.get(title[i]) == null) {
							excelText = "";
						} else {
							excelText = map.get(title[i]).toString();
						}
						label = new Label(i, j++, excelText);
						sheet.addCell(label);
					}
				}
				// 写入数据并关闭文件
				book.write();
				book.close();
				return file;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 创建excel
	 * 
	 * @author syq
	 * @createDate 2013-10-16
	 * @param filePath
	 * @param fileName
	 * @param title
	 * @param list
	 * @return
	 */
	public static File CreateExcelOrder(File file, String[] title,
			List<Map<String, Object>> listMap, int[] SETCOLUMNVIEW) {
		try {
			if (file != null) {
				// 打开文件
				WritableWorkbook book = Workbook.createWorkbook(file);
				// 生成名为fileName的工作表，参数0表示这是第一页
				WritableSheet sheet = book.createSheet(file.getName(), 0);
				// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
				Label label = null;
				for (int i = 1; i < title.length; i++) {
					label = new Label(i, 0, title[i]);
					sheet.addCell(label);
					sheet.setColumnView(i, SETCOLUMNVIEW[i - 1]); // 设置列的宽度
					int j = 1;
					String orderDetailFlg = null;
					for (Map<String, Object> map : listMap) {
						String excelText = null;
						if (map.get(title[i]) == null) {
							excelText = "";
						} else {
							excelText = map.get(title[i]).toString();
						}
						orderDetailFlg = map.get(title[0]).toString();
						
						WritableCellFormat cellFormat = new WritableCellFormat();
						// 设置背景颜色;
						cellFormat.setBackground(Colour.ORANGE);
						if (orderDetailFlg.equals("1")) {
							label = new Label(i, j++, excelText, cellFormat);
						} else {
							label = new Label(i, j++, excelText);
						}
						sheet.addCell(label);
					}
				}
				// 写入数据并关闭文件
				book.write();
				book.close();
				return file;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
}
