package com.fireCloud.tradCity.basic.callback.model;

import java.util.Map;

/**
 * 用于回传前端的统一model
 * @author wqy
 *
 */
public class CallBackModel {

	private Boolean isSuccess; //是否成功
	private String msg; //信息
	private Map<String,Object> map; //回传对象
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}
