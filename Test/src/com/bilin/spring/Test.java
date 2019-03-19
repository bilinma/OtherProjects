package com.bilin.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		
		System.out.println("》》》Spring ApplicationContext容器开始初始化了......");
		ApplicationContext applicationcontext = new ClassPathXmlApplicationContext(new String[] { "spring-service.xml" });
		System.out.println("》》》Spring ApplicationContext容器初始化完毕了......");
		
		//得到SpringBean，并使用
		SpringBean springBean = applicationcontext.getBean(SpringBean.class);
        System.out.println(springBean.toString());
        
        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)applicationcontext).registerShutdownHook();
        
	}

}
