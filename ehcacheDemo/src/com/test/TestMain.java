package com.test;

import com.util.EhcacheUtil;
import com.util.PrameUtil;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EhcacheUtil eUtil = new EhcacheUtil();
		String one = eUtil.getPrame("1");
		
		System.out.println(one);
		

	}

}
