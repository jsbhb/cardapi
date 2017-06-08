package com.fireCloud.tradCity.commodity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fireCloud.tradCity.basic.model.CallBackModel;
import com.fireCloud.tradCity.commodity.service.CommodityService;
import com.fireCloud.tradCity.constants.ConfigConstants;
import com.fireCloud.tradCity.constants.LoggerConstants;
import com.fireCloud.tradCity.log.SysLogger;

/**
 * 
 * @author wqy
 * @fun 商品服务的统一API入口
 * @date 2017/05/31
 */
@RestController
public class CommodityController {

	public static final String ERROR_MSG = "后台出现错误，请联系客服或管理员";

	@Resource
	SysLogger sysLogger;

	@Resource
	CommodityService commodityService;

	//首页上的按分类和按名称检索接口
	@RequestMapping(value = "/{version}/commoditys", method = RequestMethod.GET)
	public CallBackModel getCommoditys(@PathVariable("version") Double version, HttpServletRequest req,
			HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			res.setHeader(ConfigConstants.CROSS_DOMAIN, ConfigConstants.DOMAIN_NAME);
			try {
				//获取前台传递过来的参数
				Map<String, Object> searchItems = new HashMap<String, Object>();
				String commodityCategory1 = req.getParameter("commodityCategory1");
				String commodityCategory2 = req.getParameter("commodityCategory2");
				String commodityCategory3 = req.getParameter("commodityCategory3");
				String commodityName = req.getParameter("commodityName");
				String brand = req.getParameter("brand");
				String color = req.getParameter("color");
				String size = req.getParameter("size");
				String priceMin = req.getParameter("priceMin");
				String priceMax = req.getParameter("priceMax");
				
				//将查询条件放入map
				searchItems.put("commodityCategory1", commodityCategory1);
				searchItems.put("commodityCategory2", commodityCategory2);
				searchItems.put("commodityCategory3", commodityCategory3);
				searchItems.put("commodityName", commodityName);
				searchItems.put("brand", brand);
				searchItems.put("color", color);
				searchItems.put("size", size);
				searchItems.put("priceMin", priceMin);
				searchItems.put("priceMax", priceMax);
				
				//封装查询结果
				Map<String, Object> resultMap = commodityService.getCommodityBySearch(searchItems);
				model.setSuccess(true);
				model.setObj(resultMap);
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.SEARCH_COMMODITY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//根据查询的结果进行排序
	@RequestMapping(value = "/{version}/commoditys/sort", method = RequestMethod.GET)
	public CallBackModel getCommoditysSort(@PathVariable("version") Double version, HttpServletRequest req,
			HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			res.setHeader(ConfigConstants.CROSS_DOMAIN, ConfigConstants.DOMAIN_NAME);
			try {
				//获取前台传递过来的参数
				Map<String, Object> searchItems = new HashMap<String, Object>();
				String commodityCategory1 = req.getParameter("commodityCategory1");
				String commodityCategory2 = req.getParameter("commodityCategory2");
				String commodityCategory3 = req.getParameter("commodityCategory3");
				String commodityName = req.getParameter("commodityName");
				String brand = req.getParameter("brand");
				String color = req.getParameter("color");
				String size = req.getParameter("size");
				String priceMin = req.getParameter("priceMin");
				String priceMax = req.getParameter("priceMax");
				//排序条件
				String hotUp = req.getParameter("hotUp");
				String hotDown = req.getParameter("hotDown");
				String priceUp = req.getParameter("priceUp");
				String priceDown = req.getParameter("priceDown");
				String createTimeUp = req.getParameter("createTimeUp");
				String createTimeDown = req.getParameter("createTimeDown");
				
				//将查询条件放入map
				searchItems.put("commodityCategory1", commodityCategory1);
				searchItems.put("commodityCategory2", commodityCategory2);
				searchItems.put("commodityCategory3", commodityCategory3);
				searchItems.put("commodityName", commodityName);
				searchItems.put("brand", brand);
				searchItems.put("color", color);
				searchItems.put("size", size);
				searchItems.put("priceMin", priceMin);
				searchItems.put("priceMax", priceMax);
				//排序条件
				searchItems.put("hotUp", hotUp);
				searchItems.put("hotDown", hotDown);
				searchItems.put("priceUp", priceUp);
				searchItems.put("priceDown", priceDown);
				searchItems.put("createTimeUp", createTimeUp);
				searchItems.put("createTimeDown", createTimeDown);
				
				//封装查询结果
				Map<String, Object> resultMap = commodityService.getCommodityBySearchSort(searchItems);
				model.setSuccess(true);
				model.setObj(resultMap);
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.SEARCH_COMMODITY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//根据商品ID进行数据查询
	@RequestMapping(value = "/{version}/commoditys/id", method = RequestMethod.GET)
	public CallBackModel getCommoditysById(@PathVariable("version") Double version, HttpServletRequest req,
			HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			res.setHeader(ConfigConstants.CROSS_DOMAIN, ConfigConstants.DOMAIN_NAME);
			try {
				//获取前台传递过来的参数
				Map<String, Object> searchItems = new HashMap<String, Object>();
				String commodityId = req.getParameter("commodityId");
				
				//将查询条件放入map
				searchItems.put("commodityId", commodityId);
				
				//封装查询结果
				Map<String, Object> resultMap = commodityService.getCommodityByCommodityId(searchItems);
				model.setSuccess(true);
				model.setObj(resultMap);
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.SEARCH_COMMODITY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//根据商家ID进行数据查询
	@RequestMapping(value = "/{version}/commoditys/memberid", method = RequestMethod.GET)
	public CallBackModel getCommoditysByMemberId(@PathVariable("version") Double version, HttpServletRequest req,
			HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			res.setHeader(ConfigConstants.CROSS_DOMAIN, ConfigConstants.DOMAIN_NAME);
			try {
				//获取前台传递过来的参数
				Map<String, Object> searchItems = new HashMap<String, Object>();
				String memberId = req.getParameter("memberId");
				
				//将查询条件放入map
				searchItems.put("memberId", memberId);
				
				//封装查询结果
				Map<String, Object> resultMap = commodityService.getCommodityByMemberId(searchItems);
				model.setSuccess(true);
				model.setObj(resultMap);
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.SEARCH_COMMODITY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
}
