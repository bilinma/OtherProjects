package com.bilin.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestJava8Lambda {
	public static void main(String args[]) {

		List<String> names1 = new ArrayList<String>();
		names1.add("Google ");
		names1.add("Runoob ");
		names1.add("Taobao ");
		names1.add("Baidu ");
		names1.add("Sina ");

		List<String> names2 = new ArrayList<String>();
		names2.add("Google ");
		names2.add("Runoob ");
		names2.add("Taobao ");
		names2.add("Baidu ");
		names2.add("Sina ");

		TestJava8Lambda tester = new TestJava8Lambda();

		System.out.println("使用 Java 7 语法: ");
		tester.sortUsingJava7(names1);
		System.out.println(names1);

		System.out.println("使用 Java 8 语法: ");
		tester.sortUsingJava8(names2);
		System.out.println(names2);

		//Lambda 表达式
		// 类型声明
		MathOperation addition = (int a, int b) -> a + b;

		// 不用类型声明
		MathOperation subtraction = (a, b) -> a - b;
	}

	interface MathOperation {
		int operation(int a, int b);
	}

	// 使用 java 7 排序
	private void sortUsingJava7(List<String> names) {
		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
	}

	// 使用 java 8 排序
	private void sortUsingJava8(List<String> names) {
		//
		Collections.sort(names, (String a, String b) -> {
			return b.compareTo(a);
		});
		// 简化：去掉大括号{}以及return关键字
		Collections.sort(names, (String a, String b) -> b.compareTo(a));
		// 简化：去掉参数类型，Java编译器可以自动推导出参数类型，所以你可以不用再写一次类型
		Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
	}
}