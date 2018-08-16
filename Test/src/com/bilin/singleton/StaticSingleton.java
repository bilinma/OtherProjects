package com.bilin.singleton;

/**
 * 延迟加载
 * 内部类保证线程安全
 * @author xiaobin.ma
 *
 */
public class StaticSingleton {

	private StaticSingleton(){
		System.out.println("StaticSingleton is create");
	}
	
	
	private static class SingletonHolder{
		private static StaticSingleton instance = new StaticSingleton();
	}
	
	public static StaticSingleton getInstance(){
		return SingletonHolder.instance;
	}
}
