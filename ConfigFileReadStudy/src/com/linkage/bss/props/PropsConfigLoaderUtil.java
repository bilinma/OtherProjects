package com.linkage.bss.props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 配置工具，会读取配置文件中的内容，放到内存
 * @author ma_xiaobin
 */
public class PropsConfigLoaderUtil {
	private static Properties props=new Properties();
	static{
		InputStream ips=PropsConfigLoaderUtil.class.getClassLoader()
			.getResourceAsStream("constants.properties");		
		try {
			props.load(ips);
		} catch (IOException e) {
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
	public static String getConfigInfo(String key){
		return props.getProperty(key);
	}
	
	public static void main(String[] args){
		String value = PropsConfigLoaderUtil.getConfigInfo("crmIndexDatabase");
		System.out.println(value);
	}
}
