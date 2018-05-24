package com.swagger.entity;

import io.swagger.annotations.ApiModelProperty;

public class GetInfoResult  extends BaseResult{
	@ApiModelProperty(value="text here",required = true)
	private String text;
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
