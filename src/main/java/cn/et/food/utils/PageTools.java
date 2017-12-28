package cn.et.food.utils;

import java.util.List;


public class PageTools {
	
	//当前页
	private Integer curPage;
	//上一页
	private Integer prePage;
	//下一页
	private Integer nextPage;
	//总页数
	private Integer totalPage;
	//总数据数
	private Integer total;
	//每页显示数据数
	private Integer pageCount = 5;
	//存放分页数据
	private List rows;
	//分页开始索引
	private Integer startIndex;
	//分页结束索引
	private Integer endIndex;

	public PageTools(Integer curPage, Integer pageCount, Integer total) {
		this.curPage = curPage;
		this.total=total;
		this.pageCount = (pageCount == null ? this.pageCount : pageCount);
		this.totalPage = (total % this.pageCount == 0 ? total	/ this.pageCount : total / this.pageCount + 1);
		this.prePage = (curPage == 1 ? curPage : curPage - 1);
		
		this.nextPage = (curPage == totalPage ? totalPage : curPage + 1);
		this.startIndex = (curPage - 1) * this.pageCount + 1;
		this.endIndex = curPage * this.pageCount;
	}


	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	
}
