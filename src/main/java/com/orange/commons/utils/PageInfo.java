/*
 * Copyright 2010-2011 ESunny.com All right reserved. This software is the confidential and proprietary information of
 * ESunny.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ESunny.com.
 */
package com.orange.commons.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 页码信息类
 * 
 * @author yajun.wu
 */
public class PageInfo {

	/**
	 * 一页显示的条数
	 */
	private int	pageSize	= 10;

	/**
	 * 总记录数
	 */
	private int	totalCount	= -1;

	/**
	 * 当前页码
	 */
	private int	pageNo		= 1;

	/**
	 * 总页数
	 */
	private int	pageCount	= 1;

	/**
	 * 前置偏移量
	 */
	private int	preOffset	= -4;

	/**
	 * 后置偏移量
	 */
	private int	sufOffset	= 5;

	/**
	 * 开始显示的页数
	 */
	private int	startPage	= 1;

	/**
	 * 最后显示的页数
	 */
	private int	endPage;

	/** 上一页 */
	private int	prePage;

	/**
	 * 下一页
	 */
	private int	nextPage;

	/**
	 * 是否计算标志
	 */
	private int	calcFlag	= 1;

	/** 需要获取条数 */
	private Integer	limit;

	/** 偏移量 */
	private Integer	offset;

	/** 查询条件 */
	private Map<String, String> queryMap = new HashMap<String, String>();

	/**
	 * 获取开始显示的页数
	 * 
	 * @param pageNo 当前页数
	 * @param pageCount 总页数
	 * @param preOffset 前置偏移量
	 * @param sufOffset 后置偏移量
	 * @return 开始显示的页数
	 */
	public static int getStartPageNo(int pageNo, int pageCount, int preOffset, int sufOffset) {
		// 如果要显示的页数大于等于总页数，则开始页数为第一页
		if (sufOffset - preOffset >= pageCount) {
			return 1;
		}
		// 如果当前页数+后置偏移量已经大于总页数，则超出的部分计入前置偏移量
		if (pageNo + sufOffset > pageCount) {
			preOffset += pageCount - (pageNo + sufOffset);
		}
		// 如果当前页面+前置偏移量小于等于1，则开始页数为第一页
		if (pageNo + preOffset <= 1) {
			return 1;
		}
		return pageNo + preOffset;
	}

	/**
	 * 获取最后显示的页数
	 * 
	 * @param pageNo 当前页数
	 * @param pageCount 总页数
	 * @param preOffset 前置偏移量
	 * @param sufOffset 后置偏移量
	 * @return 最后显示的页数
	 */
	public static int getEndPageNo(int pageNo, int pageCount, int preOffset, int sufOffset) {
		// 如果要显示的页数大于等于总页数，则结束页数为最后一页
		if (sufOffset - preOffset >= pageCount) {
			return pageCount;
		}
		// 如果当前页数+前置偏移量小于第一页，则超出的部分计入后置偏移量
		if (pageNo + preOffset < 1) {
			sufOffset = sufOffset + 1 - (pageNo + preOffset);
		}
		// 如果当前页面+后置偏移量大于最大页数，则最后页数为最后一页
		if (pageNo + sufOffset > pageCount) {
			return pageCount;
		}
		return pageNo + sufOffset;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
		this.limit = this.getPageSize();
		this.offset = this.getPageSize() * (this.getPageNo() - 1);
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount >= 0) {
			this.totalCount = totalCount;
			int count = (totalCount - 1) / pageSize + 1;
			setPageCount(count);
		}
	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		if (pageNo < 1) {
			pageNo = 1;
		}
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		this.limit = this.getPageSize();
		this.offset = this.getPageSize() * (this.getPageNo() - 1);
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
		if (pageNo > pageCount) {
			setPageNo(pageCount);
		}
	}

	/**
	 * @return the preOffset
	 */
	public int getPreOffset() {
		return preOffset;
	}

	/**
	 * @param preOffset the preOffset to set
	 */
	public void setPreOffset(int preOffset) {
		this.preOffset = preOffset;
	}

	/**
	 * @return the sufOffset
	 */
	public int getSufOffset() {
		return sufOffset;
	}

	/**
	 * @param sufOffset the sufOffset to set
	 */
	public void setSufOffset(int sufOffset) {
		this.sufOffset = sufOffset;
	}

	/**
	 * @return the startPage
	 */
	public int getStartPage() {
		if (this.calcFlag == 1) {
			return getStartPageNo(pageNo, pageCount, preOffset, sufOffset);
		}
		return startPage;
	}

	/**
	 * @param startPage the startPage to set
	 */
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	/**
	 * @return the endPage
	 */
	public int getEndPage() {
		if (this.calcFlag == 1) {
			return getEndPageNo(pageNo, pageCount, preOffset, sufOffset);
		}
		return endPage;
	}

	/**
	 * @param endPage the endPage to set
	 */
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	/**
	 * @return the prePage
	 */
	public int getPrePage() {
		prePage = this.getPageNo() - 1;
		if (prePage <= 0) {
			prePage = 1;
		}
		return prePage;
	}

	/**
	 * @param prePage the prePage to set
	 */
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		if (pageNo >= pageCount) {
			nextPage = pageCount;
			return nextPage;
		}
		nextPage = pageNo + 1;
		return nextPage;
	}

	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * @return the calcFlag
	 */
	public int getCalcFlag() {
		return calcFlag;
	}

	/**
	 * @param calcFlag the calcFlag to set
	 */
	public void setCalcFlag(int calcFlag) {
		this.calcFlag = calcFlag;
	}

	public Map<String, String> getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(Map<String, String> queryMap) {
		this.queryMap = queryMap;
	}

}
