package com.fireCloud.tradCity.member.service;

import java.util.Map;

import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.basic.model.SortModelList;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;

/**
 * @author wqy
 * @fun 会员服务类service 1、根据会员名称、行业、主营类目、地区、是否优质商家，是否平台担保等信息查询；根据入驻时间、信誉度排序
 * @date 2017年6月5日
 */
public interface MemberService {

	/**
	 * @fun 商城搜索会员，显示会员基本信息
	 * @param memberInfo 搜索会员信息参数
	 * @param sortList 排序对象
	 * @param pagination 分页对象
	 * @param flag 是否需要分页 
	 * @return 会员对象List和前台筛选条件对象SearchFilterModel
	 */
	Map<String, Object> queryMember(SimpleMemberInfoModel memberInfo, SortModelList sortList, Pagination pagination,
			boolean flag);
}
