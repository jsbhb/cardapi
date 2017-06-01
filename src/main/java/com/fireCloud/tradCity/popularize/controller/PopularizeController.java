package com.fireCloud.tradCity.popularize.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fireCloud.tradCity.basic.callback.model.CallBackModel;
import com.fireCloud.tradCity.log.SysLogger;
import com.fireCloud.tradCity.popularize.constants.Constants;
import com.fireCloud.tradCity.popularize.constants.LoggerConstants;
import com.fireCloud.tradCity.popularize.service.PopularizeService;

/**
 * 
 * @author wqy
 * @fun 推广服务的统一API入口
 * @time 2017/05/31
 */
@RestController
public class PopularizeController {

	@Resource
	PopularizeService popularizeService;
	
	@Resource
	SysLogger sysLogger;
	
	@RequestMapping(value="/popularizations/index",method=RequestMethod.GET)
	public CallBackModel indexPopularize(HttpServletRequest req,HttpServletResponse res){
		CallBackModel model = new CallBackModel();
		res.setHeader(Constants.CROSS_DOMAIN, Constants.DOMAIN_NAME);
		try {
			Map<String,Map<String,Object>> resultMap = popularizeService.getIndexPopularize();
			model.setSuccess(true);
			model.setObj(resultMap);
			
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.INDEX_POPULARIZE, "出错！！！！！！", e);
			model.setSuccess(false);
			model.setMsg(Constants.ERROR_MSG);
		}
		return model;
	} 
}
