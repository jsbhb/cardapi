package com.fireCloud.tradCity.common.service;

import java.util.List;

import com.fireCloud.tradCity.common.model.MemberIndustryModel;

/**
 * @author wqy
 * @fun 通用服务接口
 * 		1、获取行业分类信息
 * @date 2017年6月8日
 */
public interface CommonService {

	/**
	 * @fun 获取行业分类
	 * @return
	 */
	List<MemberIndustryModel> queryMemberCategory();
}
