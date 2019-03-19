package com.bilin.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("》》》Spring ApplicationContext容器开始初始化了......");
		ApplicationContext applicationcontext = new ClassPathXmlApplicationContext(new String[] { "spring-service.xml" });
		System.out.println("》》》Spring ApplicationContext容器初始化完毕了......");
		
		BeanFactoryPostProcessorTest beanFactoryPostProcessorTest=applicationcontext.getBean(BeanFactoryPostProcessorTest.class);
        System.out.println(beanFactoryPostProcessorTest.toString());
        
	}

}
