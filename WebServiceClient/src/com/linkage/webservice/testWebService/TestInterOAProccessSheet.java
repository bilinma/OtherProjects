package com.linkage.webservice.testWebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.InterOAProccessSheet;

public class TestInterOAProccessSheet {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 * @throws RemoteException 
	 */
	public static void main(String[] args) {
		try {
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
			URL wsdlDocumentUrl=new URL("http://localhost:8084/services/InterOAProccessSheet?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://webservices.cs.asiainfo.com");
			factory.setServiceName("InterOAProccessSheetService");
			factory.setPortName("InterOAProccessSheet");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(InterOAProccessSheet.class);
			factory.setLookupServiceOnStartup(false);
			factory.afterPropertiesSet();
			InterOAProccessSheet oaProccessSheet=(InterOAProccessSheet) factory.getObject();
			String outInfo = oaProccessSheet.replyWorkSheet("serialNo",
					"serSupplier", "serCaller", "callTime", "callerPwd",
					"replyCorp", "replyPerson", "replyDepart", "replyContact",
					"replyTime", "endResult");
			System.out.println(outInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
