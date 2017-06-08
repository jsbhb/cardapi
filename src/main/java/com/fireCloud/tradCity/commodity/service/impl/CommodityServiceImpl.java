package com.fireCloud.tradCity.commodity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fireCloud.tradCity.commodity.mapper.CommdityMapper;
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
	public Map<String, Object> getCommodityBySearch(Map<String, Object> searchItems) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//调用sql获取查询结果
		List<CommoditySearchModel> commoditySearchList = commdityMapper.queryCommodity(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_SIZE,
				commoditySearchList == null ? "=====0" : "=====" + commoditySearchList.size());

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
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getCommodityBySearchSort(Map<String, Object> searchItems) {
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
}
