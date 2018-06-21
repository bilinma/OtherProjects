package com.ucloudlink.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class TestNio {
	
	public static void nioCopyFile(String resource,String destination) throws IOException{
		FileInputStream fis = new FileInputStream(resource);
		FileOutputStream fos =new FileOutputStream(destination);
		FileChannel readChannel = fis.getChannel();
		FileChannel writeChannel = fos.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while(true){
			buffer.clear();
			int len = readChannel.read(buffer);
			if(len == -1){
				break;
			}
			System.out.println("position="+buffer.position()+" limit="+buffer.limit()+" capacity="+buffer.capacity());
			buffer.flip();
			System.out.println("position="+buffer.position()+" limit="+buffer.limit()+" capacity="+buffer.capacity());
			writeChannel.write(buffer);
		}
		readChannel.close();
		writeChannel.close();
	}

	public static void main(String[] args) throws IOException {
		//TestNio.nioCopyFile("C:/aaa.txt", "C:/bbb.txt");
		TestNio.wordSort("C:/aaa.txt");
	}

	
	public static void wordSort(String filePath) throws IOException{
		File file=new File(filePath);
		if(file.exists()){
			FileInputStream fis = new FileInputStream(file);
			FileChannel readChannel = fis.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			StringBuffer sb = new StringBuffer();
			while(true){
				buffer.clear();
				int len = readChannel.read(buffer);
				if(len == -1){
					break;
				}
				buffer.flip();
				byte[] b = buffer.array();
				System.out.println(len);
				sb.append(new String(b));
			}
			System.out.println("-----------------------");
			
			StringTokenizer st = new StringTokenizer(sb.toString(), ", !.");// 按逗号/空格/叹号分割,其他字符自行添加
			Map<String,Integer> map = new HashMap<String,Integer>();
	        while (st.hasMoreElements()) {
	        	String s =  st.nextElement().toString().trim();
	        	Integer sum = map.get(s);
	        	System.out.println(sum);
	        	if(sum ==null||sum==0){
	        		map.put(s, 1);
	        	}else{
	        		map.put(s, sum+1);
	        	}
	        }
	        System.out.println(map);
	        System.out.println("-----------------------");
	        // 将map.entrySet()转换成list
	        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
	        // 通过比较器来实现排序
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	            @Override
	            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
	                // 降序排序
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        });
	        for (Map.Entry<String, Integer> mapping : list) {
	            System.out.println("key:"+mapping.getKey() + "  value:" + mapping.getValue());
	        }
		}
	}
	
	
	
}
