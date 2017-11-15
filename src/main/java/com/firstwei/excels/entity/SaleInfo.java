/**
 * 销量
 */
package com.firstwei.excels.entity;

import com.firstwei.excels.ExcelCell;

/**
 * @author ZN-yyrj015
 *
 */
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
	
	
}
