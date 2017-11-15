/**
 * 库存
 */
package com.firstwei.excels.entity;

import com.firstwei.excels.ExcelCell;


/**
 * @author ZN-yyrj015
 *
 */
public class StockInfo {

	/**
	 * 商品名称
	 */
	@ExcelCell(cell = "A", name = "商品名称")
	private String goodsName;
	
	/**
	 * 库存数量
	 */
	@ExcelCell(cell = "B", name = "库存数量")
	private Integer stockQuantity;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	
}
