package com.fireCloud.tradCity.member.model;

/**
 * @author wqy
 * @fun 会员分类细分model
 * @date 2017年6月5日
 */
public class CategoryEntryModel {

	private Integer id;
	
	private Integer categoryEntry;

	private String entryName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryEntry() {
		return categoryEntry;
	}

	public void setCategoryEntry(Integer categoryEntry) {
		this.categoryEntry = categoryEntry;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	
}
