package com.linkage.webservice.testWebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.ICrmWebServiceForOss;

public class TestCrmWebServiceForOss {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		try {
			//http://132.228.103.5/BssCrmWebServiceNew/services/ICrmWebServiceForOss?wsdl
			//http://localhost/BssCrmWebService/services/ICrmWebServiceForOss?wsdl
			
			JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
//			URL wsdlDocumentUrl=new URL("http://132.228.224.143/BssCrmWebService/services/ICrmWebServiceForOss?wsdl");
			URL wsdlDocumentUrl=new URL("http://132.228.138.124/BssCrmWebService/services/ICrmWebServiceForOss?wsdl");
			factory.setWsdlDocumentUrl(wsdlDocumentUrl);
			factory.setNamespaceUri("http://foross.BssCrmWebService.crm.bss.linkage.com");
			factory.setServiceName("CrmForOssServiceImplService");
			factory.setPortName("ICrmWebServiceForOss");
			factory.setServiceFactoryClass(ServiceFactory.class);
			factory.setServiceInterface(ICrmWebServiceForOss.class);
			factory.setLookupServiceOnStartup(false);
			factory.afterPropertiesSet();
			ICrmWebServiceForOss crmWebServiceForOss=(ICrmWebServiceForOss) factory.getObject();
			
			//报竣调用
			/*String inXml = "<root><msgHead><source>OD.OSS@js.cn</source><target>CRM.BSS@js.cn</target><time>2012-05-24 16:45:03</time></msgHead><msgBody><areaCode>nt.js.cn</areaCode><boIds><boId>130141047588</boId></boIds><psoId>13800000000063</psoId><type>C</type></msgBody></root>";
			System.out.println(inXml);
			String outXml = crmWebServiceForOss.boReplyMsg(inXml);
			System.out.println(outXml);*/
			//抽取购物车信息
			
			String inXml = "<root><time>2013-01-08T18:24:30</time><areaCode>sz.js.cn</areaCode><block code=\"CRM.BSS@js.cn:contractData\"><key code=\"436931\" type=\"FlowInstId\"/></block><requestId>2490207</requestId></root>";
//			String inXml = "<root><msgHead><source>OD.OSS@js.cn</source><target>CRM.BSS@js.cn</target><time>2012-08-11 17:21:42</time></msgHead><msgBody><areaCode>sz.js.cn</areaCode><boIds><boId>120439658387</boId></boIds><psoId>12800000003383</psoId><type>C</type></msgBody></root>";
//			String inXml = "<root><time>2012-08-28T19:49:03</time><areaCode>sz.js.cn</areaCode><block code=\"CRM.BSS@js.cn:contractData\"><key code=\"6698671\" type=\"FlowInstId\"/></block><requestId>1882138</requestId></root>";
//			String inXml = "<root><msgHead><source>OD.OSS@js.cn</source><target>CRM.BSS@js.cn</target><time>2012-12-24 11:36:26</time></msgHead><msgBody><areaCode>sz.js.cn</areaCode><boIds><boId>131217791142</boId></boIds><psoId>13800000330827</psoId><type>C</type></msgBody></root>";
			System.out.println(new Date()+":"+inXml);
			String outXml = crmWebServiceForOss.getOrderListByFlowInstId(inXml);
//			String outXml = crmWebServiceForOss.getProdOffferInfoByProdId(inXml);
//			String outXml = crmWebServiceForOss.boReplyMsg(inXml);
//			String outXml = crmWebServiceForOss.getAccountInfoByProdId(inXml);
//			String outXml = crmWebServiceForOss.boReplyMsg(inXml);
			System.out.println(new Date()+":"+outXml);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}
