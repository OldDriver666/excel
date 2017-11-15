package com.firstwei.excels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcelUtil {

	/**
	 * Excel导入
	 * 
	 * @throws ParseException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws InstantiationException
	 */
	public static <T> List<T> importExcel(String filePath, Class<T> clazz) {
		InputStream in = null;

		String fileType = filePath.split("\\.")[1];
		Field[] fields = clazz.getDeclaredFields();
		Workbook wb = null;
		List<T> list = new ArrayList<T>();
		try {
			in = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fileType.equals("xls")) {
			try {
				wb = new HSSFWorkbook(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (fileType.equals("xlsx")) {
			try {
				wb = new XSSFWorkbook(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Sheet sheet = wb.getSheetAt(0);
		int length = sheet.getLastRowNum();
		for (int i = 1; i <= length; i++) {
			Row row = sheet.getRow(i);
			T t;
			try {
				t = clazz.newInstance();
				for (Field field : fields) {
					ExcelCell ec = field.getAnnotation(ExcelCell.class);
					int idx = ec.cell().toUpperCase().toCharArray()[0] - 'A';
					Cell cell = row.getCell(idx);
					field.setAccessible(true);
					try {
						field.set(t, getValue(field.getType(), cell));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				list.add(t);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;

	}

	/**
	 * 获取单元格的值
	 * 
	 * @param clazz
	 * @param cell
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public static Object getValue(Class<?> clazz, Cell cell) throws Exception {
		
		if (clazz.isInstance(new String())) {
			return cell.getStringCellValue();
		} else if (clazz.isInstance(new Character('A'))) {
			return cell.getStringCellValue().toCharArray()[0];
		} else if (clazz.isInstance(new Date())) {
			return new SimpleDateFormat("yyyy/MM/dd").parse(cell.getStringCellValue());
		} else if (clazz.isInstance(new Integer(0))) {
			Double d = cell.getNumericCellValue();
			return d.intValue();
		} else if (clazz.isInstance(new Double(0))) {
			return cell.getNumericCellValue();
		} else if (clazz.isInstance(new Long(0))) {
			Double d = cell.getNumericCellValue();
			return d.longValue();
		} else if (clazz.isInstance(new BigDecimal(0))) {
			Double d = cell.getNumericCellValue();
			return new BigDecimal(d);
		} else if (clazz.isInstance(new Float(0))) {
			Double d = cell.getNumericCellValue();
			return d.floatValue();
		} else {
			return cell.getStringCellValue();
		}
	}

}
