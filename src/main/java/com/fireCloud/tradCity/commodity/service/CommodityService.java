package com.fireCloud.tradCity.commodity.service;

import java.util.List;
import java.util.Map;

import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.basic.model.SortModelList;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryAModel;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryBModel;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryCModel;
import com.fireCloud.tradCity.commodity.model.CommodityModel;

/**
 * 
 * @author wqy
 * @fun 获取商品服务的service（包括分类检索和名称检索）
 * @date 2017/05/31
 */
public interface CommodityService {

	//首页上的按分类和按名称检索接口
 	Map<String,Object> getCommodityBySearch(Map<String, Object> searchItems, Pagination pagination);
 	
 	//采用lucene查询
 	Map<String,Object> getCommodityBySearch(CommodityModel commodity, SortModelList sortList, Pagination pagination);
	
	//根据商品ID进行查询
	Map<String,Object> getCommodityByCommodityId(Map<String, Object> searchItems, Pagination pagination);
	
	//根据商家ID进行查询
	Map<String,Object> getCommodityByMemberId(Map<String, Object> searchItems, Pagination pagination);
	
	//新增商品的一级类目
	Map<String,Object> addCommodityCategoryA(CommodityCategoryAModel commodityCategoryA);
	
	//根据一级类目ID进行查询
	Map<String,Object> getCommodityCategoryAById(Map<String, Object> searchItems);
	
	//新增商品的二级类目
	Map<String,Object> addCommodityCategoryB(CommodityCategoryBModel commodityCategoryB);
	
	//根据二级类目ID进行查询
	Map<String,Object> getCommodityCategoryBById(Map<String, Object> searchItems);
	
	//新增商品的三级类目
	Map<String,Object> addCommodityCategoryC(CommodityCategoryCModel commodityCategoryC);
	
	//根据三级类目ID进行查询
	Map<String,Object> getCommodityCategoryCById(Map<String, Object> searchItems);
	
	//新增商品
	String addCommodityInfo(Map<String, Object> addItems);
	
	List<CommodityModel> queryInitCommodity();
	
	void updateLuceneIndex();
}
