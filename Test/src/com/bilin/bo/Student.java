package com.bilin.bo;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student>{
	private int id;

	private String name;

	private int age;

	// 一个学生有多个电话号码
	List<Tel> tels = new ArrayList<Tel>();
	
	
	
	public Student() {
		super();
	}

	public Student(int id) {
		super();
		this.id = id;
	}



	public Student(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

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

	@Override
	public int compareTo(Student o) {
		if(this.id>o.getId()){
			return 1;
		}else if(this.id<o.getId()){
			return -1;
		}else{
			return 0;
		}
	}
}
