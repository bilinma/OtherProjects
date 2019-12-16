package com.bilin.designMode.structure.proxy.dynamicproxy.jdk;

//测试动态代理类
public class TestMainJavaProxy {
	public static void main(String[] args) {
		JavaProxyInvocationHandler proxyInvocationHandler = new JavaProxyInvocationHandler(new HelloService());
		IHelloService helloService = (IHelloService) proxyInvocationHandler.newProxyInstance();
		helloService.sayHello("yupao");
		helloService.sayByeBye("paopao");
	}
}