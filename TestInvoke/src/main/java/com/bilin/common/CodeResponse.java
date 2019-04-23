package com.bilin.common;

import java.io.Serializable;

import com.bilin.common.constant.ResultCode;

public class CodeResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String streamNo;
	private String resultCode = ResultCode.C_SUCCESS;

	private String resultDesc = ResultCode.getResultMap(this.resultCode);

	private T data;

	public void setResultState(String code) {
		setResultCode(code);
		setResultDesc(ResultCode.getResultMap(code));
	}

	public void setResultState(String code, String desc) {
		setResultCode(code);
		setResultDesc(desc);
	}

	public boolean checkSuccess() {
		if (ResultCode.C_SUCCESS.equals(this.resultCode)) {
			return true;
		}
		return false;
	}

	public boolean checkNotSuccess() {
		return !checkSuccess();
	}

	public String getStreamNo() {
		return this.streamNo;
	}

	public void setStreamNo(String streamNo) {
		this.streamNo = streamNo;
	}

	public String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return this.resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String toString() {
		return "CodeResponse [streamNo=" + this.streamNo + ", resultCode=" + this.resultCode + ", resultDesc="
				+ this.resultDesc + ", data=" + this.data + "]";
	}
}
