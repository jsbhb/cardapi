package com.fireCloud.tradCity.commodity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.commodity.mapper.CommdityMapper;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryAModel;
import com.fireCloud.tradCity.commodity.model.CommoditySearchModel;
import com.fireCloud.tradCity.commodity.service.CommodityService;
import com.fireCloud.tradCity.constants.LoggerConstants;
import com.fireCloud.tradCity.log.SysLogger;

@Service
public class CommodityServiceImpl implements CommodityService {

	@Resource
	CommdityMapper commdityMapper;

	@Resource
	SysLogger sysLogger;

	@Override
	public Map<String, Object> getCommodityBySearch(Map<String, Object> searchItems, Pagination pagination) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//调用sql获取查询结果
		List<CommoditySearchModel> commoditySearchList = commdityMapper.queryCommodity(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_SIZE,
				commoditySearchList == null ? "=====0" : "=====" + commoditySearchList.size());

		pagination.setTotalRows((long)commoditySearchList.size());
		pagination.init();
		//定义前台展示的数组
		ArrayList<String> commodityCategory2 = new ArrayList<String>();
		ArrayList<String> commodityCategory3 = new ArrayList<String>();
		ArrayList<String> brand = new ArrayList<String>();
		ArrayList<String> color = new ArrayList<String>();
		ArrayList<String> size = new ArrayList<String>();
		
		//如果查询结果不为空
		if (commoditySearchList != null && commoditySearchList.size() > 0) {
			for (CommoditySearchModel commodity : commoditySearchList) {
				//去重记录前台展示的信息
				if (!commodityCategory2.contains(commodity.getCommodityCategory2())) {
					commodityCategory2.add(commodity.getCommodityCategory2());
				}
				if (!commodityCategory3.contains(commodity.getCommodityCategory3())) {
					commodityCategory3.add(commodity.getCommodityCategory3());
				}
				if (!brand.contains(commodity.getBrand())) {
					brand.add(commodity.getBrand());
				}
				if (!color.contains(commodity.getColor())) {
					color.add(commodity.getColor());
				}
				if (!size.contains(commodity.getSize())) {
					size.add(commodity.getSize());
				}
			}
		}

		//将显示信息拼装好
		resultMap.put("brand", brand);
		resultMap.put("color", color);
		resultMap.put("size", size);
		//如果是按照分类进行查询，则不将分类信息返回前台
		if (searchItems.get("commodityCategory3") == null) {
			resultMap.put("commodityCategory2", commodityCategory2);
			resultMap.put("commodityCategory3", commodityCategory3);
		} else {
			resultMap.put("commodityCategory2", null);
			resultMap.put("commodityCategory3", null);
		}
		resultMap.put("commoditySearchList", commoditySearchList);
		resultMap.put("pagination", pagination.webListConverter());
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getCommodityByCommodityId(Map<String, Object> searchItems) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//调用sql获取查询结果
		List<CommoditySearchModel> commoditySearchList = commdityMapper.queryCommodity(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_SIZE,
				commoditySearchList == null ? "=====0" : "=====" + commoditySearchList.size());

		//将显示信息拼装好
		resultMap.put("commoditySearchList", commoditySearchList);
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getCommodityByMemberId(Map<String, Object> searchItems) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//调用sql获取查询结果
		List<CommoditySearchModel> commoditySearchList = commdityMapper.queryCommodity(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_SIZE,
				commoditySearchList == null ? "=====0" : "=====" + commoditySearchList.size());

		//将显示信息拼装好
		resultMap.put("commoditySearchList", commoditySearchList);
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> addCommodityCategoryA(CommodityCategoryAModel commodityCategoryA) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean InsertFlg = false;

		//调用sql获取查询结果
		Map<String, Object> searchItems = new HashMap<String, Object>();
		searchItems.put("categoryName", commodityCategoryA.getCategoryName());
		searchItems.put("status", commodityCategoryA.getStatus());
		List<CommodityCategoryAModel> commodityCategoryASearchList = commdityMapper.queryCommodityCategoryA(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_CATEGORY_A_SIZE,
				commodityCategoryASearchList == null ? "=====0" : "=====" + commodityCategoryASearchList.size());
		
		//判断新增的记录是否已经存在
		if (commodityCategoryASearchList.size() <= 0) {
			//执行新增操作
			sysLogger.info(LoggerConstants.ADD_COMMODITY_CATEGORY_A,LoggerConstants.OPEARTION_START);
			commdityMapper.insCommodityCategoryA(commodityCategoryA);
			sysLogger.info(LoggerConstants.ADD_COMMODITY_CATEGORY_A,LoggerConstants.OPEARTION_END);
			InsertFlg = true;
		}
		
		//调用sql获取查询结果
		List<CommodityCategoryAModel> commodityCategoryASearchList2 = commdityMapper.queryCommodityCategoryA(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_CATEGORY_A_SIZE,
				commodityCategoryASearchList2 == null ? "=====0" : "=====" + commodityCategoryASearchList2.size());

		//将显示信息拼装好
		if (InsertFlg) {
			resultMap.put("commodityCategoryASearchList", commodityCategoryASearchList2);
		} else {
			resultMap.put("commodityCategoryASearchList", commodityCategoryASearchList);
		}
		
		return resultMap;
	}
}
