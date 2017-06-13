package com.fireCloud.tradCity.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fireCloud.tradCity.common.mapper.CommonMapper;
import com.fireCloud.tradCity.common.model.MemberCategoryModel;
import com.fireCloud.tradCity.common.model.MemberIndustryModel;
import com.fireCloud.tradCity.common.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Resource
	CommonMapper commonMapper;
	
	@Override
	public List<MemberIndustryModel> queryMemberCategory() {
		List<MemberIndustryModel> categoryList = new ArrayList<MemberIndustryModel>();
		categoryList = commonMapper.queryMemberCategory();
		return categoryList;
	}

}
