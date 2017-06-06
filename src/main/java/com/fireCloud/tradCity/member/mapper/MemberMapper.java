package com.fireCloud.tradCity.member.mapper;

import java.util.Map;

import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;
import com.github.pagehelper.Page;


/**
 * @author wqy
 * @fun 获取会员信息数据
 * @date 2017年6月5日
 */
public interface MemberMapper {
	
	Page<SimpleMemberInfoModel> querySimpleMember(Map<String, Object> searchItems);
}