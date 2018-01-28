package com.linkage.webservice.testWebService;

import java.net.URL;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.MakeCardService;

public class TestMakeCardService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
			URL wsdlDocumentUrl=new URL("http://127.0.0.1/BssCrmWebService/services/MakeCardServiceImpl?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://crm.crmwsi");
			factory.setServiceName("MakeCardServiceService");
			factory.setPortName("MakeCardService");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(MakeCardService.class);
			factory.setLookupServiceOnStartup(false);
			factory.afterPropertiesSet();
			MakeCardService makeCardService=(MakeCardService) factory.getObject();
			String inXml = "<root><AccessNumberInfo><areaCode>sz.js.cn</areaCode><anTypeCd>103</anTypeCd><anId>120174742467</anId></AccessNumberInfo></root>";
			String outXml = makeCardService.allocAn(inXml);
			System.out.println(outXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
