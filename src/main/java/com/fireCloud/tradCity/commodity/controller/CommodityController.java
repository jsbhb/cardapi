package com.fireCloud.tradCity.commodity.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fireCloud.tradCity.basic.model.CallBackModel;
import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryAModel;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryBModel;
import com.fireCloud.tradCity.commodity.model.CommodityCategoryCModel;
import com.fireCloud.tradCity.commodity.model.CommodityModel;
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
	public static final String PARAMETER_ERROR_MSG = "请求参数有误，请确认后重试";

	@Resource
	SysLogger sysLogger;

	@Resource
	CommodityService commodityService;

	//首页上的按分类和按名称检索接口
	@RequestMapping(value = "/{version}/commoditys", method = RequestMethod.GET)
	public CallBackModel getCommoditys(@PathVariable("version") Double version, CommodityModel commodity,
			Pagination pagination, HttpServletRequest req, HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				//获取前台传递过来的参数
				Map<String, Object> searchItems = new HashMap<String, Object>();
				
				//将查询条件放入map
				searchItems.put("commodity", commodity);
				
				//封装查询结果
				Map<String, Object> resultMap = commodityService.getCommodityBySearch(searchItems, pagination);
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
	@RequestMapping(value = "/{version}/commoditys/{id}", method = RequestMethod.GET)
	public CallBackModel getCommoditysById(@PathVariable("version") Double version, @PathVariable("id") Integer id, CommodityModel commodity,
			HttpServletRequest req,	HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				//获取前台传递过来的参数
				Map<String, Object> searchItems = new HashMap<String, Object>();
				
				//将查询条件放入map
				searchItems.put("commodityId", id);
				searchItems.put("commodity", commodity);
				
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
	@RequestMapping(value = "/{version}/{memberId}/commoditys", method = RequestMethod.GET)
	public CallBackModel getCommoditysByMemberId(@PathVariable("version") Double version, @PathVariable("memberId") Integer memberId, CommodityModel commodity,
			HttpServletRequest req,	HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				//获取前台传递过来的参数
				Map<String, Object> searchItems = new HashMap<String, Object>();
				
				//将查询条件放入map
				searchItems.put("memberId", memberId);
				searchItems.put("commodity", commodity);
				
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
	
	//新增商品一级类目
	@RequestMapping(value = "/{version}/commoditys/addCategoryA", method = RequestMethod.POST)
	public CallBackModel addCategoryA(@PathVariable("version") Double version, HttpServletRequest req,
			CommodityCategoryAModel commodityCategoryA, HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				if(commodityCategoryA.getCategoryName() != null) {
				    //执行新增操作&封装返回结果
					Map<String, Object> resultMap = commodityService.addCommodityCategoryA(commodityCategoryA);
					model.setSuccess(true);
					model.setObj(resultMap);
				} else {
					model.setSuccess(false);
					model.setMsg(PARAMETER_ERROR_MSG);
				}
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.ADD_COMMODITY_CATEGORY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//查询商品一级类目
	@RequestMapping(value = "/{version}/commoditys/categoryA", method = RequestMethod.GET)
	public CallBackModel getCategoryA(@PathVariable("version") Double version, @PathVariable("id") Integer id, HttpServletRequest req,
			CommodityCategoryAModel commodityCategoryA, HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				if(commodityCategoryA.getId() != null) {
					Map<String, Object> searchItems = new HashMap<String, Object>();
					
					//将条件放入map
					searchItems.put("id", commodityCategoryA.getId());
					//封装查询结果
					Map<String, Object> resultMap = commodityService.getCommodityCategoryAById(searchItems);
					model.setSuccess(true);
					model.setObj(resultMap);
				} else {
					model.setSuccess(false);
					model.setMsg(PARAMETER_ERROR_MSG);
				}
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.ADD_COMMODITY_CATEGORY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//新增商品二级类目
	@RequestMapping(value = "/{version}/commoditys/addCategoryB", method = RequestMethod.POST)
	public CallBackModel addCategoryB(@PathVariable("version") Double version, HttpServletRequest req,
			CommodityCategoryBModel commodityCategoryB, HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				if(commodityCategoryB.getCategoryName() != null) {
				    //执行新增操作&封装返回结果
					Map<String, Object> resultMap = commodityService.addCommodityCategoryB(commodityCategoryB);
					model.setSuccess(true);
					model.setObj(resultMap);
				} else {
					model.setSuccess(false);
					model.setMsg(PARAMETER_ERROR_MSG);
				}
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.ADD_COMMODITY_CATEGORY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//查询商品二级类目
	@RequestMapping(value = "/{version}/commoditys/categoryB", method = RequestMethod.GET)
	public CallBackModel getCategoryB(@PathVariable("version") Double version, HttpServletRequest req,
			CommodityCategoryBModel commodityCategoryB, HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				if(commodityCategoryB.getId() != null) {
					Map<String, Object> searchItems = new HashMap<String, Object>();
					
					//将条件放入map
					searchItems.put("id", commodityCategoryB);
					//封装查询结果
					Map<String, Object> resultMap = commodityService.getCommodityCategoryBById(searchItems);
					model.setSuccess(true);
					model.setObj(resultMap);
				} else {
					model.setSuccess(false);
					model.setMsg(PARAMETER_ERROR_MSG);
				}
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.ADD_COMMODITY_CATEGORY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//新增商品三级类目
	@RequestMapping(value = "/{version}/commoditys/addCategoryC", method = RequestMethod.POST)
	public CallBackModel addCategoryC(@PathVariable("version") Double version, HttpServletRequest req,
			CommodityCategoryCModel commodityCategoryC, HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				if(commodityCategoryC.getCategoryName() != null) {
				    //执行新增操作&封装返回结果
					Map<String, Object> resultMap = commodityService.addCommodityCategoryC(commodityCategoryC);
					model.setSuccess(true);
					model.setObj(resultMap);
				} else {
					model.setSuccess(false);
					model.setMsg(PARAMETER_ERROR_MSG);
				}
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.ADD_COMMODITY_CATEGORY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//查询商品三级类目
	@RequestMapping(value = "/{version}/commoditys/categoryC", method = RequestMethod.GET)
	public CallBackModel getCategoryC(@PathVariable("version") Double version, HttpServletRequest req,
			CommodityCategoryCModel commodityCategoryC, HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				if(commodityCategoryC.getId() != null) {
					Map<String, Object> searchItems = new HashMap<String, Object>();
					
					//将条件放入map
					searchItems.put("id", commodityCategoryC);
					//封装查询结果
					Map<String, Object> resultMap = commodityService.getCommodityCategoryCById(searchItems);
					model.setSuccess(true);
					model.setObj(resultMap);
				} else {
					model.setSuccess(false);
					model.setMsg(PARAMETER_ERROR_MSG);
				}
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.ADD_COMMODITY_CATEGORY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//新增商品
	@RequestMapping(value = "/{version}/commoditys/addCommodity", method = RequestMethod.POST)
	public CallBackModel addCommodity(@PathVariable("version") Double version, HttpServletRequest req,
			HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				//获取前台传递过来的参数
				Map<String, Object> addItems = new HashMap<String, Object>();
				String categoryName = req.getParameter("categoryName");

				//将条件放入map
				addItems.put("categoryName", categoryName);
				
			    //执行更新操作
			    
				//封装返回结果
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.SEARCH_COMMODITY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//根据前台参数进行商品维护
	@RequestMapping(value = "/{version}/commoditys/maintain", method = RequestMethod.GET)
	public CallBackModel updCommoditysMaintain(@PathVariable("version") Double version, HttpServletRequest req,
			HttpServletResponse res) {
		//定义返回结果的model
		CallBackModel model = new CallBackModel();
		//增加版本控制，后期版本升级可以兼容
		if(ConfigConstants.FIRST_VERSION.equals(version)){
			try {
				//获取前台传递过来的参数形成map
			    Map<String, Object> updItems = retConverMap(req.getParameterMap());
				
			    //执行更新操作
			    
				//封装返回结果
//				Map<String, Object> resultMap = commodityService.getCommodityByMemberId(updItems);
//				model.setSuccess(true);
//				model.setObj(resultMap);
				
			} catch (Exception e) {
				sysLogger.error(LoggerConstants.SEARCH_COMMODITY, "出错！！！！！！", e);
				model.setSuccess(false);
				model.setMsg(ERROR_MSG);
			}
		}
		return model;
	}
	
	//将前台传递的json串转换成查询需要的map
	@SuppressWarnings({ "unchecked", "rawtypes" })  
	public static Map<String, Object> retConverMap(Map properties) {
		Map<String, Object> items = new HashMap<String, Object>();
	    Map returnMap = new HashMap();  
	    Iterator entries = properties.entrySet().iterator();  
	    Map.Entry entry;  
	    String name = "";  
	    String value = "";  
	    while (entries.hasNext()) {  
	        entry = (Map.Entry) entries.next();  
	        name = (String) entry.getKey();  
	        Object valueObj = entry.getValue();  
	        if(null == valueObj){  
	            value = "";  
	        }else if(valueObj instanceof String[]){  
	            String[] values = (String[])valueObj;  
	            for(int i=0;i<values.length;i++){  
	                value = values[i] + ",";  
	            }  
	            value = value.substring(0, value.length()-1);  
	        }else{  
	            value = valueObj.toString();  
	        }  
	        returnMap.put(name, value);  
	    }
	    
	    //将结果放入map中
	    for (Object obj : returnMap.values()) {
	    	String tmpStr = obj.toString().substring(1, obj.toString().length()-1).replace("\"", "");
	    	String[] tmpArray = tmpStr.split(",");
	    	for (String index : tmpArray) {
	    		items.put(index.split(":")[0], index.split(":")[1]);
	        }
	    }
	    
	    return items;
	}
}
