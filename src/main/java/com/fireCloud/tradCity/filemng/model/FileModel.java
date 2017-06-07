package com.fireCloud.tradCity.filemng.model;


/**
 * @author wqy
 * @fun 文件管理model
 * @date 2017年6月7日
 */
public class FileModel {

	private Integer id;
	//原始名称
	private String origialName;
	//最终名称
	private String finalName;
	//
	private String suffix;
	//加密方式0：MD5
	private Integer rule;
	//存储类型0：静态服务器；1：后台服务器；2：oss
	private Integer storeType;
	//
	private String createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrigialName() {
		return origialName;
	}
	public void setOrigialName(String origialName) {
		this.origialName = origialName;
	}
	public String getFinalName() {
		return finalName;
	}
	public void setFinalName(String finalName) {
		this.finalName = finalName;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Integer getRule() {
		return rule;
	}
	public void setRule(Integer rule) {
		this.rule = rule;
	}
	public Integer getStoreType() {
		return storeType;
	}
	public void setStoreType(Integer storeType) {
		this.storeType = storeType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "FileModel [id=" + id + ", origialName=" + origialName + ", finalName=" + finalName + ", suffix="
				+ suffix + ", rule=" + rule + ", storeType=" + storeType + ", createTime=" + createTime + "]";
	}
	
}
