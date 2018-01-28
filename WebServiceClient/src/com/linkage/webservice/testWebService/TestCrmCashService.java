package com.linkage.webservice.testWebService;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.CrmCashService;

public class TestCrmCashService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
			URL wsdlDocumentUrl=new URL("http://localhost/CrmHttpServiceWeb/services/CrmCashService?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://localhost/CrmHttpServiceWeb/services/CrmCashService");
			factory.setServiceName("CashWebserviceServerService");
			factory.setPortName("CrmCashService");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(CrmCashService.class);
			factory.setLookupServiceOnStartup(false);
			/*Map<String,Integer> timeOut = new HashMap<String,Integer>();
			timeOut.put("axis.connection.timeout",10000);
			factory.setCustomPropertyMap(timeOut);	*/		
			factory.afterPropertiesSet();
			CrmCashService crmCashService=(CrmCashService) factory.getObject();
			
			String inXml = "";
			System.out.println(inXml);
			String outXml = crmCashService.feeCommit("190030895286", "1", "1", "1", "0519");
			System.out.println(outXml);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
