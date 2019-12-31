package com.bilin.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream概述

是用函数式编程方式在集合类上进行复杂操作的工具，更像一个高级版本的 Iterator

原始版本的 Iterator，用户只能显式地一个一个遍历元素并对其执行某些操作

高级版本的 Stream，用户只要给出需要对其包含的元素执行什么操作

Stream 会隐式地在内部进行遍历，做出相应的数据转换

而和迭代器又不同的是，Stream 可以并行化操作

借助于 Lambda 表达式，极大的提高编程效率和程序可读性

 * @author bilinma
 *
 */
public class javaStream {

	public static void main(String [] args){
		//Collection提供了两个方法.stream()与paralleStream()
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
        Stream<Integer> stream = list.stream();//串行流
        stream.forEach(System.out::println);
        
        Stream<Integer> integerStream = list.parallelStream();//并行流
        integerStream.forEach(System.out::println);
        
        
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
