package com.fireCloud.tradCity.member.service;

import java.util.List;
import java.util.Map;

import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.basic.model.SortModelList;
import com.fireCloud.tradCity.member.model.MemberInfoModel;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;

/**
 * @author wqy
 * @fun 会员服务类service 
 * 		1、根据会员名称、行业、主营类目、地区、是否优质商家，是否平台担保等信息查询；根据入驻时间、信誉度排序
 * 		2、根据memberId搜索会员详细信息
 * @date 2017年6月5日
 */
public interface MemberService {

	/**
	 * @fun 商城搜索会员，显示会员基本信息
	 * @param memberInfo 搜索会员信息参数
	 * @param sortList 排序对象
	 * @param pagination 分页对象
	 * @return 会员对象List和前台筛选条件对象SearchFilterModel
	 */
	Map<String, Object> queryMember(SimpleMemberInfoModel memberInfo, SortModelList sortList, Pagination pagination);
	
	/**
	 * @fun 根据memberId搜索会员详细信息
	 * @param memberId
	 * @return 会员详细信息
	 */
	MemberInfoModel queryMemberDetail(Integer memberId);
	
	/**
	 * @fun 查询所有创建索引
	 * @return
	 */
	List<SimpleMemberInfoModel> queryMember();
	
	/**
	 * @fun 更新lucene索引
	 */
	void updateLuceneIndex();
}
