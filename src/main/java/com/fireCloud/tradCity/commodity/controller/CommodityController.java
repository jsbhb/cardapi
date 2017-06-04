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

	@RequestMapping(value = "/{version}/commodity/goodsSearch", method = RequestMethod.POST)
	public CallBackModel goodsSearch(@PathVariable("version") Double version, HttpServletRequest req,
			HttpServletResponse res) {
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			res.setHeader(ConfigConstants.CROSS_DOMAIN, ConfigConstants.DOMAIN_NAME);
			try {
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
				
				searchItems.put("commodityCategory1", commodityCategory1);
				searchItems.put("commodityCategory2", commodityCategory2);
				searchItems.put("commodityCategory3", commodityCategory3);
				searchItems.put("commodityName", commodityName);
				searchItems.put("brand", brand);
				searchItems.put("color", color);
				searchItems.put("size", size);
				searchItems.put("priceMin", priceMin);
				searchItems.put("priceMax", priceMax);
				
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
}
