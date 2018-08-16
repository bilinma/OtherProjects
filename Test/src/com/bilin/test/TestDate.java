package com.bilin.test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

import com.bilin.utils.DateUtil;

public class TestDate {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Date date = new Date(1523601688059L);
		System.out.println(date.getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("北京时间：" + dateFormat.format(date));

		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		System.out.println("格林尼治时间：" + dateFormat.format(date));

		DecimalFormat df = new DecimalFormat("0.00");
		double a = 1006.565;
		double b = 0.920;
		double c = a + b;
		System.out.println(df.format(c));

		for (int i = 0; i < 3; i++) {
			new TestSimpleDateFormatThreadSafe().start();
		}

	}

	public static class TestSimpleDateFormatThreadSafe extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					this.join(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					System.out.println(this.getName() + ":" + DateUtil.parse("2013-05-24 06:02:20"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
