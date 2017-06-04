package com.fireCloud.tradCity.popularize.model;

/**
 * 
 * @author wqy
 * @fun 首页轮播model
 * @date 2017/06/01
 */
public class IndexBannerModel {

	private String picPath;
	
	private String href;
	
	private String name;
	
	private String popularizeCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getPopularizeCode() {
		return popularizeCode;
	}

	public void setPopularizeCode(String popularizeCode) {
		this.popularizeCode = popularizeCode;
	}

	@Override
	public String toString() {
		return "IndexBannerModel [picPath=" + picPath + ", href=" + href + ", popularizeCode=" + popularizeCode + "]";
	}
	
}
