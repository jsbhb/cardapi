package com.fireCloud.tradCity.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fireCloud.tradCity.common.model.MemberCategoryModel;
import com.fireCloud.tradCity.common.service.CommonService;
import com.fireCloud.tradCity.constants.CacheConstants;
import com.fireCloud.tradCity.constants.LoggerConstants;
import com.fireCloud.tradCity.log.SysLogger;
import com.fireCloud.tradCity.popularize.service.PopularizeService;


/**
 * @author wqy
 * @fun 系统初始化操作
 * @date 2017年6月2日
 */
@Component
public class SysInit {

	@Resource
	PopularizeService popularizeService;
	
	@Resource
	CommonService commonService;
	
	@Resource
	SysCache sysCache;
	
	@Resource
	SysLogger sysLogger;
	
	@PostConstruct
	private void init(){
		sysLogger.info(LoggerConstants.SYS_INIT, "开始！！！！！！");
		//加载首页推广缓存
		loadIndexPopularize();
		
		//加载导航类缓存
		loadIndexNavigation();
		
		sysLogger.info(LoggerConstants.SYS_INIT, "结束！！！！！！");
	}
	
	
	private void loadIndexPopularize(){
		Map<String,Map<String,Object>> resultMap = new HashMap<String,Map<String,Object>>();
		try {
			resultMap = popularizeService.getIndexPopularize();
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.SYS_INIT, "加载首页推广类服务出错", e);
		}
		
		sysCache.put(CacheConstants.POPULARIZE_CACHE, resultMap);
	}
	
	private void loadIndexNavigation(){
		List<MemberCategoryModel> list = new ArrayList<MemberCategoryModel>();
		try {
			list = commonService.queryMemberCategory();
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.SYS_INIT, "获取行业分类出错！！！！！！", e);
		}
		sysCache.put(CacheConstants.INDEX_NAVIGATION_CACHE, list);
		
	}
}
