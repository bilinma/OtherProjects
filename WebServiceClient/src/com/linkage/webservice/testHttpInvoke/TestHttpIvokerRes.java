package com.linkage.webservice.testHttpInvoke;

import java.util.List;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.linkage.webservice.intf.webService.IOSSInterfaceForCRM;
import com.linkage.webservice.intf.webService.RscServiceSMO;

public class TestHttpIvokerRes {
	public static void main(String[] args) throws Exception {
		HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
		
		httpInvokerProxyFactoryBean.setServiceUrl("http://oss.bxsys.jsbss.net/BizHall/xrainbow/services/bss.bizHall.rscFacade.releaseAn");
		httpInvokerProxyFactoryBean.setServiceInterface(IOSSInterfaceForCRM.class);
		
		httpInvokerProxyFactoryBean.afterPropertiesSet();
		System.out.println(httpInvokerProxyFactoryBean);
		
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		Class [] classIntf = new Class[]{IOSSInterfaceForCRM.class};
		proxyFactoryBean.setProxyInterfaces(classIntf);
		proxyFactoryBean.setTarget(httpInvokerProxyFactoryBean);
//		String[] InterNames =new String[] {"intfLogAdvisor"};
//		proxyFactoryBean.setInterceptorNames(InterNames);
//		System.out.println(proxyFactoryBean.getObject());
		
		IOSSInterfaceForCRM rscServiceSMO= (IOSSInterfaceForCRM) httpInvokerProxyFactoryBean.getObject();
		String inXml = "<root><AccessNumberInfo><anTypeCd>103</anTypeCd><rscStatusCd>14</rscStatusCd><anId>170012734218</anId></AccessNumberInfo></root>";
		String list = rscServiceSMO.allocAn(inXml);
		System.out.println(list);
	}
}
