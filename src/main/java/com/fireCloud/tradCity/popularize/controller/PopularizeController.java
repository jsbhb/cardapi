package com.fireCloud.tradCity.popularize.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fireCloud.tradCity.basic.SysCache;
import com.fireCloud.tradCity.basic.model.CallBackModel;
import com.fireCloud.tradCity.constants.CacheConstants;
import com.fireCloud.tradCity.constants.ConfigConstants;
import com.fireCloud.tradCity.constants.LoggerConstants;
import com.fireCloud.tradCity.log.SysLogger;

/**
 * 
 * @author wqy
 * @fun 推广服务的统一API入口
 * @date 2017/05/31
 */
@RestController
public class PopularizeController {

	public final String ERROR_MSG = "推广服务后台出现错误，请联系客服或管理员";

	@Resource
	SysLogger sysLogger;

	@Resource
	SysCache sysCache;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{version}/popularizations/index", method = RequestMethod.GET)
	public CallBackModel indexPopularize(@PathVariable("version") Double version, HttpServletRequest req,
			HttpServletResponse res) {
		CallBackModel model = new CallBackModel();
		res.setHeader(ConfigConstants.CROSS_DOMAIN, ConfigConstants.DOMAIN_NAME);
		try {
			// 增加版本控制，后期版本升级可以兼容
			if (ConfigConstants.FIRST_VERSION.equals(version)) {

				Map<String, Map<String, Object>> resultMap = (Map<String, Map<String, Object>>) sysCache
						.get(CacheConstants.POPULARIZE_CACHE);
				model.setSuccess(true);
				model.setObj(resultMap);
			}
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.INDEX_POPULARIZE, "出错！！！！！！", e);
			model.setSuccess(false);
			model.setMsg(ERROR_MSG);
		}
		return model;
	}
}
