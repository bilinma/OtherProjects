package com.bilin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static final String date_format = "yyyy-MM-dd HH:mm:ss";
	//线程不安全
	private static final SimpleDateFormat sdf = new SimpleDateFormat(date_format);
	//线程安全
	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

	public static DateFormat getDateFormat() {
		DateFormat df = threadLocal.get();
		if (df == null) {
			df = new SimpleDateFormat(date_format);
			threadLocal.set(df);
		}
		return df;
	}

	public static String formatDate(Date date) throws ParseException {
		//return sdf.format(date);
		return getDateFormat().format(date);
	}

	public static Date parse(String strDate) throws ParseException {

		//return sdf.parse(strDate);
		return getDateFormat().parse(strDate);
	}
	
	public static String getDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		return format.format(new Date());
	}
	
}
