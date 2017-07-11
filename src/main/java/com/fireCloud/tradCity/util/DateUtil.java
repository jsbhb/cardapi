package com.fireCloud.tradCity.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @fun 日期工具类
 * @author wqy
 *
 * @date 2017年7月10日
 */
public class DateUtil {
	
	public static long stringToLong(String strTime, String formatType) throws ParseException {
		Date date = stringToDate(strTime, formatType); // String类型转成date类型
		if (date == null) {
			return 0;
		} else {
			long currentTime = dateToLong(date); // date类型转成long类型
			return currentTime;
		}
	}

	public static long dateToLong(Date date) {
		return date.getTime();
	}

	public static Date stringToDate(String strTime, String formatType) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		Date date = null;
		try {
			if(strTime == null){
				return new Date();
			}
			date = formatter.parse(strTime);
		} catch (ParseException e) {
			try {
				date = formatter.parse("2017-06-29 14:37:36");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return date;
	}
}
