package com.bilin.classloader;

public class TestClassLoader {
	public static int k=0;
	public static TestClassLoader t1 = new TestClassLoader("t1");
	public static TestClassLoader t2 = new TestClassLoader("t2");
	public static int i=print("i");
	public static int n=99;
	public int j =print("j");
	
	static{
		print("静态代码块");
	}
	
	public TestClassLoader(String str){
		System.out.println((++k)+":"+str+" i="+i+" n="+n);
		++i;
		++n;
	}
	
	{
		print("构造块");
	}
	
	
	public static int print(String str){
		System.out.println((++k)+":"+str+" i="+i+" n="+n);
		++n;
		return ++i;
	}
	public static void main(String[] args) {
		TestClassLoader t = new TestClassLoader("init");
	}

}
