package com.fireCloud.tradCity.popularize.model;

import com.fireCloud.tradCity.popularize.model.submodel.BaseProductInfo;

/**
 * 
 * @author wqy
 * @fun 推广商品model
 * @date 2017/05/31
 */
public class ProductInfoModel extends BaseProductInfo {

	// 推广名称用于显示头部
	private String name;
	// 推广代码
	private String popularizeCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPopularizeCode() {
		return popularizeCode;
	}
	public void setPopularizeCode(String popularizeCode) {
		this.popularizeCode = popularizeCode;
	}
	@Override
	public String toString() {
		return super.toString() + "ProductInfo [name=" + name + ", popularizeCode=" + popularizeCode + "]";
	}
	
}
