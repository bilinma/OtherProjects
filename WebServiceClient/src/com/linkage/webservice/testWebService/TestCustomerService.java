package com.linkage.webservice.testWebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import bss.crm.businessIntf.bmo.impl.bo.ChangePasswordResponse;

import com.linkage.webservice.intf.webService.ICustomerService;

public class TestCustomerService {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		try {
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
			URL wsdlDocumentUrl=new URL("http://127.0.0.1/BssCrmWebService/services/CustomerService_A?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://crm.crmwsi");
			factory.setServiceName("CustomerService_AService");
			factory.setPortName("CustomerService_A");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(ICustomerService.class);
			factory.setLookupServiceOnStartup(false);
			/*Map<String,Integer> timeOut = new HashMap<String,Integer>();
			timeOut.put("axis.connection.timeout",10000);
			factory.setCustomPropertyMap(timeOut);*/			
			factory.afterPropertiesSet();
			ICustomerService customerService=(ICustomerService) factory.getObject();
			
			ChangePasswordResponse Resp = customerService.changePassword("66272355",1, "123123", "234234", 1, 1, "0512", "", "");
			System.out.println(Resp.getResultMsg());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
