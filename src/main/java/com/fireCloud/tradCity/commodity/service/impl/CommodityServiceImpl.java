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
import com.fireCloud.tradCity.commodity.model.CommodityCategoryBModel;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryCModel;
import com.fireCloud.tradCity.commodity.model.CommodityModel;
import com.fireCloud.tradCity.commodity.model.CommoditySearchModel;
import com.fireCloud.tradCity.commodity.model.CommodityShowModel;
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
		int retRows = 1;
		
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
		if (commoditySearchList.size() <= 0) {
			resultMap.put("commoditySearchList", null);
		} else {
			resultMap.put("commoditySearchList", commoditySearchList);
		}
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
		

		//调用sql获取查询结果
		searchItems.put("goodFlg", "1");
		List<CommoditySearchModel> memberGoodCommodityList = commdityMapper.queryCommodity(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_SIZE,
				commoditySearchList == null ? "=====0" : "=====" + commoditySearchList.size());

		//定义前台展示的数组
		ArrayList<String> commodityCategory2 = new ArrayList<String>();
		ArrayList<String> commodityCategory3 = new ArrayList<String>();
		
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
			}
		}
		
		//将显示信息拼装好
		resultMap.put("commoditySearchList", commoditySearchList);
		resultMap.put("commodityCategory2", commodityCategory2);
		resultMap.put("commodityCategory3", commodityCategory3);
		resultMap.put("memberGoodCommodityList", memberGoodCommodityList);
		
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
	
	@Override
	public Map<String, Object> getCommodityCategoryAById(Map<String, Object> searchItems) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<CommodityCategoryAModel> commodityCategoryASearchList = commdityMapper.queryCommodityCategoryA(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_CATEGORY_A_SIZE,
				commodityCategoryASearchList == null ? "=====0" : "=====" + commodityCategoryASearchList.size());

		//将显示信息拼装好
		resultMap.put("commodityCategoryASearchList", commodityCategoryASearchList);
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> addCommodityCategoryB(CommodityCategoryBModel commodityCategoryB) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean InsertFlg = false;

		//调用sql获取查询结果
		Map<String, Object> searchItems = new HashMap<String, Object>();
		searchItems.put("categoryName", commodityCategoryB.getCategoryName());
		searchItems.put("status", commodityCategoryB.getStatus());
		searchItems.put("categoryAId", commodityCategoryB.getCategoryAId());
		List<CommodityCategoryBModel> commodityCategoryBSearchList = commdityMapper.queryCommodityCategoryB(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_CATEGORY_B_SIZE,
				commodityCategoryBSearchList == null ? "=====0" : "=====" + commodityCategoryBSearchList.size());
		
		//判断新增的记录是否已经存在
		if (commodityCategoryBSearchList.size() <= 0) {
			//执行新增操作
			sysLogger.info(LoggerConstants.ADD_COMMODITY_CATEGORY_B,LoggerConstants.OPEARTION_START);
			commdityMapper.insCommodityCategoryB(commodityCategoryB);
			sysLogger.info(LoggerConstants.ADD_COMMODITY_CATEGORY_B,LoggerConstants.OPEARTION_END);
			InsertFlg = true;
		}
		
		//调用sql获取查询结果
		List<CommodityCategoryBModel> commodityCategoryBSearchList2 = commdityMapper.queryCommodityCategoryB(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_CATEGORY_B_SIZE,
				commodityCategoryBSearchList2 == null ? "=====0" : "=====" + commodityCategoryBSearchList2.size());

		//将显示信息拼装好
		if (InsertFlg) {
			resultMap.put("commodityCategoryASearchList", commodityCategoryBSearchList2);
		} else {
			resultMap.put("commodityCategoryASearchList", commodityCategoryBSearchList);
		}
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getCommodityCategoryBById(Map<String, Object> searchItems) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<CommodityCategoryBModel> commodityCategoryBSearchList = commdityMapper.queryCommodityCategoryB(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_CATEGORY_B_SIZE,
				commodityCategoryBSearchList == null ? "=====0" : "=====" + commodityCategoryBSearchList.size());

		//将显示信息拼装好
		resultMap.put("commodityCategoryBSearchList", commodityCategoryBSearchList);
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> addCommodityCategoryC(CommodityCategoryCModel commodityCategoryC) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean InsertFlg = false;

		//调用sql获取查询结果
		Map<String, Object> searchItems = new HashMap<String, Object>();
		searchItems.put("categoryName", commodityCategoryC.getCategoryName());
		searchItems.put("status", commodityCategoryC.getStatus());
		searchItems.put("categoryBId", commodityCategoryC.getCategoryBId());
		List<CommodityCategoryCModel> commodityCategoryCSearchList = commdityMapper.queryCommodityCategoryC(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_CATEGORY_C_SIZE,
				commodityCategoryCSearchList == null ? "=====0" : "=====" + commodityCategoryCSearchList.size());
		
		//判断新增的记录是否已经存在
		if (commodityCategoryCSearchList.size() <= 0) {
			//执行新增操作
			sysLogger.info(LoggerConstants.ADD_COMMODITY_CATEGORY_C,LoggerConstants.OPEARTION_START);
			commdityMapper.insCommodityCategoryC(commodityCategoryC);
			sysLogger.info(LoggerConstants.ADD_COMMODITY_CATEGORY_C,LoggerConstants.OPEARTION_END);
			InsertFlg = true;
		}
		
		//调用sql获取查询结果
		List<CommodityCategoryCModel> commodityCategoryCSearchList2 = commdityMapper.queryCommodityCategoryC(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_CATEGORY_C_SIZE,
				commodityCategoryCSearchList2 == null ? "=====0" : "=====" + commodityCategoryCSearchList2.size());

		//将显示信息拼装好
		if (InsertFlg) {
			resultMap.put("commodityCategoryASearchList", commodityCategoryCSearchList2);
		} else {
			resultMap.put("commodityCategoryASearchList", commodityCategoryCSearchList);
		}
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getCommodityCategoryCById(Map<String, Object> searchItems) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<CommodityCategoryCModel> commodityCategoryCSearchList = commdityMapper.queryCommodityCategoryC(searchItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_CATEGORY_C_SIZE,
				commodityCategoryCSearchList == null ? "=====0" : "=====" + commodityCategoryCSearchList.size());

		//将显示信息拼装好
		resultMap.put("commodityCategoryCSearchList", commodityCategoryCSearchList);
		
		return resultMap;
	}
	
	@Override
	public String addCommodityInfo(Map<String, Object> addItems) {
		String resultId = null;
		boolean InsertFlg = false;
		
		//调用sql获取查询结果
		List<CommoditySearchModel> commoditySearchList = commdityMapper.queryCommodity(addItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_SIZE,
				commoditySearchList == null ? "=====0" : "=====" + commoditySearchList.size());
		
		//判断新增的记录是否已经存在
		if (commoditySearchList.size() <= 0) {
			//执行新增操作
			sysLogger.info(LoggerConstants.ADD_COMMODITY,LoggerConstants.OPEARTION_START);
			commdityMapper.insCommodity((CommodityModel)addItems.get("commodity"));
			sysLogger.info(LoggerConstants.ADD_COMMODITY,LoggerConstants.OPEARTION_END);
			InsertFlg = true;
		}
		
		//调用sql获取查询结果
		List<CommoditySearchModel> commoditySearchList2 = commdityMapper.queryCommodity(addItems);
		sysLogger.info(LoggerConstants.SEARCH_COMMODITY_SIZE,
				commoditySearchList2 == null ? "=====0" : "=====" + commoditySearchList2.size());
		
		CommodityShowModel insShow = (CommodityShowModel)addItems.get("commodityShow");
		if (insShow.getCommodityIntroduction() != null) {
			//执行新增操作
			insShow.setCommodityId(commoditySearchList2.get(0).getCommodityId());
			sysLogger.info(LoggerConstants.ADD_COMMODITY_SHOW,LoggerConstants.OPEARTION_START);
			commdityMapper.insCommodityShow(insShow);
			sysLogger.info(LoggerConstants.ADD_COMMODITY_SHOW,LoggerConstants.OPEARTION_END);
			
			//调用sql获取查询结果
			commoditySearchList2 = commdityMapper.queryCommodity(addItems);
			sysLogger.info(LoggerConstants.SEARCH_COMMODITY_SIZE,
					commoditySearchList2 == null ? "=====0" : "=====" + commoditySearchList2.size());
		}

		//将返回信息拼装好
		if (InsertFlg) {
			resultId = commoditySearchList2.get(0).getCommodityId().toString();
		} else {
			resultId = commoditySearchList.get(0).getCommodityId().toString();
		}
		
		return resultId;
	}
}
