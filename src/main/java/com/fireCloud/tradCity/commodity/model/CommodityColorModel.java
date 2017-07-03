package com.fireCloud.tradCity.commodity.model;

/**
 * 
 * @author wqy
 * @fun model
 * @date 2017/06/01
 */
public class CommodityColorModel {
	
	private Integer commodityId;
	
	private String color;
	
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
