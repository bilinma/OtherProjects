package com.ucloudlink.bo;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private int id;

	private String name;

	private int age;

	// 一个学生有多个电话号码
	List<Tel> tels = new ArrayList<Tel>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Tel> getTels() {
		return tels;
	}

	public void setTels(List<Tel> tels) {
		this.tels = tels;
	}
}
