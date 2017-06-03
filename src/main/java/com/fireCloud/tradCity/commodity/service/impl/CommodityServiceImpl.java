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
		
		List<CommoditySearchModel> commoditySearchList = commdityMapper.queryCommodity(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_SIZE,
				commoditySearchList == null ? "=====0" : "=====" + commoditySearchList.size());

		ArrayList<String> commodityCategory2 = new ArrayList<String>();
		ArrayList<String> commodityCategory3 = new ArrayList<String>();
		ArrayList<String> brand = new ArrayList<String>();
		ArrayList<String> color = new ArrayList<String>();
		ArrayList<String> size = new ArrayList<String>();
		if (commoditySearchList != null && commoditySearchList.size() > 0) {
			for (CommoditySearchModel commodity : commoditySearchList) {
				//add value to array
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

		resultMap.put("brand", brand);
		resultMap.put("color", color);
		resultMap.put("size", size);
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
}
