package com.bilin.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Tel {
	
	private String name;
	
	@NotBlank(message = "电话不能为空！")
	private String tel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
