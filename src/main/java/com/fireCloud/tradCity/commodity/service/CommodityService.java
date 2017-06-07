package com.fireCloud.tradCity.commodity.service;

import java.util.Map;

/**
 * 
 * @author wqy
 * @fun 获取商品服务的service（包括分类检索和名称检索）
 * @date 2017/05/31
 */
public interface CommodityService {
	//首页上的按分类和按名称检索接口
	Map<String,Object> getCommodityBySearch(Map<String, Object> searchItems);
	
	//根据查询的结果进行排序
	Map<String,Object> getCommodityBySearchSort(Map<String, Object> searchItems);
	
	//根据查询的结果进行排序
	Map<String,Object> getCommodityByCommodityId(Map<String, Object> searchItems);
	
	//根据查询的结果进行排序
	Map<String,Object> getCommodityByMemberId(Map<String, Object> searchItems);
}
