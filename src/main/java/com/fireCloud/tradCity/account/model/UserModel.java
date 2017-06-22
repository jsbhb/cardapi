package com.fireCloud.tradCity.account.model;


/**
 * @author wqy
 * @fun 用户信息
 * @date 2017年6月20日
 */
public class UserModel{

	private String nickName;
	
	private Integer mobileValidate;
	
	private Integer emailValidate;
	
	private String name;
	//证件类型
	private Integer certificates;
	
	private String cardNum;
	
	private String lastLoginIP;
	
	private String lastLoginTime;
	
	private String IPCity;
	
	private Integer id;
	
	private String account;
	
	private String mobile;
	
	private String email;
	//登录/注册方式  0：自定义；1：手机；2：邮箱
	private Integer type;
	
	private String pwd;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getMobileValidate() {
		return mobileValidate;
	}

	public void setMobileValidate(Integer mobileValidate) {
		this.mobileValidate = mobileValidate;
	}

	public Integer getEmailValidate() {
		return emailValidate;
	}

	public void setEmailValidate(Integer emailValidate) {
		this.emailValidate = emailValidate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCertificates() {
		return certificates;
	}

	public void setCertificates(Integer certificates) {
		this.certificates = certificates;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getIPCity() {
		return IPCity;
	}

	public void setIPCity(String iPCity) {
		IPCity = iPCity;
	}

	
}
