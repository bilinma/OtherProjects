package com.bilin.test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class JVMTest {

	// 在Run as ->Run Configurations中输入设置“-Xmx8m -Xms8m -XX:+PrintGCDetails”可以参看垃圾回收机制原理
	public static void main(String[] args) {
		long maxMemory = Runtime.getRuntime().maxMemory();// 返回Java虚拟机试图使用的最大内存量。
		Long totalMemory = Runtime.getRuntime().totalMemory();// 返回Java虚拟机中的内存总量。
		System.out.println("MAX_MEMORY =" + maxMemory + "(字节)、" + (maxMemory / (double) 1024 / 1024) + "MB");
		System.out.println("TOTAL_ MEMORY = " + totalMemory + "(字节)" + (totalMemory / (double) 1024 / 1024) + "MB");
		String str = "www.baidu.com";
		while (true) {
			str += str + new Random().nextInt(88888888) + new Random().nextInt(99999999);
		}
	}

}
