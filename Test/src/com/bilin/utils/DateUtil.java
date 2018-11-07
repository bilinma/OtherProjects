package com.bilin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class DateUtil {
	private static Logger logger = Logger.getLogger(DateUtil.class);

	public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String C_TIME_PATTON_CUSTOM = "yyyy-MM-dd HH:mm";

	public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
	public static final String C_DATA_PATTON_YYYYMMDD = "yyyyMMdd";

	public static final String C_DATA_PATTON_MMDDYYYY = "MM/dd/yyyy";

	public static final String C_TIME_PATTON_HHMMSS = "HH:mm:ss";

	public static final String C_PATTERN_YYYYMMSSHHMM = "yyyyMMddHHmm";
	public static final String C_PATTERN_YYYYMMSSHHMMSS = "yyyyMMddHHmmss";
	public static final String C_PATTERN_YYYYMMSSHHMMSSSSS = "yyyyMMddHHmmssSSS";
	
	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = 60000;
	public static final int C_ONE_HOUR = 3600000;
	public static final long C_ONE_DAY = 86400000L;

	// 线程不安全
	private static final SimpleDateFormat sdf = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);
	// 线程安全
	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

	public static DateFormat getDateFormat() {
		DateFormat df = threadLocal.get();
		if (df == null) {
			df = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);
			threadLocal.set(df);
		}
		return df;
	}

	public static String formatDate(Date date) throws ParseException {
		// return sdf.format(date);
		return getDateFormat().format(date);
	}

	public static Date parse(String strDate) throws ParseException {

		// return sdf.parse(strDate);
		return getDateFormat().parse(strDate);
	}

	public static String getDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		return format.format(new Date());
	}

	public static String getTimeZone(String timeZone) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		format.setTimeZone(TimeZone.getTimeZone(timeZone));
		return format.format(new Date());
	}

	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return currDate;
	}

	public static String getCurrentDateStr(String strFormat) {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return format(currDate, strFormat);
	}

	public static Date parseDate(String dateValue) {
		return parseDate("yyyy-MM-dd", dateValue);
	}

	public static Date parseDate(String strFormat, String dateValue) {
		return parseDate(strFormat, dateValue, null);
	}

	public static Date parseDate(String strFormat, String dateValue, String timeZone) {
		if (dateValue == null) {
			return null;
		}
		if (strFormat == null) {
			strFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		if (StringUtils.isNotBlank(timeZone)) {
			dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		}
		Date newDate = null;
		try {
			newDate = dateFormat.parse(dateValue);
		} catch (ParseException pe) {
			newDate = null;
		}
		System.out.println(newDate);
		return newDate;
	}

	public static String format(Date aTs_Datetime) {
		return format(aTs_Datetime, "yyyy-MM-dd");
	}

	public static String format(Date aTs_Datetime, String as_Pattern) {
		if ((aTs_Datetime == null) || (as_Pattern == null)) {
			return null;
		}
		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Pattern);
		return dateFromat.format(aTs_Datetime);
	}

	public static String format(Date date, String pattern, String timeZone) {
		if ((date == null) || (date.getTime() <= 0L)) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		if (StringUtils.isEmpty(timeZone)) {
			timeZone = "GMT+0";
		}
		format.setTimeZone(TimeZone.getTimeZone(timeZone));
		return format.format(date);
	}

	public static long format(String dateStr, String pattern, String timeZone) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		if (StringUtils.isEmpty(timeZone)) {
			timeZone = "GMT+0";
		}
		format.setTimeZone(TimeZone.getTimeZone(timeZone));
		long time = format.parse(dateStr).getTime();
		return time;
	}

	public static Date geLastWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DAY_OF_MONTH, -7);
		return cal.getTime();
	}

	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DAY_OF_MONTH, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	public static Date getThisWeekDay(Date date, int weekDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}

		cal.setFirstDayOfWeek(Calendar.MONDAY);

		int day = cal.get(Calendar.DAY_OF_WEEK);

		cal.add(Calendar.DAY_OF_MONTH, cal.getFirstDayOfWeek() - day + weekDay - 2);
		return cal.getTime();
	}

	public static Date getThisWeekDay(Date date, int weekDay, int hour, int minute, int second, String timeZone) {
		Calendar cal = Calendar.getInstance();
		if (StringUtils.isNotBlank(timeZone)) {
			cal.setTimeZone(TimeZone.getTimeZone(timeZone));
		}
		cal.setTime(date);

		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}

		cal.setFirstDayOfWeek(Calendar.MONDAY);

		int day = cal.get(Calendar.DAY_OF_WEEK);

		cal.add(Calendar.DAY_OF_MONTH, cal.getFirstDayOfWeek() - day + weekDay - 2);
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getNextWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DAY_OF_MONTH, 7);
		return cal.getTime();
	}

	public static int getMonth(Date date) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	public static Date firstDayOfMonth(Date date, int month_offset) throws Exception {
		Calendar c = addCalendarMonths(date, month_offset);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, c.getMinimum(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.getMinimum(Calendar.MINUTE));
		c.set(Calendar.SECOND, c.getMinimum(Calendar.SECOND));
		return c.getTime();
	}

	public static Date firstDayOfMonth(Date date, int hour, int minute, int second, String timeZone) {
		Calendar cal = Calendar.getInstance();
		if (StringUtils.isNotBlank(timeZone)) {
			cal.setTimeZone(TimeZone.getTimeZone(timeZone));
		}
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date middleDayOfMonth(Date date, int month_offset) throws Exception {
		Calendar c = addCalendarMonths(date, month_offset);
		c.set(Calendar.DAY_OF_MONTH, 15);
		c.set(Calendar.HOUR_OF_DAY, c.getMaximum(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.getMaximum(Calendar.MINUTE));
		c.set(Calendar.SECOND, c.getMaximum(Calendar.SECOND));
		return c.getTime();
	}

	public static Date middleDayOfMonth(Date date, int hour, int minute, int second, String timeZone) {
		Calendar cal = Calendar.getInstance();
		if (StringUtils.isNotBlank(timeZone)) {
			cal.setTimeZone(TimeZone.getTimeZone(timeZone));
		}
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date lastDayOfMonth(Date date, int month_offset) throws Exception {
		Calendar c = addCalendarMonths(date, month_offset);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, c.getMaximum(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.getMaximum(Calendar.MINUTE));
		c.set(Calendar.SECOND, c.getMaximum(Calendar.SECOND));
		return c.getTime();
	}

	public static Date lastDayOfMonth(Date date, int hour, int minute, int second, String timeZone) {
		Calendar cal = Calendar.getInstance();
		if (StringUtils.isNotBlank(timeZone)) {
			cal.setTimeZone(TimeZone.getTimeZone(timeZone));
		}
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Calendar addCalendarMonths(Date date, int month_offset) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month_offset);
		return c;
	}

	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / 86400000L;

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static void main(String[] args) throws Exception {
		System.out.println(daysBetween(new Date(1541561097526L), new Date(1541561101545L)));
	}

}
