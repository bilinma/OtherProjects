package com.bilin.nio.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class TestFileNio {

	public static void nioCopyFile(String resource, String destination) throws IOException {
		long startTime = System.currentTimeMillis();
		FileInputStream fis = new FileInputStream(resource);
		FileOutputStream fos = new FileOutputStream(destination);
		FileChannel readChannel = fis.getChannel();
		FileChannel writeChannel = fos.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (true) {
			if (readChannel.read(buffer) == -1) {
				break;
			}
			//System.out.println("position=" + buffer.position() + " limit=" + buffer.limit() + " capacity=" + buffer.capacity());
			buffer.flip();
			//System.out.println("position=" + buffer.position() + " limit=" + buffer.limit() + " capacity=" + buffer.capacity());
			writeChannel.write(buffer);
			buffer.clear();
		}
		readChannel.close();
		writeChannel.close();
		long end = System.currentTimeMillis();
		System.out.println("nioCopyFile耗费时间:" + (end - startTime));
	}

	public static void main(String[] args) throws IOException {
		TestFileNio.nioCopyFile("C:/111.xlsx", "C:/222.xlsx");
		// TestFileNio.wordSort("C:/aaa.txt");
//		nioCopyTest1();
//		nioCopyTest2();
//		nioCopyTest3();
	}

	/**
	 * 通道之间的数据传输(直接缓冲区的模式)
	 *
	 */
	private static void nioCopyTest3() throws IOException {
		long startTime = System.currentTimeMillis();

		FileChannel inChannel = FileChannel.open(Paths.get("E:\\ 1.avi"), StandardOpenOption.READ);

		FileChannel outChennel = FileChannel.open(Paths.get("E:\\ 13.avi"), StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

		outChennel.transferFrom(inChannel, 0, inChannel.size());

		long end = System.currentTimeMillis();
		System.out.println("nioCopyTest3耗费时间:" + (end - startTime));
	}

	/**
	 * 使用直接缓冲区完成文件的复制(内存映射文件)
	 */
	private static void nioCopyTest2() throws IOException {
		long startTime = System.currentTimeMillis();

		FileChannel inChannel = FileChannel.open(Paths.get("E:\\ 1.avi"), StandardOpenOption.READ);

		FileChannel outChennel = FileChannel.open(Paths.get("E:\\ 12.avi"), StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

		// 内存映射文件(什么模式 从哪开始 到哪结束)
		MappedByteBuffer inMappeBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappeBuf = outChennel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

		// 直接都缓冲区进行数据的读写操作
		byte[] dst = new byte[inMappeBuf.limit()];
		inMappeBuf.get(dst);
		outMappeBuf.put(dst);

		inChannel.close();
		outChennel.close();
		long end = System.currentTimeMillis();
		System.out.println("nioCopyTest2耗费时间:" + (end - startTime));
	}

	/**
	 * 非直接缓冲区 文件的复制
	 * 
	 * @throws IOException
	 */
	private static void nioCopyTest1() throws IOException {
		long startTime = System.currentTimeMillis();
		FileInputStream fileInputStream = new FileInputStream("E:\\ 1.avi");
		FileOutputStream fileOutputStream = new FileOutputStream("E:\\ 11.avi");

		// 获取通道
		FileChannel inChannel = fileInputStream.getChannel();
		FileChannel outChanel = fileOutputStream.getChannel();

		// 分配缓冲区的大小
		ByteBuffer buf = ByteBuffer.allocate(1024);

		// 将通道中的数据存入缓冲区
		while (inChannel.read(buf) != -1) {
			buf.flip();// 切换读取数据的模式
			outChanel.write(buf);
			buf.clear();
		}
		outChanel.close();
		inChannel.close();
		fileInputStream.close();
		fileOutputStream.close();
		long end = System.currentTimeMillis();
		System.out.println("nioCopyTest1耗费时间:" + (end - startTime));
	}

	
	/**
	 * 单词分析
	 * @param filePath
	 * @throws IOException
	 */
	public static void wordSort(String filePath) throws IOException {
		File file = new File(filePath);
		if (file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			FileChannel readChannel = fis.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			StringBuffer sb = new StringBuffer();
			while (true) {
				buffer.clear();
				int len = readChannel.read(buffer);
				if (len == -1) {
					break;
				}
				buffer.flip();
				byte[] b = buffer.array();
				sb.append(new String(b));
			}
			System.out.println("-----------------------");

			StringTokenizer st = new StringTokenizer(sb.toString(), ", !.");// 按逗号/空格/叹号分割,其他字符自行添加
			Map<String, Integer> map = new HashMap<String, Integer>();
			while (st.hasMoreElements()) {
				String s = st.nextElement().toString().trim();
				Integer sum = map.get(s);
				System.out.println(sum);
				if (sum == null || sum == 0) {
					map.put(s, 1);
				} else {
					map.put(s, sum + 1);
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
				System.out.println("key:" + mapping.getKey() + "  value:" + mapping.getValue());
			}
		}
	}

}
