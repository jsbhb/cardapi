package com.fireCloud.tradCity.member.model;

import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfo;

/**
 * @author wqy
 * @fun 会员实体类
 * @date 2017年6月2日
 */
public class MemberInfo extends SimpleMemberInfo {

	// 会员对应的账号
	private Integer accountId;

	// 企业公告
	private String notice;
	// 企业简介
	private String aboutUs;
	//
	private String licensePath;
	//
	private String operator;
	//
	private String mobile;
	//
	private String email;
	//
	private String phone;
	// 多客服以逗号分开
	private String qq;
	//
	private String frontPicPath;
	// 入驻时间
	private String enterTime;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public String getLicensePath() {
		return licensePath;
	}

	public void setLicensePath(String licensePath) {
		this.licensePath = licensePath;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getFrontPicPath() {
		return frontPicPath;
	}

	public void setFrontPicPath(String frontPicPath) {
		this.frontPicPath = frontPicPath;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	@Override
	public String toString() {
		return super.toString() + "MemberInfo [accountId=" + accountId + ", notice=" + notice + ", aboutUs=" + aboutUs
				+ ", licensePath=" + licensePath + ", operator=" + operator + ", mobile=" + mobile + ", email=" + email
				+ ", phone=" + phone + ", qq=" + qq + ", frontPicPath=" + frontPicPath + ", enterTime=" + enterTime
				+ "]";
	}

}
