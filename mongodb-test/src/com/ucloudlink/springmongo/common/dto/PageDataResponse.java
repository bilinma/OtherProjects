package com.ucloudlink.springmongo.common.dto;

import java.io.Serializable;
import java.util.Collection;

public class PageDataResponse<E> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7038327072188123149L;
	
	public static final int DEFAULT_PAGE_SIZE = 25;
	private Integer perPageCount;
	private Integer currentPage = Integer.valueOf(1);

	private int totalCount;

	private int totalPageCount;
	private Collection<E> dataList;

	public Integer getPerPageCount() {
		if ((this.perPageCount != null) && (this.perPageCount.intValue() > 0)) {
			return this.perPageCount;
		}
		setPerPageCount(Integer.valueOf(25));

		return this.perPageCount;
	}

	public void setPerPageCount(Integer perPageCount) {
		this.perPageCount = perPageCount;
	}

	public Integer getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalPageCount = ((this.totalCount + getPerPageCount().intValue() - 1) / getPerPageCount().intValue());
	}

	public int getTotalPageCount() {
		return this.totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public Collection<E> getDataList() {
		return this.dataList;
	}

	public void setDataList(Collection<E> dataList) {
		this.dataList = dataList;
	}
}
