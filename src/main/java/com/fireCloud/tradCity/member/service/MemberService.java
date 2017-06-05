package com.fireCloud.tradCity.member.service;

import java.util.List;
import java.util.Map;

import com.fireCloud.tradCity.basic.model.SortModel;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;

/**
 * @author wqy
 * @fun 会员服务类service
 * 		1、根据会员名称、行业、主营类目、地区、是否优质商家，是否平台担保等信息查询；根据入驻时间、信誉度排序
 * @date 2017年6月5日
 */
public interface MemberService {

	Map<String,Object> queryMember(SimpleMemberInfoModel memberInfo, List<SortModel> sortList);
}
