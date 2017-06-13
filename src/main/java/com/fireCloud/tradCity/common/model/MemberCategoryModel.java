package com.fireCloud.tradCity.common.model;

import java.util.List;

/**
 * @author wqy
 * @fun 行业分类model
 * @date 2017年6月8日
 */
public class MemberCategoryModel {

	private Integer id;
	
	private String dictName;
	
	private List<MemberCategoryEnteryModel> entryList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}


	public List<MemberCategoryEnteryModel> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<MemberCategoryEnteryModel> entryList) {
		this.entryList = entryList;
	}

	@Override
	public String toString() {
		return "MemberCategoryModel [id=" + id + ", dictName=" + dictName + ", entryList=" + entryList + "]";
	}
	
}
