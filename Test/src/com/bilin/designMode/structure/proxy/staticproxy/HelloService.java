package com.bilin.designMode.structure.proxy.staticproxy;

//委托类实现
public class HelloService implements IHelloService {

	@Override
	public String sayHello(String userName) {
		System.out.println("helloService " + userName);
		return "HelloService" + userName;
	}

}
