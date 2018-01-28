package com.linkage.webservice.testWebService;

import java.net.URL;
import java.util.Date;

import org.apache.axis.client.ServiceFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

import com.linkage.webservice.intf.webService.IOSSInterfaceForCRM;

public class TestOssWebService {
	public static void main(String[] args) throws Exception{
		
		//调用WebService 方式1	
		JaxRpcPortProxyFactoryBean factory= new JaxRpcPortProxyFactoryBean();
		//http://132.228.125.112:9080/resweb/service/IOSSInterfaceForCRM?wsdl
//		URL wsdlDocumentUrl=new URL("http://nt.res.jsoss.net/resweb/service/IOSSInterfaceForCRM?wsdl");
		URL wsdlDocumentUrl=new URL("http://132.228.224.230/resweb/service/IOSSInterfaceForCRM?wsdl");
		factory.setWsdlDocumentUrl(wsdlDocumentUrl);
		factory.setNamespaceUri("http://webservice.bizwebservice.module.resmaster.ztesoft.com/");
		factory.setServiceName("OSSInterfaceForCRMService");
		factory.setPortName("OSSInterfaceForCRMPort");
		factory.setServiceFactoryClass(ServiceFactory.class);
		factory.setServiceInterface(IOSSInterfaceForCRM.class);
		factory.setLookupServiceOnStartup(false);
		factory.afterPropertiesSet();
		IOSSInterfaceForCRM ossInterfaceForCRM=(IOSSInterfaceForCRM) factory.getObject();
		/*System.out.println(ossInterfaceForCRM);
		String inXml ="<root><AccessNumberInfo><uuId>f4fdb1e7abd347549c302631621e177b</uuId><canChangePwd>Y</canChangePwd><anTypeCd>502</anTypeCd><rscStatusCd>0</rscStatusCd><areaCode>sq.sz.js.cn</areaCode><name>6290-0107</name><password>1</password></AccessNumberInfo></root>";
		System.out.println(inXml);
		String outXml = ossInterfaceForCRM.getSwitchByAreaAndTerminalSpec(inXml);
		System.out.println(outXml);*/
		
		/*//调用WebService 方式2
		ApplicationContext context=new ClassPathXmlApplicationContext("./rscClient-spring-wsclient.xml");
		IOSSInterfaceForCRM ossInterfaceForCrm=(IOSSInterfaceForCRM) context.getBean("oss.ossInterfaceForCRM");
		
		String outXml=null;
		String inXml= null;
		
		inXml ="<root><uuId>f4fdb1e7abd347549c302631621e177b</uuId><AccessNumberInfo><canChangePwd>Y</canChangePwd><anTypeCd>502</anTypeCd><rscStatusCd>0</rscStatusCd><areaCode>sq.sz.js.cn</areaCode><name>6290-0107</name><password>1</password></AccessNumberInfo></root>";
		System.out.println(inXml);
		outXml =ossInterfaceForCrm.getAn(inXml);
		System.out.println(outXml);*/
		/*outXml = ossInterfaceForCrm.getPageReturnVal("23", "f4fdb1e7abd347549c302631621e177b");
		System.out.println(outXml);
		
		outXml = ossInterfaceForCrm.getTerminalDevice(null);
		System.out.println(outXml);*/
		
		/*
		inXml="<root><AccessNumberInfo><canChangePwd>Y</canChangePwd><anTypeCd>307</anTypeCd><rscStatusCd>0</rscStatusCd><areaId>21</areaId><name>051266090384</name><password>435688</password></AccessNumberInfo></root>";
		List reqList = DealRscWebServiceXmlUtil.getOldGetAddAnInfo(inXml);
		inXml=DealRscWebServiceXmlUtil.createGetAnXml(reqList);
		System.out.println(outXml);
		outXml = ossInterfaceForCrm.addAn(inXml);
		System.out.println(outXml);*/
		
	   /*inXml = "<?xml version='1.0' encoding='UTF-8'?>" +
				"<root>" +
				"<AccessNumberInfo>" +
				"<uuId>40</uuId>" +
				"<areaId>20</areaId>" +
				"<anId>12000000000021</anId>" +
				"<rscStatusCd>170003</rscStatusCd>" +
				"<anTypeCd>6074</anTypeCd>" +
				"/AccessNumberInfo>" +
				"</root>";
		outXml = ossInterfaceForCrm.allocAn(inXml);
		System.out.println(outXml);*/
		
	   /*inXml="<terminalDevice>" +
				"<boId>120001119844</boId>" +
				"<uuId>6de1c08c6a464f53bbb75bc79f26c093</uuId>" +
				"<areaId>20</areaId>" +
				"<channelId>80000128</channelId>" +
				"<code>12345678900987654321</code>" +
				"<terminalDevSpec>" +
				"<id>10302057</id>" +
				"<servSpecCd/>" +
				"</terminalDevSpec>" +
				"</terminalDevice>";
		
		outXml = ossInterfaceForCrm.getTerminalDevice(inXml);
		System.out.println(outXml);*/

		/*outXml = ossInterfaceForCrm.deleteAn(null);
		System.out.println(outXml);
		
		
		outXml = ossInterfaceForCrm.getAn(null);
		System.out.println(outXml);
		
		outXml = ossInterfaceForCrm.getTerminalDeviceCode(null, "14");
		System.out.println(outXml);*/
		/*String outXml="<?xml version='1.0' encoding='utf-8'?>" +
				"<root>" +
				"<baseInfo>" +
				"<result>1</result>" +
				"<resultMsg>是虚拟网号码</resultMsg>" +
				"</baseInfo>" +
				"<resultInfo>"+
				"<anIds>"+
				"<anId>12000002562</anId>"+
				"<name>83818891</name>"+
				"</anIds>"+
				"<anIds>"+
				"<anId>12000002563</anId>"+
				"<name>83818898</name>"+
				"</anIds>"+
				"</resultInfo>"+
				"</root>";
		List list=DealRscWebServiceXmlInfo.getGetAnXmlInfo(outXml);
		for(int i=0;i<list.size();i++){
			Map map=(Map) list.get(i);
			System.out.println("*************"+map);
		}*/
		
		/*String outXml="<?xml version='1.0' encoding='UTF-8'?>" +
				"<root>" +
				"<baseInfo>" +
				"<result>1</result>" +
				"<resultMsg>查询成功</resultMsg>" +
				"</baseInfo><resultInfo>" +
				"<returnData>" +
				"<root>" +
				"<result>1</result>" +
				"<resultMsg>预占成功</resultMsg>" +
				"<anId>1200000369</anId >" +
				"<number>1200000369</number>" +
				"<levelId>1200000369</levelId>" +
				"</root>" +
				"</returnData>" +
				"</resultInfo>" +
				"</root>";
		System.out.println("*************"+outXml);
		List resultList = DealRscWebServiceXmlInfo.getPageReturnNbrInfo(outXml);
		Map<String, String> rscMapInfo=new HashMap<String, String>();
		for(int i=0;i<resultList.size();i++){
			Map map=(Map) resultList.get(i);
			for(Iterator iterator = map.keySet().iterator(); iterator.hasNext();){
				String key=(String) iterator.next();
				rscMapInfo.put(key, (String) map.get(key));
			}
		}
		System.out.println(rscMapInfo);*/
		
		String inXml = "<root><AccessNumberInfo><uuId>7641dd00157d4c36a60c177c8523778a</uuId><areaCode>sq.nt.js.cn</areaCode><canChangePwd>Y</canChangePwd><anTypeCd>307</anTypeCd><name>051210526167</name><password>679296</password></AccessNumberInfo></root>";
		System.out.println(new Date()+"入参："+inXml);
//		String outXml = ossInterfaceForCRM.isTmlCoveredByAddressId(inXml);
		String outXml = ossInterfaceForCRM.addAn(inXml);
		System.out.println(new Date()+"出参："+outXml);
		
		/*System.out.println("queuePhoneNumberInfo(areaCode,startNbr,endNbr)资源入参：areaCode=sq.sz.js.cn,startNbr=82731231,endNbr=82731321");
		outXml = ossInterfaceForCrm.queuePhoneNumberInfo("cs.sz.js.cn", "51220274", "51220274");
		System.out.println("出参："+outXml);*/
		
		/*inXml = "<?xml version='1.0' encoding='UTF-8'?>" +
		"<root>" +
		"<uuId>1223322322233</uuId>"+
		"<areaCode>cs.sz.js.cn</areaCode>"+
		"<anIds>"+
		"<anId>125053029342</anId>"+
		"</anIds>"+
		"</root>";

		System.out.println("入参："+inXml);
		outXml = ossInterfaceForCrm.maketingSelectAn(inXml);
		System.out.println("出参："+outXml);*/
		
	}
}
