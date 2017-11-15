package com.firstwei.excels;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
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
	
	public static void main(String[] args) throws Exception {
		List<SaleInfo> list = importExcel("D:/采购计划.xls", SaleInfo.class);
        for (SaleInfo hc : list) {
        	System.out.println(hc.getGoodsName() + hc.getSaleQuantity()+ new SimpleDateFormat("yyyy/MM/dd").format(hc.getSaleDate()));
		}
	}
	
	/**
	 * Excel导出
	 */
    public static <T>List<T> importExcel(String filePath, Class<T> clazz) throws Exception {
		String fileType = filePath.split("\\.")[1];
		Field[] fields = clazz.getDeclaredFields();
		Workbook wb = null;
		List<T> list = new ArrayList<T>();
		InputStream in = new FileInputStream(new File(filePath));
        if (fileType.equals("xls")) {    
            wb = new HSSFWorkbook(in);    
        }    
        else if(fileType.equals("xlsx"))    
        {    
            wb = new XSSFWorkbook(in);    
        } 
		Sheet sheet = wb.getSheetAt(0);
		int length = sheet.getLastRowNum();
        for (int i = 1; i <= length; i++) {
        	Row row = sheet.getRow(i);
        	T t = clazz.newInstance();
        	for (Field field : fields) {
				ExcelCell ec = field.getAnnotation(ExcelCell.class);
				int idx = ec.cell().toUpperCase().toCharArray()[0] - 'A';
				Cell cell = row.getCell(idx);
				field.setAccessible(true);
				field.set(t, getValue(field.getType(), cell));
				
			}
        	list.add(t);
        }  
        
		in.close();
		
		return list;
    }
    
    /**
     * 获取单元格的值
     * @param clazz
     * @param cell
     * @return
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
			return Integer.valueOf(cell.getStringCellValue());
		} else if (clazz.isInstance(new Double(0))) {
			return Double.valueOf(cell.getStringCellValue());
		} else if (clazz.isInstance(new Long(0))) {
			return Long.valueOf(cell.getStringCellValue());
		} else if (clazz.isInstance(new BigDecimal(0))) {
			return BigDecimal.valueOf(Long.valueOf(cell.getStringCellValue()));
		} else if (clazz.isInstance(new Float(0))) {
			return Float.valueOf(cell.getStringCellValue());
		} else {
			return null;
		}
	}

}
