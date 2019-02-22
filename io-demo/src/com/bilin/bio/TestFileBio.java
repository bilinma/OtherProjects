package com.bilin.bio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;

public class TestFileBio {
	
	public static void main(String[] args) throws IOException {
		TestFileBio.bioCopyFile1("C:/111.xlsx", "C:/b111.xlsx");
		TestFileBio.bioCopyFile2("C:/111.xlsx", "C:/b222.xlsx");
//		TestFileBio.bioCopyFile3("C:/linux命令.txt", "C:/333.txt");
//		TestFileBio.bioCopyFile4("C:/linux命令.txt", "C:/444.txt");
//		TestFileBio.bioCopyFile5("C:/linux命令.txt", "C:/555.txt");
		
		//TestFileBio.bioReadFile("C:/aaa.txt");
	}
	
	/**
	 * InputStream、OutputStream（字节流）
	 * @param resource
	 * @param destination
	 * @throws IOException
	 */
	public static void bioCopyFile1(String resource,String destination) throws IOException{
		long startTime = System.currentTimeMillis();
		InputStream fis = null;
		OutputStream fos = null;
		try {
			fis =  new FileInputStream(resource);
			fos = new FileOutputStream(destination);
			byte[] bytes=new byte[1024];
			while(true){
				int num = fis.read(bytes);
				if(num==-1)break;
				fos.write(bytes, 0, num);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fis.close();
			fos.close();
		}
		long end = System.currentTimeMillis();
		System.out.println("bioCopyFile1耗费时间:" + (end - startTime));
	}
	
	/**
	 * BufferedInputStream、BufferedOutputStream（缓存字节流）
	 * 使用方式和字节流差不多，但是效率更高（推荐使用）
	 * @param resource
	 * @param destination
	 * @throws IOException
	 */
	public static void bioCopyFile2(String resource,String destination) throws IOException{
		long startTime = System.currentTimeMillis();
		InputStream in = null;
		OutputStream out = null;
		try {
			//读取文件(缓存字节流)
	        in = new BufferedInputStream(new FileInputStream(resource));
	        //写入相应的文件
	        out = new BufferedOutputStream(new FileOutputStream(destination));
	        //读取数据
	        //一次性取多少字节
	        byte[] bytes = new byte[2048];
	        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
	        int n = -1;
	        //循环取出数据
	        while ((n = in.read(bytes,0,bytes.length)) != -1) {
	            //写入相关文件
	            out.write(bytes, 0, n);
	        }
	        //清除缓存
	        out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭流
			in.close();
			out.close();
		}
		long end = System.currentTimeMillis();
		System.out.println("bioCopyFile2耗费时间:" + (end - startTime));
	}
	
	
	/**
	 * InputStreamReader、OutputStreamWriter
	 * （字节流，这种方式不建议使用，不能直接字节长度读写）。使用范围用做字符转换
	 * 
	 * @param resource
	 * @param destination
	 * @throws IOException
	 */
	public static void bioCopyFile3(String resource,String destination) throws IOException{
		long startTime = System.currentTimeMillis();
		InputStreamReader in = null;
		OutputStreamWriter out = null;
		try {
			//读取文件(字节流)
	        in = new InputStreamReader(new FileInputStream(resource),"GBK");
	        //写入相应的文件
	        out = new OutputStreamWriter(new FileOutputStream(destination));
	        //循环取出数据
	        int len = -1;
	        while ((len = in.read()) != -1) {
	            //写入相关文件
	            out.write(len);
	        }
	        //清除缓存
	        out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭流
			in.close();
			out.close();
		}
		long end = System.currentTimeMillis();
		System.out.println("bioCopyFile3耗费时间:" + (end - startTime));
	}
	
	/**
	 * BufferedReader、BufferedWriter(缓存流，提供readLine方法读取一行文本)
	 * 
	 * @param resource
	 * @param destination
	 * @throws IOException
	 */
	public static void bioCopyFile4(String resource,String destination) throws IOException{
		long startTime = System.currentTimeMillis();
		BufferedReader in = null;
		BufferedWriter out = null;
		try {
			//读取文件(字符流)
	        in = new BufferedReader(new InputStreamReader(new FileInputStream(resource),"GBK"));//这里主要是涉及中文
	        //BufferedReader in = new BufferedReader(new FileReader("d:\\1.txt")));
	        //写入相应的文件
	        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destination),"GBK"));
	        //BufferedWriter out = new BufferedWriter(new FileWriter("d:\\2.txt"))；
	        //读取数据
	        //循环取出数据
	        String str = null;
	        while ((str = in.readLine()) != null) {
	            //写入相关文件
	            out.write(str);
	            out.newLine();
	        }
	        //清楚缓存
	        out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭流
			in.close();
			out.close();
		}
		long end = System.currentTimeMillis();
		System.out.println("bioCopyFile4耗费时间:" + (end - startTime));
	}
	
	
	/**
	 * Reader、PrintWriter（PrintWriter这个很好用，在写数据的同时可以格式化）
	 * 
	 * @param resource
	 * @param destination
	 * @throws IOException
	 */
	public static void bioCopyFile5(String resource,String destination) throws IOException{
		long startTime = System.currentTimeMillis();
		Reader in = null;
		PrintWriter out = null;
		try {
			//读取文件(字节流)
	        in = new InputStreamReader(new FileInputStream(resource),"GBK");
	        //写入相应的文件
	        out = new PrintWriter(new FileWriter(destination));
	        //读取数据
	        int len = -1;
	        while ((len = in.read()) != -1) {
	            //写入相关文件
	            out.write(len);
	        }
	        //清楚缓存
	        out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭流
			in.close();
			out.close();
		}
		long end = System.currentTimeMillis();
		System.out.println("bioCopyFile5耗费时间:" + (end - startTime));
	}
	
	
	
	public static void bioReadFile(String resource) throws IOException{
		
		long startTime = System.currentTimeMillis();
		FileInputStream fis=  new FileInputStream(resource);
		StringBuffer sb = new StringBuffer();
		
		//按字节块读
		byte[] bytes=new byte[1024];
		while(true){
			int num = fis.read(bytes,0,bytes.length);
			if(num==-1)break;
			sb.append(new String(bytes,0,num,"GBK"));
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"gbk"));
		
		//按字符块读
		/*char[] chars=new char[1024];
		while(true){
			int num =br.read(chars);
			if(num==-1)break;
			String temp=new String(chars,0,num);
			sb.append(temp);
		}*/
		
		//按行读
		/*String line = null; 
        while ((line = br.readLine()) != null) {
        	sb.append(line + "\n");
        	System.out.println("*************:"+line);
        }*/
        
		fis.close();
		System.out.println(sb);
		long end = System.currentTimeMillis();
		System.out.println("bioReadFile耗费时间:" + (end - startTime));
	}
	
	
	
}
