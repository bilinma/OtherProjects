package com.linkage.webservice.testWebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.CrmService;

public class TestCrmService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
			URL wsdlDocumentUrl=new URL("http://localhost:8084/services/InterOAProccessSheet?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://WebService/services/CrmService");
			factory.setServiceName("CrmServiceImpl");
			factory.setPortName("CrmService");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(CrmService.class);
			factory.setLookupServiceOnStartup(false);
			Map<String,Integer> timeOut = new HashMap<String,Integer>();
			timeOut.put("axis.connection.timeout",1000);
			factory.setCustomPropertyMap(timeOut);			
			factory.afterPropertiesSet();
			CrmService crmService=(CrmService) factory.getObject();
			
			String inXml = "Ð¡±ó";
			System.out.println(inXml);
			String outXml = crmService.sayHello(inXml);
			System.out.println(outXml);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
