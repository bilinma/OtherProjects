package com.bilin.common.constant;

import java.util.HashMap;
import java.util.Map;

public class ResultCode {
	public static final String C_SUCCESS = "00000000";
	public static final String C_FAILURE = "99999999";
	public static final String C_DUPLICATE = "11111111";
	public static final String PARAM_NOT_EMPTY = "00000001";
	

	public static final Map<String, String> resultMap = new HashMap<String, String>();

	public static String getResultMap(String code) {
		return (String) resultMap.get(code);
	}

	static {
		resultMap.put(C_SUCCESS, "成功！");
		resultMap.put(C_FAILURE, "处理失败！");
		resultMap.put(C_DUPLICATE, "重复存在！");
		resultMap.put(PARAM_NOT_EMPTY, "参数不能为NULL！");
	}
}
