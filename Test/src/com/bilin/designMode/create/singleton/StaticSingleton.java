package com.bilin.designMode.create.singleton;

/**
 * 延迟加载
 * 内部类保证线程安全
 * (其实说它完美，也不一定，如果在构造函数中抛出异常，实例将永远得不到创建，也会出错。所以说，十分完美的东西是没有的，我们只能根据实际情况，选择最适合自己应用场景的实现方法)
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
