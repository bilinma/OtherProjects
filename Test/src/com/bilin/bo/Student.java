package com.bilin.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Student implements Comparable<Student>{
	private int id;

    @NotNull(message = "名字不能为空")
    private String name;
	
	private int age;

    @Size(min = 6,max = 30,message = "地址应该在6-30字符之间")
    private String address;

    @DecimalMax(value = "100.00",message = "体重有些超标哦")
    @DecimalMin(value = "60.00",message = "多吃点饭吧")
    private BigDecimal weight;

    @Past(message = "生日必须在当前时间之前")
    private Date birthday;

    @Pattern(regexp = "^(.+)@(.+)$",message = "邮箱的格式不合法")
    private String email;
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
