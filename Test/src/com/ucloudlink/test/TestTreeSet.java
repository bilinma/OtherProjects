package com.ucloudlink.test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.ucloudlink.bo.Student;

public class TestTreeSet {

	public static void main(String[] args) {
		Set<Integer> set = new TreeSet<Integer>();
		set.add(5);
		set.add(8);
		set.add(2);
		set.add(1);
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
        TreeSet<String> ts = new TreeSet<>();
        ts.add("aaaaaaaa");
        ts.add("z");
        ts.add("wc");
        ts.add("nba");
        ts.add("cba");
        System.out.println(ts);
        
        
        TreeSet<Student> ts2 = new TreeSet<Student>();
        ts2.add(new Student());
        ts2.add(new Student());
        ts2.add(new Student());
        ts2.add(new Student());
        ts2.add(new Student());
        System.out.println(ts2);
	}
}
