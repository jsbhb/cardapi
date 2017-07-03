package com.fireCloud.tradCity.commodity.model;

/**
 * 
 * @author wqy
 * @fun model
 * @date 2017/06/01
 */
public class CommodityPriceModel {
	
	private Integer commodityId;
	
	private Integer quantityStart;
	
	private Integer quantityEnd;
	
	private Double price;
	
	private String createTime;
	
	private String createOpt;
	
	private String updateTime;
	
	private String updateOpt;

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getQuantityStart() {
		return quantityStart;
	}

	public void setQuantityStart(Integer quantityStart) {
		this.quantityStart = quantityStart;
	}

	public Integer getQuantityEnd() {
		return quantityEnd;
	}

	public void setQuantityEnd(Integer quantityEnd) {
		this.quantityEnd = quantityEnd;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateOpt() {
		return createOpt;
	}

	public void setCreateOpt(String createOpt) {
		this.createOpt = createOpt;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateOpt() {
		return updateOpt;
	}

	public void setUpdateOpt(String updateOpt) {
		this.updateOpt = updateOpt;
	}

}
