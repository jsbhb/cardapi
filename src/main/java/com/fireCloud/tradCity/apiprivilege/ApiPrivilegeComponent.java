package com.fireCloud.tradCity.apiprivilege;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fireCloud.tradCity.apiprivilege.mapper.PrivilegeMapper;
import com.fireCloud.tradCity.apiprivilege.model.ApiPrivilege;

@Component
public class ApiPrivilegeComponent{

	@Resource
	PrivilegeMapper privilegeMapper;
	
	public List<ApiPrivilege> queryAll() {
		
		return privilegeMapper.queryAll();
	}

}
