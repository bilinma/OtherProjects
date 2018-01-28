package com.linkage.bss.crm.httpclient;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.linkage.bss.crm.httpService.SayHelloHttp;

public class TestSayHelloHttpInvoker {

	public static void main(String[] args) throws Exception {
		HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
		
		httpInvokerProxyFactoryBean.setServiceUrl("http://localhost/HttpServiceWebStudy/service/sayHelloHttp");
		httpInvokerProxyFactoryBean.setServiceInterface(SayHelloHttp.class);
		
		httpInvokerProxyFactoryBean.afterPropertiesSet();
		
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		Class [] classIntf = new Class[]{SayHelloHttp.class};
		proxyFactoryBean.setProxyInterfaces(classIntf);
//		proxyFactoryBean.setTarget(httpInvokerProxyFactoryBean);
//		String[] InterNames =new String[] {"intfLogAdvisor"};
//		proxyFactoryBean.setInterceptorNames(InterNames);
//		System.out.println(proxyFactoryBean.getObject());
		
		SayHelloHttp rscServiceSMO= (SayHelloHttp) httpInvokerProxyFactoryBean.getObject();
		String inXml = "<root><AccessNumberInfo><anTypeCd>103</anTypeCd><rscStatusCd>14</rscStatusCd><anId>102091621372</anId></AccessNumberInfo></root>";
		String list = rscServiceSMO.sayHello(inXml);
		System.out.println(list);
	}

}
