package com.fireCloud.tradCity.popularize.model;

/**
 * 
 * @author wqy
 * @fun 推广企业的model
 * @date 2017/05/31
 */
public class MemberModel {
	
	//推广名称用于显示头部
	private String name;
	//推广代码
	private String popularizeCode;
	//logo显示的图片地址
	private String logoPath;
	
	private Integer memberId;
	
	private String picPath;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPopularizeCode() {
		return popularizeCode;
	}

	public void setPopularizeCode(String popularizeCode) {
		this.popularizeCode = popularizeCode;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MemberModel [name=" + name + ", popularizeCode=" + popularizeCode + ", logoPath=" + logoPath
				+ ", memberId=" + memberId + ", picPath=" + picPath + ", description=" + description + "]";
	}
	
}
