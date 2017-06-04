package com.fireCloud.tradCity.basic.model;

/**
 * 用于回传前端的统一model
 * @author wqy
 *
 */
public class CallBackModel {

	private Boolean success = false; //是否成功
	private String msg = "初始化对象，信息为空"; //信息
	private Object obj; //回传对象
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
