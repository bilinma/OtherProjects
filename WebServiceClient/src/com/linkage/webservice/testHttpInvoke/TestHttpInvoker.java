package com.linkage.webservice.testHttpInvoke;

import java.util.List;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.linkage.webservice.intf.webService.RscServiceSMO;

public class TestHttpInvoker {

	public static void main(String[] args) throws Exception {
		HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
		
		httpInvokerProxyFactoryBean.setServiceUrl("http://132.228.183.73:9092/SecManagerWeb/service/secPayAcctHttpService");
		httpInvokerProxyFactoryBean.setServiceInterface(RscServiceSMO.class);
		
		httpInvokerProxyFactoryBean.afterPropertiesSet();
		
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		Class [] classIntf = new Class[]{RscServiceSMO.class};
		proxyFactoryBean.setProxyInterfaces(classIntf);
//		proxyFactoryBean.setTarget(httpInvokerProxyFactoryBean);
//		String[] InterNames =new String[] {"intfLogAdvisor"};
//		proxyFactoryBean.setInterceptorNames(InterNames);
//		System.out.println(proxyFactoryBean.getObject());
		
		RscServiceSMO rscServiceSMO= (RscServiceSMO) httpInvokerProxyFactoryBean.getObject();
		String inXml = "<root><AccessNumberInfo><anTypeCd>103</anTypeCd><rscStatusCd>14</rscStatusCd><anId>102091621372</anId></AccessNumberInfo></root>";
		List list = rscServiceSMO.allocAn(inXml);
		System.out.println(list);
	}

}
