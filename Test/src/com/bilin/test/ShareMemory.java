package com.bilin.test;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class ShareMemory {

	public static void main(String[] args) throws Exception {
		String filename= "C:/shm.lock";
		MappedByteBuffer mapBuf = ShareMemory.readShareRom(filename);
		boolean flag = ShareMemory.startWrite(mapBuf);
		
		try {
			if(flag){
				//从文件的第二个字节开始，依次写入 A-Z 字母，第一个字节指明了当前操作的位置    
			    for(int i=65;i<91;i++){    
			        int index = i-64;    
			        System.out.println(System.currentTimeMillis() +  " position:" + index +" write:" + (char)i);    
			        mapBuf.put(index,(byte)i);//index 位置写入数据    
			        Thread.sleep(1000);    
			    }
			}else{
				System.out.println("内存锁");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ShareMemory.StopWrite(mapBuf);
		}
		
	}
	
	public static MappedByteBuffer readShareRom(String filename) throws Exception{
		//获得一个只读的随机存取文件对象
		RandomAccessFile raFile = new RandomAccessFile(filename,"r");
		//获得相应的文件通道
		FileChannel fc = raFile.getChannel();
		//取得文件的实际大小
		int size = (int)fc.size();
		//获得共享内存缓冲区，该共享内存只读
		MappedByteBuffer mapBuf = fc.map(MapMode.READ_ONLY,0,size);
		return mapBuf;
	}
	
	public static MappedByteBuffer writeShareRom(String filename) throws Exception{
		
		//获得一个可读写的随机存取文件对象
		RandomAccessFile raFile = new RandomAccessFile(filename,"rw");
		//获得相应的文件通道
		FileChannel fc = raFile.getChannel();
		//获得文件的实际大小，以便映像到共享内存
		int size = (int)fc.size();
		//获得共享内存缓冲区，该共享内存可读写
		MappedByteBuffer mapBuf = fc.map(MapMode.READ_WRITE,0,size);
		return mapBuf;
	}
	
	
	public static boolean startWrite(MappedByteBuffer mapBuf) {
		//获取头部消息：存取权限
		int mode = mapBuf.getInt();
		System.out.println("mode="+mode);
		if (mode == 0) { // 标志为0，则表示可写
			mode = 1; // 置标志为1，意味着别的应用不可写该共享内存
			System.out.println("position="+mapBuf.position()+" limit="+mapBuf.limit()+" capacity="+mapBuf.capacity());
			mapBuf.flip();
			System.out.println("position="+mapBuf.position()+" limit="+mapBuf.limit()+" capacity="+mapBuf.capacity());
			mapBuf.putInt(mode); // 写如共享内存的头部信息
			return true;
		} else {
			return false; // 指明已经有应用在写该共享内存，本应用不可写该共享内存
		}
	}

	public static boolean StopWrite(MappedByteBuffer mapBuf) {
		//获取头部消息：存取权限
		int mode = mapBuf.getInt();
		mode = 0; // 释放写权限
		mapBuf.flip();
		mapBuf.putInt(mode); // 写入共享内存头部信息
		return true;
	}
}
