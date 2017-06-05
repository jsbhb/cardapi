package com.fireCloud.tradCity.member.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fireCloud.tradCity.basic.model.SortModel;
import com.fireCloud.tradCity.member.mapper.MemberMapper;
import com.fireCloud.tradCity.member.model.MemberClassifyModel;
import com.fireCloud.tradCity.member.model.SearchFilterModel;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;
import com.fireCloud.tradCity.member.service.MemberService;

/**
 * @author wqy
 * @fun MemberService接口实现类
 * @date 2017年6月5日
 */
@Service
public class MemberServiceImpl implements MemberService {

	private final String MEMBER = "member";
	private final String SORT_LIST = "sortList";
	private final String MEMBER_LIST = "memberList";
	private final String SEARCH_FILTER = "searchFilter";
	
	@Resource
	MemberMapper memberMapper;

	@Override
	public Map<String, Object> queryMember(SimpleMemberInfoModel memberInfo, List<SortModel> sortList) {
		Map<String, Object> resultMap = new HashMap<String, Object>(16);
		Map<String, Object> parmsMap = new HashMap<String, Object>();
		parmsMap.put(MEMBER, memberInfo);
		if(sortList != null && sortList.size() > 0){
			parmsMap.put(SORT_LIST, sortList);
		}
		List<SimpleMemberInfoModel> infoList = memberMapper.querySimpleMember(parmsMap);
		if (infoList != null && infoList.size() > 0) {
			SearchFilterModel searchFilterModel = new SearchFilterModel();
			//抽取前台搜索过滤条件
			extractFilterCondition(infoList, searchFilterModel);
			resultMap.put(MEMBER_LIST, infoList);
			resultMap.put(SEARCH_FILTER, searchFilterModel);
		}
		
		return resultMap;
	}

	private void extractFilterCondition(List<SimpleMemberInfoModel> infoList,
			SearchFilterModel searchFilterModel) {
		Map<String,String> industryMap = new HashMap<String,String>();
		Map<String,String> categoryMap = new HashMap<String,String>();
		List<MemberClassifyModel> temp = null;
		for (SimpleMemberInfoModel info : infoList) {
			temp = info.getMemberClassify();
			for (MemberClassifyModel model : temp) {
				industryMap.put(model.getIndustry(),model.getIndustryName());
				categoryMap.put(model.getCategory(),model.getCategoryName());
			}
		}
		searchFilterModel.setCategoryMap(categoryMap);
		searchFilterModel.setIndustryMap(industryMap);

	}

}
