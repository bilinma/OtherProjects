package com.bilin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String input1 = sc.next();
		
		String input2 = sc.next();
		
		boolean flag = false;
		
		//String input1="BBDDCFFEL";
		//String input2="LCEFB";
		// 1、获取 input1 的字符集合
		List<Character> listInput1 =new ArrayList<Character>();
		for(int i=0 ;i<input1.length(); i++){
			char a = input1.charAt(i);
			listInput1.add(a);
		}
		
		// 2、获取 input2 的字符集合
		List<Character> listInput2 =new LinkedList<Character>();
		for(int i=0 ;i<input2.length(); i++){
			char b = input2.charAt(i);
			listInput2.add(b);
		}
		// 3、检索listInput2 集合字符是否在 listInput1 中包含，包含则移除listInput2对应字符
		Iterator<Character> it2 = listInput2.iterator();
		while (it2.hasNext()) {
			Character n = it2.next();
			if(listInput1.contains(n)){
				listInput1.remove(n);
				it2.remove();
			}
		}
		// 4、统计是否完全包含input2 中是字符完全包含则应该移除完了
		if(listInput2.size()==0){
			flag = true;
		}
		System.out.print(flag);
	}

}
