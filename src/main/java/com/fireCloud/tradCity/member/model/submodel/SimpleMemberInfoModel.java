package com.fireCloud.tradCity.member.model.submodel;

import java.util.List;

import com.fireCloud.tradCity.member.model.CategoryDictModel;
import com.fireCloud.tradCity.member.model.CategoryEntryModel;
import com.fireCloud.tradCity.member.model.IndustryModel;

/**
 * @author wqy
 * @fun 会员的简单信息，用于查询是显示缩略信息，详细会员信息model继承该model
 * @date 2017年6月2日
 */
public class SimpleMemberInfoModel {

	private Integer memberId;

	private String memberName;
	
	private List<IndustryModel> industryList;
	//会员分类
	private List<CategoryDictModel> dictList;
	// 会员分类
	private List<CategoryEntryModel> entryList;
	// 主营产品，逗号隔开字符串
	private String product;
	// 商家信用
	private Integer reputation;
	//
	private String logoPath;
	//
	private String frontPicPath;
	// 是否平台担保 0:否；1：是
	private Integer guarantee;
	// 是否优质商家 0:否；1：是
	private Integer highQuality;
	// 是否诚信示范 0:否；1：是
	private Integer sincerity;
	// 是否支持退换货0:否；1：是
	private Integer returnGoods;
	//
	private String province;
	//
	private String city;
	//
	private String area;
	//
	private String address;
	//
	private Integer popularize;
	// 入驻时间
	private String enterTime;
	
	private Integer isRel;
	
	private Integer isDel;

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getIsRel() {
		return isRel;
	}

	public void setIsRel(Integer isRel) {
		this.isRel = isRel;
	}

	public Integer getPopularize() {
		return popularize;
	}

	public void setPopularize(Integer popularize) {
		this.popularize = popularize;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getFrontPicPath() {
		return frontPicPath;
	}

	public void setFrontPicPath(String frontPicPath) {
		this.frontPicPath = frontPicPath;
	}

	public List<CategoryDictModel> getDictList() {
		return dictList;
	}

	public void setDictList(List<CategoryDictModel> dictList) {
		this.dictList = dictList;
	}

	public List<CategoryEntryModel> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<CategoryEntryModel> entryList) {
		this.entryList = entryList;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public List<IndustryModel> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<IndustryModel> industryList) {
		this.industryList = industryList;
	}

	@Override
	public String toString() {
		return "SimpleMemberInfoModel [memberId=" + memberId + ", memberName=" + memberName + ", product=" + product
				+ ", reputation=" + reputation + ", logoPath=" + logoPath + ", frontPicPath=" + frontPicPath
				+ ", guarantee=" + guarantee + ", highQuality=" + highQuality + ", sincerity=" + sincerity
				+ ", returnGoods=" + returnGoods + ", province=" + province + ", city=" + city + ", area=" + area
				+ ", address=" + address + ", popularize=" + popularize + ", enterTime=" + enterTime + ", isRel="
				+ isRel + ", isDel=" + isDel + "]";
	}

}
