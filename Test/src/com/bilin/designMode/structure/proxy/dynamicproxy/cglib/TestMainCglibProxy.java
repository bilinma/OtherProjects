package com.bilin.designMode.structure.proxy.dynamicproxy.cglib;

public class TestMainCglibProxy {
	public static void main(String[] args) {
		CglibInterceptor cglibProxy = new CglibInterceptor();
		CglibHelloClass cglibHelloClass = (CglibHelloClass) cglibProxy.newProxyInstance(CglibHelloClass.class);
		cglibHelloClass.sayHello("isole");
		cglibHelloClass.sayByeBye("sss");
	}
}
