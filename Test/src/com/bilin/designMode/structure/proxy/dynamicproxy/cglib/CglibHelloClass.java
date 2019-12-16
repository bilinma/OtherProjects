package com.bilin.designMode.structure.proxy.dynamicproxy.cglib;

//委托类,是一个简单类
public class CglibHelloClass {
	/**
	 * 方法1
	 * 
	 * @param userName
	 * @return
	 */
	public String sayHello(String userName) {
		System.out.println("目标对象的方法执行了");
		return userName + " sayHello";
	}

	public String sayByeBye(String userName) {
		System.out.println("目标对象的方法执行了");
		return userName + " sayByeBye";
	}

}