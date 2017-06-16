package com.fireCloud.tradCity.account.model;

/**
 * @author wqy
 * @fun 登录model
 * @date 2017年6月15日
 */
public class LoginModel {

	private Integer id;
	
	private String userName;
	
	private String passWord;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "LoginModel [id=" + id + ", userName=" + userName + ", passWord=" + passWord + "]";
	}
	
}
