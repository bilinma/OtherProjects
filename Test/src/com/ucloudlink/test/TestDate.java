package com.ucloudlink.test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestDate {

	public static void main(String[] args) {
		
		Date date = new Date(1523601688059L);
		System.out.println(date.getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("北京时间："+dateFormat.format(date));
		
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		System.out.println("格林尼治时间："+dateFormat.format(date));
		
		DecimalFormat df = new DecimalFormat("0.00");
		double a = 1006.565;
		double b = 0.920;
		double c = a + b ;
		System.out.println(df.format(c));
	}

}
