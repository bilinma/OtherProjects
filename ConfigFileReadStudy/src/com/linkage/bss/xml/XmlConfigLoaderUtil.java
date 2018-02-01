package com.linkage.bss.xml;

import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlConfigLoaderUtil {
	private static Document doc;

	static{
		InputStream ips= null;
		try {
			ips = XmlConfigLoaderUtil.class.getClassLoader().getResourceAsStream("NewFile.xml");
			SAXReader sax = new SAXReader();
			doc = sax.read(ips);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			try {
				if (ips!=null){
					ips.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//
	public static String getSingleNodeValue(String nodePath){
		Element root = doc.getRootElement();
		return root.selectSingleNode(nodePath).getText();
	}

	public static void main(String[] args) {
		String value = XmlConfigLoaderUtil.getSingleNodeValue("./msgCondition/msg/param");	
		System.out.println(value);
	}

}
