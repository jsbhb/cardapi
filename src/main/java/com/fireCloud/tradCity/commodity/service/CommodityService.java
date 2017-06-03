package com.fireCloud.tradCity.commodity.service;

import java.util.Map;

/**
 * 
 * @author wqy
 * @fun 获取商品服务的service（包括分类检索和名称检索）
 * @date 2017/05/31
 */
public interface CommodityService {

	Map<String,Object> getCommodityBySearch(Map<String, Object> searchItems);
}
