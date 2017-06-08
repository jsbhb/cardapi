package com.fireCloud.tradCity.member.model;

/**
 * @author wqy
 * @fun 会员分类model
 * @date 2017年6月5日
 */
public class MemberClassifyModel {
	
	private Integer id;

	private Integer categoryDict;
	
	private String dictName;
	
	private Integer categoryEntry;
	
	private String entryName;


	public Integer getCategoryDict() {
		return categoryDict;
	}

	public void setCategoryDict(Integer categoryDict) {
		this.categoryDict = categoryDict;
	}

	public Integer getCategoryEntry() {
		return categoryEntry;
	}

	public void setCategoryEntry(Integer categoryEntry) {
		this.categoryEntry = categoryEntry;
	}

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

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	@Override
	public String toString() {
		return "MemberClassifyModel [id=" + id + ", categoryDict=" + categoryDict + ", dictName=" + dictName
				+ ", categoryEntry=" + categoryEntry + ", entryName=" + entryName + "]";
	}

}
