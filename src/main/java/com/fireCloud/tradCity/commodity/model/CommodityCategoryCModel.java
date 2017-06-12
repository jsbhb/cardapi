package com.fireCloud.tradCity.commodity.model;

/**
 * 
 * @author wqy
 * @fun model
 * @date 2017/06/01
 */
public class CommodityCategoryCModel {

	private Integer id;
	
	private Integer categoryBId;
	
	private String categoryName;
	
	private Integer status;
	
	private String createTime;
	
	private String createOpt;
	
	private String updateTime;
	
	private String updateOpt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryBId() {
		return categoryBId;
	}

	public void setCategoryBId(Integer categoryBId) {
		this.categoryBId = categoryBId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
