package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PrameUtil {

	public String getPrame(String key){
		String value = "";
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("p.properties");
		Properties p = new Properties();
		try {
			p.load(is);
			value = p.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	}
}
