package com.fireCloud.tradCity.util;

import com.fireCloud.tradCity.constants.Constants;

public class RegularUtil {
	
	private static final String EMAIL_REGEX = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
	private static final String MOBILE_REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
	
	public static String emailOrPhone(String str){
		if(str.matches(EMAIL_REGEX)){
			return Constants.EMAIL;
		}
		if(str.matches(MOBILE_REGEX)){
			return Constants.MOBILE;
		}
		return Constants.ACCOUNT;
	}
}
