package com.fireCloud.tradCity.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fireCloud.tradCity.basic.SysCache;
import com.fireCloud.tradCity.constants.CacheConstants;
import com.fireCloud.tradCity.constants.ConfigConstants;
import com.fireCloud.tradCity.util.Base64Util;
import com.fireCloud.tradCity.util.RSAUtil;

public class ApiPrivilegeInterceptor extends HandlerInterceptorAdapter {

	@Resource
	SysCache sysCache;
	
	private static final Long TIME_OUT= 10 * 1000L;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		response.setHeader(ConfigConstants.CROSS_DOMAIN, ConfigConstants.DOMAIN_NAME);
		String publicKey = "";
		String sign = "";

		Long ServiceTime = System.currentTimeMillis();

		response.setCharacterEncoding("UTF-8");

		Map parameterMap = request.getParameterMap();
		// 参数排序
		if (parameterMap != null && parameterMap.size() > 0) {
			if (parameterMap.get("timestamp") == null) {
				writerError(response, "缺少时间戳");
				return false;
			}

			String CilentTime = ((String[]) parameterMap.get("timestamp"))[0];
			Long intervalTime = ServiceTime - Long.parseLong(CilentTime);
			if (intervalTime > TIME_OUT) {
				writerError(response, "api时间戳已经超时");
				return false;
			}

			if (parameterMap.get("apiUser") == null) {
				writerError(response,"缺少调用者ID");
				return false;
			}

			Map<String, String> param = new HashMap<String, String>();
			for (Iterator<String> iter = parameterMap.keySet().iterator(); iter.hasNext();) {
				String key = iter.next();
				String[] values = (String[]) parameterMap.get(key);
				String value = "";
				for (int i = 0; i < values.length; i++) {
					value = (i == values.length - 1) ? value + values[i] : value + values[i] + ",";
				}
				if (!"sign".equals(key)) {
					param.put(key, value);
				} else {
					sign = ((String[]) parameterMap.get("sign"))[0];
				}

			}
			
			Map<String, String> apiMap = ((Map<String, String>) sysCache.get(CacheConstants.API_PRIVILEGE));
			publicKey = apiMap.get(param.get("apiUser"));
			
			List<String> keyList = new ArrayList<>(param.keySet());
			Collections.sort(keyList);
			StringBuilder sb = new StringBuilder();
			for (String key : keyList) {
				sb.append(key);
				sb.append("=");
				sb.append(param.get(key));
			}
			byte[] content = sb.toString().getBytes("utf-8");

			if (publicKey == null || publicKey.length() == 0) {
				writerError(response, "缺少公钥，请确认是否在后台进行注册");
				return false;
			}
			// 校验签名
			boolean isOK = RSAUtil.verify(publicKey, Base64Util.decode(sign), content);
			if (isOK) {
				return true;
			} else {
				writerError(response, "验签错误");
				return false;
			}
		} else {
			writerError(response, "没有参数，请确认");
			return false;
		}

	}

	private void writerError(HttpServletResponse response,String msg) throws IOException {
		PrintWriter pw = response.getWriter();
		pw.println("{\"msg\":\""+ msg +"\"}");
		if (pw != null) {
			pw.close();
		}
	}
}
