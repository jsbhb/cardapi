package com.fireCloud.tradCity.basic.pagination.model;

/**
 * 
 * @author wqy
 * @fun 分页组件
 */
public class Pagination {

	private Integer numPerPage; //每页几条
	private Integer currentPage; //当前页
	public Integer getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
