package com.fireCloud.tradCity.account.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fireCloud.tradCity.account.model.UserModel;
import com.fireCloud.tradCity.account.service.UserService;
import com.fireCloud.tradCity.basic.model.CallBackModel;
import com.fireCloud.tradCity.constants.Constants;
import com.fireCloud.tradCity.constants.LoggerConstants;
import com.fireCloud.tradCity.log.SysLogger;
import com.fireCloud.tradCity.util.JwtUtils;
import com.fireCloud.tradCity.util.MD5Util;

@RestController
public class UserController {

	private static final Long ONE_DAY = 24 * 3600 * 1000L;
	public final String ERROR_MSG = "账号服务后台出现错误，请联系客服或管理员";

	@Resource
	UserService userService;

	@Resource
	SysLogger sysLogger;

	@RequestMapping(value = "{version}/check", method = RequestMethod.GET)
	public CallBackModel check(@PathVariable("version") Double version, HttpServletRequest req,
			HttpServletResponse res) {

		res.setHeader(Constants.CROSS_DOMAIN, Constants.DOMAIN_NAME);
		CallBackModel model = new CallBackModel();
		try {
			// 增加版本控制，后期版本升级可以兼容
			if (Constants.FIRST_VERSION.equals(version)) {
				String name = req.getParameter("name");
				if(name == null){
					model.setSuccess(false);
					model.setMsg("名称为空");
					return model;
				}
				boolean isExist = userService.isExist(name);
				model.setSuccess(isExist);
			}
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.ACCOUNT_SERVICE, "检测是否已经存在出错！！！！！！", e);
			model.setSuccess(false);
			model.setMsg(ERROR_MSG);
		}
		return model;
	}

	@RequestMapping(value = "{version}/register", method = RequestMethod.POST)
	public CallBackModel register(@PathVariable("version") Double version, UserModel userModel, HttpServletRequest req,
			HttpServletResponse res) {

		res.setHeader(Constants.CROSS_DOMAIN, Constants.DOMAIN_NAME);
		CallBackModel model = new CallBackModel();
		try {
			// 增加版本控制，后期版本升级可以兼容
			if (Constants.FIRST_VERSION.equals(version)) {
				userModel.setPwd(MD5Util.MD5(userModel.getPwd()));
				userService.register(userModel);
				String token = JwtUtils.sign(userModel, ONE_DAY);
				res.setHeader("Set-Cookie", "token=" + token + ";Path=/;HttpOnly");
				model.setSuccess(true);
			}
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.ACCOUNT_SERVICE, "注册出错！！！！！！", e);
			model.setSuccess(false);
			model.setMsg(ERROR_MSG);
		}
		return model;
	}

	@RequestMapping(value = "{version}/login", method = RequestMethod.GET)
	public CallBackModel login(@PathVariable("version") Double version, UserModel userModel, HttpServletRequest req,
			HttpServletResponse res) {
		CallBackModel model = new CallBackModel();
		try {
			// 增加版本控制，后期版本升级可以兼容
			if (Constants.FIRST_VERSION.equals(version)) {
				userModel.setPwd(MD5Util.MD5(userModel.getPwd()));
				UserModel user = userService.login(userModel);
				if (user == null) {
					model.setSuccess(false);
					model.setMsg("密码错误");
					return model;
				}
				String token = JwtUtils.sign(user, ONE_DAY);
				res.setHeader("Set-Cookie", "token=" + token + ";Path=/;HttpOnly");
				model.setSuccess(true);
			}
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.ACCOUNT_SERVICE, "登录出错！！！！！！", e);
			model.setSuccess(false);
			model.setMsg(ERROR_MSG);
		}
		return model;
	}

	@RequestMapping(value = "{version}/user/information", method = RequestMethod.PUT)
	public CallBackModel get(@PathVariable("version") Double version, UserModel userModel, HttpServletRequest req,
			HttpServletResponse res) {
		CallBackModel model = new CallBackModel();
		try {
			// 增加版本控制，后期版本升级可以兼容
			if (Constants.FIRST_VERSION.equals(version)) {
				model.setSuccess(true);
				model.setMsg("test");
			}
		} catch (Exception e) {

		}
		return model;
	}
}
