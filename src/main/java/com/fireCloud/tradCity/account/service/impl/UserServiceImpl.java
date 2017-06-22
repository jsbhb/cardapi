package com.fireCloud.tradCity.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fireCloud.tradCity.account.mapper.UserMapper;
import com.fireCloud.tradCity.account.model.UserModel;
import com.fireCloud.tradCity.account.service.UserService;
import com.fireCloud.tradCity.util.RegularUtil;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	UserMapper userMapper;
	
	@Override
	public boolean isExist(String name) {
		String type = RegularUtil.emailOrPhone(name);
		Map<String,String> parma = new HashMap<String,String>();
		Map<String,Object> finalParma = new HashMap<String,Object>();
		parma.put(type, name);
		finalParma.put("parma", parma);
		int exist = userMapper.isExist(finalParma);
		return exist > 0 ? false : true;
	}

	@Override
	public void register(UserModel user) {
		userMapper.addUser(user);
	}

	@Override
	public UserModel login(UserModel user) {
		String type = RegularUtil.emailOrPhone(user.getAccount());
		Map<String,String> parma = new HashMap<String,String>();
		Map<String,Object> finalParma = new HashMap<String,Object>();
		parma.put(type, user.getAccount());
		finalParma.put("parma", parma);
		return userMapper.queryUser(finalParma);
	}

	
}
