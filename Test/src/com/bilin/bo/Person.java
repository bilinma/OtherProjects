package com.bilin.bo;

import java.util.List;

import com.bilin.validate.ValidPersonNumber;

@ValidPersonNumber
public class Person {
    private String name;
    private int age;
    private String Sex;
    private List<Person> children;
    
    private int number;
    
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
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public List<Person> getChildren() {
		return children;
	}
	public void setChildren(List<Person> children) {
		this.children = children;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
    
    
}
