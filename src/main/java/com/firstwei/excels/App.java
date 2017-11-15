package com.firstwei.excels;

import java.util.ArrayList;
import java.util.List;

import com.firstwei.excels.entity.AddOrderInfo;
import com.firstwei.excels.entity.BackOrderInfo;
import com.firstwei.excels.entity.SaleDayInfo;
import com.firstwei.excels.entity.SaleInfo;
import com.firstwei.excels.entity.StockInfo;


/**
 * Hello world!
 *
 */
public class App {
	
	private static String SALE_TABLE = "D:\\采购计划\\销量分析.xlsx";
	private static String STOCK_TABLE = "D:\\采购计划\\库存量.xlsx";
	private static String BACK_ORDER_TABLE = "D:\\采购计划\\未交量.xlsx";
	private static String SALE_DAY_TABLE = "D:\\采购计划\\可销天数.xlsx";
	private static String ADD_ORDER_TABLE = "D:\\采购计划\\加单数量.xlsx";

	/**
	 * 入口
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//读取销量表
		List<SaleInfo>  saleList = ImportExcelUtil.importExcel(SALE_TABLE, SaleInfo.class);
		//读取库存表
		List<StockInfo>  stockList = ImportExcelUtil.importExcel(STOCK_TABLE, StockInfo.class);
		//读取未交量表
		List<BackOrderInfo>  backOrderList = ImportExcelUtil.importExcel(BACK_ORDER_TABLE, BackOrderInfo.class);
		//可销天数表
		List<SaleDayInfo>  saleDayList = ImportExcelUtil.importExcel(SALE_DAY_TABLE, SaleDayInfo.class);

		//计算加单量
		List<AddOrderInfo> addOrderList = new ArrayList<AddOrderInfo>();
		AddOrderInfo ao = new AddOrderInfo();
		ao.setGoodsName("苹果");
		ao.setAddOrderQuantity(2000);
		addOrderList.add(ao);
		
		AddOrderInfo bo = new AddOrderInfo();
		bo.setGoodsName("香蕉");
		bo.setAddOrderQuantity(2000);
		addOrderList.add(bo);
		
		ExportExcelUtil.exportExcel(saleList, SALE_TABLE);
		ExportExcelUtil.exportExcel(stockList, STOCK_TABLE);
		ExportExcelUtil.exportExcel(backOrderList, BACK_ORDER_TABLE);
		ExportExcelUtil.exportExcel(saleDayList, SALE_DAY_TABLE);
		ExportExcelUtil.exportExcel(addOrderList, ADD_ORDER_TABLE);
   
		
	}
	

	
	
//	public static void main(String[] args) throws Exception {
//
//		List<SaleInfo> list = new ArrayList<SaleInfo>();
//		SaleInfo hc = new SaleInfo();
//		hc.setGoodsName("苹果");
//		hc.setSaleQuantity(120);
//		hc.setSaleDate(new SimpleDateFormat("yyyy/MM/dd").parse("2017/011/01"));
//		list.add(hc);
//		
//		SaleInfo bc = new SaleInfo();
//		bc.setGoodsName("香蕉");
//		bc.setSaleQuantity(120);
//		bc.setSaleDate(new SimpleDateFormat("yyyy/MM/dd").parse("2017/011/01"));
//		list.add(bc);
//		String filePath = "D:/采购计划.xls";
//		ExportExcelUtil.exportExcel(list, filePath);
//
//	}
	
	

}
