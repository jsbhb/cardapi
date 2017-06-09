package com.fireCloud.tradCity.commodity.service;

import java.util.Map;

import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryAModel;

/**
 * 
 * @author wqy
 * @fun 获取商品服务的service（包括分类检索和名称检索）
 * @date 2017/05/31
 */
public interface CommodityService {

	//首页上的按分类和按名称检索接口
 	Map<String,Object> getCommodityBySearch(Map<String, Object> searchItems, Pagination pagination);
	
	//根据商品ID进行查询
	Map<String,Object> getCommodityByCommodityId(Map<String, Object> searchItems);
	
	//根据商家ID进行查询
	Map<String,Object> getCommodityByMemberId(Map<String, Object> searchItems);
	
	//新增商品的一级类目
	Map<String,Object> addCommodityCategoryA(CommodityCategoryAModel commodityCategoryA);
}
