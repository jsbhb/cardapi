package com.fireCloud.tradCity.apiprivilege.mapper;

import java.util.List;

import com.fireCloud.tradCity.apiprivilege.model.ApiPrivilege;


/**
 * @author wqy
 * @date 2017年6月5日
 */
public interface PrivilegeMapper {
	
	List<ApiPrivilege> queryAll();
	
}