package com.bilin.singleton;

/**
 * 单例饿汉式
 * @author xiaobin.ma
 *
 */
public class Singleton {

	private Singleton(){
		System.out.println("Singleton is create");
	}
	
	private static Singleton instance  =  new Singleton();
	
	public static Singleton getInstance(){
		return instance;
	}
}
