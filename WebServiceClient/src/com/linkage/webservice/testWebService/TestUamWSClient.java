package com.linkage.webservice.testWebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.IUamWSClient;

public class TestUamWSClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
			URL wsdlDocumentUrl=new URL("http://132.228.224.129/UaServer?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://www.mbossuac.com.cn/ua");
			factory.setServiceName("UaService");
			factory.setPortName("UaServer");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(IUamWSClient.class);
			factory.setLookupServiceOnStartup(false);
			Map<String,Integer> timeOut = new HashMap<String,Integer>();
			timeOut.put("axis.connection.timeout",5000);
			factory.setCustomPropertyMap(timeOut);			
			factory.afterPropertiesSet();
			IUamWSClient crmService=(IUamWSClient) factory.getObject();
			
			String inXml = "<CAPRoot><SessionHeader><ServiceCode>CAP01001</ServiceCode><Version>2009062411025800</Version><ActionCode>0</ActionCode><TransactionID>11013201301010000005096</TransactionID><SrcSysID>23888</SrcSysID><DstSysID>11000</DstSysID><ReqTime>20130101123851</ReqTime><DigitalSign/></SessionHeader><SessionBody><AuthReq><AuthInfo><AccountType>2000008</AccountType><AccountID>w051082857083</AccountID><PWDType>01</PWDType><Password>198302</Password></AuthInfo></AuthReq></SessionBody></CAPRoot>";
			System.out.println(inXml);
			String outXml = crmService.authReq(inXml);
			System.out.println(outXml);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
