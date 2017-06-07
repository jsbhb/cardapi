package com.fireCloud.tradCity.basic.model;

import com.github.pagehelper.Page;

/**
 * 
 * @author wqy
 * @fun 分页组件
 */
public class Pagination {

	private Integer numPerPage = 20; //每页几条
	private Integer currentPage = 1; //当前页
	private Integer lastIndex;
	private Integer totalPages;
	private Long totalRows;
	
	
	public Pagination webPageConverter(Page<?> page) {
		this.setCurrentPage(page.getPageNum());
		this.setLastIndex(page.getEndRow());
		this.setNumPerPage(page.getPageSize());
		this.setTotalPages(page.getPages());
		this.setTotalRows(page.getTotal());
		
		return this;
	}
	
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

	public Integer getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(Integer lastIndex) {
		this.lastIndex = lastIndex;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}
	
}
