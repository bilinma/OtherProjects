package com.ucloudlink.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
		TestNio.nioCopyFile("C:/aaa.txt", "C:/bbb.txt");

	}

	
	
}
