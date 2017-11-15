/**
 * 可销天数
 */
package com.firstwei.excels.entity;

import com.firstwei.excels.ExcelCell;

/**
 * @author ZN-yyrj015
 *
 */
public class SaleDayInfo {
	/**
	 * 商品名称
	 */
	@ExcelCell(cell = "A", name = "商品名称")
	private String goodsName;
	
	/**
	 * 可销天数
	 */
	@ExcelCell(cell = "B", name = "可销天数")
	private Integer saleDay;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getSaleDay() {
		return saleDay;
	}

	public void setSaleDay(Integer saleDay) {
		this.saleDay = saleDay;
	}
	
	
	
}
