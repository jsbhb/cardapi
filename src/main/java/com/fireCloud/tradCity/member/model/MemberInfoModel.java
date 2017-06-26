package com.fireCloud.tradCity.member.model;

import java.util.List;

import com.fireCloud.tradCity.filemng.model.FileModel;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;

/**
 * @author wqy
 * @fun 会员实体类
 * @date 2017年6月2日
 */
public class MemberInfoModel extends SimpleMemberInfoModel {

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
	//
	private String fax;
	// 多客服以逗号分开
	private String qq;
	//
	private List<FileModel> fileList;

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

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

	public List<FileModel> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileModel> fileList) {
		this.fileList = fileList;
	}

	@Override
	public String toString() {
		return "MemberInfoModel [accountId=" + accountId + ", notice=" + notice + ", aboutUs=" + aboutUs
				+ ", licensePath=" + licensePath + ", operator=" + operator + ", mobile=" + mobile + ", email=" + email
				+ ", phone=" + phone + ", qq=" + qq + ", fileList=" + fileList + "]";
	}

}
