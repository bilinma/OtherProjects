package com.bilin.springmongo.common.dto;

import java.io.Serializable;
import java.util.Collection;

public class ResponseData implements Serializable {
	private static final long serialVersionUID = 4891410366565272863L;
	public static final int DEFAULT_PAGE_SIZE = 25;
	private Integer pageSize = Integer.valueOf(25);

	private Integer pageNum = Integer.valueOf(1);

	private int totalRecord;

	private int totalPage;
	private Collection<?> dataList;

	public Integer getPageSize() {
		if ((this.pageSize != null) && (this.pageSize.intValue() > 0)) {
			return this.pageSize;
		}
		setPageSize(Integer.valueOf(25));
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return this.pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalRecord() {
		return this.totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = ((totalRecord + getPageSize().intValue() - 1) / getPageSize().intValue());
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Collection<?> getDataList() {
		return this.dataList;
	}

	public void setDataList(Collection<?> dataList) {
		this.dataList = dataList;
	}
}
