package com.fireCloud.tradCity.commodity.mapper;

import java.util.List;
import java.util.Map;

import com.fireCloud.tradCity.commodity.model.CommoditySearchModel;
/**
 * 
 * @author wqy
 * @fun 获取商品服务的数据
 */
public interface CommdityMapper {
	
	List<CommoditySearchModel> queryCommodity(Map<String, Object> searchItems);
}