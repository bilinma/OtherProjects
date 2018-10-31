package com.bilin.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
	public static Date geLastWeekDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekDay(date));
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}

	public static Date getThisWeekDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(cal.getFirstDayOfWeek());
		System.out.println(day);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	public static Date getNextWeekDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekDay(date));
		cal.add(Calendar.DATE, 7);
		return cal.getTime();
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse("2018-10-30");
			System.out.println("今天是" + sdf.format(date));
			System.out.println("上周一" + sdf.format(geLastWeekDay(date)));
			System.out.println("本周一" + sdf.format(getThisWeekDay(date)));
			System.out.println("下周一" + sdf.format(getNextWeekDay(date)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
