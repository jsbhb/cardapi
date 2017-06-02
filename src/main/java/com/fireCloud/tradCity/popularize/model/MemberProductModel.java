package com.fireCloud.tradCity.popularize.model;

import java.util.List;

import com.fireCloud.tradCity.popularize.model.submodel.BaseProductInfo;

/**
 * @author wqy
 * @fun 用来存储会员和商品联合推广的model
 * @date 2017/05/31
 */
public class MemberProductModel {

	//推广名称用于显示头部
	private String name;
	//推广代码
	private String popularizeCode;
	//logo显示的图片地址
	private String logoPath;
	//
	private Integer memberId;
	//普通图片显示
	private List<BaseProductInfo> generalInfoList;
	//
	private String picPath1;
	//
	private String picPath2;
	//
	private String picPath3;
	//
	private String description1;
	//
	private String description2;
	//
	private String description3;
	public String getPopularizeCode() {
		return popularizeCode;
	}
	public void setPopularizeCode(String popularizeCode) {
		this.popularizeCode = popularizeCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPicPath1() {
		return picPath1;
	}
	public void setPicPath1(String picPath1) {
		this.picPath1 = picPath1;
	}
	public String getPicPath2() {
		return picPath2;
	}
	public void setPicPath2(String picPath2) {
		this.picPath2 = picPath2;
	}
	public String getPicPath3() {
		return picPath3;
	}
	public void setPicPath3(String picPath3) {
		this.picPath3 = picPath3;
	}
	
	public String getDescription1() {
		return description1;
	}
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	public String getDescription2() {
		return description2;
	}
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public String getDescription3() {
		return description3;
	}
	public void setDescription3(String description3) {
		this.description3 = description3;
	}
	public List<BaseProductInfo> getGeneralInfoList() {
		return generalInfoList;
	}
	public void setGeneralInfoList(List<BaseProductInfo> generalInfoList) {
		this.generalInfoList = generalInfoList;
	}
	@Override
	public String toString() {
		return "MemberProductModel [name=" + name + ", popularizeCode=" + popularizeCode + ", logoPath=" + logoPath
				+ ", memberId=" + memberId + ", generalInfoList=" + generalInfoList + ", picPath1=" + picPath1
				+ ", picPath2=" + picPath2 + ", picPath3=" + picPath3 + ", description1=" + description1
				+ ", description2=" + description2 + ", description3=" + description3 + "]";
	}
	
}
