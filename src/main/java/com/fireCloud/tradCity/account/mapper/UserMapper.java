package com.fireCloud.tradCity.account.mapper;

import java.util.Map;

import com.fireCloud.tradCity.account.model.UserModel;

public interface UserMapper {

	int isExist(Map<String,Object> parms);
	 
	UserModel queryUser(Map<String,Object> parms);
	
	void addUser(UserModel user);
}
