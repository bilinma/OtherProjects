package com.linkage.webservice.testWebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.CrmService;
import com.linkage.webservice.intf.webService.InterGetVoiceInfoSheet;

public class TestInterGetVoiceInfoSheet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
//			URL wsdlDocumentUrl=new URL("http://10.131.128.17:24688/services/InterGetVoiceInfoSheet?wsdl");
			URL wsdlDocumentUrl=new URL("http://10.131.114.175:24021/services/InterGetVoiceInfoSheet?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://webservices.cs.asiainfo.com");
			factory.setServiceName("InterGetVoiceInfoSheetService");
			factory.setPortName("InterGetVoiceInfoSheet");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(InterGetVoiceInfoSheet.class);
			factory.setLookupServiceOnStartup(false);
			/*Map<String,Integer> timeOut = new HashMap<String,Integer>();
			timeOut.put("axis.connection.timeout",1000);
			factory.setCustomPropertyMap(timeOut);*/			
			factory.afterPropertiesSet();
			InterGetVoiceInfoSheet getVoiceInfoService=(InterGetVoiceInfoSheet) factory.getObject();
			
			String beginDateTime = "2015-08-20 21:00:00";
			String endDateTime = "2015-08-20 23:00:00";
			String userType = "G001";
			String cityCode = "0917";
			String userGrade = "00";
			String retInfo = getVoiceInfoService.getEmergencyQTVoice(beginDateTime, endDateTime, userType, cityCode, userGrade);
			System.out.println(retInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
