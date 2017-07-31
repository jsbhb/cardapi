package com.fireCloud.tradCity.popularize.model.submodel;

import java.math.BigDecimal;

/**
 * 
 * @author wqy
 * @fun 推广商品的基础model
 * @date 2017/05/31
 */
public class BaseProductInfoModel {
	
	private Integer id;

	private String title;
	
	private String shortTitle;
	
	private String description;
	
	private BigDecimal price;
	
	private Long productId;
	
	private String picPath;
	
	private Integer memberId;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "BaseProductInfo [id=" + id + ", title=" + title + ", shortTitle=" + shortTitle + ", description="
				+ description + ", price=" + price + ", productId=" + productId + ", picPath=" + picPath + "]";
	}
}
