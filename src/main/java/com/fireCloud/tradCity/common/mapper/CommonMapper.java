package com.fireCloud.tradCity.common.mapper;

import java.util.List;

import com.fireCloud.tradCity.common.model.MemberIndustryModel;

/**
 * @author wqy
 * @fun 获取通用数据
 * @date 2017年6月8日
 */
public interface CommonMapper {

	List<MemberIndustryModel> queryMemberCategory();
}
