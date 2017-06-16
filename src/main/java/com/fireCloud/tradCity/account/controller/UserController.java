package com.fireCloud.tradCity.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fireCloud.tradCity.account.model.LoginModel;
import com.fireCloud.tradCity.basic.model.CallBackModel;
import com.fireCloud.tradCity.constants.ConfigConstants;
import com.fireCloud.tradCity.util.JwtUtils;

@RestController
public class UserController {

	private static final Long DAY = 24*3600*1000L;
	
	@RequestMapping(value="{version}/login", method = RequestMethod.GET)
	public CallBackModel login(@PathVariable("version") Double version, LoginModel loginModel, HttpServletRequest req, HttpServletResponse res){
		CallBackModel model = new CallBackModel();
		try {
			// 增加版本控制，后期版本升级可以兼容
			if (ConfigConstants.FIRST_VERSION.equals(version)) {
				String token = JwtUtils.sign(loginModel, DAY);
				res.setHeader("Set-Cookie", "token="+ token + ";Path=/;Max-Age=seconds;HttpOnly");  
				model.setSuccess(true);
				model.setMsg("测试成功");
			}
		}catch(Exception e){
			
		}
		return model;
	}
	
	@RequestMapping(value="{version}/user", method = RequestMethod.GET)
	public CallBackModel get(@PathVariable("version") Double version, LoginModel loginModel, HttpServletRequest req, HttpServletResponse res){
		CallBackModel model = new CallBackModel();
		try {
			// 增加版本控制，后期版本升级可以兼容
			if (ConfigConstants.FIRST_VERSION.equals(version)) {
				model.setSuccess(true);
				model.setMsg("test");
			}
		}catch(Exception e){
			
		}
		return model;
	}
}
