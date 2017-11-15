/**
 * 未交量
 */
package com.firstwei.excels.entity;

import com.firstwei.excels.ExcelCell;

/**
 * @author ZN-yyrj015
 *
 */
public class BackOrderInfo {

	/**
	 * 商品名称
	 */
	@ExcelCell(cell = "A", name = "商品名称")
	private String goodsName;
	
	/**
	 * 未交量
	 */
	@ExcelCell(cell = "B", name = "未交量")
	private Integer backOrderQuantity;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getBackOrderQuantity() {
		return backOrderQuantity;
	}

	public void setBackOrderQuantity(Integer backOrderQuantity) {
		this.backOrderQuantity = backOrderQuantity;
	}


	
}
