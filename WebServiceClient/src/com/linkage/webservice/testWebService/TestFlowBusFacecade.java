package com.linkage.webservice.testWebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.FlowBusFacecade;

public class TestFlowBusFacecade {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		try {
			//����WebService ��ʽ1	
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
			URL wsdlDocumentUrl=new URL("http://132.228.183.3:9080/FLOWBUS/services/FlowBusService?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://bl.flowbus.js.oss.ztesoft.com");
			factory.setServiceName("FlowBusService");
			factory.setPortName("FlowBusServiceHttpEndpoint");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(FlowBusFacecade.class);
			factory.setLookupServiceOnStartup(false);
			factory.afterPropertiesSet();
			FlowBusFacecade flowBusFacecade=(FlowBusFacecade) factory.getObject();
			String inXml = "<orderInfo><order id=\"0\"><partyId>121764119828</partyId><installAreaId>21</installAreaId><feeType>1</feeType><orderTypeId>1</orderTypeId><prodSpecId>80000101</prodSpecId><offeringId>500000009</offeringId><accessNumber>LNCVPN051222084674</accessNumber><anId>130000001296</anId><prod2accNbr>051222084674</prod2accNbr><anId2>130000001296</anId2><servicePak/><pricePlanPak><pricePlan><pricePlanCd>500002120</pricePlanCd><actionType>0</actionType><startDt/><endDt/><properties/></pricePlan></pricePlanPak><prodPropertys><property propertyType=\"prod\"><id>29</id><name/><value>1</value><actionType>0</actionType></property><property propertyType=\"prod\"><id>550000006</id><name/><value>123423423</value><actionType>0</actionType></property></prodPropertys><acctCd>12000000005573152</acctCd><coLinkMan>asdfasd</coLinkMan><coLinkNbr>111</coLinkNbr></order></orderInfo>";
			String outXml = flowBusFacecade.unlock(inXml);
			System.out.println(outXml);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
