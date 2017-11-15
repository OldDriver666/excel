package com.firstwei.excels;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExportExcelUtil {
	/**
	 * Excel导出
	 */
    public static void exportExcel(List<?> list, String filePath) throws Exception {
		Class<?> clazz = list.get(0).getClass();
		String fileType = filePath.split("\\.")[1];
		Workbook wb = null;
		
        if (fileType.equals("xls")) {    
            wb = new HSSFWorkbook();    
        }    
        else if(fileType.equals("xlsx"))    
        {    
            wb = new XSSFWorkbook();    
        } 
		Sheet sheet = wb.createSheet("采购计划");
		assembleFirstRow(sheet, clazz);
		assembleTable(sheet, list);
		FileOutputStream out = new FileOutputStream(new File(filePath));
		wb.write(out);
		out.close();
    }	

	/**
	 * 组装表头第一行
	 */
	private static void assembleFirstRow(Sheet sheet, Class<?> clazz) {
		Row row = sheet.createRow(0);
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			ExcelCell ec = field.getAnnotation(ExcelCell.class);
			int idx = ec.cell().toUpperCase().toCharArray()[0] - 'A';
			Cell cell = row.createCell(idx);
			cell.setCellValue(ec.name());
		}
	}

	/**
	 * 组装表内容
	 */
	private static void assembleTable(Sheet sheet, List<?> list) throws Exception {
		Class<?> clazz = list.get(0).getClass();
		Field[] fields = clazz.getDeclaredFields();
		int rownum = 1;
		for (Object o : list) {
			Row row = sheet.createRow(rownum++);
			for (Field field : fields) {
				ExcelCell ec = field.getAnnotation(ExcelCell.class);
				int idx = ec.cell().toCharArray()[0] - 'A';
				Cell cell = row.createCell(idx);
				field.setAccessible(true);
				cell.setCellValue(getValue(field, o).toString());
			}
		}
	}
	
    /**
     * 获取属性值
     * @param clazz
     * @param cell
     * @return
     * @throws Exception
     */
    public static Object getValue(Field field, Object o) throws Exception {
        if (field.getType().isInstance(new Date())) {
        	return new SimpleDateFormat("yyyy/MM/dd").format((Date)field.get(o));
		}
    	return field.get(o);
	}
}
