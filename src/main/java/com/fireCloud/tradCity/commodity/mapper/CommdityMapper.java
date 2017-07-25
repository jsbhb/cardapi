package com.fireCloud.tradCity.commodity.mapper;

import java.util.List;
import java.util.Map;

import com.fireCloud.tradCity.commodity.model.CommodityCategoryAModel;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryBModel;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryCModel;
import com.fireCloud.tradCity.commodity.model.CommodityModel;
import com.fireCloud.tradCity.commodity.model.CommoditySearchModel;
import com.fireCloud.tradCity.commodity.model.CommodityShowModel;
/**
 * 
 * @author wqy
 * @fun 获取商品服务的数据
 */
public interface CommdityMapper {
	
	List<CommoditySearchModel> queryCommodity(Map<String, Object> searchItems);
	
	void insCommodityCategoryA(CommodityCategoryAModel commodityCategoryA);
	
	List<CommodityCategoryAModel> queryCommodityCategoryA(Map<String, Object> searchItems);
	
	void insCommodityCategoryB(CommodityCategoryBModel commodityCategoryB);
	
	List<CommodityCategoryBModel> queryCommodityCategoryB(Map<String, Object> searchItems);
	
	void insCommodityCategoryC(CommodityCategoryCModel commodityCategoryC);
	
	List<CommodityCategoryCModel> queryCommodityCategoryC(Map<String, Object> searchItems);
	
	void insCommodity(CommodityModel commodity);
	
	void insCommodityShow(CommodityShowModel commodityShow);
	
	Integer queryCommodityCount(Map<String, Object> searchItems);
	
	List<CommodityModel> queryInitCommodity();
	
	void updateLuceneIndex();
	
	CommoditySearchModel queryCommodityModel(String id);
}