package com.firstwei.excels;

import java.util.Date;

public class SaleInfo {

	/**
	 * 商品名称
	 */
	@ExcelCell(cell = "A", name = "商品名称")
	private String goodsName;
	
	/**
	 * 销量
	 */
	@ExcelCell(cell = "B", name = "销量")
	private Integer saleQuantity;
	
	/**
	 * 日期
	 */
	@ExcelCell(cell = "C", name = "日期")
	private Date saleDate;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(Integer saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	
}
