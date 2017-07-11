package com.fireCloud.tradCity.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fireCloud.tradCity.apiprivilege.ApiPrivilegeComponent;
import com.fireCloud.tradCity.apiprivilege.model.ApiPrivilege;
import com.fireCloud.tradCity.common.model.MemberIndustryModel;
import com.fireCloud.tradCity.common.service.CommonService;
import com.fireCloud.tradCity.constants.CacheConstants;
import com.fireCloud.tradCity.constants.LoggerConstants;
import com.fireCloud.tradCity.log.SysLogger;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;
import com.fireCloud.tradCity.member.service.MemberService;
import com.fireCloud.tradCity.popularize.service.PopularizeService;
import com.fireCloud.tradCity.util.LuceneUtil;


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
	ApiPrivilegeComponent apiPrivilegeComponent;
	
	@Resource
	SysLogger sysLogger;
	
	@Resource
	MemberService memberService;
	
	@PostConstruct
	private void init(){
		sysLogger.info(LoggerConstants.SYS_INIT, "开始！！！！！！");
		//加载首页推广缓存
		loadIndexPopularize();
		
		//加载导航类缓存
		loadIndexNavigation();
		
		//建lucene索引
		createIndex();
		
		//加载API权限缓存
//		loadApiPrivilege();
		
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
		List<MemberIndustryModel> list = new ArrayList<MemberIndustryModel>();
		try {
			list = commonService.queryMemberCategory();
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.SYS_INIT, "获取行业分类出错！！！！！！", e);
		}
		sysCache.put(CacheConstants.INDEX_NAVIGATION_CACHE, list);
		
	}
	
	private void loadApiPrivilege(){
		List<ApiPrivilege> list = new ArrayList<ApiPrivilege>();
		Map<String,String> apiMap = new HashMap<String,String>();
		try {
			list = apiPrivilegeComponent.queryAll();
			for(ApiPrivilege model : list){
				apiMap.put(model.getUserName(), model.getPublicKey());
			}
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.SYS_INIT, "获取API权限出错！！！！！！", e);
		}
		sysCache.put(CacheConstants.API_PRIVILEGE, apiMap);
	}
	
	private void createIndex(){
		List<SimpleMemberInfoModel> list = memberService.queryMember();
		if(list != null && list.size() > 0){
			LuceneUtil.getInstance().writerMemberIndx(list);
		}
	}
}
