package com.fireCloud.tradCity.member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.basic.model.SortModel;
import com.fireCloud.tradCity.basic.model.SortModelList;
import com.fireCloud.tradCity.filemng.model.FileModel;
import com.fireCloud.tradCity.filemng.service.FileService;
import com.fireCloud.tradCity.member.mapper.MemberMapper;
import com.fireCloud.tradCity.member.model.MemberClassifyModel;
import com.fireCloud.tradCity.member.model.MemberInfoModel;
import com.fireCloud.tradCity.member.model.SearchFilterModel;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;
import com.fireCloud.tradCity.member.service.MemberService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

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
	private final String PAGINATION = "pagination";

	@Resource
	MemberMapper memberMapper;
	
	@Resource
	FileService fileService;

	@Override
	public Map<String, Object> queryMember(SimpleMemberInfoModel memberInfo, SortModelList sortList,
			Pagination pagination, boolean flag) {
		Map<String, Object> resultMap = new HashMap<String, Object>(16);
		Map<String, Object> parmsMap = new HashMap<String, Object>();
		PageHelper.startPage(pagination.getCurrentPage(), pagination.getNumPerPage(), flag);
		parmsMap.put(MEMBER, memberInfo);
		List<SortModel> sortListEntity = sortList.getSortList();
		if (sortListEntity != null && sortListEntity.size() > 0) {
			parmsMap.put(SORT_LIST, sortListEntity);
		}
		Page<SimpleMemberInfoModel> infoPage = memberMapper.querySimpleMember(parmsMap);
		if (infoPage != null && infoPage.size() > 0) {
			SearchFilterModel searchFilterModel = new SearchFilterModel();
			// 抽取前台搜索过滤条件
			extractFilterCondition(infoPage, searchFilterModel);
			resultMap.put(MEMBER_LIST, infoPage);
			resultMap.put(SEARCH_FILTER, searchFilterModel);
			resultMap.put(PAGINATION, pagination.webPageConverter(infoPage));
		}

		return resultMap;
	}

	/**
	 * @fun 将会员的行业和分类抽离出来，以便给前台更详细的查询条件,其他优质商家等通用信息前台直接写死
	 * @param infoPage
	 * @param searchFilterModel
	 */
	private void extractFilterCondition(Page<SimpleMemberInfoModel> infoPage, SearchFilterModel searchFilterModel) {
		Map<String, String> industryMap = new HashMap<String, String>();
		Map<String, String> categoryMap = new HashMap<String, String>();
		List<MemberClassifyModel> temp = null;
		for (SimpleMemberInfoModel info : infoPage) {
			temp = info.getMemberClassify();
			for (MemberClassifyModel model : temp) {
				industryMap.put(model.getIndustry(), model.getIndustryName());
				categoryMap.put(model.getCategory(), model.getCategoryName());
			}
		}
		searchFilterModel.setCategoryMap(categoryMap);
		searchFilterModel.setIndustryMap(industryMap);

	}

	@Override
	public MemberInfoModel queryMemberDetail(Integer memberId) {
		MemberInfoModel model = new MemberInfoModel();
		model = memberMapper.queryMemberDetail(memberId);
		
		List<String> fileList = memberMapper.queryMemberFileId(memberId);
		
		List<FileModel> fielModelList = new ArrayList<FileModel>();
		if(fileList != null && fileList.size() > 0){
			fielModelList = fileService.queryFileById(fileList);
		}
		
		model.setFileList(fielModelList);
		return model;
	}

}
