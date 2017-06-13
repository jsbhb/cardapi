package com.fireCloud.tradCity.common.model;

/**
 * @author wqy
 * @fun 行业分类细分model
 * @date 2017年6月8日
 */
public class MemberCategoryEnteryModel {

	private Integer id;
	
	private String entryName;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}


	@Override
	public String toString() {
		return "MemberCategoryEnteryModel [id=" + id + ", entryName=" + entryName + "]";
	}
	
}
