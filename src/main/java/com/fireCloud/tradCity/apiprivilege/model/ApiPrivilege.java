package com.fireCloud.tradCity.apiprivilege.model;

/**
 * @author wqy
 * @fun 有权限调用api的用户model
 * @date 2017年6月15日
 */
public class ApiPrivilege {
	
	private Integer id;

	private String userName;
	
	private String publicKey;

	public String getUserName() {
		return userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
}
