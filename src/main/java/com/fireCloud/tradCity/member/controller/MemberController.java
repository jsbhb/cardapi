package com.fireCloud.tradCity.member.controller;

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
import com.fireCloud.tradCity.basic.model.SortModelList;
import com.fireCloud.tradCity.constants.ConfigConstants;
import com.fireCloud.tradCity.constants.LoggerConstants;
import com.fireCloud.tradCity.log.SysLogger;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;
import com.fireCloud.tradCity.member.service.MemberService;

/**
 * @author wqy
 * @fun 会员服务API统一入口
 * @date 2017年6月5日
 */
@RestController
public class MemberController {

	public final String ERROR_MSG = "会员服务后台出现错误，请联系客服或管理员";

	@Resource
	MemberService memberService;

	@Resource
	SysLogger sysLogger;

	@RequestMapping(value = "/{version}/members", method = RequestMethod.GET)
	public CallBackModel getSimpleMembers(@PathVariable("version") Double version, SimpleMemberInfoModel memberInfo,
			Pagination pagination, SortModelList sortList, HttpServletRequest req, HttpServletResponse res) {

		CallBackModel model = new CallBackModel();
		res.setHeader(ConfigConstants.CROSS_DOMAIN, ConfigConstants.DOMAIN_NAME);
		try {
			// 增加版本控制，后期版本升级可以兼容
			if (ConfigConstants.FIRST_VERSION.equals(version)) {
				Map<String, Object> resultMap = memberService.queryMember(memberInfo, sortList, pagination, true);
				model.setSuccess(true);
				model.setObj(resultMap);
			}
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.SEARCH_MEMBER, "模糊查询会员信息出错！！！！！！", e);
			model.setSuccess(false);
			model.setMsg(ERROR_MSG);
		}
		return model;
	}

	@RequestMapping(value = "/{version}/members/{id}", method = RequestMethod.GET)
	public CallBackModel getMemberInfo(@PathVariable("version") Double version, @PathVariable("id") Integer id,
			HttpServletRequest req, HttpServletResponse res) {
		
		CallBackModel model = new CallBackModel();
		res.setHeader(ConfigConstants.CROSS_DOMAIN, ConfigConstants.DOMAIN_NAME);
		try {
			if (ConfigConstants.FIRST_VERSION.equals(version)) {
				
			}
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.SEARCH_MEMBER, "根据ID查询会员信息出错！！！！！！", e);
			model.setSuccess(false);
			model.setMsg(ERROR_MSG);
		}
		return model;
	}
}
