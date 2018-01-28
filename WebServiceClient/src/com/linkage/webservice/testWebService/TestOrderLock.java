package com.linkage.webservice.testWebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.IOrderLock;
import org.apache.commons.discovery.tools.DiscoverSingleton;

public class TestOrderLock {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//����WebService ��ʽ1	
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
			URL wsdlDocumentUrl=new URL("http://132.228.224.48/resweb/service/od?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://impl.service.webservice.module.resmaster.ztesoft.com/");
			factory.setServiceName("ODWebServiceService");
			factory.setPortName("ODWebServicePort");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(IOrderLock.class);
			factory.setLookupServiceOnStartup(false);
//			factory.setPortInterface(IOrderLock.class);
			Map<String,Integer> timeOut = new HashMap<String,Integer>();
			timeOut.put("axis.connection.timeout",1000);
			factory.setCustomPropertyMap(timeOut);			
			factory.afterPropertiesSet();
			IOrderLock orderLock=(IOrderLock) factory.getObject();
			
			
			/*String inXml = "<root><msgHead><time>2012-05-25T17:58:49</time><source>CRM.BSS@js.cn</source><target>OD.OSS@js.cn</target></msgHead><msgBody><boIds><boId>120275472798</boId><boId>120275472799</boId></boIds></msgBody></root>";
			System.out.println(inXml);
			String outXml = orderLock.orderLock(inXml);
			System.out.println(outXml);*/
			
			String inXml = "<root><msgHead><source>CRM.BSS@js.cn</source><target>OD.OSS@js.cn</target><time>2013-01-09T14:37:58</time></msgHead><msgBody><areaCode>wx.js.cn</areaCode><boIds><boId>100282793336</boId></boIds></msgBody></root>";
			System.out.println(inXml);
			String outXml = orderLock.getActiveProdServOrder(inXml);
			System.out.println(outXml);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
