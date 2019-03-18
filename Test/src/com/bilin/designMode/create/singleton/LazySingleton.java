package com.bilin.designMode.create.singleton;

/**
 * 延迟加载但存在线程安全问题
 * 多线程下实例有可能不唯一
 * 使用syncronized 又影响性能
 * @author xiaobin.ma
 *
 */
public class LazySingleton {

	private LazySingleton(){
		System.out.println("LazySingleton is create");
	}
	private static LazySingleton instance  = null;
	
	public static synchronized LazySingleton getInstance(){
		if(instance == null){
			instance = new LazySingleton();
		}
		return instance;
	}
	
	
}
