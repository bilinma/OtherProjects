package com.bilin.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestShell {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Ping
		Process process = Runtime.getRuntime().exec("ping www.baidu.com -l 1000 -n 4");
		BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
		String line = null;
		while ((line = buf.readLine()) != null) {
			System.out.println(line);
		}
		
		//traceroute
		Process process2 = Runtime.getRuntime().exec("traceroute www.baidu.com -m 20");
		BufferedReader buf2 = new BufferedReader(new InputStreamReader(process2.getInputStream(), "GBK"));
		String line2 = null;
		while ((line2 = buf2.readLine()) != null) {
			System.out.println(line2);
		}

	}

}
