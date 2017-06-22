package com.fireCloud.tradCity.account.service;

import com.fireCloud.tradCity.account.model.UserModel;

/**
 * @author wqy
 * @fun 用户服务
 * @date 2017年6月20日
 */
public interface UserService {

	boolean isExist(String name);
	
	void register(UserModel userModel);
	
	UserModel login(UserModel userModel);
}
