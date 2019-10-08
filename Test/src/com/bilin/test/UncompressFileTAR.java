package com.bilin.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;

public class UncompressFileTAR {

	public static final String EXT = ".tar.gz";

	/**
	 * @param compressFile
	 *            need decompress file
	 * @param destDir
	 * 
	 */
	public static String decompress(String pathname) {
		File filed = new File(pathname);
		System.out.println("解压前的文件名称：" + pathname);
		if (!filed.exists() || !filed.getName().endsWith(EXT)) {
			return "文件不存在或格式错误";
		}

		// begin decompress
		String fileName = null;
		FileInputStream fis;
		ArchiveInputStream in = null;
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			fis = new FileInputStream(filed);
			GZIPInputStream is = new GZIPInputStream(new BufferedInputStream(fis));
			
			in = new ArchiveStreamFactory().createArchiveInputStream("tar", is);

			String outFileName = getFileName(pathname);
			bufferedInputStream = new BufferedInputStream(in);
			TarArchiveEntry entry = (TarArchiveEntry) in.getNextEntry();
			while (entry != null) {
				String name = entry.getName();
				System.out.println(name);
				String[] names = name.split("/");
				fileName = outFileName;
				// System.out.println(fileName);
				for (int i = 0; i < names.length; i++) {
					String str = names[i];
					fileName = fileName + File.separator + str;

				}
				if (name.endsWith("/")) {
					mkFolder(fileName);
				} else {
					File file = mkFile(fileName);
					System.out.println(fileName);
					bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
					int b = 0;
					while ((b = bufferedInputStream.read()) != -1) {
						bufferedOutputStream.write(b);
					}
					bufferedOutputStream.flush();
					bufferedOutputStream.close();
				}
				entry = (TarArchiveEntry) in.getNextEntry();
			}

			System.out.println("解压后的文件名称：" + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArchiveException e) {
			e.printStackTrace();
		} finally {
			if (bufferedInputStream != null) {
				try {
					bufferedInputStream.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileName;
	}

	private static void mkFolder(String fileName) {
		File f = new File(fileName);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	private static File mkFile(String fileName) {
		File f = new File(fileName);
		try {
			f.getParentFile().mkdirs();
			f.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public static String getFileName(String f) {
		String fname = "";
		int i = f.lastIndexOf('.');

		if (i > 0 && i < f.length() - 1) {
			fname = f.substring(0, i - 4);
		}
		return fname;
	}

	public static void main(String[] args) {
		// File file = new
		// File("D:\\TempFile\\20170609\\Ericsson_PM_201705311715-1730.tar.gz");
		decompress("customerUsage_201905_20190527.tar.gz");
		
		//unGzipFile("customerUsage_201905_20190527.tar.gz");
	}

	public static void unGzipFile(String sourcedir) {
		 String ouputfile = "";
		try {
		//建立gzip压缩文件输入流 
		 FileInputStream fin = new FileInputStream(sourcedir); 
		 //建立gzip解压工作流
		 GZIPInputStream gzin = new GZIPInputStream(fin); 
		 //建立解压文件输出流  
		 ouputfile = sourcedir.substring(0,sourcedir.lastIndexOf('.'));
		 ouputfile = ouputfile.substring(0,ouputfile.lastIndexOf('.'));
		 FileOutputStream fout = new FileOutputStream(ouputfile); 
		 int num;
		 byte[] buf=new byte[1024];

		 while ((num = gzin.read(buf,0,buf.length)) != -1)
		 { 
		  fout.write(buf,0,num); 
		 }


		 gzin.close(); 
		 fout.close(); 
		 fin.close(); 
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return;
		}
}