package com.fireCloud.tradCity.member.model;

/**
 * @author wqy
 * @fun 会员分类model
 * @date 2017年6月5日
 */
public class CategoryDictModel {

	private Integer id;

	private Integer categoryDict;
	
	private String dictName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryDict() {
		return categoryDict;
	}

	public void setCategoryDict(Integer categoryDict) {
		this.categoryDict = categoryDict;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	
}
