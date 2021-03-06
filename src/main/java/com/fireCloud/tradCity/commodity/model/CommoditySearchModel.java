package com.fireCloud.tradCity.commodity.model;

import java.util.List;

/**
 * 
 * @author wqy
 * @fun model
 * @date 2017/06/01
 */
public class CommoditySearchModel {

	private String commodityId;
	
	private String commodityCategory1;
	
	private String commodityCategory2;
	
	private String commodityCategory3;
	
	private String commodityName;
	
	private String brand;
	
	private Double price;
	
	private List<CommodityPriceModel> priceList;
	
	private String quantity;
	
	private String uom;
	
	private String color;
	
	private List<CommodityColorModel> colorList;
	
	private String size;
	
	private List<CommoditySizeModel> sizeList;
	
	private String saleState;
	
	private Integer goodFlg;
	
	private Integer hotFlg;
	
	private Integer choiceFlg;
	
	private Integer appreciateFlg;
	
	private String picture1;
	
	private String picture2;
	
	private String picture3;
	
	private String picture4;
	
	private String picture5;
	
	private String commodityIntroduction;
	
	private String packingSpecification;
	
	private String instructions;
	
	private String customerService;
	
	private String showPagePath;
	
	private String showPageSource;

	private Integer memberId;
	
	private String memberName;
	
	private String logoPath;
	
	private String topPicPath;
	//商家信用
	private String reputation;
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
	//
	private String mobile;
	//
	private String email;
	//
	private String phone;
	//
	private String fax;
	// 多客服以逗号分开
	private String qq;
	
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityCategory1() {
		return commodityCategory1;
	}
	public void setCommodityCategory1(String commodityCategory1) {
		this.commodityCategory1 = commodityCategory1;
	}
	public String getCommodityCategory2() {
		return commodityCategory2;
	}
	public void setCommodityCategory2(String commodityCategory2) {
		this.commodityCategory2 = commodityCategory2;
	}
	public String getCommodityCategory3() {
		return commodityCategory3;
	}
	public void setCommodityCategory3(String commodityCategory3) {
		this.commodityCategory3 = commodityCategory3;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSaleState() {
		return saleState;
	}
	public void setSaleState(String saleState) {
		this.saleState = saleState;
	}
	public Integer getGoodFlg() {
		return goodFlg;
	}
	public void setGoodFlg(Integer goodFlg) {
		this.goodFlg = goodFlg;
	}
	public Integer getHotFlg() {
		return hotFlg;
	}
	public void setHotFlg(Integer hotFlg) {
		this.hotFlg = hotFlg;
	}
	public Integer getChoiceFlg() {
		return choiceFlg;
	}
	public void setChoiceFlg(Integer choiceFlg) {
		this.choiceFlg = choiceFlg;
	}
	public Integer getAppreciateFlg() {
		return appreciateFlg;
	}
	public void setAppreciateFlg(Integer appreciateFlg) {
		this.appreciateFlg = appreciateFlg;
	}
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
	public String getPicture3() {
		return picture3;
	}
	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}
	public String getPicture4() {
		return picture4;
	}
	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}
	public String getPicture5() {
		return picture5;
	}
	public void setPicture5(String picture5) {
		this.picture5 = picture5;
	}
	public String getCommodityIntroduction() {
		return commodityIntroduction;
	}
	public void setCommodityIntroduction(String commodityIntroduction) {
		this.commodityIntroduction = commodityIntroduction;
	}
	public String getPackingSpecification() {
		return packingSpecification;
	}
	public void setPackingSpecification(String packingSpecification) {
		this.packingSpecification = packingSpecification;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getCustomerService() {
		return customerService;
	}
	public void setCustomerService(String customerService) {
		this.customerService = customerService;
	}
	public String getShowPagePath() {
		return showPagePath;
	}
	public void setShowPagePath(String showPagePath) {
		this.showPagePath = showPagePath;
	}
	public String getShowPageSource() {
		return showPageSource;
	}
	public void setShowPageSource(String showPageSource) {
		this.showPageSource = showPageSource;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getReputation() {
		return reputation;
	}
	public void setReputation(String reputation) {
		this.reputation = reputation;
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
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getTopPicPath() {
		return topPicPath;
	}
	public void setTopPicPath(String topPicPath) {
		this.topPicPath = topPicPath;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public List<CommodityPriceModel> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<CommodityPriceModel> priceList) {
		this.priceList = priceList;
	}
	public List<CommodityColorModel> getColorList() {
		return colorList;
	}
	public void setColorList(List<CommodityColorModel> colorList) {
		this.colorList = colorList;
	}
	public List<CommoditySizeModel> getSizeList() {
		return sizeList;
	}
	public void setSizeList(List<CommoditySizeModel> sizeList) {
		this.sizeList = sizeList;
	}
	
}
