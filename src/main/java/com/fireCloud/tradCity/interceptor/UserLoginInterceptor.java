package com.fireCloud.tradCity.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fireCloud.tradCity.account.model.LoginModel;
import com.fireCloud.tradCity.util.CookieUtils;
import com.fireCloud.tradCity.util.JwtUtils;

public class UserLoginInterceptor extends HandlerInterceptorAdapter{

	private static final String KEY = "token";
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		
		res.setCharacterEncoding("utf-8");
		
		String token = CookieUtils.getValue(req, KEY);
		
		LoginModel model = JwtUtils.unsign(token, LoginModel.class);
		
		if(model == null){
			writerError(res, "token 非法或已经过期，请重新登录");
			return false;
		}
		return super.preHandle(req, res, obj);
	}

	
	private void writerError(HttpServletResponse response,String msg) throws IOException {
		PrintWriter pw = response.getWriter();
		pw.println("{\"msg\":\""+ msg +"\"}");
		if (pw != null) {
			pw.close();
		}
	}
}
