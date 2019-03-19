package com.bilin.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class SpringBean implements BeanNameAware, BeanFactoryAware,InitializingBean, DisposableBean {
	
	private String name;
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("【注入属性】注入属性name");
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		System.out.println("【注入属性】注入属性sex");
		this.sex = sex;
	}

	@Override
	public void setBeanName(String paramString) {
		System.out.println("》》》调用了BeanNameAware的setBeanName方法了");
	}
	
	@Override
	public void setBeanFactory(BeanFactory paramBeanFactory) throws BeansException {
		System.out.println("》》》调用了BeanFactoryAware的setBeanFactory方法了");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("》》》调用了Initailization的afterPropertiesSet方法了");
	}
	
    public void init(){  
        System.out.println("》》》调用了init-method 的 init 方法了");  
    }  

	@Override
	public void destroy() throws Exception {
		System.out.println("》》》调用了DisposableBean的destroy方法了");
	}
	
	@Override
	public String toString() {
		return "BeanFactoryPostProcessorTest [name=" + name + ", sex=" + sex + "]";
	}
	
	
    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestory() {
        System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }
}