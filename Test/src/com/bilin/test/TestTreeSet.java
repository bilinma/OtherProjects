package com.bilin.test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.bilin.bo.Student;

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
        
        //对象比较器
        TreeSet<Student> ts2 = new TreeSet<Student>();
        ts2.add(new Student(0));
        ts2.add(new Student(1));
        ts2.add(new Student(2));
        ts2.add(new Student(3));
        ts2.add(new Student(4));
        for(Student s : ts2){
        	System.out.println(s.getId());
        }
        //TreeSet 添加比较器
        TreeSet<Student> ts3 = new TreeSet<Student>(new Comparator<Student>(){
        	@Override
            public int compare(Student s1, Student s2) {
        		if(s1.getId()>s2.getId()){
        			return -1;
        		}else if(s1.getId()>s2.getId()){
        			return 1;
        		}else{
        			return 0;
        		}
        		
            }
    	});
        ts3.add(new Student(0));
        ts3.add(new Student(1));
        ts3.add(new Student(2));
        ts3.add(new Student(3));
        ts3.add(new Student(4));
        for(Student s : ts3){
        	System.out.println(s.getId());
        }
	}
}
