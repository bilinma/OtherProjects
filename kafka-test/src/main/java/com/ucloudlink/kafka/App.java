package com.ucloudlink.kafka;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println(toGMT(new Date()));
	}

	public static String toGMT(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Calendar cal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		//Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
		//date = cal.getTime();
		//format.setCalendar(cal);
		//format.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		return format.format(date);
	}
}
