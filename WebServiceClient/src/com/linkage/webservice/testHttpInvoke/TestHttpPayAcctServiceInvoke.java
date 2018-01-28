package com.linkage.webservice.testHttpInvoke;

import java.util.Map;

import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.linkage.webservice.intf.httpService.IHttpPayAcctService;

public class TestHttpPayAcctServiceInvoke {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
		
		httpInvokerProxyFactoryBean.setServiceUrl("http://132.228.183.73:9092/SecManagerWeb/service/secPayAcctHttpService");
		httpInvokerProxyFactoryBean.setServiceInterface(IHttpPayAcctService.class);
		
		httpInvokerProxyFactoryBean.afterPropertiesSet();
	
		IHttpPayAcctService httpPayAcctService= (IHttpPayAcctService) httpInvokerProxyFactoryBean.getObject();
		String areaCode = "";
		String sysFlag = "";
		String operator = "";
		String uuid = "";
		String authCode ="";
		String ip = "";
		String funName ="";
		long paymentAccountId =1L;
		Map<String, Object>  list = httpPayAcctService.queryPayAcctByAcctId(areaCode, sysFlag, operator, uuid, authCode, ip, funName, paymentAccountId);
		System.out.println(list);

	}

}
