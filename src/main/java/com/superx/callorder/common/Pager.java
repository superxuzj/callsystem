package com.superx.callorder.common;


import java.io.Serializable;
import java.util.List;

/**
 * 个人推荐pager
 * @author xuzj
 * @param <T>
 */
public class Pager<T> implements Serializable{

	/**
	 * git
	 */
	private static final long serialVersionUID = 1L;
	private int pageSize; // 每页显示多少条记录
	
	private int currentPage; //当前第几页数据
	
	private int totalSize; // 一共多少条记录
	
	private int pages; // 一共多少页记录
	
	private List<T> results; //要显示的数据
	

	public Pager(int pageNum, int pageSize,int totalSize, List<T> sourceList){
		if(sourceList == null || sourceList.isEmpty()){
			return;
		}
		
		// 总记录条数
		this.totalSize = totalSize;
		
		// 每页显示多少条记录
		this.pageSize = pageSize;
		//获取总页数
		this.pages = this.totalSize / this.pageSize;
		if(this.totalSize % this.pageSize !=0){
			this.pages = this.pages + 1;
		}
		
		// 当前第几页数据
		this.currentPage = this.pages < pageNum ?  this.pages : pageNum;
				
				
		this.results = sourceList;
	}
	
	public Pager(){
		
	}

	public Pager(int pageSize, int currentPage, int totalSize, int pages,
			List<T> results) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalSize = totalSize;
		this.pages = pages;
		this.results = results;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

}
