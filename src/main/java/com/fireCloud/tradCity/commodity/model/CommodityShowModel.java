package com.fireCloud.tradCity.commodity.model;

/**
 * 
 * @author wqy
 * @fun model
 * @date 2017/06/01
 */
public class CommodityShowModel {
	
	private Integer commodityId;
	
	private String commodityIntroduction;
	
	private String packingSpecification;
	
	private String instructions;
	
	private String customerService;
	
	private String showPagePath;
	
	private String showPageSource;
	
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

	public String getCommodityIntroduction() {
		return commodityIntroduction;
	}

	public void setCommodityIntroduction(String commodityIntroduction) {
		this.commodityIntroduction = commodityIntroduction;
	}

	public String getPackingSpecification() {
		return packingSpecification;
	}

	public void setPackingSpecification(String packingSpecification) {
		this.packingSpecification = packingSpecification;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getCustomerService() {
		return customerService;
	}

	public void setCustomerService(String customerService) {
		this.customerService = customerService;
	}

	public String getShowPagePath() {
		return showPagePath;
	}

	public void setShowPagePath(String showPagePath) {
		this.showPagePath = showPagePath;
	}

	public String getShowPageSource() {
		return showPageSource;
	}

	public void setShowPageSource(String showPageSource) {
		this.showPageSource = showPageSource;
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
