package com.fireCloud.tradCity.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

/**
 * @author wqy
 * @fun Cookie工具类
 * @date 2017年6月15日
 */
public class CookieUtils {

	public static Cookie[] getCookies(HttpServletRequest req){
		return req.getCookies();
	}
	
	public static String getValue(HttpServletRequest req,String key){
		Assert.notNull(key, "key 不能为null");
		Cookie[] cookies = getCookies(req);
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(key.equals(cookie.getName())){
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
