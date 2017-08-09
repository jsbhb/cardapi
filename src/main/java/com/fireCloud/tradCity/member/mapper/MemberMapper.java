package com.fireCloud.tradCity.member.mapper;

import java.util.List;
import java.util.Map;

import com.fireCloud.tradCity.member.model.MemberInfoModel;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;


/**
 * @author wqy
 * @fun 获取会员信息数据
 * @date 2017年6月5日
 */
public interface MemberMapper {
	
	List<SimpleMemberInfoModel> querySimpleMember(Map<String,Object> parma);
	
	MemberInfoModel queryMemberDetail(Integer memberId);
	
	List<String> queryMemberFileId(Integer memberId);
	
	Integer queryCount(Map<String, Object> searchItems);
	
	List<SimpleMemberInfoModel> queryMember();
	
	void updateLuceneIndex();
	
	void updateLuceneIndexById(Integer member);
}