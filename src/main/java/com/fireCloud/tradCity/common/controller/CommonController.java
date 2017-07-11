package com.fireCloud.tradCity.common.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fireCloud.tradCity.basic.SysCache;
import com.fireCloud.tradCity.basic.model.CallBackModel;
import com.fireCloud.tradCity.common.model.MemberIndustryModel;
import com.fireCloud.tradCity.constants.CacheConstants;
import com.fireCloud.tradCity.constants.Constants;
import com.fireCloud.tradCity.constants.LoggerConstants;
import com.fireCloud.tradCity.log.SysLogger;

@RestController
public class CommonController {

	private final String ERROR_MSG = "公共后台出现错误，请联系客服或管理员";

	@Resource
	SysLogger sysLogger;

	@Resource
	SysCache sysCache;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{version}/commons/memberCategory", method = RequestMethod.GET)
	public CallBackModel getMemberCategory(@PathVariable("version") Double version, HttpServletRequest req,
			HttpServletResponse res) {

		CallBackModel model = new CallBackModel();
		res.setHeader(Constants.CROSS_DOMAIN, Constants.DOMAIN_NAME);
		try {
			if (Constants.FIRST_VERSION.equals(version)) {
				List<MemberIndustryModel> list = (List<MemberIndustryModel>) sysCache
						.get(CacheConstants.INDEX_NAVIGATION_CACHE);
				model.setObj(list);
				model.setSuccess(true);
			}
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.COMMON_MEMBER_CATEGORY, "获取行业分类出错！！！！！！", e);
			model.setSuccess(false);
			model.setMsg(ERROR_MSG);
		}
		return model;
	}
}
