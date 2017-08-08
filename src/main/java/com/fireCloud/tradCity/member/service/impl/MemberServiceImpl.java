package com.fireCloud.tradCity.member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.basic.model.SortModelList;
import com.fireCloud.tradCity.constants.Constants;
import com.fireCloud.tradCity.constants.LoggerConstants;
import com.fireCloud.tradCity.filemng.model.FileModel;
import com.fireCloud.tradCity.filemng.service.FileService;
import com.fireCloud.tradCity.log.SysLogger;
import com.fireCloud.tradCity.member.mapper.MemberMapper;
import com.fireCloud.tradCity.member.model.MemberInfoModel;
import com.fireCloud.tradCity.member.model.SearchFilterModel;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;
import com.fireCloud.tradCity.member.service.MemberService;
import com.fireCloud.tradCity.util.lucene.LuceneUtil;
import com.fireCloud.tradCity.util.lucene.impl.MemberLucene;

/**
 * @author wqy
 * @fun MemberService接口实现类
 * @date 2017年6月5日
 */
@Service
public class MemberServiceImpl implements MemberService {

	private final String MEMBER_LIST = "memberList";
	private final String SEARCH_FILTER = "searchFilter";
	private final String PAGINATION = "pagination";

	@Resource
	MemberMapper memberMapper;

	@Resource
	FileService fileService;

	@Resource
	SysLogger sysLogger;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryMember(SimpleMemberInfoModel memberInfo, SortModelList sortList,
			Pagination pagination) {
		Map<String, Object> resultMap = new HashMap<String, Object>(16);
		Map<String, Object> luceneMap = new HashMap<String, Object>();

		try {
			luceneMap = MemberLucene.getInstance().search(memberInfo, pagination, sortList);
		} catch (Exception e) {
			sysLogger.error(LoggerConstants.SEARCH_MEMBER, "lucene搜索出错:", e);
		}
		Integer total = (Integer) luceneMap.get(Constants.TOTAL);
		List<Integer> memberIdList = (List<Integer>) luceneMap.get(Constants.ID_LIST);
		pagination.setTotalRows((long) total);
		if (memberIdList != null && memberIdList.size() > 0) {
			List<SimpleMemberInfoModel> memberList = new ArrayList<SimpleMemberInfoModel>();
			SimpleMemberInfoModel model = null;
			// 设置高亮
			Map<Integer, SimpleMemberInfoModel> highlighterModel = (Map<Integer, SimpleMemberInfoModel>) luceneMap
					.get(Constants.HIGHLIGHTER_MODEL);
			for (Integer id : memberIdList) {
				model = memberMapper.querySimpleMember(id);
				if (highlighterModel != null && highlighterModel.size() > 0) {
					if (highlighterModel.get(id).getMemberName() != null
							&& !"".equals(highlighterModel.get(id).getMemberName())){
						
						model.setMemberName(highlighterModel.get(id).getMemberName());
					}
					if (highlighterModel.get(id).getProduct() != null
							&& !"".equals(highlighterModel.get(id).getProduct())){
						
						model.setProduct(highlighterModel.get(id).getProduct());
					}
				}
				memberList.add(model);
			}

			resultMap.put(MEMBER_LIST, memberList);
		}

		resultMap.put(PAGINATION, pagination.webListConverter());

		SearchFilterModel searchFilter = new SearchFilterModel();

		resultMap.put(SEARCH_FILTER, searchFilter);

		return resultMap;
	}

	@Override
	public MemberInfoModel queryMemberDetail(Integer memberId) {
		MemberInfoModel model = new MemberInfoModel();
		model = memberMapper.queryMemberDetail(memberId);

		List<String> fileList = memberMapper.queryMemberFileId(memberId);

		List<FileModel> fielModelList = new ArrayList<FileModel>();
		if (fileList != null && fileList.size() > 0) {
			fielModelList = fileService.queryFileById(fileList);
		}

		model.setFileList(fielModelList);
		return model;
	}

	@Override
	public List<SimpleMemberInfoModel> queryMember() {

		return memberMapper.queryMember();
	}

	@Override
	public void updateLuceneIndex() {
		
		memberMapper.updateLuceneIndex();
	}

}
