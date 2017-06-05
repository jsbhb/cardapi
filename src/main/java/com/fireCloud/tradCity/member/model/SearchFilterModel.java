package com.fireCloud.tradCity.member.model;

import java.util.Map;
import java.util.Set;

/**
 * @author wqy
 * @fun 将查询出来的会员对象，提取行业和主营信息供前台筛选
 * @date 2017年6月5日
 */
public class SearchFilterModel {

	// 所在行业
	private Map<String,String> industryMap;
	// 主营类目
	private Map<String,String> categoryMap;
	public Map<String, String> getIndustryMap() {
		return industryMap;
	}
	public void setIndustryMap(Map<String, String> industryMap) {
		this.industryMap = industryMap;
	}
	public Map<String, String> getCategoryMap() {
		return categoryMap;
	}
	public void setCategoryMap(Map<String, String> categoryMap) {
		this.categoryMap = categoryMap;
	}
	@Override
	public String toString() {
		return "SearchFilterModel [industryMap=" + industryMap + ", categoryMap=" + categoryMap + "]";
	}
	
}
