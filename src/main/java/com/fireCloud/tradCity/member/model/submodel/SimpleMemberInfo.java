package com.fireCloud.tradCity.member.model.submodel;

import java.util.List;

/**
 * @author wqy
 * @fun 会员的简单信息，用于查询是显示缩略信息，详细会员信息model继承该model
 * @date 2017年6月2日
 */
public class SimpleMemberInfo {

	private Integer memberId;
	// 所在行业
	private List<String> industryList;
	// 主营类目
	private List<String> categoryList;
	// 主营产品，逗号隔开字符串
	private String product;
	//商家信用
	private String reputation;
	//
	private String logoPath;
	//是否平台担保 0:否；1：是
	private Integer guarantee;
	//是否优质商家 0:否；1：是
	private Integer highQuality;
	//是否诚信示范 0:否；1：是
	private Integer sincerity;
	//是否支持退换货0:否；1：是
	private Integer returnGoods;
	//
	private String province;
	//
	private String city;
	//
	private String area;
	//
	private String address;
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public List<String> getIndustryList() {
		return industryList;
	}
	public void setIndustryList(List<String> industryList) {
		this.industryList = industryList;
	}
	public List<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getReputation() {
		return reputation;
	}
	public void setReputation(String reputation) {
		this.reputation = reputation;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public Integer getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(Integer guarantee) {
		this.guarantee = guarantee;
	}
	public Integer getHighQuality() {
		return highQuality;
	}
	public void setHighQuality(Integer highQuality) {
		this.highQuality = highQuality;
	}
	public Integer getSincerity() {
		return sincerity;
	}
	public void setSincerity(Integer sincerity) {
		this.sincerity = sincerity;
	}
	public Integer getReturnGoods() {
		return returnGoods;
	}
	public void setReturnGoods(Integer returnGoods) {
		this.returnGoods = returnGoods;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "SimpleMemberInfo [memberId=" + memberId + ", industryList=" + industryList + ", categoryList="
				+ categoryList + ", product=" + product + ", reputation=" + reputation + ", logoPath=" + logoPath
				+ ", guarantee=" + guarantee + ", highQuality=" + highQuality + ", sincerity=" + sincerity
				+ ", returnGoods=" + returnGoods + ", province=" + province + ", city=" + city + ", area=" + area
				+ ", address=" + address + "]";
	}
	
}
