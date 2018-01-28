package com.linkage.webservice.testWebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.IUamSyncIntfWSClient;

public class TestUamSyncIntfService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
			URL wsdlDocumentUrl=new URL("http://132.228.99.130/sync/services/CrmInterfaces?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://www.mbossuac.com.cn/ua");
			factory.setServiceName("CrmService");
			factory.setPortName("CrmServer");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(IUamSyncIntfWSClient.class);
			factory.setLookupServiceOnStartup(false);
			Map<String,Integer> timeOut = new HashMap<String,Integer>();
			timeOut.put("axis.connection.timeout",5000);
			factory.setCustomPropertyMap(timeOut);			
			factory.afterPropertiesSet();
			IUamSyncIntfWSClient uamSyncIntfWSClient=(IUamSyncIntfWSClient) factory.getObject();
			
			String reqXml = "<root><msgHead><source>CRM.BSS@js.cn</source><target>OD.OSS@js.cn</target><time>Wed May 30 09:58:57 CST 2012</time></msgHead><msgBody><anTypeCd>405</anTypeCd><areaCode>sq.nt.js.cn</areaCode><assignType>0</assignType><accessNum>null</accessNum></msgBody></root>";
			System.out.println(reqXml);
			String outXml = uamSyncIntfWSClient.resetPassword(reqXml);
			System.out.println(outXml);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
