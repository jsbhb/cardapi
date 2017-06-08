package com.fireCloud.tradCity.common.model;

/**
 * @author wqy
 * @fun 行业分类细分model
 * @date 2017年6月8日
 */
public class MemberCategoryEnteryModel {

	private Integer id;
	
	private Integer dictId;
	
	private String entryName;
	
	private String createTime;
	
	private Integer opt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDictId() {
		return dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getOpt() {
		return opt;
	}

	public void setOpt(Integer opt) {
		this.opt = opt;
	}

	@Override
	public String toString() {
		return "MemberCategoryEnteryModel [id=" + id + ", dictId=" + dictId + ", entryName=" + entryName
				+ ", createTime=" + createTime + ", opt=" + opt + "]";
	}
	
}
