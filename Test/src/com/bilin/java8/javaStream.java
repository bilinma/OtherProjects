package com.bilin.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class javaStream {

	public static void main(String [] args){
		//Collection提供了两个方法.stream()与paralleStream()
		List<Integer> list = new ArrayList<>();
        Stream<Integer> stream = list.stream();//串行流
        Stream<Integer> integerStream = list.parallelStream();//并行流
        
        //通过Arrays中的Stream()获取一个数组流
        Integer[] integers ={};
        Stream<Integer> stream1 = Arrays.stream(integers);
        
        //通过Stream类中静态方法of()
        Stream<String> stream2 = Stream.of("aaa", "bbb");

        //创建无限流（无穷的数据）
        //1.生成
        Stream.generate(() -> Math.random() * 10).limit(5).forEach(System.out::println);
        //2.迭代
        //通过迭代的方式（一元运算）生成5个数
        Stream.iterate(0,x->x+2).limit(5).forEach(System.out::println);
	}
}
