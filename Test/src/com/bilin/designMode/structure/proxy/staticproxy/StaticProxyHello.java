package com.bilin.designMode.structure.proxy.staticproxy;

//代理类
public class StaticProxyHello implements IHelloService {

	private IHelloService helloService = new HelloService();

    @Override
    public String sayHello(String userName) {
        /** 代理对象可以在此处包装一下*/
        System.out.println("Before invoke sayHello" );
        String retStr = helloService.sayHello(userName);
        System.out.println("After invoke sayHello");
        return retStr;
    }
}
