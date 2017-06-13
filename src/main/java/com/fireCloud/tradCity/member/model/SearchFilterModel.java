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
	private Map<String,String> entryMap;
	//
	private Map<String,String> dictMap;
	
	public Map<String, String> getDictMap() {
		return dictMap;
	}
	public void setDictMap(Map<String, String> dictMap) {
		this.dictMap = dictMap;
	}
	public Map<String, String> getIndustryMap() {
		return industryMap;
	}
	public void setIndustryMap(Map<String, String> industryMap) {
		this.industryMap = industryMap;
	}
	
	public Map<String, String> getEntryMap() {
		return entryMap;
	}
	public void setEntryMap(Map<String, String> entryMap) {
		this.entryMap = entryMap;
	}
	@Override
	public String toString() {
		return "SearchFilterModel [industryMap=" + industryMap + ", entryMap=" + entryMap + ", dictMap=" + dictMap
				+ "]";
	}
	
}
