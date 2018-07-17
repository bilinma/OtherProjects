package com.bilin.bio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestFileBio {
	
	public static void main(String[] args) throws IOException {
		//TestFileBio.bioCopyFile("C:/111.xlsx", "C:/222.xlsx");
		
		TestFileBio.bioReadFile("C:/aaa.txt");
	}
	
	public static void bioCopyFile(String resource,String destination) throws IOException{
		long startTime = System.currentTimeMillis();
		FileInputStream fis=  new FileInputStream(resource);
		FileOutputStream fos = new FileOutputStream(destination);
		byte[] cbuf=new byte[1024];
		while(true){
			int num = fis.read(cbuf);
			if(num==-1)break;
			fos.write(cbuf, 0, num);
		}
		fos.flush();
		long end = System.currentTimeMillis();
		System.out.println("nioCopyTest1耗费时间:" + (end - startTime));
	}
	
	
	public static void bioReadFile(String resource) throws IOException{
		
		long startTime = System.currentTimeMillis();
		FileInputStream fis=  new FileInputStream(resource);
		byte[] cbuf=new byte[1024];
		StringBuffer sb = new StringBuffer();
		while(true){
			int num = fis.read(cbuf);
			if(num==-1)break;
			sb.append(new String(cbuf));
		}
		System.out.println(sb);
		long end = System.currentTimeMillis();
		System.out.println("nioCopyTest1耗费时间:" + (end - startTime));
		
		
		/* 编码
		long startTime = System.currentTimeMillis();
		FileInputStream fis=  new FileInputStream(resource);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		char[] cbuf=new char[1024];
		StringBuffer sb = new StringBuffer();
		while(true){
			int num =br.read(cbuf);
			if(num==-1)break;
			String temp=new String(cbuf,0,num);
			sb.append(temp);
		}
		System.out.println(sb);
		long end = System.currentTimeMillis();
		System.out.println("nioCopyTest1耗费时间:" + (end - startTime));*/
	}
}
