package com.fireCloud.tradCity.member.model;

/**
 * @author wqy
 * @fun 会员分类model
 * @date 2017年6月5日
 */
public class MemberClassifyModel {
	
	private Integer id;

	private String industry;
	
	private String industryName;
	
	private String category;
	
	private String categoryName;

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "MemberClassifyModel [id=" + id + ", industry=" + industry + ", industryName=" + industryName
				+ ", category=" + category + ", categoryName=" + categoryName + "]";
	}
	
}
