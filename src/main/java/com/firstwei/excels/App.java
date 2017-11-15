package com.firstwei.excels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {

		List<SaleInfo> list = new ArrayList<SaleInfo>();
		SaleInfo hc = new SaleInfo();
		hc.setGoodsName("苹果");
		hc.setSaleQuantity(120);
		hc.setSaleDate(new SimpleDateFormat("yyyy/MM/dd").parse("2017/011/01"));
		list.add(hc);
		
		SaleInfo bc = new SaleInfo();
		bc.setGoodsName("香蕉");
		bc.setSaleQuantity(120);
		bc.setSaleDate(new SimpleDateFormat("yyyy/MM/dd").parse("2017/011/01"));
		list.add(bc);
		String filePath = "D:/采购计划.xls";
		ExportExcelUtil.exportExcel(list, filePath);

	}
	
	

}
